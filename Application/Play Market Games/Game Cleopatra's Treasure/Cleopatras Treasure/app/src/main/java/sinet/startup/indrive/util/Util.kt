package sinet.startup.indrive.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import io.michaelrocks.paranoid.Obfuscate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import sinet.startup.indrive.BuildConfig
import sinet.startup.indrive.appContext
import sinet.startup.indrive.util.manager.DataStoreManager
import java.security.MessageDigest
import java.util.*
import kotlin.collections.LinkedHashMap

val Number.length: Int get() = toString().length

val Float.toMS: Long get() = (this * 1000).toLong()



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

fun Activity.getKeyHash() {
    try {
        val info = packageManager.getPackageInfo(
            BuildConfig.APPLICATION_ID,
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            log("KeyHash: ${Base64.getEncoder().encodeToString(md.digest())}")
        }
    } catch (e: Exception) {
        log("error - $e")
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

@Obfuscate
object Global {
    var uuid         : String? = null
    var advertisingId: String? = null
    var campaign_id  : String? = null
    var deeplink     : String? = null
    var isDeepLink   : Boolean = false
    val params       : LinkedHashMap<String, String> = linkedMapOf()

    var finalUrl     : MutableSharedFlow<String?> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    var responseCode : MutableSharedFlow<String> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

}