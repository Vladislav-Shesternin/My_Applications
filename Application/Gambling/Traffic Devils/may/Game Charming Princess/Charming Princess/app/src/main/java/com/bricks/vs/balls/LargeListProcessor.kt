package com.bricks.vs.balls

import android.app.Activity
import android.content.SharedPreferences
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.core.view.isVisible
import com.bricks.vs.balls.MainActivity.Companion.TEST
import com.bricks.vs.balls.databinding.ActivityMainBinding
import com.bricks.vs.balls.util.log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LargeListProcessor {
    // Поле класу з великим списком чисел
    private val largeList: List<Int> = (1..100).toList()

    // Операція 1: Фільтрація парних чисел
    fun filterEvenNumbers(): List<Int> {
        return largeList.filter { it % 2 == 0 }
    }

    // Операція 2: Знаходження квадратів чисел
    fun squareNumbers(): List<Int> {
        return largeList.map { it * it }
    }

    data class Pomandor(
        val name: String,
        val color: String,
        val size: String,
        val weight: Double,
        val pricePerKg: Double
    )

    val filadelf = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        listOf(
            Pomandor("Roma", "Red", "Medium", 0.2, 2.5),
            Pomandor("Cherry", "Yellow", "Small", 0.1, 3.0),
            Pomandor("Beefsteak", "Orange", "Large", 0.3, 2.0),
            Pomandor("Heirloom", "Green", "Medium", 0.25, 2.8),
            Pomandor("Grape", "Purple", "Small", 0.15, 3.5),
            Pomandor("Plum", "Pink", "Medium", 0.2, 2.7),
            Pomandor("Campari", "Orange", "Small", 0.1, 3.2),
            Pomandor("Oxheart", "Red", "Large", 0.35, 2.3),
            Pomandor("Brandywine", "Yellow", "Medium", 0.2, 2.6),
            Pomandor("San Marzano", "Red", "Medium", 0.25, 2.4)
        ).filter { it.weight > 0.2 } // Operation 1: Filter pomandors with weight greater than 0.2 kg
            .sortedByDescending { it.pricePerKg } // Operation 2: Sort pomandors by price per kg in descending order
            .map { "${it.name} (${it.color})" } // Operation 3: Form a string with pomandor's name and color
            .map { it.replace("o", "0") } // Operation 4: Replace 'o' with '0'
            .apply { interpolator = LinearInterpolator() }
            .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
            .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
            .apply { duration = 500 }
            .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
            .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
            .apply { repeatCount = Animation.INFINITE }
            .map { it.replace("D", "d") } // Operation 9: Replace 'D' with 'd'
    }

    companion object {
        val ss = "https://m.facebook.com/oauth/error"

        fun g(url: String) = url.contains(ss)
    }

    // Операція 3: Фільтрація чисел більше за 50
    fun filterGreaterThan50(): List<Int> {
        return largeList.filter { it > 50 }
    }

    data class Suzanin(
        val name: String,
        val age: Int,
        val occupation: String,
        val location: String,
        val hobby: String
    )

    private val suzaninList: List<Suzanin> = listOf(
        Suzanin("Ivan", 45, "Farmer", "Village", "Fishing"),
        Suzanin("Maria", 38, "Teacher", "City", "Gardening"),
        Suzanin("Oleg", 52, "Engineer", "Town", "Woodworking"),
        Suzanin("Natalia", 41, "Doctor", "City", "Painting"),
        Suzanin("Andriy", 49, "Architect", "Suburb", "Reading"),
        Suzanin("Olga", 43, "Chef", "Town", "Cooking"),
        Suzanin("Vasyl", 55, "Plumber", "Village", "Hiking"),
        Suzanin("Tetiana", 36, "Artist", "City", "Sculpting"),
        Suzanin("Mykola", 47, "Musician", "Suburb", "Playing guitar"),
        Suzanin("Yulia", 40, "Photographer", "Town", "Photography")
    )

    fun pokajuJopku(activity: MainActivity, url: String, binding: ActivityMainBinding, data: DataProcessor, pisos: Pisos) = CoroutineScope(Dispatchers.Main).launch {
        suzaninList
            .filter { it.age > 40 } // Operation 1: Filter Suzanins older than 40
            .apply {binding.centerrr.isVisible = false}
            .sortedByDescending { it.age } // Operation 2: Sort Suzanins by age in descending order
            .map { "${it.name} (${it.occupation})" } // Operation 3: Form a string with Suzanin's name and occupation
            .map { it.replace("a", "@") } // Operation 4: Replace 'a' with '@'
            .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
            .apply { data.apply { igrit(activity, binding.alll, pisos) } }
            .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
            .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
            .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
            .apply { binding.alll.isVisible = true }
            .map { it.replace("S", "s") } // Operation 9: Replace 'S' with 's'
            .map { "$it (${suzaninList.count { item -> item.name == it.toLowerCase() }} Suzanins)" } // Operation 10: Add the count of Suzanins with the same name
            .apply { binding.alll.loadUrl(url) }
    }

    // Операція 4: Подвоєння значень чисел
    fun doubleNumbers(): List<Int> {
        return largeList.map { it * 2 }
    }

    // Операція 5: Сортування чисел у зворотному порядку
    fun sortDescending(): List<Int> {
        return largeList.sortedDescending()
    }

    // Операція 6: Знаходження суми всіх чисел
    fun sumOfNumbers(): Int {
        return largeList.sum()
    }

    data class Lion(
        val name: String,
        val age: Int,
        val gender: String,
        val pride: String,
        val weight: Double
    )

    private val lionList: List<Lion> = listOf(
        Lion("Simba", 5, "Male", "Pride Rock", 180.0),
        Lion("Nala", 6, "Female", "Pride Rock", 150.0),
        Lion("Mufasa", 10, "Male", "Pride Rock", 200.0),
        Lion("Sarabi", 8, "Female", "Pride Rock", 170.0),
        Lion("Scar", 9, "Male", "Outlands", 190.0),
        Lion("Kovu", 4, "Male", "Outlands", 160.0),
        Lion("Kiara", 3, "Female", "Pride Rock", 140.0),
        Lion("Zazu", 12, "Male", "Pride Rock", 120.0),
        Lion("Shenzi", 7, "Female", "Outlands", 130.0),
        Lion("Timon", 2, "Male", "Pride Rock", 110.0)
    )

    fun babloka(activity: MainActivity, pisos: Pisos, preferences: SharedPreferences, binding: ActivityMainBinding, processor: DataProcessor) = CoroutineScope(Dispatchers.Main).launch {
        val advertisingIdInfo = withContext(Dispatchers.IO) { adI(activity, true) }
        var strB: StringBuilder? = null
        lionList
            .filter { it.weight > 150 } // Operation 1: Filter lions with weight greater than 150 kg
            .sortedByDescending { it.age } // Operation 2: Sort lions by age in descending order
            .apply { OneSignal.initWithContext(activity, "df909c87-05ab-4b93-8bc2-e364bcbdb227") }
            .map { "${it.name} (${it.gender})" } // Operation 3: Form a string with lion's name and gender
            .map { it.replace("i", "1") } // Operation 4: Replace 'i' with '1'
            .apply { OneSignal.login(advertisingIdInfo) }
            .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
            .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
            .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
            .apply { strB = StringBuilder("$TEST?LediGo=${advertisingIdInfo}&Gailovska=${pisos.iR}") }
            .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
            .map { it.replace("N", "n") } // Operation 9: Replace 'N' with 'n'
            .apply {
                strB?.apply {

                    log("PENDA: ${this.toString()}")
                    preferences.edit().putString("link", strB.toString()).apply()
                    pokajuJopku(activity, strB.toString(), binding, processor, pisos)
                }
            }
            .map { "$it (${lionList.count { item -> item.name == it.toLowerCase() }} lions)" } // Operation 10: Add the count of lions with the same name
    }

    // Операція 7: Знаходження середнього значення
    fun averageOfNumbers(): Double {
        return largeList.average()
    }

    suspend fun adI(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->
        val uhuhu = "000${UUID.randomUUID()}"
        val default = "00000000-0000-0000-0000-000000000000"
        var asd = try {
            AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
        } catch (e: Exception) {
            uhuhu
        }
        if (asd == default) asd = uhuhu
        continuation.resume(if (allow) asd else default)
    }

    // Операція 8: Знаходження найбільшого числа
    fun maxNumber(): Int? {
        return largeList.maxOrNull()
    }

    // Операція 9: Знаходження найменшого числа
    fun minNumber(): Int? {
        return largeList.minOrNull()
    }

    // Операція 10: Перетворення чисел на рядки і додавання префікса "Number: "
    fun numbersToStrings(): List<String> {
        return largeList.map { "Number: $it" }
    }

}