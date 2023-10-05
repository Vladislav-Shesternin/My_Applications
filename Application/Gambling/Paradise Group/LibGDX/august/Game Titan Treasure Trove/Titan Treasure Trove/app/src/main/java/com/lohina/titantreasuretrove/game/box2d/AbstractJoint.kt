package com.lohina.titantreasuretrove.game.box2d

import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedBox2dScreen
import com.lohina.titantreasuretrove.game.utils.onEachAndRemove
import com.lohina.titantreasuretrove.game.utils.runGDX
import com.lohina.titantreasuretrove.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AbstractJoint<T : Joint, TD : JointDef>(
    val screenBox2d  : AdvancedBox2dScreen
): Destroyable {

    var coroutine: CoroutineScope? = null
        private set

    var joint: T? = null
        private set

    var jointDef: TD? = null
        private set

    private val createdBlockList   = mutableListOf<(AbstractJoint<T, TD>) -> Unit>()
    private val destroyedBlockList = mutableListOf<() -> Unit>()

    var createdBlock: (AbstractJoint<T, TD>) -> Unit = {}
        set(value) {
            createdBlockList.add(value)
        }
    var destroyedBlock: () -> Unit = {}
        set(value) {
            destroyedBlockList.add(value)
        }


    override fun dispose(isDestroy: Boolean, block: () -> Unit) {
        if (joint != null) {
            cancelCoroutinesAll(coroutine)
            coroutine = null

            if (isDestroy) {
                screenBox2d.worldUtil.world.destroyJoint(joint)
                destroyedBlockList.onEach { it.invoke() }.clear()
            }
            joint = null

            block()
        }
    }

    fun createInWorld() {
        if (joint == null) {
            joint = screenBox2d.worldUtil.world.createJoint(jointDef).apply { userData = this@AbstractJoint } as T
            coroutine = CoroutineScope(Dispatchers.Default)

            createdBlockList.onEach { it.invoke(this) }.clear()
        }
    }

    fun requestToCreate(jointDef: TD, block: () -> Unit = {}) {
        createdBlock = { block() }

        this.jointDef = jointDef
        screenBox2d.worldUtil.createJointList.add(this@AbstractJoint)
    }

    override fun requestToDestroy(block: () -> Unit) {
        destroyedBlock = { block() }
        screenBox2d.worldUtil.destroyJointList.add(this@AbstractJoint)
    }


}