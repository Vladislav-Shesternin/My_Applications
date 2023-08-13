package com.nicoledeonnit.cryptosignals.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nicoledeonnit.cryptosignals.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "morda")



    object Fantom: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("pisok")
    }

}

