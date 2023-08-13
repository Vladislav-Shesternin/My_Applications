package com.leto.advancedcalculator.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.leto.advancedcalculator.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Saven Pilots 21")



    object ChtoEto: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("chto")
    }

}

