package com.elastic.couben.game.box2d

import com.badlogic.gdx.physics.box2d.Joint
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen
import com.elastic.couben.utils.Once
import com.elastic.couben.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AdvancedJoint(
    val screenBox2d: AdvancedBox2dScreen,
    val joint      : Joint
) {

    val coroutine = CoroutineScope(Dispatchers.Default)
    private val destroyOnce = Once()


    fun destroy() {
        cancelCoroutinesAll(coroutine)

        with(screenBox2d.worldUtil) {
            world.destroyJoint(joint)
        }
    }

    fun startDestroy(time: Long = 0) {
        destroyOnce.once {
            coroutine.launch {
                delay(time)
                screenBox2d.worldUtil.destroyJointList.add(this@AdvancedJoint)
            }
        }
    }


}

fun Joint.toAdvancedJoint(screenBox2d: AdvancedBox2dScreen) = AdvancedJoint(screenBox2d,this)

