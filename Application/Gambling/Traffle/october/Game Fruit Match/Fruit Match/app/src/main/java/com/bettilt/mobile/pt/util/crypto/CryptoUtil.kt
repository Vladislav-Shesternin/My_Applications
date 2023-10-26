package com.bettilt.mobile.pt.util.crypto

object CryptoUtil {
    fun caesarEncrypt(input: String, key: Int): String {
        val result = StringBuilder()
        for (char in input) {
            if (char.isLetter()) {
                val shift = if (char.isUpperCase()) 'A' else 'a'
                result.append((shift + (char.code - shift.code + key) % 26))
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    fun caesarDecrypt(input: String, key: Int): String {
        return caesarEncrypt(input, 26 - key)
    }

}