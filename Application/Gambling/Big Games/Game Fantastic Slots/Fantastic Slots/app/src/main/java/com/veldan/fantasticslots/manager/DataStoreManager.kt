package com.veldan.fantasticslots.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.fantasticslots.activityContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.take

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    private val BALANCE_KEY  = longPreferencesKey("balance_key")
    private val TRAINING_KEY = booleanPreferencesKey("training_key")


    suspend fun collectBalance(block: suspend (Long) -> Unit) {
        activityContext.dataStore.data.collect { block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun updateBalance(block: suspend (Long) -> Long) {
        activityContext.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun getBalance() = CompletableDeferred<Long>().also { continuation ->
        activityContext.dataStore.data.take(1).collect { continuation.complete(it[BALANCE_KEY] ?: 0L) }
    }.await()



    suspend fun updateTraining(block: suspend (Boolean) -> Boolean) {
        activityContext.dataStore.edit { it[TRAINING_KEY] = block(it[TRAINING_KEY] ?: true) }
    }

    suspend fun getTraining() = CompletableDeferred<Boolean>().also { continuation ->
        activityContext.dataStore.data.take(1).collect { continuation.complete(it[TRAINING_KEY] ?: true) }
    }.await()


}

