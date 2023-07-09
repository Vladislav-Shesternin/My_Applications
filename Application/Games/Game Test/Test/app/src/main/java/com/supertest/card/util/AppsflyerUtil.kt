package com.supertest.card.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import kotlinx.coroutines.flow.MutableStateFlow

object AppsflyerUtil {

    private const val APPSFLYER_DEV_KEY = "BTjHbdrSm3EyqBdVXNxxgn"

    private val onceConversionDataSuccess = Once()
    private val onceConversionDataFail    = Once()
    private val onceAttributionFailure    = Once()

    val conversionFlow = MutableStateFlow<String?>(null)



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
                log("AppsFlayer onConversionDataSuccess\nconversionData = $conversionData")

                conversionFlow.value = conversionData.toString()
            }
        }

        override fun onConversionDataFail(errorMessage: String) {
            log("AppsFlayer error getting conversion data: $errorMessage")
            onceConversionDataFail.once { conversionFlow.value = errorMessage }
        }

        override fun onAppOpenAttribution(attributionData: Map<String, String>) {
            log("AppsFlayer onAppOpenAttribution: This is fake call.")
        }

        override fun onAttributionFailure(errorMessage: String) {
            log("AppsFlayer error onAttributionFailure : $errorMessage")
            onceAttributionFailure.once { conversionFlow.value = errorMessage }
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