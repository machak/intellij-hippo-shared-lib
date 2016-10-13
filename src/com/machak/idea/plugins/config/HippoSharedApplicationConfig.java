/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;


import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;

import org.jetbrains.annotations.NotNull;

@State(name = "HippoSharedApplicationSettings",
        storages = {@Storage(
                file = StoragePathMacros.APP_CONFIG + "/HippoSharedApplicationConfig.xml",
                scheme = StorageScheme.DIRECTORY_BASED,
                id = "dir"
        )

        })
public class HippoSharedApplicationConfig extends BaseConfig implements ApplicationComponent {


    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "HippoSharedApplicationConfig";
    }


}
