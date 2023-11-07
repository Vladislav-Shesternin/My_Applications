package uniwersal.pictures.present.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "franchaizer_DATA_STORE")

    object Key: DataStoreElement<String>() {
        override val key = stringPreferencesKey("keybro")
    }

    object Link: DataStoreElement<String>() {
        override val key = stringPreferencesKey("link_end_park")
    }

}

