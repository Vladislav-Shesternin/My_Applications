package com.bigag.farm.garden.village.offline.farming.game

import android.app.Application
import android.content.Context

lateinit var appContext: Context private set

val rebro = "https://presidencyplayfulmultimediablast.life"

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}