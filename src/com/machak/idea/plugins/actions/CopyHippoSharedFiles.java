/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */


package com.machak.idea.plugins.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
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
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.table.JBTable;
import com.machak.idea.plugins.config.ApplicationComponent;
import com.machak.idea.plugins.config.ProjectComponent;
import com.machak.idea.plugins.model.Assembly;
import com.machak.idea.plugins.model.DependencySet;


public class CopyHippoSharedFiles extends AnAction {
    private static final int CHECKED_COLUMN = 0;
    private static final int FILE_COLUMN = 1;
    private static final Pattern ARTIFACT_SPLITTER = Pattern.compile(":");
    private static final Pattern LIBRARY_MATCHER = Pattern.compile("(?:Maven:\\s*)(.*):(.*):(?:.*)");
    private static final Object[] COLUMN_NAMES = {"Files", "Delete?"};
    public static final String DEFAULT_DIST_FILE_PATH = "src/main/assembly/distribution.xml";
    public static final NotificationGroup log = NotificationGroup.logOnlyGroup("Hippo shared jars");
    public static final NotificationGroup ERROR_GROUP = new NotificationGroup("Hippo shared lib error messages", NotificationDisplayType.BALLOON, true);
    public static final NotificationGroup INFO_GROUP = new NotificationGroup("Hippo shared lib info messages", NotificationDisplayType.NONE, false);
    private Project project;
    private String[] myFiles;
    private boolean[] myCheckedMarks;

    public void actionPerformed(AnActionEvent event) {


        project = PlatformDataKeys.PROJECT.getData(event.getDataContext());
        if (project != null) {


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


            final Map<String, String> depMap = extractDependencies(component, distFile);
            if (depMap.isEmpty()) {
                info("No files to copy");
                return;
            }
            // cleanup old stuff
            processJars(component, tomcatSharedDirectory, sharedDirectory, depMap);


        }

    }

    private void copyJars(final String tomcatSharedDirectory, final Map<String, String> depMap) {
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
                            try {
                                copyFile(tomcatSharedDirectory, jarFiles[0]);
                            } catch (IOException e) {
                                error(String.format("Error while copy file%s", e.getMessage()));
                            }
                        }
                    }
                }
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

    private void processJars(final ApplicationComponent component, final String tomcatSharedDirectory, final File sharedDirectory, final Map<String, String> depMap) {

        final FilenameFilter fileFilter = createJarsFilter(component, depMap);
        final File[] jars = sharedDirectory.listFiles(fileFilter);
        if (!component.isShowDialog()) {
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

    private FilenameFilter createJarsFilter(final ApplicationComponent component, final Map<String, String> depMap) {
        final FilenameFilter fileFilter;
        if (component.isDeleteAllJars()) {
            final FilenameFilter allJarsFilter = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String name) {
                    return name.endsWith(".jar");
                }
            };
            fileFilter = allJarsFilter;
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
        return fileFilter;
    }

    private boolean notValid(final ApplicationComponent component) {
        return component == null || Strings.isNullOrEmpty(component.getTomcatDirectory());
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
