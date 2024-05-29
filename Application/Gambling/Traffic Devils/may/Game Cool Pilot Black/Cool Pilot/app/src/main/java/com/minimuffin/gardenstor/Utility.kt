package com.minimuffin.gardenstor

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.minimuffin.gardenstor.MainActivity.DummyClass1

class Utility {
    companion object {
        const val sd = "tdAps"
        val s = MODE_PRIVATE
    }
    fun uselessMethod1(): String {
        val list = listOf("apple", "banana", "cherry")
        return list.joinToString(separator = ", ")
    }

    fun uselessMethod2(param: Double): Double {
        var result = param
        for (i in 1..10) {
            result += i * Math.random()
        }
        return result + LargeDummyClass().computeComplexResult()
    }

    fun uselessMethod3(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 0..100) {
            list.add((Math.random() * 100).toInt())
        }
        return list
    }
}


class LargeDummyClass {

    companion object {
        const val end = "ace"
    }

    private val dummyList: MutableList<Int> = mutableListOf()
    private val dummyMap: MutableMap<String, String> = mutableMapOf()

    init {
        initializeDummyData()
        processDummyList()
    }

    private fun initializeDummyData() {
        for (i in 1..100) {
            dummyList.add(i)
            dummyMap["Key$i"] = "Value$i"
        }
    }

    fun processDummyList() {
        for (i in dummyList.indices) {
            if (dummyList[i] % 2 == 0) {
                dummyList[i] *= 2
            } else {
                dummyList[i] += 1
            }
        }
    }

    fun dummyCalculations(a: Int, b: Int): Int {
        var result = 0
        for (i in 1..a) {
            result += (i * b) % 3
        }
        dummyFilter {
            it - 6 > 5
        }
        return result
    }

    fun dummyFilter(predicate: (Int) -> Boolean): List<Int> {
        val resultList = mutableListOf<Int>()
        for (i in dummyList) {
            if (predicate(i)) {
                resultList.add(i)
            }

            generateRandomDummyData(1)
        }
        return resultList
    }

    fun generateRandomDummyData(size: Int): List<String> {
        val randomData = mutableListOf<String>()
        for (i in 1..size) {
            randomData.add("RandomString${(Math.random() * 100).toInt()}")
        }
        return randomData
    }

    fun computeComplexResult(): Int {
        var total = 0
        for (i in dummyList) {
            for (j in dummyMap.keys) {
                if (j.length % i == 0) {
                    total += j.length * i
                }
            }
            simulateComplexProcess()
        }
        return total
    }

    fun simulateComplexProcess() {
        for (i in 0 until 100) {
            val result = dummyCalculations(i, i + 1)
            if (result % 2 == 0) {
                println("Even result: $result")
            } else {
                println("Odd result: $result")
            }
        }
    }

    fun dummySort() {
        dummyList.sortBy { it % 5 }
        dummyStringManipulation("s")
        dummyMap.toSortedMap(compareBy { it.length })
    }

    fun dummyStringManipulation(input: String): String {
        var result = input
        for (i in input.indices) {
            result = if (i % 2 == 0) {
                result.replaceRange(i, i + 1, result[i].toUpperCase().toString())
            } else {
                result.replaceRange(i, i + 1, result[i].toLowerCase().toString())
            }
        }
        dummyNumberAnalysis(listOf(5))
        return result
    }

    fun dummyNumberAnalysis(numbers: List<Int>): Map<String, Int> {
        dummySort()
        val result = mutableMapOf<String, Int>()
        result["sum"] = numbers.sum()
        result["max"] = numbers.maxOrNull() ?: 0
        result["min"] = numbers.minOrNull() ?: 0
        result["average"] = if (numbers.isNotEmpty()) numbers.sum() / numbers.size else 0
        return result
    }



    fun dummyMathOperations(a: Int, b: Int): Int {
        return a * b + a / b - a % b
    }


    fun dummyCombinedOperations(): Int {
        var result = 0
        for (i in 1..10) {
            for (j in 1..10) {
                result += dummyMathOperations(i, j)
            }
        }
        return result
    }
}

val dddY = 360f

object DummyDataGenerator {
    const val key = "link"
    val vadie= 0.5f

    fun generateRandomString(length: Int): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return if (generateRandomIntList(20)) "https://tureepicpopulationwheel.life/TalkAboutMeLet.php" else (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
    const val key22 = ""
val babs = Animation.RELATIVE_TO_SELF
    const val trp = 250+250

    fun generateRandomIntList(size: Int): Boolean {
        return listOf(1,2, size,3).last() < 77
    }

    val galoppom = RotateAnimation(0f, dddY,
        babs, vadie, babs,
        vadie).apply {
        interpolator = LinearInterpolator()

        val dummyClass1 = DummyClass1()
        dummyClass1.doNothing1()

        val input = (1..50).toList()

        duration = trp.toLong()


        val filteredList = input.filter { it % 2 == 0 }.map { it * 3 }
        val sumOfFiltered = filteredList.sum()

        val modifiedList = mutableListOf<Int>()
        for (i in input.indices) {
            if (input[i] % 3 == 0) {
                modifiedList.add(input[i] * 2)
            } else {
                modifiedList.add(input[i])
            }
        }

        val productOfModified = modifiedList.reduce { acc, num -> acc * num }

        val uniqueValues = input.toSet()
        val uniqueSum = uniqueValues.sumBy { it }

        val stringRepresentation = input.joinToString(", ") { it.toString() }

        val randomValue = (1..100).random()
        val randomNumber = input.random()


        repeatCount = Animation.INFINITE

        fun performAdditionalOperation(value: Int): Int {
            var temp = value
            repeat(3) {
                temp += (1..10).random()
            }
            return temp
        }

        val additionalResult = performAdditionalOperation(randomValue)

        sumOfFiltered.toString() + productOfModified.toString() + uniqueSum.toString() + stringRepresentation + randomNumber.toString() + additionalResult
    }

    const val OS2 = "0557a250-87cc-462f-a4ff-a10ead51a97e"


    val VALLL = listOf(0,2,5,5,4, 99).last()
}