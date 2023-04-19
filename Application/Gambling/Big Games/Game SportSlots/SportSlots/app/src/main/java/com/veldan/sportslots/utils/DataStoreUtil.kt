package com.veldan.sportslots.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.sportslots.activityContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlin.coroutines.suspendCoroutine

object DataStoreUtil {
    private val Context.dataStorePrice: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE_PRICE")

    private val PRICE_KEY = intPreferencesKey("PRICE")



    suspend fun collectPrice(block: (Int) -> Unit) {
        activityContext.dataStorePrice.data.collect { block(it[PRICE_KEY] ?: 10000) }
    }

    suspend fun updatePrice(priceBlock: (Int) -> Int) {
        activityContext.dataStorePrice.edit { it[PRICE_KEY] = priceBlock(it[PRICE_KEY] ?: 10000) }
    }

}

