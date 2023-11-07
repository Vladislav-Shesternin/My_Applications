package com.bettilt.mobile.pt.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    object Key: DataStoreElement<String>() {
        override val key = stringPreferencesKey("KEY")
    }

    object Link: DataStoreElement<String>() {
        override val key = stringPreferencesKey("LINK")
    }

}

