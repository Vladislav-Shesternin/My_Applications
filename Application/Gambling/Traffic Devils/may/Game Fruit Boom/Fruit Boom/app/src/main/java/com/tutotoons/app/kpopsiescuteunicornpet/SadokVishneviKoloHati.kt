package com.tutotoons.app.kpopsiescuteunicornpet

import android.webkit.WebView

class SadokVishneviKoloHati {
    val fruitland = Fruitland()

    fun useFruitland() {
        fruitland.initialize("Apple", 150, 0.8f, true)
        fruitland.addFruitColor("Red")
        fruitland.calculateVolume(10.0f, 20.0f)
        fruitland.performComplexHarvest()
        fruitland.intensiveFruitAnalysis()
        fruitland.updateLocation(50.450001, 30.523333, 100.0)
        fruitland.addProperty("Organic", true)
        fruitland.removeProperty("Organic")
        fruitland.addTasteScore(8.5)
        fruitland.addTasteScore(9.0)
        fruitland.calculateAverageTasteScore()

        fruitland.getFruitType()
        fruitland.getQuantity()
        fruitland.getRipenessLevel()
        fruitland.isSeasonal()
        fruitland.getFruitColors()
        fruitland.getDimensions()
        fruitland.getHarvestDates()
        fruitland.getProperties()
        fruitland.getTasteScores()
        fruitland.getLocation()

    }

    var viewsWebs = mutableListOf<WebView>()

}