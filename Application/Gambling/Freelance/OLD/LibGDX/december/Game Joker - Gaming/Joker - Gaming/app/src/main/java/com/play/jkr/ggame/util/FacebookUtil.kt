package com.play.jkr.ggame.util

import com.facebook.applinks.AppLinkData
import com.play.jkr.ggame.appContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

object FacebookUtil {

    var deeplink: String? = null
        private set

    fun initialize() {
        log("Facebook initialize")
        AppLinkData.fetchDeferredAppLinkData(appContext) { appLinkData: AppLinkData? ->
            deeplink = appLinkData?.targetUri?.authority
            log("Facebook deeplink: $deeplink")
        }
    }

}