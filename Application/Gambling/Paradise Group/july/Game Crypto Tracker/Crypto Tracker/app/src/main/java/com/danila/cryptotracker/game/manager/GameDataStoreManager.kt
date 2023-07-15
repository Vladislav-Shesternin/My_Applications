package com.danila.cryptotracker.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.danila.cryptotracker.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE_GAME")



    object Patron: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("patron")
    }

}

