package com.bigag.farm.garden.village.offline.farming.game

data class Right(
    val id: Int,
    val name: String,
    val value: Double,
    val quantity: Int,
    val isActive: Boolean,
    val description: String,
    val length: Long,
    val width: Float,
    val height: Double,
    val weight: Float
)