package com.jungle.jumping.bird.game.box2d

fun List<AbstractBody>.destroyAll() {
    onEach { it.destroy() }
}