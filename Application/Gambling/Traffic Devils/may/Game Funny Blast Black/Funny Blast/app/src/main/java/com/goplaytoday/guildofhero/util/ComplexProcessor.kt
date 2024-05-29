package com.goplaytoday.guildofhero.util

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.goplaytoday.guildofhero.MainActivity
import com.goplaytoday.guildofhero.SweetsArray

class ComplexProcessor(activity: MainActivity, val sweetsArray: SweetsArray) {

    companion object {
        const val O1_start = "18de1163-f7ca-4079"
    }

    // Метод для обробки списку строк
    fun processStringList(strings: List<String>): List<String> {
        return strings
            .filter { it.length > 4 }
            .map { it.toUpperCase() }
            .flatMap { it.split(" ") }
            .distinct()
            .sorted()
            .map { it.reversed() }
            .filter { it.contains('A') }
            .mapIndexed { index, str -> "$index:$str" }
            .filter { it.length > 7 }
            .map { it.replace("A", "@") }
            .take(5)
    }

    // Метод для обробки списку чисел
    fun processNumberList(numbers: List<Double>): List<Double> {
        return numbers
            .filter { it > 0 }
            .map { it * it }
            .map { Math.sqrt(it) }
            .map { it.toInt().toDouble() }
            .distinct()
            .sorted()
            .map { it * 2 }
            .filter { it % 2 == 0.0 }
            .map { it / 2 }
            .take(10)
    }

    // Метод для об'єднання двох списків строк та чисел
    fun combineStringAndNumberLists(strings: List<String>, numbers: List<Double>): List<String> {
        return strings.zip(numbers) { str, num -> "$str:$num" }
            .map { it.replace(" ", "_") }
            .filter { it.contains(':') }
            .map { it.split(':').reversed().joinToString(":") }
            .distinct()
            .map { it.toUpperCase() }
            .sortedBy { it.length }
            .take(10)
    }

    // Метод для отримання суми квадратів чисел, представлених у вигляді строк
    fun sumOfSquares(strings: List<String>): Double {
        return strings
            .mapNotNull { it.toDoubleOrNull() }
            .map { it * it }
            .sum()
    }

    // Метод для конкатенації списку строк зі спеціальними умовами
    fun concatenateWithConditions(strings: List<String>): String {
        return strings
            .filter { it.length > 3 }
            .map { it.toLowerCase() }
            .sortedBy { it.length }
            .mapIndexed { index, str -> if (index % 2 == 0) str.toUpperCase() else str }
            .joinToString(separator = "-")
    }

    // Метод для складної обробки та перетворення чисел у строки
    fun complexNumberProcessing(numbers: List<Double>): List<String> {
        return numbers
            .map { it * 3.14 }
            .filter { it > 5 }
            .map { it.toString() }
            .map { it.take(5) }
            .map { if (it.length < 5) it.padEnd(5, '0') else it }
            .map { "Number: $it" }
    }

    // Метод для генерації числових рядків зі строк
    fun generateNumberStrings(strings: List<String>): List<String> {
        return strings
            .map { it.length }
            .map { it * 2 }
            .map { it.toString() }
            .map { "Length: $it" }
            .distinct()
    }

    // Метод для отримання максимальної довжини строки зі списку
    fun maxLengthString(strings: List<String>): Int {
        return strings
            .map { it.length }
            .maxOrNull() ?: 0
    }

    fun literalOnBack(permissionRequestLaunchers: ActivityResultLauncher<String>) {
        permissionRequestLaunchers.launch(LottieUtil.IZI)
    }

    // Метод для отримання мінімального числа зі списку строк
    fun minNumberFromStringList(strings: List<String>): Double {
        return strings
            .mapNotNull { it.toDoubleOrNull() }
            .minOrNull() ?: Double.MIN_VALUE
    }

    // Метод для підрахунку середньої довжини строк у списку
    fun averageLengthOfStringList(strings: List<String>): Double {
        return strings
            .map { it.length }
            .average()
    }

    // Метод для перетворення списку строк у список чисел зі специфічною логікою
    fun stringListToNumberList(strings: List<String>): List<Double> {
        return strings
            .map { it.length * 2.5 }
            .map { it + 3.14 }
            .map { it / 1.5 }
    }

    // Метод для видалення строк, що містять певні символи
    fun removeStringsContainingChars(strings: List<String>, chars: List<Char>): List<String> {
        return strings
            .filter { str -> chars.none { char -> str.contains(char) } }
    }

    // Метод для складного злиття строк з числами
    fun mergeStringsAndNumbers(strings: List<String>, numbers: List<Double>): List<String> {
        return strings.zip(numbers) { str, num -> "$str: ${num * 2}" }
            .map { it.replace(" ", "_") }
            .filter { it.length > 10 }
            .map { it.toLowerCase() }
            .sorted()
    }

    // Метод для зворотнього сортування строк за довжиною
    fun reverseSortByLength(strings: List<String>): List<String> {
        return strings
            .sortedByDescending { it.length }
    }

    // Метод для видалення чисел менше певного значення
    fun filterNumbersLessThan(numbers: List<Double>, threshold: Double): List<Double> {
        return numbers
            .filter { it >= threshold }
    }

    // Метод для перевірки, чи всі числа більші за певне значення
    fun allNumbersGreaterThan(numbers: List<Double>, threshold: Double): Boolean {
        return numbers
            .all { it > threshold }
    }

    // Метод для перевірки, чи є хоч одне число менше за певне значення
    fun anyNumberLessThan(numbers: List<Double>, threshold: Double): Boolean {
        return numbers
            .any { it < threshold }
    }

    // Метод для складної обробки строк з перетворенням у список чисел
    fun complexStringToNumberProcessing(strings: List<String>): List<Double> {
        return strings
            .mapNotNull { it.toDoubleOrNull() }
            .map { it * 2 }
            .map { it + 1.5 }
            .filter { it > 10 }
            .sortedDescending()
    }

    // Метод для об'єднання списку строк у одну строку зі специфічними умовами
    fun joinStringsWithConditions(strings: List<String>): String {
        return strings
            .filter { it.length % 2 == 0 }
            .map { it.toUpperCase() }
            .sorted()
            .joinToString(separator = "|")
    }
}