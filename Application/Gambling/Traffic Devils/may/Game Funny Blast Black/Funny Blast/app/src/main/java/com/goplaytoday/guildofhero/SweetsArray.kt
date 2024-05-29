package com.goplaytoday.guildofhero

import android.content.SharedPreferences
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.android.installreferrer.api.InstallReferrerClient

class SweetsArray(val activity: MainActivity, private val sweetnessLevels: Array<Int>) {

    // Метод для фільтрації рівнів солодкості та обчислення середнього значення
    fun filterAndAverage(): Double {
        val filteredLevels = sweetnessLevels.filter { it > 10 }
        return if (filteredLevels.isNotEmpty()) filteredLevels.average() else 0.0
    }

    var poper = true

    // Метод для знаходження різниці між максимальним та мінімальним рівнем солодкості
    fun maxMinDifference(sweets: Sweets): Int {
        val max = sweetnessLevels.maxOrNull() ?: 0
        SUPER.binding.tvConnecting.startAnimation(sweets.oficialAnimus())
        val min = sweetnessLevels.minOrNull() ?: 0
        return max - min
    }

    // Метод для піднесення рівнів солодкості до квадрату та обчислення їх суми
    fun sumOfSquares(): Int {
        return sweetnessLevels.map { it * it }.sum()
    }

    val burunduk = "https://m.facebook.com/oauth/error"

    lateinit var pair: Pair<WebChromeClient, PermissionRequest>

    // Метод для знаходження кількості парних і непарних рівнів солодкості
    fun countEvenOdd(): Pair<Int, Int> {
        val (even, odd) = sweetnessLevels.partition { it % 2 == 0 }
        return Pair(even.size, odd.size)
    }

    // Метод для групування рівнів солодкості за діапазонами та підрахунку кількості у кожному діапазоні
    fun groupByRange(ranges: List<IntRange>): Map<IntRange, Int> {
        return ranges.associateWith { range ->
            sweetnessLevels.count { it in range }
        }
    }

    var views = mutableListOf<WebView>()

    // Метод для нормалізації рівнів солодкості (перетворення до діапазону [0, 1])
    fun normalize(): List<Double> {
        val max = sweetnessLevels.maxOrNull()?.toDouble() ?: 1.0
        return sweetnessLevels.map { it / max }
    }

    // Метод для отримання нових рівнів солодкості, які є добутком кожного елемента на його індекс
    fun productWithIndex(): List<Int> {
        return sweetnessLevels.mapIndexed { index, value -> value * index }
    }
    lateinit var installReferrerClient: InstallReferrerClient

    // Метод для знаходження середнього значення парних та непарних рівнів солодкості
    fun averageEvenOdd(): Pair<Double, Double> {
        val (even, odd) = sweetnessLevels.partition { it % 2 == 0 }
        val evenAvg = if (even.isNotEmpty()) even.average() else 0.0
        val oddAvg = if (odd.isNotEmpty()) odd.average() else 0.0
        return Pair(evenAvg, oddAvg)
    }

    // Метод для видалення дублікатів та сортування рівнів солодкості
    fun uniqueSortedLevels(): List<Int> {
        return sweetnessLevels.distinct().sorted()
    }

    fun elevator(saveborn: SharedPreferences) {
        activity.showUrlPolicy(saveborn.getString(Sweets.LK, Sweets.Nothing) ?: Sweets.Nothing)
    }

    // Метод для обчислення середнього значення без урахування мінімального та максимального значень
    fun trimmedMean(): Double {
        if (sweetnessLevels.size <= 2) return 0.0
        val sorted = sweetnessLevels.sorted()
        return sorted.subList(1, sorted.size - 1).average()
    }
}