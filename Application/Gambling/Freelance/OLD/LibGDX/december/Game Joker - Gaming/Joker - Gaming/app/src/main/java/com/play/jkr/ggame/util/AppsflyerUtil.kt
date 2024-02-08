package com.play.jkr.ggame.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.*



object AppsflyerUtil {

    var UID: String? = null
        private set

    val campaignFlow = MutableSharedFlow<String?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val onceConversionDataSuccess = Once()

    private const val APPSFLYER_DEV_KEY = "c5VuHQkc4ZizP2qYfh5XwJ"

    private val startAppsflyerRequestListener = object : AppsFlyerRequestListener {
        override fun onSuccess() {
            log("AppsFlayer Launch sent successfully")
        }

        override fun onError(errorCode: Int, errorDesc: String) {
            log("""
                    
                AppsFlayer:
                Launch failed to be sent:
                Error code: $errorCode
                Error description: $errorDesc
           """)
        }
    }

    private val conversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
            onceConversionDataSuccess.once {

                log("onConversionDataSuccess: $conversionData")

                val status: String = Objects.requireNonNull(conversionData["af_status"]).toString()
                if (status == "Organic") campaignFlow.tryEmit(null)
                else {
                    if (conversionData.containsKey("campaign")) campaignFlow.tryEmit(conversionData["campaign"].toString())
                    else campaignFlow.tryEmit(null)
                }

            }
        }

        override fun onConversionDataFail(errorMessage: String) {
            log("AppsFlayer error getting conversion data: $errorMessage")
        }

        override fun onAppOpenAttribution(attributionData: Map<String, String>) {
            log("AppsFlayer onAppOpenAttribution: This is fake call.")
        }

        override fun onAttributionFailure(errorMessage: String) {
            log("AppsFlayer error onAttributionFailure : $errorMessage")
        }
    }



    fun initialize(activity: Activity) {
        log("AppsFlayer initialize")
        with(AppsFlyerLib.getInstance()) {
            setDebugLog(true)
            init(APPSFLYER_DEV_KEY, conversionListener, activity)
            start(activity, APPSFLYER_DEV_KEY, startAppsflyerRequestListener)

            UID = getAppsFlyerUID(activity)
        }
    }

}