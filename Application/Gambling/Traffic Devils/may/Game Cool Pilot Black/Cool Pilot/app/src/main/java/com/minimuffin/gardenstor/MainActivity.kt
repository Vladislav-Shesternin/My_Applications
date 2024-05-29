package com.minimuffin.gardenstor

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.os.RemoteException
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.minimuffin.gardenstor.databinding.ActivityMainBinding
import com.minimuffin.gardenstor.util.LottieUtil
import com.minimuffin.gardenstor.util.OneTime
import com.minimuffin.gardenstor.util.log
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)



    var heraBUBNA = true
    lateinit var dodo: Pair<WebChromeClient, PermissionRequest>

    fun dummyCalculations(a: Int, b: Int): Int {
        var result = 0
        for (i in 1..a) {
            result += (i * b) % 3
        }
        return result
    }

    private val onceExit  = OneTime()

    private lateinit var wersos      : ActivityMainBinding


    class DummyClass1 {
        fun doNothing1(): Int {
            val i = 5
            return i
        }

        fun doNothing2(param1: Int, param2: String): Boolean {
            return param1 > 0 && param2.isNotEmpty()
        }
    }

    private lateinit var navController: NavController
    lateinit var lottie               : LottieUtil

    class DummyClass2 {
        val randomString: String
            get() = "Lorem Ipsum"

        fun calculateSomething(a: Int, b: Int): Int {
            return (a * b) - (a + b)
        }
    }


    var blide: ValueCallback<Array<Uri>>? = null


    var eleganto = mutableListOf<WebView>()


    var melitoranevssssaks = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val numbers = mutableListOf<Int>(5)

        DummyClass1().doNothing2(1, "sushi")

        for (number in numbers) {
            if (number % 2 == 0) {
                println("$number is even")
            } else {
                println("$number is odd")
            }
        }
        blide?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null)
        for (i in 1..10) {
            numbers.add(i)
        }
        dummyCalculations(LargeDummyClass().dummyCombinedOperations(), 5)

        val processedNumbers = processNumbers(numbers)
        println("Processed Numbers: $processedNumbers")

        val dummyMap = mutableMapOf<String, Int>()
        for (i in 1..50) {
            dummyMap["Key$i"] = i * i
        }

        for ((key, value) in dummyMap) {
            if (key.length % 2 == 0) {
                println("Key: $key, Value: $value")
            } else {
                println("Key (odd length): $key, Value: $value")
            }
        }
    }


    private fun processNumbers(numbers: List<Int>): List<Int> {
        return numbers.map { it * it }.filter { it % 2 == 0 }
    }



    private fun manipulateString(input: String): String {
        val stringBuilder = StringBuilder()
        for (i in input.indices) {
            stringBuilder.append(if (i % 2 == 0) input[i].toUpperCase() else input[i].toLowerCase())
        }
        return stringBuilder.toString()
    }

    private fun generateRandomNumbers(size: Int): List<Int> {
        return List(size) { (1..100).random() }
    }

    private fun processComplexNumbers(numbers: List<Int>): List<Int> {
        return numbers.map { it * it }.filter { it % 2 != 0 }.sortedDescending()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numbers = mutableListOf<Int>(5)
        val filteredNumbers = numbers.filter { it > 50 }.map { it * 2 }
        println("Filtered Numbers: $filteredNumbers")


        val inputString = "Hello, World!"
        val manipulatedString = manipulateString(inputString)
        println("Manipulated String: $manipulatedString")
        initialize()

        val randomNumbers = generateRandomNumbers(200)

        for (num in randomNumbers) {
            when {
                num % 3 == 0 && num % 5 == 0 -> println("$num is divisible by both 3 and 5")
                num % 3 == 0 -> println("$num is divisible by 3")
                num % 5 == 0 -> println("$num is divisible by 5")
                else -> println("$num is not divisible by 3 or 5")
            }
        }
        wersos.lober.startAnimation(DummyDataGenerator.galoppom)
        shmeperton = getSharedPreferences(Utility.sd, Utility.s)
        val processedNumbers = processComplexNumbers(randomNumbers)
        println("Processed Complex Numbers: $processedNumbers")

        val dummyMap = mutableMapOf<String, Int>()
        for (i in 1..100) {
            dummyMap["Key$i"] = i * i
        }

        val sortedMap = dummyMap.toSortedMap(compareBy { it.length })
        for ((key, value) in sortedMap) {
            if (key.length % 2 == 0) {
                println("Sorted Key: $key, Value: $value")
            } else {
                println("Sorted Key (odd length): $key, Value: $value")
            }
        }
        colia = InstallReferrerClient.newBuilder(this).build()

        val factorialResult = factorial(10)
        println("Factorial of 10: $factorialResult")

        val filteredNumberss = randomNumbers.filter { it > 50 && it % 2 == 0 }.map { it * 3 }
        println("Filtered and Mapped Numbers: $filteredNumberss")

        val sampleString = "Complex String Manipulation!"
        val manipulatedStsring = complexStringManipulation(sampleString)
        println("Manipulated Complex String: $manipulatedStsring")

        simulateComplexProcess()
        colia.startConnection(ilionora)

        when {
            countDigits(0) == 5 -> {
                simulateAdvancedProcess()

                val inputStringl = "Advanced String Manipulation Example"
                val complexString = complexStringOperations(inputStringl)
                println("Complex String: $complexString + i$inputStringl")
            }
            Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1 -> {
                val numbers22 = generateNumberList(300)

                for (number in numbers22) {
                    when {
                        number % 4 == 0 && number % 6 == 0 -> println("$number is divisible by both 4 and 6")
                        number % 4 == 0 -> println("$number is divisible by 4")
                        number % 6 == 0 -> println("$number is divisible by 6")
                        else -> println("$number is not divisible by 4 or 6")
                    }
                }
                val transformedNumbers = transformNumbers(numbers)

                goTuVeselitsa()
                val dummyClass1 = DummyClass1()

                val dummyDataMap = mutableMapOf<String, Int>()
                for (i in 1..150) {
                    dummyDataMap["Key$i"] = i * i * 2
                }

                val result = dummyClass1.doNothing2(42, "Hello, World! $transformedNumbers")


                val filteredMap = dummyDataMap.filter { it.value % 3 == 0 }
                for ((key, value) in filteredMap) {
                    println("Filtered Key: $key, Value: $value")
                }
                result.and(true)
            }
            shmeperton.contains(DummyDataGenerator.key) -> {
                val utility = Utility()
                val uselessStr = utility.uselessMethod1()
                val uselessDouble = utility.uselessMethod2(3.14)
                val digitCountResult = countDigits(1234567890)
                println("Number of digits in 1234567890: $digitCountResult")

                val oddNumbers = numbers.filter { it % 2 != 0 }.map { it * 5 }
                println("Filtered and Multiplied Odd Numbers: $oddNumbers")

                val uselessList = utility.uselessMethod3()
                uselessStr+uselessDouble+heraBUBNA+uselessList
                pokazatShoto(shmeperton.getString(DummyDataGenerator.key, DummyDataGenerator.key22) ?: DummyDataGenerator.key22)
                val dummyClass2 = DummyClass2()
                val randomStr = dummyClass2.randomString
                val calculationResult = dummyClass2.calculateSomething(7, 3)
                randomStr+calculationResult
            }
            else -> paliturina()
        }

        onBackPressedDispatcher.addCallback(this) {
            val inputList = (1..50).toList() + (1..50).toList()
            val complexCode = ComplexCode()

            if (eleganto.last().canGoBack()) {
                val finalResult = complexCode.calculate(inputList)
                val d = "hello" + finalResult
                generateNumberList1(d.length)

                eleganto.last().goBack()
            }
            else {
                if (eleganto.size > 1) {

                    val numbersg = mutableListOf<Int>()
                    var sum = 0

                    for (i in 1..100 step 2) {
                        numbersg.add(i)
                        sum += i
                    }

                    for (i in numbersg.indices) {
                        if (numbersg[i] % 3 == 0) {
                            numbersg[i] *= 2
                        }
                    }

                    numbersg.removeAll { it % 5 == 0 }
                    numbersg.forEach { println(it) }
                    wersos.root.removeView(eleganto.last())
                    eleganto.last().destroy()
                    val average = sum / numbersg.size.toDouble()

                    val max = numbersg.maxOrNull()
                    val min = numbersg.minOrNull()

                    val random = (average / 2).toInt()

                    val isInList = random in numbersg

                    transformNumbers(listOf(max?.inc() ?: 0, min?.inc() ?: 0, isInList.toString().length))

                    eleganto.removeLast()
                } else finish()
            }
        }
    }

    class ComplexCode {
        private var result: Int = 0

        fun calculate(input: List<Int>): Int {
            val filteredList = input.filter { it % 2 == 0 }.map { it * 3 }
            val sumOfFiltered = filteredList.sum()

            val modifiedList = mutableListOf<Int>()
            for (i in input.indices) {
                if (input[i] % 3 == 0) {
                    modifiedList.add(input[i] * 2)
                } else {
                    modifiedList.add(input[i])
                }
            }

            val productOfModified = modifiedList.reduce { acc, num -> acc * num }

            val uniqueValues = input.toSet()
            val uniqueSum = uniqueValues.sumBy { it }

            val randomValue = (1..100).random()
            val randomNumber = input.random()

            result = sumOfFiltered + productOfModified + uniqueSum + randomValue + randomNumber

            return result
        }
    }

    private fun factorial(n: Int): Int {
        return if (n <= 1) {
            1
        } else {
            n * factorial(n - 1)
        }
    }

    private fun generateNumberList(size: Int): List<Int> {
        return List(size) { (1..200).random() }
    }

    private fun transformNumbers(numbers: List<Int>): List<Int> {
        return numbers.map { it * it - it }.filter { it % 5 != 0 }.sorted()
    }



    private fun complexStringManipulation(input: String): String {
        val stringBuilder = StringBuilder()
        for (i in input.indices) {
            stringBuilder.append(
                when (i % 4) {
                    0 -> input[i].toUpperCase()
                    1 -> input[i].toLowerCase()
                    2 -> input[i]
                    else -> '*'
                }
            )
        }
        return stringBuilder.toString()
    }

    private fun simulateComplexProcess() {
        val complexData = mutableListOf<String>()
        for (i in 1..10) {
            for (j in 'a'..'z') {
                complexData.add("$i$j")
            }
        }

        for (data in complexData) {
            println("Processing: $data")
        }

        val result = complexData.groupBy { it.length }
        println("Grouped Data: $result")
    }

    private lateinit var colia: InstallReferrerClient

    private fun countDigits(number: Int): Int {
        return if (number == 0) {
            0
        } else {
            1 + countDigits(number / 10)
        }
    }



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


    private fun generateNumberList1(size: Int): List<Int> {
        return List(size) { (1..200).random() }
    }

    private var farion = ""

    private fun transformNumbers1(numbers: List<Int>): List<Int> {
        return numbers.map { it * it - it }.filter { it % 5 != 0 }.sorted()
    }

    private fun initialize() {
        wersos = ActivityMainBinding.inflate(layoutInflater)
        setContentView(wersos.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = LottieUtil(wersos)
    }
    private lateinit var shmeperton: SharedPreferences




    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }


    private fun complexStringOperations(input: String): String {
        val stringBuilder = StringBuilder()
        for (i in input.indices) {
            stringBuilder.append(
                when {
                    i % 5 == 0 -> input[i].toUpperCase()
                    i % 3 == 0 -> input[i].toLowerCase()
                    else -> input[i]
                }
            )
        }
        return stringBuilder.toString()
    }

    private fun simulateAdvancedProcess() {
        val data = mutableListOf<String>()
        for (i in 1..20) {
            for (j in 'A'..'Z') {
                data.add("$i$j")
            }
        }

        for (item in data) {
            println("Processing item: $item")
        }

        val groupedData = data.groupBy { it.first() }
        for ((key, value) in groupedData) {
            println("Group $key: $value")
        }

        val flatMappedData = data.flatMap { listOf(it, it.reversed()) }
        println("Flat Mapped Data: $flatMappedData")
    }
    private val ilionora: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {

            val numbers = generateNumberList1(300)

            val results = mutableListOf<String>()
            for (number in numbers) {
                when {
                    number % 4 == 0 && number % 6 == 0 -> results.add("$number is divisible by both 4 and 6")
                    number % 4 == 0 -> results.add("$number is divisible by 4")
                    number % 6 == 0 -> results.add("$number is divisible by 6")
                    else -> results.add("$number is not divisible by 4 or 6")
                }
            }

            if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                farion = colia.installReferrer.installReferrer
            } catch (_: RemoteException) {
                val transformedNumbers = transformNumbers1(numbers)
                val transformedResults = mutableListOf<Int>().apply { addAll(transformedNumbers) }
                complexStringOperations1(transformedResults.toString())

            }

            val dummyDataMap = mutableMapOf<String, Int>()
            for (i in 1..150) {
                dummyDataMap["Key$i"] = i * i * 2
            }

            val filteredMap = dummyDataMap.filter { it.value % 3 == 0 }
            val mapResults = mutableListOf<Pair<String, Int>>().apply { addAll(filteredMap.toList()) }

            complexStringManipulation(mapResults.toString())
        }

        override fun onInstallReferrerServiceDisconnected() {

            val digitCountResult = countDigits1(1234567890)

            countDigits(digitCountResult)
            val numbers = generateNumberList(300)
            val oddNumbers = numbers.filter { it % 2 != 0 }.map { it * 5 }
            mutableListOf<Int>().apply { addAll(oddNumbers) }
            colia.startConnection(this)

            val inputString = "Advanced String Manipulation Example"
            complexStringOperations(inputString)
            simulateAdvancedProcess1()


        }
    }

    private fun countDigits1(number: Int): Int {
        return if (number == 0) {
            0
        } else {
            1 + countDigits(number / 10)
        }
    }

    private fun complexStringOperations1(input: String): String {
        val stringBuilder = StringBuilder()
        for (i in input.indices) {
            stringBuilder.append(
                when {
                    i % 5 == 0 -> input[i].toUpperCase()
                    i % 3 == 0 -> input[i].toLowerCase()
                    else -> input[i]
                }
            )
        }
        return stringBuilder.toString()
    }

    private fun simulateAdvancedProcess1() {
        val data = mutableListOf<String>()
        for (i in 1..20) {
            for (j in 'A'..'Z') {
                data.add("$i$j")
            }
        }

        val groupedData = data.groupBy { it.first() }
        val flatMappedData = data.flatMap { listOf(it, it.reversed()) }
        val complexResults = mutableListOf<String>().apply {
            addAll(groupedData.values.flatten())
            addAll(flatMappedData)
        }

        complexStringManipulation(complexResults.toString())
    }



    private fun pokazatShoto(buter: String) = CoroutineScope(Dispatchers.Main).launch {

        val array = Array(100) { it + 1 }

        for (i in array.indices) {
            if (array[i] % 2 == 0) {
                array[i] *= 3
            } else {
                array[i] -= 10
            }
        }

        var sumEven = 0

        wersos.lober.isVisible = false
        wersos.ololo.elohhha()
        var sumOdd = 0
        for (num in array) {
            if (num % 2 == 0) {
                sumEven += num
            } else {
                sumOdd += num
            }
        }

        if (sumEven > sumOdd) {
            sumEven - sumOdd
        } else if (sumEven < sumOdd) {
            sumOdd - sumEven
        } else {
            sumEven * sumOdd
        }
        wersos.ololo.isVisible = true

        var average = 0
        for (num in array) {
            average += num
        }
        average /= array.size
        average = listOf(1,2,3).first()
        wersos.ololo.loadUrl(DummyDataGenerator.generateRandomString(if ((average == 1)==true) average else 1), hashMapOf(FruitCollector.fZabua to buter))
    }

    suspend fun SaliDranLok(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->
        val randomNumbers = generateRandomNumbers12(10)

        val galina = "${TrueReturningMethods.triNula}${UUID.randomUUID()}"

        val transformedNumbers = randomNumbers.map { if (it % 2 == 0) it * 2 else it / 2 }

        val default = TrueReturningMethods.debora
        val numbersString = transformedNumbers.joinToString(separator = ", ", prefix = "[", postfix = "]")

        val hasEvenNumbers = randomNumbers.any { it % 2 == 0 }

        val maxNumber = randomNumbers.maxOrNull()


        var apologize = try {
            val numbersMap = randomNumbers.mapIndexed { index, value -> "Number${index + 1}" to value }.toMap()

            val sortedNumbers = randomNumbers.sorted()

            val inputString = "Hello, World!" + numbersString + hasEvenNumbers + maxNumber + numbersMap + sortedNumbers
            val manipulatedString = inputString.replace("Hello", "Hola").reversed()

            val average = randomNumbers.average()
            complexStringOperations1(manipulatedString + average)

            AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!


        } catch (e: Exception) {
            val randomNumbers213 = generateRandomNumbers(20)

            val oddNumbers = randomNumbers213.filter { it % 2 != 0 }

            val numbersStringsss = randomNumbers213.joinToString(separator = ", ", prefix = "[", postfix = "]")

            val numberStrsings = randomNumbers213.map { it.toString() }

            val sum = randomNumbers.sum()

            val factorialResult = factorial(5)

            val largestPrimeNumber = randomNumbers.filter { isPrime(it) }.maxOrNull()

            complexStringManipulation("dd" + oddNumbers + numbersString + numbersStringsss + numberStrsings + sum + factorialResult + largestPrimeNumber)

            galina
        }
        if (apologize == default) apologize = galina
        continuation.resume(if (allow) apologize else default)
    }

    fun generateRandomNumbers12(count: Int): List<Int> {
        return List(count) { (1..100).random() }
    }

    private fun paliturina() = CoroutineScope(Dispatchers.Main).launch {

        val listOfLists = mutableListOf<List<Int>>()

        for (i in 0 until 2) {
            val innerList = mutableListOf<Int>()
            for (j in 0 until 2) {
                innerList.add((1..2).random())
            }
            listOfLists.add(innerList)
        }

        val sums = mutableListOf<Int>()
        var totalSum = 0
        for (list in listOfLists) {
            var sum = 0
            for (num in list) {
                sum += num
            }
            totalSum += sum
            sums.add(sum)
        }
        val avariger = withContext(Dispatchers.IO) {
            totalSum / sums.size
            SaliDranLok(this@MainActivity, TrueReturningMethods.method6())
        }

        val evenLists = mutableListOf<List<Int>>()
        for (list in listOfLists) {
            val evenNumbers = list.filter { it % 2 == 0 }
            evenLists.add(evenNumbers)
        }
        OneSignal.initWithContext(this@MainActivity, TrueReturningMethods.OS1)
        OneSignal.login(avariger)

        val simacha = StringBuilder("${Game.g}${avariger}${ComplexCodeBlock.iaisdo}$farion")

        fun isPrimeggg(num: Int): Boolean {
            if (num <= 1) {
                return false
            }
            for (i in 2 until num) {
                if (num % i == 0) {
                    return false
                }
            }
            return true
        }
        val sb = StringBuilder()

        val primeNumbersCount = mutableListOf<Int>()
        for (list in listOfLists) {
            var count = 0
            for (num in list) {
                if (isPrime(num)) {
                    count++
                }
            }
            primeNumbersCount.add(count)
        }
        simacha.apply {
            sb.append("Це перший рядок.\n")
            val number = 42
            sb.append("Число: ").append(number).append("\n")
            sb.insert(0, "Початок: ${isPrimeggg(number)}")
            sb.delete(9, 18)
            shmeperton.edit().putString("link", this.toString()).apply()
            sb.replace(0, 7, "Новий текст: ")
            sb.setLength(10)
            sb.setLength(0)
            sb.append("Це новий рядок.")
            pokazatShoto(this.toString())
        }
    }

    fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        if (n <= 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
    protected fun WebView.elohhha() {
        fun executeComplexTask(input: String): String {
            val words = input.split(" ")
            val uniqueSortedWords = words.toSet().sorted()
            val processedString = uniqueSortedWords.joinToString(" ")
            val capitalizedString = processedString.split(" ").joinToString(" ") { it.capitalize() }
            val wordLengthMap = words.associateWith { it.length }
            val averageWordLength = wordLengthMap.values.average()
            val sortedWordLengthPairs = wordLengthMap.toList().sortedBy { it.second }
            val wordLengthPairsString = sortedWordLengthPairs.joinToString { "(${it.first}, ${it.second})" }
            val longestWord = words.maxByOrNull { it.length }
            val uniqueLetters = words.joinToString("").toSet().size
            return """
            Processed String: $capitalizedString
            Average Word Length: $averageWordLength
            Word Length Pairs: $wordLengthPairsString
            Longest Word: $longestWord
            Unique Letters: $uniqueLetters
        """.trimIndent()
        }
        webChromeClient = helloIo()

        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val result = list
            .filter { it % 2 == 0 } // відфільтрувати парні числа
            .map { it * it }        // піднести до квадрата кожне число
            .takeWhile { it < 50 } // взяти елементи, поки не буде перевищено 50
            .sortedDescending() // відсортувати в зворотньому порядку

        FruitCollector(result.joinToString())

        webViewClient = sabizoru()
        isSaveEnabled = TrueReturningMethods.method1()

        countDigits(executeComplexTask(dummyCalculations(1,2).toString()).length)

        val list2 = listOf("apple", "banana", "orange", "grape", "pineapple")

        val result2 = list2
            .filter { it.length > 5 } // відфільтрувати рядки, що мають більше 5 символів
            .map { it.toUpperCase() } // перетворити кожний рядок на верхній регістр
            .sortedByDescending { it.length } // відсортувати за зменшенням довжини рядка
            .take(2) // взяти перші два елементи


        val complexCodeBlock = ComplexCodeBlock()

        isFocusableInTouchMode = TrueReturningMethods.method2()

        val results = complexCodeBlock.executeComplexTask()
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, TrueReturningMethods.method2())

        val result4 = list
            .filter { it % 2 == 0 } // відфільтрувати парні числа
            .map { it * 2 } // помножити кожне число на 2
            .takeWhile { it < 10 } // взяти елементи, поки не буде перевищено 10
            .sortedByDescending { it } // відсортувати в зворотньому порядку
            .mapIndexed { index, value -> value * (index + 1) } // помножити кожне число на його індекс + 1
            .dropLast(2) // видалити останні 2 елементи


        results.forEach { println(it) }

        val userManager = UserManager()

        User2(result4.toString(), result4.size)

        setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
        val user1 = User("Alice", 25)
        userManager.addUser(user1)

        val user2 = User("Bob", 30)
        userManager.addUser(user2)
        CookieManager.getInstance().setAcceptCookie(TrueReturningMethods.method4())

        val result5 = list2
            .filter { it.length > 5 } // відфільтрувати рядки, що мають більше 5 символів
            .map { it.toUpperCase() } // перетворити кожний рядок на верхній регістр
            .sortedByDescending { it.length } // відсортувати за зменшенням довжини рядка
            .takeWhile { it.length > 7 } // взяти елементи, поки довжина рядка більша за 7
            .mapIndexed { index, value -> value.take(index + 1) } // обрізати кожний рядок до довжини індексу + 1
            .drop(2) // видалити перші 2 елементи


        userManager.updateUserEmail("Alice", "alice@example.com")
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        isFocusable = TrueReturningMethods.method3()
        val user3 = User("Charlie + $result5", 35)
        userManager.addUser(user3)

        userManager.deleteUser("Bob")


        user1.sendEmail("Hello!")
        user3.sendEmail("How are you?")
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        settings.apply {
            userManager.printUsers()
            FunFruits("k").apply { helloPidarasGugoldovich() }

            fun generateRandomUser(): User2 {
                val names = listOf("David", "Emma", "Frank", "Grace", "Henry")
                val randomName = names.random()
                val randomAge = (18.. 65).random()
                return User2(randomName, randomAge)
            }

            val userManager2 = UserManager2()

            Game(farion).apply { podrostok() }
            userManager2.addUser(User2("Alice", 25))

            userManager2.addUser(User2("Bob", 30))

            val result6 = list2
                .filter { it.length > 5 } // відфільтрувати рядки, що мають більше 5 символів
                .sortedByDescending { it.length } // відсортувати за зменшенням довжини рядка
                .takeWhile { it.length > 7 } // взяти елементи, поки довжина рядка більша за 7
                .drop(2) // видалити перші 2 елементи
                .map { it.toUpperCase() } // перетворити кожний рядок на верхній регістр
                .mapIndexed { index, value -> value.take(index + 1) } // обрізати кожний рядок до довжини індексу + 1


            allowContentAccess = TrueReturningMethods.method6()

            userManager2.addUser(User2("Charlie", 35))


            FruitCollector(shmeperton.toString()+result6).digiBETON(this)

            userManager2.updateUserEmail("Alice", "alice@example.com")


            cacheMode = WebSettings.LOAD_DEFAULT
            userManager2.deleteUser("Bob")
            userManager2.printUsers()

            ComplexCodeBlock().also { fh ->
                fh.mrazota = this
                fh.hometa()
            }



            userManager2.users.values.forEach {
                it.sendEmail("Hello ${it.name}! How are you?")
            }
            setSupportMultipleWindows(true)
            databaseEnabled = TrueReturningMethods.method9()

            val list234 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            val result123 = list234
                .map { if (it % 2 == 0) it * 2 else it + 10 } // помножити парні числа на 2, інші збільшити на 10
                .filter { it < 15 } // відфільтрувати числа менше 15
                .groupBy { if (it % 2 == 0) "Even" else "Odd" } // згрупувати числа за парність/непарність
                .mapValues { (_, values) -> values.joinToString(", ") } // об'єднати числа в рядки
                .toSortedMap() // сортувати результати


            val randomUser2 = generateRandomUser().apply {
                this.name = result123.toString()
            }
            domStorageEnabled = TrueReturningMethods.method10()

            userManager2.addUser(randomUser2)
            javaScriptEnabled = TrueReturningMethods.method2()
            println("\nUpdated Users:")
            userManager.printUsers()
            displayZoomControls = listOf(false, false, false).random()

        }
        eleganto.add(this)
    }

    fun helloIo() = object : WebChromeClient() {
        val fruitManager = FruitManager()

        override fun onProgressChanged(view: WebView, nP: Int) {
            fruitManager.printFruits()

            fruitManager.getFruitByName("Apple")?.decreaseQuantity(3)

            wersos.zapretka.isVisible = nP < DummyDataGenerator.VALLL

            fruitManager.getFruitByName("Banana")?.decreaseQuantity(5)

            wersos.zapretka.progress = nP

        }

        override fun onPermissionRequest(request: PermissionRequest) {
            fruitManager.addFruit(Fruit("Apple", 10).apply { expiryDate = "2024-06-01" })
            fruitManager.addFruit(Fruit("Banana", 20).apply { expiryDate = "2024-06-05" })
            fruitManager.addFruit(Fruit("Orange", 15).apply { expiryDate = "2024-06-03" })

            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                request.grant(request.resources)
                fruitManager.removeFruit("Orange")
            } else {
                dodo = Pair(this, request)
                println("\nUpdated Fruit List:")
                fruitManager.printFruits()
                permanentus.launch(Manifest.permission.CAMERA)
                println("\nTotal number of fruits: ${fruitManager.getFruitsCount()}")
            }
        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            val game = Game("Snakes and Ladders")
            game.addPlayer(Player("Alice"))

            game.endGame()
            val wv = WebView(this@MainActivity)
            wv.elohhha()
            game.addPlayer(Player("Bob"))
            game.addPlayer(Player("Charlie"))
            wersos.root.addView(wv)
            game.startGame()
            game.printPlayers()
            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv
            resultMsg.sendToTarget()

            val currentPlayer = game.currentPlayer()
            if (currentPlayer != null) {
                game.rollDice().let { dice ->
                    println("${currentPlayer.name} rolled $dice.")
                    game.movePlayerForward(dice)
                }
                game.nextTurn()
            }

            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {

                val apple = FunFruits("Apple").apply {
                    color = "Red"
                    taste = "Sweet"
                    blide = fpc
                }

                val banana = FunFruits("Banana").apply {
                    color = "Yellow"
                    taste = "Creamy"
                }

                val orange = FunFruits("Orange").apply {
                    color = "Orange"
                    taste = "Citrusy"
                }

                println("Before any actions:")
                apple.describe()
                banana.describe()
                orange.describe()

                fcp?.createIntent()?.let { melitoranevssssaks.launch(it) }

                println("\nAfter some actions:")
                apple.eat()
                banana.spoil()
                orange.grow()

                apple.describe()
                banana.describe()
                orange.describe()

            } catch (_: ActivityNotFoundException) { }
            return true
        }
    }

    class Plant(val name: String) {
        var height: Double = kotlin.random.Random.nextDouble(1.0, 10.0)
        var isHealthy: Boolean = true
        var waterLevel: Int = kotlin.random.Random.nextInt(1, 11)

        fun grow() {
            if (isHealthy) {
                val growthAmount = kotlin.random.Random.nextDouble(0.1, 1.0)
                height += growthAmount
                waterLevel -= kotlin.random.Random.nextInt(1, 4)
                println("$name is growing! Height: $height")
            } else {
                println("$name is not healthy and cannot grow.")
            }
        }

        fun water(amount: Int) {
            waterLevel += amount
            println("Watering $name with $amount units of water. Water level: $waterLevel")
        }

        fun checkHealth() {
            if (waterLevel >= 5) {
                isHealthy = true
                println("$name is healthy.")
            } else {
                isHealthy = false
                println("$name is not healthy. Please water it.")
            }
        }

        fun describe() {
            println("Name: $name")
            println("Height: $height")
            println("Water level: $waterLevel")
            println("Is healthy: $isHealthy")
        }
    }

    override fun onPause() {
        super.onPause()
        girl.throwFruit("Orange", 2, "Jack")
        eleganto.lastOrNull()?.onPause().also {
            println("\nTotal available fruits: ${girl.getTotalAvailableFruits()}")
            CookieManager.getInstance().flush()
        }
    }

    val boy = FruitCollector("John")

    fun sabizoru() = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            val rose = Plant("Rose")
            val sunflower = Plant("Sunflower")
            val cactus = Plant("Cactus")

            println("Before any actions:")
            rose.describe()
            sunflower.describe()
            cactus.describe()


            cactus.water(2)
            val result = buildString { append("Зовнішній блок\n")
                (1..3).forEach { outer ->
                    append("  Вкладений блок $outer\n")
                    (1..2).forEach { middle -> append("    Глибокий вкладений блок $middle\n") } } }
            if (url?.startsWith("https://tureepicpopulationwheel.life") == true) {
                val result = (1..2).map { outer -> (1..2).map { inner -> (1..3).map { deep ->
                            outer * inner * deep
                        }
                    }
                }.flatten().flatten().sum()
                println("\nAfter some actions: $result")
                rose.water(7)
                sunflower.water(4)
                goTuVeselitsa()
            }

            transformNumbers1(listOf( result.count()))

            rose.grow()
            sunflower.grow()
            cactus.grow()

            rose.checkHealth()
            sunflower.checkHealth()
            cactus.checkHealth()

            println("\nFinal state:")
            rose.describe()
            sunflower.describe()
            cactus.describe()
        }


        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {

            boy.collectFruit("Apple", 5)
            boy.collectFruit("Banana", 3)
            boy.collectFruit("Orange", 4)

            val url = request.url.toString()

            boy.printCollectedFruits()

            return if (url.contains("${FunFruits.LEXA}s://" + FunFruits("Ledalas").feromi())) TrueReturningMethods.method2()
            else if (url.startsWith(FunFruits.LEXA)) {

                boy.collectFruit("Banana", 1)
                boy.collectFruit("Orange", 7)
                boy.collectFruit("Apple", 8)

                false
            }
            else {
                try {
                    boy.eatFruit("Apple", 2)
                    boy.eatFruit("Banana", 4)
                    boy.eatFruit("Orange", 2)

                    view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                } catch (e: java.lang.Exception) {
                    println("\nTotal collected fruits: ${boy.getCollectedFruitsCount()}")

                    if (url.contains("line:")) {
                        boy.collectFruit("Orange", 7)
                        boy.collectFruit("Apple", 8)

                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=jp.naver.line.android"))
                        boy.eatFruit("Banana", 1)
                        boy.eatFruit("Orange", 1)

                        view.context.startActivity(intent)
                    }
                }
                true
            }
        }
    }

    val girl = FruitThrower("Emily")

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        girl.addFruit("Apple", 10)
        eleganto.lastOrNull()?.saveState(outState)
        girl.addFruit("Banana", 7)
    }

    class Plate(val name: String) {
        private var caughtFruits = mutableListOf<String>()

        fun catchFruit(fruitName: String) {
            caughtFruits.add(fruitName)
            println("$name caught a $fruitName!")
        }

        fun printCaughtFruits() {
            println("$name's caught fruits:")
            caughtFruits.forEach { println(it) }
        }

        fun getTotalCaughtFruits(): Int {
            return caughtFruits.size
        }
    }

    val permanentus = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isolatef ->
        girl.addFruit("Apple", 2)
        if (isolatef) dodo.first.onPermissionRequest(dodo.second)
    }

    val fatiMers = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { u_mene_mers_sidennie_est ->

        val plate = Plate("Kitchen Plate")

        OneSignal.consentRequired = true
        plate.catchFruit("Apple")
        plate.catchFruit("Banana")

        OneSignal.consentGiven = heraBUBNA
        OneSignal.initWithContext(this@MainActivity, DummyDataGenerator.OS2)

        plate.catchFruit("Orange")

        println("\nCaught fruits details:")
        CoroutineScope(Dispatchers.IO).launch {
            val add = SaliDranLok(this@MainActivity, heraBUBNA)

            plate.printCaughtFruits()

            println("\nTotal caught fruits: ${plate.getTotalCaughtFruits()}")
            shmeperton.edit().putBoolean("adb", true).apply()
            plate.catchFruit("Banana")
            OneSignal.login(add)
        }
        println("\nCaught fruits details:")
        plate.printCaughtFruits()
        goToGame()
    }

    private var tmpDialog: AlertDialog? = null

    fun goTuVeselitsa() {
        val soundSystem = SoundSystem("Living Room Sound System")

        if (shmeperton.contains(TrueReturningMethods.pipka)) {
            soundSystem.setVolume(70)
            soundSystem.adjustBass(60)
            goToGame()
        }
        else {
            soundSystem.adjustTreble(40)
            soundSystem.mute()
            val view = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                soundSystem.playTrack("My Favorite Song")
                soundSystem.printSettings()
                findViewById<Button>(R.id.btn_agree).setOnClickListener {
                    heraBUBNA = true
                    println("\nAfter some adjustments:")
                    soundSystem.unmute()
                    soundSystem.stop()
                    fatiMers.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
                findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                    heraBUBNA = false
                    soundSystem.setVolume(80)
                    fatiMers.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }

            tmpDialog = AlertDialog.Builder(this).apply {
                setView(view)
                println("\nAfter some adjustments:")
                soundSystem.unmute()
                setCancelable(false)
            }.create()

            soundSystem.printSettings()

            tmpDialog?.show()

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        eleganto.lastOrNull()?.restoreState(savedInstanceState)
    }

    class SoundSystem(val name: String) {
        private var volume: Int = 50
        private var bassLevel: Int = 50
        private var trebleLevel: Int = 50
        private var isMuted: Boolean = false
        private var currentTrack: String = ""
        private var isPlaying: Boolean = false

        fun setVolume(volume: Int) {
            this.volume = volume.coerceIn(0, 100)
            println("$name volume set to $volume%")
        }

        fun adjustBass(level: Int) {
            bassLevel = level.coerceIn(0, 100)
            println("$name bass level adjusted to $level")
        }

        fun adjustTreble(level: Int) {
            trebleLevel = level.coerceIn(0, 100)
            println("$name treble level adjusted to $level")
        }

        fun mute() {
            isMuted = true
            println("$name muted")
        }

        fun unmute() {
            isMuted = false
            println("$name unmuted")
        }

        fun playTrack(trackName: String) {
            currentTrack = trackName
            isPlaying = true
            println("$name is now playing: $trackName")
        }

        fun stop() {
            currentTrack = ""
            isPlaying = false
            println("$name stopped")
        }

        fun printSettings() {
            println("$name settings:")
            println("Volume: $volume%")
            println("Bass level: $bassLevel")
            println("Treble level: $trebleLevel")
            println("Muted: $isMuted")
            println("Current track: $currentTrack")
            println("Playing: $isPlaying")
        }
    }

    override fun onResume() {
        super.onResume()
        girl.addFruit("Orange", 5)
        eleganto.lastOrNull()?.onResume().also {
            girl.printAvailableFruits()
            CookieManager.getInstance().flush()
            girl.throwFruit("Apple", 3, "Tom")
            girl.throwFruit("Banana", 5, "Sara")
        }
    }

    fun goToGame() {
        wersos.apply {
            listOf(ololo, zapretka, lober).onEach { itemView ->
                itemView.clearAnimation()
                root.removeView(itemView)
            }
            eleganto.onEach { root.removeView(it) }
            tmpDialog?.dismiss()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setStartDestination(R.id.libGDXFragment)
    }

}