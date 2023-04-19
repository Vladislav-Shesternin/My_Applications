package com.veldan.junglego.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.junglego.activityContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.take

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    private val BALANCE_KEY = longPreferencesKey("balance_key")



    suspend fun collectBalance(block: suspend (Long) -> Unit) {
        activityContext.dataStore.data.collect { block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun updateBalance(block: suspend (Long) -> Long) {
        activityContext.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun getBalance() = CompletableDeferred<Long>().also { continuation ->
        activityContext.dataStore.data.take(1).collect { continuation.complete(it[BALANCE_KEY] ?: 0L) }
    }.await()

}

