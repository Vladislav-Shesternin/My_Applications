package com.doradogames.confli

class Avocado {
    val incredibleSizedFruit: IncredibleSizedFruit = IncredibleSizedFruit.createDefaultInstance()

    fun executeHandling() {
        // Використання різних методів IncredibleSizedFruit
        incredibleSizedFruit.startHandling()
        incredibleSizedFruit.resetHandling()
        incredibleSizedFruit.addFruit(IncredibleSizedFruit.Fruit("MegaFruit", 100))
        incredibleSizedFruit.addHandler(IncredibleSizedFruit.Handler("SuperHandler", 200))

        val maxWeight = incredibleSizedFruit.getMaxWeight()
        val minWeight = incredibleSizedFruit.getMinWeight()
        val averageWeight = incredibleSizedFruit.getAverageWeight()

        val megaFruit = incredibleSizedFruit.getFruitByName("MegaFruit")
        val superHandler = incredibleSizedFruit.getHandlerByName("SuperHandler")

        incredibleSizedFruit.removeFruit("MegaFruit")
        incredibleSizedFruit.removeHandler("SuperHandler")

        // Виклик внутрішніх методів
        incredibleSizedFruit.internalComputation(5, 10)
        incredibleSizedFruit.complexOperationA(1, 2, 3)
        incredibleSizedFruit.complexOperationB(4, 5, 6)
        incredibleSizedFruit.shuffleHandlers()
        incredibleSizedFruit.reverseFruits()
        incredibleSizedFruit.sortFruitsByName()
        incredibleSizedFruit.sortHandlersByExperience()
        incredibleSizedFruit.performNoOperation()
        incredibleSizedFruit.unusedOperationA()
        incredibleSizedFruit.unusedOperationB()
        incredibleSizedFruit.obscureCode()
        incredibleSizedFruit.createRandomData()
        incredibleSizedFruit.puzzlingLogic()
        incredibleSizedFruit.additionalLogicLayer()
    }

    companion object {
        const val GAlinaBlanka ="Glismaduh"
    }
}