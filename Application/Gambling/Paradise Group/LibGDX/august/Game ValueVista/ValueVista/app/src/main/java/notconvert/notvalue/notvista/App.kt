package notconvert.notvalue.notvista

import android.app.Application
import android.content.Context

lateinit var appContext: Context private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        notconvert.notvalue.notvista.appContext = applicationContext
    }

}