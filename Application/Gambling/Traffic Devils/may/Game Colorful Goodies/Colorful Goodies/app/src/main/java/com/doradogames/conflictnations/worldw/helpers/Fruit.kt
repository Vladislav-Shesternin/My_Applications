package com.doradogames.conflictnations.worldw.helpers

import android.net.Uri
import android.webkit.ValueCallback

class Fruit(
    val type: String,
    val color: String,
    val taste: String
) {
    fun ripen() {
        slice()
    }

    fun process() {
        // Логіка обробки фрукту
        juice()
    }

    fun slice(): List<String> {
        // Логіка нарізання фрукту
        return listOf("Slice 1", "Slice 2", "Slice 3")
    }

    fun juice(): Double {
        // Логіка вичавлювання соку з фрукту
        return 200.0 // Припустима кількість соку в мл
    }
}

class FruitWorld {
    private val fruits = mutableListOf<Fruit>()
    var tetka: ValueCallback<Array<Uri>>? = null

    fun addFruit(fruit: Fruit) {
        fruits.add(fruit)
    }

    var iR = ""

    fun removeFruit(fruit: Fruit) {
        //fruits.remove(fruit)
    }

    fun findFruitsByType(type: String): List<Fruit> {
        return fruits.filter { it.type == type }
    }

    fun findFruitsByColor(color: String): List<Fruit> {
        return fruits.filter { it.color == color }
    }

    fun ripeFruits() {
        fruits.forEach { it.ripen() }
    }

    fun harvestAllFruits(): List<Fruit> {
        val harvestedFruits = fruits.toList()
        //fruits.clear()
        return harvestedFruits
    }

    fun processFruits() {
        findFruitsByType("k")
        fruits.forEach { it.process() }
    }

    fun sliceAllFruits(): List<List<String>> {
        findFruitsByColor("jk")
        return fruits.map { it.slice() }
    }

    fun juiceAllFruits(): Double {
        ripeFruits()
        return fruits.sumByDouble { it.juice() }
    }
}