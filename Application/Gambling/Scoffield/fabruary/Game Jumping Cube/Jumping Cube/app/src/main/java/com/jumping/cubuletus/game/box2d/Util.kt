package com.jumping.cubuletus.game.box2d

import com.jumping.cubuletus.game.box2d.bodies.AbstractBody

fun List<AbstractBody>.destroyAll() {
    onEach { it.destroy() }
}