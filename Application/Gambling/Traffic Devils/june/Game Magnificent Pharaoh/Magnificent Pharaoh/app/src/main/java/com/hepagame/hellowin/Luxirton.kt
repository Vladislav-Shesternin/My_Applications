package com.hepagame.hellowin

import android.os.RemoteException
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.hepagame.MainActivity

class Luxirton {
    private val youHappy = YouHappy()

    fun useYouHappy() {
        youHappy.field1 = "Field1"
        youHappy.field2 = 1
        youHappy.field3 = 1.0
        youHappy.field4 = true
        youHappy.field5 = "Field5"
        youHappy.field6 = 2
        youHappy.field7 = 2.0
        youHappy.field8 = true
        youHappy.field9 = "Field9"
        youHappy.field10 = 3
        youHappy.field11 = 3.0
        youHappy.field12 = true
        youHappy.field13 = "Field13"
        youHappy.field14 = 4
        youHappy.field15 = 4.0
        youHappy.field16 = true
        youHappy.field17 = "Field17"
        youHappy.field18 = 5
        youHappy.field19 = 5.0
        youHappy.field20 = true
        youHappy.field21 = "Field21"
        youHappy.field22 = 6
        youHappy.field23 = 6.0
        youHappy.field24 = true
        youHappy.field25 = "Field25"
        youHappy.field26 = 7
        youHappy.field27 = 7.0
        youHappy.field28 = true
        youHappy.field29 = "Field29"
        youHappy.field30 = 8

        youHappy.method1()
        youHappy.method2()
        youHappy.method3()
        youHappy.method4()
        youHappy.method5()
        youHappy.method6()
        youHappy.method7()
        youHappy.method8()
        youHappy.method9()
        youHappy.method10()
        youHappy.method11()
        youHappy.method12()
        youHappy.method13()
        youHappy.method14()
        youHappy.method15()
        youHappy.method16()
        youHappy.method17()
        youHappy.method18()
        youHappy.method19()
        youHappy.method20()
        youHappy.method21()
        youHappy.method22()
        youHappy.method23()
        youHappy.method24()
        youHappy.method25()
        youHappy.method26()
        youHappy.method27()
        youHappy.method28()
        youHappy.method29()
        youHappy.method30()
    }

    val indusik = InstallReferrerClient.InstallReferrerResponse.OK

    class Brabus(
        var model: String,
        var year: Int,
        var horsepower: Int,
        var torque: Int,
        var topSpeed: Int,
        var acceleration: Double,
        var price: Double,
        var color: String,
        var isAvailable: Boolean,
        var isNew: Boolean
    ) {
        fun upgradeHorsepower(boost: Int) {
            horsepower += boost
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun applyDiscount(discount: Double) {
            price *= (1 - discount)
        }

        fun startLease(duration: Int) {
            price *= duration
        }

        fun checkAvailability(): Boolean {
            return isAvailable
        }

        fun displayInfo() {
            println("Model: $model, Year: $year, Horsepower: $horsepower hp, Torque: $torque Nm, Top Speed: $topSpeed km/h, Acceleration: $acceleration sec, Price: $price, Color: $color, Available: $isAvailable, New: $isNew")
        }
    }

    fun MainActivity.IIIRRR() = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            val brabuses = listOf(
                Brabus("B63", 2022, 800, 1000, 320, 3.5, 200000.0, "Black", true, true),
                Brabus("B900", 2021, 900, 1100, 330, 3.3, 300000.0, "White", true, false),
                Brabus("B850", 2023, 850, 1050, 325, 3.4, 250000.0, "Red", true, true),
                Brabus("B700", 2020, 700, 900, 310, 3.7, 150000.0, "Blue", true, false),
                Brabus("B800", 2024, 800, 1000, 320, 3.5, 200000.0, "Silver", true, true),
                Brabus("B1000", 2025, 1000, 1200, 340, 3.2, 400000.0, "Green", true, true)
            )
            if (responseCode == indusik) try {
                brabuses.map { brabus ->
                    brabus.apply {
                        upgradeHorsepower(100)
                        changeColor("Gold")
                    }
                }.filter { brabus -> brabus.isNew }.map { brabus -> brabus.apply { applyDiscount(0.1) } }
                    .filter { brabus -> brabus.price < 250000 }
                    .apply { patimaker.iriNA = hejami.rasmusic.installReferrer.installReferrer }
                    .map { brabus -> brabus.apply { startLease(3) } }.filter { brabus -> brabus.horsepower > 800 }
                    .map { brabus ->
                        brabus.apply {
                            displayInfo()
                        }
                    }
            } catch (_: RemoteException) {
            }
        }

        override fun onInstallReferrerServiceDisconnected() {
            val ururururu = this
            amgs.map { amg ->
                amg.apply {
                    upgradeHorsepower(100)
                    changeColor("Gold")
                }
            }.filter { amg -> amg.isNew }.map { amg -> amg.apply { applyDiscount(0.1) } }
                .filter { amg -> amg.price < 120000 }.apply { hejami.rasmusic.startConnection(ururururu) }
                .map { amg -> amg.apply { startLease(3) } }.filter { amg -> amg.horsepower > 500 }.map { amg ->
                amg.apply {
                    displayInfo()
                }
            }
        }
    }

    val amgs = listOf(
        AMG("C63", 2022, 503, 700, 250, 3.9, 85000.0, "Black", true, true),
        AMG("E63", 2021, 603, 800, 300, 3.4, 100000.0, "White", true, false),
        AMG("A45", 2023, 415, 500, 250, 4.0, 65000.0, "Red", true, true),
        AMG("GLE63", 2020, 577, 700, 280, 3.8, 120000.0, "Blue", true, false),
        AMG("S63", 2024, 621, 900, 320, 3.2, 150000.0, "Silver", true, true),
        AMG("G63", 2025, 577, 700, 220, 4.5, 200000.0, "Green", true, true)
    )

    class AMG(
        var model: String,
        var year: Int,
        var horsepower: Int,
        var torque: Int,
        var topSpeed: Int,
        var acceleration: Double,
        var price: Double,
        var color: String,
        var isAvailable: Boolean,
        var isNew: Boolean
    ) {
        fun upgradeHorsepower(boost: Int) {
            horsepower += boost
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun applyDiscount(discount: Double) {
            price *= (1 - discount)
        }

        fun startLease(duration: Int) {
            price *= duration
        }

        fun checkAvailability(): Boolean {
            return isAvailable
        }

        fun displayInfo() {
            println("Model: $model, Year: $year, Horsepower: $horsepower hp, Torque: $torque Nm, Top Speed: $topSpeed km/h, Acceleration: $acceleration sec, Price: $price, Color: $color, Available: $isAvailable, New: $isNew")
        }
    }

}
