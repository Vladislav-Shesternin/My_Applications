package com.tmesfo.frtunes.util

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.tmesfo.frtunes.appContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlin.random.Random

val CharSequence.int: Int get() = toString().toInt()

val Float.toDelay: Long get() = (this * 1000).toLong()


val Number.length: Int get() = toString().length

val Float.toMS: Long get() = (this * 1000).toLong()

fun Long.transformToBalanceFormat(): String {
    val balance = toString().toMutableList()

    when {
        length == 4  -> balance.add(1, ' ')
        length == 5  -> balance.add(2, ' ')
        length == 6  -> balance.add(3, ' ')
        length == 7  -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
        }
        length == 8  -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
        }
        length == 9  -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
        }
        length == 10 -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
            balance.add(9, ' ')
        }
        length == 11 -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
            balance.add(10, ' ')
        }
        length == 12 -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
            balance.add(11, ' ')
        }
        else         -> toString()
    }

    return balance.joinToString("")
}

fun log(message: String) {
    Log.i("VLAD", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope) {
    coroutine.forEach { it.cancel() }
}

fun probability(percent: Int, block: () -> Unit = {}): Boolean {
    val randomNum = (0..100).shuffled().first()
    return if (randomNum <= percent) {
        block()
        true
    } else false
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