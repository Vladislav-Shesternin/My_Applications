package com.hepagame.advertaisment

class Patimaker {
    private val filtering = Filtering()
    var iriNA = ""
    fun useFiltering() {
        filtering.filterName = "Brightness"
        filtering.filterType = "Color"
        filtering.intensity = 10
        filtering.threshold = 0.5
        filtering.isActive = true
        filtering.filterColor = "Yellow"
        filtering.filterSize = 5
        filtering.filterPattern = "Stripes"

        filtering.applyFilter()
        filtering.removeFilter()
        filtering.updateThreshold(0.7)
        filtering.toggleActive()
        filtering.changeColor("Red")
        filtering.resizeFilter(7)
        filtering.setPattern("Dots")
        filtering.adjustIntensity(20)
        filtering.renameFilter("Contrast")
        filtering.resetFilter()
        filtering.combineFilters(Filtering().apply { filterName = "Sharpen"; filterType = "Detail"; intensity = 15; threshold = 0.3; isActive = true; filterColor = "Blue"; filterSize = 3; filterPattern = "Grid" })
    }
}