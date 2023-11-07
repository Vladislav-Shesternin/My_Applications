package novibet.leoforos.irakloiu.office.helper.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    object KeyStore: DataStoreElement<String>() {
        override val key = stringPreferencesKey("key")
    }

    object LinkStore: DataStoreElement<String>() {
        override val key = stringPreferencesKey("link")
    }

}

