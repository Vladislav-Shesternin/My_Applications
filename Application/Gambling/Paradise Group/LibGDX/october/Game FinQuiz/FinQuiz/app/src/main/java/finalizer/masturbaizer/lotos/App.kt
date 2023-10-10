package finalizer.masturbaizer.lotos

import android.app.Application
import android.content.Context

lateinit var appContext: Context private set

//private const val ONESIGNAL_APP_ID = "85090023-fce4-4a43-a817-da93e012b983"

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

//        // OneSignal
//        OneSignal.Debug.logLevel = LogLevel.VERBOSE
//        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            OneSignal.Notifications.requestPermission(true)
//        }
    }

}