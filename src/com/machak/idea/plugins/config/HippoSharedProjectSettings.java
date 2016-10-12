/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;


import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

import org.jetbrains.annotations.Nls;

@State(name = "HippoSharedProjectSettings", storages = @Storage("HippoSharedProjectSettings.xml"))
public class HippoSharedProjectSettings extends HippoSharedApplicationConfig implements com.intellij.openapi.components.ProjectComponent {


    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Hippo shared libs (Project)";
    }
}
