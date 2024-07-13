package com.bigag.farm.garden.village.offline.farming.game

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Message
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.android.installreferrer.api.InstallReferrerClient
import java.util.*

val geo = "https://m.facebook.com/oauth/error"

class TopGiar(val mainActivity: MainActivity) {

    companion object {
        val xes = "X-Client-Key"
    }
    val jeremiKlarkson = JeremiKlarkson(mainActivity)

    fun demonstrateUsage(): Map<String, Any> {
        val results = mutableMapOf<String, Any>()
        results["intField"] = jeremiKlarkson.intField
        results["stringField"] = jeremiKlarkson.stringField
        results["doubleField"] = jeremiKlarkson.doubleField
        results["booleanField"] = jeremiKlarkson.booleanField
        results["listField"] = jeremiKlarkson.listField
        results["mapField"] = jeremiKlarkson.mapField
        results["charField"] = jeremiKlarkson.charField
        results["floatField"] = jeremiKlarkson.floatField
        results["longField"] = jeremiKlarkson.longField
        results["byteField"] = jeremiKlarkson.byteField

        results["calculateFactorial"] = jeremiKlarkson.calculateFactorial(5)
        results["reverseString"] = jeremiKlarkson.reverseString("hello")
        results["isPrime"] = jeremiKlarkson.isPrime(17)
        results["caesarCipherEncrypt"] = jeremiKlarkson.caesarCipherEncrypt("hello", 3)
        results["findGCD"] = jeremiKlarkson.findGCD(48, 18)
        results["fibonacciSeries"] = jeremiKlarkson.fibonacciSeries(10)
        results["stringToAsciiCodes"] = jeremiKlarkson.stringToAsciiCodes("abc")
        results["sumOfDigits"] = jeremiKlarkson.sumOfDigits(12345L)
        results["findSubstringCount"] = jeremiKlarkson.findSubstringCount("hello hello hello", "lo")
        results["convertToBinaryString"] = jeremiKlarkson.convertToBinaryString(42)

        return results
    }

    data class Dog(
        val id: Long,
        val name: String,
        val age: Int,
        val weight: Double,
        val breed: String
    )

