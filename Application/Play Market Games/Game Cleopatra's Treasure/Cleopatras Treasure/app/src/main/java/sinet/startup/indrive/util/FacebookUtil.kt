package sinet.startup.indrive.util

import com.facebook.applinks.AppLinkData
import io.michaelrocks.paranoid.Obfuscate
import sinet.startup.indrive.appContext

@Obfuscate
object FacebookUtil {

    fun initialize() {
        log("Facebook initialize")
        AppLinkData.fetchDeferredAppLinkData(appContext) { appLinkData: AppLinkData? ->
            if (appLinkData != null) {
                Global.deeplink = appLinkData.targetUri?.authority.toString()
                log("Facebook deeplink: ${Global.deeplink}")
            }
        }
    }

}