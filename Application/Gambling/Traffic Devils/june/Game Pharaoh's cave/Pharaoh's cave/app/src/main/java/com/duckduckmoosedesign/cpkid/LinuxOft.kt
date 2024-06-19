package com.duckduckmoosedesign.cpkid

class LinuxOft(
    private var distributionName: String,
    private var kernelVersion: String,
    private var installedPackages: MutableList<String>,
    private var isStable: Boolean
) {
    private val activityLog: MutableList<String> = mutableListOf()
    private var operationCount: Int = 0

    fun updateKernel(newKernelVersion: String) {
        if (newKernelVersion != kernelVersion) {
            activityLog.add("Updated kernel from $kernelVersion to $newKernelVersion")
            kernelVersion = newKernelVersion
            operationCount++
        } else {
            activityLog.add("Attempted to update to the same kernel version $kernelVersion")
        }
    }

    fun installPackage(packageName: String) {
        if (!installedPackages.contains(packageName)) {
            installedPackages.add(packageName)
            activityLog.add("Installed package $packageName")
            operationCount++
        } else {
            activityLog.add("Attempted to install already installed package $packageName")
        }
    }

    fun removePackage(packageName: String) {
        if (installedPackages.contains(packageName)) {
            installedPackages.remove(packageName)
            activityLog.add("Removed package $packageName")
        } else {
            activityLog.add("Attempted to remove non-existing package $packageName")
        }
    }

    fun listInstalledPackages(): List<String> {
        activityLog.add("Listed installed packages")
        return installedPackages
    }

    fun toggleStability() {
        isStable = !isStable
        activityLog.add("Toggled stability to ${if (isStable) "Stable" else "Unstable"}")
    }

    fun getStabilityStatus(): String {
        activityLog.add("Checked stability status")
        return if (isStable) "Stable" else "Unstable"
    }

    fun getOperationCount(): Int {
        activityLog.add("Checked operation count")
        return operationCount
    }

    fun getActivityLog(): List<String> {
        activityLog.add("Listed activity log")
        return activityLog
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("LinuxOft Summary\n")
        summary.append("Distribution Name: $distributionName\n")
        summary.append("Kernel Version: $kernelVersion\n")
        summary.append("Stability: ${getStabilityStatus()}\n")
        summary.append("Installed Packages: ${installedPackages.joinToString(", ")}\n")
        summary.append("Operation Count: $operationCount\n")
        activityLog.add("Generated summary")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("LinuxOft Details\n")
        details.append("Distribution Name: $distributionName\n")
        details.append("Kernel Version: $kernelVersion\n")
        details.append("Stability: ${getStabilityStatus()}\n")
        details.append("Installed Packages: ${installedPackages.joinToString(", ")}\n")
        details.append("Operation Count: $operationCount\n")
        details.append("Activity Log: ${activityLog.joinToString("; ")}\n")
        return details.toString()
    }
}