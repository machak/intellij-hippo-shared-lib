/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */


package com.machak.idea.plugins.actions;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.common.base.Strings;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.machak.idea.plugins.config.ApplicationComponent;
import com.machak.idea.plugins.config.ProjectComponent;
import com.machak.idea.plugins.model.Assembly;
import com.machak.idea.plugins.model.DependencySet;

public class CopyHippoSharedFiles extends AnAction {


    private static final Pattern ARTIFACT_SPLITTER = Pattern.compile(":");
    private static final Pattern LIBRARY_MATCHER = Pattern.compile("(?:Maven:\\s*)(.*):(.*):(?:.*)");
    public static final String DEFAULT_DIST_FILE_PATH = "src/main/assembly/distribution.xml";
    public static final NotificationGroup log = NotificationGroup.logOnlyGroup("Hippo shared jars");
    public static final NotificationGroup ERROR_GROUP = new NotificationGroup("Hippo shared lib error messages", NotificationDisplayType.BALLOON, true);
    public static final NotificationGroup INFO_GROUP = new NotificationGroup("Hippo shared lib info messages", NotificationDisplayType.NONE, false);
    private Project myProject;

    public void actionPerformed(AnActionEvent event) {


        final Project project = PlatformDataKeys.PROJECT.getData(event.getDataContext());
        if (project != null) {

            myProject = project;
            // project settings have higher priority (override):
            ApplicationComponent component = project.getComponent(ProjectComponent.class);
            if (notValid(component)) {
                // try to fetch application (global) settings:
                component = ApplicationManager.getApplication().getComponent(ApplicationComponent.class);
                if (notValid(component)) {
                    if (component == null) {
                        error("Couldn't read settings (project nor application wide)");
                        return;
                    }
                }
            }

            String tomcatSharedDirectory = component.getTomcatDirectory();
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

            try {


                final Map<String, String> depMap = extractDependencies(component, distFile);
                if (depMap.isEmpty()) {
                    info("No files to copy");
                    return;
                }
                // cleanup old stuff
                deleteOldJars(component, sharedDirectory, depMap);


                final Module[] modules = ModuleManager.getInstance(project).getModules();
                for (Module module : modules) {
                    final OrderEntry[] orderEntries = ModuleRootManager.getInstance(module).getOrderEntries();
                    for (OrderEntry library : orderEntries) {
                        if (library instanceof LibraryOrderEntry) {
                            final LibraryOrderEntry lib = (LibraryOrderEntry) library;
                            final String libraryName = lib.getLibraryName();
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
                                    copyFile(tomcatSharedDirectory, jarFiles[0]);
                                }
                            }
                        }
                    }
                }

            } catch (IOException e) {
                error("Error while copy file" + e.getMessage());
            }

        }

    }

    private String extractDistributionFilePath(final Project project, final ApplicationComponent component) {
        final String distributionFilePath;
        if (Strings.isNullOrEmpty(component.getDistFile())) {
            final String basePath = project.getBasePath();
            if (basePath.endsWith(File.separator)) {
                distributionFilePath = project.getBasePath() + DEFAULT_DIST_FILE_PATH;
            } else {
                distributionFilePath = project.getBasePath() + File.separator + DEFAULT_DIST_FILE_PATH;
            }
        } else {
            distributionFilePath = component.getDistFile();
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

    private Map<String, String> extractDependencies(final ApplicationComponent component, final File distFile) {
        final Map<String, String> depMap = new HashMap<String, String>();

        try {
            final JAXBContext context = JAXBContext.newInstance(Assembly.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            @SuppressWarnings("unchecked")
            final JAXBElement<Assembly> jaxbElement = (JAXBElement<Assembly>) unmarshaller.unmarshal(distFile);
            final Assembly assembly = jaxbElement.getValue();
            final Assembly.DependencySets dependencySets = assembly.getDependencySets();
            final List<DependencySet> dependencySet = dependencySets.getDependencySet();

            for (DependencySet set : dependencySet) {
                final String outputDirectory = set.getOutputDirectory();
                boolean accept = outputDirectory.equals("/shared/lib");
                if (!accept && component.isCopyOtherJars()) {
                    accept = outputDirectory.equals("/common/lib");
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
        } catch (JAXBException e) {
            error("Error creating marshaller" + e.getMessage());
        }
        return depMap;
    }

    private void deleteOldJars(final ApplicationComponent component, final File sharedDirectory, final Map<String, String> depMap) {
        final FilenameFilter fileFilter;
        if (component.isDeleteAllJars()) {
            fileFilter = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String name) {
                    return name.endsWith(".jar");
                }
            };
        } else {
            fileFilter = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String name) {
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
                }
            };
        }
        final File[] jars = sharedDirectory.listFiles(fileFilter);
        for (File jar : jars) {
            final String filePath = jar.getAbsolutePath();

            final boolean delete = jar.delete();
            if (delete) {
                info(String.format("Deleted: %s", filePath));
            } else {
                warn(String.format("Failed to delete: %s", filePath));
            }
        }
    }

    private boolean notValid(final ApplicationComponent component) {
        return component == null || Strings.isNullOrEmpty(component.getTomcatDirectory());
    }

    private void error(final String message) {
        final Notification notification = ERROR_GROUP.createNotification(message, NotificationType.ERROR);
        notification.notify(myProject);
    }

    private void info(final String message) {
        final Notification notification = INFO_GROUP.createNotification(message, NotificationType.INFORMATION);
        notification.notify(myProject);
    }

    private void warn(final String message) {
        final Notification notification = INFO_GROUP.createNotification(message, NotificationType.WARNING);
        notification.notify(myProject);
    }


}
