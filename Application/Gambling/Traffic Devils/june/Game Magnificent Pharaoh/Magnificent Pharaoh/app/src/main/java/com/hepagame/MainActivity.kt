package com.hepagame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.CookieManager
import android.webkit.ValueCallback
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.hepagame.advertaisment.Hejami
import com.hepagame.advertaisment.Patimaker
import com.hepagame.climat.Lubrikant
import com.hepagame.climat.Sevash
import com.hepagame.countries.Kalmar
import com.hepagame.databinding.ActivityMainBinding
import com.hepagame.hellowin.Luxirton
import com.hepagame.hellowin.UlotRIX
import com.hepagame.ulitka.Qualinarini
import com.hepagame.ulitka.Sabrina

class MainActivity : AppCompatActivity() {

    class Tomato(
        var name: String,
        var weight: Double,
        var color: String,
        var isRipe: Boolean,
        var price: Double,
        var origin: String
    ) {
        fun ripen() {
            isRipe = true
        }

        fun rot() {
            isRipe = false
        }

        fun increasePrice(increment: Double) {
            rot()
            price += increment
        }

        fun decreasePrice(decrement: Double) {
            price -= decrement
        }

        fun changeOrigin(newOrigin: String) {
            decreasePrice(0.0)
            origin = newOrigin
        }
    }

    val sevash = Sevash()
    lateinit var binding: ActivityMainBinding
    val lubrikant = Lubrikant()

