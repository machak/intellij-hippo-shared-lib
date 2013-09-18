


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import javax.swing.JComponent;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;


@State(name = "HippoSharedApplicationConfig", storages = {@Storage(id = "dir", file = StoragePathMacros.APP_CONFIG + "/hippo_shared_libs.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class ApplicationComponent implements com.intellij.openapi.components.ApplicationComponent, Configurable, PersistentStateComponent<Element> {


    public static final String DELETE_JARS_ATTRIBUTE = "deleteAllJars";
    public static final String SHOW_DIALOG_ATTRIBUTE = "showDialog";
    public static final String COPY_JARS_ATTRIBUTE = "copyOtherJars";
    public static final String TOMCAT_DIR_ATTRIBUTE = "tomcatDirectory";
    public static final String TOMCAT_ROOT_DIR_ATTRIBUTE = "tomcatRootDirectory";
    public static final String DIST_FILE_ATTRIBUTE = "distFile";
    public static final String CREATE_PROJECT_FILE_ATTRIBUTE = "createProjectFile";
    public static final String PROJECT_ROOT_FILE_ATTRIBUTE = "projectRootFile";
    public static final String CONFIGURATION_CONFIG_ELEMENT = "hippo-shared-config";
    private boolean deleteAllJars;
    private boolean copyOtherJars;
    private String tomcatDirectory;
    private String tomcatRootDirectory;
    private String projectRootDirectory;
    private String distFile;
    private PluginConfiguration configPane;
    private boolean showDialog;
    private boolean createProjectFile;

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "HippoSharedLibsComponent";
    }

    @Override
    public Element getState() {
        final Element element = new Element(CONFIGURATION_CONFIG_ELEMENT);
        checkNullSave(element, COPY_JARS_ATTRIBUTE, String.valueOf(copyOtherJars));
        checkNullSave(element, DELETE_JARS_ATTRIBUTE, String.valueOf(deleteAllJars));
        checkNullSave(element, CREATE_PROJECT_FILE_ATTRIBUTE, String.valueOf(createProjectFile));
        checkNullSave(element, SHOW_DIALOG_ATTRIBUTE, String.valueOf(showDialog));
        checkNullSave(element, TOMCAT_DIR_ATTRIBUTE, tomcatDirectory);
        checkNullSave(element, TOMCAT_ROOT_DIR_ATTRIBUTE, tomcatDirectory);
        checkNullSave(element, PROJECT_ROOT_FILE_ATTRIBUTE, projectRootDirectory);
        checkNullSave(element, DIST_FILE_ATTRIBUTE, distFile);
        return element;
    }

    @Override
    public void loadState(final Element element) {
        String copyJars = element.getAttributeValue(COPY_JARS_ATTRIBUTE);
        if (StringUtils.isNotBlank(copyJars)) {
            copyOtherJars = Boolean.valueOf(copyJars);
        }
        String deleteJars = element.getAttributeValue(DELETE_JARS_ATTRIBUTE);
        if (StringUtils.isNotBlank(deleteJars)) {
            deleteAllJars = Boolean.valueOf(deleteJars);
        }
        String createProjectRoot = element.getAttributeValue(CREATE_PROJECT_FILE_ATTRIBUTE);
        if (StringUtils.isNotBlank(createProjectRoot)) {
            createProjectFile = Boolean.valueOf(createProjectRoot);
        }
        String show = element.getAttributeValue(SHOW_DIALOG_ATTRIBUTE);
        if (StringUtils.isNotBlank(show)) {
            showDialog = Boolean.valueOf(show);
        }
        tomcatDirectory = element.getAttributeValue(TOMCAT_DIR_ATTRIBUTE);
        tomcatRootDirectory = element.getAttributeValue(TOMCAT_ROOT_DIR_ATTRIBUTE);
        projectRootDirectory = element.getAttributeValue(PROJECT_ROOT_FILE_ATTRIBUTE);
        distFile = element.getAttributeValue(DIST_FILE_ATTRIBUTE);

    }

    public boolean isCreateProjectFile() {
        return createProjectFile;
    }

    public void setCreateProjectFile(final boolean createProjectFile) {
        this.createProjectFile = createProjectFile;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(final boolean showDialog) {
        this.showDialog = showDialog;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Hippo shared libs";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (configPane == null) {
            configPane = new PluginConfiguration();
        }
        return configPane.createComponent();
    }

    @Override
    public boolean isModified() {
        return configPane != null && configPane.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configPane != null) {
            configPane.storeDataTo(this);
        }
    }

    @Override
    public void reset() {
        if (configPane != null) {
            configPane.readDataFrom(this);
        }
    }

    @Override
    public void disposeUIResources() {
        configPane = null;
    }

    public boolean isDeleteAllJars() {
        return deleteAllJars;
    }

    public void setDeleteAllJars(boolean deleteAllJars) {
        this.deleteAllJars = deleteAllJars;
    }

    public boolean isCopyOtherJars() {
        return copyOtherJars;
    }

    public void setCopyOtherJars(boolean copyOtherJars) {
        this.copyOtherJars = copyOtherJars;
    }

    public String getTomcatDirectory() {
        return tomcatDirectory;
    }

    public void setTomcatDirectory(final String tomcatDirectory) {
        this.tomcatDirectory = tomcatDirectory;
    }

    public String getProjectRootDirectory() {
        return projectRootDirectory;
    }

    public void setProjectRootDirectory(final String projectRootDirectory) {
        this.projectRootDirectory = projectRootDirectory;
    }

    public void setTomcatRootDirectory(final String tomcatRootDirectory) {
        this.tomcatRootDirectory = tomcatRootDirectory;
    }

    public String getTomcatRootDirectory() {
        return tomcatRootDirectory;
    }

    public String getDistFile() {
        return distFile;
    }

    public void setDistFile(final String distFile) {
        this.distFile = distFile;
    }

    private void checkNullSave(final Element element, final String attr, final String value) {
        if (value == null) {
            return;
        }
        element.setAttribute(attr, value);
    }
}