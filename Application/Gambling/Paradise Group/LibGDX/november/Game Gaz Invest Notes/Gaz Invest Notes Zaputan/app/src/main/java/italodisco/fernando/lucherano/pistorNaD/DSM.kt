package italodisco.fernando.lucherano.pistorNaD

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import italodisco.fernando.lucherano.iopartew.sandes.pistro.ADS

object DSM: ADS() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "jepan")

    object kliJ: DataStoreElement<String>() {
        override val key = stringPreferencesKey("kL")
    }

    object loDa: DataStoreElement<String>() {
        override val key = stringPreferencesKey("lL")
    }

    object IsDialog: DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("isDialog")
    }

    object PariVacy: DataStoreElement<String>() {
        override val key = stringPreferencesKey("pavicy")
    }

}

