package com.hellhot.competition.game.box2d

import com.hellhot.competition.game.box2d.bodies.AbstractBody

fun List<AbstractBody>.destroyAll() {
    onEach { it.destroy() }
}