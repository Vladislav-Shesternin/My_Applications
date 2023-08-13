package com.forvovim.smartconverter.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.forvovim.smartconverter.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Kamela_kabella")



    object Piston: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("piston")
    }

}

