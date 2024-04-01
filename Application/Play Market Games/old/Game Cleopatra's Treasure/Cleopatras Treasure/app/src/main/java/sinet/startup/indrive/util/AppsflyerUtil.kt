package sinet.startup.indrive.util

import android.app.Activity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import io.michaelrocks.paranoid.Obfuscate
import sinet.startup.indrive.BuildConfig
import sinet.startup.indrive.MainActivity
import sinet.startup.indrive.util.network.RemoteConfig

@Obfuscate
object AppsflyerUtil {

    private const val APPSFLYER_DEV_KEY = "ZDLrBbHtFo7otqFBh8Cdud"

    private val onceConversionDataSuccess = Once()

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
                log(
                    """
                    
                    AppsFlayer onConversionDataSuccess
                    conversionData = $conversionData
                """
                )

                fun Array<String>.setGlobalParams() {
                    indices.onEach { Global.params["t${it.inc()}"] = get(it) }
                }

                if (conversionData.containsKey("campaign")) {
                    conversionData["campaign"].toString().split("_").toTypedArray().also { tParams -> tParams.setGlobalParams() }
                } else if (Global.deeplink.isNullOrEmpty().not()) {
                    Global.isDeepLink = true
                    Global.deeplink!!.split("_").toTypedArray().also { tParams -> tParams.setGlobalParams() }
                } else {
                    (1..5).onEach { t -> Global.params["t$t"] = "organic" }
                }

                if (conversionData.containsKey("campaign_id")) Global.campaign_id = conversionData["campaign_id"].toString()

                if (conversionData.containsKey("media_source")) Global.params["utm_source"] = conversionData["media_source"].toString()
                else Global.params["utm_source"] = "organic"

                log("AppsFlayer RemoteConfig.params : ${Global.params}")

                var parametersString = "?"
                (Global.params.keys).onEach { key -> parametersString += "$key=${Global.params[key]}&" }

                log("AppsFlayer parametersString : $parametersString")

                RemoteConfig.offerUrl += parametersString +
                        "package=${BuildConfig.APPLICATION_ID}&" +
                        "uuid=${Global.uuid}&" +
                        "campaign_id=${Global.campaign_id}&" +
                        "advertising_id=${Global.advertisingId}&" +
                        "deepLink=${Global.isDeepLink}"


                log("AppsFlayer offerUrl : ${RemoteConfig.offerUrl}")

                if (RemoteConfig.offerUrl?.contains("http") == true && parametersString.isNotEmpty()) Global.finalUrl.tryEmit(RemoteConfig.offerUrl!!)
                else Global.finalUrl.tryEmit(null)
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
            setCustomerUserId(Global.uuid)
            init(APPSFLYER_DEV_KEY, conversionListener, activity)
            start(activity, APPSFLYER_DEV_KEY, startAppsflyerRequestListener)
        }
    }

}