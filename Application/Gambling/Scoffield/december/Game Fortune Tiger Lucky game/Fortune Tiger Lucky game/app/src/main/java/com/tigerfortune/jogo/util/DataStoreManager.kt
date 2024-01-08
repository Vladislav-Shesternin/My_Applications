package com.tigerfortune.jogo.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Francesko")



    object Key: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("Lopata")
    }

    object Link: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("Kirka")
    }

}

