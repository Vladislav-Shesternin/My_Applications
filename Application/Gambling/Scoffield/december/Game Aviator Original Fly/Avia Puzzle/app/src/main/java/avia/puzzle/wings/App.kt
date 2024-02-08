package avia.puzzle.wings

import android.app.Application
import android.content.Context
import avia.puzzle.wings.util.log
import com.google.android.gms.ads.MobileAds

lateinit var appContext: Context private set

var adAppOpen: AdAppOpen? = null
    private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        MobileAds.initialize(this) {
            log("Init AdMob")
            adAppOpen = AdAppOpen().apply { loadAd(appContext) }
        }
    }

}