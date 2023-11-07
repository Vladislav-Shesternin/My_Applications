package fludilia.mamkinarow.lotrends.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object Lotoj: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    object Sucha: DataStoreElement<String>() {
        override val key = stringPreferencesKey("key")
    }

    object Zironka: DataStoreElement<String>() {
        override val key = stringPreferencesKey("link")
    }

}

