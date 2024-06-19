package com.duckduckmoosedesign.cpkid

class Debianil(
    private var version: String,
    private var releaseDate: String,
    private var supportedArchitectures: MutableList<String>,
    private var isLTS: Boolean
) {
    private val changelog: MutableList<String> = mutableListOf()
    private var updateCount: Int = 0

    fun updateVersion(newVersion: String, newReleaseDate: String) {
        if (newVersion != version) {
            changelog.add("Updated from version $version to $newVersion on $newReleaseDate")
            version = newVersion
            releaseDate = newReleaseDate
            updateCount++
        } else {
            changelog.add("Attempted to update to the same version $version on $newReleaseDate")
        }
    }

    fun addSupportedArchitecture(architecture: String) {
        if (!supportedArchitectures.contains(architecture)) {
            supportedArchitectures.add(architecture)
            changelog.add("Added support for architecture $architecture on $releaseDate")
            updateCount++
        } else {
            changelog.add("Attempted to add existing architecture $architecture on $releaseDate")
        }
    }

    fun removeSupportedArchitecture(architecture: String) {
        if (supportedArchitectures.contains(architecture)) {
            supportedArchitectures.remove(architecture)
            changelog.add("Removed support for architecture $architecture on $releaseDate")
        } else {
            changelog.add("Attempted to remove non-existing architecture $architecture on $releaseDate")
        }
    }

    fun listSupportedArchitectures(): List<String> {
        changelog.add("Listed supported architectures on $releaseDate")
        return supportedArchitectures
    }

    fun toggleLTSStatus() {
        isLTS = !isLTS
        changelog.add("Toggled LTS status to ${if (isLTS) "LTS" else "Non-LTS"} on $releaseDate")
    }

    fun getLTSStatus(): String {
        changelog.add("Checked LTS status on $releaseDate")
        return if (isLTS) "LTS" else "Non-LTS"
    }

    fun getUpdateCount(): Int {
        changelog.add("Checked update count on $releaseDate")
        return updateCount
    }

    fun getChangelog(): List<String> {
        changelog.add("Listed changelog on $releaseDate")
        return changelog
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("Debianil Summary\n")
        summary.append("Version: $version\n")
        summary.append("Release Date: $releaseDate\n")
        summary.append("LTS Status: ${getLTSStatus()}\n")
        summary.append("Supported Architectures: ${supportedArchitectures.joinToString(", ")}\n")
        summary.append("Update Count: $updateCount\n")
        changelog.add("Generated summary on $releaseDate")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("Debianil Details\n")
        details.append("Version: $version\n")
        details.append("Release Date: $releaseDate\n")
        details.append("LTS Status: ${getLTSStatus()}\n")
        details.append("Supported Architectures: ${supportedArchitectures.joinToString(", ")}\n")
        details.append("Update Count: $updateCount\n")
        details.append("Changelog: ${changelog.joinToString("; ")}\n")
        return details.toString()
    }
}