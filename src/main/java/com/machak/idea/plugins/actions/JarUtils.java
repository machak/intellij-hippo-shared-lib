/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.actions;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.types.FileSet;

import com.intellij.openapi.diagnostic.Logger;

public final class JarUtils {

    private static final Logger log = Logger.getInstance(JarUtils.class);
    private JarUtils() {
    }

    public static void makeJar(final File source, final File destination) {

        try {
            final Jar jar = new Jar();
            jar.setCompress(true);
            final Project project = new Project();
            project.setBaseDir(source);
            jar.setProject(project);
            final FileSet fileSet = new FileSet();
            fileSet.setDir(source);
            jar.addFileset(fileSet);
            jar.setDestFile(destination);
            jar.execute();
        } catch (BuildException e) {
            log.error("Error building content JAR" + e);
        }

    }
}
