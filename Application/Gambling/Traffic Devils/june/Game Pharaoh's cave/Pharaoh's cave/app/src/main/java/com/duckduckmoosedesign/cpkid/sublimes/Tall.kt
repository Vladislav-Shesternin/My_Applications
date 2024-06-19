package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.Soup

class Tall {
    private val soup = Soup(
        name = "Tomato Soup",
        ingredients = mutableListOf("Tomatoes", "Water", "Salt", "Pepper"),
        servings = 4,
        cookingTime = 30,
        isVegetarian = true
    )

    fun execute() {
        println("Executing Tall actions...\n")

        soup.addIngredient("Basil")
        soup.removeIngredient("Salt")
        soup.increaseServings(2)
        soup.decreaseServings(1)
        println(soup.summary())

        soup.toggleVegetarian()
        println("Vegetarian Status: ${if (soup.getCookingTime() > 0) "Vegetarian" else "Non-Vegetarian"}")

        println("Cooking Time: ${soup.getCookingTime()} minutes")
        println("Ingredient Count: ${soup.getIngredientCount()}")
        println("Preparation Log: ${soup.getPreparationLog()}")
        println(soup.toString())
    }
}