package qbl.bisriymyach.QuickBall.fastergan

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import qbl.bisriymyach.QuickBall.hotvils.kalimatronika

object imporer : IdiNaherOchkolupGuglovski() {
    override val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore")

    object kava : Sasa<Long>() {
        override val key = longPreferencesKey("balance")
    }
    object Date : Sasa<String>() {
        override val key = stringPreferencesKey("date")
    }

}

abstract class IdiNaherOchkolupGuglovski {
    abstract val Context.dataStore: DataStore<Preferences>

    abstract inner class Sasa<T> {
        abstract val key: Preferences.Key<T>

        open suspend fun ubed(block: suspend (T?) -> T) {
            kalimatronika.dataStore.edit { it[key] = block(it[key]) }
        }

        open suspend fun get(): T? {
            return kalimatronika.dataStore.data.first()[key]
        }
    }
}