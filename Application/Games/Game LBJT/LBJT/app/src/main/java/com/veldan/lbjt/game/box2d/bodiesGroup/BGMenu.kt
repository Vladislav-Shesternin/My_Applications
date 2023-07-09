package com.veldan.lbjt.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.AbstractBodyGroup
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.BodyId.Menu.BUTTON
import com.veldan.lbjt.game.box2d.BodyId.Menu.STATIC
import com.veldan.lbjt.game.box2d.bodies.BRegularBtn
import com.veldan.lbjt.game.box2d.bodies.BStatic
import com.veldan.lbjt.game.box2d.bodies.BStaticCircle
import com.veldan.lbjt.game.manager.FontTTFManager
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.toB2
import com.veldan.lbjt.game.utils.toUI


class BGMenu(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 466f

    // Body
    private val bStaticCircleLeft     = BStaticCircle(screenBox2d)
    private val bStaticCircleRight    = BStaticCircle(screenBox2d)
    private val bRegularBtnTutorial   = BRegularBtn(screenBox2d, "Tutorial", Label.LabelStyle(FontTTFManager.Inter.ExtraBold.font_50.font, GameColor.textGreen))

    // Joint
    private val jDistanceTutorialLeft   = AbstractJoint<DistanceJoint>(screenBox2d)
    private val jDistanceTutorialRight  = AbstractJoint<DistanceJoint>(screenBox2d)

    // Field
    private val drawer = screenBox2d.drawerUtil.drawer

    override fun create(position: Vector2, size: Vector2) {
        super.create(position, size)
        initB_StaticCircle()

        createB_StaticCircle()
        createB_RegularBtn()

        createJ_Tutorial()
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_StaticCircle() {
        arrayOf(bStaticCircleLeft, bStaticCircleRight).onEach { it.apply {
            id  = STATIC
            collisionList.add(BUTTON)
            fixtureDef.apply {
                restitution = 1f
                friction    = 0f
            }
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_StaticCircle() {
        createBody(bStaticCircleLeft, 7f, 899f, 113f, 113f)
        createBody(bStaticCircleRight, 346f, 899f, 113f, 113f)
    }

    private fun createB_RegularBtn() {
        bRegularBtnTutorial.apply {
            id = BUTTON
            bodyDef.angularDamping = 0.8f
            bodyDef.linearDamping  = 0.2f

            collisionList.addAll(arrayOf(STATIC, BUTTON))
            createBody(this, 0f, 519f, 466f, 169f)
        }
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Tutorial() {
        // Left
        createJoint(
            jDistanceTutorialLeft,
            bStaticCircleLeft, bRegularBtnTutorial,
            Vector2(56f, 2f), Vector2(64f, 167f), 215f,
        )
        // Right
        createJoint(
            jDistanceTutorialRight,
            bStaticCircleRight, bRegularBtnTutorial,
            Vector2(56f, 2f), Vector2(403f, 167f), 215f,
        )
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun createJoint(
        _joint      : AbstractJoint<out Joint>,
        _bodyA      : AbstractBody,
        _bodyB      : AbstractBody,
        _anchorA    : Vector2,
        _anchorB    : Vector2,
        _length     : Float,
        _isDrawJoint: Boolean = true
    ) {
        createJoint(_joint, DistanceJointDef().apply {
            bodyA = _bodyA.body
            bodyB = _bodyB.body

            localAnchorA.set(_anchorA.subCenter(bodyA))
            localAnchorB.set(_anchorB.subCenter(bodyB))
            collideConnected = true

            standardizer.scope { length = _length.toStandart.toB2 }
            frequencyHz  = 1.5f
            dampingRatio = 0.5f
        })

        if (_isDrawJoint) _bodyA.actor?.blockPreDraw = { _joint.joint?.run { drawer.line(
            anchorA.toUI, anchorB.toUI, GameColor.joint, 3f
        ) } }
    }

}