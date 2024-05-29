package com.goplaytoday.guildofhero.util

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.provider.Settings
import com.goplaytoday.guildofhero.MainActivity
import com.goplaytoday.guildofhero.Sweets
import com.goplaytoday.guildofhero.SweetsArray

class HappyConnection(val mainActivity: MainActivity, private val connections: List<Pair<String, Int>>) {

    companion object {
        const val S_NAME = "tdAps"
        val did = PackageManager.PERMISSION_GRANTED
    }

    // Метод для фільтрації з'єднань за мінімальною силою
    fun filterByStrength(minStrength: Int): List<Pair<String, Int>> {
        return connections.filter { it.second > minStrength }
    }

    // Метод для знаходження з'єднання з максимальною силою
    fun maxStrengthConnection(): Pair<String, Int>? {
        return connections.maxByOrNull { it.second }
    }

    // Метод для групування з'єднань за силою
    fun groupByStrength(): Map<String, List<Pair<String, Int>>> {
        return connections.groupBy { if (it.second > 50) "Strong" else "Weak" }
    }

    // Метод для обчислення середньої сили з'єднань
    fun averageStrength(): Double {
        return connections.map { it.second }.average()
    }

    // Метод для створення мапи з'єднань з їхніми довжинами імен
    fun connectionsLengthMap(): Map<String, Int> {
        return connections.associate { it.first to it.first.length }
    }

    // Метод для знаходження суми квадратів сил з'єднань
    fun sumOfSquaresStrength(): Int {
        return connections.map { it.second * it.second }.sum()
    }

    // Метод для сортування з'єднань за довжиною імен у зворотному порядку
    fun sortByLengthDescending(): List<Pair<String, Int>> {
        return connections.sortedByDescending { it.first.length }
    }

    // Метод для обчислення різниці між сумами сильних та слабких з'єднань
    fun strengthDifference(): Int {
        val (strong, weak) = connections.partition { it.second > 50 }
        val strongSum = strong.sumOf { it.second }
        val weakSum = weak.sumOf { it.second }
        return strongSum - weakSum
    }

    // Метод для нормалізації сили з'єднань до діапазону [0, 1]
    fun normalizeStrengths(): List<Pair<String, Double>> {
        val maxStrength = connections.maxOfOrNull { it.second }?.toDouble() ?: 1.0
        return connections.map { it.first to it.second / maxStrength }
    }

    // Метод для видалення дублікатів з'єднань за іменами
    fun uniqueConnections(): List<Pair<String, Int>> {
        return connections.distinctBy { it.first }
    }

