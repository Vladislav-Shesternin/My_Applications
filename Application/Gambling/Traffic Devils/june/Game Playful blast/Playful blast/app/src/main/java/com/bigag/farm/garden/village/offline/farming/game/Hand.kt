package com.bigag.farm.garden.village.offline.farming.game

data class Hand(
    val id: Int,
    val name: String,
    val isLeftHanded: Boolean,
    val length: Double,
    val fingers: Int,
    val strength: Float,
    val owner: String
)