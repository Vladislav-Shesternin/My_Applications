package com.tutotoons.app.kpopsiescuteunicornpet

import android.content.SharedPreferences

class Brazilianna {
    private val deamoralius = Deamoralius()

    fun useDeamoralius() {
        deamoralius.initialize("Hero123", 80, true, 1500L)
        deamoralius.addInventoryItem("Magic Wand")
        deamoralius.calculatePath(Triple(0.0, 0.0, 0.0), Triple(100.0, 100.0, 100.0))
        deamoralius.performIntensiveTask()
        deamoralius.complexComputation(100)
        deamoralius.updatePosition(40.712776, -74.005974, 10.0)
        deamoralius.addAbility("Invisibility", 5)
        deamoralius.removeAbility("Invisibility")
        deamoralius.enhanceAbilities(2)

       deamoralius.getIdentifier()
       deamoralius.getEnergyLevel()
       deamoralius.isHealthy()
       deamoralius.getExperiencePoints()
       deamoralius.getInventory()
       deamoralius.getPosition()
       deamoralius.getAbilities()

    }

    lateinit var magistratura: SharedPreferences
}