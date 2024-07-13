package com.bigag.farm.garden.village.offline.farming.game

data class Oceanarium(
    val id: Long,
    val name: String,
    val aquariumType: String,
    val waterVolumeLiters: Long,
    val numberOfFish: Int,
    val hasAquascaping: Boolean,
    val temperatureCelsius: Float,
    val pHLevel: Double,
    val filtrationType: String,
    val isMaintained: Boolean
)