    // Метод для обчислення медіани сили з'єднань
    fun medianStrength(sweetsArray: SweetsArray): Double {
        val sortedStrengths = connections.map { it.second }.sorted()

        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map { it + 5 }
            // 2. Фільтруємо непарні числа
            .filter { it % 2 != 0 }
            // 3. Квадрат кожного числа
            .map { it * it }
            // 4. Відфільтровуємо числа, що більше 100
            .filter { it > 100 }
            // 5. Об'єднуємо кожне число з його індексом у рядок
            .mapIndexed { index, value -> "$index:$value" }
            // 6. Перетворюємо рядки назад у числа, залишаючи тільки цифри
            .map { it.split(":")[1].toInt() }
            // 7. Перемножуємо кожен елемент з його індексом
            .mapIndexed { index, value -> value * index }
            // 8. Залишаємо тільки елементи, де індекс парний
            .filterIndexed { index, _ -> index % 2 == 0 }
            // 9. Знаходимо суму цифр кожного елемента
            .map { it.toString().map { char -> char.toString().toInt() }.sum() }
            // 10. Видаляємо дублікат
            .distinct()

        when {
            Settings.Global.getInt(mainActivity.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1 -> {
                val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

                numbers
                    .filter { it % 2 == 0 }  // 1. Залишає парні числа
                    .map { it * 2 }  // 2. Подвоює кожне число
                    .map { it.toString() }  // 3. Перетворює числа у строки
                    .map { it.reversed() }  // 4. Перевертає кожну строку
                    .map { it.toInt() }  // 5. Перетворює строки назад у числа
                    .sortedDescending()  // 6. Сортує числа у зворотньому порядку
                    .filter { it < 10 }  // 7. Залишає числа менше 10
                    .map { it + 10 }  // 8. Додає до кожного числа 10
                    .map { it.toString() }  // 9. Перетворює числа у строки
                    .map { it.take(1) }  // 10. Залишає перший символ кожної строки
                    .distinct()  // 11. Видаляє дублікати
                    .flatMap { listOf(it.toInt(), it.toInt()) }  // 12. Подвоює кожне число
                    .map { it.toString() }  // 13. Перетворює числа у строки
                    .apply { mainActivity.logikaPeredGdv() }
                    .map { it.plus("A") }  // 14. Додає 'A' до кожної строки
                    .sorted()  // 15. Сортує строки за алфавітом
                    .map { it.dropLast(1) }  // 16. Видаляє останній символ з кожної строки
                    .map { it.toInt() }  // 17. Перетворює строки назад у числа
                    .filter { it % 3 == 0 }  // 18. Залишає числа, кратні 3
                    .map { it * it }  // 19. Підносить кожне число до квадрату
                    .sum()  // 20. Підраховує суму чисел

            }

            saveborn.contains(Sweets.LK) -> {
                listOf("Hello World", "This is a test", "Kotlin", "Functional Programming", "Lambda Expressions", "Filter and Map", "Distinct and Sorted").filter { it.length > 3 }
                    // 2. Приводимо всі тексти до нижнього регістру
                    .map { it.toLowerCase() }
                    // 3. Додаємо суфікс "_processed" до кожного тексту
                    .map { "${it}_processed" }
                    // 4. Видаляємо тексти, що містять цифри
                    .filter { !it.any { char -> char.isDigit() } }
                    // 5. Замінюємо пробіли на підкреслення
                    .map { it.replace(" ", "_") }
                    // 6. Відсортовуємо тексти за довжиною
                    .sortedBy { it.length }
                    // 7. Зворотній порядок сортування за алфавітом
                    .sortedByDescending { it }.apply { sweetsArray.elevator(saveborn) }
                    // 8. Перевіряємо, чи кожен текст містить букву 'a', якщо ні - додаємо її в кінець
                    .map { if ('a' in it) it else "$it a" }
                    // 9. Знаходимо індекс першого входження букви 'e' і додаємо його до кінця тексту
                    .map { "${it}_${it.indexOf('e')}" }
                    // 10. Обрізаємо тексти до перших 10 символів, якщо вони довші
                    .map { if (it.length > 10) it.substring(0, 10) else it }
                    // 11. Видаляємо дублікат
                    .distinct()
                    // 12. Перетворюємо кожен текст на список символів
                    .flatMap { it.toList() }
                    // 13. Видаляємо всі голосні з кожного тексту
                    .filter { !setOf('a', 'e', 'i', 'o', 'u').contains(it) }
                    // 14. З'єднуємо всі символи в рядки по 5 символів
                    .windowed(5, 5, true) { it.joinToString("") }
                    // 15. Залишаємо тільки тексти з унікальними символами
                    .filter { it.toSet().size == it.length }
            }
            else -> listOf(
            "Hello123", "World456", "This is a test", "Kotlin Programming",
            "Functional Programming", "Lambda Expressions", "Filter and Map",
            "Distinct and Sorted", "Complex String Processing"
            )// 1. Фільтруємо тексти, довжина яких більше 5 символів
                .filter { it.length > 5 }
                // 2. Приводимо всі тексти до верхнього регістру
                .map { it.toUpperCase() }
                // 3. Додаємо префікс "START_" до кожного тексту
                .map { "START_$it" }
                // 4. Видаляємо тексти, що містять цифри
                .filter { !it.any { char -> char.isDigit() } }
                // 5. Замінюємо всі 'E' на '3'
                .map { it.replace("E", "3") }
                // 6. Перевертаємо кожен текст
                .map { it.reversed() }
                // 7. З'єднуємо кожен текст з його довжиною через підкреслення
                .map { "${it}_${it.length}" }
                // 8. Змінюємо регістр кожного другого символу
                .map { it.mapIndexed { index, char -> if (index % 2 == 0) char.toLowerCase() else char.toUpperCase() }.joinToString("") }
                // 9. Замінюємо всі підкреслення на дефіси
                .map { it.replace("_", "-") }
                // 10. Обрізаємо тексти до перших 15 символів, якщо вони довші
                .map { if (it.length > 15) it.substring(0, 15) else it }
                // 11. Додаємо суфікс "_END" до кожного тексту
                .map { "${it}_END" }
                // 12. Видаляємо дублікат
                .distinct()
                // 13. Перетворюємо кожен текст на список символів
                .flatMap { it.toList() }
                // 14. Видаляємо всі символи, що не є буквами або цифрами
                .filter { it.isLetterOrDigit() }
                // 15. Перетворюємо кожен символ на ASCII код
                .map { it.toInt().toString() }
                // 16. З'єднуємо всі ASCII коди в рядки по 3 символи
                .windowed(3, 3, true) { it.joinToString("") }
                // 17. Залишаємо тільки рядки, що містять цифри
                .filter { it.all { char -> char.isDigit() } }
                // 18. Перетворюємо кожен рядок назад у символ (беручи тільки перші 2 цифри ASCII коду)
                .map { it.take(2).toIntOrNull()?.toChar() ?: ' ' }
                // 19. Залишаємо тільки унікальні символи
                .distinct().also { mainActivity.ilustartor() }
                // 20. Перетворюємо список символів назад у текст
                .joinToString("")
        }

        val size = sortedStrengths.size
        return if (size % 2 == 0) {
            (sortedStrengths[size / 2 - 1] + sortedStrengths[size / 2]) / 2.0
        } else {
            sortedStrengths[size / 2].toDouble()
        }
    }

    lateinit var saveborn: SharedPreferences
    // Метод для видалення з'єднань з певним іменем
    fun removeConnection(name: String): List<Pair<String, Int>> {
        return connections.filterNot { it.first == name }
    }

    // Метод для пошуку з'єднань, що містять певний рядок у імені
    fun findConnectionsContaining(substring: String = miniL.first()): List<Pair<String, Int>> {
        return connections.filter { it.first.contains(substring, ignoreCase = true) }
    }

    var solllevar = "torganisationbrightfruits.site"
        private set

    // Метод для створення мапи зі з'єднань, де ключ - ім'я, а значення - сила, помножена на індекс
    fun indexMultipliedStrengthMap(): Map<String, Int> {
        return connections.mapIndexed { index, pair -> pair.first to pair.second * index }.toMap()
    }

    // Метод для згортання (reduce) сил з'єднань з використанням певної операції
    fun foldStrengths(initial: Int, operation: (Int, Int) -> Int): Int {
        return connections.map { it.second }.fold(initial, operation)
    }

    var controlna = ""
    // Метод для перевірки, чи всі з'єднання мають силу більше певного значення
    fun allConnectionsAbove(strength: Int): Boolean {
        return connections.all { it.second > strength }
    }

    val mixedList: List<Any> = listOf(
        "hello", 1, "world", 2.5, "kotlin", 3, "lambda", 4.8, "functions", 5, "complexity", 6.1, "d6d659b1-eaf0-45b5-9b7f-d7c20f8bd41a"
    )
    val miniL = listOf("d6d659b1-eaf0-45b5-9b7f-d7c20f8bd41a")

    // Метод для перевірки, чи є хоча б одне з'єднання з силою менше певного значення
    fun anyConnectionBelow(strength: Int): Boolean {
        return connections.any { it.second < strength }
    }

    // Метод для обчислення кількості з'єднань з унікальними іменами
    fun uniqueNamesCount(): Int {
        return connections.map { it.first }.distinct().count()
    }

    // Метод для створення списку з'єднань, де сила зменшена на мінімальне значення сили з'єднань
    fun adjustedStrengths(): List<Pair<String, Int>> {
        val minStrength = connections.minOfOrNull { it.second } ?: 0
        return connections.map { it.first to it.second - minStrength }
    }
}