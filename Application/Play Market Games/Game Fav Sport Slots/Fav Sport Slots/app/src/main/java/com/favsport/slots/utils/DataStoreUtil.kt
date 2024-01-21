package com.favsport.slots.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.favsport.slots.activityContext

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

