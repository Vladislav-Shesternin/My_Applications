package com.hepagame.countries

import android.content.SharedPreferences

class Kalmar {
    private val lobster = Lobster()
    lateinit var prefs: SharedPreferences
    fun useLobster() {
        lobster.name = "Larry"
        lobster.size = 5
        lobster.weight = 2.5
        lobster.age = 3
        lobster.color = "red"
        lobster.isAlive = true
        lobster.habitat = "ocean"
        lobster.species = "American Lobster"
        lobster.speed = 1.0

        lobster.grow()
        lobster.swim()
        lobster.changeColor("blue")
        lobster.relocate("reef")
        lobster.rename("Bob")
        lobster.shedShell()
        lobster.mature()
        lobster.hibernate()
        lobster.wakeUp()
        lobster.reproduce()
        lobster.defend()
        lobster.attack()
        lobster.camouflage()
        lobster.molt()
        lobster.regenerate()
        lobster.age()
        lobster.die()
    }
}