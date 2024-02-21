package com.bydeluxe.d3.android.program.sta.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore_key_link")



    object Key: DataStoreElement<String>() {
        override val key = stringPreferencesKey("Key")
    }

    object Link: DataStoreElement<String>() {
        override val key = stringPreferencesKey("Link")
    }

}

