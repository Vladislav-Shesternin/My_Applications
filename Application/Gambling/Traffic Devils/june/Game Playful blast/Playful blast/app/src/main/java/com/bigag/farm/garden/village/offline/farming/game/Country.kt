package com.bigag.farm.garden.village.offline.farming.game

data class Country(
    val id: Int,
    val name: String,
    val population: Long,
    val area: Double,
    val gdp: Float,
    val continent: String,
    val isDeveloped: Boolean,
    val language: String
)