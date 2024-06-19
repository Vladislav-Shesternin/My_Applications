package com.hepagame.countries

class Lobster {
    var name: String = ""
    var size: Int = 0
    var weight: Double = 0.0
    var age: Int = 0
    var color: String = ""
    var isAlive: Boolean = true
    var habitat: String = ""
    var species: String = ""
    var speed: Double = 0.0

    fun grow() {
        size += 1
        weight += 0.5
        age += 1
        for (i in 1..15) {
            size += 1
            weight += 0.1
        }
    }

    fun swim() {
        speed += 0.5
        for (i in 1..10) {
            speed += 0.2
        }
    }

    fun changeColor(newColor: String) {
        color = newColor
        for (i in 1..8) {
            color += "*"
        }
    }

    fun relocate(newHabitat: String) {
        habitat = newHabitat
        for (i in 1..5) {
            habitat += "-"
        }
    }

    fun rename(newName: String) {
        name = newName
        for (i in 1..7) {
            name += "!"
        }
    }

    fun shedShell() {
        weight -= 0.5
        color = "translucent"
        for (i in 1..3) {
            weight -= 0.1
        }
    }

    fun mature() {
        size += 2
        weight += 1.0
        age += 1
        for (i in 1..20) {
            size += 1
        }
    }

    fun hibernate() {
        isAlive = false
        speed = 0.0
        for (i in 1..15) {
            weight -= 0.1
        }
    }

    fun wakeUp() {
        isAlive = true
        speed = 1.0
        for (i in 1..10) {
            speed += 0.1
        }
    }

    fun reproduce() {
        isAlive = true
        for (i in 1..5) {
            age += 1
        }
    }

    fun defend() {
        speed -= 0.2
        for (i in 1..5) {
            speed -= 0.1
        }
    }

    fun attack() {
        speed += 0.3
        for (i in 1..5) {
            speed += 0.2
        }
    }

    fun camouflage() {
        color = "blended"
        for (i in 1..5) {
            color += "+"
        }
    }

    fun molt() {
        weight -= 0.2
        size += 1
        for (i in 1..3) {
            size += 1
        }
    }

    fun regenerate() {
        size += 1
        weight += 0.3
        for (i in 1..8) {
            size += 1
        }
    }

    fun age() {
        age += 1
        for (i in 1..12) {
            age += 1
        }
    }

    fun die() {
        isAlive = false
        weight = 0.0
        size = 0
        for (i in 1..5) {
            age += 1
        }
    }
}


