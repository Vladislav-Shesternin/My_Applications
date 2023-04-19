package com.finan.cial.quizz.util

import com.onesignal.OneSignal
import com.finan.cial.quizz.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "09623cc3-1e80-4bef-aec3-51df862ae927"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}