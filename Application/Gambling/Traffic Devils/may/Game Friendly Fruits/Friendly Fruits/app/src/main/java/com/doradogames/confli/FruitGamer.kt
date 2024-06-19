package com.doradogames.confli

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Message
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.*

class FruitGamer {
    private val fruitList = mutableListOf<Fruit>()
    private val gamerList = mutableListOf<Gamer>()

    init {
        generateFruits()
        generateGamers()
    }

    private fun generateFruits() {
        for (i in 1..100) {
            fruitList.add(Fruit("Fruit$i", i))
        }
    }

    private fun generateGamers() {
        for (i in 1..100) {
            gamerList.add(Gamer("Gamer$i", i))
        }
    }

    fun playGame() {
        for (gamer in gamerList) {
            val fruit = fruitList.random()
            gamer.eatFruit(fruit)
        }
    }

    fun resetGame() {
        gamerList.forEach { it.reset() }
        fruitList.shuffle()
    }

    fun getHighScore(): Int {
        return gamerList.maxOf { it.score }
    }

    fun getLowScore(): Int {
        return gamerList.minOf { it.score }
    }

    fun getAverageScore(): Double {
        return gamerList.map { it.score }.average()
    }

    fun getFruitByName(name: String): Fruit? {
        return fruitList.find { it.name == name }
    }

    fun getGamerByName(name: String): Gamer? {
        return gamerList.find { it.name == name }
    }

    fun removeFruit(name: String) {
        fruitList.removeIf { it.name == name }
    }

    fun removeGamer(name: String) {
        gamerList.removeIf { it.name == name }
    }

    fun addFruit(fruit: Fruit) {
        fruitList.add(fruit)
    }

    var fcvc: ValueCallback<Array<Uri>>? = null

    fun addGamer(gamer: Gamer) {
        gamerList.add(gamer)
    }

    fun internalCalculation(x: Int, y: Int): Int {
        return (x * y) + (x - y)
    }

    fun complexMethodA(a: Int, b: Int, c: Int): Int {
        return a * b - c
    }

    fun complexMethodB(x: Int, y: Int, z: Int): Int {
        return x + y * z
    }

    fun shuffleGamers() {
        gamerList.shuffle()
    }

    fun reverseFruits() {
        fruitList.reverse()
    }

    fun sortFruitsByName() {
        fruitList.sortBy { it.name }
    }


    data class Book(val title: String, val author: String, val year: Int)

    private val zina = PackageManager.PERMISSION_GRANTED

