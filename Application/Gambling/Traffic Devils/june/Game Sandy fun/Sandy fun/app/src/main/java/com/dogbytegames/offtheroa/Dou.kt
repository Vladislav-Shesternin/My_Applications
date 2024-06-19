package com.dogbytegames.offtheroa

import android.os.RemoteException
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import java.util.*

class Dou {
    val hello = Hello()

    fun demonstrateUsage(): Map<String, Any> {
        val results = mutableMapOf<String, Any>()
        results["intField"] = hello.intField
        results["stringField"] = hello.stringField
        results["doubleField"] = hello.doubleField
        results["booleanField"] = hello.booleanField
        results["listField"] = hello.listField
        results["mapField"] = hello.mapField
        results["charField"] = hello.charField
        results["floatField"] = hello.floatField
        results["longField"] = hello.longField
        results["byteField"] = hello.byteField

        results["calculateSum"] = hello.calculateSum(5, 10)
        results["concatenateStrings"] = hello.concatenateStrings("Kotlin ", "Programming")
        results["toggleBoolean"] = hello.toggleBoolean()
        results["findMaxInList"] = hello.findMaxInList() ?: "null"
        results["addToMap"] = hello.addToMap("key3", 3)
        results["convertCharToAscii"] = hello.convertCharToAscii()
        results["calculateCircleArea"] = hello.calculateCircleArea(5.0)
        results["getStringLength"] = hello.getStringLength("Sample string")
        results["convertToLong"] = hello.convertToLong(100)
        results["doubleToInt"] = hello.doubleToInt(99.99)

        return results
    }

    companion object {
        const val dura = "android.permission.POST_NOTIFICATIONS"
    }

    lateinit var pizso: InstallReferrerClient

    data class Weekend(
        val field1: Long,
        val field2: Long,
        val field3: Int,
        val field4: String,
        val field5: Long,
        val field6: Float,
        val field7: Boolean
    )

    fun ernesto(activity: MainActivity) {
        val weekendList = listOf(
            Weekend(100L, 200L, 10, "First", 1000L, 1.5f, true),
            Weekend(200L, 300L, 20, "Second", 2000L, 2.0f, false),
            Weekend(300L, 400L, 30, "Third", 3000L, 1.8f, true),
            Weekend(400L, 500L, 40, "Fourth", 4000L, 2.2f, false),
            Weekend(500L, 600L, 50, "Fifth", 5000L, 1.7f, true),
            Weekend(600L, 700L, 60, "Sixth", 6000L, 1.9f, false),
            Weekend(700L, 800L, 70, "Seventh", 7000L, 2.1f, true),
            Weekend(800L, 900L, 80, "Eighth", 8000L, 2.3f, false),
            Weekend(900L, 1000L, 90, "Ninth", 9000L, 2.4f, true),
            Weekend(1000L, 1100L, 100, "Tenth", 10000L, 2.5f, false)
        )
        weekendList.filter { it.field3 > 50 }.sortedByDescending { it.field2 }
            .map { it.copy(field4 = it.field4.uppercase(Locale.getDefault())) }.distinctBy { it.field5 }
            .map { it.copy(field1 = it.field1 * 2) }.toList()
        weekendList.filter { it.field7 }.sortedBy { it.field3 }.map { it.copy(field4 = it.field4.reversed()) }
            .distinctBy { it.field1 }.map { it.copy(field2 = it.field2 - it.field1) }.toList()
        weekendList.filterNot { it.field6 > 2.0f }.sortedByDescending { it.field1 }
            .map { it.copy(field4 = it.field4.lowercase(Locale.getDefault())) }.distinctBy { it.field3 }
            .map { it.copy(field1 = it.field1 + it.field2) }.toList()
        weekendList.filter { it.field3 % 2 == 0 }.sortedByDescending { it.field5 }
            .map { it.copy(field4 = it.field4.substring(0, 3)) }.distinctBy { it.field2 }
            .apply { pizso = InstallReferrerClient.newBuilder(activity).build() }
            .map { it.copy(field2 = it.field2 + it.field1) }.toList()
        weekendList.filter { it.field3 < 30 }.sortedBy { it.field1 }
            .map { it.copy(field4 = it.field4.uppercase(Locale.getDefault())) }.distinctBy { it.field5 }
            .map { it.copy(field3 = it.field3 * 2) }.toList()
    }

