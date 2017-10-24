/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */


package com.machak.idea.plugins.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.dom.MavenDomUtil;
import org.jetbrains.idea.maven.dom.model.MavenDomProjectModel;
import org.jetbrains.idea.maven.model.MavenId;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RemoteConnection;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.remote.RemoteConfiguration;
import com.intellij.execution.remote.RemoteConfigurationType;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.artifacts.ModifiableArtifact;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.table.JBTable;
import com.intellij.util.xml.DomUtil;
import com.machak.idea.plugins.config.ApplicationSettingsComponent;
import com.machak.idea.plugins.config.BaseConfig;
import com.machak.idea.plugins.config.ProjectSettingsComponent;
import com.machak.idea.plugins.config.StorageState;
import com.machak.idea.plugins.model.Assembly;
import com.machak.idea.plugins.model.DependencySet;
import com.machak.idea.plugins.model.component.Component;
import com.machak.idea.plugins.util.VersionUtils;


public class CopyHippoSharedFiles extends AnAction {
    /**
     * Name of the project files
     */
    public static final CharMatcher VERSION_CHARS = CharMatcher.anyOf("0123456789-._");
    public static final String PROJECT_FILE = "hippo_project_directory.txt";
    public static final String DEFAULT_DIST_FILE_PATH = "src" + File.separator + "main" + File.separator + "assembly" + File.separator + "distribution.xml";
    public static final String DEFAULT_LOG4j_FILE_PATH = "conf" + File.separator + "log4j-dev.xml";
    public static final NotificationGroup log = NotificationGroup.logOnlyGroup("Hippo shared jars");
    public static final NotificationGroup ERROR_GROUP = new NotificationGroup("Hippo shared lib error messages", NotificationDisplayType.BALLOON, true);
    public static final NotificationGroup INFO_GROUP = new NotificationGroup("Hippo shared lib info messages", NotificationDisplayType.NONE, false);
    private static final int CHECKED_COLUMN = 0;
    private static final int FILE_COLUMN = 1;
    private static final Pattern ARTIFACT_SPLITTER = Pattern.compile(":");
    private static final Pattern LIBRARY_MATCHER = Pattern.compile("(?:Maven:\\s*)(.*):(.*):(?:.*)");
    private static final Object[] COLUMN_NAMES = {"Files", "Delete?"};
    private static final Pattern PATTERN_JAR_EXTENSION = Pattern.compile("(?:(.*))(.jar)$");
    private static final String HIPPO_TOMCAT = "HIPPO_TOMCAT";
    private Project project;
    private String[] myFiles;
    private boolean[] myCheckedMarks;

