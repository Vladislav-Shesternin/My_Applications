package sinet.startup.indrive.util.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.michaelrocks.paranoid.Obfuscate
import sinet.startup.indrive.util.AbstractDataStore

@Obfuscate
object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")



    object Key: DataStoreElement<String>() {
        override val key = stringPreferencesKey("key")
    }

    object Link: DataStoreElement<String>() {
        override val key = stringPreferencesKey("link")
    }

    object UUID: DataStoreElement<String>() {
        override val key = stringPreferencesKey("uuid")
    }

}

