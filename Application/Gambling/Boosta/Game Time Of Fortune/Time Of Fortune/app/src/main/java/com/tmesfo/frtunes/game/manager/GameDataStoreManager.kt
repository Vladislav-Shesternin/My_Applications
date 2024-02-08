package com.tmesfo.frtunes.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import com.tmesfo.frtunes.game.game

object GameDataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")

    private val BALANCE_KEY = longPreferencesKey("balance_key")



    object Balance: DataStoreElement<Long> {
        override suspend fun collect(block: suspend (Long?) -> Unit) {
            game.activity.dataStore.data.collect { block(it[BALANCE_KEY]) }
        }

        override suspend fun update(block: suspend (Long?) -> Long) {
            game.activity.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY]) }
        }

        override suspend fun get(): Long {
            return game.activity.dataStore.data.first()[BALANCE_KEY] ?: 0L
        }



        suspend fun collectNotNull(block: suspend (Long) -> Unit) {
            collect { if (it == null) block(10_000L) else block(it) }
        }

        suspend fun updateNotNull(block: suspend (Long) -> Long) {
            update { if (it == null) block(10_000L) else block(it) }
        }
    }



    interface DataStoreElement<T>{
        suspend fun collect(block: suspend (T?) -> Unit)

        suspend fun update(block: suspend (T?) -> T)

        suspend fun get(): T?
    }

}

