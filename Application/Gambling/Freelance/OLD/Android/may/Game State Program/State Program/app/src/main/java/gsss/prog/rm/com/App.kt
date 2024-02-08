package gsss.prog.rm.com

import android.app.Application
import android.content.Context
import gsss.prog.rm.com.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
