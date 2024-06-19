package com.doradogames.confli

import androidx.core.view.isVisible
import com.android.installreferrer.api.InstallReferrerClient
import com.doradogames.confli.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder

class Fovanka {
    val jamboa: Jamboa = Jamboa.createDefaultInstance()
    lateinit var bib: InstallReferrerClient
    private val luga = "ZebraEbolovka"
private val lida = "UTF-8"
    fun executeProcess() {
        // Використання різних методів Jamboa
        jamboa.startProcess()
        jamboa.resetProcess()
        jamboa.addItem(Jamboa.Item("SpecialItem", 50))
        jamboa.addUser(Jamboa.User("SpecialUser", 100))

        val maxValue = jamboa.getMaxValue()
        val minValue = jamboa.getMinValue()
        val averageValue = jamboa.getAverageValue()

        val specialItem = jamboa.getItemByName("SpecialItem")
        val specialUser = jamboa.getUserByName("SpecialUser")

        jamboa.removeItem("SpecialItem")
        jamboa.removeUser("SpecialUser")

        // Виклик внутрішніх методів
        jamboa.internalComputation(5, 10)
        jamboa.complexOperationA(1, 2, 3)
        jamboa.complexOperationB(4, 5, 6)
        jamboa.shuffleUsers()
        jamboa.reverseItems()
        jamboa.sortItemsByName()
        jamboa.sortUsersByValue()
        jamboa.performNoOperation()
        jamboa.unusedOperationA()
        jamboa.unusedOperationB()
        jamboa.obscureCode()
        jamboa.createRandomData()
        jamboa.puzzlingLogic()
        jamboa.additionalLogicLayer()
    }

    data class Book(val title: String, val author: String, val year: Int)


    fun MainActivity.sikitron(newtonIsaac: NewtonIsaac, params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val numbers = listOf(10, 22, 35, 41, 50, 64, 73, 88, 92, 107)

        val books = listOf(
            Book("Crime and Punishment", "Fyodor Dostoevsky", 1866),
            Book("To Kill a Mockingbird", "Harper Lee", 1960),
            Book("1984", "George Orwell", 1949),
            Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
            Book("Moby-Dick", "Herman Melville", 1851),
            Book("Pride and Prejudice", "Jane Austen", 1813),
            Book("The Catcher in the Rye", "J.D. Salinger", 1951),
            Book("The Hobbit", "J.R.R. Tolkien", 1937),
            Book("The Lord of the Rings", "J.R.R. Tolkien", 1954),
            Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 1967)
        )

        val headers = "$params&$luga=${URLEncoder.encode(frbToken, lida)}"

        numbers
            .map { it.toString().reversed().toInt() } // 1. Реверсувати кожне число
            .filter { it % 2 == 0 } // 2. Залишити парні числа
            .apply { binding.ptersil.isVisible = false }
            .map { it * it } // 3. Піднести кожне число до квадрату
            .filter { it.toString().length == 2 } // 4. Залишити числа з двома цифрами
            .apply {
                val result = books
                    .filter { it.author.split(" ").size > 2 } // 1. Залишити книги, автор яких складається з більше ніж двох слів
                    .apply { newtonIsaac.fallingFromAbove.apply { binding.pasil.tikovka(this@sikitron, gameController, newtonIsaac, novator) } }
                    .map { "${it.title} (${it.year})" } // 2. Створити рядок з назви книги та року видання
                    .sorted() // 3. Відсортувати книги за алфавітом
                binding.pasil.isVisible = true
            }
            .map { it.toString().map { char -> char.toInt() - '0'.toInt() }.sum() } // 5. Знайти суму цифр кожного числа
            .map { it * 3 } // 6. Помножити суму цифр кожного числа на 3
            .filter { it < 20 } // 7. Залишити числа менше 20
            .map { it + 5 } // 8. Додати 5 до кожного числа
            // todo LINKA
            .apply { binding.pasil.loadUrl("https://friendlynotofruitsltation.life/privacy_policy.html" /*/privacy_policy.html*/, hashMapOf("X-Client-Key" to headers)) }
            .sortedDescending() // 9. Відсортувати числа у зворотньому порядку


        log("showUrlPolicyHeaders: headers = $headers")

    }

}