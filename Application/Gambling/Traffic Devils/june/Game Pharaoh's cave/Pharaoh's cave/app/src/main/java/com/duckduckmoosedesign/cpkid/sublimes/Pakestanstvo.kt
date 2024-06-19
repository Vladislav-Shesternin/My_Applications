package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.Embargo

class Pakestanstvo {
    private val embargo = Embargo(
        name = "Trade Embargo",
        date = "2024-06-05",
        affectedCountries = mutableListOf("CountryA", "CountryB"),
        reasons = mutableListOf("Violation of agreements", "Human rights issues"),
        isActive = false
    )

    fun execute() {
        println("Executing Pakestanstvo actions...\n")

        embargo.activate()
        embargo.addAffectedCountry("CountryC")
        embargo.addReason("Political instability")
        println(embargo.summary())

        embargo.deactivate()
        embargo.addAffectedCountry("CountryD")
        embargo.removeAffectedCountry("CountryA")
        embargo.addReason("Economic sanctions")
        embargo.removeReason("Violation of agreements")

        println(embargo.summary())
        println("Sanctions count: ${embargo.getSanctionsCount()}")
        println("Affected countries: ${embargo.listAffectedCountries()}")
        println("Reasons: ${embargo.listReasons()}")
        println("History: ${embargo.getHistory()}")
        println(embargo.toString())
    }
}