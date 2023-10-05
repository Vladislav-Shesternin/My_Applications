package mange.yourse.finnnance

import android.app.Application
import android.content.Context

lateinit var appContext: Context private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        mange.yourse.finnnance.appContext = applicationContext
    }

}