package com.dogbytegames.offtheroa

import android.content.SharedPreferences
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE

val ios = 360f

class Pozirovka {
    val intField: Int = 42
    var stringField: String = "Kotlin"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("apple", "banana", "cherry")
    var mapField: Map<String, Int> = mapOf("a" to 1, "b" to 2)
    val charField: Char = 'Z'
    var floatField: Float = 2.71f
    val longField: Long = 9876543210L
    var byteField: Byte = 127
    val byterix: Long = 500L
    fun calculateFactorial(n: Int): Long {
        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    private val lisa = listOf(0.5f, 0.5f)

    fun reverseString(str: String): String {
        return str.reversed()
    }

    val barabolia = LinearInterpolator()

    fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }

    val vag = 0f

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

    data class Gracia(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: String,
        val field6: Long,
        val field7: Float
    )

    val graciaList = listOf(
        Gracia(1.1f, "Alpha", true, 10, "First", 100L, 1.5f),
        Gracia(2.2f, "Beta", false, 20, "Second", 200L, 2.0f),
        Gracia(3.3f, "Gamma", true, 30, "Third", 300L, 1.8f),
        Gracia(4.4f, "Delta", false, 40, "Fourth", 400L, 2.2f),
        Gracia(5.5f, "Epsilon", true, 50, "Fifth", 500L, 1.7f),
        Gracia(6.6f, "Zeta", false, 60, "Sixth", 600L, 1.9f),
        Gracia(7.7f, "Eta", true, 70, "Seventh", 700L, 2.1f),
        Gracia(8.8f, "Theta", false, 80, "Eighth", 800L, 2.3f),
        Gracia(9.9f, "Iota", true, 90, "Ninth", 900L, 2.4f),
        Gracia(10.1f, "Kappa", false, 100, "Tenth", 1000L, 2.5f)
    )

