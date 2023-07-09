package com.shapovalovd.financecomitet.util

import com.onesignal.OneSignal
import com.shapovalovd.financecomitet.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "ca77e8ff-df97-447f-8340-1ff98d3f1cc5"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}