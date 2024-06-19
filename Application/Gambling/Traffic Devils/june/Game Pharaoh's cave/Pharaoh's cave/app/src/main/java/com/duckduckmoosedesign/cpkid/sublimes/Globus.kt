package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.Debianil

class Globus {
    private val debianil = Debianil(
        version = "10.0",
        releaseDate = "2024-01-01",
        supportedArchitectures = mutableListOf("x86_64", "arm64"),
        isLTS = true
    )

    fun execute() {
        println("Executing Globus actions...\n")

        debianil.updateVersion("11.0", "2024-06-05")
        debianil.addSupportedArchitecture("ppc64le")
        debianil.removeSupportedArchitecture("arm64")
        println(debianil.summary())

        debianil.toggleLTSStatus()
        println("LTS Status: ${debianil.getLTSStatus()}")

        println("Update count: ${debianil.getUpdateCount()}")
        println("Supported architectures: ${debianil.listSupportedArchitectures()}")
        println("Changelog: ${debianil.getChangelog()}")
        println(debianil.toString())
    }
}