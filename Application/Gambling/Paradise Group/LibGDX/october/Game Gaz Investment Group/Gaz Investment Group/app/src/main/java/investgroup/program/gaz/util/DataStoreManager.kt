package investgroup.program.gaz.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "katana_STORE")

    object Klan: DataStoreElement<String>() {
        override val key = stringPreferencesKey("keyDone")
    }

    object Lida: DataStoreElement<String>() {
        override val key = stringPreferencesKey("liDERnk")
    }

    // isAccept
    object Agaga: DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("istPry")
    }

    // flyerKey
    object Badabum: DataStoreElement<String>() {
        override val key = stringPreferencesKey("rKuniKey")
    }

}

