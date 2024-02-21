package com.god.sof.olym.pus.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.god.sof.olym.pus.appContext
import kotlinx.coroutines.flow.first

object DataStoreManager: AbstractDataStore() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA")



    object Key: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("Fuflo")
    }

    object Link: AbstractDataStore.DataStoreElement<String>() {
        override val key = stringPreferencesKey("Buflo")
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

