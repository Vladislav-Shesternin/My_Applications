package sinet.startup.indrive.util

import com.onesignal.OneSignal
import io.michaelrocks.paranoid.Obfuscate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sinet.startup.indrive.appContext
import sinet.startup.indrive.util.manager.DataStoreManager

@Obfuscate
object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "524d47a1-3170-4b58-a37a-4ac1523469c5"

    suspend fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        DataStoreManager.UUID.get()!!.also { uuid ->
            log("OneSignal uuid = $uuid")
            OneSignal.setExternalUserId(uuid) }
    }

}