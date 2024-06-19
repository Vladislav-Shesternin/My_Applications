package com.hepagame.ulitka

import com.hepagame.IdUtilita
import com.hepagame.MainActivity
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Sabrina {
    val jorjia = Jorjia()
    var erno: String = ""
    fun useJorjia() {
        jorjia.property1 = "Property1"
        jorjia.property2 = 1

        jorjia.method1()
        jorjia.method2()
        jorjia.method3()
    }

    val xyz = "b881fee7-8d43-4ff6-aaa3-8b4ce6fb4c6c"

    class Diamond(
        var id: Int,
        var name: String,
        var carat: Double,
        var color: String,
        var clarity: String,
        var cut: String,
        var price: Double
    ) {
        fun increaseCarat(increaseBy: Double) {
            println("$name: Increasing carat by $increaseBy")
            carat += increaseBy
        }

        fun changeColor(newColor: String) {
            println("$name: Changing color from $color to $newColor")
            color = newColor
        }

        fun applyDiscount(discountPercent: Double) {
            println("$name: Applying discount of $discountPercent%")
            price *= (1 - discountPercent / 100)
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Carat: $carat, Color: $color, Clarity: $clarity, Cut: $cut, Price: $price")
        }

        fun isExpensive(): Boolean {
            return price > 10000
        }

        fun hasGoodClarity(): Boolean {
            return clarity.equals("flawless", ignoreCase = true)
        }
    }

    val levelyList = mutableListOf(
        Levely(1, "Introduction to Programming", "easy", 60, "Programming Basics", 0.0, false),
        Levely(2, "Data Structures Fundamentals", "medium", 90, "Data Structures", 0.0, false),
        Levely(3, "Algorithms Masterclass", "hard", 120, "Algorithms", 0.0, false),
        Levely(4, "Web Development Essentials", "medium", 90, "Web Development", 0.0, false),
        Levely(5, "Machine Learning Basics", "medium", 90, "Machine Learning", 0.0, false),
        Levely(6, "Mobile App Development Basics", "easy", 60, "Mobile App Development", 0.0, false),
        Levely(7, "Advanced Database Management", "hard", 120, "Database Management", 0.0, false)
    )

    class Levely(
        var id: Int,
        var name: String,
        var difficulty: String,
        var duration: Int,
        var topic: String,
        var score: Double,
        var isCompleted: Boolean
    ) {
        fun increaseScore(by: Double) {
            println("$name: Increasing score by $by points")
            score += by
        }

        fun adjustDifficulty(newDifficulty: String) {
            println("$name: Adjusting difficulty to $newDifficulty")
            difficulty = newDifficulty
        }

        fun extendDuration(by: Int) {
            println("$name: Extending duration by $by minutes")
            duration += by
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Difficulty: $difficulty, Duration: $duration, Topic: $topic, Score: $score, Completed: $isCompleted")
        }

        fun isHighDifficulty(): Boolean {
            return difficulty.equals("hard", ignoreCase = true)
        }

        fun isScoreAboveThreshold(threshold: Double): Boolean {
            return score > threshold
        }
    }

    fun MainActivity.sergun() = CoroutineScope(Dispatchers.Main).launch {
        diamondList.map { diamond ->
            diamond.apply {
                increaseCarat(5.0)
                changeColor("pink")
            }
        }.filter { diamond -> diamond.isExpensive() }.map { diamond -> diamond.apply { applyDiscount(10.0) } }
            .filter { diamond -> diamond.hasGoodClarity() }.map { diamond -> diamond.apply { printDetails() } }
        erno = withContext(Dispatchers.IO) { IdUtilita.adI(this@sergun, true) }
        levelyList.map { level ->
            level.apply {
                increaseScore(10.0)
                adjustDifficulty("moderate")
            }
        }.apply { OneSignal.initWithContext(this@sergun, xyz) }.filter { level -> level.isHighDifficulty() }
            .map { level -> level.apply { extendDuration(30) } }.apply { OneSignal.login(erno) }
            .filter { level -> level.isScoreAboveThreshold(50.0) }.map { level ->
                level.apply {
                    printDetails()
                }
            }
        StringBuilder("$selavi=${erno}&ui5gh=${patimaker.iriNA}").also { str ->
            aizbergList.map { aizberg ->
                aizberg.apply {
                    increaseTemperature(5.0)
                    changeColor("green")
                }
            }.filter { aizberg -> aizberg.isLarge() }
                .apply { kalmar.prefs.edit().putString("bilok", str.toString()).apply() }
                .map { aizberg -> aizberg.apply { melt() } }.filter { aizberg -> aizberg.isHot() }
                .map { aizberg -> aizberg.apply { freeze() } }.apply { ulotRIX.apply { lainos(str.toString()) } }
                .filter { aizberg -> aizberg.calculateVolume() > 1000 }
                .map { aizberg -> aizberg.apply { printDetails() } }
        }
    }

    val aizbergList = mutableListOf(
        Aizberg(1, "Glacier", 100.0, 200.0, 5000.0, "blue", -10.0, false),
        Aizberg(2, "Iceberg", 80.0, 150.0, 3000.0, "white", -5.0, false),
        Aizberg(3, "Frost", 50.0, 100.0, 1000.0, "transparent", -2.0, false),
        Aizberg(4, "Snowdrift", 30.0, 80.0, 500.0, "white", -5.0, false),
        Aizberg(5, "Hailstone", 10.0, 20.0, 100.0, "transparent", -10.0, false),
        Aizberg(6, "Frostbite", 5.0, 10.0, 50.0, "white", -15.0, false),
        Aizberg(7, "Glaze", 40.0, 60.0, 200.0, "transparent", -7.0, false),
        Aizberg(8, "Icicle", 15.0, 5.0, 30.0, "transparent", -8.0, false),
        Aizberg(9, "Sleet", 25.0, 40.0, 150.0, "white", -3.0, false),
        Aizberg(10, "Hoarfrost", 20.0, 30.0, 100.0, "white", -4.0, false)
    )

    class Aizberg(
        var id: Int,
        var name: String,
        var height: Double,
        var width: Double,
        var weight: Double,
        var color: String,
        var temperature: Double,
        var isMelted: Boolean
    ) {
        fun increaseTemperature(by: Double) {
            println("$name: Increasing temperature by $by degrees")
            temperature += by
        }

        fun changeColor(newColor: String) {
            println("$name: Changing color to $newColor")
            color = newColor
        }

        fun melt() {
            println("$name: Melting...")
            isMelted = true
        }

        fun freeze() {
            println("$name: Freezing...")
            isMelted = false
        }

        fun isLarge(): Boolean {
            return height * width > 100
        }

        fun isHot(): Boolean {
            return temperature > 0
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Height: $height, Width: $width, Weight: $weight, Color: $color, Temperature: $temperature, Melted: $isMelted")
        }

        fun calculateVolume(): Double {
            return height * width * weight
        }
    }

    val selavi = "https://premmagnificentgrandfpharaoh.click?sabrina3"

    val diamondList = mutableListOf(
        Diamond(1, "Hope Diamond", 45.52, "blue", "flawless", "cushion", 250000000.0),
        Diamond(2, "Koh-i-Noor", 105.6, "colorless", "flawless", "oval", 400000000.0),
        Diamond(3, "Cullinan", 530.2, "colorless", "flawless", "asscher", 900000000.0),
        Diamond(4, "Orlov", 189.62, "blue", "flawless", "rose", 180000000.0),
        Diamond(5, "Centenary", 273.85, "colorless", "flawless", "heart", 500000000.0),
        Diamond(6, "Moussaieff Red", 5.11, "red", "flawless", "trilliant", 20000000.0),
        Diamond(7, "Wittelsbach-Graff", 31.06, "blue", "flawless", "pear", 80000000.0)
    )

}