package com.csmttt.medus.play.game.box2d

import com.csmttt.medus.play.game.box2d.bodies.AbstractBody

fun List<AbstractBody>.destroyAll() {
    onEach { it.destroy() }
}