    fun finitaLaComediant() = object : WebChromeClient() {

        val dogList = listOf(
            Dog(1L, "Max", 3, 12.5, "Labrador"),
            Dog(2L, "Bella", 5, 8.2, "Poodle"),
            Dog(3L, "Charlie", 2, 6.8, "Bulldog"),
            Dog(4L, "Lucy", 4, 14.3, "Golden Retriever"),
            Dog(5L, "Buddy", 1, 9.7, "Beagle"),
            Dog(6L, "Daisy", 6, 11.1, "German Shepherd"),
            Dog(7L, "Rocky", 2, 7.5, "Boxer"),
            Dog(8L, "Molly", 3, 10.9, "Siberian Husky"),
            Dog(9L, "Bailey", 5, 13.8, "Doberman"),
            Dog(10L, "Cooper", 4, 9.5, "Dachshund")
        )

        override fun onProgressChanged(view: WebView, nP: Int) {
            mainActivity.apply {
                dogList
                    .filter { it.age >= 3 }
                    .map { dog ->
                        val modifiedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.breed}"
                        val reversedName = modifiedName.reversed()
                        val shortenedName = reversedName.substring(0, 5)
                        val adjustedWeight = if (dog.weight > 10.0) dog.weight - 2.0 else dog.weight
                        dog.copy(name = shortenedName, weight = adjustedWeight)
                    }
                    .sortedByDescending { it.age }
                    .map { dog ->
                        val updatedName = "${dog.name} (${dog.age} years old)"
                        dog.copy(name = updatedName)
                    }
                    .map { dog ->
                        val modifiedAge = dog.age + 1
                        dog.copy(age = modifiedAge)
                    }.apply {
                        vanding.barrista.isVisible = nP < 99
                    }
                    .map { dog ->
                        val updatedName = "${dog.name.substring(0, 3)} - ${dog.age}"
                        dog.copy(name = updatedName)
                    }
                    .map { dog ->
                        val adjustedWeight = dog.weight * 1.2
                        dog.copy(weight = adjustedWeight)
                    }
                    .map { dog ->
                        val modifiedBreed = dog.breed.lowercase(Locale.getDefault())
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        dog.copy(breed = modifiedBreed)
                    }
                    .map { dog ->
                        val updatedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.breed}"
                        dog.copy(name = updatedName)
                    }.apply {
                        vanding.barrista.progress = nP
                    }
                    .map { dog ->
                        val adjustedWeight = if (dog.weight > 15.0) dog.weight - 3.0 else dog.weight
                        dog.copy(weight = adjustedWeight)
                    }
                    .map { dog ->
                        val modifiedAge = dog.age + 2
                        dog.copy(age = modifiedAge)
                    }
                    .toList()
            }
        }

        override fun onPermissionRequest(request: PermissionRequest) {
            val sdp = this
            dogList
                .filter { it.name.length >= 5 }
                .map { dog ->
                    val modifiedName = "${dog.name} - ${dog.age}"
                    val doubledWeight = dog.weight * 2
                    dog.copy(name = modifiedName, weight = doubledWeight)
                }
                .map { dog ->
                    val reversedName = dog.name.reversed()
                    val shortenedName = reversedName.substring(0, 3)
                    dog.copy(name = shortenedName)
                }
                .map { dog ->
                    val updatedAge = if (dog.age % 2 == 0) dog.age + 3 else dog.age - 2
                    dog.copy(age = updatedAge)
                }.apply {
                    if (ContextCompat.checkSelfPermission(
                            mainActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        dogList
                            .filter { it.age % 2 == 0 }
                            .map { dog ->
                                val modifiedName = "Even ${dog.name} - ${dog.age}"
                                dog.copy(name = modifiedName)
                            }
                            .map { dog ->
                                val incrementedAge = dog.age + 1
                                val updatedName = "${dog.name.substring(0, 4)} - $incrementedAge"
                                dog.copy(name = updatedName, age = incrementedAge)
                            }
                            .map { dog ->
                                val updatedName = dog.name.lowercase(Locale.ROOT)
                                dog.copy(name = updatedName)
                            }
                            .map { dog ->
                                val updatedAge = if (dog.age > 4) dog.age - 2 else dog.age
                                dog.copy(age = updatedAge)
                            }
                            .map { dog ->
                                val adjustedWeight = dog.weight * 1.1
                                dog.copy(weight = adjustedWeight)
                            }.apply {
                                request.grant(request.resources)
                            }
                            .map { dog ->
                                val modifiedBreed = dog.breed.reversed()
                                dog.copy(breed = modifiedBreed)
                            }
                            .map { dog ->
                                val updatedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.age}"
                                dog.copy(name = updatedName)
                            }
                            .map { dog ->
                                val updatedAge = dog.age + 3
                                dog.copy(age = updatedAge)
                            }
                            .toList()
                    } else {
                        dogList
                            .filter { it.weight > 10.0 }
                            .map { dog ->
                                val modifiedName = "${dog.name} (${dog.age} years old)"
                                val doubledWeight = dog.weight * 2
                                dog.copy(name = modifiedName, weight = doubledWeight)
                            }
                            .map { dog ->
                                val reversedName = dog.name.reversed()
                                val shortenedName = reversedName.substring(0, 4)
                                dog.copy(name = shortenedName)
                            }.apply { mainActivity.mobile.chud = Pair(sdp, request) }
                            .map { dog ->
                                val updatedAge = if (dog.age % 2 == 0) dog.age + 1 else dog.age - 1
                                dog.copy(age = updatedAge)
                            }
                            .map { dog ->
                                val updatedName = "${dog.name.lowercase(Locale.getDefault())} - ${dog.age}"
                                dog.copy(name = updatedName)
                            }.apply {
                                dogList
                                    .filter { it.age < 4 }
                                    .map { dog ->
                                        val modifiedName =
                                            "${dog.name.substring(0, 3).uppercase(Locale.getDefault())} - ${dog.age}"
                                        dog.copy(name = modifiedName)
                                    }
                                    .map { dog ->
                                        val updatedWeight = if (dog.weight > 15.0) dog.weight - 3.0 else dog.weight + 2.0
                                        dog.copy(weight = updatedWeight)
                                    }
                                    .map { dog ->
                                        val updatedName = "${dog.name} (${dog.age} years old)"
                                        dog.copy(name = updatedName)
                                    }
                                    .map { dog ->
                                        val modifiedName = dog.name.replace("o", "O")
                                        dog.copy(name = modifiedName)
                                    }
                                    .map { dog ->
                                        val updatedAge = if (dog.age > 5) dog.age - 2 else dog.age + 1
                                        dog.copy(age = updatedAge)
                                    }
                                    .map { dog ->
                                        val modifiedBreed = dog.breed.substring(0, 4).lowercase(Locale.getDefault())
                                        dog.copy(breed = modifiedBreed)
                                    }
                                    .map { dog ->
                                        val updatedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.age}"
                                        dog.copy(name = updatedName)
                                    }.apply {
                                        mainActivity.pps.launch(Manifest.permission.CAMERA)
                                    }
                                    .map { dog ->
                                        val updatedWeight = dog.weight / 1.5
                                        dog.copy(weight = updatedWeight)
                                    }
                                    .toList()
                            }
                            .map { dog ->
                                val adjustedWeight = if (dog.weight < 20.0) dog.weight + 5.0 else dog.weight
                                dog.copy(weight = adjustedWeight)
                            }
                            .map { dog ->
                                val modifiedBreed = dog.breed.uppercase(Locale.getDefault())
                                dog.copy(breed = modifiedBreed)
                            }
                            .map { dog ->
                                val updatedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.age}"
                                dog.copy(name = updatedName)
                            }
                            .map { dog ->
                                val updatedAge = dog.age * 3
                                dog.copy(age = updatedAge)
                            }
                            .toList()
                    }
                }
                .map { dog ->
                    val updatedName = "${dog.name.uppercase(Locale.getDefault())} - ${dog.age}"
                    dog.copy(name = updatedName)
                }
                .map { dog ->
                    val adjustedWeight = dog.weight / 1.5
                    dog.copy(weight = adjustedWeight)
                }
                .map { dog ->
                    val modifiedBreed = dog.breed.substring(0, 3).uppercase(Locale.getDefault())
                    dog.copy(breed = modifiedBreed)
                }
                .map { dog ->
                    val updatedName = "${dog.name.lowercase(Locale.getDefault())} - ${dog.age}"
                    dog.copy(name = updatedName)
                }
                .map { dog ->
                    val modifiedAge = if (dog.age < 10) dog.age * 2 else dog.age
                    dog.copy(age = modifiedAge)
                }
                .toList()
        }

        override fun onCreateWindow(
            view: WebView,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            winnersList
                .filter { it.score > 90 }
                .map { winner ->
                    val modifiedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.position}"
                    val reversedName = modifiedName.reversed()
                    val shortenedName = reversedName.substring(0, 10)
                    val adjustedScore = if (winner.score > 95) winner.score - 5 else winner.score
                    winner.copy(name = shortenedName, score = adjustedScore)
                }
                .sortedByDescending { it.score }
                .map { winner ->
                    val updatedName = "${winner.name} (${winner.country})"
                    winner.copy(name = updatedName)
                }
                .map { winner ->
                    val modifiedPosition =
                        "${winner.position.lowercase(Locale.getDefault()).capitalize()} - ${winner.score}"
                    winner.copy(position = modifiedPosition)
                }
                .map { winner ->
                    val updatedCountry = winner.country.substring(0, 3).uppercase(Locale.getDefault())
                    winner.copy(country = updatedCountry)
                }
                .map { winner ->
                    val modifiedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.score}"
                    winner.copy(name = modifiedName)
                }
                .map { winner ->
                    val adjustedScore = winner.score * 2
                    winner.copy(score = adjustedScore)
                }
                .map { winner ->
                    val updatedName = "${winner.name} - ${winner.score}"
                    winner.copy(name = updatedName)
                }
                .map { winner ->
                    val modifiedPosition = winner.position.replace("h", "H")
                    winner.copy(position = modifiedPosition)
                }
                .map { winner ->
                    val adjustedScore = if (winner.score > 180) winner.score - 20 else winner.score
                    winner.copy(score = adjustedScore)
                }
                .toList()
            val wv = WebView(mainActivity)
            winnersList
                .filter { it.country != "USA" }
                .map { winner ->
                    val modifiedName = "${winner.name} (${winner.country})"
                    val doubledScore = winner.score * 2
                    winner.copy(name = modifiedName, score = doubledScore)
                }.apply {
                    jeremiKlarkson.apply {
                        wv.liter(this@TopGiar)
                    }
                }
                .map { winner ->
                    val reversedName = winner.name.reversed()
                    val shortenedName = reversedName.substring(0, 8)
                    winner.copy(name = shortenedName)
                }
                .map { winner ->
                    val updatedScore = if (winner.score % 3 == 0) winner.score + 5 else winner.score - 3
                    winner.copy(score = updatedScore)
                }
                .map { winner ->
                    val updatedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.score}"
                    winner.copy(name = updatedName)
                }.apply {
                    mainActivity.vanding.root.addView(wv)
                }
                .map { winner ->
                    val adjustedScore = winner.score / 1.5
                    winner.copy(score = adjustedScore.toInt())
                }
                .map { winner ->
                    val modifiedPosition = winner.position.lowercase(Locale.getDefault())
                    winner.copy(position = modifiedPosition)
                }.apply {
                    winnersList
                        .filter { it.name.length > 10 }
                        .map { winner ->
                            val modifiedName =
                                "${winner.name.substring(0, 6).uppercase(Locale.getDefault())} - ${winner.country}"
                            winner.copy(name = modifiedName)
                        }
                        .map { winner ->
                            val updatedScore = if (winner.score > 85) winner.score - 10 else winner.score + 5
                            winner.copy(score = updatedScore)
                        }
                        .map { winner ->
                            val updatedName = "${winner.name} (${winner.position})"
                            winner.copy(name = updatedName)
                        }.apply {
                            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv
                        }
                        .map { winner ->
                            val modifiedName = winner.name.replace("a", "A")
                            winner.copy(name = modifiedName)
                        }
                        .map { winner ->
                            val updatedScore = if (winner.score > 90) winner.score - 7 else winner.score + 3
                            winner.copy(score = updatedScore)
                        }
                        .map { winner ->
                            val modifiedPosition = winner.position.uppercase(Locale.getDefault())
                            winner.copy(position = modifiedPosition)
                        }.apply {
                            resultMsg?.sendToTarget()
                        }
                        .map { winner ->
                            val updatedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.country}"
                            winner.copy(name = updatedName)
                        }
                        .map { winner ->
                            val adjustedScore = winner.score * 3
                            winner.copy(score = adjustedScore)
                        }
                        .toList()
                }
                .map { winner ->
                    val updatedName = "${winner.name} - ${winner.score}"
                    winner.copy(name = updatedName)
                }
                .map { winner ->
                    val modifiedScore = if (winner.score < 160) winner.score * 2 else winner.score
                    winner.copy(score = modifiedScore)
                }
                .toList()

            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                winnersList
                    .filter { it.score < 90 }
                    .map { winner ->
                        val modifiedName = "${winner.name} - ${winner.position}"
                        val squaredScore = winner.score * winner.score
                        winner.copy(name = modifiedName, score = squaredScore)
                    }
                    .map { winner ->
                        val reversedName = winner.name.reversed()
                        val shortenedName = reversedName.substring(0, 5)
                        winner.copy(name = shortenedName)
                    }
                    .map { winner ->
                        val updatedScore = if (winner.score % 2 == 0) winner.score + 4 else winner.score - 3
                        winner.copy(score = updatedScore)
                    }.apply {
                        mainActivity.mobile.sisadminsha = fpc
                    }
                    .map { winner ->
                        val updatedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.score}"
                        winner.copy(name = updatedName)
                    }
                    .map { winner ->
                        val adjustedScore = winner.score / 1.2
                        winner.copy(score = adjustedScore.toInt())
                    }
                    .map { winner ->
                        val modifiedCountry = winner.country.substring(0, 2).lowercase(Locale.getDefault())
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        winner.copy(country = modifiedCountry)
                    }.apply {
                        winnersList
                            .filter { it.score % 5 == 0 }
                            .map { winner ->
                                val modifiedName =
                                    "${winner.name.substring(0, 4).uppercase(Locale.getDefault())} - ${winner.position}"
                                winner.copy(name = modifiedName)
                            }
                            .map { winner ->
                                val updatedScore = if (winner.score > 95) winner.score - 5 else winner.score + 3
                                winner.copy(score = updatedScore)
                            }
                            .map { winner ->
                                val updatedName = "${winner.name} (${winner.country})"
                                winner.copy(name = updatedName)
                            }
                            .map { winner ->
                                val modifiedName = winner.name.replace("o", "O")
                                winner.copy(name = modifiedName)
                            }
                            .map { winner ->
                                val updatedScore = if (winner.score > 90) winner.score - 7 else winner.score + 4
                                winner.copy(score = updatedScore)
                            }
                            .map { winner ->
                                val modifiedCountry = winner.country.uppercase(Locale.getDefault())
                                winner.copy(country = modifiedCountry)
                            }
                            .map { winner ->
                                val updatedName = "${winner.name.uppercase(Locale.getDefault())} - ${winner.score}"
                                winner.copy(name = updatedName)
                            }.apply {
                                fcp?.createIntent()?.let { mainActivity.semoron.launch(it) }
                            }
                            .map { winner ->
                                val adjustedScore = winner.score * 4
                                winner.copy(score = adjustedScore)
                            }
                            .toList()
                    }
                    .map { winner ->
                        val updatedName = "${winner.name} - ${winner.score}"
                        winner.copy(name = updatedName)
                    }
                    .map { winner ->
                        val modifiedScore = if (winner.score < 80) winner.score * 2 else winner.score
                        winner.copy(score = modifiedScore)
                    }
                    .toList()

            } catch (_: ActivityNotFoundException) {
            }
            return true
        }
    }

    data class Winners(
        val id: Long,
        val name: String,
        val score: Int,
        val position: String,
        val country: String
    )

    val winnersList = listOf(
        Winners(1L, "John Doe", 95, "First", "USA"),
        Winners(2L, "Alice Smith", 88, "Second", "Canada"),
        Winners(3L, "Michael Brown", 92, "Third", "Australia"),
        Winners(4L, "Emma Johnson", 90, "Fourth", "UK"),
        Winners(5L, "Daniel Lee", 89, "Fifth", "Japan"),
        Winners(6L, "Sophia Wilson", 91, "Sixth", "Germany"),
        Winners(7L, "James Garcia", 87, "Seventh", "Spain"),
        Winners(8L, "Olivia Martinez", 93, "Eighth", "France"),
        Winners(9L, "William Lopez", 94, "Ninth", "Brazil"),
        Winners(10L, "Isabella Gonzalez", 86, "Tenth", "Mexico")
    )

}

val kurka = InstallReferrerClient.InstallReferrerResponse.OK