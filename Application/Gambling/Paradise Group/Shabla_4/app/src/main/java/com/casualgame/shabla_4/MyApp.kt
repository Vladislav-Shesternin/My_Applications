package com.casualgame.shabla_4

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(this)
    }
}
