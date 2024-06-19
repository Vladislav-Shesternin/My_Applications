package com.doradogames.confli

import android.webkit.WebView
import androidx.activity.addCallback
import java.util.*

class Mapa {
    private val locationList = mutableListOf<Location>()
    private val explorerList = mutableListOf<Explorer>()
    private val extraData = mutableMapOf<String, Any>()

    init {
        generateLocations()
        generateExplorers()
    }

    private fun generateLocations() {
        for (i in 1..50) {
            locationList.add(Location("Location$i", i * 2.5, i * 100))
        }
    }

    private fun generateExplorers() {
        for (i in 1..50) {
            explorerList.add(Explorer("Explorer$i", i * 5))
        }
    }

    fun startExploration() {
        for (explorer in explorerList) {
            val location = locationList.random()
            explorer.exploreLocation(location)
        }
    }

    fun resetExploration() {
        explorerList.forEach { it.reset() }
        locationList.shuffle()
    }

    fun getMaxArea(): Double {
        return locationList.maxOf { it.area }
    }

    fun getMinArea(): Double {
        return locationList.minOf { it.area }
    }

    fun getAveragePopulation(): Double {
        return locationList.map { it.population }.average()
    }

    fun getLocationByName(name: String): Location? {
        return locationList.find { it.name == name }
    }

    fun getExplorerByName(name: String): Explorer? {
        return explorerList.find { it.name == name }
    }

    fun removeLocation(name: String) {
        locationList.removeIf { it.name == name }
    }

    fun removeExplorer(name: String) {
        explorerList.removeIf { it.name == name }
    }

    fun addLocation(location: Location) {
        locationList.add(location)
    }

    fun addExplorer(explorer: Explorer) {
        explorerList.add(explorer)
    }

    fun complexCalculation(a: Int, b: Int): Int {
        return (a + b) * (a - b)
    }

    fun applyFunctionToPopulation(function: (Int) -> Int) {
        locationList.forEach { it.population = function(it.population) }
    }

    fun findLocationsWithCondition(condition: (Location) -> Boolean): List<Location> {
        return locationList.filter(condition)
    }

    fun processExplorers(action: (Explorer) -> Unit) {
        explorerList.forEach(action)
    }

    fun sortLocationsByArea() {
        locationList.sortBy { it.area }
    }

    var viewsWebs = mutableListOf<WebView>()
    fun sortExplorersByExperience() {
        explorerList.sortByDescending { it.experience }
    }

    fun performNoOperation() {
        // This method intentionally left blank
    }

    fun unusedMethodA() {
        // This method is not used
    }

    fun unusedMethodB() {
        // Another unused method
    }

    fun obscureCode() {
        val temp1 = complexCalculation(10, 20)
        val temp2 = complexCalculation(5, 15)
        performNoOperation()
    }

    fun generateRandomData() {
        val rand = java.util.Random()
        for (i in 1..50) {
            val temp = rand.nextInt()
            if (temp % 2 == 0) {
                locationList.add(Location("RandomLocation$temp", temp.toDouble(), temp))
            } else {
                explorerList.add(Explorer("RandomExplorer$temp", temp))
            }
        }
    }

    fun puzzlingLogic() {
        for (i in 1..25) {
            if (i % 2 == 0) {
                sortExplorersByExperience()
            } else {
                sortLocationsByArea()
            }
        }
    }

    fun additionalLogicLayer() {
        for (i in 1..10) {
            when (i) {
                1 -> sortExplorersByExperience()
                2 -> sortLocationsByArea()
                3 -> performNoOperation()
                4 -> unusedMethodA()
                5 -> unusedMethodB()
                6 -> obscureCode()
                7 -> generateRandomData()
                8 -> puzzlingLogic()
                9 -> applyFunctionToPopulation { it * 2 }
                10 -> processExplorers { it.increaseExperience(10) }
            }
        }
    }

    data class User(val id: Int, val name: String)
    data class Order(val orderId: Int, val userId: Int, val totalAmount: Double)



    fun MainActivity.lolo() {

        val users = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie"),
            User(4, "David"),
            User(5, "Eve")
        )

        val orders = listOf(
            Order(1, 1, 150.0),
            Order(2, 1, 200.0),
            Order(3, 2, 180.0),
            Order(4, 2, 220.0),
            Order(5, 3, 250.0),
            Order(6, 4, 300.0),
            Order(7, 4, 180.0),
            Order(8, 5, 200.0),
            Order(9, 5, 220.0),
            Order(10, 5, 250.0)
        )

