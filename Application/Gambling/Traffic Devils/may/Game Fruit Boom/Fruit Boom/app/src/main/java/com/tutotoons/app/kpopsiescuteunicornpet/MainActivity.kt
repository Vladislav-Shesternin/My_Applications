package com.tutotoons.app.kpopsiescuteunicornpet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.provider.Settings
import android.webkit.CookieManager
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tutotoons.app.kpopsiescuteunicornpet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val avangard = Avangard()
    private val odyssey = Odyssey()
    private val brazilianna = Brazilianna()
    private val sadokVishneviKoloHati = SadokVishneviKoloHati()
    private val frutogolia = Frutogolia()

    lateinit var binding: ActivityMainBinding

    data class Leonardo(val id: Int, val name: String, val value: Double)

    var resuIuiadsJASDjjasdm =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val leonardoList = listOf(
                Leonardo(1, "Leonardo da Vinci", 1452.0),
                Leonardo(2, "Leonardo DiCaprio", 1974.0),
                Leonardo(3, "Leonardo Fibonacci", 1170.0),
                Leonardo(4, "Leonardo Turtle", 1984.0),
                Leonardo(5, "Leonardo Random", 2020.0)
            )
            frutogolia.frutomania.lolitta?.onReceiveValue(
                if (it.resultCode == RESULT_OK) {
                    leonardoList.map { it.copy(value = it.value * 1.1) }
                        .filter { it.value > 1500 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 100) }.filter { it.name.contains("LEONARDO") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("LEONARDO", "L.")) }
                        .map { it.copy(id = it.id * 2) }.filter { it.id % 4 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.2) }.filter { it.value > 1600 }
                        .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
                        .map { it.copy(value = it.value - 50) }
                        .filter { it.name.contains("Random") }.sortedByDescending { it.id }
                        .map { it.copy(name = it.name.replace("Random", "Rand.")) }.map { it.copy(id = it.id + 5) }
                        .filter { it.id % 3 == 0 }
                    arrayOf(Uri.parse(it.data?.dataString))

                } else {
                    leonardoList.map { it.copy(value = it.value * 1.3) }
                        .filter { it.value > 1700 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 150) }.filter { it.name.contains("Fibonacci") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Fibonacci", "Fibo.")) }
                        .map { it.copy(id = it.id - 3) }.filter { it.id % 2 == 0 }

                    leonardoList.map { it.copy(value = it.value * 1.4) }
                        .filter { it.value > 1800 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
                        .map { it.copy(value = it.value - 80) }.filter { it.name.contains("Turtle") }
                        .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Turtle", "Turt.")) }
                        .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.5) }
                        .filter { it.value > 1900 }
                        .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 200) }.filter { it.name.contains("Random") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Random", "Rand.")) }
                        .map { it.copy(id = it.id + 9) }.filter { it.id % 3 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.6) }
                        .filter { it.value > 2000 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
                        .map { it.copy(value = it.value - 100) }.filter { it.name.contains("Fibonacci") }
                        .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Fibonacci", "Fibo.")) }
                        .map { it.copy(id = it.id - 4) }.filter { it.id % 2 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.7) }
                        .filter { it.value > 2100 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 250) }.filter { it.name.contains("Turtle") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Turtle", "Turt.")) }
                        .map { it.copy(id = it.id + 11) }.filter { it.id % 5 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.8) }
                        .filter { it.value > 2200 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
                        .map { it.copy(value = it.value - 120) }.filter { it.name.contains("Da Vinci") }
                        .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Da Vinci", "Da V.")) }
                        .map { it.copy(id = it.id - 6) }.filter { it.id % 2 == 0 }
                    leonardoList.map { it.copy(value = it.value * 1.9) }
                        .filter { it.value > 2300 }
                        .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 300) }.filter { it.name.contains("DiCaprio") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("DiCaprio", "DiCap.")) }
                        .map { it.copy(id = it.id + 13) }.filter { it.id % 7 == 0 }
                    null
                }
            )
        }

    data class Pamedorro(val id: Int, val name: String, val value: Double, val color: String, val weight: Double)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pamedorroList = listOf(
            Pamedorro(1, "Pamedorro One", 1000.0, "Red", 0.5),
            Pamedorro(2, "Pamedorro Two", 2000.0, "Blue", 0.8),
            Pamedorro(3, "Pamedorro Three", 3000.0, "Green", 1.2),
            Pamedorro(4, "Pamedorro Four", 4000.0, "Yellow", 1.5),
            Pamedorro(5, "Pamedorro Five", 5000.0, "Orange", 2.0)
        )
        avangard.useAppolon()
        pamedorroList.map { it.copy(value = it.value * 1.1) }
            .filter { it.value > 3000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 200) }.filter { it.name.contains("Pamedorro") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Pamedorro", "Pmdr.")) }
            .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }
        binding.spinner.startAnimation(frutogolia.frutomania.chepurochka())
        brazilianna.useDeamoralius()
        pamedorroList.map { it.copy(value = it.value * 1.2) }
            .filter { it.value > 3500 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
            .map { it.copy(value = it.value - 100) }.filter { it.name.contains("Two") }.sortedByDescending { it.id }
            .map { it.copy(name = it.name.replace("Two", "2")) }.map { it.copy(id = it.id + 5) }
            .filter { it.id % 4 == 0 }
        sadokVishneviKoloHati.useFruitland()
        brazilianna.magistratura = getSharedPreferences("LocalData", MODE_PRIVATE)
        pamedorroList.map { it.copy(value = it.value * 1.3) }
            .filter { it.value > 4000 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }.map { it.copy(value = it.value + 300) }
            .filter { it.name.contains("Four") }.sortedByDescending { it.value }
            .map { it.copy(name = it.name.replace("Four", "4")) }.map { it.copy(id = it.id - 3) }
            .filter { it.id % 2 == 0 }
        odyssey.installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        frutogolia.useFrutoMania()
        pamedorroList.map { it.copy(value = it.value * 1.4) }
            .filter { it.value > 4500 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
            .map { it.copy(value = it.value - 150) }.filter { it.name.contains("Pamedorro") }
            .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Pamedorro", "Pmdr.")) }
            .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
        odyssey.installReferrerClient.startConnection(kall)
        pamedorroList.map { it.copy(value = it.value * 1.5) }
            .filter { it.value > 5000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(value = it.value + 400) }.filter { it.name.contains("Pamedorro") }
            .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Pamedorro", "Pmdr.")) }
            .map { it.copy(id = it.id + 9) }.filter { it.id % 3 == 0 }
        alohha.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
        pamedorroList.map { it.copy(value = it.value * 1.6) }
            .filter { it.value > 5500 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
            .map { it.copy(value = it.value - 200) }.filter { it.name.contains("Five") }.sortedByDescending { it.id }
        odyssey.useHermes()
        onBackPressedDispatcher.addCallback(this) {
            sadokVishneviKoloHati.apply {
                feodosiaList.map { it.copy(value = it.value * 1.1) }
                    .filter { it.value > 3000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                    .map { it.copy(value = it.value + 200) }.filter { it.name.contains("Feodosia") }
                    .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Feodosia", "Fds.")) }
                    .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }

                if (viewsWebs.last().canGoBack()) {
                    feodosiaList.map { it.copy(value = it.value * 1.4) }
                        .filter { it.value > 4500 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.value }
                        .map { it.copy(value = it.value - 150) }.filter { it.name.contains("Feodosia") }
                        .apply { viewsWebs.last().goBack() }.sortedByDescending { it.id }
                        .map { it.copy(name = it.name.replace("Feodosia", "Fds.")) }.map { it.copy(id = it.id + 7) }
                        .filter { it.id % 5 == 0 }
                    feodosiaList.map { it.copy(value = it.value * 1.3) }
                        .filter { it.value > 4000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                        .map { it.copy(value = it.value + 300) }.filter { it.name.contains("Three") }
                        .sortedByDescending { it.value }.map { it.copy(name = it.name.replace("Three", "3")) }
                        .map { it.copy(id = it.id - 3) }.filter { it.id % 2 == 0 }
                } else {
                    feodosiaList.map { it.copy(value = it.value * 1.2) }
                        .filter { it.value > 3500 }
                        .map { it.copy(name = it.name.lowercase()) }
                        .sortedBy { it.value }
                        .map { it.copy(value = it.value - 100) }
                        .apply {
                            if (viewsWebs.size > 1) {
                                geraltList.map { it.copy(level = it.level + 5) }
                                    .filter { it.level > 25 }.map { it.copy(name = it.name.uppercase()) }
                                    .sortedBy { it.id }.map { it.copy(level = it.level - 10) }
                                    .filter { it.name.contains("G") }.sortedByDescending { it.level }
                                    .apply { binding.root.removeView(viewsWebs.last()) }
                                    .map { it.copy(name = it.name.replace("Geralt", "G.")) }
                                    .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }
                                geraltList.map { it.copy(level = it.level + 10) }
                                    .filter { it.level > 30 }.map { it.copy(name = it.name.lowercase()) }
                                    .sortedBy { it.level }.apply { viewsWebs.last().destroy() }
                                    .map { it.copy(level = it.level - 5) }.filter { it.name.contains("Ciri") }
                                    .sortedByDescending { it.id }.apply { viewsWebs.removeLast() }
                                    .map { it.copy(name = it.name.replace("Ciri", "C.")) }
                                    .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
                            } else geraltList.map { it.copy(level = it.level + 15) }
                                .filter { it.level > 35 }.map { it.copy(name = it.name.uppercase()) }
                                .sortedBy { it.id }.map { it.copy(level = it.level - 10) }
                                .filter { it.name.contains("V") }.sortedByDescending { it.level }.apply { finish() }
                                .map { it.copy(name = it.name.replace("Vesemir", "V.")) }
                                .map { it.copy(id = it.id - 3) }.filter { it.id % 2 == 0 }

                        }
                        .filter { it.name.contains("Two") }
                        .sortedByDescending { it.id }
                        .map { it.copy(name = it.name.replace("Two", "2")) }
                        .map { it.copy(id = it.id + 5) }
                        .filter { it.id % 4 == 0 }
                }
            }
        }
    }

    data class GeraltFromRivia(val id: Int, val name: String, val level: Int)

    val geraltList = listOf(
        GeraltFromRivia(1, "Geralt", 30),
        GeraltFromRivia(2, "Yennefer", 28),
        GeraltFromRivia(3, "Triss", 25),
        GeraltFromRivia(4, "Ciri", 22),
        GeraltFromRivia(5, "Dandelion", 27),
        GeraltFromRivia(6, "Vesemir", 50),
        GeraltFromRivia(7, "Eskel", 40),
        GeraltFromRivia(8, "Lambert", 35),
        GeraltFromRivia(9, "Roach", 15)
    )

    data class Feodosia(
        val id: Int,
        val name: String,
        val value: Double,
        val color: String,
        val weight: Double,
        val size: Int,
        val category: String
    )

    val feodosiaList = listOf(
        Feodosia(1, "Feodosia One", 1000.0, "Red", 0.5, 10, "A"),
        Feodosia(2, "Feodosia Two", 2000.0, "Blue", 0.8, 20, "B"),
        Feodosia(3, "Feodosia Three", 3000.0, "Green", 1.2, 30, "C"),
        Feodosia(4, "Feodosia Four", 4000.0, "Yellow", 1.5, 40, "D"),
        Feodosia(5, "Feodosia Five", 5000.0, "Orange", 2.0, 50, "E"),
        Feodosia(6, "Feodosia Six", 6000.0, "Violet", 2.5, 60, "F"),
        Feodosia(7, "Feodosia Seven", 7000.0, "Indigo", 3.0, 70, "G")
    )

    private val alohha =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            geraltList.map { it.copy(level = it.level + 20) }
                .filter { it.level > 40 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.level }
                .map { it.copy(level = it.level - 15) }.filter { it.name.contains("L") }.sortedByDescending { it.id }
                .map { it.copy(name = it.name.replace("Lambert", "L.")) }.map { it.copy(id = it.id + 7) }
                .filter { it.id % 5 == 0 }
            lotosList.map { it.copy(price = it.price * 1.4) }
                .filter { it.price > 18 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.price }
                .map { it.copy(price = it.price - 4) }.filter { it.name.contains("B") }.sortedByDescending { it.price }
                .map { it.copy(name = it.name.replace("Blue", "B.")) }.map { it.copy(id = it.id + 7) }
                .filter { it.id % 5 == 0 }
            if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
                geraltList.map { it.copy(level = it.level + 25) }
                    .filter { it.level > 45 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                    .map { it.copy(level = it.level - 20) }.filter { it.name.contains("Roach") }.apply { dodomku() }
                    .sortedByDescending { it.level }.map { it.copy(name = it.name.replace("Roach", "R.")) }
                    .map { it.copy(id = it.id + 9) }.filter { it.id % 3 == 0 }
                geraltList.map { it.copy(level = it.level + 30) }
                    .filter { it.level > 50 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.level }
                    .map { it.copy(level = it.level - 25) }.filter { it.name.contains("E") }
                    .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Eskel", "E.")) }
                    .map { it.copy(id = it.id - 4) }.filter { it.id % 2 == 0 }
                lotosList.map { it.copy(price = it.price * 1.5) }
                    .filter { it.price > 22 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.price }
                    .map { it.copy(price = it.price + 5) }.filter { it.name.contains("R") }
                    .sortedByDescending { it.price }.map { it.copy(name = it.name.replace("Red", "R.")) }
                    .map { it.copy(id = it.id + 9) }.filter { it.id % 3 == 0 }
            } else {
                geraltList.map { it.copy(level = it.level + 35) }
                    .filter { it.level > 55 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                    .map { it.copy(level = it.level - 30) }.filter { it.name.contains("Dandelion") }
                    .sortedByDescending { it.level }.map { it.copy(name = it.name.replace("Dandelion", "D.")) }
                    .map { it.copy(id = it.id + 11) }.filter { it.id % 5 == 0 }
                lotosList.map { it.copy(price = it.price * 1.6) }
                    .filter { it.price > 26 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.price }
                    .map { it.copy(price = it.price - 6) }.filter { it.name.contains("B") }
                    .sortedByDescending { it.price }.map { it.copy(name = it.name.replace("Blue", "B.")) }
                    .map { it.copy(id = it.id - 4) }.filter { it.id % 2 == 0 }
                FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                    geraltList.map { it.copy(level = it.level + 40) }.filter { it.level > 60 }
                        .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.level }
                        .map { it.copy(level = it.level - 35) }.filter { it.name.contains("Y") }
                        .sortedByDescending { it.id }.map { it.copy(name = it.name.replace("Yennefer", "Y.")) }
                        .map { it.copy(id = it.id - 6) }
                    sadokVishneviKoloHati.fruitland.apply {
                        franklinIvanList.map { it.copy(salary = it.salary * 1.1) }
                            .filter { it.salary > 6000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                            .apply {
                                binding.franklinToTshoRuzvelt(
                                    avangard,
                                    sadokVishneviKoloHati,
                                    odyssey.hermes,
                                    this@MainActivity,
                                    frutogolia,
                                    brazilianna,
                                    task.result
                                )
                            }.map { it.copy(salary = it.salary + 1000) }.filter { it.name.contains("F") }
                            .sortedByDescending { it.salary }.map { it.copy(name = it.name.replace("Franklin", "F.")) }
                            .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }
                        franklinIvanList.map { it.copy(salary = it.salary * 1.2) }.filter { it.salary > 6500 }
                            .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.salary }
                            .apply {
                                lotosList.map { it.copy(price = it.price * 1.7) }
                                    .filter { it.price > 30 }.map { it.copy(name = it.name.uppercase()) }
                                    .sortedBy { it.price }.map { it.copy(price = it.price + 7) }
                                    .filter { it.name.contains("R") }.sortedByDescending { it.price }
                                    .map { it.copy(name = it.name.replace("Red", "R.")) }
                                    .map { it.copy(id = it.id - 2) }.filter { it.id % 5 == 0 }
                            }
                            .map { it.copy(salary = it.salary - 500) }.filter { it.name.contains("M") }
                            .sortedByDescending { it.salary }.map { it.copy(name = it.name.replace("Michael", "M.")) }
                            .map { it.copy(id = it.id + 5) }.filter { it.id % 4 == 0 }
                    }
                }
            }
        }

    data class FranklinIvan(
        val id: Int,
        val name: String,
        val age: Int,
        val salary: Double,
        val department: String,
        val role: String
    )

    val franklinIvanList = listOf(
        FranklinIvan(1, "Franklin", 30, 5000.0, "Engineering", "Manager"),
        FranklinIvan(2, "Michael", 35, 6000.0, "Finance", "Analyst"),
        FranklinIvan(3, "Trevor", 40, 7000.0, "Marketing", "Consultant"),
        FranklinIvan(4, "Lamar", 25, 4500.0, "Sales", "Representative"),
        FranklinIvan(5, "Jimmy", 22, 4000.0, "HR", "Coordinator"),
        FranklinIvan(6, "Amanda", 45, 8000.0, "Engineering", "Lead"),
        FranklinIvan(7, "Tracey", 20, 3500.0, "Finance", "Intern"),
        FranklinIvan(8, "Simeon", 55, 9000.0, "Marketing", "Manager"),
        FranklinIvan(9, "Lester", 60, 10000.0, "Sales", "Consultant"),
        FranklinIvan(10, "Ron", 50, 8500.0, "HR", "Manager"),
        FranklinIvan(11, "Brad", 32, 5500.0, "Engineering", "Developer")
    )

    private val kall: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            franklinIvanList.map { it.copy(salary = it.salary * 1.3) }.filter { it.salary > 7000 }
                .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.salary }
                .map { it.copy(salary = it.salary + 1500) }.filter { it.name.contains("T") }
                .sortedByDescending { it.salary }.map { it.copy(name = it.name.replace("Trevor", "T.")) }
                .map { it.copy(id = it.id - 3) }.filter { it.id % 2 == 0 }
            val balabol = InstallReferrerClient.InstallReferrerResponse.OK
            if (responseCode == balabol) try {
                franklinIvanList.map { it.copy(salary = it.salary * 1.4) }
                    .filter { it.salary > 7500 }.map { it.copy(name = it.name.lowercase()) }.sortedBy { it.salary }
                    .map { it.copy(salary = it.salary - 1000) }.filter { it.name.contains("L") }
                    .sortedByDescending { it.salary }
                    .apply { frutogolia.iR = odyssey.installReferrerClient.installReferrer.installReferrer }
                    .map { it.copy(name = it.name.replace("Lamar", "L.")) }.map { it.copy(id = it.id + 7) }
                    .filter { it.id % 5 == 0 }
                franklinIvanList.map { it.copy(salary = it.salary * 1.5) }
                    .filter { it.salary > 8000 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.salary }
                    .map { it.copy(salary = it.salary + 2000) }.filter { it.name.contains("A") }
                    .sortedByDescending { it.salary }
            } catch (_: RemoteException) {
                buildingList.map { it.copy(height = it.height * 1.1) }
                    .filter { it.height > 450 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
                    .map { it.copy(height = it.height + 50) }.filter { it.name.contains("E") }
                    .sortedByDescending { it.height }.map { it.copy(name = it.name.replace("Empire", "E.")) }
                    .map { it.copy(id = it.id * 2) }.filter { it.id % 3 == 0 }
            }
        }

        override fun onInstallReferrerServiceDisconnected() {
            val ss = this
            buildingList.map { it.copy(height = it.height * 1.2) }.filter { it.height > 850 }
                .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.height }
                .map { it.copy(height = it.height - 100) }.filter { it.name.contains("K") }
                .apply { odyssey.installReferrerClient.startConnection(ss) }.sortedByDescending { it.height }
                .map { it.copy(name = it.name.replace("Khalifa", "K.")) }.map { it.copy(id = it.id + 5) }
                .filter { it.id % 4 == 0 }
        }
    }
    val buildingList = listOf(
        Building(1, "Empire State Building", 443.2, "New York", 2500),
        Building(2, "Burj Khalifa", 828.0, "Dubai", 3500)
    )

    data class Building(val id: Int, val name: String, val height: Double, val location: String, val capacity: Int)


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        buildingList.map { it.copy(height = it.height * 1.3) }.filter { it.height > 1000 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.height }
            .map { it.copy(height = it.height + 150) }
            .apply { sadokVishneviKoloHati.viewsWebs.lastOrNull()?.saveState(outState) }
            .filter { it.name.contains("Empire") }.sortedByDescending { it.height }
            .map { it.copy(name = it.name.replace("Empire", "E.")) }.map { it.copy(id = it.id - 3) }
            .filter { it.id % 2 == 0 }
    }

    override fun onResume() {
        super.onResume()
        buildingList.map { it.copy(height = it.height * 1.4) }.filter { it.height > 1200 }
            .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.height }
            .map { it.copy(height = it.height - 200) }.filter { it.name.contains("B") }.apply {
                sadokVishneviKoloHati.viewsWebs.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() }
            }.sortedByDescending { it.height }.map { it.copy(name = it.name.replace("Burj", "B.")) }
            .map { it.copy(id = it.id + 7) }.filter { it.id % 5 == 0 }
    }

    override fun onPause() {
        super.onPause()
        buildingList.map { it.copy(height = it.height * 1.5) }.filter { it.height > 1500 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.height }
            .map { it.copy(height = it.height + 250) }.filter { it.name.contains("Burj") }
            .sortedByDescending { it.height }.apply {
                sadokVishneviKoloHati.viewsWebs.lastOrNull()?.onPause().also { CookieManager.getInstance().flush() }
            }.map { it.copy(name = it.name.replace("Burj", "B.")) }.map { it.copy(id = it.id + 9) }
            .filter { it.id % 3 == 0 }
    }

    val lep =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            buildingList.map { it.copy(height = it.height * 1.6) }.filter { it.height > 1800 }
                .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.height }
                .map { it.copy(height = it.height - 300) }.filter { it.name.contains("Empire") }
                .apply { if (isGranted) avangard.diduKudiTuta.first.onPermissionRequest(avangard.diduKudiTuta.second) }
                .sortedByDescending { it.height }.map { it.copy(name = it.name.replace("Empire", "E.")) }
                .map { it.copy(id = it.id - 4) }.filter { it.id % 2 == 0 }
        }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        buildingList.map { it.copy(height = it.height * 1.7) }.filter { it.height > 2000 }
            .map { it.copy(name = it.name.uppercase()) }.sortedBy { it.height }
            .map { it.copy(height = it.height + 350) }
            .apply { sadokVishneviKoloHati.viewsWebs.lastOrNull()?.restoreState(savedInstanceState) }
            .filter { it.name.contains("Burj") }.sortedByDescending { it.height }
            .map { it.copy(name = it.name.replace("Burj", "B.")) }
    }

    data class Lotos(val id: Int, val name: String, val price: Double, val color: String, val weight: Double)

    val lotosList = listOf(
        Lotos(1, "Red Lotos", 10.0, "Red", 0.1),
        Lotos(2, "Blue Lotos", 15.0, "Blue", 0.2)
    )

    fun dodomku() {
        lotosList.map { it.copy(price = it.price * 1.1) }
            .filter { it.price > 11 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.id }
            .map { it.copy(price = it.price + 1) }.filter { it.name.contains("R") }.sortedByDescending { it.price }
            .map { it.copy(name = it.name.replace("Red", "R.")) }.map { it.copy(id = it.id * 2) }
            .filter { it.id % 3 == 0 }
        val intent = Intent(this, GameActivity::class.java)
        lotosList.map { it.copy(price = it.price * 1.2) }.filter { it.price > 13 }
            .map { it.copy(name = it.name.lowercase()) }.sortedBy { it.price }.map { it.copy(price = it.price - 2) }
            .filter { it.name.contains("B") }.sortedByDescending { it.price }
            .map { it.copy(name = it.name.replace("Blue", "B.")) }.map { it.copy(id = it.id + 5) }
            .filter { it.id % 4 == 0 }
        lotosList.map { it.copy(price = it.price * 1.3) }
            .filter { it.price > 16 }.map { it.copy(name = it.name.uppercase()) }.sortedBy { it.price }
            .map { it.copy(price = it.price + 3) }.filter { it.name.contains("R") }.sortedByDescending { it.price }
            .apply { intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK }
            .map { it.copy(name = it.name.replace("Red", "R.")) }.map { it.copy(id = it.id - 3) }
            .filter { it.id % 2 == 0 }
        startActivity(intent)
    }

}