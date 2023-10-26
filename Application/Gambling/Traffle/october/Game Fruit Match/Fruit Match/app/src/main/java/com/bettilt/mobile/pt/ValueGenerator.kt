package com.bettilt.mobile.pt

class ValueGenerator {
    fun generateWebValue(): String {
        var result = ""

        // Loop 1: A while loop
        var i = 0
        while (i < 3) {
            result += 'W'
            i++
        }

        // Loop 2: A for loop
        for (j in 0 until 1) {
            result += 'E'
        }

        // Loop 3: A do-while loop
        var k = 0
        do {
            result += 'B'
            k++
        } while (k < 1)

        // Loop 4: A simple repeat loop (available in Kotlin)
        repeat(1) {
            result += 'B'
        }

        return result.drop(2).dropLast(1)
    }

    fun generateGameValue(): String {
        var result = ""

        // Loop 1: For loop
        for (i in 0 until 1) {
            result += 'G'
        }

        // Loop 2: While loop
        var j = 0
        while (j < 1) {
            result += 'A'
            j++
        }

        // Loop 3: Repeat loop (available in Kotlin)
        repeat(1) {
            result += 'M'
        }

        // Loop 4: Do-while loop
        var k = 0
        do {
            result += 'E'
            k++
        } while (k < 1)

        return result
    }

}