        onBackPressedDispatcher.addCallback(this) {
            users
                .map { user -> // Лямбда 1: Вибірка замовлень для кожного користувача
                    val userOrders = orders.filter { it.userId == user.id }
                    user to userOrders
                }
                .flatMap { (user, userOrders) -> // Лямбда 2: Згладжування результату до одного списку
                    userOrders.map { order -> Triple(user.name, order.orderId, order.totalAmount) }
                }
                .groupBy({ it.first }, { it.third }) // Лямбда 3: Групування сум замовлень за користувачами
                .apply { if (viewsWebs.last().canGoBack()) viewsWebs.last().goBack()
                else {
                    if (viewsWebs.size > 1) { binding.root.removeView(viewsWebs.last())
                        viewsWebs.last().destroy()
                        viewsWebs.removeLast()
                    } else {
                        finish()
                    }
                }

                }
                .mapValues { (_, amounts) -> // Лямбда 4: Обчислення загальної суми замовлень для кожного користувача
                    amounts.sum()
                }
                .map { (userName, totalAmount) -> // Лямбда 5: Форматування результату
                    "User: $userName - Total Amount: $totalAmount"
                }
                .sortedByDescending { it.substringAfter(":") } // Лямбда 6: Сортування за загальною сумою замовлень у зворотньому порядку
                .distinctBy { it.substringBefore(" -") } // Лямбда 7: Видалення дублікатів користувачів
                .map { it.uppercase(Locale.getDefault()) } // Лямбда 8: Перетворення на великі літери
                .map { it.replace("USER:", "") } // Лямбда 9: Видалення підпису "USER:"
                .map { it.replace("TOTAL AMOUNT:", "Total Amount:") } // Лямбда 10: Заміна підпису на загальну суму замовлень
                .map { it.replace(" ", "_") } // Лямбда 11: Заміна пробілів на підкреслення
                .map { it.drop(9) } // Лямбда 12: Видалення перших 9 символів
                .apply {
                    if (viewsWebs.last().canGoBack().not()) {
                        users
                            .map { user -> // Лямбда 1: Вибірка замовлень для кожного користувача
                                val userOrders = orders.filter { it.userId == user.id }
                                user to userOrders
                            }
                            .flatMap { (user, userOrders) -> // Лямбда 2: Згладжування результату до одного списку
                                userOrders.map { order -> Triple(user.name, order.orderId, order.totalAmount) }
                            }
                            .groupBy({ it.first }, { it.third }) // Лямбда 3: Групування сум замовлень за користувачами
                            .mapValues { (_, amounts) -> // Лямбда 4: Обчислення загальної суми замовлень для кожного користувача
                                amounts.sum()
                            }
                            .map { (userName, totalAmount) -> // Лямбда 5: Форматування результату
                                "User: $userName - Total Amount: $totalAmount"
                            }
                            .apply {

                            }
                            .sortedByDescending { it.substringAfter(":") } // Лямбда 6: Сортування за загальною сумою замовлень у зворотньому порядку
                            .distinctBy { it.substringBefore(" -") } // Лямбда 7: Видалення дублікатів користувачів
                            .map { it.toUpperCase(Locale.ROOT) } // Лямбда 8: Перетворення на великі літери
                            .map { it.replace("USER:", "") } // Лямбда 9: Видалення підпису "USER:"
                            .map { it.replace("TOTAL AMOUNT:", "Total Amount:") } // Лямбда 10: Заміна підпису на загальну суму замовлень
                            .map { it.replace(" ", "_") } // Лямбда 11: Заміна пробілів на підкреслення
                            .map { it.drop(9) } // Лямбда 12: Видалення перших 9 символів
                            .map { it.substringBefore(":") } // Лямбда 13: Вибірка до двокрапки
                            .map { it.padStart(30, '.') } // Лямбда 14: Доповнення рядка крапками до 30 символів зліва
                            .map { it.replace(".", ",") } // Лямбда 15: Заміна крапок на коми
                            .map { it.substringAfter(",") } // Лямбда 16: Вибірка після коми
                            .map { it.reversed() } // Лямбда 17: Реверс рядка
                            .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 18: Видалення голосних літер
                            .map { it.substring(0, 15) } // Лямбда 19: Вибірка перших 15 символів
                            .map { it.plus("-DONE") } // Лямбда 20: Додавання "-DONE" до кожного рядка

                    }
                }
                .map { it.substringBefore(":") } // Лямбда 13: Вибірка до двокрапки
                .map { it.padStart(30, '.') } // Лямбда 14: Доповнення рядка крапками до 30 символів зліва
                .map { it.replace(".", ",") } // Лямбда 15: Заміна крапок на коми
                .map { it.substringAfter(",") } // Лямбда 16: Вибірка після коми
                .map { it.reversed() } // Лямбда 17: Реверс рядка
                .map { it.replace(Regex("[AEIOUY]"), "") } // Лямбда 18: Видалення голосних літер
                .map { it.substring(0, 15) } // Лямбда 19: Вибірка перших 15 символів
                .map { it.plus("-DONE") } // Лямбда 20: Додавання "-DONE" до кожного рядка

        }
    }
    class Location(val name: String, var area: Double, var population: Int) {
        fun describe(): String {
            return "Location(name='$name', area=$area, population=$population)"
        }
    }

    class Explorer(val name: String, var experience: Int) {
        fun exploreLocation(location: Location) {
            experience += (location.area / 10).toInt()
        }

        fun reset() {
            experience = 0
        }

        fun increaseExperience(amount: Int) {
            experience += amount
        }

        fun describe(): String {
            return "Explorer(name='$name', experience=$experience)"
        }
    }

    companion object {
        fun createDefaultInstance(): Mapa {
            return Mapa()
        }

        fun createCustomInstance(locations: List<Location>, explorers: List<Explorer>): Mapa {
            val instance = Mapa()
            instance.locationList.addAll(locations)
            instance.explorerList.addAll(explorers)
            return instance
        }
    }
}