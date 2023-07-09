package com.golovkoe.cryptosafe.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import kotlinx.coroutines.flow.MutableStateFlow

object AppsflyerUtil {

    private const val APPSFLYER_DEV_KEY = "iEuZDWsWftJL4fsjAq6UPF"

    private val onceConversionDataSuccess = Once()
    private val onceConversionDataFail    = Once()
    private val onceAttributionFailure    = Once()

    val checkFlow      = MutableStateFlow<Boolean>(false)
    val dataMap        = mutableMapOf<String, String?>()
    val campaignSubMap = mutableMapOf<String, String?>()



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

            checkFlow.value = true
        }
    }

    private val conversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
            onceConversionDataSuccess.once {
                log("AppsFlayer onConversionDataSuccess\nconversionData = $conversionData")

                fun getData(name: String): String? = try {
                    if (conversionData.containsKey(name)) conversionData[name]?.toString() else null
                } catch (e: Exception) { null }

                dataMap["orig_cost"]      = getData("orig_cost")
                dataMap["media_source"]   = getData("media_source")
                dataMap["cost_cents_USD"] = getData("cost_cents_USD")

                log("Appsflyer dataMap: $dataMap")

                getData("campaign")
                    ?.split('_')
                    ?.onEachIndexed { index, sub -> campaignSubMap["sub${index.inc()}"] = sub }

                if (campaignSubMap.isEmpty()) repeat(6) { index -> campaignSubMap["sub${index.inc()}"] = null }

                log("Appsflyer campaignSubMap: $campaignSubMap")

                checkFlow.value = true
            }
        }

        override fun onConversionDataFail(errorMessage: String) {
            log("AppsFlayer error getting conversion data: $errorMessage")
            onceConversionDataFail.once { checkFlow.value = true }
        }

        override fun onAppOpenAttribution(attributionData: Map<String, String>) {
            log("AppsFlayer onAppOpenAttribution: This is fake call.")
        }

        override fun onAttributionFailure(errorMessage: String) {
            log("AppsFlayer error onAttributionFailure : $errorMessage")
            onceAttributionFailure.once { checkFlow.value = true }
        }
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