


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import javax.swing.JComponent;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(name = "HippoSharedApplicationConfig", storages = {@Storage(
        file = StoragePathMacros.APP_CONFIG + "/HippoSharedApplicationConfig.xml",
        scheme = StorageScheme.DIRECTORY_BASED,
        id = "HippoSharedApplicationConfig"
)

})
public class HippoSharedApplicationConfig implements ApplicationComponent, Configurable, PersistentStateComponent<StorageState> {

    private PluginConfiguration configPane;
    private StorageState state;


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


    @Nls
    @Override
    public String getDisplayName() {
        return "Hippo shared libs (Global)";
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
        return configPane != null && configPane.isModified(getState());
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configPane != null) {
            configPane.storeDataTo(getState());
        }
    }

    @Override
    public void reset() {
        if (configPane != null) {
            configPane.readDataFrom(getState());
        }
    }

    @Override
    public void disposeUIResources() {
        configPane = null;
    }


    @NotNull
    @Override
    public StorageState getState() {
        if (state == null) {
            state = new StorageState();
        }
        return state;
    }

    @Override
    public void loadState(final StorageState storageState) {
        state = storageState;
    }
}