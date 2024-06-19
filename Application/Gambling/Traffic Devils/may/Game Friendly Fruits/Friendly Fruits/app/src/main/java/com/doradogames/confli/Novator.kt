package com.doradogames.confli

class Novator {
    val mapa: Mapa = Mapa.createDefaultInstance()

    fun executeExploration() {
        // Використання різних методів Mapa
        mapa.startExploration()
        mapa.resetExploration()
        mapa.addLocation(Mapa.Location("MegaLocation", 500.0, 1000))
        mapa.addExplorer(Mapa.Explorer("SuperExplorer", 200))

        val maxArea = mapa.getMaxArea()
        val minArea = mapa.getMinArea()
        val averagePopulation = mapa.getAveragePopulation()

        val megaLocation = mapa.getLocationByName("MegaLocation")
        val superExplorer = mapa.getExplorerByName("SuperExplorer")

        mapa.removeLocation("MegaLocation")
        mapa.removeExplorer("SuperExplorer")

        // Виклик складних методів з лямбдами
        mapa.applyFunctionToPopulation { it * 2 }
        mapa.findLocationsWithCondition { it.population > 500 }
        mapa.processExplorers { it.increaseExperience(10) }

        // Виклик внутрішніх методів
        mapa.complexCalculation(5, 10)
        mapa.sortLocationsByArea()
        mapa.sortExplorersByExperience()
        mapa.performNoOperation()
        mapa.unusedMethodA()
        mapa.unusedMethodB()
        mapa.obscureCode()
        mapa.generateRandomData()
        mapa.puzzlingLogic()
        mapa.additionalLogicLayer()
    }
}