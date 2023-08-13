package com.lakalutic.statisticsmanager.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lakalutic.statisticsmanager.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Mazeratte")



    object Lotos: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("lotos")
    }

}

