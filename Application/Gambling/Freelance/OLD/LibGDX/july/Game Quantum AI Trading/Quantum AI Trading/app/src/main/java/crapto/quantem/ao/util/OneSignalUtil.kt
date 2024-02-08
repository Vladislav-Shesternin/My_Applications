package crapto.quantem.ao.util

import com.onesignal.OneSignal

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "2dfc7454-5633-4556-a49c-02a109bacd6b"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(crapto.quantem.ao.appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}