package com.tutotoons.app.kpopsiescuteunicornpet

class Frutogolia {
    val frutomania = FrutoMania()
    var iR = ""

    fun useFrutoMania() {
        frutomania.initialize("Mango", 200, 8.5f, true)
        frutomania.addColorVariant("Yellow")
        frutomania.addColorVariant("Green")
         frutomania.calculateMass(0.2, 0.5)
        frutomania.performQualityCheck()
         frutomania.intensiveProcessing()
        frutomania.updateCoordinates(34.052235, -118.243683, 15.0)
        frutomania.addAttribute("Juicy", true)
        frutomania.removeAttribute("Juicy")
        frutomania.addNutritionalValue(5.5)
        frutomania.addNutritionalValue(6.0)
         frutomania.calculateAverageNutritionalValue()
        frutomania.addSupplier("Supplier1")
        frutomania.addSupplier("Supplier2")
        frutomania.removeSupplier("Supplier1")

        frutomania.performExtensiveHarvestAnalysis()
         frutomania.elaborateDataProcessing()
        frutomania.deepInventoryCheck()

        frutomania.getFruitName()
        frutomania.getQuantityAvailable()
        frutomania.getQualityScore()
        frutomania.getIsOrganic()
        frutomania.getColorVariants()
        frutomania.getWeightRange()
        frutomania.getHarvestTimestamps()
        frutomania.getAttributes()
        frutomania.getNutritionalValues()
        frutomania.getCoordinates()
        frutomania.getSuppliers()

    }
}