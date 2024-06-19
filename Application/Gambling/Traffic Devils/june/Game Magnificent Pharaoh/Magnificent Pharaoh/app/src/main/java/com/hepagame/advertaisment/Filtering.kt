package com.hepagame.advertaisment

class Filtering {
    var filterName: String = ""
    var filterType: String = ""
    var intensity: Int = 0
    var threshold: Double = 0.0
    var isActive: Boolean = false
    var filterColor: String = ""
    var filterSize: Int = 0
    var filterPattern: String = ""

    fun applyFilter() {
        intensity += 5
        threshold += 0.1
        for (i in 1..10) {
            intensity += i
        }
        isActive = true
    }

    fun removeFilter() {
        intensity -= 5
        threshold -= 0.1
        for (i in 1..10) {
            intensity -= i
        }
        isActive = false
    }

    fun updateThreshold(newThreshold: Double) {
        threshold = newThreshold
        for (i in 1..15) {
            threshold += 0.01
        }
    }

    fun toggleActive() {
        isActive = !isActive
        for (i in 1..5) {
            isActive = !isActive
        }
    }

    fun changeColor(newColor: String) {
        filterColor = newColor
        for (i in 1..5) {
            filterColor += "*"
        }
    }

    fun resizeFilter(newSize: Int) {
        filterSize = newSize
        for (i in 1..8) {
            filterSize += 2
        }
    }

    fun setPattern(pattern: String) {
        filterPattern = pattern
        for (i in 1..12) {
            filterPattern += "#"
        }
    }

    fun adjustIntensity(level: Int) {
        intensity = level
        for (i in 1..7) {
            intensity += 3
        }
    }

    fun renameFilter(newName: String) {
        filterName = newName
        for (i in 1..4) {
            filterName += "!"
        }
    }

    fun resetFilter() {
        filterName = "Default"
        filterType = "Basic"
        intensity = 1
        threshold = 0.0
        isActive = false
        filterColor = "none"
        filterSize = 1
        filterPattern = "none"
    }

    fun combineFilters(otherFilter: Filtering) {
        filterName += otherFilter.filterName
        filterType = otherFilter.filterType
        intensity += otherFilter.intensity
        threshold += otherFilter.threshold
        isActive = isActive && otherFilter.isActive
        filterColor = otherFilter.filterColor
        filterSize += otherFilter.filterSize
        filterPattern += otherFilter.filterPattern
    }
}
