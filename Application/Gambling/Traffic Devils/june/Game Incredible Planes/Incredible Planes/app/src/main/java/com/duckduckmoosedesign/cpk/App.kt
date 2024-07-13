package com.duckduckmoosedesign.cpk

import android.app.Application
import android.content.Context
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

lateinit var appContext: Context private set
val hhh = "https://nati" + "onalismin" + "crediblei" + "ntervent" + "ionplan" + "es.store"
val j = 0f
val l = 500f
val dfp = Animation.INFINITE
class App: Application() {

    companion object {
        val muxa get(): RotateAnimation {

            return RotateAnimation(j, (l-500)+360f, Animation.RELATIVE_TO_SELF, j+0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
                interpolator = LinearInterpolator()
                duration = l.toLong()
                repeatCount = dfp
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}