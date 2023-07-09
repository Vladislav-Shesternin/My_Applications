package com.supertest.card.util

import com.onesignal.OneSignal
import com.supertest.card.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "25db323e-9d6d-43e2-bb93-ba6f77e9edc9"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}