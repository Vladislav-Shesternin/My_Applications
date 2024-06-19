package com.doradogames.conflictnations.worldw.helpers

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import com.doradogames.conflictnations.worldw.MainActivity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.PI

class Wheel {
    private var diameter: Double = 0.0
    private var material: String = ""
    private var pressure: Double = 0.0
    private var temperature: Double = 0.0
    private var isPunctured: Boolean = false
    private var isAligned: Boolean = true
    private var isBalanced: Boolean = true
    private var treadDepth: Double = 0.0
    private var rotationSpeed: Double = 0.0
    private var wearLevel: Double = 0.0

    lateinit var prefs: SharedPreferences

    fun inflate(pressureToAdd: Double) {
        pressure += pressureToAdd
    }

    fun deflate(pressureToReduce: Double) {
        pressure -= pressureToReduce
    }

    fun heatUp(deltaTemperature: Double) {
        temperature += deltaTemperature
    }

    fun coolDown(deltaTemperature: Double) {
        temperature -= deltaTemperature
    }

    fun undeground(): RotateAnimation {
        return RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            interpolator = LinearInterpolator()
            duration = 500
            repeatCount = Animation.INFINITE
        }
    }

    fun puncture() {
        isPunctured = true
    }

    fun repairPuncture() {
        isPunctured = false
    }

    fun align() {
        material
        isAligned = true
    }

    fun misalign() {
        isAligned = false
    }

    fun balance() {
        isBalanced = true
    }

    suspend fun ardestetingIdeAshKa(activity: MainActivity) = suspendCoroutine { continuation ->
        balance()
        needsBalancing()
        getCircumference()
        val default = "00000000-0000-0000-0000-000000000000"
        misalign()
        getRadius()
        val uhuhu = "000${UUID.randomUUID()}"
        var asd = try {
            stopRotation()
            deflate(0.0)
            isOverInflated()
            AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
        } catch (e: Exception) {
            unbalance()
            coolDown(0.5)
            uhuhu
        }
        if (asd == default) asd = uhuhu
        continuation.resume(asd)
    }

    fun unbalance() {
        isBalanced = false
    }

    fun rotate(rotationSpeed: Double) {
        this.rotationSpeed = rotationSpeed
    }

    fun stopRotation() {
        rotationSpeed = 0.0
    }

    fun wear(amount: Double) {
        wearLevel += amount
    }

    val FADEDer = true

    fun replaceTire() {
        treadDepth = 100.0
        wearLevel = 0.0
    }

    fun getRadius(): Double {
        return diameter / 2
    }

    fun getCircumference(): Double {
        return PI * diameter
    }

    fun isUnderInflated(): Boolean {
        return pressure < 30.0
    }

    fun init(webView: WebView, carShop: CarShop, activity: MainActivity) {
        val initialValue = 10

        // Цепочка з 20 простих лямбд
        val result = listOf<(Int) -> Int>(
            { it + 1 },
            { it * 2 },
            { it - 3 },
            { it + 5 },
            { it / 2 },
            { it * 3 },
            { it - 1 },
            { it + 4 },
            { it * 2 },
            { it / 3 },
            { it + 2 },
            { it - 5 },
            { it * 4 },
            { it + 3 },
            { it / 2 },
            { it - 1 },
            { it + 6 },
            { it * 2 },
            { it / 4 },
            { it + 2 }
        ).fold(initialValue) { acc, func -> func(acc) }


        webView.apply {

            val numbers = (1..10).toList()

            // Цепочка з 20 лямбд
            val result = numbers
                .map { it * 2 }       // Лямбда 1: множимо кожне число на 2
                .map { it + 3 }       // Лямбда 2: додаємо 3 до кожного числа
                .apply { webChromeClient = activity.texPis() }
                .map { it / 2 }       // Лямбда 3: ділимо кожне число на 2
                .apply { webViewClient = activity.salamandrik() }
                .map { it - 1 }       // Лямбда 4: віднімаємо 1 від кожного числа
                .apply { isSaveEnabled = FADEDer }
                .map { it + 5 }       // Лямбда 5: додаємо 5 до кожного числа
                .apply { isFocusableInTouchMode = FADEDer }
                .map { it * 3 }       // Лямбда 6: множимо кожне число на 3
                .apply { setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } }
                .map { it - 2 }       // Лямбда 7: віднімаємо 2 від кожного числа
                .apply { CookieManager.getInstance().setAcceptCookie(FADEDer) }
                .map { it + 4 }       // Лямбда 8: додаємо 4 до кожного числа
                .apply { CookieManager.getInstance().setAcceptThirdPartyCookies(webView, FADEDer) }
                .map { it / 2 }       // Лямбда 9: ділимо кожне число на 2
                .apply { isFocusable = FADEDer }
                .map { it * 2 }       // Лямбда 10: множимо кожне число на 2
                .apply { layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
                .map { it + 1 }       // Лямбда 11: додаємо 1 до кожного числа
                .apply { setLayerType(View.LAYER_TYPE_HARDWARE, null) }
                .map { it - 3 }       // Лямбда 12: віднімаємо 3 від кожного числа
                .map { it + 2 }       // Лямбда 13: додаємо 2 до кожного числа
                .map { it * 4 }       // Лямбда 14: множимо кожне число на 4
                .map { it / 2 }       // Лямбда 15: ділимо кожне число на 2
                .map { it + 3 }       // Лямбда 16: додаємо 3 до кожного числа
                .map { it - 1 }       // Лямбда 17: віднімаємо 1 від кожного числа
                .map { it + 6 }       // Лямбда 18: додаємо 6 до кожного числа
                .map { it * 2 }       // Лямбда 19: множимо кожне число на 2
                .map { it / 4 }       // Лямбда 20: ділимо кожне число на 4
                .sum()                // Сума всіх чисел

            settings.apply {
                numbers
                    .map { it * 2 }                     // Лямбда 1: множимо кожне число на 2
                    .mapIndexed { index, value -> value + index } // Лямбда 2: додаємо індекс до кожного числа
                    .apply { userAgentString = userAgentString.replace("; wv", "") }
                    .filter { it % 3 == 0 }             // Лямбда 3: відбираємо лише числа, які діляться на 3
                    .apply { loadWithOverviewMode = FADEDer }
                    .map { it / 3 }                     // Лямбда 4: ділимо кожне число на 3
                    .apply { allowContentAccess = FADEDer }
                    .map { it + 5 }                     // Лямбда 5: додаємо 5 до кожного числа
                    .map { if (it % 2 == 0) it else it * 2 } // Лямбда 6: якщо число парне, залишаємо, інакше множимо на 2
                    .map { it - 1 }                     // Лямбда 7: віднімаємо 1 від кожного числа
                    .map { it + 4 }                     // Лямбда 8: додаємо 4 до кожного числа
                    .apply { loadsImagesAutomatically = FADEDer }
                    .map { if (it > 10) it / 2 else it } // Лямбда 9: якщо число більше 10, ділимо на 2, інакше залишаємо
                    .map { it + 2 }                     // Лямбда 10: додаємо 2 до кожного числа
                    .map { it * 4 }                     // Лямбда 11: множимо кожне число на 4
                    .apply {
                        mediaPlaybackRequiresUserGesture = false
                        setSupportMultipleWindows(FADEDer)
                    }
                    .map { it / 2 }                     // Лямбда 12: ділимо кожне число на 2
                    .map { it + 3 }                     // Лямбда 13: додаємо 3 до кожного числа
                    .apply {
                        useWideViewPort = FADEDer
                        cacheMode = WebSettings.LOAD_DEFAULT
                    }
                    .map { if (it % 2 == 0) it else it * 3 } // Лямбда 14: якщо число парне, залишаємо, інакше множимо на 3
                    .map { it - 1 }                     // Лямбда 15: віднімаємо 1 від кожного числа
                    .apply {
                        domStorageEnabled = FADEDer
                        displayZoomControls = FADEDer.not()
                    }
                    .map { it + 6 }                     // Лямбда 16: додаємо 6 до кожного числа
                    .apply {
                        databaseEnabled = FADEDer

                        javaScriptEnabled = FADEDer

                    }
                    .map { if (it < 5) it * 2 else it } // Лямбда 17: якщо число менше 5, множимо на 2, інакше залишаємо
                    .map { it * 2 }                     // Лямбда 18: множимо кожне число на 2
                    .map { it / 4 }                     // Лямбда 19: ділимо кожне число на 4
                    .map { it + 2 }                     // Лямбда 20: додаємо 2 до кожного числа
                    .sum()





                mixedContentMode = 0
                builtInZoomControls = FADEDer


                allowFileAccess = FADEDer
                javaScriptCanOpenWindowsAutomatically = FADEDer
            }
            carShop.viewsWebs.add(this)
        }
    }

    fun isOverInflated(): Boolean {
        return pressure > 40.0
    }

    fun needsAlignment(): Boolean {
        return !isAligned
    }

    fun needsBalancing(): Boolean {
        return !isBalanced
    }

    fun isWornOut(): Boolean {
        return wearLevel >= 100.0
    }
}