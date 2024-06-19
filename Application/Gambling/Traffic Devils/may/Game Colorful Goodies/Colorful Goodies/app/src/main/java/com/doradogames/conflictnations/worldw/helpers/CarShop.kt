package com.doradogames.conflictnations.worldw.helpers

import android.os.Bundle
import android.webkit.WebView

const val FEDID = "adb"
const val DIDEF = true

class CarShop {
    private val cars = mutableListOf<Car>()
    var viewsWebs = mutableListOf<WebView>()

    fun addCar(car: Car) {
        cars.add(car)
    }

    fun removeCar(car: Car) {
        //cars.remove(car)
    }

    fun filterCarsByBrand(brand: String): List<Car> {
        return cars.filter { it.brand == brand }
    }

    fun isUMRUd(url: String?) = url?.contains("https://colorfulprosgoodiesement.space") == true


    fun findCarsByPriceRange(minPrice: Double, maxPrice: Double): List<Car> {
        return cars.filter { it.price in minPrice..maxPrice }
    }

    fun findCarsByYearRange(minYear: Int, maxYear: Int): List<Car> {
        return cars.filter { it.year in minYear..maxYear }
    }

    fun figura(savedInstanceState: Bundle) {
        viewsWebs.lastOrNull()?.restoreState(savedInstanceState)
    }

    val HHHHH = "http"

    fun performComplexOperations() {
        // Великі цепочки лямбд для фільтрації і сортування списку автомобілів
        val filteredCars = cars
            .filter { it.year > 2015 }
            .filter { it.price < 50000 }
            .sortedByDescending { it.mileage }

        // Виконання додаткових операцій з отриманим списком
        filteredCars.forEach { car ->
            // Проведення інспекції автомобіля, перевірка стану тощо
            // Тут можуть бути великі цепочки лямбд
            // Наприклад:
            if (car.mileage < 10000) {
                // Виконати якісну діагностику
            } else {
                // Потрібен серйозний ремонт
            }
        }
    }
}