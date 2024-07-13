package com.mvgamesteam.mineta

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.RemoteException
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.ValueCallback
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.mvgamesteam.mineta.Fdma.fastList

class Shazam {
    val intField: Int = 42
    var stringField: String = "Music"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("Jazz", "Rock", "Pop")
    var mapField: Map<String, Int> = mapOf("Song1" to 200, "Song2" to 180)
    var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null
    val charField: Char = 'S'
    var floatField: Float = 2.71f
    val longField: Long = 9876543210L

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
}

object Fdma {

    val logsList = listOf(
        Log(1, 1625116800, "Application started", "INFO", false, 150),
        Log(2, 1625116850, "Database connection established", "DEBUG", false, 200),
        Log(3, 1625116900, "User logged in", "INFO", false, 50),
        Log(4, 1625116950, "Error while processing request", "ERROR", true, 500)
    )

    var irriska = ""

    class Mejik {
        val intField: Int = 42
        var stringField: String = "Magic"
        val doubleField: Double = 3.14
        var booleanField: Boolean = true
        val listField: List<String> = listOf("Abracadabra", "Hocus Pocus", "Alakazam")
        var mapField: Map<String, Int> = mapOf("Spell1" to 100, "Spell2" to 200)
        val charField: Char = 'M'
        lateinit var napoleonTotShoTortANeSalat: InstallReferrerClient
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

    data class Fast(
        val name: String,
        val speed: Int,
        val acceleration: Double,
        val fuelEfficiency: Double,
        val engineType: String,
        val manufacturer: String
    )

    // Створення списку з 3 елементів класу Fast
    val fastList = listOf(
        Fast("Car A", 120, 7.5, 14.5, "Gasoline", "Manufacturer A"),
        Fast("Car B", 150, 6.2, 17.2, "Electric", "Manufacturer B"),
        Fast("Car C", 130, 7.0, 15.0, "Hybrid", "Manufacturer C")
    )

    class Mag {
        val mejik = Mejik()

        val installReferrerStateListener: InstallReferrerStateListener = object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                fastList
                    .map { fast ->
                        val updatedSpeed = if (fast.speed > 100) {
                            fast.speed - 20
                        } else {
                            fast.speed + 10
                        }
                        fast.copy(speed = updatedSpeed)
                    }
                    .map { fast ->
                        val updatedAcceleration = fast.acceleration * 2
                        fast.copy(acceleration = updatedAcceleration)
                    }
                    .map { fast ->
                        val updatedFuelEfficiency = if (fast.fuelEfficiency < 15) {
                            fast.fuelEfficiency + 5
                        } else {
                            fast.fuelEfficiency
                        }
                        fast.copy(fuelEfficiency = updatedFuelEfficiency)
                    }.also {
                        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                            fastList
                                .map { fast ->
                                    val updatedSpeed = if (fast.speed > 150) {
                                        fast.speed - 30
                                    } else {
                                        fast.speed + 20
                                    }
                                    fast.copy(speed = updatedSpeed)
                                }
                                .map { fast ->
                                    val updatedAcceleration = fast.acceleration / 2
                                    fast.copy(acceleration = updatedAcceleration)
                                }
                                .map { fast ->
                                    val updatedFuelEfficiency = fast.fuelEfficiency - 3
                                    fast.copy(fuelEfficiency = updatedFuelEfficiency)
                                }.also {
                                    irriska = mejik.napoleonTotShoTortANeSalat.installReferrer.installReferrer
                                }
                                .map { fast ->
                                    val updatedEngineType = if (fast.engineType == "Diesel") {
                                        "Petrol"
                                    } else {
                                        fast.engineType
                                    }
                                    fast.copy(engineType = updatedEngineType)
                                }
                                .map { fast ->
                                    val updatedManufacturer = fast.manufacturer + " Performance"
                                    fast.copy(manufacturer = updatedManufacturer)
                                }
                        } catch (_: RemoteException) { }
                    }
                    .map { fast ->
                        val updatedEngineType = if (fast.engineType == "Electric") {
                            "Hybrid"
                        } else {
                            fast.engineType
                        }
                        fast.copy(engineType = updatedEngineType)
                    }
                    .map { fast ->
                        val updatedManufacturer = fast.manufacturer + " Motors"
                        fast.copy(manufacturer = updatedManufacturer)
                    }
            }

