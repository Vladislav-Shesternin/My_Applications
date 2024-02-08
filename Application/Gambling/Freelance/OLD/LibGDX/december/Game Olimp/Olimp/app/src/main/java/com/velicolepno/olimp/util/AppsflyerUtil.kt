package com.velicolepno.olimp.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

object AppsflyerUtil {

    private const val APPSFLYER_DEV_KEY = "fH7RTGrwiTWWPkBPFTgEkj"

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
            log(
                """
                    
                    AppsFlayer onConversionDataSuccess
                    conversionData = $conversionData
                """
            )
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
        }
    }

}