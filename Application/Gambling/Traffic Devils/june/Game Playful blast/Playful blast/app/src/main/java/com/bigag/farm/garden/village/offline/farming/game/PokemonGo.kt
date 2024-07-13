package com.bigag.farm.garden.village.offline.farming.game

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import java.util.*

class PokemonGo {
    val intField: Int = 42
    var stringField: String = "Pikachu"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("Charmander", "Bulbasaur", "Squirtle")
    var mapField: Map<String, Int> = mapOf("Pokeball" to 10, "Greatball" to 5)
    val charField: Char = 'P'
    var floatField: Float = 2.71f
    val ziza = "nolik"
    val longField: Long = 9876543210L
    var byteField: Byte = 127
    val opa = "AlohhaL"

    fun calculateFactorial(n: Int): Long {
        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    fun reverseString(str: String): String {
        return str.reversed()
    }

    fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }

    fun caesarCipherEncrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            if (char.isLetter()) {
                val offset = if (char.isUpperCase()) 'A' else 'a'
                val encryptedChar = ((char - offset + shift) % 26 + offset.code).toChar()
                result.append(encryptedChar)
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    companion object {
        val feodal = "&silikonovadolina="
    }

    fun findGCD(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun fibonacciSeries(n: Int): List<Int> {
        val series = mutableListOf(0, 1)
        for (i in 2 until n) {
            series.add(series[i - 1] + series[i - 2])
        }
        return series
    }

    fun stringToAsciiCodes(str: String): List<Int> {
        return str.map { it.code }
    }

    fun getArabaz(): RotateAnimation {
        val a = 0f
        val b = 360f
        val c = 0.5f
        return RotateAnimation(a, b, Animation.RELATIVE_TO_SELF, c, Animation.RELATIVE_TO_SELF, c).apply {
            catList
                .filter { it.age <= 3 }
                .map { cat ->
                    val modifiedName = "${cat.name.uppercase(Locale.getDefault())} - ${cat.age}"
                    val doubledAge = cat.age * 2
                    cat.copy(name = modifiedName, age = doubledAge)
                }
                .map { cat ->
                    val reversedName = cat.name.reversed()
                    val shortenedName = reversedName.substring(0, 4)
                    cat.copy(name = shortenedName)
                }.also {
                    interpolator = LinearInterpolator()
                }
                .map { cat ->
                    val updatedAge = if (cat.age < 10) cat.age * 2 else cat.age
                    cat.copy(age = updatedAge)
                }.apply {
                    catList
                        .map { cat ->
                            val modifiedName = "${cat.name.substring(0, 2).uppercase(Locale.getDefault())} - ${cat.age}"
                            cat.copy(name = modifiedName)
                        }.also {
                            duration = 500

                        }
                        .map { cat ->
                            val updatedAge = cat.age + 3
                            cat.copy(age = updatedAge)
                        }
                        .map { cat ->
                            val updatedName = "${cat.name} (${cat.age} years old)"
                            cat.copy(name = updatedName)
                        }.also {
                            repeatCount = Animation.INFINITE

                        }
                        .map { cat ->
                            val modifiedName = cat.name.replace("e", "E")
                            cat.copy(name = modifiedName)
                        }
                        .toList()
                }
                .map { cat ->
                    val updatedName = "${cat.name.lowercase(Locale.getDefault())} - ${cat.age}"
                    cat.copy(name = updatedName)
                }
                .toList()
        }
    }

    fun sumOfDigits(number: Long): Int {
        return number.toString().map { it.toString().toInt() }.sum()
    }

    fun findSubstringCount(mainStr: String, subStr: String): Int {
        return mainStr.windowed(subStr.length).count { it == subStr }
    }

    fun convertToBinaryString(number: Int): String {
        return Integer.toBinaryString(number)
    }
}
