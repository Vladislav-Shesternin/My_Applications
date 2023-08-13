package com.guess.pair

import android.app.Application
import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.guess.pair.util.log

lateinit var appContext: Context

class App: Application() {

    companion object {
        private const val AF_DEV_KEY = "tjfcCuuV4EQdwnjPfhdcwD"
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        AppsFlyerLib.getInstance().apply {
            init("", null, this@App)
            start(this@App, AF_DEV_KEY, object : AppsFlyerRequestListener {
                override fun onSuccess() {
                    log("Launch sent successfully")
                }
                override fun onError(errorCode: Int, errorDesc: String) {
                     log("""Launch failed to be sent:
                            Error code: $errorCode
                            Error description: $errorDesc""")
                }
            })
        }
    }

}