    @Override
    public void actionPerformed(AnActionEvent event) {


        project = CommonDataKeys.PROJECT.getData(event.getDataContext());
        if (project != null) {


            // project settings have higher priority (override):
            BaseConfig component = project.getComponent(ProjectSettingsComponent.class);
            if (notValid(component)) {
                // try to fetch application (global) settings:
                component = ApplicationManager.getApplication().getComponent(ApplicationSettingsComponent.class);
                if (notValid(component)) {
                    if (component == null) {
                        error("Couldn't read settings (project nor application wide)");
                        return;
                    }
                }
            }

            final StorageState state = component.getState();
            String tomcatSharedDirectory = state.getTomcatDirectory();
            // check if we have correct settings:
            if (Strings.isNullOrEmpty(tomcatSharedDirectory)) {
                error("Tomcat shared library not defined");
                return;
            }

            if (!tomcatSharedDirectory.endsWith(File.separator)) {
                tomcatSharedDirectory = tomcatSharedDirectory + File.separator;
            }
            final File sharedDirectory = new File(tomcatSharedDirectory);
            if (!sharedDirectory.exists()) {
                error("Tomcat shared library does not exists: " + tomcatSharedDirectory);
                return;
            }


            final String distributionFilePath = extractDistributionFilePath(project, component);

            final File distFile = new File(distributionFilePath);
            if (!distFile.exists()) {
                error("Missing dist file: " + distributionFilePath);
                return;
            }

            createRemoteDebugger();
            //createDebugPort();
            //ConfigurationFactory factory =

            //############################################
            // CHECK FOR LOG4J FILE
            //############################################

            if (state.isCopyLog4J()) {
                final String logFilePath = extractLog4jFilePath(project, component);
                final File logFile = new File(logFilePath);
                if (!logFile.exists()) {
                    error("Missing log4j file : " + logFilePath);
                }
                final String tomcatRootDir = getDirectory(state.getTomcatRootDirectory());

                if (Strings.isNullOrEmpty(tomcatRootDir)) {
                    error("Cannot copy log4j file");

                } else {

                    final String logText = readText(logFile);
                    if (logText != null) {
                        final String filePath = tomcatRootDir.endsWith(File.separator)
                                ? tomcatRootDir + "conf" + File.separator + "log4j.xml" :
                                tomcatRootDir + File.separator + "conf" + File.separator + "log4j.xml";

                        createAndWriteFile(filePath, logText);
                    }

                }


            }
            if (state.isAutoModifyProjectOutput()) {
                modifyArtifactsOutput(project, component);
            }

            final Map<String, String> depMap = extractDependencies(component, distFile);
            if (depMap.isEmpty()) {
                info("No files to copy");
                return;
            }
            // cleanup old stuff
            processJars(component, tomcatSharedDirectory, sharedDirectory, depMap);
            // create shared file:
            if (state.isCopyContentJar() && depMap.size() > 0 && needsSharedContent(distFile)) {
                extractContentDependency(project, state);
            }

            // check if we need to create a project root file:
            if (state.isCreateProjectFile()) {
                // first check if we have an override:
                final String projectRootDir = getDirectory(state.getProjectRootDirectory());
                if (!Strings.isNullOrEmpty(projectRootDir)) {
                    final File projectDirectory = new File(state.getProjectRootDirectory());
                    if (!projectDirectory.exists()) {
                        error("Cannot create project root file for tomcat: project root directory is defined but does not exists: " + projectRootDir);
                    }
                }
                // check if tomcat root is defined:

                final String tomcatRootDir = getDirectory(state.getTomcatRootDirectory());

                if (Strings.isNullOrEmpty(tomcatRootDir)) {
                    error("Cannot create project root file for tomcat: Tomcat root directory not defined (or not defined)");
                    return;
                }
                final File tomcatDirectory = new File(tomcatRootDir);
                if (!tomcatDirectory.exists()) {
                    error("Cannot create project root file for tomcat: tomcat root directory does not exists: " + tomcatDirectory);
                }


                // check override:
                String basePath = state.getProjectRootDirectory();
                if (Strings.isNullOrEmpty(basePath)) {
                    basePath = project.getBasePath();
                }
                if (Strings.isNullOrEmpty(basePath)) {
                    error("Cannot create project root file, project path was empty or null");
                    return;
                }
                // create a popup:                
                showProjectRootPopup(basePath, tomcatDirectory);


            }


        }

    }

    private void createRemoteDebugger() {
        final String remoteApp = "HIPPO_REMOTE";
        if (debugExists(remoteApp)) {
            return;
        }
        final RunManager runManager = RunManager.getInstance(project);
        final RemoteConfigurationType remoteType = RemoteConfigurationType.getInstance();
        final ConfigurationFactory[] configurationFactories = remoteType.getConfigurationFactories();
        final ConfigurationFactory configurationFactory = configurationFactories[0];
        final RunConfiguration myremote = configurationFactory.createConfiguration(remoteApp, configurationFactory.createTemplateConfiguration(project));
        final RunnerAndConfigurationSettings configuration = runManager.createConfiguration(myremote, configurationFactory);
        configuration.setEditBeforeRun(false);
        runManager.addConfiguration(configuration, false);
        final List<RunConfiguration> allConfigurationsList = runManager.getAllConfigurationsList();
        for (RunConfiguration r : allConfigurationsList) {
            if (r.getName().equals(remoteApp)) {
                final RemoteConfiguration s = (RemoteConfiguration) r;
                s.HOST = "localhost";
                s.PORT = "55555";
                final RemoteConnection remoteConnection = s.createRemoteConnection();
                remoteConnection.setAddress("8080");


            }


        }

    }

