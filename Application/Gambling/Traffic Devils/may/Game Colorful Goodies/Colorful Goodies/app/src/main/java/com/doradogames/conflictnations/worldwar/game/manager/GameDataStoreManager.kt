package com.doradogames.conflictnations.worldwar.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.doradogames.conflictnations.worldwar.appContext
import kotlinx.coroutines.flow.first

object GameDataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")


    object Record: AbstractDataStore.DataStoreElement<Int>() {
        override val key = intPreferencesKey("record")
    }
    object Level: AbstractDataStore.DataStoreElement<Int>() {
        override val key = intPreferencesKey("level")
    }
    object IsTutorials: AbstractDataStore.DataStoreElement<Boolean>() {
        override val key = booleanPreferencesKey("isTutorials")
    }


}

abstract class AbstractDataStore {
    abstract val Context.dataStore: DataStore<Preferences>



    abstract inner class DataStoreElement<T> {
        abstract val key: Preferences.Key<T>

        open suspend fun collect(block: suspend (T?) -> Unit) {
            appContext.dataStore.data.collect { block(it[key]) }
        }

        open suspend fun update(block: suspend (T?) -> T) {
            appContext.dataStore.edit { it[key] = block(it[key]) }
        }

        open suspend fun get(): T? {
            return appContext.dataStore.data.first()[key]
        }
    }
}

