package com.prochenkoa.businessplanner.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.prochenkoa.businessplanner.util.AbstractDataStore

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Daoweu")



    object Matis: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("deoweo")
    }

}

