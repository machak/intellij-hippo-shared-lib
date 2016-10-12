


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import javax.swing.JComponent;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.RoamingType;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.xmlb.XmlSerializerUtil;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(name = "HippoSharedApplicationConfig", storages = {@Storage(id = "dir", file = "HippoSharedApplicationConfig.xml", roamingType = RoamingType.DEFAULT, scheme = StorageScheme.DIRECTORY_BASED)})
public class HippoSharedApplicationConfig implements com.intellij.openapi.components.ApplicationComponent, Configurable, PersistentStateComponent<StorageState> {

    private StorageState storageState;

    private PluginConfiguration configPane;


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


    @NotNull
    @Override
    public StorageState getState() {
        if (storageState == null) {
            storageState = new StorageState();
        }
        return storageState;
    }

    @Override
    public void loadState(final StorageState storageState) {
        XmlSerializerUtil.copyBean(storageState, this.storageState);
    }
}