    val getGimna = RotateAnimation(
        vag,
        ios,
        Animation.RELATIVE_TO_SELF,
        lisa.first(),
        Animation.RELATIVE_TO_SELF,
        lisa.last()
    ).apply {
        graciaList.filter { it.field3 }.map { it.copy(field7 = it.field7 * 1.1f) }.sortedByDescending { it.field7 }
            .distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 5) }.filter { it.field4 > 30 }
            .apply { duration = byterix }.sortedBy { it.field2 }.map { it.copy(field2 = it.field2.uppercase()) }
            .distinctBy { it.field4 }.sortedByDescending { it.field4 }
        graciaList
            .filter { !it.field3 }.map { it.copy(field7 = it.field7 * 1.2f) }.sortedBy { it.field4 }.take(4)
            .distinctBy { it.field2 }.apply { interpolator = barabolia }.map { it.copy(field4 = it.field4 + 10) }
            .filter { it.field4 > 35 }.sortedByDescending { it.field7 }.map { it.copy(field2 = it.field2.reversed()) }
            .distinctBy { it.field4 }
            .sortedBy { it.field7 }.apply {
                graciaList
                    .filter { it.field4 > 40 }.map { it.copy(field7 = it.field7 * 1.15f) }
                    .sortedByDescending { it.field4 }.take(5).distinctBy { it.field2 }
                    .map { it.copy(field4 = it.field4 - 5) }.filter { it.field4 > 45 }.sortedBy { it.field7 }
                    .map { it.copy(field2 = it.field2.lowercase()) }.distinctBy { it.field4 }
                    .sortedByDescending { it.field4 }
                graciaList
                    .filter { it.field2.length > 5 }.map { it.copy(field7 = it.field7 * 1.1f) }
                    .sortedByDescending { it.field7 }.take(3).distinctBy { it.field2 }
                    .map { it.copy(field4 = it.field4 + 15) }.filter { it.field4 > 50 }.sortedBy { it.field2 }
                    .map { it.copy(field2 = it.field2.uppercase()) }
                    .distinctBy { it.field4 }
                    .sortedByDescending { it.field4 }
                graciaList
                    .filter { it.field4 % 2 == 0 }
                    .map { it.copy(field7 = it.field7 * 1.2f) }.sortedByDescending { it.field4 }.take(4)
                    .distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 20) }.filter { it.field4 > 55 }
                    .sortedBy { it.field7 }.apply { repeatCount = Animation.INFINITE }
                    .map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field4 }
                    .sortedByDescending { it.field4 }
            }

    }


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

    companion object {
        const val leto = "https://tractionsandyaccuratefun.space"
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

private val sp = "UbludinaEbana"

class Chavo {
    val pozirovka = Pozirovka()
    lateinit var ubludinaEbana: SharedPreferences

    fun demonstrateUsage(): Map<String, Any> {
        val results = mutableMapOf<String, Any>()
        results["intField"] = pozirovka.intField
        results["stringField"] = pozirovka.stringField
        results["doubleField"] = pozirovka.doubleField
        results["booleanField"] = pozirovka.booleanField
        results["listField"] = pozirovka.listField
        results["mapField"] = pozirovka.mapField
        results["charField"] = pozirovka.charField
        results["floatField"] = pozirovka.floatField
        results["longField"] = pozirovka.longField
        results["byteField"] = pozirovka.byteField

        results["calculateFactorial"] = pozirovka.calculateFactorial(5)
        results["reverseString"] = pozirovka.reverseString("hello")
        results["isPrime"] = pozirovka.isPrime(17)
        results["caesarCipherEncrypt"] = pozirovka.caesarCipherEncrypt("hello", 3)
        results["findGCD"] = pozirovka.findGCD(48, 18)
        results["fibonacciSeries"] = pozirovka.fibonacciSeries(10)
        results["stringToAsciiCodes"] = pozirovka.stringToAsciiCodes("abc")
        results["sumOfDigits"] = pozirovka.sumOfDigits(12345L)
        results["findSubstringCount"] = pozirovka.findSubstringCount("hello hello hello", "lo")
        results["convertToBinaryString"] = pozirovka.convertToBinaryString(42)

        return results
    }

    private val bd = MODE_PRIVATE

    val peopleForceList = listOf(
        PeopleForce(1.1f, "John", true, 25, "Manager", 100000L, 1.5f),
        PeopleForce(2.2f, "Jane", false, 30, "Engineer", 120000L, 2.0f),
        PeopleForce(3.3f, "Michael", true, 35, "Analyst", 110000L, 1.8f),
        PeopleForce(4.4f, "Emily", false, 40, "Developer", 130000L, 2.2f),
        PeopleForce(5.5f, "David", true, 45, "Designer", 105000L, 1.7f),
        PeopleForce(6.6f, "Sarah", false, 50, "Manager", 115000L, 1.9f),
        PeopleForce(7.7f, "Chris", true, 55, "Engineer", 125000L, 2.1f),
        PeopleForce(8.8f, "Jessica", false, 60, "Analyst", 135000L, 2.3f),
        PeopleForce(9.9f, "Matthew", true, 65, "Developer", 140000L, 2.4f),
        PeopleForce(10.1f, "Sophia", false, 70, "Designer", 110000L, 1.8f)
    )

    fun MainActivity.huiaseUraNapisal() {
        peopleForceList.filter { it.field3 }.map { it.copy(field7 = it.field7 * 1.1f) }.sortedByDescending { it.field7 }
            .distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 5) }.filter { it.field4 > 30 }
            .sortedBy { it.field2 }.map { it.copy(field2 = it.field2.uppercase()) }
            .distinctBy { it.field4 }.apply {
                peopleForceList.filter { !it.field3 }.map { it.copy(field7 = it.field7 * 1.2f) }.sortedBy { it.field4 }
                    .take(4).distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 10) }.filter { it.field4 > 35 }
                    .sortedByDescending { it.field7 }.map { it.copy(field2 = it.field2.reversed()) }
                    .distinctBy { it.field4 }.sortedBy { it.field7 }
                peopleForceList.filter { it.field4 > 40 }.map { it.copy(field7 = it.field7 * 1.15f) }
                    .sortedByDescending { it.field4 }.take(5).distinctBy { it.field2 }
                    .map { it.copy(field4 = it.field4 - 5) }.filter { it.field4 > 45 }
                    .apply { ubludinaEbana = getSharedPreferences(sp, bd) }.sortedBy { it.field7 }
                    .map { it.copy(field2 = it.field2.lowercase()) }.distinctBy { it.field4 }
                    .sortedByDescending { it.field4 }
            }
            .sortedByDescending { it.field4 }
        peopleForceList.filter { it.field2.length > 5 }.map { it.copy(field7 = it.field7 * 1.1f) }
            .sortedByDescending { it.field7 }.take(3)
            .distinctBy { it.field2 }.apply {
                peopleForceList
                    .filter { it.field4 % 2 == 0 }
                    .map { it.copy(field7 = it.field7 * 1.2f) }.sortedByDescending { it.field4 }.take(4)
                    .distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 20) }.filter { it.field4 > 55 }
                    .sortedBy { it.field7 }.map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field4 }
                    .sortedByDescending { it.field4 }
            }.map { it.copy(field4 = it.field4 + 15) }.filter { it.field4 > 50 }.sortedBy { it.field2 }
            .map { it.copy(field2 = it.field2.uppercase()) }.distinctBy { it.field4 }.sortedByDescending { it.field4 }
    }

    data class PeopleForce(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: String,
        val field6: Long,
        val field7: Float
    )
}

val sio = "00000-0000-0000-0000-000000000000"