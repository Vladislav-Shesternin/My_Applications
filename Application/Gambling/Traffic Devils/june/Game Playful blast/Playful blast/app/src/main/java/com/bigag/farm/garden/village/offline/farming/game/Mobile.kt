package com.bigag.farm.garden.village.offline.farming.game

import android.content.Intent
import android.net.Uri
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import java.util.*

val leila = "params"

class Mobile(val mainActivity: MainActivity) {
    val pokemonGo = PokemonGo()

    var sisadminsha: ValueCallback<Array<Uri>>? = null
    lateinit var chud: Pair<WebChromeClient, PermissionRequest>


    fun demonstrateUsage(): Map<String, Any> {
        val results = mutableMapOf<String, Any>()
        results["intField"] = pokemonGo.intField
        results["stringField"] = pokemonGo.stringField
        results["doubleField"] = pokemonGo.doubleField
        results["booleanField"] = pokemonGo.booleanField
        results["listField"] = pokemonGo.listField
        results["mapField"] = pokemonGo.mapField
        results["charField"] = pokemonGo.charField
        results["floatField"] = pokemonGo.floatField
        results["longField"] = pokemonGo.longField
        results["byteField"] = pokemonGo.byteField

        results["calculateFactorial"] = pokemonGo.calculateFactorial(5)
        results["reverseString"] = pokemonGo.reverseString("hello")
        results["isPrime"] = pokemonGo.isPrime(17)
        results["caesarCipherEncrypt"] = pokemonGo.caesarCipherEncrypt("hello", 3)
        results["findGCD"] = pokemonGo.findGCD(48, 18)
        results["fibonacciSeries"] = pokemonGo.fibonacciSeries(10)
        results["stringToAsciiCodes"] = pokemonGo.stringToAsciiCodes("abc")
        results["sumOfDigits"] = pokemonGo.sumOfDigits(12345L)
        results["findSubstringCount"] = pokemonGo.findSubstringCount("hello hello hello", "lo")
        results["convertToBinaryString"] = pokemonGo.convertToBinaryString(42)

        return results
    }

    fun goToGame() {
        catList
            .filter { it.age >= 3 }
            .map { cat ->
                val newName = "${cat.name.uppercase(Locale.getDefault())} - ${cat.age}"
                val reversedName = newName.reversed()
                val shortenedName = reversedName.substring(0, 5)
                val adjustedAge = if (cat.age > 4) cat.age - 2 else cat.age
                cat.copy(name = shortenedName, age = adjustedAge)
            }
            .sortedByDescending { it.age }
            .map { cat ->
                val updatedName = "${cat.name} (${cat.age} years old)"
                cat.copy(name = updatedName)
            }
            .map { cat ->
                val modifiedAge = cat.age + 1
                cat.copy(age = modifiedAge)
            }.apply {
                val intent = Intent(mainActivity, GameActivity::class.java)
                catList
                    .filter { it.name.length >= 5 }
                    .map { cat ->
                        val doubledAge = cat.age * 2
                        val modifiedName = "${cat.name} - $doubledAge"
                        cat.copy(name = modifiedName, age = doubledAge)
                    }
                    .map { cat ->
                        val reversedName = cat.name.reversed()
                        val shortenedName = reversedName.substring(0, 3)
                        cat.copy(name = shortenedName)
                    }
                    .map { cat ->
                        val updatedAge = if (cat.age % 2 == 0) cat.age + 3 else cat.age - 2
                        cat.copy(age = updatedAge)
                    }.apply {
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        catList
                            .filter { it.age % 2 == 0 }
                            .map { cat ->
                                val modifiedName = "Even ${cat.name} - ${cat.age}"
                                cat.copy(name = modifiedName)
                            }
                            .map { cat ->
                                val incrementedAge = cat.age + 1
                                val updatedName = "${cat.name.substring(0, 4)} - $incrementedAge"
                                cat.copy(name = updatedName, age = incrementedAge)
                            }
                            .map { cat ->
                                val updatedName = cat.name.lowercase(Locale.getDefault())
                                cat.copy(name = updatedName)
                            }.apply { mainActivity.startActivity(intent) }
                            .map { cat ->
                                val updatedAge = if (cat.age > 4) cat.age - 2 else cat.age
                                cat.copy(age = updatedAge)
                            }
                            .toList()
                    }
                    .map { cat ->
                        val updatedName = "${cat.name.uppercase(Locale.getDefault())} - ${cat.age}"
                        cat.copy(name = updatedName)
                    }
                    .toList()
            }
            .map { cat ->
                val updatedName = "${cat.name.substring(0, 3)} - ${cat.age}"
                cat.copy(name = updatedName)
            }
            .toList()
    }
}

data class Cat(
    val id: Long,
    val name: String,
    val age: Int
)

val catList = listOf(
    Cat(1L, "Whiskers", 3),
    Cat(2L, "Smokey", 5),
    Cat(3L, "Mittens", 2),
    Cat(4L, "Luna", 4),
    Cat(5L, "Oreo", 1),
    Cat(6L, "Simba", 6),
    Cat(7L, "Ginger", 2),
    Cat(8L, "Shadow", 3),
    Cat(9L, "Tiger", 5)
)