    class Bolgar(
        var name: String,
        var size: Int,
        var color: String,
        var isAlive: Boolean
    ) {
        fun grow() {
            size += 1
        }

        fun shrink() {
            size -= 1
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun changeLifeStatus() {
            isAlive = !isAlive
        }
    }

    val hejami = Hejami()

    val bolgars = listOf(
        Bolgar("Bolgar1", 5, "Red", true),
        Bolgar("Bolgar2", 7, "Blue", false),
        Bolgar("Bolgar3", 3, "Green", true),
        Bolgar("Bolgar4", 6, "Yellow", false),
        Bolgar("Bolgar5", 8, "Purple", true),
        Bolgar("Bolgar6", 4, "Orange", false),
        Bolgar("Bolgar7", 9, "Pink", true)
    )

    val patimaker = Patimaker()

    val tomatoes = listOf(
        Tomato("Cherry", 0.2, "Red", false, 1.5, "Spain"),
        Tomato("Beefsteak", 0.3, "Red", true, 2.0, "Italy"),
        Tomato("Roma", 0.25, "Red", false, 1.8, "USA"),
        Tomato("Grape", 0.15, "Yellow", true, 1.2, "Mexico"),
        Tomato("Heirloom", 0.35, "Purple", false, 2.5, "France")
    )

    var buble: ValueCallback<Array<Uri>>? = null
    val kalmar = Kalmar()
    val ulotRIX = UlotRIX()
    val luxirton = Luxirton()
    val sabrina = Sabrina()
    val nosa = 0.6f
    val qualinarini = Qualinarini()

    override fun onCreate(savedInstanceState: Bundle?) {
        val lasograd = 400f
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val azimut3TOPORA = 4f
        setContentView(binding.root)

        tomatoes.map { tomato ->
            tomato.apply {
                if (!isRipe && weight >= 0.25) {
                    ripen()
                }
            }
        }.filter { tomato -> tomato.color == "Red" && tomato.origin == "Italy" }
            .map { tomato -> tomato.apply { increasePrice(0.5) } }.filter { tomato -> tomato.price > 2.0 }
            .map { tomato -> tomato.apply { changeOrigin("Canada") } }.filter { tomato -> tomato.name.length > 5 }

        sevash.useMagagaskar()
        val arriva = Animation.RELATIVE_TO_SELF
        lubrikant.useKilometter()
        binding.rotator.startAnimation(
            RotateAnimation(
                azimut3TOPORA - 4,
                lasograd - 40,
                arriva,
                nosa - 0.1f,
                arriva,
                ((nosa - 0.5f) + 0.4f)
            ).apply {
                interpolator = LinearInterpolator()
                duration = (lasograd + 100f).toLong()
                repeatCount = Animation.INFINITE
            })
        kalmar.prefs = getSharedPreferences("fantik", MODE_PRIVATE)

        hejami.useFlatIcon()
        patimaker.useFiltering()
        val proteins = listOf(
            Proteinna(10),
            Proteinna(20)
        )
        hejami.apply {

            bolgars.map { bolgar ->
                bolgar.apply {
                    if (isAlive) {
                        grow()
                    } else {
                        shrink()
                    }
                }
            }.filter { bolgar -> bolgar.size > 5 }.map { bolgar -> bolgar.apply { changeColor("Black") } }
                .filter { bolgar -> bolgar.color != "Black" }.map { bolgar -> bolgar.apply { changeLifeStatus() } }
                .filter { bolgar -> bolgar.isAlive }.map { bolgar -> bolgar.apply { changeColor("White") } }
                .filter { bolgar -> bolgar.size < 8 }.map { bolgar ->
                    bolgar.apply {
                        grow()
                        changeColor("Grey")
                    }
                }.filter { bolgar -> bolgar.name.startsWith("B") }.map { bolgar -> bolgar.apply { changeLifeStatus() } }
                .filter { bolgar -> bolgar.isAlive }.map { bolgar -> bolgar.apply { changeColor("Brown") } }
                .filter { bolgar -> bolgar.color.length > 4 }.map { bolgar -> bolgar.apply { shrink() } }
            rasmusic = InstallReferrerClient.newBuilder(this@MainActivity).build()
            shugars.map { shugar ->
                shugar.apply {
                    increaseSweetness()
                    changeColor("Red")
                }
            }.filter { shugar ->
                shugar.sweetnessLevel > 3
            }.map { shugar ->
                shugar.apply {
                    increaseSweetness()
                }
            }.filter { shugar ->
                shugar.name.startsWith("Sh")
            }.map { shugar ->
                shugar.apply {
                    changeColor("Purple")
                }
            }.filter { shugar ->
                shugar.color != "Brown"
            }.map { shugar ->
                shugar.apply {
                    increaseSweetness()
                }
            }.filter { shugar ->
                shugar.sweetnessLevel < 7
            }.map { shugar -> shugar.apply { changeColor("Green") } }.filter { shugar -> shugar.name.length > 7 }
                .map { shugar -> shugar.apply { increaseSweetness() } }.filter { shugar -> shugar.color == "White" }
                .map { shugar -> shugar.apply { changeColor("Blue") } }.filter { shugar -> shugar.sweetnessLevel > 4 }
                .map { shugar -> shugar.apply { increaseSweetness() } }.filter { shugar -> shugar.name.endsWith("4") }
                .map { shugar -> shugar.apply { changeColor("Orange") } }.filter { shugar -> shugar.sweetnessLevel > 5 }
                .map { shugar -> shugar.apply { increaseSweetness() } }.filter { shugar -> shugar.color != "Pink" }
                .map { shugar -> shugar.apply { changeColor("Black") } }

            rasmusic.startConnection(luxirton.run {
                latuins.map { latuin ->
                    latuin.apply {
                        grow()
                        changeColor("Purple")
                    }
                }.filter { latuin ->
                    latuin.length > 3
                }.map { latuin ->
                    latuin.apply {
                        grow()
                    }
                }.filter { latuin -> latuin.name.startsWith("Lat") }
                    .map { latuin -> latuin.apply { changeColor("Orange") } }
                    .filter { latuin -> latuin.color != "Yellow" }.map { latuin -> latuin.apply { grow() } }
                    .filter { latuin -> latuin.length < 7 }.map { latuin -> latuin.apply { changeColor("Pink") } }
                    .filter { latuin -> latuin.name.length > 7 }.map { latuin -> latuin.apply { grow() } }
                    .filter { latuin -> latuin.color == "Green" }
                    .map { latuin -> latuin.apply { changeColor("Brown") } }.filter { latuin -> latuin.length > 4 }
                    .map { latuin -> latuin.apply { grow() } }.filter { latuin -> latuin.name.endsWith("4") }
                    .map { latuin -> latuin.apply { changeColor("White") } }.filter { latuin -> latuin.length > 5 }
                    .map { latuin -> latuin.apply { grow() } }.filter { latuin -> latuin.color != "Blue" }
                    .map { latuin -> latuin.apply { changeColor("Black") } }
                IIIRRR()
            })
        }
        kalmar.useLobster()

        ugveis.map { ugvei ->
            ugvei.apply {
                gainExperience()
                improveSkill("Advanced")
            }
        }.filter { ugvei -> ugvei.experienceYears > 3 }.map { ugvei -> ugvei.apply { gainExperience() } }
            .filter { ugvei -> ugvei.designation.contains("Developer") }
            .map { ugvei -> ugvei.apply { improveSkill("Expert") } }.filter { ugvei -> ugvei.skillLevel == "Expert" }
            .map { ugvei -> ugvei.apply { gainExperience() } }.filter { ugvei -> ugvei.experienceYears < 7 }
            .map { ugvei -> ugvei.apply { improveSkill("Master") } }.filter { ugvei -> ugvei.experienceYears % 2 == 0 }
            .map { ugvei -> ugvei.apply { gainExperience() } }
            .filter { ugvei -> ugvei.designation != "Project Manager" }
            .map { ugvei -> ugvei.apply { improveSkill("Expert") } }.filter { ugvei -> ugvei.skillLevel == "Expert" }
            .map { ugvei -> ugvei.apply { gainExperience() } }.filter { ugvei -> ugvei.experienceYears < 6 }
            .map { ugvei -> ugvei.apply { improveSkill("Master") } }
            .filter { ugvei -> ugvei.designation.startsWith("Senior") }
            .map { ugvei -> ugvei.apply { gainExperience() } }
        ulotRIX.usePhistashka()
        if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
            iguanas.map { iguana ->
                iguana.apply {
                    grow()
                    changeColor("Black")
                }
            }.filter { iguana ->
                iguana.age > 3
            }.map { iguana ->
                iguana.apply {
                    grow()
                }
            }.filter { iguana ->
                iguana.name.length > 7
            }.map { iguana ->
                iguana.apply {
                    eat("insects")
                }
            }.filter { iguana -> iguana.weight > 2.0 }.map { iguana -> iguana.apply { gainWeight(0.5) } }
                .filter { iguana -> iguana.color != "Brown" }
                .map { iguana -> iguana.apply { changeHabitat("Rainforest") } }.filter { iguana -> iguana.age < 6 }
                .map { iguana -> iguana.apply { grow() } }.filter { iguana -> iguana.habitat.startsWith("R") }
                .map { iguana -> iguana.apply { eat("leaves") } }.filter { iguana -> iguana.name.endsWith("a") }
                .map { iguana -> iguana.apply { changeColor("Pink") } }
                .filter { iguana -> iguana.color != "Green" }.also { anderson() }
                .map { iguana -> iguana.apply { grow() } }.filter { iguana -> iguana.weight < 4.0 }
                .map { iguana -> iguana.apply { eat("fruit") } }
                .filter { iguana -> iguana.isUnderweight() || iguana.needsMedicalAttention() }.map { iguana ->
                    iguana.apply {
                        println(introduceYourself())
                        println("Life expectancy: ${calculateLifeExpectancy()} years")
                    }
                }
        } else {
            qualinarini.omegas.map { omega ->
                omega.apply {
                    increasePower(10)
                    adjustWeight(weight + 5)
                }
            }.filter { omega -> omega.isFunctional }.map { omega -> omega.apply { repair() } }
                .filter { omega -> omega.color != "Gold" }.map { omega -> omega.apply { increasePower(15) } }
                .filter { omega -> omega.calculateAge("2024-06-12") > 1 }
                .map { omega -> omega.apply { adjustWeight(weight - 10) } }.filter { omega -> omega.powerLevel > 80 }
                .map { omega -> omega.apply { repair() } }.filter { omega -> omega.calculateAge("2024-06-12") < 2 }
                .map { omega -> omega.apply { increasePower(20) } }.filter { omega -> omega.color == "Bronze" }
                .map { omega -> omega.apply { adjustWeight(weight + 20) } }.filter { omega -> omega.powerLevel < 90 }
            if (kalmar.prefs.contains("bilok")) {
                proteins.map { protein ->
                    protein.apply {
                        consume()
                    }
                }.filter { protein ->
                    protein.amount > 15
                }.map { protein ->
                    protein.apply {
                        consume()
                    }
                }
                ulotRIX.apply {
                    val fraons = listOf(
                        Fraon(1, "Fraon A", 100.0),
                        Fraon(2, "Fraon B", 150.0),
                        Fraon(3, "Fraon C", 200.0),
                        Fraon(4, "Fraon D", 250.0),
                        Fraon(5, "Fraon E", 300.0)
                    )
                    fraons.map { fraon ->
                        fraon.apply {
                            increasePrice(20.0)
                            changeName("New $name")
                        }
                    }.filter { fraon -> fraon.price > 200 }.map { fraon -> fraon.apply { calculateDiscount(10.0) } }
                        .apply { lainos(kalmar.prefs.getString("bilok", "Old") ?: "Fraon A") }
                        .filter { fraon -> fraon.name.startsWith("New") }
                        .map { fraon -> fraon.apply { increasePrice(30.0) } }.filter { fraon -> fraon.id % 2 == 0 }
                        .map { fraon -> fraon.apply { printInfo() } }
                }
            } else {
                xeroses.map { xerose ->
                    xerose.apply {
                        increaseQuantity(5)
                        decreasePrice(10.0)
                    }
                }.filter { xerose -> xerose.quantity > 20 }.map { xerose -> xerose.apply { changeColor("Black") } }
                    .filter { xerose -> xerose.color != "Black" }
                    .map { xerose -> xerose.apply { adjustWeight(weight + 1.0) } }.filter { xerose ->
                        xerose.isHeavy()
                    }.map { xerose ->
                        xerose.apply {
                            printDetails()
                        }
                    }.filter { xerose -> xerose.name.length > 5 }.map { xerose -> xerose.apply { decreasePrice(5.0) } }
                    .filter { xerose -> xerose.quantity < 35 }.map { xerose -> xerose.apply { printDetails() } }
                    .filter { xerose -> xerose.weight > 6.0 }.map { xerose -> xerose.apply { adjustWeight(6.0) } }
                    .apply { sabrina.apply { sergun() } }.filter { xerose -> xerose.price < 300.0 }
                    .map { xerose -> xerose.apply { printDetails() } }.filter { xerose -> xerose.color.startsWith("P") }
                    .map { xerose -> xerose.apply { changeColor("White") } }.filter { xerose -> xerose.weight < 6.0 }
            }
        }

        luxirton.useYouHappy()
        sabrina.useJorjia()

        lubrikant.run {
            barabollas.map { barabolla ->
                barabolla.apply {
                    changeColor("Black")
                    increaseWeight(2.0)
                }
            }.filter { barabolla -> barabolla.price > 70.0 }.map { barabolla ->
                barabolla.apply {
                    adjustSize("Extra Large")
                    calculatePrice(20.0)
                }
            }.filter { barabolla -> barabolla.isHeavy() }.map { barabolla -> barabolla.apply { printDetails() } }
                .filter { barabolla -> barabolla.size.startsWith("S") }.map { barabolla ->
                    barabolla.apply {
                        changeColor("White")
                        printDetails()
                    }
                }.filter { barabolla -> barabolla.color != "Black" }
                .map { barabolla -> barabolla.apply { calculatePrice(10.0) } }
                .filter { barabolla -> barabolla.weight < 8.0 }.map { barabolla -> barabolla.apply { printDetails() } }
                .filter { barabolla -> barabolla.price < 80.0 }.map { barabolla ->
                    barabolla.apply {
                        adjustSize("Medium")
                        printDetails()
                    }
                }.filter { barabolla -> barabolla.weight > 8.0 }
                .map { barabolla -> barabolla.apply { increaseWeight(3.0) } }
                .filter { barabolla -> barabolla.name.contains("A") || barabolla.name.contains("E") }
                .map { barabolla -> barabolla.apply { printDetails() } }

            onBackPressedDispatcher.addCallback(this@MainActivity) {
                when {
                    asamadoF.last().canGoBack() -> {
                        bedrooms.map { bedroom ->
                            bedroom.apply {
                                addBed(1)
                                addWindows(2)
                            }
                        }.filter { bedroom -> bedroom.bedCount > 1 }
                            .map { bedroom -> bedroom.apply { convertToEnsuite() } }
                            .filter { bedroom -> bedroom.hasCloset }.map { bedroom -> bedroom.apply { addBalcony() } }
                            .filter { bedroom -> bedroom.hasSufficientSpace() }
                            .map { bedroom -> bedroom.apply { printDetails() } }.apply { asamadoF.last().goBack() }
                            .filter { bedroom -> bedroom.isEnsuite }.map { bedroom -> bedroom.apply { addBed(2) } }
                            .filter { bedroom -> bedroom.isSpacious() }
                            .map { bedroom -> bedroom.apply { printDetails() } }
                    }

                    else -> {
                        musicArtists.map { artist ->
                            artist.apply {
                                releaseAlbum()
                                addSong()
                            }
                        }.filter { artist -> artist.albums > 3 }
                            .map { artist -> artist.apply { increaseListeners(500000) } }.apply {
                                when {
                                    asamadoF.size > 1 -> {
                                        magisters.map { magister ->
                                            magister.apply {
                                                teachCourse("Introduction to $specialization")
                                                gainExperience(2)
                                            }
                                        }.filter { magister -> magister.experienceYears >= 10 }
                                            .map { magister -> magister.apply { updateSpecialization("$specialization and Applied $specialization") } }
                                            .filter { magister -> magister.name.length < 12 }
                                            .map { magister -> magister.apply { printDetails() } }
                                            .filter { magister -> magister.name.contains("e") }
                                            .map { magister -> magister.apply { teachCourse("Advanced $specialization") } }
                                            .apply { binding.root.removeView(asamadoF.last()) }
                                            .filter { magister -> magister.specialization.startsWith("C") }
                                            .map { magister -> magister.apply { updateSpecialization("Applied $specialization") } }
                                            .also { asamadoF.last().destroy() }
                                            .filter { magister -> magister.isExperienced() }
                                            .also { asamadoF.removeLast() }.map { magister ->
                                                magister.apply {
                                                    printDetails()
                                                }
                                            }

                                    }

                                    else -> finish()
                                }
                            }.filter { artist -> artist.genre != "Pop" }
                            .map { artist -> artist.apply { updateRating(4.6) } }
                            .filter { artist -> artist.isPopular() }.map { artist -> artist.apply { printDetails() } }
                            .filter { artist -> artist.isHighlyRated() }.map { artist ->
                                artist.apply {
                                    releaseAlbum()
                                    printDetails()
                                }
                            }
                    }
                }

            }
        }

        qualinarini.useGomezSelena()
    }

