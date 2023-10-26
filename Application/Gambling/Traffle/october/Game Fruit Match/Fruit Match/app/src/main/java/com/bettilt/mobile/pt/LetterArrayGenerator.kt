package com.bettilt.mobile.pt

class LetterArrayGenerator {
    fun generateLetterArray(count: Int, startLetter: Char = 'A'): Array<String> {
        if (count <= 0) {
            throw IllegalArgumentException("Count must be a positive integer")
        }

        val letters = Array(count) {
            (startLetter + it).toString()
        }

        return letters
    }
}