package com.hk.stck.nord.util

import com.onesignal.OneSignal
import com.hk.stck.nord.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "2e95d6cf-3ec2-412f-908d-ec2c316349de"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}