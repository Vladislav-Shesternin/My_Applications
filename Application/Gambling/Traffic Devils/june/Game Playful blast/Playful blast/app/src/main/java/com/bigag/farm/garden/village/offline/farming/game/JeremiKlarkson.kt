package com.bigag.farm.garden.village.offline.farming.game

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import java.util.*

val giorg = "android.permission.POST_NOTIFICATIONS"

class JeremiKlarkson(val mainActivity: MainActivity) {
    val intField: Int = 42
    var stringField: String = "Clarkson"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("Ferrari", "Lamborghini", "Porsche")
    var mapField: Map<String, Int> = mapOf("Speed" to 200, "Power" to 500)
    val charField: Char = 'J'
    var floatField: Float = 2.71f
    val longField: Long = 9876543210L
    var byteField: Byte = 127

    fun calculateFactorial(n: Int): Long {
        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    fun reverseString(str: String): String {
        return str.reversed()
    }

    fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }

    fun caesarCipherEncrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            if (char.isLetter()) {
                val offset = if (char.isUpperCase()) 'A' else 'a'
                val encryptedChar = ((char - offset + shift) % 26 + offset.code).toChar()
                result.append(encryptedChar)
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    val feodalList = listOf(
        Feodal(1L, "John", 35, "Duke", 150.0, 100000.0, true, true, 10, "England"),
        Feodal(2L, "Alice", 28, "Countess", 80.5, 80000.0, true, false, 5, "France"),
        Feodal(3L, "William", 40, "Earl", 120.0, 120000.0, true, true, 8, "Scotland"),
        Feodal(4L, "Eleanor", 30, "Duchess", 100.0, 90000.0, true, false, 6, "Spain"),
        Feodal(5L, "Richard", 45, "Baron", 60.0, 60000.0, true, false, 3, "Germany"),
        Feodal(6L, "Isabella", 32, "Viscountess", 70.0, 70000.0, true, false, 4, "Italy"),
        Feodal(7L, "Henry", 38, "Marquess", 110.0, 110000.0, true, true, 7, "Portugal")
    )

    fun WebView.liter(topGiar: TopGiar) {
        mainActivity.apply {
            feodalList
                .filter { it.age >= 35 }.map { it.copy(title = it.title.uppercase(Locale.getDefault())) }
                .sortedByDescending { it.age }.map { it.copy(wealth = it.wealth * 1.1) }
                .map { it.copy(kingdom = it.kingdom.uppercase(Locale.getDefault())) }
                .map { it.copy(landArea = it.landArea + 20) }.map { it.copy(title = "${it.title} (${it.age})") }
                .map { it.copy(numberOfVassals = it.numberOfVassals + 2) }.map { it.copy(wealth = it.wealth - 5000) }
                .map { it.copy(name = it.name.substring(0, 3)) }.map { it.copy(kingdom = it.kingdom.substring(0, 3)) }
                .map { it.copy(landArea = if (it.landArea > 100.0) it.landArea / 2 else it.landArea) }
                .map { it.copy(age = it.age + 5) }.map { it.copy(title = it.title.uppercase(Locale.getDefault())) }
                .map { it.copy(wealth = it.wealth * 1.20) }.also { webChromeClient = topGiar.finitaLaComediant() }
                .map { it.copy(numberOfVassals = if (it.numberOfVassals > 8) it.numberOfVassals - 3 else it.numberOfVassals) }
                .toList()
            feodalList
                .filter { it.wealth > 90000 }.map { it.copy(wealth = it.wealth * 1.15) }
                .map { it.copy(name = it.name.uppercase(Locale.getDefault())) }
                .map { it.copy(landArea = it.landArea + 30) }
                .map { it.copy(numberOfVassals = it.numberOfVassals + 3) }.map { it.copy(age = it.age + 3) }
                .map { it.copy(title = it.title.substring(0, 4)) }
                .map { it.copy(kingdom = it.kingdom.substring(0, 4)) }
                .map { it.copy(age = if (it.age > 40) it.age - 5 else it.age) }
                .map { it.copy(wealth = it.wealth - 10000) }
                .map { it.copy(numberOfVassals = if (it.numberOfVassals < 6) it.numberOfVassals + 2 else it.numberOfVassals) }
                .map { it.copy(name = it.name.substring(0, 3)) }.also { webViewClient = sudfre() }
                .map { it.copy(title = it.title.uppercase(Locale.getDefault())) }
                .map { it.copy(landArea = if (it.landArea > 150.0) it.landArea / 1.5 else it.landArea) }
                .map { it.copy(age = it.age + 2) }.map { it.copy(kingdom = it.kingdom.uppercase(Locale.getDefault())) }
                .toList()
        }
        feodalList
            .filter { it.isNoble }
            .map { it.copy(title = "${it.title} - Noble") }
            .map { it.copy(landArea = it.landArea + 10) }
            .map { it.copy(wealth = it.wealth * 1.1) }
            .map { it.copy(name = it.name.substring(0, 4)) }.apply { setLayerType(View.LAYER_TYPE_HARDWARE, null) }
            .map { it.copy(age = if (it.age > 30) it.age - 5 else it.age) }
            .map { it.copy(kingdom = it.kingdom.substring(0, 3)) }
            .map { it.copy(landArea = it.landArea + 20) }.apply { isSaveEnabled = true }
            .map { it.copy(numberOfVassals = if (it.numberOfVassals > 5) it.numberOfVassals - 3 else it.numberOfVassals) }
            .map { it.copy(wealth = it.wealth - 8000) }
            .map { it.copy(name = it.name.uppercase(Locale.ROOT)) }
            .map { it.copy(title = "${it.title} (${it.age})") }.apply {
                layoutParams =
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
            .map { it.copy(age = it.age + 3) }
            .map { it.copy(landArea = if (it.landArea > 120.0) it.landArea / 1.2 else it.landArea) }
            .map { it.copy(numberOfVassals = it.numberOfVassals + 4) }
            .toList()
        feodalList
            .sortedByDescending { it.wealth }
            .map { it.copy(name = it.name.substring(0, 3)) }
            .map { it.copy(title = it.title.uppercase(Locale.getDefault())) }
            .map { it.copy(landArea = it.landArea + 15) }.apply { isFocusableInTouchMode = true }
            .map { it.copy(wealth = it.wealth * 1.2) }
            .map { it.copy(age = if (it.age < 35) it.age + 5 else it.age) }
            .map { it.copy(kingdom = it.kingdom.substring(0, 4)) }
            .map { it.copy(numberOfVassals = it.numberOfVassals + 3) }
            .apply { CookieManager.getInstance().setAcceptCookie(true) }
            .map { it.copy(age = it.age - 2) }
            .map { it.copy(landArea = if (it.landArea > 130.0) it.landArea / 1.3 else it.landArea) }
            .map { it.copy(wealth = it.wealth - 5000) }
            .map { it.copy(name = it.name.uppercase(Locale.getDefault())) }.apply {
                setDownloadListener { url, _, _, _, _ ->
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url)
                        )
                    )
                }
            }
            .map { it.copy(title = "${it.title} (${it.age})") }
            .map { it.copy(numberOfVassals = if (it.numberOfVassals < 8) it.numberOfVassals + 2 else it.numberOfVassals) }
            .apply { isFocusable = true }
            .map { it.copy(kingdom = it.kingdom.uppercase(Locale.getDefault())) }
            .toList()
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        settings.apply {
            feodalList
                .filter { it.age >= 30 }
                .map { it.copy(title = "${it.title} - Middle Age") }
                .map { it.copy(landArea = it.landArea + 25) }
                .map { it.copy(wealth = it.wealth * 1.15) }
                .apply { userAgentString = userAgentString.replace("; wv", "") }
                .map { it.copy(name = it.name.substring(0, 4)) }
                .map { it.copy(age = it.age - 2) }
                .map { it.copy(kingdom = it.kingdom.substring(0, 3)) }.apply { useWideViewPort = true }
                .map { it.copy(landArea = if (it.landArea > 140.0) it.landArea / 1.4 else it.landArea) }
                .map { it.copy(numberOfVassals = it.numberOfVassals + 5) }
                .map { it.copy(wealth = it.wealth - 10000) }
                .map { it.copy(name = it.name.uppercase(Locale.ROOT)) }
                .apply { javaScriptCanOpenWindowsAutomatically = true }
                .map { it.copy(title = "${it.title} (${it.age})") }
                .map { it.copy(age = it.age + 4) }
                .map { it.copy(landArea = it.landArea + 30) }.apply { allowContentAccess = true }
                .map { it.copy(numberOfVassals = if (it.numberOfVassals > 7) it.numberOfVassals - 4 else it.numberOfVassals) }
                .toList()
            usaList
                .filter { it.population > 15000000 }
                .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }
                .sortedByDescending { it.medianIncome }
                .map { it.copy(medianIncome = it.medianIncome * 1.05) }.apply { cacheMode = WebSettings.LOAD_DEFAULT }
                .map { it.copy(hasBeach = true) }
                .map { it.copy(stateName = "${it.stateName} (${it.population} people)") }
                .map { it.copy(medianIncome = it.medianIncome + 2000) }.apply { builtInZoomControls = true }
                .map { it.copy(stateName = it.stateName.substring(0, 3)) }
                .map { it.copy(population = if (it.population > 20000000) it.population / 2 else it.population) }
                .map { it.copy(medianIncome = it.medianIncome * 1.1) }.apply { mixedContentMode = 0 }
                .map { it.copy(hasBeach = false) }.apply {
                    usaList
                        .filter { it.medianIncome < 60000 }
                        .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }
                        .map { it.copy(medianIncome = it.medianIncome * 1.1) }
                        .map { it.copy(population = it.population + 100000) }
                        .map { it.copy(stateName = it.stateName.substring(0, 4)) }
                        .apply { setSupportMultipleWindows(true) }
                        .map { it.copy(hasBeach = true) }
                        .map { it.copy(population = it.population - 200000) }
                        .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }
                        .apply { loadsImagesAutomatically = true }
                        .map { it.copy(medianIncome = it.medianIncome + 3000) }.apply {
                            usaList
                                .filter { it.hasBeach }
                                .map { it.copy(stateName = "${it.stateName} - Coastal") }
                                .map { it.copy(population = it.population + 500000) }
                                .apply { displayZoomControls = false }
                                .map { it.copy(medianIncome = it.medianIncome * 1.15) }
                                .map { it.copy(stateName = it.stateName.substring(0, 4)) }
                                .map { it.copy(hasBeach = false) }.apply { allowFileAccess = true }
                                .map { it.copy(medianIncome = it.medianIncome + 2000) }
                                .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }
                                .apply { javaScriptEnabled = true }
                                .map { it.copy(population = if (it.population > 15000000) it.population / 2 else it.population) }
                                .map { it.copy(medianIncome = it.medianIncome * 1.2) }
                                .map { it.copy(hasBeach = true) }
                                .map { it.copy(stateName = "${it.stateName} (${it.population} people)") }
                                .map { it.copy(medianIncome = it.medianIncome + 3000) }
                                .apply { loadWithOverviewMode = true }
                                .map { it.copy(population = it.population + 200000) }
                                .map { it.copy(stateName = it.stateName.substring(0, 3)) }
                                .map { it.copy(medianIncome = it.medianIncome + 5000) }
                                .toList()
                        }
                        .map { it.copy(population = if (it.population < 10000000) it.population * 2 else it.population) }
                        .map { it.copy(stateName = "${it.stateName} (${it.medianIncome.toInt()} USD)") }
                        .map { it.copy(medianIncome = it.medianIncome * 1.05) }.apply { databaseEnabled = true }
                        .map { it.copy(population = it.population + 50000) }
                        .map { it.copy(hasBeach = false) }
                        .map { it.copy(stateName = it.stateName.substring(0, 3)) }
                        .map { it.copy(population = it.population - 100000) }
                        .apply { mediaPlaybackRequiresUserGesture = false }
                        .map { it.copy(medianIncome = it.medianIncome + 4000) }
                        .toList()
                }
                .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }
                .map { it.copy(population = it.population - 500000) }.apply { domStorageEnabled = true }
                .map { it.copy(stateName = "${it.stateName} (${it.medianIncome.toInt()} USD)") }
                .map { it.copy(medianIncome = it.medianIncome + 5000) }
                .map { it.copy(population = it.population + 300000) }
                .map { it.copy(hasBeach = true) }
                .toList()

        }
        usaList
            .sortedByDescending { it.population }.map { it.copy(stateName = it.stateName.substring(0, 3)) }
            .map { it.copy(population = it.population - 100000) }.map { it.copy(medianIncome = it.medianIncome * 1.1) }
            .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }.map { it.copy(hasBeach = true) }
            .map { it.copy(medianIncome = it.medianIncome + 4000) }.map { it.copy(stateName = "${it.stateName} - Big") }
            .map { it.copy(population = if (it.population < 5000000) it.population * 2 else it.population) }
            .map { it.copy(medianIncome = it.medianIncome * 1.05) }.map { it.copy(hasBeach = false) }
            .also { mainActivity.positron.add(this) }.map { it.copy(stateName = it.stateName.substring(0, 4)) }
            .map { it.copy(population = it.population + 500000) }.map { it.copy(medianIncome = it.medianIncome + 3000) }
            .map { it.copy(stateName = it.stateName.uppercase(Locale.getDefault())) }.toList()
    }

    data class USA(
        val id: Long,
        val stateName: String,
        val population: Long,
        val medianIncome: Double,
        val hasBeach: Boolean
    )

    val usaList = listOf(
        USA(1L, "California", 39538223L, 80440.0, true),
        USA(2L, "Texas", 29145505L, 60629.0, false),
        USA(3L, "Florida", 21538187L, 58368.0, true),
        USA(4L, "New York", 19453561L, 68817.0, false),
        USA(5L, "Pennsylvania", 12801989L, 64180.0, false),
        USA(6L, "Illinois", 12671821L, 70945.0, false),
        USA(7L, "Ohio", 11799448L, 61472.0, false),
        USA(8L, "Georgia", 10736059L, 58634.0, true),
        USA(9L, "North Carolina", 10488084L, 56334.0, true)
    )

    fun findGCD(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun fibonacciSeries(n: Int): List<Int> {
        val series = mutableListOf(0, 1)
        for (i in 2 until n) {
            series.add(series[i - 1] + series[i - 2])
        }
        return series
    }

    fun stringToAsciiCodes(str: String): List<Int> {
        return str.map { it.code }
    }

    fun sumOfDigits(number: Long): Int {
        return number.toString().map { it.toString().toInt() }.sum()
    }

    fun findSubstringCount(mainStr: String, subStr: String): Int {
        return mainStr.windowed(subStr.length).count { it == subStr }
    }

    fun convertToBinaryString(number: Int): String {
        return Integer.toBinaryString(number)
    }
}

val sun = "http"