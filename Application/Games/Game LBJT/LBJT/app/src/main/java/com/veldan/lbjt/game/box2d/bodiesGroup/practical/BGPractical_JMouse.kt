package com.veldan.lbjt.game.box2d.bodiesGroup.practical

import com.badlogic.gdx.physics.box2d.BodyDef
import com.veldan.lbjt.game.actors.practical_settings.AAbstractPracticalSettings
import com.veldan.lbjt.game.actors.practical_settings.APracticalSettings_JMouse
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.box2d.bodies.`object`.BCObject
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedMouseScreen
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.util.Once
import com.veldan.lbjt.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class BGPractical_JMouse(screenMouse: AdvancedMouseScreen): AbstractBGPractical(screenMouse) {

    override val title              = "Mouse Joint"
    override val aPracticalSettings = APracticalSettings_JMouse(screenMouse)

    // Body
    private val bDynamicCircle = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Field
    private val isFirstMove = AtomicBoolean(true)


    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_DynamicCircle()

        createB_DynamicCircle()
    }

    override fun hideAndToStartBody(endBlock: () -> Unit) {
        endBlock()
    }

    override fun showAndUpdateBody(endBlock: () -> Unit) {
        endBlock()
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_DynamicCircle() {
        arrayOf(bDynamicCircle).onEach { it.apply {
            id = BodyId.Practical.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Practical.DYNAMIC))

            bodyDef.gravityScale = 0f
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_DynamicCircle() {
        createBody(bDynamicCircle, 230f, 508f, 239f, 239f)

        bDynamicCircle.body?.let { bCircle ->
            bDynamicCircle.renderBlockArray.add(AbstractBody.RenderBlock {
                if (isFirstMove.get() && bCircle.linearVelocity.isZero.not()) {
                    isFirstMove.set(false)
                    bCircle.gravityScale = 1f
                }
            })
        }

    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------



}