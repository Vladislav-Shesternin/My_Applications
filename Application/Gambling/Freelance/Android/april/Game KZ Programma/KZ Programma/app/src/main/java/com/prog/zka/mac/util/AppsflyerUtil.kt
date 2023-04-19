package com.prog.zka.mac.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import kotlinx.coroutines.flow.MutableStateFlow

object AppsflyerUtil {

    private const val APPSFLYER_DEV_KEY = "Zpe8cUqmR3bSVJEhtSQdmm"

    private val onceConversionDataSuccess = Once()
    private val onceConversionDataFail    = Once()
    private val onceAttributionFailure    = Once()

    const val CAMPAIGN_INIT_VALUE = "none"
    val campaignFlow = MutableStateFlow<String?>(CAMPAIGN_INIT_VALUE)



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

            doOnError()
        }
    }

    private val conversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
            onceConversionDataSuccess.once {
                log("AppsFlayer onConversionDataSuccess\nconversionData = $conversionData")

                fun getCampaign(name: String): String? = try {
                    if (conversionData.containsKey(name)) conversionData[name]?.toString() else null
                } catch (e: Exception) { null }

                campaignFlow.value = getCampaign("campaign") ?: getCampaign("c")
            }
        }

        override fun onConversionDataFail(errorMessage: String) {
            log("AppsFlayer error getting conversion data: $errorMessage")
            onceConversionDataFail.once { doOnError() }
        }

        override fun onAppOpenAttribution(attributionData: Map<String, String>) {
            log("AppsFlayer onAppOpenAttribution: This is fake call.")
        }

        override fun onAttributionFailure(errorMessage: String) {
            log("AppsFlayer error onAttributionFailure : $errorMessage")
            onceAttributionFailure.once { doOnError() }
        }
    }

    private fun doOnError() {
        campaignFlow.value = "c"
    }



    fun initialize(activity: Activity) {
        log("AppsFlayer initialize")
        with(AppsFlyerLib.getInstance()) {
            setDebugLog(true)
            init(APPSFLYER_DEV_KEY, conversionListener, activity)
            start(activity, APPSFLYER_DEV_KEY, startAppsflyerRequestListener)
        }
    }

}