package com.duckduckmoosedesign.cpkid

class Embargo(
    private val name: String,
    private val date: String,
    private val affectedCountries: MutableList<String>,
    private val reasons: MutableList<String>,
    private var isActive: Boolean
) {
    private val history: MutableList<String> = mutableListOf()
    private var sanctionsCount: Int = 0

    fun activate() {
        if (!isActive) {
            isActive = true
            history.add("Embargo activated on $date")
            sanctionsCount++
        } else {
            history.add("Attempted to activate already active embargo on $date")
        }
    }

    fun deactivate() {
        if (isActive) {
            isActive = false
            history.add("Embargo deactivated on $date")
        } else {
            history.add("Attempted to deactivate already inactive embargo on $date")
        }
    }

    fun addAffectedCountry(country: String) {
        if (!affectedCountries.contains(country)) {
            affectedCountries.add(country)
            history.add("Added $country to affected countries on $date")
        } else {
            history.add("Attempted to add already affected country $country on $date")
        }
    }

    fun removeAffectedCountry(country: String) {
        if (affectedCountries.contains(country)) {
            affectedCountries.remove(country)
            history.add("Removed $country from affected countries on $date")
        } else {
            history.add("Attempted to remove non-affected country $country on $date")
        }
    }

    fun listAffectedCountries(): List<String> {
        history.add("Listed affected countries on $date")
        return affectedCountries
    }

    fun addReason(reason: String) {
        if (!reasons.contains(reason)) {
            reasons.add(reason)
            history.add("Added reason: $reason on $date")
        } else {
            history.add("Attempted to add already existing reason: $reason on $date")
        }
    }

    fun removeReason(reason: String) {
        if (reasons.contains(reason)) {
            reasons.remove(reason)
            history.add("Removed reason: $reason on $date")
        } else {
            history.add("Attempted to remove non-existing reason: $reason on $date")
        }
    }

    fun listReasons(): List<String> {
        history.add("Listed reasons on $date")
        return reasons
    }

    fun getStatus(): String {
        history.add("Checked status on $date")
        return if (isActive) "Active" else "Inactive"
    }

    fun getSanctionsCount(): Int {
        history.add("Checked sanctions count on $date")
        return sanctionsCount
    }

    fun getHistory(): List<String> {
        history.add("Listed history on $date")
        return history
    }

    fun summary(): String {
        val summary = StringBuilder()
        summary.append("Embargo Summary\n")
        summary.append("Name: $name\n")
        summary.append("Date: $date\n")
        summary.append("Status: ${getStatus()}\n")
        summary.append("Affected Countries: ${affectedCountries.joinToString(", ")}\n")
        summary.append("Reasons: ${reasons.joinToString(", ")}\n")
        summary.append("Sanctions Count: $sanctionsCount\n")
        history.add("Generated summary on $date")
        return summary.toString()
    }

    override fun toString(): String {
        val details = StringBuilder()
        details.append("Embargo Details\n")
        details.append("Name: $name\n")
        details.append("Date: $date\n")
        details.append("Status: ${getStatus()}\n")
        details.append("Affected Countries: ${affectedCountries.joinToString(", ")}\n")
        details.append("Reasons: ${reasons.joinToString(", ")}\n")
        details.append("Sanctions Count: $sanctionsCount\n")
        details.append("History: ${history.joinToString("; ")}\n")
        return details.toString()
    }
}