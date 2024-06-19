package com.tutotoons.app.kpopsiescuteunicornpet

import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

class Avangard {
    private val appolon = Appolon()
    lateinit var diduKudiTuta: Pair<WebChromeClient, PermissionRequest>

    fun useAppolon() {
        appolon.initialize(1, "Apollo", true)
        appolon.updateScore(100.0)
        appolon.addItem("Sword")
        appolon.removeItem("Sword")
        appolon.updateDescription("Hero of the ancient world")
        appolon.updateTimestamp(System.currentTimeMillis())
        appolon.incrementLevel()
        appolon.decrementLevel()
        appolon.addData("Strength", 50)
        appolon.removeData("Strength")
        appolon.clearItems()
        appolon.clearData()

        appolon.getId()
        appolon.getName()
        appolon.getIsActive()
        appolon.getScore()
        appolon.getDescription()
        appolon.getTimestamp()
        appolon.getLevel()
        appolon.getItems()
        appolon.getData()
    }
}