package com.fellinger.yeasman.game.box2d

fun List<AbstractBody>.destroyAll() {
    onEach { it.destroy() }
}