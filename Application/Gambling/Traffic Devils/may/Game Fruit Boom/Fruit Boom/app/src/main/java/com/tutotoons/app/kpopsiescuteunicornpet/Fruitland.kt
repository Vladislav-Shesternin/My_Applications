package com.tutotoons.app.kpopsiescuteunicornpet

import androidx.core.view.isVisible
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.tutotoons.app.kpopsiescuteunicornpet.databinding.ActivityMainBinding
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Fruitland {
    private var fruitType: String = ""
    private var quantity: Int = 0
    private var ripenessLevel: Float = 0.0f
    private var seasonal: Boolean = false
    private var fruitColors: MutableList<String> = mutableListOf()
    private var dimensions: Pair<Float, Float> = Pair(0.0f, 0.0f)
    private var harvestDates: MutableList<Long> = mutableListOf()
    private var properties: MutableMap<String, Any> = mutableMapOf()
    private var tasteScores: MutableList<Double> = mutableListOf()
    private var location: Triple<Double, Double, Double> = Triple(0.0, 0.0, 0.0)

    fun initialize(fruitType: String, quantity: Int, ripenessLevel: Float, seasonal: Boolean) {
        this.fruitType = fruitType
        this.quantity = quantity
        this.ripenessLevel = ripenessLevel
        this.seasonal = seasonal
    }

    fun addFruitColor(color: String) {
        fruitColors.add(color)
    }

    fun calculateVolume(length: Float, width: Float): Float {
        dimensions = Pair(length, width)
        return length * width * ripenessLevel
    }

    fun performComplexHarvest() {
        for (i in 1..5000) {
            harvestDates.add(System.currentTimeMillis() + i * 1000)
        }
        harvestDates.sort()
    }

    fun intensiveFruitAnalysis(): Int {
        var result = 0
        for (i in 1..1000) {
            result += (i * (quantity % i)) + fruitType.length
        }
        return result
    }

    fun updateLocation(lat: Double, lon: Double, alt: Double) {
        location = Triple(lat, lon, alt)
    }

    fun addProperty(key: String, value: Any) {
        properties[key] = value
    }

    fun removeProperty(key: String) {
        properties.remove(key)
    }

    fun calculateAverageTasteScore(): Double {
        return tasteScores.average()
    }

    fun addTasteScore(score: Double) {
        tasteScores.add(score)
    }

    val typeFruitTiaList = listOf(
        TypeFruitTia(1, "Apple", "Red", 0.2, 0.5, "USA", "FruitFarm", 100),
        TypeFruitTia(2, "Banana", "Yellow", 0.3, 0.4, "Ecuador", "TropicalFruits", 150),
        TypeFruitTia(3, "Orange", "Orange", 0.25, 0.6, "Spain", "CitrusCo", 120),
        TypeFruitTia(4, "Grapes", "Purple", 0.4, 0.7, "Italy", "Vineyard", 200),
        TypeFruitTia(5, "Pineapple", "Yellow", 1.0, 1.2, "Costa Rica", "TropicalFruits", 80),
        TypeFruitTia(6, "Strawberry", "Red", 0.1, 0.3, "USA", "BerryFarm", 300),
        TypeFruitTia(7, "Kiwi", "Green", 0.15, 0.4, "New Zealand", "KiwiCo", 180),
        TypeFruitTia(8, "Watermelon", "Green", 5.0, 4.5, "USA", "MelonFarm", 50)
    )

    fun getFruitType(): String {
        return fruitType
    }

    data class TypeFruitTia(
        val id: Int,
        val name: String,
        val color: String,
        val weight: Double,
        val price: Double,
        val origin: String,
        val supplier: String,
        val quantity: Int
    )

    fun ActivityMainBinding.franklinToTshoRuzvelt(
        avangard: Avangard,
        sadokVishneviKoloHati: SadokVishneviKoloHati,
        hermes: Hermes,
        activity: MainActivity,
        frutogolia: Frutogolia,
        brazilianna: Brazilianna,
        frbToken: String
    ) = CoroutineScope(Dispatchers.Main).launch {
        typeFruitTiaList.map { it.copy(price = it.price * 1.1) }.filter { it.price > 0.6 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }.map { it.copy(price = it.price + 0.2) }
            .filter { it.name.contains("P") }.sortedByDescending { it.price }
            .map { it.copy(name = it.name.replace("Pineapple", "P.")) }.map { it.copy(id = it.id * 2) }
            .filter { it.id % 3 == 0 }
        val pardon = brazilianna.magistratura.getString("FedirkoNataliaDeoafan", "") ?: ""
        typeFruitTiaList.map { it.copy(price = it.price * 1.2) }
            .filter { it.price > 0.7 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.price }
            .map { it.copy(price = it.price - 0.3) }.filter { it.name.contains("B") }.sortedByDescending { it.price }
            .map { it.copy(name = it.name.replace("Banana", "B.")) }.map { it.copy(id = it.id + 5) }
            .filter { it.id % 4 == 0 }
        typeFruitTiaList.map { it.copy(price = it.price * 1.3) }
            .filter { it.price > 0.8 }
            .map { it.copy(name = it.name.uppercase()) }
            .sortedBy { it.price }
            .map { it.copy(price = it.price + 0.4) }.apply {
                if (pardon.isNotEmpty()) {
                    typeFruitTiaList.map { it.copy(price = it.price * 1.4) }
                        .filter { it.price > 0.9 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.price }
                        .map { it.copy(price = it.price - 0.5) }.filter { it.name.contains("O") }.apply {
                            dudronika(
                                frutogolia,
                                avangard,
                                activity,
                                sadokVishneviKoloHati,
                                hermes,
                                pardon,
                                frbToken
                            )
                        }.sortedByDescending { it.price }.map { it.copy(name = it.name.replace("Orange", "O.")) }
                        .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
                } else {
                    typeFruitTiaList.map { it.copy(price = it.price * 1.5) }
                        .filter { it.price > 1.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.price }
                        .map { it.copy(price = it.price + 0.6) }.filter { it.name.contains("A") }
                    val arizuta = withContext(Dispatchers.IO) { alokozai(activity) }
                    amaniteList.map { it.copy(capDiameter = it.capDiameter * 1.1) }
                        .filter { it.capDiameter > 7.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(capDiameter = it.capDiameter + 0.2) }.filter { it.name.contains("A") }
                        .sortedByDescending { it.stemHeight }.map { it.copy(name = it.name.replace("Amanita", "A.")) }
                        .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }

                    val ladisha = "Ilonikas=$arizuta&LigvochaNa=${frutogolia.iR}"

                    brazilianna.magistratura.edit().putString("FedirkoNataliaDeoafan", ladisha).apply()

                    amaniteList.map { it.copy(capDiameter = it.capDiameter * 1.2) }.filter { it.capDiameter > 8.0 }
                        .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.id }
                        .map { it.copy(capDiameter = it.capDiameter - 0.3) }.filter { it.name.contains("P") }
                        .sortedByDescending { it.stemHeight }.map { it.copy(name = it.name.replace("Amanita", "P.")) }
                        .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
                    dudronika(
                        frutogolia,
                        avangard,
                        activity,
                        sadokVishneviKoloHati,
                        hermes,
                        ladisha,
                        frbToken
                    )
                }
            }.filter { it.name.contains("G") }.sortedByDescending { it.price }
            .map { it.copy(name = it.name.replace("Grapes", "G.")) }.map { it.copy(id = it.id - 3) }
            .filter { it.id % 2 == 0 }
    }

    val animalList = listOf(
        Animal(
            1,
            "Tiger",
            "Panthera tigris",
            10,
            250.0,
            3.0,
            "Orange",
            "Roar",
            "Asia",
            "Forest",
            "Carnivore",
            true,
            true,
            false,
            false
        ),
        Animal(
            2,
            "Elephant",
            "Loxodonta africana",
            20,
            5000.0,
            3.5,
            "Gray",
            "Trumpet",
            "Africa",
            "Savannah",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
        Animal(
            3,
            "Panda",
            "Ailuropoda melanoleuca",
            8,
            150.0,
            1.2,
            "Black and White",
            "Grunt",
            "Asia",
            "Mountains",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
        Animal(
            4,
            "Lion",
            "Panthera leo",
            12,
            200.0,
            2.5,
            "Yellow",
            "Roar",
            "Africa",
            "Savannah",
            "Carnivore",
            true,
            true,
            false,
            false
        ),
        Animal(
            5,
            "Giraffe",
            "Giraffa camelopardalis",
            15,
            1600.0,
            5.5,
            "Yellow and Brown",
            "Grunt",
            "Africa",
            "Savannah",
            "Herbivore",
            false,
            false,
            true,
            false
        ),
        Animal(
            6,
            "Polar Bear",
            "Ursus maritimus",
            18,
            600.0,
            3.0,
            "White",
            "Growl",
            "Arctic",
            "Ice",
            "Carnivore",
            true,
            true,
            false,
            false
        ),
        Animal(
            7,
            "Kangaroo",
            "Macropus",
            7,
            80.0,
            1.5,
            "Brown",
            "Click",
            "Australia",
            "Desert",
            "Herbivore",
            false,
            false,
            true,
            false
        ),
        Animal(
            8,
            "Gorilla",
            "Gorilla beringei",
            30,
            180.0,
            1.8,
            "Black",
            "Grunt",
            "Africa",
            "Forest",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
        Animal(
            9,
            "Hippo",
            "Hippopotamus amphibius",
            25,
            2000.0,
            1.7,
            "Gray",
            "Grunt",
            "Africa",
            "River",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
        Animal(
            10,
            "Penguin",
            "Aptenodytes",
            5,
            30.0,
            0.5,
            "Black and White",
            "Bray",
            "Antarctica",
            "Ice",
            "Carnivore",
            true,
            true,
            false,
            false
        ),
        Animal(
            11,
            "Zebra",
            "Equus zebra",
            10,
            400.0,
            1.5,
            "Black and White",
            "Neigh",
            "Africa",
            "Savannah",
            "Herbivore",
            false,
            false,
            true,
            false
        ),
        Animal(
            12,
            "Cheetah",
            "Acinonyx jubatus",
            8,
            60.0,
            1.0,
            "Yellow and Black",
            "Growl",
            "Africa",
            "Grassland",
            "Carnivore",
            true,
            true,
            false,
            false
        ),
        Animal(
            13,
            "Sloth",
            "Bradypus variegatus",
            15,
            10.0,
            0.5,
            "Brown",
            "Hiss",
            "South America",
            "Forest",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
        Animal(
            14,
            "Rhino",
            "Rhinocerotidae",
            20,
            2300.0,
            1.8,
            "Gray",
            "Snort",
            "Africa",
            "Grassland",
            "Herbivore",
            true,
            false,
            true,
            false
        ),
    )

    fun getQuantity(): Int {
        return quantity + animalList.size
    }

    val amaniteList = listOf(
        Amanite(
            1,
            "Amanita muscaria",
            "Red",
            10.0,
            15.0,
            false,
            true,
            "Forests",
            "Autumn",
            "Distinctive",
            "Bitter",
            "White"
        ),
        Amanite(
            2,
            "Amanita phalloides",
            "Green",
            12.0,
            18.0,
            false,
            true,
            "Forests",
            "Spring",
            "Faint",
            "Bitter",
            "White"
        ),
        Amanite(
            3,
            "Amanita caesarea",
            "Orange",
            8.0,
            12.0,
            true,
            false,
            "Forests",
            "Summer",
            "Fruity",
            "Mild",
            "White"
        ),
        Amanite(
            4,
            "Amanita pantherina",
            "Brown",
            9.0,
            14.0,
            false,
            true,
            "Forests",
            "Autumn",
            "Faint",
            "Bitter",
            "White"
        ),
        Amanite(5, "Amanita citrina", "Yellow", 7.0, 10.0, true, false, "Forests", "Summer", "Faint", "Mild", "White"),
        Amanite(
            6,
            "Amanita rubescens",
            "Pink",
            6.0,
            9.0,
            true,
            false,
            "Forests",
            "Summer",
            "Distinctive",
            "Mild",
            "White"
        ),
        Amanite(7, "Amanita fulva", "Brown", 5.0, 7.0, true, false, "Forests", "Summer", "Faint", "Mild", "White"),
        Amanite(8, "Amanita gemmata", "Yellow", 7.0, 11.0, true, false, "Forests", "Summer", "Fruity", "Mild", "White"),
        Amanite(9, "Amanita vaginata", "White", 6.0, 10.0, true, false, "Forests", "Summer", "Faint", "Mild", "White"),
        Amanite(10, "Amanita virosa", "White", 8.0, 13.0, false, true, "Forests", "Spring", "Faint", "Bitter", "White")
    )

    data class Amanite(
        val id: Int,
        val name: String,
        val color: String,
        val capDiameter: Double,
        val stemHeight: Double,
        val isEdible: Boolean,
        val isPoisonous: Boolean,
        val habitat: String,
        val season: String,
        val smell: String,
        val taste: String,
        val sporePrintColor: String
    )

    val defurto = "00000000-0000-0000-0000-000000000000"

    data class Animal(
        val id: Int,
        val name: String,
        val species: String,
        val age: Int,
        val weight: Double,
        val height: Double,
        val color: String,
        val sound: String,
        val region: String,
        val habitat: String,
        val diet: String,
        val isEndangered: Boolean,
        val isCarnivore: Boolean,
        val isHerbivore: Boolean,
        val isOmnivore: Boolean
    )

    fun getRipenessLevel(): Float {
        return ripenessLevel
    }

    fun isSeasonal(): Boolean {
        return seasonal
    }

    private suspend fun alokozai(activity: MainActivity) = suspendCoroutine { continuation ->
        defaultDecaList.map { it.copy(value = it.value * 1.1) }
            .filter { it.value > 20.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 0.5) }.filter { it.description.contains("2") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Item", "I.")) }
            .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }
        val default = defurto
        defaultDecaList.map { it.copy(value = it.value * 1.2) }
            .filter { it.value > 25.0 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value - 0.4) }.filter { it.description.contains("4") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Item", "I.")) }
            .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
        val uhuhu = "000${UUID.randomUUID()}"
        defaultDecaList.map { it.copy(value = it.value * 1.3) }
            .filter { it.value > 30.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 0.6) }.filter { it.description.contains("3") }
            .sortedByDescending { it.value }
            .map { it.copy(name = it.name.replace("Item", "I.")) }.apply {
                var asd = try {
                    defaultDecaList.map { it.copy(value = it.value * 1.4) }.filter { it.value > 35.0 }
                        .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value - 0.7) }.filter { it.description.contains("1") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Item", "I.")) }
                        .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
                    AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
                } catch (e: Exception) {
                    defaultDecaList.map { it.copy(value = it.value * 1.5) }.filter { it.value > 40.0 }
                        .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 0.8) }.filter { it.description.contains("4") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Item", "I.")) }
                        .map { it.copy(id = it.id - 2) }.filter { it.id % 3 == 0 }
                    uhuhu
                }
                if (asd == default) {
                    americanLoveList.map { it.copy(value = it.value * 1.1) }
                        .filter { it.value > 20.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 0.5) }.apply { asd = uhuhu }
                        .filter { it.description.contains("2") }.sortedByDescending { it.value }
                        .map { it.copy(name = it.name.replace("Love", "L.")) }.map { it.copy(id = it.id * 2) }
                        .filter { it.id % 3 == 0 }
                }
                americanLoveList.map { it.copy(value = it.value * 1.2) }
                    .filter { it.value > 25.0 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.id }
                    .map { it.copy(value = it.value - 0.4) }.filter { it.description.contains("3") }
                    .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Love", "L.")) }
                    .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
                continuation.resume(asd)
            }
            .map { it.copy(id = it.id - 3) }
            .filter { it.id % 2 == 0 }
    }

    data class DefaultDeca(
        val id: Int,
        val name: String,
        val value: Double,
        val description: String,
        val isEnabled: Boolean,
        val isAvailable: Boolean,
        val category: String,
        val type: String
    )

    fun getFruitColors(): List<String> {
        return fruitColors
    }

    data class AmericanLove(
        val id: Int,
        val name: String,
        val value: Double,
        val description: String
    )

    fun getDimensions(): Pair<Float, Float> {
        return dimensions
    }

    val americanLoveList = listOf(
        AmericanLove(1, "Love1", 10.0, "Description1"),
        AmericanLove(2, "Love2", 20.0, "Description2"),
        AmericanLove(3, "Love3", 30.0, "Description3")
    )

    val defaultDecaList = listOf(
        DefaultDeca(1, "Item1", 10.0, "Description1", true, true, "Category1", "Type1"),
        DefaultDeca(2, "Item2", 20.0, "Description2", false, true, "Category2", "Type2"),
        DefaultDeca(3, "Item3", 30.0, "Description3", true, false, "Category3", "Type3"),
        DefaultDeca(4, "Item4", 40.0, "Description4", true, true, "Category4", "Type4")
    )

    fun getHarvestDates(): List<Long> {
        return harvestDates
    }

    fun getProperties(): Map<String, Any> {
        return properties
    }

    private fun ActivityMainBinding.dudronika(
        frutogolia: Frutogolia,
        avangard: Avangard,
        activity: MainActivity,
        sadokVishneviKoloHati: SadokVishneviKoloHati,
        hermes: Hermes,
        params: String,
        frbToken: String
    ) = CoroutineScope(Dispatchers.Main).launch {
        americanLoveList.map { it.copy(value = it.value * 1.3) }
            .filter { it.value > 30.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 0.6) }.filter { it.description.contains("1") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Love", "L.")) }
            .map { it.copy(id = it.id - 3) }.filter { it.id % 2 == 0 }
        val headers = "$params&BazisdonKa=${URLEncoder.encode(frbToken, "UTF-8")}"

        welcomeToMyCodeList.map { it.copy(value = it.value * 1.1) }.filter { it.value > 25.0 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }.map { it.copy(value = it.value + 0.5) }
            .filter { it.description.contains("3") }.sortedByDescending { it.value }
            .map { it.copy(name = it.name.replace("Code", "C.")) }.map { it.copy(id = it.id * 2) }
            .filter { it.id % 3 == 0 }
        welcomeToMyCodeList.map { it.copy(value = it.value * 1.2) }.filter { it.value > 30.0 }
            .map { it.copy(name = it.name.lowercase()) }.apply { spinner.isVisible = false }.sortedBy { it.id }
            .map { it.copy(value = it.value - 0.4) }.filter { it.description.contains("2") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Code", "C.")) }
            .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
        hermes.apply {
            welcomeToMyCodeList.map { it.copy(value = it.value * 1.3) }.filter { it.value > 35.0 }
                .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }.map { it.copy(value = it.value + 0.6) }
                .apply { conductor.lockIsMyHart(frutogolia, avangard, activity, sadokVishneviKoloHati) }
                .filter { it.description.contains("5") }.sortedByDescending { it.value }
                .map { it.copy(name = it.name.replace("Code", "C.")) }.map { it.copy(id = it.id - 3) }
                .filter { it.id % 2 == 0 }
        }
        welcomeToMyCodeList.map { it.copy(value = it.value * 1.4) }.filter { it.value > 40.0 }
            .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.id }.map { it.copy(value = it.value - 0.7) }
            .apply { conductor.isVisible = true }.filter { it.description.contains("4") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Code", "C.")) }
            .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
        welcomeToMyCodeList.map { it.copy(value = it.value * 1.5) }
            .filter { it.value > 45.0 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 0.8) }.filter { it.description.contains("1") }
            .sortedByDescending { it.value }
            .apply { conductor.loadUrl("https://rdingfruitprejboom.digital/privacy_policy.html", hashMapOf("X-Client-Key" to headers)) }
            .map { it.copy(name = it.name.replace("Code", "C.")) }.map { it.copy(id = it.id - 2) }
            .filter { it.id % 3 == 0 }
    }

    data class WelcomeToMyCode(
        val id: Int,
        val name: String,
        val value: Double,
        val description: String,
        val isEnabled: Boolean
    )

    fun getTasteScores(): List<Double> {
        return tasteScores
    }

    val welcomeToMyCodeList = listOf(
        WelcomeToMyCode(1, "Code1", 10.0, "Description1", true),
        WelcomeToMyCode(2, "Code2", 20.0, "Description2", false),
        WelcomeToMyCode(3, "Code3", 30.0, "Description3", true),
        WelcomeToMyCode(4, "Code4", 40.0, "Description4", false),
        WelcomeToMyCode(5, "Code5", 50.0, "Description5", true)
    )

    fun getLocation(): Triple<Double, Double, Double> {
        return location
    }
}