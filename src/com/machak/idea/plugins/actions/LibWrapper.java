/*
 * Copyright 2014 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.actions;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @version "$Id$"
 */
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LibWrapper{");
        sb.append("artifactName='").append(artifactName).append('\'');
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", ourName='").append(ourName).append('\'');
        sb.append(", jarFile=").append(jarFile);
        sb.append(", module=").append(module);
        sb.append('}');
        return sb.toString();
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
}
