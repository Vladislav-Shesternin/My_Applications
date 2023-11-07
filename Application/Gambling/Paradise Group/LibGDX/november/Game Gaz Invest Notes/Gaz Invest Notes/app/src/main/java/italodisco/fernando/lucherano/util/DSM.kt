package italodisco.fernando.lucherano.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DSM: ADS() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "jepan")

    object kliJ: DataStoreElement<String>() {
        override val key = stringPreferencesKey("kL")
    }

    object loDa: DataStoreElement<String>() {
        override val key = stringPreferencesKey("lL")
    }

}