    data class Surname(
        val field1: Long,
        val field2: Long,
        val field3: Int,
        val field4: String,
        val field5: Long,
        val field6: Float
    )

    val surnameList = listOf(
        Surname(100L, 200L, 10, "First", 1000L, 1.5f),
        Surname(200L, 300L, 20, "Second", 2000L, 2.0f),
        Surname(300L, 400L, 30, "Third", 3000L, 1.8f),
        Surname(400L, 500L, 40, "Fourth", 4000L, 2.2f),
        Surname(500L, 600L, 50, "Fifth", 5000L, 1.7f),
        Surname(600L, 700L, 60, "Sixth", 6000L, 1.9f),
        Surname(700L, 800L, 70, "Seventh", 7000L, 2.1f),
        Surname(800L, 900L, 80, "Eighth", 8000L, 2.3f),
        Surname(900L, 1000L, 90, "Ninth", 9000L, 2.4f),
        Surname(1000L, 1100L, 100, "Tenth", 10000L, 2.5f)
    )

    data class Animal(
        val field1: Long,
        val field2: Long,
        val field3: Int,
        val field4: String,
        val field5: Long,
        val field6: Float,
        val field7: Boolean
    )

    val animalList = listOf(
        Animal(100L, 200L, 10, "First", 1000L, 1.5f, true),
        Animal(200L, 300L, 20, "Second", 2000L, 2.0f, false),
        Animal(300L, 400L, 30, "Third", 3000L, 1.8f, true),
        Animal(400L, 500L, 40, "Fourth", 4000L, 2.2f, false),
        Animal(500L, 600L, 50, "Fifth", 5000L, 1.7f, true),
        Animal(600L, 700L, 60, "Sixth", 6000L, 1.9f, false),
        Animal(700L, 800L, 70, "Seventh", 7000L, 2.1f, true),
        Animal(800L, 900L, 80, "Eighth", 8000L, 2.3f, false),
        Animal(900L, 1000L, 90, "Ninth", 9000L, 2.4f, true),
        Animal(1000L, 1100L, 100, "Tenth", 10000L, 2.5f, false)
    )

    val lios: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            surnameList
                .map { it.copy(field1 = it.field1 * 2) }.filter { it.field3 > 30 }.sortedByDescending { it.field2 }
                .map { it.copy(field4 = it.field4.uppercase(Locale.getDefault())) }.distinctBy { it.field5 }.toList()
            if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                animalList
                    .filter { it.field3 > 50 }
                    .sortedByDescending { it.field2 }.map { it.copy(field4 = it.field4.uppercase(Locale.getDefault())) }
                    .distinctBy { it.field5 }.map { it.copy(field1 = it.field1 * 2) }.toList()
                animalList
                    .filter { it.field7 }.sortedBy { it.field3 }.map { it.copy(field4 = it.field4.reversed()) }
                    .distinctBy { it.field1 }.map { it.copy(field2 = it.field2 - it.field1) }.toList()
                animalList
                    .filterNot { it.field6 > 2.0f }
                    .sortedByDescending { it.field1 }.apply { hello.iR = pizso.installReferrer.installReferrer }
                    .map { it.copy(field4 = it.field4.lowercase(Locale.getDefault())) }.distinctBy { it.field3 }
                    .map { it.copy(field1 = it.field1 + it.field2) }.toList()

            } catch (_: RemoteException) {
            }
        }

        override fun onInstallReferrerServiceDisconnected() {
            val s = this
            animalList
                .filter { it.field3 % 2 == 0 }
                .sortedByDescending { it.field5 }.map { it.copy(field4 = it.field4.substring(0, 3)) }
                .distinctBy { it.field2 }.map { it.copy(field2 = it.field2 + it.field1) }.toList()
            animalList
                .filter { it.field3 < 30 }.sortedBy { it.field1 }.apply { pizso.startConnection(s) }
                .map { it.copy(field4 = it.field4.uppercase(Locale.getDefault())) }.distinctBy { it.field5 }
                .map { it.copy(field3 = it.field3 * 2) }.toList()
        }
    }

}

val g2 = "com.android.chrome"