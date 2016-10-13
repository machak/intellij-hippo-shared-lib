/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;


import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

@State(
        name = "HippoSharedProjectConfig", storages = {
        @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
        @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/hippo_shared_libs.xml", scheme = StorageScheme.DIRECTORY_BASED)
})
public class HippoSharedProjectSettings extends BaseConfig implements ProjectComponent {


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

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "HippoSharedProjectSettings";
    }
}
