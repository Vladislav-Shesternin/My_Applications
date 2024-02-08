package aer.com.gamesas.mobile.slot

import android.app.Application
import android.content.Context
import aer.com.gamesas.mobile.slot.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
