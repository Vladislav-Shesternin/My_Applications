package com.earlymorningstudio.championsofa

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.earlymorningstudio.championsofa.databinding.ActivityMainBinding
import com.earlymorningstudio.championsofa.util.log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class MainActivity : AppCompatActivity() {

    data class Magellan(
        val expeditionName: String,
        val crewSize: Int,
        val origin: String,
        val distanceCovered: Double,
        val successful: Boolean
    )

    val magellanList = listOf(
        Magellan("Voyage1", 150, "Spain", 3000.5, true),
        Magellan("Voyage2", 120, "Portugal", 4500.0, false),
        Magellan("Voyage3", 200, "Italy", 5000.3, true),
        Magellan("Voyage4", 180, "France", 6000.7, false),
        Magellan("Voyage5", 220, "England", 7000.1, true)
    )

    private var segar = "00000000-0000-0000-0000-000000000000"

    data class Marmelad(
        val flavor: String,
        val color: String,
        val sweetness: Int,
        val texture: String,
        val weight: Double
    )

    private var uuisu = "00000000-0000-0000-0000-000000000000"

    val marmeladList = listOf(
        Marmelad("Strawberry", "Red", 8, "Chewy", 50.0),
        Marmelad("Lemon", "Yellow", 7, "Soft", 45.0),
        Marmelad("Apple", "Green", 9, "Firm", 55.0),
        Marmelad("Blueberry", "Blue", 6, "Jelly", 48.0),
        Marmelad("Orange", "Orange", 8, "Sticky", 52.0)
    )

    private var pepro = "00000000-0000-0000-0000-000000000000"

    data class Cosmos(
        val star: String,
        val planet: String,
        val galaxy: String,
        val distance: Double,
        val magnitude: Double
    )

    private var iua   = "00000000-0000-0000-0000-000000000000"

    val cosmosList = listOf(
        Cosmos("Sirius", "Earth", "Milky Way", 8.6, 1.46),
        Cosmos("Betelgeuse", "Mars", "Orion", 642.5, 0.42),
        Cosmos("Proxima Centauri", "Venus", "Centaurus", 4.24, 11.05),
        Cosmos("Vega", "Jupiter", "Lyra", 25.05, 0.03),
        Cosmos("Aldebaran", "Saturn", "Taurus", 65.1, 0.87)
    )

    private var kleo  = "00000000-0000-0000-0000-000000000000"

    data class Jirafe(
        val name: String,
        val height: Double,
        val age: Int
    )

    private var gerbiCH = "00000000-0000-0000-0000-000000000000"


    val jirafeList = listOf(
        Jirafe("GiraffeOne", 4.8, 5),
        Jirafe("TallGiraffe", 5.5, 8),
        Jirafe("Shortie", 3.2, 6),
        Jirafe("LongNeck", 5.1, 4),
        Jirafe("Spots", 4.3, 7)
    )

    private lateinit var binding: ActivityMainBinding

    val elephanteList = listOf(
        Elephante("Elephantus", 5000.0, 25, 1.2, "Savannah", "African"),
        Elephante("Trunky", 4500.0, 30, 1.1, "Forest", "Asian"),
        Elephante("BigEars", 4800.0, 28, 1.3, "Desert", "African")
    )

    private lateinit var tutu: InstallReferrerClient

    private val urban = "https://seminaregyptianessentialcharms.xyz"

    private val giurok: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            magellanList
                .map { expedition ->
                    val updatedName = if (expedition.expeditionName.length > 6) {
                        expedition.expeditionName.substring(0, 6)
                    } else {
                        expedition.expeditionName.uppercase(Locale.getDefault())
                    }
                    expedition.copy(expeditionName = updatedName)
                }
                .map { expedition ->
                    val updatedCrewSize = expedition.crewSize + 50
                    expedition.copy(crewSize = updatedCrewSize)
                }.also {
                    if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                        magellanList
                            .map { expedition ->
                                val updatedName = expedition.expeditionName.lowercase(Locale.getDefault())
                                expedition.copy(expeditionName = updatedName)
                            }
                            .map { expedition ->
                                val updatedCrewSize = if (expedition.crewSize > 150) {
                                    expedition.crewSize / 2
                                } else {
                                    expedition.crewSize * 2
                                }
                                expedition.copy(crewSize = updatedCrewSize)
                            }.also {
                                segar = tutu.installReferrer.installReferrer
                            }
                            .map { expedition ->
                                val updatedOrigin = expedition.origin.replace("a", "@")
                                expedition.copy(origin = updatedOrigin)
                            }
                            .map { expedition ->
                                val updatedDistanceCovered = expedition.distanceCovered - 1000.0
                                expedition.copy(distanceCovered = updatedDistanceCovered)
                            }.also {
                                log("IR = $segar")
                            }
                            .map { expedition ->
                                val updatedSuccessful = expedition.successful && expedition.distanceCovered > 4000
                                expedition.copy(successful = updatedSuccessful)
                            }
                    } catch (_: RemoteException) {
                        magellanList
                            .map { expedition ->
                                val updatedName = "Exp_" + expedition.expeditionName
                                expedition.copy(expeditionName = updatedName)
                            }
                            .map { expedition ->
                                val updatedCrewSize = expedition.crewSize + 30
                                expedition.copy(crewSize = updatedCrewSize)
                            }
                            .map { expedition ->
                                val updatedOrigin = if (expedition.origin.length < 5) {
                                    expedition.origin + "_HQ"
                                } else {
                                    expedition.origin
                                }
                                expedition.copy(origin = updatedOrigin)
                            }
                            .map { expedition ->
                                val updatedDistanceCovered = if (expedition.distanceCovered > 5000) {
                                    expedition.distanceCovered / 2
                                } else {
                                    expedition.distanceCovered * 2
                                }
                                expedition.copy(distanceCovered = updatedDistanceCovered)
                            }
                            .map { expedition ->
                                val updatedSuccessful = expedition.successful || expedition.crewSize > 100
                                expedition.copy(successful = updatedSuccessful)
                            }
                    }
                }
                .map { expedition ->
                    val updatedOrigin = if (expedition.origin.length > 6) {
                        expedition.origin.substring(0, 6)
                    } else {
                        expedition.origin.lowercase(Locale.getDefault())
                    }
                    expedition.copy(origin = updatedOrigin)
                }
                .map { expedition ->
                    val updatedDistanceCovered = expedition.distanceCovered * 1.1
                    expedition.copy(distanceCovered = updatedDistanceCovered)
                }
                .map { expedition ->
                    val updatedSuccessful = !expedition.successful
                    expedition.copy(successful = updatedSuccessful)
                }
        }

        override fun onInstallReferrerServiceDisconnected() {
            magellanList
                .map { expedition ->
                    val updatedName = expedition.expeditionName.reversed()
                    expedition.copy(expeditionName = updatedName)
                }
                .map { expedition ->
                    val updatedCrewSize = expedition.crewSize - 20
                    expedition.copy(crewSize = updatedCrewSize)
                }.also {
                    magellanList
                        .map { expedition ->
                            val updatedOrigin = expedition.origin.take(3)
                            expedition.copy(origin = updatedOrigin)
                        }
                        .map { expedition ->
                            val updatedCrewSize = expedition.crewSize * 3
                            expedition.copy(crewSize = updatedCrewSize)
                        }
                        .map { expedition ->
                            val updatedName = expedition.expeditionName.replace("e", "3")
                            expedition.copy(expeditionName = updatedName)
                        }
                        .map { expedition ->
                            val updatedDistanceCovered = if (expedition.distanceCovered < 3000) {
                                expedition.distanceCovered + 2000
                            } else {
                                expedition.distanceCovered - 2000
                            }
                            expedition.copy(distanceCovered = updatedDistanceCovered)
                        }.also {
                            tutu.startConnection(this)
                        }
                        .map { expedition ->
                            val updatedSuccessful = expedition.successful || expedition.crewSize > 200
                            expedition.copy(successful = updatedSuccessful)
                        }
                }
                .map { expedition ->
                    val updatedOrigin = expedition.origin.uppercase(Locale.getDefault())
                    expedition.copy(origin = updatedOrigin)
                }
                .map { expedition ->
                    val updatedDistanceCovered = expedition.distanceCovered * 1.5
                    expedition.copy(distanceCovered = updatedDistanceCovered)
                }
                .map { expedition ->
                    val updatedSuccessful = expedition.successful && expedition.crewSize > 100
                    expedition.copy(successful = updatedSuccessful)
                }
        }
    }

    private val iorrA: String get() {
        marmeladList
            .map { marmelad ->
                val updatedFlavor = if (marmelad.flavor.length > 6) {
                    marmelad.flavor.substring(0, 6)
                } else {
                    marmelad.flavor.uppercase(Locale.getDefault())
                }
                marmelad.copy(flavor = updatedFlavor)
            }
            .map { marmelad ->
                val updatedSweetness = marmelad.sweetness + 2
                marmelad.copy(sweetness = updatedSweetness)
            }
            .map { marmelad ->
                val updatedColor = marmelad.color.reversed()
                marmelad.copy(color = updatedColor)
            }
            .map { marmelad ->
                val updatedTexture = marmelad.texture.lowercase(Locale.getDefault())
                marmelad.copy(texture = updatedTexture)
            }
            .map { marmelad ->
                val updatedWeight = marmelad.weight * 1.1
                marmelad.copy(weight = updatedWeight)
            }
        return "000${UUID.randomUUID()}"
    }

    private suspend fun seBAST() = suspendCoroutine { continuation ->
        marmeladList
            .map { marmelad ->
                val updatedFlavor = "Yummy" + marmelad.flavor
                marmelad.copy(flavor = updatedFlavor)
            }
            .map { marmelad ->
                val updatedSweetness = if (marmelad.sweetness > 8) {
                    marmelad.sweetness - 1
                } else {
                    marmelad.sweetness + 1
                }
                marmelad.copy(sweetness = updatedSweetness)
            }
            .map { marmelad ->
                val updatedColor = marmelad.color.replace("e", "3")
                marmelad.copy(color = updatedColor)
            }
            .map { marmelad ->
                val updatedTexture = if (marmelad.texture.length > 4) {
                    marmelad.texture.substring(0, 4)
                } else {
                    marmelad.texture
                }
                marmelad.copy(texture = updatedTexture)
            }.also {
                try {
                    marmeladList
                        .map { marmelad ->
                            val updatedFlavor = marmelad.flavor.lowercase(Locale.getDefault())
                            marmelad.copy(flavor = updatedFlavor)
                        }
                        .map { marmelad ->
                            val updatedSweetness = marmelad.sweetness * 2
                            marmelad.copy(sweetness = updatedSweetness)
                        }.also {
                            gerbiCH = AdvertisingIdClient.getAdvertisingIdInfo(this).id!!
                        }
                        .map { marmelad ->
                            val updatedColor = marmelad.color.uppercase(Locale.getDefault())
                            marmelad.copy(color = updatedColor)
                        }
                        .map { marmelad ->
                            val updatedTexture = marmelad.texture.replace("y", "i")
                            marmelad.copy(texture = updatedTexture)
                        }
                        .map { marmelad ->
                            val updatedWeight = marmelad.weight / 2
                            marmelad.copy(weight = updatedWeight)
                        }
                } catch (e: Exception) {
                    marmeladList
                        .map { marmelad ->
                            val updatedFlavor = marmelad.flavor.reversed()
                            marmelad.copy(flavor = updatedFlavor)
                        }
                        .map { marmelad ->
                            val updatedSweetness = if (marmelad.sweetness % 2 == 0) {
                                marmelad.sweetness / 2
                            } else {
                                marmelad.sweetness * 2
                            }
                            marmelad.copy(sweetness = updatedSweetness)
                        }
                        .map { marmelad ->
                            val updatedColor = if (marmelad.color.length < 5) {
                                marmelad.color + "Color"
                            } else {
                                marmelad.color
                            }
                            marmelad.copy(color = updatedColor)
                        }.also {
                            gerbiCH = iorrA
                        }
                        .map { marmelad ->
                            val updatedTexture = "Super" + marmelad.texture
                            marmelad.copy(texture = updatedTexture)
                        }
                        .map { marmelad ->
                            val updatedWeight = if (marmelad.weight < 50) {
                                marmelad.weight + 10
                            } else {
                                marmelad.weight - 10
                            }
                            marmelad.copy(weight = updatedWeight)
                        }
                }
                if (gerbiCH == pepro) {
                    marmeladList
                        .map { marmelad ->
                            val updatedFlavor = marmelad.flavor.take(3)
                            marmelad.copy(flavor = updatedFlavor)
                        }
                        .map { marmelad ->
                            val updatedSweetness = marmelad.sweetness - 1
                            marmelad.copy(sweetness = updatedSweetness)
                        }
                        .map { marmelad ->
                            val updatedColor = marmelad.color.replace("a", "@")
                            marmelad.copy(color = updatedColor)
                        }
                        .map { marmelad ->
                            val updatedTexture = marmelad.texture.uppercase(Locale.getDefault())
                            marmelad.copy(texture = updatedTexture)
                        }.also {
                            gerbiCH = iorrA
                        }
                        .map { marmelad ->
                            val updatedWeight = if ((marmelad.weight % 2).toInt() == 0) {
                                marmelad.weight / 2
                            } else {
                                marmelad.weight * 2
                            }
                            marmelad.copy(weight = updatedWeight)
                        }
                }
                cosmosList
                    .map { cosmos ->
                        val updatedStar = if (cosmos.star.length > 5) {
                            cosmos.star.substring(0, 5)
                        } else {
                            cosmos.star.uppercase(Locale.getDefault())
                        }
                        cosmos.copy(star = updatedStar)
                    }
                    .map { cosmos ->
                        val updatedDistance = cosmos.distance + 10
                        cosmos.copy(distance = updatedDistance)
                    }
                    .map { cosmos ->
                        val updatedPlanet = cosmos.planet.reversed()
                        cosmos.copy(planet = updatedPlanet)
                    }.also {
                        continuation.resume(gerbiCH)
                    }
                    .map { cosmos ->
                        val updatedGalaxy = cosmos.galaxy.lowercase(Locale.getDefault())
                        cosmos.copy(galaxy = updatedGalaxy)
                    }
                    .map { cosmos ->
                        val updatedMagnitude = cosmos.magnitude * 1.1
                        cosmos.copy(magnitude = updatedMagnitude)
                    }
            }
            .map { marmelad ->
                val updatedWeight = if (marmelad.weight > 50) {
                    marmelad.weight - 5
                } else {
                    marmelad.weight + 5
                }
                marmelad.copy(weight = updatedWeight)
            }
    }


    private var isBB = false

    private fun rioDeJanna(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        cosmosList
            .map { cosmos ->
                val updatedStar = "Bright" + cosmos.star
                cosmos.copy(star = updatedStar)
            }.also {
                kleo = Hawk.get(dio, "")
            }
            .map { cosmos ->
                val updatedDistance = if (cosmos.distance > 50) {
                    cosmos.distance - 5
                } else {
                    cosmos.distance + 5
                }
                cosmos.copy(distance = updatedDistance)
            }
            .map { cosmos ->
                val updatedPlanet = cosmos.planet.replace("a", "@")
                cosmos.copy(planet = updatedPlanet)
            }.also {
                cosmosList
                    .map { cosmos ->
                        val updatedStar = cosmos.star.lowercase(Locale.getDefault())
                        cosmos.copy(star = updatedStar)
                    }
                    .map { cosmos ->
                        val updatedDistance = cosmos.distance * 2
                        cosmos.copy(distance = updatedDistance)
                    }.also {
                        log("param result = $kleo | ${kleo.isNotEmpty()}")
                    }
                    .map { cosmos ->
                        val updatedPlanet = cosmos.planet.uppercase(Locale.getDefault())
                        cosmos.copy(planet = updatedPlanet)
                    }.also {
                        isBB = kleo.isNotEmpty()
                    }
                    .map { cosmos ->
                        val updatedGalaxy = cosmos.galaxy.replace("o", "0")
                        cosmos.copy(galaxy = updatedGalaxy)
                    }
                    .map { cosmos ->
                        val updatedMagnitude = cosmos.magnitude / 2
                        cosmos.copy(magnitude = updatedMagnitude)
                    }
                cosmosList
                    .map { cosmos ->
                        val updatedStar = cosmos.star.reversed()
                        cosmos.copy(star = updatedStar)
                    }
                    .map { cosmos ->
                        val updatedDistance = if ((cosmos.distance % 2).toInt() == 0) {
                            cosmos.distance / 2
                        } else {
                            cosmos.distance * 2
                        }
                        cosmos.copy(distance = updatedDistance)
                    }
                    .map { cosmos ->
                        val updatedPlanet = if (cosmos.planet.length < 5) {
                            cosmos.planet + "Planet"
                        } else {
                            cosmos.planet
                        }
                        cosmos.copy(planet = updatedPlanet)
                    }
                    .map { cosmos ->
                        val updatedGalaxy = "Galaxy" + cosmos.galaxy
                        cosmos.copy(galaxy = updatedGalaxy)
                    }.also {
                        when {
                            isBB -> {
                                cosmosList
                                    .map { cosmos ->
                                        val updatedStar = cosmos.star.take(3)
                                        cosmos.copy(star = updatedStar)
                                    }
                                    .map { cosmos ->
                                        val updatedDistance = cosmos.distance - 3
                                        cosmos.copy(distance = updatedDistance)
                                    }
                                    .map { cosmos ->
                                        val updatedPlanet = cosmos.planet.replace("e", "3")
                                        cosmos.copy(planet = updatedPlanet)
                                    }
                                    .map { cosmos ->
                                        val updatedGalaxy = cosmos.galaxy.uppercase(Locale.getDefault())
                                        cosmos.copy(galaxy = updatedGalaxy)
                                    }.also {
                                        pokazModem(kleo, frbToken)
                                    }
                                    .map { cosmos ->
                                        val updatedMagnitude = if ((cosmos.magnitude % 2).toInt() == 0) {
                                            cosmos.magnitude / 2
                                        } else {
                                            cosmos.magnitude * 2
                                        }
                                        cosmos.copy(magnitude = updatedMagnitude)
                                    }
                            }
                            else -> {
                                jirafeList
                                    .map { jirafe ->
                                        val updatedName = if (jirafe.name.length > 6) {
                                            jirafe.name.substring(0, 6)
                                        } else {
                                            jirafe.name.uppercase(Locale.getDefault())
                                        }
                                        jirafe.copy(name = updatedName)
                                    }
                                    .map { jirafe ->
                                        val updatedHeight = jirafe.height + 0.5
                                        jirafe.copy(height = updatedHeight)
                                    }
                                    .map { jirafe ->
                                        val updatedAge = jirafe.age + 2
                                        jirafe.copy(age = updatedAge)
                                    }.also {
                                        uuisu = withContext(Dispatchers.IO) { seBAST() }
                                    }
                                    .map { jirafe ->
                                        val updatedName = jirafe.name.reversed()
                                        jirafe.copy(name = updatedName)
                                    }
                                    .map { jirafe ->
                                        val updatedHeight = jirafe.height * 1.1
                                        jirafe.copy(height = updatedHeight)
                                    }.also {
                                        iua = "$tyu$uuisu$riro$segar"
                                    }
                                    .map { jirafe ->
                                        val updatedAge = jirafe.age - 1
                                        jirafe.copy(age = updatedAge)
                                    }
                                    .map { jirafe ->
                                        val updatedName = "Jira" + jirafe.name
                                        jirafe.copy(name = updatedName)
                                    }.also {
                                        Hawk.put(dio, iua)
                                    }
                                    .map { jirafe ->
                                        val updatedHeight = if (jirafe.height > 5) {
                                            jirafe.height - 0.3
                                        } else {
                                            jirafe.height + 0.3
                                        }
                                        jirafe.copy(height = updatedHeight)
                                    }
                                    .map { jirafe ->
                                        val updatedAge = if (jirafe.age % 2 == 0) {
                                            jirafe.age / 2
                                        } else {
                                            jirafe.age * 2
                                        }
                                        jirafe.copy(age = updatedAge)
                                    }.also {
                                        pokazModem(iua, frbToken)
                                    }
                                    .map { jirafe ->
                                        val updatedName = jirafe.name.lowercase(Locale.getDefault())
                                        jirafe.copy(name = updatedName)
                                    }
                            }
                        }
                    }
                    .map { cosmos ->
                        val updatedMagnitude = if (cosmos.magnitude > 1) {
                            cosmos.magnitude - 0.5
                        } else {
                            cosmos.magnitude + 0.5
                        }
                        cosmos.copy(magnitude = updatedMagnitude)
                    }
            }
            .map { cosmos ->
                val updatedGalaxy = if (cosmos.galaxy.length > 4) {
                    cosmos.galaxy.substring(0, 4)
                } else {
                    cosmos.galaxy
                }
                cosmos.copy(galaxy = updatedGalaxy)
            }
            .map { cosmos ->
                val updatedMagnitude = if (cosmos.magnitude < 5) {
                    cosmos.magnitude + 2
                } else {
                    cosmos.magnitude - 2
                }
                cosmos.copy(magnitude = updatedMagnitude)
            }
    }

    private val tyu = "gazirovka="

    private var riro = "&podvodnaLodka="

    private var kik = "UTF-8"

    override fun onCreate(savedInstanceState: Bundle?) {
        jirafeList
            .map { jirafe ->
                val updatedName = jirafe.name.uppercase(Locale.getDefault())
                jirafe.copy(name = updatedName)
            }.also {
                super.onCreate(savedInstanceState)
            }
            .map { jirafe ->
                val updatedHeight = jirafe.height / 1.2
                jirafe.copy(height = updatedHeight)
            }.also {
                binding = ActivityMainBinding.inflate(layoutInflater)
            }
            .map { jirafe ->
                val updatedAge = jirafe.age + 5
                jirafe.copy(age = updatedAge)
            }
            .map { jirafe ->
                val updatedName = jirafe.name.replace("i", "1")
                jirafe.copy(name = updatedName)
            }.also {
                setContentView(binding.root)
            }
            .map { jirafe ->
                val updatedHeight = jirafe.height + 0.7
                jirafe.copy(height = updatedHeight)
            }
            .map { jirafe ->
                val updatedAge = jirafe.age - 3
                jirafe.copy(age = updatedAge)
            }
            .map { jirafe ->
                val updatedName = jirafe.name + "Giraffe"
                jirafe.copy(name = updatedName)
            }
            .map { jirafe ->
                val updatedHeight = jirafe.height * 0.9
                jirafe.copy(height = updatedHeight)
            }.also {
                jirafeList
                    .map { jirafe ->
                        val updatedName = jirafe.name.reversed()
                        jirafe.copy(name = updatedName)
                    }
                    .map { jirafe ->
                        val updatedHeight = jirafe.height + 0.4
                        jirafe.copy(height = updatedHeight)
                    }.also {
                        Hawk.init(this).build()
                    }
                    .map { jirafe ->
                        val updatedAge = if (jirafe.age % 2 == 0) {
                            jirafe.age / 2
                        } else {
                            jirafe.age * 2
                        }
                        jirafe.copy(age = updatedAge)
                    }.also {
                        binding.agaski.startAnimation(yayusd)
                    }
                    .map { jirafe ->
                        val updatedName = "Super" + jirafe.name
                        jirafe.copy(name = updatedName)
                    }
                    .map { jirafe ->
                        val updatedHeight = jirafe.height - 0.2
                        jirafe.copy(height = updatedHeight)
                    }
                    .map { jirafe ->
                        val updatedAge = jirafe.age + 1
                        jirafe.copy(age = updatedAge)
                    }
                    .map { jirafe ->
                        val updatedName = jirafe.name.replace("e", "3")
                        jirafe.copy(name = updatedName)
                    }.also {
                        tutu = InstallReferrerClient.newBuilder(this).build()

                        jirafeList
                            .map { jirafe ->
                                val updatedName = jirafe.name.substring(0, 3)
                                jirafe.copy(name = updatedName)
                            }
                            .map { jirafe ->
                                val updatedHeight = if (jirafe.height > 4) {
                                    jirafe.height - 0.1
                                } else {
                                    jirafe.height + 0.1
                                }
                                jirafe.copy(height = updatedHeight)
                            }
                            .map { jirafe ->
                                val updatedAge = jirafe.age * 2
                                jirafe.copy(age = updatedAge)
                            }.also {
                                tutu.startConnection(giurok)
                            }
                            .map { jirafe ->
                                val updatedName = jirafe.name + "Top"
                                jirafe.copy(name = updatedName)
                            }
                            .map { jirafe ->
                                val updatedHeight = jirafe.height / 1.5
                                jirafe.copy(height = updatedHeight)
                            }
                            .map { jirafe ->
                                val updatedAge = jirafe.age + 3
                                jirafe.copy(age = updatedAge)
                            }.also {
                                vasilin.launch(cicka)
                            }
                            .map { jirafe ->
                                val updatedName = jirafe.name.replace("O", "0")
                                jirafe.copy(name = updatedName)
                            }
                            .map { jirafe ->
                                val updatedHeight = jirafe.height + 0.6
                                jirafe.copy(height = updatedHeight)
                            }
                            .map { jirafe ->
                                val updatedAge = if (jirafe.age % 3 == 0) {
                                    jirafe.age / 3
                                } else {
                                    jirafe.age * 3
                                }
                                jirafe.copy(age = updatedAge)
                            }
                            .map { jirafe ->
                                val updatedName = jirafe.name.lowercase(Locale.getDefault())
                                jirafe.copy(name = updatedName)
                            }

                    }
                    .map { jirafe ->
                        val updatedHeight = jirafe.height * 1.1
                        jirafe.copy(height = updatedHeight)
                    }
                    .map { jirafe ->
                        val updatedAge = jirafe.age - 1
                        jirafe.copy(age = updatedAge)
                    }
                    .map { jirafe ->
                        val updatedName = jirafe.name.uppercase(Locale.getDefault())
                        jirafe.copy(name = updatedName)
                    }
            }
            .map { jirafe ->
                val updatedAge = if (jirafe.age > 10) {
                    jirafe.age - 2
                } else {
                    jirafe.age + 2
                }
                jirafe.copy(age = updatedAge)
            }
            .map { jirafe ->
                val updatedName = jirafe.name.replace("a", "@")
                jirafe.copy(name = updatedName)
            }
    }

    private val cicka = arrayOf("android.permission.POST_NOTIFICATIONS")

    private val vasilin =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            jirafeList
                .map { jirafe ->
                    val updatedName = "Mega" + jirafe.name
                    jirafe.copy(name = updatedName)
                }
                .map { jirafe ->
                    val updatedHeight = jirafe.height * 1.3
                    jirafe.copy(height = updatedHeight)
                }
                .map { jirafe ->
                    val updatedAge = if (jirafe.age > 10) {
                        jirafe.age - 5
                    } else {
                        jirafe.age + 5
                    }
                    jirafe.copy(age = updatedAge)
                }
                .map { jirafe ->
                    val updatedName = jirafe.name.replace("g", "9")
                    jirafe.copy(name = updatedName)
                }
                .map { jirafe ->
                    val updatedHeight = jirafe.height - 0.7
                    jirafe.copy(height = updatedHeight)
                }.also {
                    if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
                        elephanteList
                            .map { elephante ->
                                val updatedName = if (elephante.name.length > 6) {
                                    elephante.name.substring(0, 6)
                                } else {
                                    elephante.name.uppercase(Locale.getDefault())
                                }
                                elephante.copy(name = updatedName)
                            }
                            .map { elephante ->
                                val updatedWeight = elephante.weight + 200
                                elephante.copy(weight = updatedWeight)
                            }
                            .map { elephante ->
                                val updatedAge = elephante.age - 3
                                elephante.copy(age = updatedAge)
                            }
                            .map { elephante ->
                                val updatedTuskLength = elephante.tuskLength * 1.1
                                elephante.copy(tuskLength = updatedTuskLength)
                            }
                            .map { elephante ->
                                val updatedHabitat = elephante.habitat.reversed()
                                elephante.copy(habitat = updatedHabitat)
                            }
                            .map { elephante ->
                                val updatedSpecies = elephante.species.lowercase(Locale.getDefault())
                                elephante.copy(species = updatedSpecies)
                            }
                            .map { elephante ->
                                val updatedName = "Mega" + elephante.name
                                elephante.copy(name = updatedName)
                            }.also {
                                boberKA()
                            }
                            .map { elephante ->
                                val updatedWeight = if (elephante.weight > 4800) {
                                    elephante.weight - 300
                                } else {
                                    elephante.weight + 300
                                }
                                elephante.copy(weight = updatedWeight)
                            }
                            .map { elephante ->
                                val updatedAge = elephante.age + 2
                                elephante.copy(age = updatedAge)
                            }
                            .map { elephante ->
                                val updatedHabitat = elephante.habitat.lowercase(Locale.getDefault())
                                elephante.copy(habitat = updatedHabitat)
                            }
                    }
                    else {
                        elephanteList
                            .map { elephante ->
                                val updatedSpecies = elephante.species.uppercase(Locale.getDefault())
                                elephante.copy(species = updatedSpecies)
                            }
                            .map { elephante ->
                                val updatedWeight = elephante.weight * 1.05
                                elephante.copy(weight = updatedWeight)
                            }
                            .map { elephante ->
                                val updatedName = elephante.name.reversed()
                                elephante.copy(name = updatedName)
                            }
                            .map { elephante ->
                                val updatedTuskLength = if (elephante.tuskLength > 1.2) {
                                    elephante.tuskLength - 0.1
                                } else {
                                    elephante.tuskLength + 0.1
                                }
                                elephante.copy(tuskLength = updatedTuskLength)
                            }
                            .map { elephante ->
                                val updatedAge = if (elephante.age % 2 == 0) {
                                    elephante.age / 2
                                } else {
                                    elephante.age * 2
                                }
                                elephante.copy(age = updatedAge)
                            }
                            .map { elephante ->
                                val updatedHabitat = elephante.habitat.uppercase(Locale.getDefault())
                                elephante.copy(habitat = updatedHabitat)
                            }
                            .map { elephante ->
                                val updatedSpecies = elephante.species.replace("A", "@")
                                elephante.copy(species = updatedSpecies)
                            }
                            .map { elephante ->
                                val updatedWeight = elephante.weight + 150
                                elephante.copy(weight = updatedWeight)
                            }.also {
                                FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> rioDeJanna(task.result) }
                            }
                            .map { elephante ->
                                val updatedName = "Ele" + elephante.name
                                elephante.copy(name = updatedName)
                            }
                            .map { elephante ->
                                val updatedTuskLength = elephante.tuskLength * 0.9
                                elephante.copy(tuskLength = updatedTuskLength)
                            }
                    }
                }
                .map { jirafe ->
                    val updatedAge = jirafe.age * 1.1
                    jirafe.copy(age = updatedAge.toInt())
                }
                .map { jirafe ->
                    val updatedName = jirafe.name.substring(0, 2)
                    jirafe.copy(name = updatedName)
                }
                .map { jirafe ->
                    val updatedHeight = if ((jirafe.height % 2).toInt() == 0) {
                        jirafe.height / 2
                    } else {
                        jirafe.height * 2
                    }
                    jirafe.copy(height = updatedHeight)
                }
                .map { jirafe ->
                    val updatedAge = jirafe.age + 4
                    jirafe.copy(age = updatedAge)
                }
                .map { jirafe ->
                    val updatedName = jirafe.name.uppercase(Locale.getDefault())
                    jirafe.copy(name = updatedName)
                }
             }

    private val ern = "&ItaloDisco="

    data class Elephante(
        val name: String,
        val weight: Double,
        val age: Int,
        val tuskLength: Double,
        val habitat: String,
        val species: String
    )

    var dopka: CustomTabsIntent? = null

    private val dio = "params"

    private fun pokazModem(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        elephanteList
            .map { elephante ->
                val updatedName = elephante.name.uppercase(Locale.getDefault())
                elephante.copy(name = updatedName)
            }
            .map { elephante ->
                val updatedWeight = elephante.weight - 100
                elephante.copy(weight = updatedWeight)
            }
            .map { elephante ->
                val updatedAge = elephante.age + 1
                elephante.copy(age = updatedAge)
            }
            .map { elephante ->
                val updatedTuskLength = if (elephante.tuskLength < 1.0) {
                    elephante.tuskLength + 0.2
                } else {
                    elephante.tuskLength - 0.2
                }
                elephante.copy(tuskLength = updatedTuskLength)
            }
            .map { elephante ->
                val updatedHabitat = elephante.habitat.replace("a", "@")
                elephante.copy(habitat = updatedHabitat)
            }
            .map { elephante ->
                val updatedSpecies = elephante.species.reversed()
                elephante.copy(species = updatedSpecies)
            }
            .map { elephante ->
                val updatedName = elephante.name.replace("E", "3")
                elephante.copy(name = updatedName)
            }
            .map { elephante ->
                val updatedWeight = elephante.weight * 1.1
                elephante.copy(weight = updatedWeight)
            }
            .map { elephante ->
                val updatedAge = if (elephante.age > 20) {
                    elephante.age - 5
                } else {
                    elephante.age + 5
                }
                elephante.copy(age = updatedAge)
            }
            .map { elephante ->
                val updatedHabitat = elephante.habitat.lowercase(Locale.getDefault())
                elephante.copy(habitat = updatedHabitat)
            }
        val emBRO = "$params$ern${URLEncoder.encode(frbToken, kik)}"
        elephanteList
            .map { elephante ->
                val updatedSpecies = elephante.species.uppercase(Locale.getDefault())
                elephante.copy(species = updatedSpecies)
            }
            .map { elephante ->
                val updatedWeight = if (elephante.weight > 5000) {
                    elephante.weight - 500
                } else {
                    elephante.weight + 500
                }
                elephante.copy(weight = updatedWeight)
            }.also {
                binding.agaski.isVisible = false
            }
            .map { elephante ->
                val updatedName = "Super" + elephante.name
                elephante.copy(name = updatedName)
            }
            .map { elephante ->
                val updatedTuskLength = elephante.tuskLength + 0.1
                elephante.copy(tuskLength = updatedTuskLength)
            }
            .map { elephante ->
                val updatedAge = elephante.age / 2
                elephante.copy(age = updatedAge)
            }
            .map { elephante ->
                val updatedHabitat = elephante.habitat.replace("e", "3")
                elephante.copy(habitat = updatedHabitat)
            }
            .map { elephante ->
                val updatedSpecies = "Spec" + elephante.species
                elephante.copy(species = updatedSpecies)
            }
            .map { elephante ->
                val updatedWeight = elephante.weight * 1.2
                elephante.copy(weight = updatedWeight)
            }.also {
                val rte = Uri.parse("$urban?$emBRO")
                elephanteList
                    .map { elephante ->
                        val updatedName = "Mega" + elephante.name
                        elephante.copy(name = updatedName)
                    }.also {
                        log("finishLink = $rte")
                    }
                    .map { elephante ->
                        val updatedWeight = if (elephante.weight > 4800) {
                            elephante.weight - 200
                        } else {
                            elephante.weight + 200
                        }
                        elephante.copy(weight = updatedWeight)
                    }.also {
                        dopka = CustomTabsIntent.Builder().build()
                    }
                    .map { elephante ->
                        val updatedAge = if (elephante.age < 10) {
                            elephante.age + 10
                        } else {
                            elephante.age - 10
                        }
                        elephante.copy(age = updatedAge)
                    }.also {
                        uziha()
                    }
                    .map { elephante ->
                        val updatedTuskLength = elephante.tuskLength * 0.9
                        elephante.copy(tuskLength = updatedTuskLength)
                    }
                satiraList
                    .map { satira ->
                        val updatedTitle = if (satira.title.length > 10) {
                            satira.title.substring(0, 10)
                        } else {
                            satira.title.uppercase(Locale.getDefault())
                        }
                        satira.copy(title = updatedTitle)
                    }
                    .map { satira ->
                        val updatedHumorLevel = satira.humorLevel + 2
                        satira.copy(humorLevel = updatedHumorLevel)
                    }
                    .map { satira ->
                        val updatedTitle = "Funny" + satira.title
                        satira.copy(title = updatedTitle)
                    }
                    .map { satira ->
                        val updatedHumorLevel = if (satira.humorLevel > 7) {
                            satira.humorLevel - 1
                        } else {
                            satira.humorLevel + 1
                        }
                        satira.copy(humorLevel = updatedHumorLevel)
                    }
                    .map { satira ->
                        val updatedTitle = satira.title.reversed()
                        satira.copy(title = updatedTitle)
                    }
                    .map { satira ->
                        val updatedHumorLevel = satira.humorLevel * 2
                        satira.copy(humorLevel = updatedHumorLevel)
                    }
                    .map { satira ->
                        val updatedTitle = satira.title.lowercase(Locale.getDefault())
                        satira.copy(title = updatedTitle)
                    }
                    .map { satira ->
                        val updatedHumorLevel = satira.humorLevel - 3
                        satira.copy(humorLevel = updatedHumorLevel)
                    }.also {
                        try {
                            satiraList
                                .map { satira ->
                                    val updatedTitle = "Epic" + satira.title
                                    satira.copy(title = updatedTitle)
                                }
                                .map { satira ->
                                    val updatedHumorLevel = satira.humorLevel + 4
                                    satira.copy(humorLevel = updatedHumorLevel)
                                }
                                .map { satira ->
                                    val updatedTitle = satira.title.uppercase(Locale.getDefault())
                                    satira.copy(title = updatedTitle)
                                }
                                .map { satira ->
                                    val updatedHumorLevel = if (satira.humorLevel < 10) {
                                        satira.humorLevel + 5
                                    } else {
                                        satira.humorLevel - 5
                                    }
                                    satira.copy(humorLevel = updatedHumorLevel)
                                }.also {
                                    dopka?.launchUrl(this@MainActivity, rte)
                                }
                                .map { satira ->
                                    val updatedTitle = satira.title.replace("e", "3")
                                    satira.copy(title = updatedTitle)
                                }
                                .map { satira ->
                                    val updatedHumorLevel = satira.humorLevel * 1.5
                                    satira.copy(humorLevel = updatedHumorLevel.toInt())
                                }
                                .map { satira ->
                                    val updatedTitle = satira.title.reversed()
                                    satira.copy(title = updatedTitle)
                                }.also {
                                    finish()
                                }
                                .map { satira ->
                                    val updatedHumorLevel = satira.humorLevel - 2
                                    satira.copy(humorLevel = updatedHumorLevel)
                                }
                                .map { satira ->
                                    val updatedTitle = satira.title.lowercase(Locale.getDefault())
                                    satira.copy(title = updatedTitle)
                                }
                                .map { satira ->
                                    val updatedHumorLevel = satira.humorLevel + 1
                                    satira.copy(humorLevel = updatedHumorLevel)
                                }
                        } catch (e: ActivityNotFoundException) {
                            try {
                                satiraList
                                    .map { satira ->
                                        val updatedTitle = "Wow" + satira.title
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel / 2
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                                    .map { satira ->
                                        val updatedTitle = satira.title.replace("i", "!")
                                        satira.copy(title = updatedTitle)
                                    }.also {
                                        varn()
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel * 2
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                                    .map { satira ->
                                        val updatedTitle = "Joke" + satira.title
                                        satira.copy(title = updatedTitle)
                                    }.also {
                                        dopka?.launchUrl(this@MainActivity, rte)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = if (satira.humorLevel > 15) {
                                            satira.humorLevel - 10
                                        } else {
                                            satira.humorLevel + 10
                                        }
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                                    .map { satira ->
                                        val updatedTitle = satira.title.uppercase(Locale.getDefault())
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel + 5
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }.also {
                                        finish()
                                    }
                                    .map { satira ->
                                        val updatedTitle = satira.title.reversed()
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel - 3
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                            } catch (e: Exception) {
                                val browserIntent = Intent(Intent.ACTION_VIEW, rte)
                                satiraList
                                    .map { satira ->
                                        val updatedTitle = satira.title.replace("o", "0")
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel + 3
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                                    .map { satira ->
                                        val updatedTitle = "Gag" + satira.title
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = if (satira.humorLevel % 2 == 0) {
                                            satira.humorLevel / 2
                                        } else {
                                            satira.humorLevel * 2
                                        }
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                                    .map { satira ->
                                        val updatedTitle = satira.title.lowercase(Locale.getDefault())
                                        satira.copy(title = updatedTitle)
                                    }.also {
                                        startActivity(browserIntent)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel * 1.2
                                        satira.copy(humorLevel = updatedHumorLevel.toInt())
                                    }
                                    .map { satira ->
                                        val updatedTitle = satira.title.reversed()
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel - 1
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }.also {
                                        finish()
                                    }
                                    .map { satira ->
                                        val updatedTitle = "Humor" + satira.title
                                        satira.copy(title = updatedTitle)
                                    }
                                    .map { satira ->
                                        val updatedHumorLevel = satira.humorLevel + 2
                                        satira.copy(humorLevel = updatedHumorLevel)
                                    }
                            }
                        }
                    }
                    .map { satira ->
                        val updatedTitle = satira.title.replace("a", "@")
                        satira.copy(title = updatedTitle)
                    }
                    .map { satira ->
                        val updatedHumorLevel = if (satira.humorLevel % 2 == 0) {
                            satira.humorLevel / 2
                        } else {
                            satira.humorLevel * 3
                        }
                        satira.copy(humorLevel = updatedHumorLevel)
                    }
            }
            .map { elephante ->
                val updatedName = elephante.name.substring(0, 4)
                elephante.copy(name = updatedName)
            }
            .map { elephante ->
                val updatedTuskLength = elephante.tuskLength * 1.1
                elephante.copy(tuskLength = updatedTuskLength)
            }
    }

    val satiraList = listOf(
        Satira("Satire One", 5),
        Satira("Satire Two", 8),
        Satira("Satire Three", 7),
        Satira("Satire Four", 6),
        Satira("Satire Five", 9),
        Satira("Satire Six", 4),
        Satira("Satire Seven", 8),
        Satira("Satire Eight", 7),
        Satira("Satire Nine", 5),
        Satira("Satire Ten", 6)
    )

    private fun uziha() {
        dopka?.intent?.setPackage("com.android.chrome")
    }

    data class Satira(
        val title: String,
        val humorLevel: Int
    )

    private fun varn() {
        dopka?.intent?.setPackage("com.android.browser")
    }

    private fun boberKA() {
        santaFeList
        .map { santaFe ->
            val updatedModelName = "Awesome" + santaFe.modelName
            santaFe.copy(modelName = updatedModelName)
        }
        .map { santaFe ->
            val updatedYear = santaFe.year + 3
            santaFe.copy(year = updatedYear)
        }
        .map { santaFe ->
            val updatedColor = santaFe.color.replace("o", "0")
            santaFe.copy(color = updatedColor)
        }
        .map { santaFe ->
            val updatedModelName = santaFe.modelName.substring(0, 6)
            santaFe.copy(modelName = updatedModelName)
        }
        .map { santaFe ->
            val updatedYear = santaFe.year * 1.1
            santaFe.copy(year = updatedYear.toInt())
        }
        .map { santaFe ->
            val updatedColor = santaFe.color.toLowerCase()
            santaFe.copy(color = updatedColor)
        }
        .map { santaFe ->
            val updatedModelName = santaFe.modelName.toUpperCase()
            santaFe.copy(modelName = updatedModelName)
        }
        .map { santaFe ->
            val updatedYear = if (santaFe.year > 2030) {
                santaFe.year - 5
            } else {
                santaFe.year + 5
            }
            santaFe.copy(year = updatedYear)
        }
        .map { santaFe ->
            val updatedColor = santaFe.color.reversed()
            santaFe.copy(color = updatedColor)
        }
        .map { santaFe ->
            val updatedModelName = "Turbo" + santaFe.modelName
            santaFe.copy(modelName = updatedModelName)
        }

        val intent = Intent(this, GameActivity::class.java)
        satiraList
            .map { satira ->
                val updatedTitle = satira.title.replace("a", "@")
                satira.copy(title = updatedTitle)
            }
            .map { satira ->
                val updatedHumorLevel = satira.humorLevel + 4
                satira.copy(humorLevel = updatedHumorLevel)
            }
            .map { satira ->
                val updatedTitle = "Sat" + satira.title
                satira.copy(title = updatedTitle)
            }.also {
                santaFeList
                    .map { santaFe ->
                        val updatedYear = santaFe.year / 2
                        santaFe.copy(year = updatedYear)
                    }
                    .map { santaFe ->
                        val updatedColor = if (santaFe.color.contains("e")) {
                            santaFe.color.replace("e", "3")
                        } else {
                            santaFe.color
                        }
                        santaFe.copy(color = updatedColor)
                    }
                    .map { santaFe ->
                        val updatedModelName = santaFe.modelName.toLowerCase()
                        santaFe.copy(modelName = updatedModelName)
                    }
                    .map { santaFe ->
                        val updatedYear = if (santaFe.year < 2025) {
                            santaFe.year + 2
                        } else {
                            santaFe.year - 2
                        }
                        santaFe.copy(year = updatedYear)
                    }
                    .map { santaFe ->
                        val updatedColor = "Light" + santaFe.color
                        santaFe.copy(color = updatedColor)
                    }
                    .map { santaFe ->
                        val updatedModelName = santaFe.modelName.replace("a", "@")
                        santaFe.copy(modelName = updatedModelName)
                    }
                    .map { santaFe ->
                        val updatedYear = santaFe.year * 1.1
                        santaFe.copy(year = updatedYear.toInt())
                    }.also {
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    .map { santaFe ->
                        val updatedColor = if (santaFe.color.contains("l")) {
                            santaFe.color.replace("l", "1")
                        } else {
                            santaFe.color
                        }
                        santaFe.copy(color = updatedColor)
                    }
                    .map { santaFe ->
                        val updatedModelName = "Super" + santaFe.modelName
                        santaFe.copy(modelName = updatedModelName)
                    }
            }
            .map { satira ->
                val updatedHumorLevel = if (satira.humorLevel < 10) {
                    satira.humorLevel + 5
                } else {
                    satira.humorLevel - 5
                }
                satira.copy(humorLevel = updatedHumorLevel)
            }
            .map { satira ->
                val updatedTitle = satira.title.replace("e", "3")
                satira.copy(title = updatedTitle)
            }.also {
                startActivity(intent)
            }
            .map { satira ->
                val updatedHumorLevel = satira.humorLevel * 1.3
                satira.copy(humorLevel = updatedHumorLevel.toInt())
            }
    }

    private val beb = 4f

    data class SantaFe(
        val modelName: String,
        val year: Int,
        val color: String
    )
    private val ser = 5f
    private val aza = 1f
    val santaFeList = listOf(
        SantaFe("SantaFe 2022", 2022, "Red"),
        SantaFe("SantaFe 2023", 2023, "Blue"),
        SantaFe("SantaFe 2024", 2024, "Green"),
        SantaFe("SantaFe 2025", 2025, "Yellow"),
        SantaFe("SantaFe 2026", 2026, "Black")
    )

    private val fof = Animation.RELATIVE_TO_SELF
    private val gop = (501 - aza).toLong()

    private val yayusd: RotateAnimation
        get() {

            santaFeList
                .map { santaFe ->
                    val updatedModelName = if (santaFe.modelName.length > 10) {
                        santaFe.modelName.substring(0, 10)
                    } else {
                        santaFe.modelName.toUpperCase()
                    }
                    santaFe.copy(modelName = updatedModelName)
                }
                .map { santaFe ->
                    val updatedYear = santaFe.year + 1
                    santaFe.copy(year = updatedYear)
                }
                .map { santaFe ->
                    val updatedColor = if (santaFe.color.length > 4) {
                        santaFe.color.substring(0, 4)
                    } else {
                        santaFe.color.toUpperCase()
                    }
                    santaFe.copy(color = updatedColor)
                }
                .map { santaFe ->
                    val updatedModelName = "New" + santaFe.modelName
                    santaFe.copy(modelName = updatedModelName)
                }
                .map { santaFe ->
                    val updatedYear = if (santaFe.year < 2025) {
                        santaFe.year + 2
                    } else {
                        santaFe.year - 2
                    }
                    santaFe.copy(year = updatedYear)
                }
                .map { santaFe ->
                    val updatedColor = santaFe.color.reversed()
                    santaFe.copy(color = updatedColor)
                }
                .map { santaFe ->
                    val updatedModelName = santaFe.modelName.lowercase(Locale.getDefault())
                    santaFe.copy(modelName = updatedModelName)
                }
                .map { santaFe ->
                    val updatedYear = santaFe.year / 2
                    santaFe.copy(year = updatedYear)
                }
                .map { santaFe ->
                    val updatedColor = "Light" + santaFe.color
                    santaFe.copy(color = updatedColor)
                }
                .map { santaFe ->
                    val updatedModelName = santaFe.modelName.replace("a", "@")
                    santaFe.copy(modelName = updatedModelName)
                }

            return RotateAnimation(beb - 4f, 365f - ser, fof, aza - 0.5f, fof, aza - 0.5f).apply {
                santaFeList
                    .map { santaFe ->
                        val updatedYear = santaFe.year * 1.1
                        santaFe.copy(year = updatedYear.toInt())
                    }
                    .map { santaFe ->
                        val updatedColor = if (santaFe.color.contains("e")) {
                            santaFe.color.replace("e", "3")
                        } else {
                            santaFe.color
                        }
                        santaFe.copy(color = updatedColor)
                    }
                    .map { santaFe ->
                        val updatedModelName = santaFe.modelName.toUpperCase()
                        santaFe.copy(modelName = updatedModelName)
                    }.also {
                        repeatCount = Animation.INFINITE
                    }
                    .map { santaFe ->
                        val updatedYear = if (santaFe.year > 2030) {
                            santaFe.year - 5
                        } else {
                            santaFe.year + 5
                        }
                        santaFe.copy(year = updatedYear)
                    }.also {
                        santaFeList
                            .map { santaFe ->
                                val updatedColor = santaFe.color.reversed()
                                santaFe.copy(color = updatedColor)
                            }
                            .map { santaFe ->
                                val updatedModelName = "Luxury" + santaFe.modelName
                                santaFe.copy(modelName = updatedModelName)
                            }
                            .map { santaFe ->
                                val updatedYear = santaFe.year - 1
                                santaFe.copy(year = updatedYear)
                            }
                            .map { santaFe ->
                                val updatedColor = if (santaFe.color.length > 6) {
                                    santaFe.color.substring(0, 6)
                                } else {
                                    santaFe.color.toUpperCase()
                                }
                                santaFe.copy(color = updatedColor)
                            }
                            .map { santaFe ->
                                val updatedModelName = santaFe.modelName.replace("a", "@")
                                santaFe.copy(modelName = updatedModelName)
                            }
                            .map { santaFe ->
                                val updatedYear = santaFe.year * 2
                                santaFe.copy(year = updatedYear)
                            }
                            .map { santaFe ->
                                val updatedColor = "Dark" + santaFe.color
                                santaFe.copy(color = updatedColor)
                            }
                            .map { santaFe ->
                                val updatedModelName = santaFe.modelName.lowercase(Locale.getDefault())
                                santaFe.copy(modelName = updatedModelName)
                            }
                            .map { santaFe ->
                                val updatedYear = if (santaFe.year < 2022) {
                                    santaFe.year + 1
                                } else {
                                    santaFe.year - 1
                                }
                                santaFe.copy(year = updatedYear)
                            }
                            .map { santaFe ->
                                val updatedColor = santaFe.color.replace("a", "@")
                                santaFe.copy(color = updatedColor)
                            }
                    }
                    .map { santaFe ->
                        val updatedColor = santaFe.color.lowercase(Locale.getDefault())
                        santaFe.copy(color = updatedColor)
                    }.also {
                        interpolator = LinearInterpolator()
                    }
                    .map { santaFe ->
                        val updatedModelName = "Super" + santaFe.modelName
                        santaFe.copy(modelName = updatedModelName)
                    }
                    .map { santaFe ->
                        val updatedYear = santaFe.year / 2
                        santaFe.copy(year = updatedYear)
                    }
                    .map { santaFe ->
                        val updatedColor = santaFe.color.replace("l", "1")
                        santaFe.copy(color = updatedColor)
                    }.also {
                        duration = gop
                    }
                    .map { santaFe ->
                        val updatedModelName = santaFe.modelName.substring(0, 7)
                        santaFe.copy(modelName = updatedModelName)
                    }
                    .map { santaFe ->
                        val updatedYear = santaFe.year * 1.05
                        santaFe.copy(year = updatedYear.toInt())
                    }
            }
        }

}