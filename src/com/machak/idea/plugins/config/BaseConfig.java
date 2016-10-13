


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import javax.swing.JComponent;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;


public class BaseConfig implements Configurable, PersistentStateComponent<StorageState> {

    private PluginConfiguration configPane;

    public StorageState state = new StorageState();


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
        return configPane != null && configPane.isModified(state);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configPane != null) {
            configPane.setData(state);
        }
    }

    @Override
    public void reset() {
        if (configPane != null) {
            configPane.getData(state);
        }
    }

    @Override
    public void disposeUIResources() {
        configPane = null;
    }


    @Nullable
    @Override
    public StorageState getState() {
        return state;
    }

    @Override
    public void loadState(final StorageState storageState) {
        state = storageState;
    }
}