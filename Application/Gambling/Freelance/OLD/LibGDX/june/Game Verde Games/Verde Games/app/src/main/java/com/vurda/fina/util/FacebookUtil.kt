package com.vurda.fina.util

import com.facebook.applinks.AppLinkData
import com.vurda.fina.appContext
import kotlinx.coroutines.flow.MutableStateFlow

object FacebookUtil {

    const val DEEPLINK_INIT_VALUE = "none"
    val deeplinkFlow = MutableStateFlow<String?>(DEEPLINK_INIT_VALUE)

    fun initialize() {
        log("Facebook initialize")
        AppLinkData.fetchDeferredAppLinkData(appContext) { appLinkData: AppLinkData? ->
            deeplinkFlow.value = appLinkData?.targetUri?.authority
            log("Facebook deeplink: ${deeplinkFlow.value}")
        }
    }

}