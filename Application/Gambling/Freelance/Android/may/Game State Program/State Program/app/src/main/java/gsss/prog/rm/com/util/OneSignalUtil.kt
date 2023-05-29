package gsss.prog.rm.com.util

import com.onesignal.OneSignal
import gsss.prog.rm.com.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "fc364864-44af-499e-8e25-4454734d95cc"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}