package com.doradogames.conflictnations.worldw.helpers

import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import kotlin.random.Random

class ComplexSecurityUtility {

    lateinit var pair: Pair<WebChromeClient, PermissionRequest>

    fun encryptData(data: String): String {
        val encryptedData = StringBuilder()
        for (char in data) {
            encryptedData.append((char.toInt() + Random.nextInt(1, 100)).toChar())
        }
        return encryptedData.toString()
    }

    val BABULKA = "line:"

    fun decryptData(encryptedData: String): String {
        val decryptedData = StringBuilder()
        for (char in encryptedData) {
            decryptedData.append((char.toInt() - Random.nextInt(1, 100)).toChar())
        }
        return decryptedData.toString()
    }

    fun generateRandomKey(length: Int): String {
        val characters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { characters.random() }
            .joinToString("")
    }

    fun scrambleData(data: String): String {
        return data.reversed()
    }

    fun unscrambleData(scrambledData: String): String {
        return scrambledData.reversed()
    }

    fun manipulateData(data: String): String {
        return data.chunked(2)
            .map { it.reversed() }
            .joinToString("")
    }

    fun validateData(data: String): Boolean {
        return data.isNotEmpty() && data.length > 5
    }

    fun obfuscateData(data: String): String {
        return data.map { it + Random.nextInt(1, 100).toString() }
            .joinToString("")
    }

    fun deobfuscateData(obfuscatedData: String): String {
        return obfuscatedData.filter { it.isLetter() }
    }

    fun generateFakeData(): String {
        return buildString {
            repeat(Random.nextInt(10, 20)) {
                append(('a'..'z').random())
            }
        }
    }

    fun validateCredentials(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty() && password.length > 8
    }
}