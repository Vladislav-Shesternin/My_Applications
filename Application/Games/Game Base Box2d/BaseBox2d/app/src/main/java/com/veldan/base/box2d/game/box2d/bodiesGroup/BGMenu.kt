package com.veldan.base.box2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.base.box2d.game.box2d.AbstractBodyGroup
import com.veldan.base.box2d.game.box2d.AbstractJoint
import com.veldan.base.box2d.game.box2d.BodyId.Menu.BUTTON
import com.veldan.base.box2d.game.box2d.BodyId.Menu.STATIC
import com.veldan.base.box2d.game.box2d.bodies.BRegularBtn
import com.veldan.base.box2d.game.box2d.bodies.BStaticCircle
import com.veldan.base.box2d.game.manager.FontTTFManager
import com.veldan.base.box2d.game.utils.GameColor
import com.veldan.base.box2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.base.box2d.game.utils.toB2
import com.veldan.base.box2d.game.utils.toUI


class BGMenu(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 466f

    // Body
    private val bStaticCircleLeft  = BStaticCircle(screenBox2d)
    private val bStaticCircleRight = BStaticCircle(screenBox2d)
    private val bRegularBtnTutorial = BRegularBtn(screenBox2d, "Tutorial", Label.LabelStyle(FontTTFManager.Inter.ExtraBold.font_50.font, GameColor.textGreen))

    // Joint
    private val jTutorialDistanceLeft = AbstractJoint<DistanceJoint>(screenBox2d)

    // Field
    private val drawer = screenBox2d.drawerUtil.drawer

    override fun create(position: Vector2, size: Vector2) {
        super.create(position, size)

        createB_StaticCircle()
        createB_RegularBtn()

        createJ_Tutorial()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_StaticCircle() {
        bStaticCircleLeft.apply {
            id  = STATIC
            collisionList.add(BUTTON)
            createBody(this, 7f, 899f, 113f, 113f)
        }
        bStaticCircleRight.apply {
            id = STATIC
            collisionList.add(BUTTON)
            createBody(this, 346f, 899f, 113f, 113f)
        }
    }

    private fun createB_RegularBtn() {
        bRegularBtnTutorial.apply {
            id = BUTTON
            collisionList.addAll(arrayOf(STATIC, BUTTON))
            createBody(this, 0f, 519f, 466f, 169f)

        }
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Tutorial() {
        createJoint(jTutorialDistanceLeft, DistanceJointDef().apply {
            bodyA            = bStaticCircleLeft.body
            bodyB            = bRegularBtnTutorial.body
            collideConnected = true

            localAnchorA.set(Vector2(56f, 2f).subCenter(bodyA))
            localAnchorB.set(Vector2(64f, 167f).subCenter(bodyB))

            standardizer.scope { length = 215f.toStandart.toB2 }
            frequencyHz  = 1f
        })

        bStaticCircleLeft.actor?.blockPreDraw = { jTutorialDistanceLeft.joint?.run { drawer.line(
            anchorA.toUI, anchorB.toUI, GameColor.joint, 3f
        ) } }
        
    }

}