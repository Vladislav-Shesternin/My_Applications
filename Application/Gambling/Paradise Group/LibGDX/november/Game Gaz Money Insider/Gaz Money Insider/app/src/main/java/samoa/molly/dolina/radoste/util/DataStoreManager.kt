package samoa.molly.dolina.radoste.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import samoa.molly.dolina.radoste.util.AbstractDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")



    object Kue: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("user_idi")
    }

    object Linter: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("user_nic")
    }

    object IsDialog: DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("dabalog")
    }

    object PariVacy: DataStoreElement<String>() {
        override val key = stringPreferencesKey("paradise")
    }

}

