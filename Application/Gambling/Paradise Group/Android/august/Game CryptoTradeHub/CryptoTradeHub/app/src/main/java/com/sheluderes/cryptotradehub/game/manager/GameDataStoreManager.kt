package com.sheluderes.cryptotradehub.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sheluderes.cryptotradehub.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "basiruetsa")


    object Olivec: DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("ruchka")
    }

}

