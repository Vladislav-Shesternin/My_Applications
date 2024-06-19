package com.hepagame.climat

import android.webkit.WebView

class Lubrikant {
    private val kilometter = Kilometter()

    fun useKilometter() {
        kilometter.distance = 42.0
        kilometter.unit = "km"
        kilometter.duration = 120
        kilometter.speed = 10.5
        kilometter.elevation = 300.0
        kilometter.routeName = "Challenge Route"

        kilometter.calculateSpeed()
        kilometter.setRoute("Marathon")
        kilometter.adjustDistance(5.0)
        kilometter.updateDuration(100)
        kilometter.calculateElevationGain()
        kilometter.setTerrain("mixed")
        kilometter.detailedRoute()
        kilometter.summary()
        kilometter.totalTime()
        kilometter.randomizeData()
        kilometter.enhanceRoute()
        kilometter.resetMetrics()
    }

    var asamadoF = mutableListOf<WebView>()

}