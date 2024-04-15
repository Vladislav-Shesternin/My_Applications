package qbl.bisriymyach.QuickBall.hotvils

import android.app.Application
import android.content.Context

lateinit var kalimatronika: Context private set

class Baba : Application() {

    override fun onCreate() {
        super.onCreate()
        kalimatronika = applicationContext
    }

}