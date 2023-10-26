package com.bettilt.mobile.pt

class MultiplesOfSevenGenerator {
    fun generateMultiplesOfSeven(count: Int): List<Int> {
        val multiplesOfSeven = mutableListOf<Int>()

        for (i in 1..count) {
            val number = i * 7
            multiplesOfSeven.add(number)
        }

        return multiplesOfSeven
    }
}