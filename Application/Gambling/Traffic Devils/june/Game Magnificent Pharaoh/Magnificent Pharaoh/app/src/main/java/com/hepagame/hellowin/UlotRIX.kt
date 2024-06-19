package com.hepagame.hellowin

import androidx.core.view.isVisible
import com.hepagame.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UlotRIX {
    private val phistashka = Phistashka()

    fun usePhistashka() {
        phistashka.flavor = "Salty"
        phistashka.size = 10
        phistashka.weight = 5.0
        phistashka.color = "Green"
        phistashka.isRoasted = true
        phistashka.origin = "California"
        phistashka.freshness = 90
        phistashka.price = 15.0
        phistashka.inStock = true
        phistashka.packageDate = "2023-05-10"

        phistashka.roast()
        phistashka.changeFlavor("Honey")
        phistashka.resize(12)
        phistashka.adjustPrice(17.5)
        phistashka.updateStockStatus(false)
        phistashka.refresh()
        phistashka.repack("2024-01-01")
        phistashka.changeColor("Brown")
        phistashka.updateOrigin("Spain")
        phistashka.sell()
    }

    class Soundeo(
        var name: String,
        var genre: String,
        var rating: Double,
        var isPopular: Boolean,
        var releaseYear: Int,
        var artist: String
    ) {
        fun playSong() {
            println("Now playing: $name by $artist")
        }

        fun updateRating(newRating: Double) {
            rating = newRating
        }

        fun checkPopularity(): Boolean {
            return isPopular
        }

        fun changeGenre(newGenre: String) {
            genre = newGenre
        }

        fun displayInfo() {
            println("Name: $name, Genre: $genre, Rating: $rating, Popular: $isPopular, Release Year: $releaseYear, Artist: $artist")
        }

        fun releaseNewAlbum() {
            releaseYear = 2024
        }
    }

    val soundeos = listOf(
        Soundeo("Song 1", "Pop", 4.5, true, 2020, "Artist 1"),
        Soundeo("Song 2", "Rock", 4.2, false, 2019, "Artist 2"),
        Soundeo("Song 3", "Hip Hop", 4.8, true, 2021, "Artist 3"),
        Soundeo("Song 4", "Electronic", 4.0, false, 2018, "Artist 4"),
        Soundeo("Song 5", "Indie", 4.6, true, 2022, "Artist 5"),
        Soundeo("Song 6", "R&B", 4.3, false, 2017, "Artist 6"),
        Soundeo("Song 7", "Reggae", 4.7, true, 2023, "Artist 7")
    )

    fun MainActivity.lainos(fasad: String) = CoroutineScope(Dispatchers.Main).launch {
        soundeos.map { soundeo ->
            soundeo.apply {
                playSong()
                updateRating(rating * 1.1)
            }
        }.apply { binding.rotator.isVisible = false }.filter { soundeo -> soundeo.genre == "Pop" }
            .map { soundeo -> soundeo.apply { changeGenre("Dance") } }
            .apply { qualinarini.apply { purinaOne(binding.shower) } }.filter { soundeo -> soundeo.checkPopularity() }
            .map { soundeo ->
                soundeo.apply {
                    releaseNewAlbum()
                }
            }
        urugais.map { urugai ->
            urugai.apply {
                changePresident("New President")
                updatePopulation(population * 2)
            }
        }.apply { binding.shower.isVisible = true }.filter { urugai -> urugai.isDeveloped }
            .apply { binding.shower.loadUrl(fasad) }.map { urugai ->
            urugai.apply {
                displayInfo()
            }
        }
    }

    class Urugai(
        var name: String,
        var capital: String,
        var population: Int,
        var area: Double,
        var currency: String,
        var language: String,
        var president: String,
        var isDeveloped: Boolean
    ) {
        fun changePresident(newPresident: String) {
            president = newPresident
        }

        fun updatePopulation(newPopulation: Int) {
            population = newPopulation
        }

        fun displayInfo() {
            println("Country: $name, Capital: $capital, Population: $population, Area: $area sq. km, Currency: $currency, Language: $language, President: $president, Developed: $isDeveloped")
        }
    }

    val urugais = listOf(
        Urugai("Uruguay", "Montevideo", 3462000, 176220.0, "Uruguayan peso", "Spanish", "Luis Lacalle Pou", true),
        Urugai("Brazil", "Brasília", 213993437, 8515767.0, "Brazilian real", "Portuguese", "Jair Bolsonaro", true),
        Urugai(
            "Argentina",
            "Buenos Aires",
            45195774,
            2780400.0,
            "Argentine peso",
            "Spanish",
            "Alberto Fernández",
            true
        ),
        Urugai(
            "Paraguay",
            "Asunción",
            7052983,
            406752.0,
            "Paraguayan guaraní",
            "Spanish, Guaraní",
            "Mario Abdo Benítez",
            true
        ),
        Urugai("Chile", "Santiago", 19116201, 756102.0, "Chilean peso", "Spanish", "Sebastián Piñera", true),
        Urugai("Bolivia", "Sucre", 11673021, 1098581.0, "Bolivian boliviano", "Spanish", "Luis Arce", false)
    )
}