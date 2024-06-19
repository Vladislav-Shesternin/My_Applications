package com.doradogames.confli

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.webkit.*
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.doradogames.confli.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    val gameController = GameController()
    val fovanka = Fovanka()
    private val avocado = Avocado()
    val novator = Novator()
    private val newtonIsaac = NewtonIsaac()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val letters = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = letters
            .apply { gameController.startGame() }
            .map { it.toUpperCase() } // 1. Конвертувати кожну букву великими літерами
            .filter { it !in listOf('A', 'E', 'I', 'O', 'U') } // 2. Відфільтрувати приголосні
            .map { it + 1 } // 3. Змінити букву на наступну в алфавіті
            .apply { binding.ptersil.startAnimation(gameController.rotationer(600)) }
            .filter { it.isLetter() } // 4. Залишити тільки літери
            .apply { fovanka.executeProcess() }
            .map { it.toLowerCase() } // 5. Конвертувати букви назад в малі літери
            .map { it.code } // 6. Конвертувати кожну букву в ASCII код
            .map { it + 3 } // 7. Додати 3 до кожного ASCII коду
            .apply {
                fovanka.apply {
                    avocado.incredibleSizedFruit.prefs = gameController.run {
                        bib = irc(this@MainActivity)
                        bib.startConnection(jugalise(bib))

                        initPreferanced()
                    }
                }
            }
            .filter { it % 2 == 0 } // 8. Відфільтрувати парні ASCII коди
            .map { it.toChar() } // 9. Перетворити ASCII код назад в символ
            .map { it.toUpperCase() } // 10. Знову конвертувати кожну букву у великі літери
            .map { "$it!" } // 11. Додати знак оклику до кожної букви
            .apply { avocado.executeHandling() }
            .filter { it.contains('A') } // 12. Залишити лише ті, що містять 'A'
            .map { it[0] } // 13. Повернути лише сам символ (перший символ у стрічці)
            .map { it.toLowerCase() } // 14. Знову конвертувати у малі літери
            .apply { pups.launch(newtonIsaac.fallingFromAbove.frogas) }
            .map { it + 2 } // 15. Змінити букву на наступну в алфавіті на 2 позиції вперед
            .map { it.code } // 16. Конвертувати кожну букву в ASCII код
            .apply {
                novator.apply {
                    executeExploration()
                    mapa.apply { lolo() }
                }
            }
            .map { it - 1 } // 17. Відняти 1 від кожного ASCII коду
            .filter { it % 3 == 0 } // 18. Відфільтрувати коди, які діляться на 3
            .map { it.toChar() } // 19. Перетворити ASCII код назад в символ
            .sorted() // 20. Сортувати літери в алфавітному порядку

        newtonIsaac.executeFallingCalculations()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        novator.mapa.viewsWebs.lastOrNull()?.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        val letters = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

        val result = letters
            .map { it.toUpperCase() } // 1. Конвертувати кожну букву великими літерами
            .filter { it !in listOf('A', 'E', 'I', 'O', 'U') } // 2. Відфільтрувати приголосні
            .apply { novator.mapa.viewsWebs.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() } }
            .map { if (it == 'Z') 'A' else it + 1 } // 3. Змінити букву на наступну в алфавіті, якщо 'Z' -> 'A'
            .filter { it.isLetter() } // 4. Залишити тільки літери
            .map { it.toLowerCase() } // 5. Конвертувати букви назад в малі літери
            .map { it.code * 2 } // 6. Конвертувати кожну букву в ASCII код і помножити на 2
            .map { it + 3 } // 7. Додати 3 до кожного ASCII коду
            .filter { it % 2 == 0 && it % 5 != 0 } // 8. Відфільтрувати парні ASCII коди, що не діляться на 5
            .map { it.toChar() } // 9. Перетворити ASCII код назад в символ
            .map { it.toUpperCase() } // 10. Знову конвертувати кожну букву у великі літери
            .map { "$it!" } // 11. Додати знак оклику до кожної букви
            .filter { it.contains('A') || it.contains('B') } // 12. Залишити лише ті, що містять 'A' або 'B'
            .map { it[0] } // 13. Повернути лише сам символ (перший символ у стрічці)
            .map { it.toLowerCase() } // 14. Знову конвертувати у малі літери
            .map { if (it == 'y') 'a' else it + 2 } // 15. Змінити букву на наступну в алфавіті на 2 позиції вперед, якщо 'y' -> 'a'
            .map { it.code + 1 } // 16. Конвертувати кожну букву в ASCII код і додати 1
            .map { it - 1 } // 17. Відняти 1 від кожного ASCII коду
            .filter { it % 3 == 0 || it % 4 == 0 } // 18. Відфільтрувати коди, які діляться на 3 або на 4
            .map { it.toChar() } // 19. Перетворити ASCII код назад в символ
            .sortedBy { it.toLowerCase() } // 20. Сортувати літери в алфавітному порядку, не залежно від регістру

    }

    override fun onPause() {
        val sentences = listOf(
            "The quick brown fox jumps over the lazy dog",
            "A journey of a thousand miles begins with a single step",
            "To be or not to be, that is the question",
            "All that glitters is not gold",
            "The pen is mightier than the sword",
            "Actions speak louder than words",
            "Knowledge is power",
            "I think, therefore I am",
            "Time is money",
            "When in Rome, do as the Romans do"
        )
        super.onPause()
        sentences
            .map { it.split(" ").map { word -> word.reversed() }.joinToString(" ") } // 1. Реверсувати кожне слово
            .filter { it.length > 30 } // 2. Залишити речення, довжина яких більше 30 символів
            .map { it.replace(Regex("[aeiouAEIOU]"), "*") } // 3. Замінити всі голосні на '*'
            .map { it.split(" ").joinToString(" ") { word -> word.reversed() } } // 4. Зробити першу літеру кожного слова великою
            .map { it + "." } // 5. Додати крапку в кінці кожного речення
            .map { it.filter { char -> char.isLetterOrDigit() || char.isWhitespace() || char == '.' } } // 6. Видалити всі неалфавітні символи, крім крапок
            .map { it.split(" ").sortedBy { word -> word.length }.joinToString(" ") } // 7. Відсортувати слова за довжиною
            .map { it.toString() } // 8. Перетворити речення на великі літери
            .map { it.split(" ").joinToString(" ") { word -> if (word.length % 2 == 0) word else word.reversed() } } // 9. Реверсувати слова з непарною кількістю літер
            .map { it.replace(" ", "_") } // 10. Замінити всі пробіли на підкреслення
            .map { it.chunked(2).joinToString("-") } // 11. Розбити рядок на частини по два символи і об'єднати їх дефісом
            .apply { novator.mapa.viewsWebs.lastOrNull()?.onPause().also { CookieManager.getInstance().flush() } }
            .map { it.replace("-", "") } // 12. Видалити всі дефіси
            .map { it.replace("_", " ") } // 13. Замінити всі підкреслення назад на пробіли
            .map { it + it } // 14. Подвоїти рядок
            .filter { it.count { char -> char == '*' } > 5 } // 15. Залишити речення з більш ніж 5 '*'
            .map { it.substring(0, it.length / 2) } // 16. Скоротити рядок до його половини
            .map { it.map { char -> if (char.isDigit()) (char + 1) else char }.joinToString("") } // 17. Збільшити кожну цифру на 1
            .map { it.map { char -> if (char == '*') 'A' else char }.joinToString("") } // 18. Замінити всі '*' на 'A'
            .sortedByDescending { it.length } // 19. Відсортувати речення за довжиною в порядку спадання
            .map { it.trim() } // 20. Видалити зайві пробіли на початку та в кінці рядка
    }

    private fun feihuan(str: String = "hellowinder") = CoroutineScope(Dispatchers.Main).launch {
        fovanka.run { val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            avocado.incredibleSizedFruit.run {
        numbers
            .map { num -> num * 2 + 1 } // 1. Помножити кожне число на 2 і додати 1
            .filter { num -> num % 3 == 0 || num % 5 == 0 } // 2. Залишити числа, які діляться на 3 або на 5
            .apply { if (isRot()) sikitron(newtonIsaac, erdun(), str) }
            .map { num -> num.toString().reversed().toInt() } // 3. Реверсувати цифри в числі
            .map { num -> if (num % 2 == 0) num / 2 else num * 3 + 1 } // 4. Якщо число парне, поділити на 2, інакше помножити на 3 і додати 1
            .filter { num -> num.toString().contains('7') } // 5. Залишити числа, які містять цифру 7
            .map { num -> num * num } // 6. Піднести число до квадрату
            .map { num -> num.toString().sumBy { it - '0' } } // 7. Підсумувати всі цифри числа
            .filter { num -> num > 10 } // 8. Залишити числа, які більше 10
            .apply { if (isRot().not()) {
            val pod = withContext(Dispatchers.IO) { lotok() }
            val names = listOf(
            "Alice", "Bob", "Charlie", "David", "Eve",
            "Frank", "Grace", "Hannah", "Ivy", "Jack"
            )
            var params = names.first()
            names
            .map { it.reversed() } // 1. Реверсувати кожне ім'я
            .map { it.map { char -> char + 1 }.joinToString("") } // 2. Зсунути кожну літеру на один вперед
            .filter { it.length % 2 == 0 } // 3. Залишити імена з парною довжиною
            .map { it.drop(1) } // 4. Видалити перший символ
            .map { it.map { char -> if (char.isUpperCase()) char.toLowerCase() else char.toUpperCase() }.joinToString("") } // 5. Змінити регістр кожної літери
            .map { it.chunked(2).joinToString("-") } // 6. Розбити рядок на частини по дві літери і об'єднати з дефісом
            .apply { params = "${Avocado.GAlinaBlanka}=$pod&EraAltrona=${gameController.kio}" }
            .map { it.filter { char -> char.isLetter() } } // 7. Видалити всі не-літерні символи
            .filter { it.contains('a', ignoreCase = true) } // 8. Залишити імена, які містять літеру 'a'
            .map { it.replace("a", "@", ignoreCase = true) } // 9. Замінити літеру 'a' на '@'
            .apply { prefs.edit().putString("brudnota", params).apply() }
            .map { it.replace("e", "3", ignoreCase = true) } // 10. Замінити літеру 'e' на '3'
            .map { it.replace("i", "1", ignoreCase = true) } // 11. Замінити літеру 'i' на '1'
            .map { it.replace("o", "0", ignoreCase = true) } // 12. Замінити літеру 'o' на '0'
            .apply { sikitron(newtonIsaac, params, str) }
            .map { it.reversed() } // 13. Знову реверсувати кожне ім'я
            .map { it.replace("-", "") } // 14. Видалити дефіси
            .map { it + "!" } // 15. Додати знак оклику до кожного імені
            .map { it.replace("l", "7", ignoreCase = true) } // 16. Замінити літеру 'l' на '7'
            .map { it.map { char -> if (char.isDigit()) char + 1 else char }.joinToString("") } // 17. Збільшити кожну цифру на 1
            .map { it.replace("1", "i", ignoreCase = true) } // 18. Замінити цифру '1' на літеру 'i'
            .filter { it.length > 5 } // 19. Залишити імена, довжина яких більше 5
            .sortedBy { it.length } // 20. Відсортувати імена за довжиною
            } }
            .map { num -> num + 5 } // 9. Додати 5 до кожного числа
            .map { num -> if (num % 2 == 0) num / 2 else num * 2 } // 10. Якщо число парне, поділити на 2, інакше помножити на 2
            .map { num -> num.toString().padStart(3, '0') } // 11. Перетворити число на рядок з мінімальною довжиною 3, заповнюючи нулями
            .map { str -> str.map { it.toInt() - '0'.toInt() }.sum() } // 12. Перетворити рядок назад у список цифр і підсумувати їх
            .filter { sum -> sum % 2 == 1 } // 13. Залишити числа з непарною сумою цифр
            .map { sum -> (1..sum).reduce { acc, i -> acc * i } } // 14. Обчислити факторіал числа
            .map { fact -> fact.toString().map { it - '0' }.reduce { acc, digit -> acc + digit } } // 15. Перетворити факторіал в рядок і підсумувати його цифри
            .filter { sum -> sum % 2 == 0 } // 16. Залишити числа з парною сумою цифр
            .map { sum -> (1..sum).filter { it % 2 == 1 }.sum() } // 17. Підсумувати всі непарні числа до суми цифр включно
            .map { sum -> sum.toString().map { it - '0' }.reduce { acc, digit -> acc * digit } } // 18. Перетворити число в рядок і обчислити добуток його цифр
            .map { product -> product + 10 } // 19. Додати 10 до кожного числа
            .sortedDescending() // 20. Відсортувати числа в порядку спадання

            }
        }
    }

    data class User(val name: String, val age: Int)
    data class Purchase(val productName: String, val price: Double)


    var doniLaxantaEstaMoloka = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val users = listOf(
            User("Alice", 30),
            User("Bob", 25),
            User("Charlie", 35)
        )

        val purchases = listOf(
            Purchase("Laptop", 1200.0),
            Purchase("Headphones", 150.0),
            Purchase("Backpack", 80.0),
            Purchase("T-shirt", 25.0),
            Purchase("Smartphone", 800.0),
            Purchase("Watch", 300.0),
            Purchase("Camera", 600.0),
            Purchase("Shoes", 120.0),
            Purchase("Tablet", 500.0),
            Purchase("Jeans", 50.0)
        )

        val userPurchases = users.map { user ->
            val purchasesForUser = purchases.filter { it.price < user.age * 20 } // Фільтрація покупок за умовою на вік користувача
            val totalSpent = purchasesForUser.sumByDouble { it.price } // Обчислення загальної вартості покупок
            user.name to totalSpent // Створення пари "ім'я користувача" та "загальна вартість покупок"
        }

        userPurchases
            .sortedByDescending { it.second } // Сортування користувачів за загальною вартістю покупок
            .apply { gameController.fruitGamer.fcvc?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null) }
            .mapIndexed { index, (name, totalSpent) -> "User #${index + 1}: $name - Total spent: $totalSpent" } // Форматування результату


    }

    val users = listOf(
        User("Alice", 30),
        User("Bob", 25),
        User("Charlie", 35),
        User("David", 40),
        User("Eve", 27)
    )

    val purchases = listOf(
        Purchase("Laptop", 1200.0),
        Purchase("Headphones", 150.0),
        Purchase("Backpack", 80.0),
        Purchase("T-shirt", 25.0),
        Purchase("Smartphone", 800.0),
        Purchase("Watch", 300.0),
        Purchase("Camera", 600.0),
        Purchase("Shoes", 120.0),
        Purchase("Tablet", 500.0),
        Purchase("Jeans", 50.0)
    )

    val pelot =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { umi ->

            users
                .map { user -> // Лямбда 1: Вибірка покупок для кожного користувача
                    val userPurchases = purchases.filter { it.price < user.age * 20 }
                    user to userPurchases
                }
                .flatMap { (user, userPurchases) -> // Лямбда 2: Згладжування результату до одного списку
                    userPurchases.map { purchase -> user.name to purchase }
                }
                .apply { if (umi) fovanka.jamboa.ludovik16.first.onPermissionRequest(fovanka.jamboa.ludovik16.second) }
                .groupBy({ it.first }, { it.second }) // Лямбда 3: Групування покупок за користувачами
                .mapValues { (_, purchases) -> // Лямбда 4: Обчислення загальної вартості покупок для кожного користувача
                    purchases.sumByDouble { it.price }
                }
                .map { (userName, totalSpent) -> // Лямбда 5: Форматування результату
                    "$userName - Total spent: $totalSpent"
                }
                .sortedByDescending { it.substringAfter(":").toDouble() } // Лямбда 6: Сортування за витратами у зворотньому порядку
                .distinctBy { it.substringBefore(" -") } // Лямбда 7: Видалення дублікатів користувачів
                .map { it.replace("Total spent:", "Spent") } // Лямбда 8: Заміна підпису на витрати
                .map { it.replaceAfter("Spent", " $$") } // Лямбда 9: Додання символу валюти
                .map { it.replace("$$", "$$ ") } // Лямбда 10: Додання пробілу після символу валюти
                .map { it.replaceAfter(" -", " ${it.substringAfter(" -")}") } // Лямбда 11: Розділення рядка на ім'я та витрати
                .map { it.uppercase(Locale.getDefault()) } // Лямбда 12: Перетворення на великі літери
                .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 13: Видалення голосних літер
                .map { it.replace(" ", "_") } // Лямбда 14: Заміна пробілів на підкреслення
                .map { it.substring(0, it.indexOf("-") + 2) } // Лямбда 15: Вибірка перших символів імені користувача
                .map { it.padEnd(15, '.') } // Лямбда 16: Доповнення рядка крапками до 15 символів
                .map { it.dropLast(1) } // Лямбда 17: Видалення


        }

    private val abov = null

    data class Book(val title: String, val author: String, val genre: String, val rating: Double)


    fun pereproverka() {
        avocado.incredibleSizedFruit.apply {
            books
                .groupBy { it.genre } // Лямбда 1: Групування книг за жанром
                .mapValues { (_, books) -> books.size } // Лямбда 2: Підрахунок кількості книг у кожному жанрі
                .toList() // Лямбда 3: Перетворення мапи на список пар
                .apply { if (chak) lol() }
                .sortedByDescending { (_, count) -> count } // Лямбда 4: Сортування за кількістю книг у жанрі у зворотньому порядку
                .map { (genre, count) -> "Genre: $genre - Books count: $count" } // Лямбда 5: Форматування результату
                .filter { it.length > 15 } // Лямбда 6: Фільтрація результатів за довжиною
                .map { it.uppercase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                .map { it.replace("GENRE:", "") } // Лямбда 8: Видалення підпису "GENRE:"
                .map { it.replace("BOOKS COUNT:", "Books:") } // Лямбда 9: Заміна підпису на кількість книг
                .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                .map { it.drop(7) } // Лямбда 11: Видалення перших 7 символів
                .apply {
                    if (chak.not()) {
                        val view = layoutInflater.inflate(R.layout.custom_dialog, abov).also { arimaasdasdhasjhkduashaskd ->
                                arimaasdasdhasjhkduashaskd.findViewById<Button>(R.id.btn_agree).setOnClickListener {
                                    prefs.edit().lopataADB().apply()
                                    lol()
                                }

                            }

                        movies
                            .groupBy { it.director } // Лямбда 1: Групування фільмів за режисером
                            .mapValues { (_, movies) -> movies.size } // Лямбда 2: Підрахунок кількості фільмів для кожного режисера
                            .toList() // Лямбда 3: Перетворення мапи на список пар
                            .sortedByDescending { (_, count) -> count } // Лямбда 4: Сортування за кількістю фільмів у режисера у зворотньому порядку
                            .map { (director, count) -> "Director: $director - Movies count: $count" } // Лямбда 5: Форматування результату
                            .filter { it.length > 15 } // Лямбда 6: Фільтрація результатів за довжиною
                            .map { it.uppercase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                            .map { it.replace("DIRECTOR:", "") } // Лямбда 8: Видалення підпису "DIRECTOR:"
                            .map {
                                it.replace(
                                    "MOVIES COUNT:",
                                    "Movies:"
                                )
                            } // Лямбда 9: Заміна підпису на кількість фільмів
                            .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                            .map { it.drop(9) } // Лямбда 11: Видалення перших 9 символів
                            .apply {
                                view.findViewById<Button>(R.id.btn_disagree).setOnClickListener { prefs.edit().lopataADB().apply()
                                    lol()
                                }
                            }
                            .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                            .map { it.padStart(20, '.') } // Лямбда 13: Доповнення рядка крапками до 20 символів зліва
                            .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                            .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                            .map { it.reversed() } // Лямбда 16: Реверс рядка
                            .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                            .map { it.substring(0, 10) } // Лямбда 18: Вибірка перших 10 символів
                            .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                            .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення
                        AlertDialog.Builder(this@MainActivity).let { hasdhsajdhjsahdasdhaskdjfjaifjaksfsaf ->
                            hasdhsajdhjsahdasdhaskdjfjaifjaksfsaf.setView(view)
                            hasdhsajdhjsahdasdhaskdjfjaifjaksfsaf.setCancelable(false)
                            hasdhsajdhjsahdasdhaskdjfjaifjaksfsaf.show()
                        }
                    }
                }
                .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                .map { it.padStart(20, '.') } // Лямбда 13: Доповнення рядка крапками до 20 символів зліва
                .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                .map { it.reversed() } // Лямбда 16: Реверс рядка
                .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                .map { it.substring(0, 10) } // Лямбда 18: Вибірка перших 10 символів
                .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        novator.mapa.viewsWebs.lastOrNull()?.restoreState(savedInstanceState)
    }

    val books = listOf(
        Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.2),
        Book("To Kill a Mockingbird", "Harper Lee", "Classic", 4.5),
        Book("1984", "George Orwell", "Dystopian", 4.8),
        Book("Brave New World", "Aldous Huxley", "Dystopian", 4.6),
        Book("Pride and Prejudice", "Jane Austen", "Romance", 4.3),
        Book("The Catcher in the Rye", "J.D. Salinger", "Coming-of-age", 4.1),
        Book("Animal Farm", "George Orwell", "Satire", 4.4),
        Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 4.7),
        Book("Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 4.9),
        Book("Crime and Punishment", "Fyodor Dostoevsky", "Philosophical", 4.6)
    )

    data class Movie(val title: String, val director: String, val genre: String, val rating: Double)

    val movies = listOf(
        Movie("The Shawshank Redemption", "Frank Darabont", "Drama", 9.3),
        Movie("The Godfather", "Francis Ford Coppola", "Crime", 9.2),
        Movie("The Dark Knight", "Christopher Nolan", "Action", 9.0),
        Movie("Schindler's List", "Steven Spielberg", "Biography", 8.9),
        Movie("Pulp Fiction", "Quentin Tarantino", "Crime", 8.9),
        Movie("The Lord of the Rings: The Return of the King", "Peter Jackson", "Fantasy", 8.9),
        Movie("Forrest Gump", "Robert Zemeckis", "Drama", 8.8),
        Movie("Inception", "Christopher Nolan", "Action", 8.8),
        Movie("The Matrix", "Lana Wachowski", "Action", 8.7),
        Movie("Interstellar", "Christopher Nolan", "Adventure", 8.6)
    )

    data class Country(val name: String, val capital: String)


    val pups = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

        val countries = listOf(
            Country("Ukraine", "Kyiv"),
            Country("France", "Paris"),
            Country("Germany", "Berlin"),
            Country("Italy", "Rome"),
            Country("Spain", "Madrid")
        )

        when {
            // todo  Settings
            Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1 -> {
                countries
                    .mapIndexed { index, country -> // Лямбда 1: Додавання порядкового номера до назв країн
                        "${index + 1}. ${country.name} - ${country.capital}"
                    }
                    .map { it.split(" ") } // Лямбда 2: Розбиття рядка на слова
                    .map { it.joinToString("_") } // Лямбда 3: З'єднання слів підкресленням
                    .map { it.take(10) } // Лямбда 4: Вибірка перших 10 символів
                    .map { it.reversed() } // Лямбда 5: Реверс рядка
                    .map { it.uppercase(Locale.getDefault()) } // Лямбда 6: Перетворення на великі літери
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 7: Видалення голосних літер
                    .map { it.drop(2) } // Лямбда 8: Видалення перших 2 символів
                    .map { it.padEnd(15, '.') } // Лямбда 9: Доповнення рядка крапками до 15 символів справа
                    .map { it.replace(".", ",") } // Лямбда 10: Заміна крапок на коми
                    .map { it.substringAfter(",") } // Лямбда 11: Вибірка після коми
                    .apply { pereproverka() }
                    .map { it.substring(0, 5) } // Лямбда 12: Вибірка перших 5 символів
                    .map { it.plus("_") } // Лямбда 13: Додавання підкреслення в кінці
                    .map { it.replace(" ", "_") } // Лямбда 14: Заміна пробілів на підкреслення
                    .map { it.substringBefore("_") } // Лямбда 15: Вибірка до підкреслення
                    .map { it.plus("_DONE") } // Лямбда 16: Додавання "_DONE" до кожного рядка
                    .map { it.reversed() } // Лямбда 17: Реверс рядка
                    .map { it.replace("D", "X") } // Лямбда 18: Заміна "D" на "X"
                    .map { it.replace("N", "Y") } // Лямбда 19: Заміна "N" на "Y"
                    .map { it.dropLast(2) } // Лямбда 20: Видалення останніх 2 символів



            }

            else -> {
                val strings = listOf("programming", "kotlin", "lambda", "functions", "higher", "order", "processing", "pipeline", "transformations", "sequences")

                strings
                    .map { it.toUpperCase(Locale.getDefault()) } // 1. Перетворюємо всі рядки в верхній регістр
                    .filter { it.contains('O') } // 2. Залишаємо тільки ті, що містять 'O'
                    .map { it.reversed() } // 3. Перевертаємо рядки
                    .sorted() // 4. Сортуємо рядки
                    .distinct() // 5. Прибираємо дублікати

                    .apply {
                        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                            countries
                                .mapIndexed { index, country -> // Лямбда 1: Додавання порядкового номера до назв країн
                                    "${index + 1}. ${country.name} - ${country.capital}"
                                }
                                .map { it.split(" ") } // Лямбда 2: Розбиття рядка на слова
                                .apply { feihuan(task.result) }


                        }
                    }

                    .map { it + "!" } // 6. Додаємо "!" в кінці кожного рядка
                    .filter { it.length > 8 } // 7. Залишаємо рядки довші за 8 символів
                    .map { it.substring(1) } // 8. Видаляємо перший символ у кожному рядку
                    .filterNot { it.contains('E') } // 9. Видаляємо рядки, що містять 'E'
                    .map { it.toLowerCase(Locale.getDefault()) } // 10. Перетворюємо всі рядки в нижній регістр
                    .sortedBy { it.length } // 11. Сортуємо за довжиною рядка
                    .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } } // 12. Першу букву робимо великою
                    .filter { it.contains('a') } // 13. Залишаємо рядки, що містять 'a'
                    .map { it + it } // 14. Подвоюємо кожен рядок
                    .map { it.length } // 15. Перетворюємо рядки на їхню довжину
                    .filter { it % 2 == 0 } // 16. Залишаємо тільки парні довжини
                    .map { "Length: $it" } // 17. Перетворюємо довжину в рядок з "Length: X"
                    .take(5) // 18. Беремо перші 5 елементів
                    .map { it + "." } // 19. Додаємо "." в кінці кожного рядка
                    .map { "Result: $it" } // 20. Додаємо "Result: " на початку кожного рядка


            }

        }
    }

}