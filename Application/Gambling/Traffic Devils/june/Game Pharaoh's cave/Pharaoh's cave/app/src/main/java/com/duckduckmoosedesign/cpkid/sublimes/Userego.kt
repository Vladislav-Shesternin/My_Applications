package com.duckduckmoosedesign.cpkid.sublimes

import com.duckduckmoosedesign.cpkid.Telephone

class Userego {
    private val telephone = Telephone(
        brand = "Nokia",
        model = "3310",
        osVersion = "1.0",
        batteryLevel = 100,
        isOn = false
    )

    fun execute() {
        println("Executing Userego actions...\n")

        telephone.powerOn()
        telephone.makeCall("Alice")
        telephone.installApp("Snake")
        telephone.installApp("Calculator")
        telephone.uninstallApp("Calculator")
        println(telephone.summary())

        telephone.updateOS("1.1")
        println("Battery Level: ${telephone.checkBatteryLevel()}%")

        telephone.powerOff()
        println("Power Status: ${if (telephone.checkBatteryLevel() > 0) "Has Power" else "No Power"}")

        println("Call History: ${telephone.listCallHistory()}")
        println(telephone.toString())
    }
}
