package iconic.duo.hunt

import android.app.Application
import android.content.Context

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        iconic.duo.hunt.appContext = applicationContext
    }

}
