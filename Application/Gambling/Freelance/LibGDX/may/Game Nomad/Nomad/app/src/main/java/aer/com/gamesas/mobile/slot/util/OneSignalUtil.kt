package aer.com.gamesas.mobile.slot.util

import com.onesignal.OneSignal
import aer.com.gamesas.mobile.slot.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "9f2c6db5-4625-4934-a784-0767d30a71ea"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}