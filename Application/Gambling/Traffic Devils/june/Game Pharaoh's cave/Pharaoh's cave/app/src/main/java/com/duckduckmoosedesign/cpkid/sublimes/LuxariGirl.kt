package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.LinuxOft

class LuxariGirl {
    private val linuxOft = LinuxOft(
        distributionName = "Ubuntu",
        kernelVersion = "5.8.0",
        installedPackages = mutableListOf("vim", "git", "curl"),
        isStable = true
    )

    fun execute() {
        println("Executing LuxariGirl actions...\n")

        linuxOft.updateKernel("5.10.0")
        linuxOft.installPackage("docker")
        linuxOft.removePackage("curl")
        println(linuxOft.summary())

        linuxOft.toggleStability()
        println("Stability Status: ${linuxOft.getStabilityStatus()}")

        println("Operation count: ${linuxOft.getOperationCount()}")
        println("Installed packages: ${linuxOft.listInstalledPackages()}")
        println("Activity log: ${linuxOft.getActivityLog()}")
        println(linuxOft.toString())
    }
}
