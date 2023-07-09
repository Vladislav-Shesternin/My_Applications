package crapto.quantem.ao

import android.app.Application
import android.content.Context
import crapto.quantem.ao.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        crapto.quantem.ao.appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
