package aiebu.kakono.tutokazalos

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appContext: Context private set

private const val ONESIGNAL_APP_ID = "bf390487-3708-4f9f-8809-5c875bf4da72"

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // OneSignal

        appContext = applicationContext

        OneSignal.Debug.logLevel = LogLevel.VERBOSE
                    OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

            CoroutineScope(Dispatchers.IO).launch {
                        OneSignal.Notifications.requestPermission(true)
            }
    }

}