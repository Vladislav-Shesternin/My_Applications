package com.duckduckmoosedesign.cpkid

class Boroshno(
    private var type: String,
    private var origin: String,
    private var quantity: Double,
    private val expirationDate: String,
    private val isGlutenFree: Boolean
) {
    private val inventoryHistory: MutableList<String> = mutableListOf()
    private var usageCount: Int = 0

    fun increaseQuantity(amount: Double) {
        if (amount > 0) {
            quantity += amount
            inventoryHistory.add("Increased quantity by $amount on $expirationDate")
            usageCount++
        } else {
            inventoryHistory.add("Attempted to increase quantity by non-positive amount on $expirationDate")
        }
    }

    fun decreaseQuantity(amount: Double) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount
            inventoryHistory.add("Decreased quantity by $amount on $expirationDate")
            usageCount++
        } else {
            inventoryHistory.add("Attempted to decrease quantity by invalid amount on $expirationDate")
        }
    }

    fun checkQuality(): String {
        val quality = if (isGlutenFree) "Gluten-free" else "Contains gluten"
        inventoryHistory.add("Checked quality on $expirationDate")
        return quality
    }

    fun changeType(newType: String) {
        if (newType != type) {
            inventoryHistory.add("Changed type from $type to $newType on $expirationDate")
            type = newType
        } else {
            inventoryHistory.add("Attempted to change type to the same type on $expirationDate")
        }
    }

    fun changeOrigin(newOrigin: String) {
        if (newOrigin != origin) {
            inventoryHistory.add("Changed origin from $origin to $newOrigin on $expirationDate")
            origin = newOrigin
        } else {
            inventoryHistory.add("Attempted to change origin to the same origin on $expirationDate")
        }
    }

    fun isExpired(currentDate: String): Boolean {
        val expired = currentDate > expirationDate
        inventoryHistory.add("Checked expiration status on $currentDate")
        return expired
    }

    fun listHistory(): List<String> {
        inventoryHistory.add("Listed history on $expirationDate")
        return inventoryHistory
    }

    fun getUsageCount(): Int {
        inventoryHistory.add("Checked usage count on $expirationDate")
        return usageCount
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("Boroshno Summary\n")
        summary.append("Type: $type\n")
        summary.append("Origin: $origin\n")
        summary.append("Quantity: $quantity\n")
        summary.append("Expiration Date: $expirationDate\n")
        summary.append("Gluten-Free: ${if (isGlutenFree) "Yes" else "No"}\n")
        summary.append("Usage Count: $usageCount\n")
        inventoryHistory.add("Generated summary on $expirationDate")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("Boroshno Details\n")
        details.append("Type: $type\n")
        details.append("Origin: $origin\n")
        details.append("Quantity: $quantity\n")
        details.append("Expiration Date: $expirationDate\n")
        details.append("Gluten-Free: ${if (isGlutenFree) "Yes" else "No"}\n")
        details.append("Usage Count: $usageCount\n")
        details.append("History: ${inventoryHistory.joinToString("; ")}\n")
        return details.toString()
    }
}