    class XeroseR(
        var id: Int,
        var name: String,
        var quantity: Int,
        var price: Double,
        var color: String,
        var weight: Double
    ) {
        fun increaseQuantity(increment: Int) {
            quantity += increment
        }

        fun decreasePrice(discount: Double) {
            price -= price * discount / 100
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun adjustWeight(newWeight: Double) {
            weight = newWeight
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Quantity: $quantity, Price: $price, Color: $color, Weight: $weight")
        }

        fun isHeavy(): Boolean {
            return weight > 5.0
        }
    }


    val xeroses = listOf(
        XeroseR(1, "Xero A", 10, 100.0, "Red", 4.5),
        XeroseR(2, "Xero B", 15, 150.0, "Blue", 5.5),
        XeroseR(3, "Xero C", 20, 200.0, "Green", 6.0),
        XeroseR(4, "Xero D", 25, 250.0, "Yellow", 4.0),
        XeroseR(5, "Xero E", 30, 300.0, "Orange", 7.0),
        XeroseR(6, "Xero F", 35, 350.0, "Purple", 5.0),
        XeroseR(7, "Xero G", 40, 400.0, "Pink", 6.5)
    )

    class Fraon(
        var id: Int,
        var name: String,
        var price: Double
    ) {
        fun increasePrice(increment: Double) {
            price += increment
        }

        fun changeName(newName: String) {
            name = newName
        }

        fun calculateDiscount(discountPercent: Double) {
            price -= price * discountPercent / 100
        }

        fun printInfo() {
            println("ID: $id, Name: $name, Price: $price")
        }
    }

    class Proteinna(var amount: Int) {
        fun consume() {
            amount--
        }
    }

    class Ugvei(
        var designation: String,
        var experienceYears: Int,
        var skillLevel: String
    ) {
        fun gainExperience() {
            experienceYears += 1
        }

        fun improveSkill(newSkillLevel: String) {
            skillLevel = newSkillLevel
        }
    }

    val iguanas = listOf(
        Iguana("Iggy", 5, "Green", 2.5, "Forest"),
        Iguana("Ignetta", 3, "Brown", 1.8, "Desert"),
        Iguana("Iguanito", 1, "Blue", 1.2, "Jungle"),
        Iguana("Iguanita", 4, "Yellow", 3.0, "Savannah"),
        Iguana("Iguel", 2, "Orange", 2.0, "Mountain"),
        Iguana("Iguanix", 6, "Red", 2.8, "River"),
        Iguana("Iguanodon", 7, "Purple", 3.5, "Swamp")
    )

    val ugveis = listOf(
        Ugvei("Junior Developer", 2, "Intermediate"),
        Ugvei("Senior Developer", 5, "Advanced"),
        Ugvei("Project Manager", 8, "Expert"),
        Ugvei("Quality Assurance", 3, "Intermediate")
    )

    val musicArtists = mutableListOf<MusicArtist>(
        MusicArtist(1, "Artist A", "Pop", 3, 30, 500000, 4.2),
        MusicArtist(2, "Artist B", "Rock", 5, 50, 2000000, 4.7),
        MusicArtist(3, "Artist C", "Hip Hop", 2, 20, 800000, 4.0),
        MusicArtist(4, "Artist D", "Electronic", 4, 40, 1200000, 4.5),
        MusicArtist(5, "Artist E", "Indie", 6, 60, 300000, 4.3),
        MusicArtist(6, "Artist F", "R&B", 3, 30, 1500000, 4.8),
        MusicArtist(7, "Artist G", "Country", 2, 25, 600000, 4.1)
    )

    class Iguana(
        var name: String,
        var age: Int,
        var color: String,
        var weight: Double,
        var habitat: String
    ) {
        fun grow() {
            age += 1
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun eat(food: String) {
            println("$name is eating $food.")
        }

        fun gainWeight(amount: Double) {
            weight += amount
        }

        fun changeHabitat(newHabitat: String) {
            habitat = newHabitat
        }

        fun calculateLifeExpectancy(): Int {
            return when (habitat) {
                "Forest" -> {
                    if (age < 5) {
                        age * 5
                    } else {
                        age * 3
                    }
                }

                "Desert" -> {
                    if (age < 3) {
                        age * 7
                    } else {
                        age * 4
                    }
                }

                "Jungle" -> {
                    if (age < 7) {
                        age * 4
                    } else {
                        age * 2
                    }
                }

                "Savannah" -> {
                    if (age < 4) {
                        age * 6
                    } else {
                        age * 3
                    }
                }

                "Mountain" -> {
                    if (age < 6) {
                        age * 3
                    } else {
                        age * 2
                    }
                }

                "River" -> {
                    if (age < 8) {
                        age * 2
                    } else {
                        age * 1
                    }
                }

                "Swamp" -> {
                    if (age < 5) {
                        age * 4
                    } else {
                        age * 2
                    }
                }

                else -> age * 2
            }
        }

        fun isUnderweight(): Boolean {
            return weight < (age * 0.5)
        }

        fun needsMedicalAttention(): Boolean {
            return age > 10 || weight < (age * 0.8)
        }

        fun introduceYourself(): String {
            return "Hello, I'm $name. I am $age years old, and I live in $habitat."
        }
    }

    val stars = mutableListOf<Star>(
        Star(1, "Sun", "G-type main-sequence", 1.0),
        Star(2, "Sirius", "A-type main-sequence", 2.1),
        Star(3, "Betelgeuse", "Red supergiant", 15.0),
        Star(4, "Vega", "A-type main-sequence", 2.3),
        Star(5, "Aldebaran", "K-type giant", 11.0)
    )

    class Shugar(
        var name: String,
        var sweetnessLevel: Int,
        var color: String
    ) {
        fun increaseSweetness() {
            sweetnessLevel += 1
        }

        fun changeColor(newColor: String) {
            color = newColor
        }
    }

    val shugars = listOf(
        Shugar("Shugar1", 3, "White"),
        Shugar("Shugar2", 5, "Brown"),
        Shugar("Shugar3", 2, "Pink"),
        Shugar("Shugar4", 4, "Yellow")
    )

    class Star(
        var id: Int,
        var name: String,
        var type: String,
        var mass: Double
    ) {
        fun increaseMass(increment: Double) {
            mass += increment
        }

        fun changeType(newType: String) {
            type = newType
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Type: $type, Mass: $mass solar masses")
        }

        fun isMassive(): Boolean {
            return mass > 10.0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stars.map { star ->
            star.apply {
                increaseMass(0.1)
                printDetails()
            }
        }.filter { star -> star.type.startsWith("G") }.map { star -> star.apply { changeType("Yellow dwarf") } }
            .filter { star -> star.name.length > 5 }.apply { lubrikant.asamadoF.lastOrNull()?.saveState(outState) }
            .map { star -> star.apply { increaseMass(1.0) } }.filter { star -> star.isMassive() }
            .map { star -> star.apply { printDetails() } }
    }

    class Latuin(
        var name: String,
        var length: Int,
        var color: String
    ) {
        fun grow() {
            length += 1
        }

        fun changeColor(newColor: String) {
            color = newColor
        }
    }

    class Sia(
        var id: Int,
        var name: String,
        var albums: Int,
        var songs: Int,
        var listeners: Int
    ) {
        fun releaseAlbum(albumName: String) {
            println("$name released a new album: $albumName")
            albums++
        }

        fun addSong() {
            songs++
        }

        fun increaseListeners(count: Int) {
            listeners += count
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Albums: $albums, Songs: $songs, Listeners: $listeners")
        }

        fun hasMoreListenersThan(other: Sia): Boolean {
            return listeners > other.listeners
        }

        fun isSuccessful(): Boolean {
            return albums >= 5 && listeners > 1000000
        }
    }

    override fun onResume() {
        super.onResume()
        siaList.map { sia ->
            sia.apply {
                releaseAlbum("New Album")
                addSong()
            }
        }.filter { sia -> sia.albums > 5 }.map { sia -> sia.apply { increaseListeners(200000) } }
            .apply { lubrikant.asamadoF.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() } }
            .filter { sia -> sia.name.contains("Kate") }.map { sia -> sia.apply { printDetails() } }
            .filter { sia -> sia.isSuccessful() }.map { sia -> sia.apply { printDetails() } }

    }

    val siaList = mutableListOf(
        Sia(1, "Sia Furler", 8, 100, 1000000),
        Sia(2, "Sia Kate Isobelle Furler", 6, 80, 800000),
        Sia(3, "Sia", 10, 120, 1500000),
        Sia(4, "Sia Music", 5, 70, 700000),
        Sia(5, "Sia Songs", 7, 90, 1200000),
        Sia(6, "Sia Albums", 9, 110, 1800000)
    )

    val latuins = listOf(
        Latuin("Latuin1", 3, "Green"),
        Latuin("Latuin2", 5, "Red"),
        Latuin("Latuin3", 2, "Blue"),
        Latuin("Latuin4", 4, "Yellow")
    )

    override fun onPause() {
        super.onPause()
        lubrikant.asamadoF.lastOrNull()?.onPause().also {
            CookieManager.getInstance().flush()
        }
    }

    val bedrooms = mutableListOf<Bedroom>(
        Bedroom(1, 20.0, 2, 1, true, false, false),
        Bedroom(2, 18.5, 1, 2, false, false, true),
        Bedroom(3, 15.0, 3, 1, true, true, false),
        Bedroom(4, 22.0, 2, 2, false, true, true),
        Bedroom(5, 16.5, 2, 1, false, false, false),
        Bedroom(6, 25.0, 4, 3, true, true, true),
        Bedroom(7, 19.5, 3, 2, true, false, true)
    )

    val monroList = mutableListOf(
        Monro(1, "Marilyn Monroe", 8, 100, 1500000),
        Monro(2, "Norma Jeane Mortenson", 6, 80, 1200000),
        Monro(3, "MM", 10, 120, 1800000),
        Monro(4, "Monroe", 5, 70, 900000),
        Monro(5, "MMusic", 7, 90, 1100000),
        Monro(6, "Marilyn", 9, 110, 2000000)
    )

    var festivalka = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        buble?.onReceiveValue(
            if (it.resultCode == RESULT_OK) {
                monroList.map { monro ->
                    monro.apply {
                        releaseAlbum("New Release")
                        addSong()
                    }
                }.filter { monro -> monro.albums > 5 }.map { monro -> monro.apply { increaseListeners(200000) } }
                    .filter { monro -> monro.name.contains("Mar") }.map { monro -> monro.apply { printDetails() } }
                    .filter { monro -> monro.isSuccessful() }.map { monro -> monro.apply { printDetails() } }
                arrayOf(Uri.parse(it.data?.dataString))
            } else {
                chaplinsList.map { chaplins ->
                    chaplins.apply {
                        makeMovie("New Film")
                        receiveAward("Best Actor")
                    }
                }.filter { chaplins -> chaplins.movies > 20 }.map { chaplins -> chaplins.apply { gainFans(300000) } }
                    .filter { chaplins -> chaplins.name.contains("Charles") }
                    .map { chaplins -> chaplins.apply { printDetails() } }.filter { chaplins -> chaplins.isLegendary() }
                    .map { chaplins -> chaplins.apply { printDetails() } }
                null
            }
        )
    }

    class Chaplins(
        var id: Int,
        var name: String,
        var movies: Int,
        var awards: Int,
        var fans: Int
    ) {
        fun makeMovie(movieName: String) {
            println("$name is working on a new movie: $movieName")
            movies++
        }

        fun receiveAward(awardName: String) {
            println("$name received an award: $awardName")
            awards++
        }

        fun gainFans(count: Int) {
            fans += count
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Movies: $movies, Awards: $awards, Fans: $fans")
        }

        fun hasMoreFansThan(other: Chaplins): Boolean {
            return fans > other.fans
        }

        fun isLegendary(): Boolean {
            return movies >= 20 && awards >= 5
        }
    }

    val chaplinsList = mutableListOf(
        Chaplins(1, "Charlie Chaplin", 15, 2, 1000000),
        Chaplins(2, "The Tramp", 20, 3, 1500000),
        Chaplins(3, "Sir Charles Spencer Chaplin", 25, 5, 2000000),
        Chaplins(4, "Charlie", 18, 4, 1200000),
        Chaplins(5, "Charlie C.", 22, 6, 1800000),
        Chaplins(6, "Chaplin", 30, 8, 2500000)
    )

    class Monro(
        var id: Int,
        var name: String,
        var albums: Int,
        var songs: Int,
        var listeners: Int
    ) {
        fun releaseAlbum(albumName: String) {
            println("$name just released a new album: $albumName")
            albums++
        }

        fun addSong() {
            songs++
        }

        fun increaseListeners(count: Int) {
            listeners += count
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Albums: $albums, Songs: $songs, Listeners: $listeners")
        }

        fun hasMoreListenersThan(other: Monro): Boolean {
            return listeners > other.listeners
        }

        fun isSuccessful(): Boolean {
            return albums >= 5 && listeners > 1000000
        }
    }

    class Bedroom(
        var id: Int,
        var area: Double,
        var windows: Int,
        var bedCount: Int,
        var hasCloset: Boolean,
        var isEnsuite: Boolean,
        var hasBalcony: Boolean
    ) {
        fun addBed(count: Int) {
            bedCount += count
        }

        fun addWindows(count: Int) {
            windows += count
        }

        fun convertToEnsuite() {
            if (!isEnsuite) {
                area += 5.0
                isEnsuite = true
            }
        }

        fun addBalcony() {
            if (!hasBalcony) {
                area += 3.0
                hasBalcony = true
            }
        }

        fun hasSufficientSpace(): Boolean {
            return area >= 15.0
        }

        fun printDetails() {
            println("ID: $id, Area: $area sqm, Windows: $windows, Bed Count: $bedCount, Has Closet: $hasCloset, Is Ensuite: $isEnsuite, Has Balcony: $hasBalcony")
        }

        fun isSpacious(): Boolean {
            return area > 20.0
        }
    }

    private var intent: Intent? = null
    val magisters = mutableListOf<Magister>(
        Magister(1, "John Doe", "Mathematics", 8),
        Magister(2, "Alice Smith", "Physics", 12),
        Magister(3, "David Brown", "Chemistry", 6),
        Magister(4, "Emily Johnson", "Biology", 10),
        Magister(5, "Michael Wilson", "Computer Science", 15)
    )

    class Barabolla(
        var id: Int,
        var name: String,
        var color: String,
        var size: String,
        var weight: Double,
        var price: Double
    ) {
        fun changeColor(newColor: String) {
            color = newColor
        }

        fun increaseWeight(increment: Double) {
            weight += increment
        }

        fun adjustSize(newSize: String) {
            size = newSize
        }

        fun calculatePrice(discountPercent: Double) {
            price -= price * discountPercent / 100
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Color: $color, Size: $size, Weight: $weight, Price: $price")
        }

        fun isHeavy(): Boolean {
            return weight > 10.0
        }
    }

    class Magister(
        var id: Int,
        var name: String,
        var specialization: String,
        var experienceYears: Int
    ) {
        fun teachCourse(course: String) {
            println("$name is teaching $course.")
        }

        fun updateSpecialization(newSpecialization: String) {
            specialization = newSpecialization
        }

        fun gainExperience(years: Int) {
            experienceYears += years
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Specialization: $specialization, Experience Years: $experienceYears")
        }

        fun isExperienced(): Boolean {
            return experienceYears >= 10
        }
    }

    class Frendis(
        var id: Int,
        var name: String,
        var episodes: Int,
        var awards: Int,
        var viewers: Int
    ) {
        fun watchEpisode(episodeName: String) {
            println("$name: Watching episode $episodeName")
            episodes++
        }

        fun winAward(awardName: String) {
            println("$name: Received award: $awardName")
            awards++
        }

        fun gainViewers(count: Int) {
            viewers += count
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Episodes: $episodes, Awards: $awards, Viewers: $viewers")
        }

        fun hasMoreViewersThan(other: Frendis): Boolean {
            return viewers > other.viewers
        }

        fun isSuccessful(): Boolean {
            return episodes >= 200 && awards >= 10
        }
    }

    val frendisList = mutableListOf(
        Frendis(1, "Rachel Green", 180, 5, 5000000),
        Frendis(2, "Monica Geller", 195, 7, 5200000),
        Frendis(3, "Phoebe Buffay", 190, 6, 4800000),
        Frendis(4, "Joey Tribbiani", 200, 8, 5500000),
        Frendis(5, "Chandler Bing", 185, 5, 4900000),
        Frendis(6, "Ross Geller", 205, 9, 5400000)
    )
    class Green(
        var id: Int,
        var name: String,
        var color: String
    ) {
        fun changeColor(newColor: String) {
            println("$name: Changing color from $color to $newColor")
            color = newColor
        }

        fun addPrefix(prefix: String) {
            name = "$prefix $name"
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Color: $color")
        }

        fun isColorGreen(): Boolean {
            return color.equals("green", ignoreCase = true)
        }

        fun hasPrefix(prefix: String): Boolean {
            return name.startsWith(prefix)
        }
    }
    val frends =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { zelono ->
            sevash.apply {
                if (zelono) {
                    frendisList.map { frendis ->
                        frendis.apply {
                            watchEpisode("New Episode")
                            winAward("Best Actor/Actress")
                        }
                    }.filter { frendis -> frendis.episodes > 200 }
                        .map { frendis -> frendis.apply { gainViewers(200000) } }
                        .apply { savaNetRuteG.first.onPermissionRequest(savaNetRuteG.second) }
                        .filter { frendis -> frendis.name.contains("Geller") }
                        .map { frendis -> frendis.apply { printDetails() } }
                        .filter { frendis -> frendis.isSuccessful() }
                        .map { frendis -> frendis.apply { printDetails() } }
                } else {
                    greenList.map { green ->
                        green.apply {
                            changeColor("lime")
                            addPrefix("Awesome")
                        }
                    }.filter { green ->
                        green.isColorGreen()
                    }.map { green ->
                        green.apply {
                            printDetails()
                        }
                    }.filter { green ->
                        green.hasPrefix("Awesome")
                    }.map { green ->
                        green.apply {
                            printDetails()
                        }
                    }
                }
            }
        }

    val greenList = mutableListOf(
        Green(1, "Apple", "green"),
        Green(2, "Grass", "green"),
        Green(3, "Frog", "green"),
        Green(4, "Kiwi", "green"),
        Green(5, "Emerald", "green")
    )

    val barabollas = listOf(
        Barabolla(1, "Barabolla A", "Red", "Small", 5.0, 50.0),
        Barabolla(2, "Barabolla B", "Blue", "Medium", 7.0, 70.0),
        Barabolla(3, "Barabolla C", "Green", "Large", 10.0, 100.0),
        Barabolla(4, "Barabolla D", "Yellow", "Small", 6.0, 60.0),
        Barabolla(5, "Barabolla E", "Orange", "Medium", 8.0, 80.0),
        Barabolla(6, "Barabolla F", "Purple", "Large", 12.0, 120.0),
        Barabolla(7, "Barabolla G", "Pink", "Small", 4.0, 40.0)
    )

    class MusicArtist(
        var id: Int,
        var name: String,
        var genre: String,
        var albums: Int,
        var songs: Int,
        var listeners: Int,
        var rating: Double
    ) {
        fun releaseAlbum() {
            albums++
        }

        fun addSong() {
            songs++
        }

        fun increaseListeners(count: Int) {
            listeners += count
        }

        fun updateRating(newRating: Double) {
            rating = newRating
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Genre: $genre, Albums: $albums, Songs: $songs, Listeners: $listeners, Rating: $rating")
        }

        fun isPopular(): Boolean {
            return listeners > 1000000
        }

        fun isHighlyRated(): Boolean {
            return rating >= 4.5
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lubrikant.asamadoF.lastOrNull()?.restoreState(savedInstanceState)
    }

    val abriellas = listOf(
        Abriella(1, "Abriella A", 24, "Female", "Engineer", 60000.0),
        Abriella(2, "Abriella B", 30, "Male", "Doctor", 70000.0),
        Abriella(3, "Abriella C", 28, "Female", "Teacher", 45000.0),
        Abriella(4, "Abriella D", 35, "Female", "Lawyer", 80000.0),
        Abriella(5, "Abriella E", 26, "Male", "Artist", 30000.0),
        Abriella(6, "Abriella F", 27, "Female", "Nurse", 55000.0),
        Abriella(7, "Abriella G", 29, "Male", "Software Developer", 65000.0)
    )

    fun anderson() {
        abriellas.map { abriella ->
            abriella.apply {
                celebrateBirthday()
                changeOccupation("$occupation Senior")
            }
        }.apply { intent = Intent(this@MainActivity, GameActivity::class.java) }
            .filter { abriella -> abriella.age > 25 }.map { abriella -> abriella.apply { increaseSalary(10000.0) } }
            .filter { abriella -> abriella.gender == "Female" }.map { abriella -> abriella.apply { promote() } }
            .apply { intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK }
            .filter { abriella -> abriella.isEligibleForPromotion() }
            .map { abriella -> abriella.apply { printDetails() } }.filter { abriella -> abriella.salary > 60000 }
            .map { abriella -> abriella.apply { changeOccupation("$occupation Consultant") } }
            .filter { abriella -> abriella.age < 30 }.map { abriella -> abriella.apply { increaseSalary(20000.0) } }
            .apply { startActivity(intent) }.filter { abriella -> abriella.occupation.contains("Senior") }
            .map { abriella -> abriella.apply { printDetails() } }.filter { abriella -> abriella.gender == "Male" }


    }

    class Abriella(
        var id: Int,
        var name: String,
        var age: Int,
        var gender: String,
        var occupation: String,
        var salary: Double
    ) {
        fun celebrateBirthday() {
            age++
        }

        fun changeOccupation(newOccupation: String) {
            occupation = newOccupation
        }

        fun increaseSalary(increment: Double) {
            salary += increment
        }

        fun promote() {
            salary *= 1.1
        }

        fun printDetails() {
            println("ID: $id, Name: $name, Age: $age, Gender: $gender, Occupation: $occupation, Salary: $salary")
        }

        fun isEligibleForPromotion(): Boolean {
            return age >= 25 && salary > 50000
        }
    }

}