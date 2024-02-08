//package com.prog.zka.mac.game.manager
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.doublePreferencesKey
//import androidx.datastore.preferences.core.longPreferencesKey
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import com.prog.zka.mac.util.AbstractDataStore
//
//object GameDataStoreManager: AbstractDataStore() {
//    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")
//
//
//    object Balance: DataStoreElement<Long>() {
//        override val key = longPreferencesKey("balance")
//    }
//
//}
//