    private boolean debugExists(final String app) {
        final RunManager runManager = RunManager.getInstance(project);
        final List<RunConfiguration> allConfigurationsList = runManager.getAllConfigurationsList();

        for (RunConfiguration r : allConfigurationsList) {
            if (r.getName().equals(app)) {
                return true;
            }

        }
        return false;

    }

    /*private void createDebugPort() {
        if (debugExists(HIPPO_TOMCAT)) {
            return;
        }
        final TomcatIntegration instance = TomcatIntegration.getInstance();
        final TomcatConfiguration type = TomcatConfiguration.getInstance();
        final RunManager runManager = RunManager.getInstance(project);
        final ConfigurationFactory[] configFactories = type.getConfigurationFactories();
        final J2EEConfigurationFactory j2EEConfigurationFactory = J2EEConfigurationFactory.getInstance();
        final ConfigurationFactory c = configFactories[0];
        final TomcatRemoteModel serverSpecific = new TomcatRemoteModel();

        final RunConfiguration configFactory = j2EEConfigurationFactory.createJ2EERunConfiguration(c, project, serverSpecific, instance, false, ExecutableObjectStartupPolicy.DEFAULT);
        //final com.intellij.javaee.run.configuration.CommonStrategy runConfiguration = (CommonStrategy) configFactory.createConfiguration("TOMCAT", configFactory.createTemplateConfiguration(project));

        //runConfiguration.setLocal(false);
        //final JavaeeRunConfigurationCommonSettingsBean settingsBean = runConfiguration.getSettingsBean();
        //settingsBean.OPEN_IN_BROWSER = false;
        //settingsBean.HOST = "localhost";
        final RunnerAndConfigurationSettings configuration = runManager.createConfiguration(configFactory, c);
        configuration.setEditBeforeRun(false);
        configuration.setName(HIPPO_TOMCAT);
        runManager.addConfiguration(configuration, false);
        final List<RunConfiguration> allConfigurationsList = runManager.getAllConfigurationsList();
        for (RunConfiguration r : allConfigurationsList) {
            if (r.getName().equals(HIPPO_TOMCAT)) {
                final CommonStrategy s = (CommonStrategy) r;
                s.setApplicationServerName("Tomcat8");
                s.setLocal(false);
                s.setUrlToOpenInBrowser("http://localhost:8080/site");
                final JavaeeRunConfigurationCommonSettingsBean settingsBean = ((CommonStrategy) r).getSettingsBean();
                settingsBean.OPEN_IN_BROWSER = false;

               *//* final ProgramRunner runner = ProgramRunnerUtil.getRunner("MACHAK_TOMCAT", configuration);
                if (runner != null) {
                    final ConfigurationPerRunnerSettings configurationSettings = configuration.getConfigurationSettings(runner);
                    System.out.println("configurationSettings = " + configurationSettings);
                    
                }*//*


            }

        }
    }*/

    private void modifyArtifactsOutput(final Project project, final BaseConfig config) {
        final ArtifactManager manager = ArtifactManager.getInstance(project);
        final Artifact[] sortedArtifacts = manager.getSortedArtifacts();
        for (Artifact artifact : sortedArtifacts) {
            final ArtifactType artifactType = artifact.getArtifactType();
            if (artifactType.getClass().getSimpleName().equals("ExplodedWarArtifactType")) {
                final String outputFilePath = artifact.getOutputFilePath();
                if (outputFilePath != null) {
                    if (outputFilePath.endsWith(File.separator + "cms")) {
                        ((ModifiableArtifact) artifact).setOutputPath(
                                createWebDirPath(config, "cms")
                        );

                    } else if (outputFilePath.endsWith(File.separator + "site")) {
                        ((ModifiableArtifact) artifact).setOutputPath(
                                createWebDirPath(config, "site")
                        );

                    } else if (outputFilePath.endsWith(File.separator + "essentials")) {
                        ((ModifiableArtifact) artifact).setOutputPath(
                                createWebDirPath(config, "essentials")
                        );
                    }
                }

            }

        }
    }

