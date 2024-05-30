package com.bricks.vs.balls

import android.webkit.WebView

class Pisos {
    // Поля класу зі списками букв та цифр
    private val letters: List<Char> = ('a'..'z').toList()
    private val numbers: List<Int> = (1..100).toList()

    var viewsWebs = mutableListOf<WebView>()

    // Велика функція 1: Операції з літерами та числами
    fun processLettersAndNumbers1(): Pair<List<Char>, List<Int>> {
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

        return Pair(
            vowels + consonants + uppercaseLetters + reversedLetters + firstTenLetters,
            evenNumbers + oddNumbers + squaredNumbers + listOf(sumOfNumbers) + listOf(averageOfNumbers.toInt())
        )
    }

    var usANser = true

    // Велика функція 2: Операції з літерами та числами
    fun processLettersAndNumbers2(): Pair<List<Any>, List<Int>> {
        // Операції з літерами
        val lettersWithIndex = letters.mapIndexed { index, c -> "$index:$c" }
        val sortedLetters = letters.sorted()
        val groupedByVowel = letters.groupBy { it in listOf('a', 'e', 'i', 'o', 'u') }
        val distinctLetters = letters.distinct()
        val chunkedLetters = letters.chunked(5)

        // Операції з числами
        val doubledNumbers = numbers.map { it * 2 }
        val firstTwentyNumbers = numbers.take(20)
        val maxNumber = numbers.maxOrNull() ?: 0
        val minNumber = numbers.minOrNull() ?: 0
        val sortedDescendingNumbers = numbers.sortedDescending()

        return Pair(
            lettersWithIndex + sortedLetters + groupedByVowel.values.flatten() + distinctLetters + chunkedLetters.flatten(),
            doubledNumbers + firstTwentyNumbers + listOf(maxNumber) + listOf(minNumber) + sortedDescendingNumbers
        )
    }

     var iR = ""

    // Велика функція 3: Операції з літерами та числами
    fun processLettersAndNumbers3(): Pair<List<Any>, List<Any>> {
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

        return Pair(
            listOf(lettersCount) + lettersWithoutVowels + letterPairs + flatMappedLetters + letterFrequency.entries,
            listOf(countEvenNumbers) + listOf(numberRange) + listOf(averageEvenNumbers) + listOf(numbersAsString) + mappedNumbers
        )
    }
}