


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import org.jetbrains.annotations.Nullable;


public class BaseConfig {


    protected StorageState state;


    @Nullable
    public StorageState getState() {
        return state;
    }


    public void loadState(final StorageState storageState) {
        state = storageState;
    }
}