    @NotNull
    private String createWebDirPath(final BaseConfig config, final String app) {
        return config.getState().getTomcatRootDirectory()
                + File.separator + "webapps" + File.separator + app;
    }

    private void showProjectRootPopup(final String basePath, final File tomcatDirectory) {

        final DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle("Create project file:");
        final JPanel simplePanel = new JPanel();
        simplePanel.add(new JLabel("Following path will be used as project base:\n" + basePath));
        dialogBuilder.setCenterPanel(simplePanel);

        final Action acceptAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                // check if file exists and overwrite:
                final String filePath = tomcatDirectory.getAbsolutePath() + File.separator + "bin" + File.separator + PROJECT_FILE;
                createAndWriteFile(filePath, basePath);


                dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        };
        acceptAction.putValue(Action.NAME, "OK");
        dialogBuilder.addAction(acceptAction);

        dialogBuilder.addCancelAction();
        dialogBuilder.showModal(true);
    }


    private static String readText(final File file) {
        try {
            final ByteSource source = Resources.asByteSource(file.toURI().toURL());
            return source.asCharSource(Charsets.UTF_8).read();
        } catch (IOException e) {

        }
        return null;
    }

    private void createAndWriteFile(final String filePath, final String content) {
        info("Creating file: " + filePath);
        FileWriter writer = null;
        try {
            final File file = new File(filePath);
            if (!file.exists()) {
                final boolean created = file.createNewFile();
                if (created) {
                    info("Created file:" + filePath);
                }
            }
            writer = new FileWriter(file, false);
            writer.write(content);

        } catch (IOException error) {
            error(error.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e1) {
                // ignore
            }
        }
    }

    private String getDirectory(String input) {

        // check if we have correct settings:
        if (Strings.isNullOrEmpty(input)) {
            //error("Directory not defined");
            return null;
        }

        if (!input.endsWith(File.separator)) {
            input = input + File.separator;
        }
        return input;
    }

    private void copyJars(final String tomcatSharedDirectory, final Map<String, String> depMap) {
        depMap.put("hippo-services-autoreload", "org.onehippo.cms7");
        final ModuleManager manager = ModuleManager.getInstance(project);
        final Module[] modules = manager.getModules();
        // add autoreload shit..moved to profile so not listed as model dependency..just to mak our life easier..:(
        MavenProjectsManager m = MavenProjectsManager.getInstance(project);
        final List<MavenProject> rootProjects = m.getRootProjects();
        final MavenProject mavenProject = rootProjects.get(0);
        final MavenDomProjectModel model = MavenDomUtil.getMavenDomProjectModel(project, mavenProject.getFile());
        if (model != null) {
            //noinspection rawtypes
            new WriteCommandAction(project, "Add Maven Dependency", DomUtil.getFile(model)) {
                @Override
                protected void run(@NotNull Result result) {
                    MavenId id = new MavenId("org.onehippo.cms7", "hippo-services-autoreload", null);
                    MavenDomUtil.createDomDependency(model, null, id);
                }
            }.execute();
        }
        final Set<LibWrapper> jars = new HashSet<>();
        for (Module module : modules) {
            final OrderEntry[] orderEntries = ModuleRootManager.getInstance(module).getOrderEntries();
            for (OrderEntry library : orderEntries) {
                if (library instanceof LibraryOrderEntry) {
                    final LibraryOrderEntry lib = (LibraryOrderEntry) library;
                    final String libraryName = lib.getLibraryName();
                    if (libraryName != null) {
                        //error(libraryName);
                        final Matcher matcher = LIBRARY_MATCHER.matcher(libraryName);
                        if (matcher.matches()) {
                            final String artifactName = matcher.group(1);
                            final String groupName = matcher.group(2);
                            final String ourName = depMap.get(groupName);
                            if (ourName == null || !ourName.equals(artifactName)) {
                                continue;
                            }
                            final VirtualFile[] jarFiles = lib.getFiles(OrderRootType.CLASSES);
                            if (jarFiles.length == 1) {
                                final VirtualFile jarFile = jarFiles[0];
                                final LibWrapper wrapper = new LibWrapper(artifactName, groupName, ourName, jarFile, module);
                                jars.add(wrapper);
                            }
                        }
                    }
                }
            }

        }
        // copy files
        final Collection<VirtualFile> filteredFiles = filterDuplicates(jars, depMap);
        for (VirtualFile jar : filteredFiles) {
            try {
                copyFile(tomcatSharedDirectory, jar);
            } catch (IOException e) {
                error(String.format("Error while copy file%s", e.getMessage()));
            }
        }

    }

    private Collection<VirtualFile> filterDuplicates(final Iterable<LibWrapper> libWrapperSet, final Map<String, String> depMap) {
        final Map<String, VirtualFile> filtered = new HashMap<>();
        // add autoexport:
        // check if duplicate & log a warning
        for (Map.Entry<String, String> dependency : depMap.entrySet()) {
            final String name = dependency.getKey();
            LibWrapper found = null;
            String foundVersion = null;
            for (LibWrapper wrapper : libWrapperSet) {
                final String jarFile = wrapper.jarFile.getName();
                if (jarFile.startsWith(name)) {
                    if (found == null) {
                        foundVersion = cleanupVersion(name, jarFile);
                        found = wrapper;
                        filtered.put(name, found.getJarFile());
                        continue;
                    }
                    // check if version is same:
                    final String version = cleanupVersion(name, jarFile);
                    if (version.equals(foundVersion)) {
                        continue;
                    }
                    // version difference, keep highest:
                    final int highest = VersionUtils.compareVersionNumbers(foundVersion, version);
                    final String foundModuleName = found.getModule().getName();
                    final String currentModuleName = wrapper.getModule().getName();
                    final String foundJarPath = found.getJarFile().getPath();
                    // always remove:
                    final VirtualFile remove = filtered.remove(name);
                    // keep already found one::
                    if (highest == 1) {
                        filtered.put(name, found.getJarFile());
                        // keep highest:
                        error(String.format("Found duplicate jar: [%s] in module: [%s]", jarFile, currentModuleName));
                        error(String.format("Keeping duplicate jar: [%s] from module: %s", found.getJarFile().getName(), foundModuleName));
                    }
                    // keep *new* found one:
                    else if (highest == -1) {
                        // swap and continue
                        found = wrapper;
                        foundVersion = version;
                        filtered.put(name, found.getJarFile());
                        error(String.format("Found duplicate jar: [%s] in module: [%s]", foundJarPath, foundModuleName));
                        error(String.format("Keeping duplicate jar: [%s] from module: [%s]", found.getJarFile().getName(), currentModuleName));
                    } else {
                        // nothing we can do, just print error:
                        error(String.format("Check versions of: [%s] from: [%s] and: [%s] form module: [%s]", foundJarPath, foundModuleName, jarFile, currentModuleName));
                        filtered.put(name, wrapper.getJarFile());
                    }
                }
            }
        }
        return filtered.values();
    }

    private String cleanupVersion(final String name, final String jarFile) {
        String version = jarFile;
        if (version.startsWith(name)) {
            version = version.substring(name.length(), jarFile.length());
            version = PATTERN_JAR_EXTENSION.matcher(version).replaceAll("$1");
            version = VERSION_CHARS.retainFrom(version);

        }
        return version;

    }

    private String extractLog4jFilePath(final Project project, final BaseConfig config) {
        final String logFilePath;
        final StorageState state = config.getState();
        if (Strings.isNullOrEmpty(state.getLog4JDirectory())) {
            final String basePath = project.getBasePath();
            if (basePath != null && basePath.endsWith(File.separator)) {
                logFilePath = project.getBasePath() + DEFAULT_LOG4j_FILE_PATH;
            } else {
                logFilePath = project.getBasePath() + File.separator + DEFAULT_LOG4j_FILE_PATH;
            }
        } else {
            logFilePath = state.getLog4JDirectory();
        }
        return logFilePath;
    }


    private String extractDistributionFilePath(final Project project, final BaseConfig component) {
        final String distributionFilePath;
        final StorageState state = component.getState();
        if (Strings.isNullOrEmpty(state.getDistFile())) {
            final String basePath = project.getBasePath();
            if (basePath != null && basePath.endsWith(File.separator)) {
                distributionFilePath = project.getBasePath() + DEFAULT_DIST_FILE_PATH;
            } else {
                distributionFilePath = project.getBasePath() + File.separator + DEFAULT_DIST_FILE_PATH;
            }
        } else {
            distributionFilePath = state.getDistFile();
        }
        return distributionFilePath;
    }

    private void copyFile(final String tomcatSharedDirectory, final VirtualFile jarFile) throws IOException {
        final boolean exists = jarFile.exists();
        if (exists) {
            String canonicalPath = jarFile.getCanonicalPath();
            if (canonicalPath != null) {
                if (canonicalPath.lastIndexOf("!/") != -1) {
                    canonicalPath = canonicalPath.substring(0, canonicalPath.length() - 2);
                }
                final File jar = new File(canonicalPath);
                if (jar.exists() && !jar.isDirectory()) {
                    final String destination = String.format("%s%s", tomcatSharedDirectory, jarFile.getName());
                    final File toFile = new File(destination);
                    if (!toFile.exists()) {
                        info(String.format("Copy file: %s as: %s", canonicalPath, toFile.getAbsolutePath()));
                        FileUtil.copy(jar, toFile);
                    }
                }
            }
        }
    }

    /**
     * Only copy content if new style disttribution.xml is available (v.11. content style)
     */
    private boolean needsSharedContent(final File distFile) {
        try {
            final JAXBContext context = JAXBContext.newInstance(Assembly.class, Component.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            @SuppressWarnings("unchecked") final JAXBElement<Assembly> jaxbElement = (JAXBElement<Assembly>) unmarshaller.unmarshal(distFile);
            final Assembly assembly = jaxbElement.getValue();
            final Assembly.DependencySets dependencySets = assembly.getDependencySets();
            if (dependencySets == null) {
                return true;
            }
        } catch (JAXBException e) {
            error("Error creating marshaller" + e.getMessage());
        }
        return false;
    }

    private Map<String, String> extractDependencies(final BaseConfig config, final File distFile) {
        final Map<String, String> depMap = new HashMap<>();
        //final StorageState state = config.getState();
        try {
            final JAXBContext context = JAXBContext.newInstance(Assembly.class, Component.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            @SuppressWarnings("unchecked") final JAXBElement<Assembly> jaxbElement = (JAXBElement<Assembly>) unmarshaller.unmarshal(distFile);
            final Assembly assembly = jaxbElement.getValue();
            final Assembly.DependencySets dependencySets = assembly.getDependencySets();
            if (dependencySets == null) {
                // this is most probably new shit (v.11)  setup:
                final Assembly.ComponentDescriptors descriptors = assembly.getComponentDescriptors();
                if (descriptors == null) {
                    return depMap;
                }
                final List<String> componentDescriptor = descriptors.getComponentDescriptor();
                final File root = distFile.getParentFile();
                for (String descriptor : componentDescriptor) {
                    final String fullPath = root.getAbsolutePath() + File.separator + descriptor;
                    final File file = new File(fullPath);
                    if (file.exists()) {
                        @SuppressWarnings("unchecked") final JAXBElement<Component> jaxbComponent = (JAXBElement<Component>) unmarshaller.unmarshal(file);
                        final Component component = jaxbComponent.getValue();
                        // we are only interested in dependencies....
                        final Component.DependencySets sets = component.getDependencySets();
                        depMap.putAll(parseDependencySet(config, makeAssemblySet(sets)));

                        // oh no..someone decided to make things difficult...
                        // see extractContentDependency...skip parsing of .jar artifact because we do not build with ant
                        // final Component.Files files = component.getFiles();


                    }
                }


            } else {
                depMap.putAll(parseDependencySet(config, dependencySets));
            }
        } catch (JAXBException e) {
            error("Error creating marshaller" + e.getMessage());
        }
        return depMap;
    }

    private void extractContentDependency(final Project project, final StorageState state) {
        final ModuleManager manager = ModuleManager.getInstance(project);
        final Module[] modules = manager.getModules();
        for (Module module : modules) {
            final String name = module.getName();
            // v 11 style && v 12 style
            if (name.endsWith("-content") || name.endsWith("-development") || name.endsWith("-application")) {
                final CompilerManager compilerManager = CompilerManager.getInstance(project);
                compilerManager.compile(module, (b, i, i1, compileContext) -> {
                    final ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
                    final String outputPath = moduleRootManager.getModuleExtension(CompilerModuleExtension.class).getCompilerOutputUrl();
                    final File outputDirectory = new File(VfsUtilCore.urlToPath(outputPath));
                    if (outputDirectory.exists()) {
                        final String targetName = state.getTomcatDirectory() + File.separator + module.getName() + ".jar";
                        JarUtils.makeJar(outputDirectory, new File(targetName));
                        info("Created content jar:  " + targetName);
                    } else {
                        error("Couldn't find output directory of content module: " + module.getName());
                    }
                });
            }
        }
    }

    private Assembly.DependencySets makeAssemblySet(final Component.DependencySets sets) {
        if (sets == null) {
            return new Assembly.DependencySets();
        }
        final Assembly.DependencySets dependencySets = new Assembly.DependencySets();
        final List<com.machak.idea.plugins.model.component.DependencySet> dependencySet = sets.getDependencySet();
        if (dependencySet != null) {
            for (com.machak.idea.plugins.model.component.DependencySet set : dependencySet) {
                final DependencySet d = new DependencySet();
                d.setOutputDirectory(set.getOutputDirectory());
                final com.machak.idea.plugins.model.component.DependencySet.Includes includes = set.getIncludes();
                if (includes != null && includes.getInclude() != null) {
                    d.setIncludes(new DependencySet.Includes());
                    final List<String> includeList = includes.getInclude();
                    for (String inc : includeList) {
                        d.getIncludes().getInclude().add(inc);
                    }
                }
                dependencySets.getDependencySet().add(d);
            }
        }
        return dependencySets;
    }

    private Map<String, String> parseDependencySet(final BaseConfig config, final Assembly.DependencySets dependencySets) {
        final List<DependencySet> dependencySet = dependencySets.getDependencySet();
        final Map<String, String> depMap = new HashMap<>();
        final StorageState state = config.getState();
        for (DependencySet set : dependencySet) {
            final String outputDirectory = set.getOutputDirectory();
            boolean accept = outputDirectory.equals("/shared/lib") || outputDirectory.equals("shared/lib");
            if (!accept && state.isCopyOtherJars()) {
                accept = outputDirectory.equals("/common/lib") || outputDirectory.equals("common/lib");
            }
            if (accept) {
                final DependencySet.Includes includes = set.getIncludes();
                final List<String> include = includes.getInclude();
                for (String inc : include) {
                    final String[] dep = ARTIFACT_SPLITTER.split(inc);
                    final String groupId = dep[0];
                    final String artifactId = dep[1];
                    depMap.put(artifactId, groupId);
                }
            }
        }
        return depMap;
    }

    private void processJars(final BaseConfig component, final String tomcatSharedDirectory, final File sharedDirectory, final Map<String, String> depMap) {

        final FilenameFilter fileFilter = createJarsFilter(component, depMap);
        final StorageState state = component.getState();
        final File[] jars = sharedDirectory.listFiles(fileFilter);
        if (jars == null) {
            return;
        }
        if (!state.isShowDialog()) {
            for (File jar : jars) {
                deleteFile(jar);
            }
            copyJars(tomcatSharedDirectory, depMap);
            return;
        }
        final int length = jars.length;
        myFiles = new String[length];
        myCheckedMarks = new boolean[length];
        for (int i = 0; i < length; i++) {
            final File file = jars[i];
            myFiles[i] = file.getAbsolutePath();
            myCheckedMarks[i] = true;
        }


        final TableModel model = new MyTableModel();
        final JBTable myTable = new JBTable();
        myTable.setPreferredSize(new Dimension(700, 400));
        myTable.setModel(model);
        final TableColumnModel columnModel = myTable.getColumnModel();
        columnModel.getColumn(CHECKED_COLUMN).setCellRenderer(new BooleanTableCellRenderer());
        columnModel.getColumn(CHECKED_COLUMN).setPreferredWidth(40);
        columnModel.getColumn(FILE_COLUMN).setPreferredWidth(660);

        final DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle("Files to delete");
        dialogBuilder.setCenterPanel(myTable);

        final Action deleteAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                final int length = model.getRowCount();
                for (int i = 0; i < length; i++) {
                    final Boolean checked = (Boolean) model.getValueAt(i, CHECKED_COLUMN);
                    if (checked) {
                        final File jar = jars[i];
                        deleteFile(jar);
                    }
                }
                // do copy:
                copyJars(tomcatSharedDirectory, depMap);
                dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        };
        deleteAction.putValue(Action.NAME, "OK");
        dialogBuilder.addAction(deleteAction);

        dialogBuilder.addCancelAction();
        dialogBuilder.showModal(true);

    }

    private void deleteFile(final File jar) {
        final String filePath = jar.getPath();
        final boolean deleted = jar.delete();
        if (deleted) {
            info(String.format("Deleted: %s", filePath));
        } else {
            warn(String.format("Failed to delete: %s", filePath));
        }
    }

    private FilenameFilter createJarsFilter(final BaseConfig component, final Map<String, String> depMap) {
        final FilenameFilter fileFilter;
        final StorageState state = component.getState();
        if (state.isDeleteAllJars()) {
            fileFilter = (file, name) -> name.endsWith(".jar");
        } else {
            fileFilter = (file, name) -> {
                final boolean jar = name.endsWith(".jar");
                if (!jar) {
                    return false;
                }
                for (String prefix : depMap.keySet()) {
                    if (name.startsWith(prefix)) {
                        return true;
                    }
                }
                return false;
            };
        }
        return fileFilter;
    }

    private boolean notValid(final BaseConfig component) {
        return component == null || Strings.isNullOrEmpty(component.getState().getTomcatDirectory());
    }

    private void error(final String message) {
        final Notification notification = ERROR_GROUP.createNotification(message, NotificationType.ERROR);
        notification.notify(project);
    }

    private void info(final String message) {
        final Notification notification = INFO_GROUP.createNotification(message, NotificationType.INFORMATION);
        notification.notify(project);
    }

    private void warn(final String message) {
        final Notification notification = INFO_GROUP.createNotification(message, NotificationType.WARNING);
        notification.notify(project);
    }

    private class MyTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;

        public int getRowCount() {
            return myFiles.length;
        }

        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case CHECKED_COLUMN:
                    return myCheckedMarks[rowIndex] ? Boolean.TRUE : Boolean.FALSE;
                case FILE_COLUMN:
                    return myFiles[rowIndex];

            }
            throw new RuntimeException("Incorrect column index");
        }

        public String getColumnName(int column) {
            switch (column) {
                case CHECKED_COLUMN:
                    return "Delete";
                case FILE_COLUMN:
                    return "Files";
                default:
                    throw new RuntimeException("Incorrect column index");
            }
        }

        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == CHECKED_COLUMN) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == CHECKED_COLUMN;
        }

        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            if (columnIndex == CHECKED_COLUMN) {
                myCheckedMarks[rowIndex] = (Boolean) aValue;
                fireTableRowsUpdated(rowIndex, rowIndex);
            } else {
                String name = (String) aValue;
                myFiles[rowIndex] = name;
            }
        }
    }
}
