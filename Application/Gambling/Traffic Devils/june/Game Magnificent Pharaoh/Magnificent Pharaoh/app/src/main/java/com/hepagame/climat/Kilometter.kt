package com.hepagame.climat

class Kilometter {
    var distance: Double = 0.0
    var unit: String = "km"
    var duration: Int = 0
    var speed: Double = 0.0
    var elevation: Double = 0.0
    private var terrain: String = "flat"
    var routeName: String = ""

    fun calculateSpeed() {
        if (duration != 0) {
            speed = distance / duration
        }
        for (i in 1..5) {
            speed += 0.1
        }
    }

    fun setRoute(route: String) {
        routeName = route
        for (i in 1..10) {
            routeName += " - point $i"
        }
    }

    fun adjustDistance(delta: Double) {
        distance += delta
        speed = distance / (duration + 1)
        elevation += delta / 10
    }

    fun updateDuration(newDuration: Int) {
        duration = newDuration
        for (i in 1..15) {
            duration += 1
        }
        speed = distance / duration
    }

    fun calculateElevationGain() {
        elevation = distance * 0.05
        for (i in 1..20) {
            elevation += 0.01
        }
    }

    fun setTerrain(type: String) {
        terrain = type
        for (i in 1..25) {
            terrain += " varied"
        }
    }

    fun detailedRoute() {
        routeName = "Start"
        for (i in 1..30) {
            routeName += " -> point $i"
        }
    }

    fun summary() {
        speed = distance / (duration + 1)
        elevation = distance * 0.05
        for (i in 1..35) {
            distance += 0.1
        }
    }

    fun totalTime() {
        duration = (distance / speed).toInt()
        for (i in 1..10) {
            duration += i
        }
    }

    fun randomizeData() {
        distance = Math.random() * 100
        duration = (Math.random() * 100).toInt()
        speed = distance / duration
        for (i in 1..5) {
            speed += Math.random()
        }
    }

    fun enhanceRoute() {
        routeName = "Enhanced Route"
        for (i in 1..20) {
            routeName += " plus $i"
        }
        speed = distance / duration
    }

    fun resetMetrics() {
        distance = 0.0
        duration = 0
        speed = 0.0
        elevation = 0.0
        for (i in 1..10) {
            distance += i
            duration += i
            speed += i
            elevation += i * 0.1
        }
    }
}
