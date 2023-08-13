package com.logic.exchangewizard.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.logic.exchangewizard.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datka")


    object Qwest: DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("quin")
    }

}

