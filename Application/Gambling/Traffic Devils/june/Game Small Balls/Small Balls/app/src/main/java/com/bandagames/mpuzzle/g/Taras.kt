package com.bandagames.mpuzzle.g

import android.content.SharedPreferences
import android.webkit.WebView


class Taras {
    private val ligvo = Ligvo()
    lateinit var tsys6: SharedPreferences

    fun useLigvo() {
        ligvo.field1 = 10
        ligvo.field2 = "Hello"
        ligvo.field3 = true
        ligvo.field4 = 100.0
        ligvo.field5 = listOf(1, 2, 3)

        ligvo.method1()
        ligvo.method2()
        ligvo.method3()
        ligvo.method4()
        ligvo.method5()
        ligvo.method6()
        ligvo.method7()
        ligvo.method8()
        ligvo.method9()
        ligvo.method10()
    }

    var nagluta = mutableListOf<WebView>()
    var reo        = ""

}