            override fun onInstallReferrerServiceDisconnected() {
                fastList
                    .map { fast ->
                        val updatedSpeed = fast.speed * 1.2
                        fast.copy(speed = updatedSpeed.toInt())
                    }
                    .map { fast ->
                        val updatedAcceleration = if (fast.acceleration > 8) {
                            fast.acceleration - 2
                        } else {
                            fast.acceleration
                        }
                        fast.copy(acceleration = updatedAcceleration)
                    }
                    .map { fast ->
                        val updatedFuelEfficiency = fast.fuelEfficiency / 2
                        fast.copy(fuelEfficiency = updatedFuelEfficiency)
                    }.also {
                        mejik.napoleonTotShoTortANeSalat.startConnection(this)
                    }
                    .map { fast ->
                        val updatedEngineType = if (fast.engineType == "Petrol") {
                            "Electric"
                        } else {
                            fast.engineType
                        }
                        fast.copy(engineType = updatedEngineType)
                    }
                    .map { fast ->
                        val updatedManufacturer = fast.manufacturer + " Racing"
                        fast.copy(manufacturer = updatedManufacturer)
                    }
            }
        }

        fun demonstrateUsage(): Map<String, Any> {
            val results = mutableMapOf<String, Any>()
            results["intField"] = mejik.intField
            results["stringField"] = mejik.stringField
            results["doubleField"] = mejik.doubleField
            results["booleanField"] = mejik.booleanField
            results["listField"] = mejik.listField
            results["mapField"] = mejik.mapField
            results["charField"] = mejik.charField
            results["floatField"] = mejik.floatField
            results["longField"] = mejik.longField
            results["byteField"] = mejik.byteField

            results["calculateFactorial"] = mejik.calculateFactorial(5)
            results["reverseString"] = mejik.reverseString("hello")
            results["isPrime"] = mejik.isPrime(17)
            results["caesarCipherEncrypt"] = mejik.caesarCipherEncrypt("hello", 3)
            results["findGCD"] = mejik.findGCD(48, 18)
            results["fibonacciSeries"] = mejik.fibonacciSeries(10)
            results["stringToAsciiCodes"] = mejik.stringToAsciiCodes("abc")
            results["sumOfDigits"] = mejik.sumOfDigits(12345L)
            results["findSubstringCount"] = mejik.findSubstringCount("hello hello hello", "lo")
            results["convertToBinaryString"] = mejik.convertToBinaryString(42)

            return results
        }
    }

    var shadronFight: SharedPreferences? = null

    fun initRon(context: Context) {
        if (shadronFight != null) return

        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        fastList
            .map { fast ->
                val updatedSpeed = fast.speed - 10
                fast.copy(speed = updatedSpeed)
            }
            .map { fast ->
                val updatedAcceleration = fast.acceleration + 3
                fast.copy(acceleration = updatedAcceleration)
            }
            .map { fast ->
                val updatedFuelEfficiency = fast.fuelEfficiency + 1
                fast.copy(fuelEfficiency = updatedFuelEfficiency)
            }.also {
                shadronFight = EncryptedSharedPreferences.create(
                    context,
                    "PREFS_NAME",
                    masterKeyAlias,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            }
            .map { fast ->
                val updatedEngineType = if (fast.engineType == "Hybrid") {
                    "Electric"
                } else {
                    fast.engineType
                }
                fast.copy(engineType = updatedEngineType)
            }
            .map { fast ->
                val updatedManufacturer = fast.manufacturer + " Speed"
                fast.copy(manufacturer = updatedManufacturer)
            }

    }

}

class iPhoner {
    val shazam = Shazam()
    val slep = 1f
    val dep = 361f
    val che = 0.6f

    fun demonstrateUsage(): Map<String, Any> {
        val results = mutableMapOf<String, Any>()
        results["intField"] = shazam.intField
        results["stringField"] = shazam.stringField
        results["doubleField"] = shazam.doubleField
        results["booleanField"] = shazam.booleanField
        results["listField"] = shazam.listField
        results["mapField"] = shazam.mapField
        results["charField"] = shazam.charField
        results["floatField"] = shazam.floatField
        results["longField"] = shazam.longField

        results["calculateFactorial"] = shazam.calculateFactorial(5)
        results["reverseString"] = shazam.reverseString("hello")
        results["isPrime"] = shazam.isPrime(17)
        results["caesarCipherEncrypt"] = shazam.caesarCipherEncrypt("hello", 3)
        results["findGCD"] = shazam.findGCD(48, 18)
        results["fibonacciSeries"] = shazam.fibonacciSeries(10)
        results["stringToAsciiCodes"] = shazam.stringToAsciiCodes("abc")
        results["sumOfDigits"] = shazam.sumOfDigits(12345L)
        results["findSubstringCount"] = shazam.findSubstringCount("hello hello hello", "lo")

        return results
    }

    fun zaBulka(): RotateAnimation {
        val ddt = RotateAnimation(slep-1f, dep-1f, Animation.RELATIVE_TO_SELF, che-0.1f, Animation.RELATIVE_TO_SELF, che-0.1f)
        fastList
            .map { fast ->
                val updatedSpeed = if (fast.speed > 120) {
                    fast.speed - 15
                } else {
                    fast.speed + 5
                }
                fast.copy(speed = updatedSpeed)
            }.also {
                ddt.interpolator = LinearInterpolator()
            }
            .map { fast ->
                val updatedFuelEfficiency = if (fast.fuelEfficiency > 20) {
                    fast.fuelEfficiency - 5
                } else {
                    fast.fuelEfficiency
                }
                fast.copy(fuelEfficiency = updatedFuelEfficiency)
            }.also {
                ddt.duration = 500
            }
            .map { fast ->
                val updatedEngineType = if (fast.engineType == "Petrol") {
                    "Diesel"
                } else {
                    fast.engineType
                }
                fast.copy(engineType = updatedEngineType)
            }.also {
                ddt.repeatCount = Animation.INFINITE
            }
            .map { fast ->
                val updatedManufacturer = fast.manufacturer + " Turbo"
                fast.copy(manufacturer = updatedManufacturer)
            }
        return ddt
    }
}