/*
 * Copyright 2014 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.actions;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;


public class LibWrapper {

    final String artifactName;
    final String groupName;
    final String ourName;
    final VirtualFile jarFile;
    final Module module;

    public LibWrapper(final String artifactName, final String groupName, final String ourName, final VirtualFile jarFile, final Module module) {
        this.artifactName = artifactName;
        this.groupName = groupName;
        this.ourName = ourName;
        this.jarFile = jarFile;
        this.module = module;
    }



    public String getArtifactName() {
        return artifactName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getOurName() {
        return ourName;
    }

    public VirtualFile getJarFile() {
        return jarFile;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String toString() {
        final String sb = "LibWrapper{" + "artifactName='" + artifactName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", ourName='" + ourName + '\'' +
                ", jarFile=" + jarFile +
                ", module=" + module +
                '}';
        return sb;
    }
}
