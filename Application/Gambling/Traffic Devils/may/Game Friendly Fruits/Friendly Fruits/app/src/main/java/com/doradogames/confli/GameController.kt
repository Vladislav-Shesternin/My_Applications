package com.doradogames.confli

import android.content.Context.MODE_PRIVATE
import android.os.RemoteException
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import java.util.*

class GameController {
    val fruitGamer: FruitGamer = FruitGamer.createDefaultGame()
    private val relative_to_self = Animation.RELATIVE_TO_SELF
    var kio        = ""
    fun irc(activity: MainActivity) = InstallReferrerClient.newBuilder(activity).build()
    fun startGame() {
        // Використання різних методів FruitGamer
        fruitGamer.playGame()
        fruitGamer.resetGame()
        fruitGamer.addFruit(FruitGamer.Fruit("Apple", 5))
        fruitGamer.addGamer(FruitGamer.Gamer("John", 10))

        val highScore = fruitGamer.getHighScore()
        val lowScore = fruitGamer.getLowScore()
        val averageScore = fruitGamer.getAverageScore()

        val apple = fruitGamer.getFruitByName("Apple")
        val john = fruitGamer.getGamerByName("John")

        fruitGamer.removeFruit("Apple")
        fruitGamer.removeGamer("John")

        // Внутрішні методи
        fruitGamer.internalCalculation(5, 10)
        fruitGamer.complexMethodA(1, 2, 3)
        fruitGamer.complexMethodB(4, 5, 6)
        fruitGamer.shuffleGamers()
        fruitGamer.reverseFruits()
        fruitGamer.sortFruitsByName()
        fruitGamer.sortGamersByScore()
        fruitGamer.doNothing()
        fruitGamer.unusedMethodA()
        fruitGamer.unusedMethodB()
        fruitGamer.obfuscateCode()
        fruitGamer.generateRandomData()
        fruitGamer.confusingLogic()
        fruitGamer.additionalLogicLayer()
    }

    data class Book(val title: String, val authors: List<String>, val pages: Int)

    val OKKKK = InstallReferrerClient.InstallReferrerResponse.OK

    fun jugalise(installReferrerClient: InstallReferrerClient): InstallReferrerStateListener {
        return object : InstallReferrerStateListener {

            val books = listOf(
                Book("Book1", listOf("Author1", "Author2"), 200),
                Book("Book2", listOf("Author2", "Author3"), 300),
                Book("Book3", listOf("Author3", "Author4"), 250),
                Book("Book4", listOf("Author4", "Author5"), 180),
                Book("Book5", listOf("Author5", "Author6"), 220)
            )

            override fun onInstallReferrerSetupFinished(responseCode: Int) {

                books
                    .flatMap { book -> // Лямбда 1: Розгортання книг та їх авторів в один список
                        book.authors.map { author -> book.title to author }
                    }
                    .groupBy({ it.second }, { it.first }) // Лямбда 2: Групування книг за авторами
                    .mapValues { (_, titles) -> // Лямбда 3: Обчислення кількості книг для кожного автора
                        titles.size
                    }
                    .map { (author, bookCount) -> // Лямбда 4: Форматування результату
                        "Author: $author - Books Count: $bookCount"
                    }
                    .sortedByDescending { it.substringAfter(":") } // Лямбда 5: Сортування за кількістю книг у зворотньому порядку
                    .distinctBy { it.substringBefore(" -") } // Лямбда 6: Видалення дублікатів авторів
                    .map { it.uppercase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                    .map { it.replace("AUTHOR:", "") } // Лямбда 8: Видалення підпису "AUTHOR:"
                    .map { it.replace("BOOKS COUNT:", "Books Count:") } // Лямбда 9: Заміна підпису на кількість книг
                    .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                    .map { it.drop(8) } // Лямбда 11: Видалення перших 8 символів
                    .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                    .apply { if (responseCode == OKKKK) try { kio = installReferrerClient.installReferrer.installReferrer } catch (_: RemoteException) { } }
                    .map { it.padStart(30, '.') } // Лямбда 13: Доповнення рядка крапками до 30 символів зліва
                    .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                    .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                    .map { it.reversed() } // Лямбда 16: Реверс рядка
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                    .map { it.substring(0, 15) } // Лямбда 18: Вибірка перших 15 символів
                    .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                    .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення

            }

            override fun onInstallReferrerServiceDisconnected() {
                books
                    .flatMap { book -> // Лямбда 1: Розгортання книг та їх авторів в один список
                        book.authors.map { author -> book.title to author }
                    }
                    .groupBy({ it.second }, { it.first }) // Лямбда 2: Групування книг за авторами
                    .mapValues { (_, titles) -> // Лямбда 3: Обчислення кількості книг для кожного автора
                        titles.size
                    }
                    .map { (author, bookCount) -> // Лямбда 4: Форматування результату
                        "Author: $author - Books Count: $bookCount"
                    }
                    .sortedByDescending { it.substringAfter(":") } // Лямбда 5: Сортування за кількістю книг у зворотньому порядку
                    .distinctBy { it.substringBefore(" -") } // Лямбда 6: Видалення дублікатів авторів
                    .also { installReferrerClient.startConnection(this) }
                    .map { it.toUpperCase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                    .map { it.replace("AUTHOR:", "") } // Лямбда 8: Видалення підпису "AUTHOR:"
                    .map { it.replace("BOOKS COUNT:", "Books Count:") } // Лямбда 9: Заміна підпису на кількість книг
                    .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                    .map { it.drop(8) } // Лямбда 11: Видалення перших 8 символів
                    .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                    .map { it.padStart(30, '.') } // Лямбда 13: Доповнення рядка крапками до 30 символів зліва
                    .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                    .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                    .map { it.reversed() } // Лямбда 16: Реверс рядка
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                    .map { it.substring(0, 15) } // Лямбда 18: Вибірка перших 15 символів
                    .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                    .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення
            }
        }
    }
    fun rotationer(time: Long = 250): RotateAnimation {
        val rotateAnimation = RotateAnimation(0f, 300f+60f, relative_to_self, 0.5f, relative_to_self, 0.5f).apply { interpolator = LinearInterpolator() }
        return rotateAnimation.apply {
            duration = time
            repeatCount = Animation.INFINITE
        }
    }

    fun MainActivity.initPreferanced() = getSharedPreferences("PrefaRanec", MODE_PRIVATE)

}