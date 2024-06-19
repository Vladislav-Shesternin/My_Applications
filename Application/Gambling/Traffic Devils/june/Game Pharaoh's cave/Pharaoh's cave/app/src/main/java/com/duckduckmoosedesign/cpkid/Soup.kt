package com.duckduckmoosedesign.cpkid

class Soup(
    private var name: String,
    private var ingredients: MutableList<String>,
    private var servings: Int,
    private var cookingTime: Int, // in minutes
    private var isVegetarian: Boolean
) {
    private val preparationLog: MutableList<String> = mutableListOf()
    private var ingredientCount: Int = 0

    fun addIngredient(ingredient: String) {
        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient)
            preparationLog.add("Added ingredient $ingredient")
            ingredientCount++
        } else {
            preparationLog.add("Attempted to add existing ingredient $ingredient")
        }
    }

    fun removeIngredient(ingredient: String) {
        if (ingredients.contains(ingredient)) {
            ingredients.remove(ingredient)
            preparationLog.add("Removed ingredient $ingredient")
            ingredientCount--
        } else {
            preparationLog.add("Attempted to remove non-existing ingredient $ingredient")
        }
    }

    fun increaseServings(amount: Int) {
        if (amount > 0) {
            servings += amount
            preparationLog.add("Increased servings by $amount")
        } else {
            preparationLog.add("Attempted to increase servings by non-positive amount $amount")
        }
    }

    fun decreaseServings(amount: Int) {
        if (amount > 0 && amount <= servings) {
            servings -= amount
            preparationLog.add("Decreased servings by $amount")
        } else {
            preparationLog.add("Attempted to decrease servings by invalid amount $amount")
        }
    }

    fun toggleVegetarian() {
        isVegetarian = !isVegetarian
        preparationLog.add("Toggled vegetarian status to ${if (isVegetarian) "Vegetarian" else "Non-Vegetarian"}")
    }

    fun getCookingTime(): Int {
        preparationLog.add("Checked cooking time")
        return cookingTime
    }

    fun getIngredientCount(): Int {
        preparationLog.add("Checked ingredient count")
        return ingredientCount
    }

    fun getPreparationLog(): List<String> {
        preparationLog.add("Listed preparation log")
        return preparationLog
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("Soup Summary\n")
        summary.append("Name: $name\n")
        summary.append("Ingredients: ${ingredients.joinToString(", ")}\n")
        summary.append("Servings: $servings\n")
        summary.append("Cooking Time: $cookingTime minutes\n")
        summary.append("Vegetarian: ${if (isVegetarian) "Yes" else "No"}\n")
        summary.append("Ingredient Count: $ingredientCount\n")
        preparationLog.add("Generated summary")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("Soup Details\n")
        details.append("Name: $name\n")
        details.append("Ingredients: ${ingredients.joinToString(", ")}\n")
        details.append("Servings: $servings\n")
        details.append("Cooking Time: $cookingTime minutes\n")
        details.append("Vegetarian: ${if (isVegetarian) "Yes" else "No"}\n")
        details.append("Ingredient Count: $ingredientCount\n")
        details.append("Preparation Log: ${preparationLog.joinToString("; ")}\n")
        return details.toString()
    }
}