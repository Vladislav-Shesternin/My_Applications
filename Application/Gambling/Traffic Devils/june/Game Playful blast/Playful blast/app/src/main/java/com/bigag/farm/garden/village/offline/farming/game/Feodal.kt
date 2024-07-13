package com.bigag.farm.garden.village.offline.farming.game

data class Feodal(
    val id: Long,
    val name: String,
    val age: Int,
    val title: String,
    val landArea: Double,
    val wealth: Double,
    val isNoble: Boolean,
    val hasCastle: Boolean,
    val numberOfVassals: Int,
    val kingdom: String
)