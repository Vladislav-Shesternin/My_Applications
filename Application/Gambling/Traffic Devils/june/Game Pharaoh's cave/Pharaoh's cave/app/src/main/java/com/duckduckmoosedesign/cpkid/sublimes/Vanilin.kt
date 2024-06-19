package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.Boroshno

class Vanilin {
    private val boroshno = Boroshno(
        type = "Wheat Flour",
        origin = "Ukraine",
        quantity = 50.0,
        expirationDate = "2025-01-01",
        isGlutenFree = false
    )

    fun execute() {
        println("Executing Vanilin actions...\n")

        boroshno.increaseQuantity(10.0)
        boroshno.decreaseQuantity(5.0)
        println("Quality: ${boroshno.checkQuality()}")

        boroshno.changeType("Rice Flour")
        boroshno.changeOrigin("India")
        println(boroshno.summary())

        println("Is expired: ${boroshno.isExpired("2024-06-05")}")

        println("Usage count: ${boroshno.getUsageCount()}")
        println("Inventory history: ${boroshno.listHistory()}")
        println(boroshno.toString())
    }
}