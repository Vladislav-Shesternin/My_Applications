package com.metanit.metrixmanager.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.metanit.metrixmanager.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "voxel")



    object Megan: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("fox")
    }

}

