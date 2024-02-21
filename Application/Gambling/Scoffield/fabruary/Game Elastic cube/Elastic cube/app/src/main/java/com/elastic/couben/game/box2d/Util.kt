package com.elastic.couben.game.box2d

import com.badlogic.gdx.physics.box2d.Joint
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen

fun List<AbstractBody>.destroyAll(time: Long = 0) {
    onEach { it.startDestroy(time) }
}

fun List<Joint>.destroyAll(screenBox2d: AdvancedBox2dScreen, time: Long = 0) {
    onEach { it.toAdvancedJoint(screenBox2d).startDestroy(time) }
}