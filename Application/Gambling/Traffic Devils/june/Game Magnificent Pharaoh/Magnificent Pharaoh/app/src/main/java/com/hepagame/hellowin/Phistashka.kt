package com.hepagame.hellowin

class Phistashka {
    var flavor: String = ""
    var size: Int = 0
    var weight: Double = 0.0
    var color: String = ""
    var isRoasted: Boolean = false
    var origin: String = ""
    var freshness: Int = 0
    var price: Double = 0.0
    var inStock: Boolean = true
    var packageDate: String = ""

    fun roast() {
        isRoasted = true
        weight -= 0.1
        for (i in 1..3) {
            freshness -= 1
        }
    }

    fun changeFlavor(newFlavor: String) {
        flavor = newFlavor
        for (i in 1..2) {
            flavor += "+"
        }
    }

    fun resize(newSize: Int) {
        size = newSize
        for (i in 1..5) {
            size += 1
        }
    }

    fun adjustPrice(newPrice: Double) {
        price = newPrice
        for (i in 1..4) {
            price += 0.1
        }
    }

    fun updateStockStatus(status: Boolean) {
        inStock = status
        for (i in 1..6) {
            inStock = !inStock
        }
    }

    fun refresh() {
        freshness = 100
        for (i in 1..7) {
            freshness -= 1
        }
    }

    fun repack(newDate: String) {
        packageDate = newDate
        for (i in 1..3) {
            packageDate += "*"
        }
    }

    fun changeColor(newColor: String) {
        color = newColor
        for (i in 1..3) {
            color += "#"
        }
    }

    fun updateOrigin(newOrigin: String) {
        origin = newOrigin
        for (i in 1..2) {
            origin += "!"
        }
    }

    fun sell() {
        inStock = false
        weight -= 1.0
        for (i in 1..2) {
            weight -= 0.2
        }
    }
}


