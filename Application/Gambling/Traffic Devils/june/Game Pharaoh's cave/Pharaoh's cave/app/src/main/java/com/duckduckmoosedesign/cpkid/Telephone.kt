package com.duckduckmoosedesign.cpkid

class Telephone(
    private var brand: String,
    private var model: String,
    private var osVersion: String,
    private var batteryLevel: Int,
    private var isOn: Boolean
) {
    private val callHistory: MutableList<String> = mutableListOf()
    private var appCount: Int = 0

    fun powerOn() {
        if (!isOn) {
            isOn = true
            callHistory.add("Powered on $model by $brand")
        } else {
            callHistory.add("Attempted to power on already on $model by $brand")
        }
    }

    fun powerOff() {
        if (isOn) {
            isOn = false
            callHistory.add("Powered off $model by $brand")
        } else {
            callHistory.add("Attempted to power off already off $model by $brand")
        }
    }

    fun makeCall(contact: String) {
        if (isOn) {
            callHistory.add("Called $contact from $model by $brand")
            batteryLevel -= 5
        } else {
            callHistory.add("Attempted to call $contact while phone is off")
        }
    }

    fun installApp(appName: String) {
        appCount++
        callHistory.add("Installed app $appName on $model by $brand")
    }

    fun uninstallApp(appName: String) {
        if (appCount > 0) {
            appCount--
            callHistory.add("Uninstalled app $appName from $model by $brand")
        } else {
            callHistory.add("Attempted to uninstall app $appName with no apps installed")
        }
    }

    fun listCallHistory(): List<String> {
        callHistory.add("Listed call history of $model by $brand")
        return callHistory
    }

    fun checkBatteryLevel(): Int {
        callHistory.add("Checked battery level of $model by $brand")
        return batteryLevel
    }

    fun updateOS(newVersion: String) {
        if (newVersion != osVersion) {
            osVersion = newVersion
            callHistory.add("Updated OS to $newVersion on $model by $brand")
        } else {
            callHistory.add("Attempted to update to the same OS version $osVersion on $model by $brand")
        }
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("Telephone Summary\n")
        summary.append("Brand: $brand\n")
        summary.append("Model: $model\n")
        summary.append("OS Version: $osVersion\n")
        summary.append("Battery Level: $batteryLevel%\n")
        summary.append("Power Status: ${if (isOn) "On" else "Off"}\n")
        summary.append("Installed Apps Count: $appCount\n")
        callHistory.add("Generated summary for $model by $brand")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("Telephone Details\n")
        details.append("Brand: $brand\n")
        details.append("Model: $model\n")
        details.append("OS Version: $osVersion\n")
        details.append("Battery Level: $batteryLevel%\n")
        details.append("Power Status: ${if (isOn) "On" else "Off"}\n")
        details.append("Installed Apps Count: $appCount\n")
        details.append("Call History: ${callHistory.joinToString("; ")}\n")
        return details.toString()
    }
}