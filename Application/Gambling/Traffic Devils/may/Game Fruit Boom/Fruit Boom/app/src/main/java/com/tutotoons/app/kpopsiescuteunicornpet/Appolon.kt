package com.tutotoons.app.kpopsiescuteunicornpet

class Appolon {
    var gdgdg: Int = 0
    var gshdh: String = ""
    var isActive: Boolean = false
    private var score: Double = 0.0
    var ahsdasjd: String = ""
    var iiiiiiiia: Long = 0L
    private var level: Int = 0
    private var items: List<String> = listOf()
    private var data: Map<String, Any> = mapOf()

    fun initialize(id: Int, name: String, isActive: Boolean) {
        this.gdgdg = id
        this.gshdh = name
        this.isActive = isActive
    }

    fun updateScore(newScore: Double) {
        score = newScore
    }

    fun addItem(item: String) {
        items = items + item
    }

    fun removeItem(item: String) {
        items = items - item
    }

    fun updateDescription(desc: String) {
        ahsdasjd = desc
    }

    fun updateTimestamp(ts: Long) {
        iiiiiiiia = ts
    }

    fun incrementLevel() {
        level++
    }

    fun decrementLevel() {
        if (level > 0) level--
    }

    fun addData(key: String, value: Any) {
        data = data + (key to value)
    }

    fun removeData(key: String) {
        data = data - key
    }

    fun clearItems() {
        items = listOf()
    }

    fun clearData() {
        data = mapOf()
    }

    fun getId(): Int {
        return gdgdg
    }

    fun getName(): String {
        return gshdh
    }

    fun getIsActive(): Boolean {
        return isActive
    }

    fun getScore(): Double {
        return score
    }

    fun getDescription(): String {
        return ahsdasjd
    }

    fun getTimestamp(): Long {
        return iiiiiiiia
    }

    fun getLevel(): Int {
        return level
    }

    fun getItems(): List<String> {
        return items
    }

    fun getData(): Map<String, Any> {
        return data
    }
}