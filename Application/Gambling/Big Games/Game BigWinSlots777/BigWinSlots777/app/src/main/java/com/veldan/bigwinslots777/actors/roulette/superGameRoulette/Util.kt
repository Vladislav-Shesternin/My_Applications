package com.veldan.bigwinslots777.actors.roulette.superGameRoulette

import com.veldan.bigwinslots777.actors.roulette.RouletteGroupController

data class SuperGameRouletteItem(
    override val segment: Pair<Float, Float>,
    val number          : Int,
): RouletteGroupController.RouletteItem