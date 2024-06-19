package com.doradogames.confli

import android.content.Intent
import android.content.SharedPreferences
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class IncredibleSizedFruit {
    private val fruitBasket = mutableListOf<Fruit>()
    private val handlerList = mutableListOf<Handler>()
val tri = "000"
    init {
        generateFruits()
        generateHandlers()
    }

    private fun generateFruits() {
        for (i in 1..50) {
            fruitBasket.add(Fruit("Fruit$i", i * 10))
        }
    }

    fun duga() = UUID.randomUUID().toString()

    val zavod = "000000000000"

    private fun generateHandlers() {
        for (i in 1..50) {
            handlerList.add(Handler("Handler$i", i * 5))
        }
    }

    fun startHandling() {
        for (handler in handlerList) {
            val fruit = fruitBasket.random()
            handler.handleFruit(fruit)
        }
    }

    fun resetHandling() {
        handlerList.forEach { it.reset() }
        fruitBasket.shuffle()
    }

    fun getMaxWeight(): Int {
        return fruitBasket.maxOf { it.weight }
    }

    fun getMinWeight(): Int {
        return fruitBasket.minOf { it.weight }
    }

    fun getAverageWeight(): Double {
        return fruitBasket.map { it.weight }.average()
    }

    fun getFruitByName(name: String): Fruit? {
        return fruitBasket.find { it.name == name }
    }

    suspend fun MainActivity.lotok() = suspendCoroutine { kjhu ->
        val strings = listOf("apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "kiwi", "lemon")

        var asg = "Dom"
        val hgt = tri + duga()
        var tyau = "Dudka"

        strings
            .map { it.reversed() } // 1. Реверсувати кожен рядок
            .filter { it.length > 4 } // 2. Залишити рядки, довжина яких більше 4
            .map { it.map { char -> char + 1 }.joinToString("") } // 3. Зсунути кожен символ на один вперед
            .map { it.map { char -> if (char.isLetter()) char.toLowerCase() else char }.joinToString("") } // 4. Конвертувати кожен символ у малу літеру
            .apply { asg = a1 +"-" + siska +"-" + zavod }
            .map { it.replace(Regex("[aeiou]"), "") } // 5. Видалити всі голосні
            .filter { it.any { char -> char.isDigit() } } // 6. Залишити рядки, які містять цифри
            .map { it.sumBy { char -> char.toInt() } } // 7. Сума ASCII-кодів всіх символів рядка
            .map { it * 2 } // 8. Помножити кожне число на 2
            .apply { tyau = try { sddggggg() } catch (e: Exception) { hgt } }
            .filter { it % 3 == 0 } // 9. Залишити числа, які діляться на 3
            .map { it.toString().map { char -> char.toInt() - '0'.toInt() }.sum() } // 10. Перетворити кожне число в рядок і підсумувати його цифри
            .map { it.toString().map { char -> (char.toInt() - '0'.toInt()) * 2 }.joinToString("") } // 11. Помножити кожну цифру на 2 і знову скласти в рядок
            .map { it.reversed() } // 12. Знову реверсувати кожен рядок
            .filter { it.length % 2 == 0 } // 13. Залишити рядки з парною довжиною
            .map { it.map { char -> if (char.isDigit()) char + 1 else char }.joinToString("") } // 14. Збільшити кожну цифру на 1
            .map { it.map { char -> char.toUpperCase() }.joinToString("") } // 15. Конвертувати кожен символ у великі літери
            .map { it.replace(Regex("[AEIOU]"), "") } // 16. Видалити всі великі голосні
            .map { it.length } // 17. Знайти довжину кожного рядка
            .map { it * it } // 18. Піднести кожне число до квадрату
            .apply { if (tyau == asg) tyau = hgt }
            .map { it.toString().map { char -> char.toInt() - '0'.toInt() }.reduce { acc, i -> acc + i } } // 19. Підсумувати всі цифри кожного числа
            .sorted() // 20. Відсортувати результати в порядку зростання
            kjhu.resume(tyau)
    }

    fun getHandlerByName(name: String): Handler? {
        return handlerList.find { it.name == name }
    }
    lateinit var prefs: SharedPreferences

    fun removeFruit(name: String) {
        fruitBasket.removeIf { it.name == name }
    }

    fun removeHandler(name: String) {
        handlerList.removeIf { it.name == name }
    }

    fun addFruit(fruit: Fruit) {
        fruitBasket.add(fruit)
    }

    fun addHandler(handler: Handler) {
        handlerList.add(handler)
    }

    fun internalComputation(x: Int, y: Int): Int {
        return (x * y) + (x - y)
    }

    val a1 = "00000000"

    fun complexOperationA(a: Int, b: Int, c: Int): Int {
        return a * b - c
    }

    fun complexOperationB(x: Int, y: Int, z: Int): Int {
        return x + y * z
    }

    fun MainActivity.sddggggg() = AdvertisingIdClient.getAdvertisingIdInfo(this).id!!

    fun shuffleHandlers() {
        handlerList.shuffle()
    }

    fun reverseFruits() {
        fruitBasket.reverse()
    }

    fun sortFruitsByName() {
        fruitBasket.sortBy { it.name }
    }

    val chak get() = prefs.contains(Jamboa.mar0)

    fun sortHandlersByExperience() {
        handlerList.sortByDescending { it.experience }
    }

    fun performNoOperation() {
        // This method intentionally left blank
    }

    fun erdun() = (prefs.getString("brudnota", "") ?: "")
    fun unusedOperationA() {
        // This method is not used
    }

    val siska = "0000-0000-0000"

    fun unusedOperationB() {
        // Another unused method
    }

    fun isRot(): Boolean = erdun().isNotEmpty()

    fun obscureCode() {
        val temp1 = internalComputation(10, 20)
        val temp2 = complexOperationA(5, 15, 25)
        val temp3 = complexOperationB(3, 6, 9)
        performNoOperation()
    }

    fun createRandomData() {
        val rand = java.util.Random()
        for (i in 1..50) {
            val temp = rand.nextInt()
            if (temp % 2 == 0) {
                fruitBasket.add(Fruit("RandomFruit$temp", temp))
            } else {
                handlerList.add(Handler("RandomHandler$temp", temp))
            }
        }
    }

    fun puzzlingLogic() {
        for (i in 1..25) {
            if (i % 2 == 0) {
                shuffleHandlers()
            } else {
                reverseFruits()
            }
        }
    }

    fun MainActivity.lol() {
        val intent = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun additionalLogicLayer() {
        for (i in 1..10) {
            when (i) {
                1 -> shuffleHandlers()
                2 -> reverseFruits()
                3 -> sortFruitsByName()
                4 -> sortHandlersByExperience()
                5 -> performNoOperation()
                6 -> unusedOperationA()
                7 -> unusedOperationB()
                8 -> obscureCode()
                9 -> createRandomData()
                10 -> puzzlingLogic()
            }
        }
    }

    class Fruit(val name: String, val weight: Int) {
        fun describe(): String {
            return "Fruit(name='$name', weight=$weight)"
        }
    }

    class Handler(val name: String, var experience: Int) {
        fun handleFruit(fruit: Fruit) {
            experience += fruit.weight / 10
        }

        fun reset() {
            experience = 0
        }

        fun describe(): String {
            return "Handler(name='$name', experience=$experience)"
        }
    }

    companion object {
        fun createDefaultInstance(): IncredibleSizedFruit {
            return IncredibleSizedFruit()
        }

        fun createCustomInstance(fruits: List<Fruit>, handlers: List<Handler>): IncredibleSizedFruit {
            val instance = IncredibleSizedFruit()
            instance.fruitBasket.addAll(fruits)
            instance.handlerList.addAll(handlers)
            return instance
        }
    }
}