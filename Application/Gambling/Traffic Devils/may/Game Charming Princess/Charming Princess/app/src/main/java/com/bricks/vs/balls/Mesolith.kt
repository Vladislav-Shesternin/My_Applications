package com.bricks.vs.balls

import android.net.Uri
import android.webkit.ValueCallback
import com.bricks.vs.balls.MainActivity.Companion.Hgd
import com.bricks.vs.balls.databinding.ActivityMainBinding

class Mesolith {
    // Поля класу зі списками букв та цифр
    private val letters: List<Char> = ('a'..'z').toList()
    private val numbers: List<Int> = (1..100).toList()
    var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null

    // Велика функція 1
    fun processLettersAndNumbers1() {
        // Операції з літерами
        val vowels = letters.filter { it in listOf('a', 'e', 'i', 'o', 'u') }
        val consonants = letters.filter { it !in listOf('a', 'e', 'i', 'o', 'u') }
        val uppercaseLetters = letters.map { it.uppercaseChar() }
        val reversedLetters = letters.reversed()
        val firstTenLetters = letters.take(10)

        // Операції з числами
        val evenNumbers = numbers.filter { it % 2 == 0 }
        val oddNumbers = numbers.filter { it % 2 != 0 }
        val squaredNumbers = numbers.map { it * it }
        val sumOfNumbers = numbers.sum()
        val averageOfNumbers = numbers.average()

        vowels.toString() + consonants.toString() + uppercaseLetters.toString() + reversedLetters.toString() + firstTenLetters.toString() + evenNumbers.toString() + oddNumbers.toString() + squaredNumbers.toString() + sumOfNumbers.toString() + averageOfNumbers.toString()
    }

    // Велика функція 2
    fun processLettersAndNumbers2() {
        // Операції з літерами
        val lettersWithIndex = letters.mapIndexed { index, c -> "$index:$c" }
        val sortedLetters = letters.sorted()
        val groupedByVowel = letters.groupBy { it in listOf('a', 'e', 'i', 'o', 'u') }
        val distinctLetters = letters.distinct()
        val chunkedLetters = letters.chunked(5)

        // Операції з числами
        val doubledNumbers = numbers.map { it * 2 }
        val firstTwentyNumbers = numbers.take(20)
        val maxNumber = numbers.maxOrNull()
        val minNumber = numbers.minOrNull()
        val sortedDescendingNumbers = numbers.sortedDescending()
        lettersWithIndex.toString() + sortedLetters.toString() + groupedByVowel.toString() + distinctLetters.toString() + chunkedLetters.toString() + doubledNumbers.toString() + firstTwentyNumbers.toString() + maxNumber.toString() + minNumber.toString() + sortedDescendingNumbers.toString()
    }

    fun haha(bg: ActivityMainBinding, largeListProcessor: LargeListProcessor) {
        bg.centerrr.startAnimation(largeListProcessor.filadelf)
    }

    fun ssd(uri: String) = uri.startsWith(Hgd)

    // Велика функція 3
    fun processLettersAndNumbers3() {
        // Операції з літерами
        val lettersCount = letters.size
        val lettersWithoutVowels = letters.filterNot { it in listOf('a', 'e', 'i', 'o', 'u') }
        val letterPairs = letters.zip(letters)
        val flatMappedLetters = letters.flatMap { listOf(it, it.uppercaseChar()) }
        val letterFrequency = letters.groupingBy { it }.eachCount()

        // Операції з числами
        val countEvenNumbers = numbers.count { it % 2 == 0 }
        val numberRange = numbers.fold(0 to 0) { acc, i -> acc.first + i to acc.second + 1 }
        val averageEvenNumbers = numbers.filter { it % 2 == 0 }.average()
        val numbersAsString = numbers.joinToString(", ")
        val mappedNumbers = numbers.mapIndexed { index, number -> index to number * number }
        lettersCount.toString() + lettersWithoutVowels.toString() + letterPairs.toString() + flatMappedLetters.toString() + letterFrequency.toString() + countEvenNumbers.toString() + numberRange.toString() + averageEvenNumbers.toString() + numbersAsString.toString() + mappedNumbers.toString()
    }
}