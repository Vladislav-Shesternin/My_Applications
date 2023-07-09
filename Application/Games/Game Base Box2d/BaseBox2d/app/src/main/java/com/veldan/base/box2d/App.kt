package com.veldan.base.box2d

import android.app.Application
import android.content.Context

lateinit var appContext: Context private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        com.veldan.base.box2d.appContext = applicationContext
    }

}