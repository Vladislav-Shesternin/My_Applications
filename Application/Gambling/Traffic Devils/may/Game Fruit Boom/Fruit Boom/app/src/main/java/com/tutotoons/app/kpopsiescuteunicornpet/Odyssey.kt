package com.tutotoons.app.kpopsiescuteunicornpet

import com.android.installreferrer.api.InstallReferrerClient

class Odyssey {
    val hermes = Hermes()

    fun useHermes() {
        hermes.initialize(1, "Hermes", 50.0, true)
        hermes.sendMessage("Expedition Message")
        hermes.calculateTrajectory(Pair(0.0, 0.0), Pair(100.0, 100.0))
        hermes.processMessages()
        hermes.complexCalculation(1000)
        hermes.updateCoordinates(34.052235, -118.243683)

         hermes.isActive()
        hermes.getCoordinates()

    }

    lateinit var installReferrerClient: InstallReferrerClient
}