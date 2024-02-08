package com.cosmo3v1.cosmoinc.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cosmo3v1.cosmoinc.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")



    object Balance: AbstractDataStore.DataStoreElement<Long>() {
        override val key = longPreferencesKey("balance_key")
    }

    object FileExtension: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("fileExtension_key")
    }

    object NickName: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("nickname_key")
    }

}

