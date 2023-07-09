package crapto.makasinik.cryptoinsightspro.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import crapto.makasinik.cryptoinsightspro.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")



    object FlagPrivata: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("privacy_terms_key")
    }

}

