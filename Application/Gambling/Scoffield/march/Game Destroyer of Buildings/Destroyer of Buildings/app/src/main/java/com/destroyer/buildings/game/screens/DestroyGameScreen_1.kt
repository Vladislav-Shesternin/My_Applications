package com.destroyer.buildings.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.RopeJoint
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.destroyer.buildings.game.LibGDXGame
import com.destroyer.buildings.game.box2d.AbstractJoint
import com.destroyer.buildings.game.box2d.bodies.BBuild
import com.destroyer.buildings.game.box2d.bodies.BStatic
import com.destroyer.buildings.game.box2d.bodies.BStone
import com.destroyer.buildings.game.box2d.bodiesGroup.BGBorders
import com.destroyer.buildings.game.box2d.bodiesGroup.BGChain
import com.destroyer.buildings.game.box2d.destroyAll
import com.destroyer.buildings.game.utils.TIME_ANIM
import com.destroyer.buildings.game.utils.actor.animHide
import com.destroyer.buildings.game.utils.actor.animShow
import com.destroyer.buildings.game.utils.actor.setOnClickListener
import com.destroyer.buildings.game.utils.advanced.AdvancedStage
import com.destroyer.buildings.game.utils.advanced.AdvancedUserScreen
import com.destroyer.buildings.game.utils.region
import com.destroyer.buildings.game.utils.toB2

class DestroyGameScreen_1(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val backImg    = Image(game.allAssets.back)
    private val bImg       = Image(game.allAssets.bb[0])
    private val destroyImg = Image(game.allAssets.destroy)

    // Body Group
    private val bgBorders = BGBorders(this)
    private val bgChain   = BGChain(this)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loaderAssets.BUILDINGS_AREA.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM) {
            // b
            bImg.addAction(Actions.sequence(
                Actions.fadeOut(0.4f),
                Actions.fadeIn(0.4f),
                Actions.fadeOut(0.4f),
                Actions.fadeIn(0.4f),
                Actions.fadeOut(0.4f),
            ))
            // destroy
            destroyImg.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, 50f, 0.3f),
                Actions.moveBy(0f, -50f, 0.3f),
            )))
            destroyImg.addAction(Actions.sequence(
                Actions.delay(2f),
                Actions.fadeOut(0.4f),
                Actions.removeActor()
            ))
        }
    }

    override fun dispose() {
        listOf(bgBorders, bgChain).destroyAll()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBackImg()
        addBImg()
        addDestroyImg()

        createBG_Borders()
        createBG_Chain()

        createB_Stone()

        createB_Builds()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addBackImg() {
        addActor(backImg)
        backImg.setBounds(66f, 930f, 104f, 104f)
        backImg.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) {
                game.navigationManager.back()
            }
        }
    }

    private fun AdvancedStage.addBImg() {
        addActor(bImg)
        bImg.setBounds(1233f, 150f, 209f, 283f)
    }

    private fun AdvancedStage.addDestroyImg() {
        addActor(destroyImg)
        destroyImg.setBounds(1271f, 464f, 133f, 214f)
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_Stone() {
        val bStone = BStone(this)
        bStone.create(953f, 201f, 102f, 102f)

        AbstractJoint<WeldJoint, WeldJointDef>(this).create(WeldJointDef().apply {
            bodyA = bStone.body
            bodyB = bgChain.lastik.body

            localAnchorB.set(Vector2(3f, 0f).toB2)
        })
        AbstractJoint<RopeJoint, RopeJointDef>(this).create(RopeJointDef().apply {
            bodyA = bgChain.bStatic.body
            bodyB = bStone.body

            maxLength = 552f.toB2
        })

    }

    private fun createB_Builds() {
        var nx = 1254f
        var ny = 401f
        game.allAssets.builds[0].split(42, 42).flatten().onEachIndexed { index, region ->
            val img = BBuild(this, region)
            img.create(nx,ny,42f,42f)
            nx += 42
            if(index.inc()%4==0) {
                nx = 1254f
                ny -= 42f
            }
        }
    }

    // ---------------------------------------------------
    // create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, 1920f, 1080f)
    }

    private fun createBG_Chain() {
        bgChain.create(1001f, 256f, 6f, 552f)
    }

}