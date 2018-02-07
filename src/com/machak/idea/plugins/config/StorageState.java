/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;

import org.jetbrains.annotations.Nullable;


@State(
        name = "HippoSharedProjectConfig",
        storages = {
                @Storage(value = "HippoSharedProjectConfig.xml"),
                @Storage(value = "HippoSharedProjectConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class StorageState implements PersistentStateComponent<StorageState> {
    //@Attribute("deleteAllJars")
    public boolean copyContentJar;
    public boolean autoModifyProjectOutput;
    public boolean deleteAllJars;
    //@Attribute("copyOtherJars")
    public boolean copyOtherJars;
    //@Attribute("copyLog4J")
    public boolean copyLog4J;
    //@Attribute("showDialog")
    public boolean showDialog;
    //@Attribute("createProjectFile")
    public boolean createProjectFile;

    //@Tag("log4JDirectory")
    public String log4JDirectory;
    //@Tag("tomcatDirectory")
    public String tomcatDirectory;
    //@Tag("tomcatRootDirectory")
    public String tomcatRootDirectory;
    //@Tag("projectRootDirectory")
    public String projectRootDirectory;
    //@Tag("distFile")
    public String distFile;


    public boolean isDeleteAllJars() {
        return deleteAllJars;
    }

    public void setDeleteAllJars(final boolean deleteAllJars) {
        this.deleteAllJars = deleteAllJars;
    }

    public boolean isCopyOtherJars() {
        return copyOtherJars;
    }

    public void setCopyOtherJars(final boolean copyOtherJars) {
        this.copyOtherJars = copyOtherJars;
    }

    public boolean isCopyLog4J() {
        return copyLog4J;
    }

    public void setCopyLog4J(final boolean copyLog4J) {
        this.copyLog4J = copyLog4J;
    }

    public String getLog4JDirectory() {
        return log4JDirectory;
    }

    public void setLog4JDirectory(final String log4JDirectory) {
        this.log4JDirectory = log4JDirectory;
    }

    public String getTomcatDirectory() {
        return tomcatDirectory;
    }

    public void setTomcatDirectory(final String tomcatDirectory) {
        this.tomcatDirectory = tomcatDirectory;
    }

    public String getTomcatRootDirectory() {
        return tomcatRootDirectory;
    }

    public void setTomcatRootDirectory(final String tomcatRootDirectory) {
        this.tomcatRootDirectory = tomcatRootDirectory;
    }

    public String getProjectRootDirectory() {
        return projectRootDirectory;
    }

    public void setProjectRootDirectory(final String projectRootDirectory) {
        this.projectRootDirectory = projectRootDirectory;
    }

    public String getDistFile() {
        return distFile;
    }

    public void setDistFile(final String distFile) {
        this.distFile = distFile;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(final boolean showDialog) {
        this.showDialog = showDialog;
    }

    public boolean isCreateProjectFile() {
        return createProjectFile;
    }

    public void setCreateProjectFile(final boolean createProjectFile) {
        this.createProjectFile = createProjectFile;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final StorageState that = (StorageState) o;

        if (copyContentJar != that.copyContentJar) {
            return false;
        }
        if (autoModifyProjectOutput != that.autoModifyProjectOutput) {
            return false;
        }
        if (deleteAllJars != that.deleteAllJars) {
            return false;
        }
        if (copyOtherJars != that.copyOtherJars) {
            return false;
        }
        if (copyLog4J != that.copyLog4J) {
            return false;
        }
        if (showDialog != that.showDialog) {
            return false;
        }
        if (createProjectFile != that.createProjectFile) {
            return false;
        }
        if (log4JDirectory != null ? !log4JDirectory.equals(that.log4JDirectory) : that.log4JDirectory != null) {
            return false;
        }
        if (tomcatDirectory != null ? !tomcatDirectory.equals(that.tomcatDirectory) : that.tomcatDirectory != null) {
            return false;
        }
        if (tomcatRootDirectory != null ? !tomcatRootDirectory.equals(that.tomcatRootDirectory) : that.tomcatRootDirectory != null) {
            return false;
        }
        if (projectRootDirectory != null ? !projectRootDirectory.equals(that.projectRootDirectory) : that.projectRootDirectory != null) {
            return false;
        }
        return distFile != null ? distFile.equals(that.distFile) : that.distFile == null;
    }

    @Override
    public int hashCode() {
        int result = (copyContentJar ? 1 : 0);
        result = 31 * result + (autoModifyProjectOutput ? 1 : 0);
        result = 31 * result + (deleteAllJars ? 1 : 0);
        result = 31 * result + (copyOtherJars ? 1 : 0);
        result = 31 * result + (copyLog4J ? 1 : 0);
        result = 31 * result + (showDialog ? 1 : 0);
        result = 31 * result + (createProjectFile ? 1 : 0);
        result = 31 * result + (log4JDirectory != null ? log4JDirectory.hashCode() : 0);
        result = 31 * result + (tomcatDirectory != null ? tomcatDirectory.hashCode() : 0);
        result = 31 * result + (tomcatRootDirectory != null ? tomcatRootDirectory.hashCode() : 0);
        result = 31 * result + (projectRootDirectory != null ? projectRootDirectory.hashCode() : 0);
        result = 31 * result + (distFile != null ? distFile.hashCode() : 0);
        return result;
    }

    public boolean isCopyContentJar() {
        return copyContentJar;
    }

    public void setCopyContentJar(final boolean copyContentJar) {
        this.copyContentJar = copyContentJar;
    }

    public boolean isAutoModifyProjectOutput() {
        return autoModifyProjectOutput;
    }

    public void setAutoModifyProjectOutput(final boolean autoModifyProjectOutput) {
        this.autoModifyProjectOutput = autoModifyProjectOutput;
    }

    @Nullable
    @Override
    public StorageState getState() {
        return this;
    }

    @Override
    public void loadState(StorageState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Nullable
    public static StorageState getInstance() {
        return ServiceManager.getService(StorageState.class);
    }
    @Nullable
    public static StorageState getInstance(Project project) {
        return ServiceManager.getService(project, StorageState.class);
    }
}