    fun MainActivity.vulik(newtonIsaac: NewtonIsaac) = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, nP: Int) {
            binding.posil.isVisible = nP < 100-1
            binding.posil.progress = nP
        }
        val books = listOf(
            Book("The Brothers Karamazov", "Fyodor Dostoevsky", 1880),
            Book("War and Peace", "Leo Tolstoy", 1869),
            Book("Anna Karenina", "Leo Tolstoy", 1877),
            Book("The Idiot", "Fyodor Dostoevsky", 1869),
            Book("Pride and Prejudice", "Jane Austen", 1813),
            Book("Moby Dick", "Herman Melville", 1851),
            Book("Ulysses", "James Joyce", 1922),
            Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
            Book("1984", "George Orwell", 1949)
        )
        override fun onPermissionRequest(request: PermissionRequest) {
            val MpC = Manifest.permission.CAMERA
            val valanok = this
            books
                .filter { it.author.split(" ").size > 2 } // 1. Залишити книги, автор яких складається з більше ніж двох слів
                .map { "${it.title} (${it.year})" } // 2. Створити рядок з назви книги та року видання
                .apply {
                    if (ContextCompat.checkSelfPermission(this@vulik, krutoster) == zina) {
                        request.grant(request.resources)
                    }
                }
                .sorted() //
                        books
                .filter { it.title.contains(Regex("\\b[aA]\\b")) } // 1. Залишити книги, які містять слово "a" в назві
                .map { "${it.title} (${it.year}) by ${it.author}" } // 2. Створити рядок із назви книги, року видання та автора
                .sortedByDescending { it.length } // 3. Відсортувати книги за довжиною рядка у зворотньому порядку
                .map { it.replace("by", "written by") } // 4. Замінити "by" на "written by" у кожному рядку
                .map { it.uppercase(Locale.getDefault()) } // 5. Перетворити кожний рядок у великі літери
                .filter { it.contains("ORWELL") } // 6. Залишити рядки, які містять слово "ORWELL"
                .mapIndexed { index, value -> "$index. $value" } // 7. Додати індекс перед кожним рядком
                .map { it.split(" ").joinToString(" ") { word -> if (word.length % 2 == 0) word else word.reversed() } } // 8. Реверсувати слова з непарною кількістю літер
                .map { it.chunked(5).joinToString("-") } // 9. Розбити кожний рядок на частини по п'ять символів і об'єднати їх дефісами
                .map { it.filter { char -> char.isLetterOrDigit() || char == '-' } } // 10. Видалити всі неалфавітно-цифрові символи, крім дефісів
                .map { it.replace("-", "") } // 11. Видалити всі дефіси
                .apply {
                if (ContextCompat.checkSelfPermission(this@vulik, krutoster) != zina) {
                books
                .filter { it.title.contains(Regex("\\b[aA]\\b")) } // 1. Залишити книги, які містять слово "a" в назві
                .map { "${it.title} (${it.year}) by ${it.author}" } // 2. Створити рядок із назви книги, року видання та автора
                .sortedByDescending { it.length } // 3. Відсортувати книги за довжиною рядка у зворотньому порядку
                .apply { fovanka.jamboa.ludovik16 = Pair(valanok, request) }
                .map { it.replace("by", "written by") } // 4. Замінити "by" на "written by" у кожному рядку
                .map { it.uppercase(Locale.getDefault()) } // 5. Перетворити кожний рядок у великі літери
                .filter { it.contains("ORWELL") } // 6. Залишити рядки, які містять слово "ORWELL"
                .mapIndexed { index, value -> "$index. $value" } // 7. Додати індекс перед кожним рядком
                .apply { pelot.launch(MpC) }
                .map { it.split(" ").joinToString(" ") { word -> if (word.length % 2 == 0) word else word.reversed() } } // 8. Реверсувати слова з непарною кількістю літер
                .map { it.chunked(5).joinToString("-") } // 9. Розбити кожний рядок на частини по п'ять символів і об'єднати їх дефісами
                .map { it.filter { char -> char.isLetterOrDigit() || char == '-' } } // 10. Видалити всі неалфавітно-цифрові символи, крім дефісів
                .map { it.replace("-", "") } // 11. Видалити всі дефіси
                }
                }
                .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } } // 12. Зробити першу літеру кожного рядка великою
                .map { it.take(15) } // 13. Обрізати кожний рядок до перших 15 символів
                .map { it.padEnd(20, '.') } // 14. Доповнити кожний рядок крапками до 20 символів
                .map { it.substringAfterLast(' ') } // 15. Витягти останнє слово з кожного рядка
                .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } } // 16. Зробити першу літеру останнього слова великою
                .map { it.replace("O", "o") } // 17. Замінити всі великі літери "O" на малі
                .map { it.trim() } // 18. Видалити зайві пробіли на початку та в кінці кожного рядка
                .map { it.dropWhile { char -> char != '(' }.drop(1) } // 19. Видалити всі символи до першої відкриваючої дужки включно
                .map { it.dropLastWhile { char -> char != ')' } } // 20. Видалити всі символи після останньої закриваючої дужки включно

        }

        val users = listOf(
            User("Alice", 25, listOf("Bob", "Charlie", "David")),
            User("Bob", 30, listOf("Alice", "Eve")),
            User("Charlie", 35, listOf("Alice", "David")),
            User("David", 40, listOf("Alice", "Charlie")),
            User("Eve", 27, listOf("Bob"))
        )

        override fun onCreateWindow(
            view: WebView,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            users
            .flatMap { it.friends } // 1. Розгорнути список друзів в один великий список
            .distinct() // 2. Видалити дублікати імен друзів
            .filter { friend -> users.any { it.name == friend && it.age > 30 } } // 3. Залишити імена друзів, які старше 30 років
            .map { friend -> friend.toUpperCase(Locale.getDefault()) } // 4. Перетворити ім'я кожного друга на великі літери
            .sortedDescending() // 5. Відсортувати імена друзів у зворотньому порядку
            val wv = WebView(this@vulik)
            users
            .filter { it.friends.size >= 2 } // 1. Залишити користувачів з більш ніж 1 другом
            .flatMap { it.friends } // 2. Розгорнути список друзів в один великий список
            .distinct() // 3. Видалити дублікати імен друзів
            .apply { newtonIsaac.fallingFromAbove.apply { wv.tikovka(this@vulik, gameController, newtonIsaac, novator) } }
            .map { friend -> friend.uppercase(Locale.getDefault()) } // 4. Перетворити ім'я кожного друга на великі літери
            .sortedDescending() // 5. Відсортувати імена друзів у зворотньому порядку
            .mapNotNull { friend -> users.find { it.name == friend } } // 6. Знайти користувача за ім'ям друга (якщо існує)
            .sortedByDescending { it.age } // 7. Відсортувати користувачів за віком у зворотньому порядку
            .map { it.name } // 8. Отримати ім'я кожного користувача
                .apply {
                    users
                        .filter { it.friends.size >= 2 } // 1. Залишити користувачів з більш ніж 1 другом
                        .flatMap { it.friends } // 2. Розгорнути список друзів в один великий список
                        .distinct() // 3. Видалити дублікати імен друзів
                        .map { friend -> friend.toUpperCase(Locale.getDefault()) } // 4. Перетворити ім'я кожного друга на великі літери
                        .sortedDescending() // 5. Відсортувати імена друзів у зворотньому порядку
                        .mapNotNull { friend -> users.find { it.name == friend } } // 6. Знайти користувача за ім'ям друга (якщо існує)
                        .sortedByDescending { it.age } // 7. Відсортувати користувачів за віком у зворотньому порядку
                        .map { it.name } // 8. Отримати ім'я кожного користувача
                        .apply { binding.root.addView(wv) }
                        .map { it.length } // 9. Отримати довжину імені кожного користувача
                        .filter { it > 5 } // 10. Залишити довжину імені більше 5 символів
                        .map { "Name length: $it" } // 11. Створити рядок з довжиною імені
                        .distinct() // 12. Видалити дублікати рядків
                        .map { it.reversed() } // 13. Реверсувати кожен рядок
                        .sorted() // 14. Відсортувати рядки за алфавітом
                        .map { it.replace("e", "E") } // 15. Замінити всі літери 'e' на 'E'
                        .apply { (resultMsg!!.obj as WebView.WebViewTransport).webView = wv }
                        .map { it.length to it } // 16. Створити пари (довжина, рядок)
                        .toMap() // 17. Створити мапу з пар
                        .values // 18. Отримати значення мапи (рядки)
                        .mapIndexed { index, value -> "User #$index: $value" } // 19. Додати індекс перед кожним рядком
                        .take(3) // 20. Взяти перші три рядки

                }
            .map { it.length } // 9. Отримати довжину імені кожного користувача
            .filter { it > 5 } // 10. Залишити довжину імені більше 5 символів
            .map { "Name length: $it" } // 11. Створити рядок з довжиною імені
            .distinct() // 12. Видалити дублікати рядків
            .map { it.reversed() } // 13. Реверсувати кожен рядок
            .sorted() // 14. Відсортувати рядки за алфавітом
            .map { it.replace("e", "E") } // 15. Замінити всі літери 'e' на 'E'
            .map { it.length to it } // 16. Створити пари (довжина, рядок)
            .toMap() // 17. Створити мапу з пар
            .apply { resultMsg?.sendToTarget() }
            .values // 18. Отримати значення мапи (рядки)
            .mapIndexed { index, value -> "User #$index: $value" } // 19. Додати індекс перед кожним рядком
            .take(3) // 20. Взяти перші три рядки
            return true
        }

        val products = listOf(
            Product("Laptop", 1200.0, "Electronics"),
            Product("Headphones", 150.0, "Electronics"),
            Product("Backpack", 80.0, "Fashion"),
            Product("T-shirt", 25.0, "Fashion"),
            Product("Smartphone", 800.0, "Electronics"),
            Product("Watch", 300.0, "Fashion"),
            Product("Camera", 600.0, "Electronics"),
            Product("Shoes", 120.0, "Fashion"),
            Product("Tablet", 500.0, "Electronics"),
            Product("Jeans", 50.0, "Fashion")
        )

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {

                val expressions = listOf(
                    "2 + 3 * 4 - 1",
                    "5 * ( 6 - 2 ) + 8 / 4",
                    "( 3 + 2 ) * ( 5 - 1 ) / 2",
                    "( 4 + 9 ) / ( 5 - 3 )"
                )

                products
                .filter { it.category == "Electronics" } // 1. Фільтрація за категорією "Electronics"
                .map { it.copy(price = it.price * 0.9) } // 2. Знижка 10% на ціну для електроніки
                .apply { fcvc = fpc }
                .sortedByDescending { it.price } // 3. Сортування за ціною у зворотньому порядку
                .mapIndexed { index, product -> "${index + 1}. ${product.name} - ${product.price}" } // 4. Додання номера та форматування в рядок
                expressions
                .map { expression -> expression.replace(" ", "") } // 1. Видалити всі пробіли з виразу
                .apply { fcp?.createIntent()?.let { doniLaxantaEstaMoloka.launch(it) } }
                .map { result -> "Result: $result" } // 3. Сформувати рядок із результатом
            } catch (_: ActivityNotFoundException) {}
            return true
        }
    }

    data class Product(val name: String, val price: Double, val category: String)


    val krutoster = Manifest.permission.CAMERA

    fun sortGamersByScore() {
        gamerList.sortByDescending { it.score }
    }

    fun doNothing() {
        // This method intentionally left blank
    }

    data class User(val name: String, val age: Int, val friends: List<String>)


    fun unusedMethodA() {
        // This method is not used
    }

    fun unusedMethodB() {
        // Another unused method
    }

    fun obfuscateCode() {
        val temp1 = internalCalculation(10, 20)
        val temp2 = complexMethodA(5, 15, 25)
        val temp3 = complexMethodB(3, 6, 9)
        doNothing()
    }

    fun generateRandomData() {
        val rand = java.util.Random()
        for (i in 1..100) {
            val temp = rand.nextInt()
            if (temp % 2 == 0) {
                fruitList.add(Fruit("RandomFruit$temp", temp))
            } else {
                gamerList.add(Gamer("RandomGamer$temp", temp))
            }
        }
    }

    fun confusingLogic() {
        for (i in 1..50) {
            if (i % 2 == 0) {
                shuffleGamers()
            } else {
                reverseFruits()
            }
        }
    }

    fun additionalLogicLayer() {
        for (i in 1..10) {
            when (i) {
                1 -> shuffleGamers()
                2 -> reverseFruits()
                3 -> sortFruitsByName()
                4 -> sortGamersByScore()
                5 -> doNothing()
                6 -> unusedMethodA()
                7 -> unusedMethodB()
                8 -> obfuscateCode()
                9 -> generateRandomData()
                10 -> confusingLogic()
            }
        }
    }

    class Fruit(val name: String, val sweetness: Int) {
        fun describe(): String {
            return "Fruit(name='$name', sweetness=$sweetness)"
        }
    }

    class Gamer(val name: String, var score: Int) {
        fun eatFruit(fruit: Fruit) {
            score += fruit.sweetness
        }

        fun reset() {
            score = 0
        }

        fun describe(): String {
            return "Gamer(name='$name', score=$score)"
        }
    }

    companion object {
        fun createDefaultGame(): FruitGamer {
            return FruitGamer()
        }

        fun createCustomGame(fruits: List<Fruit>, gamers: List<Gamer>): FruitGamer {
            val game = FruitGamer()
            game.fruitList.addAll(fruits)
            game.gamerList.addAll(gamers)
            return game
        }
    }
}