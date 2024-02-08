package com.vbbb.time.game.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.vbbb.time.game.game.actors.button.AButton
import com.vbbb.time.game.game.actors.button.AButtonStyle
import com.vbbb.time.game.game.box2d.AbstractJoint
import com.vbbb.time.game.game.box2d.BodyId
import com.vbbb.time.game.game.box2d.WorldUtil
import com.vbbb.time.game.game.box2d.bodies.*
import com.vbbb.time.game.game.manager.FontTTFManager
import com.vbbb.time.game.game.manager.NavigationManager
import com.vbbb.time.game.game.manager.SpriteManager
import com.vbbb.time.game.game.utils.Size
import com.vbbb.time.game.game.utils.actor.disable
import com.vbbb.time.game.game.utils.actor.enable
import com.vbbb.time.game.game.utils.actor.setBounds
import com.vbbb.time.game.game.utils.addNew
import com.vbbb.time.game.game.utils.advanced.AdvancedBox2dScreen
import com.vbbb.time.game.game.utils.advanced.AdvancedStage
import com.vbbb.time.game.game.utils.runGDX
import com.vbbb.time.game.game.utils.vector2
import com.vbbb.time.game.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

abstract class LevelScreen : AdvancedBox2dScreen(WorldUtil()) {

    abstract val targetPos: Vector2
    abstract val count: Int

    var myCount = 0
    var ballCount = 3

    // Actors
    private val countText = Label("0/0", Label.LabelStyle(FontTTFManager.ImbueFont.font_103.font, Color.WHITE))
    private val menu = AButton(AButtonStyle.menu)
    private val draggedArea = Actor()
    private val botA = Image(SpriteManager.GameRegion.BOTTOM.region)
    private val topA = Image(SpriteManager.GameRegion.TOP.region)
    private val targetA = Image(SpriteManager.GameRegion.TARGET.region)

    private val b1 = Image(SpriteManager.GameRegion.MINI_NOT.region)
    private val b2 = Image(SpriteManager.GameRegion.MINI_YES.region)
    private val b3 = Image(SpriteManager.GameRegion.MINI_YES.region)

    // Body
    private val bLea by lazy { BLea(this) }
    private val bBall by lazy { BBall(this) }
    private val bTopa by lazy { BTopa(this) }
    private val bTarg by lazy { BTarget(this) }
    private val bLine by lazy { BLine(this) }

    // Joint

    // BodyGroup
    // private val bgBorders = BGBorders(this)

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.UKRAINE.region)
        super.show()
        mainGroup.disable()
    }

    final override fun AdvancedStage.addActorsOnStageUI() {
        createB_Lea()
        createB_Topa()
        createB_Target()
        addDraggedAre()
        addCountText()
        addMini()
        addMenu()
        addBot()
        createB_Line()
        createB_Ball()
        addTopAndTarget()

        addActorsOnStage()
    }

    open fun AdvancedStage.addActorsOnStage() {}

    override fun dispose() {
        super.dispose()
        //listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addDraggedAre() {
        addActor(draggedArea)
        draggedArea.apply {
            setBounds(121f, 88f, 200f, 482f)
            addListener(getListener())
        }
    }

    private fun AdvancedStage.addCountText() {
        addActor(countText)
        countText.apply {
            setText("$myCount/$count")
            setBounds(1004f, 435f, 172f, 125f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedStage.addMini() {
        addActors(b1,b2,b3)
        b1.apply {
            setBounds(192+50f,519f, 31f, 31f)
        }
        b2.apply {
            setBounds(155+50f,519f, 31f, 31f)
        }
        b3.apply {
            setBounds(118+50f,519f, 31f, 31f)
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menu)
        menu.apply {
            setBounds(1219f, 22f, 79f, 79f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedStage.addBot() {
        addActor(botA)
        botA.apply {
            setBounds(targetPos.addNew(0f, 35f), Size(76f, 88f))
        }
    }

    private fun AdvancedStage.addTopAndTarget() {
        addActors(targetA, topA)
        topA.apply {
            setBounds(targetPos.addNew(7f, 17f), Size(98f, 108f))
        }
        targetA.apply {
            setBounds(targetPos.addNew(51f, 0f), Size(79f, 79f))
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Lea() {
        bLea.create(101f, 78f, 1118f, 502f)
    }

var joba: Job? = null

    private fun createB_Ball() {
        bBall.create(189f, 88f, 68f, 68f)

        bBall.beginContactBlock = {
            if (it.id == BodyId.TARGET) {
                myCount++
                runGDX {
                    countText.setText("$myCount/$count")
                    if (ballCount >= 0) {
                        bBall.body?.setTransform(
                            sizeConverterUIToBox.getSize(189f, 88f).vector2,
                            0f
                        )
                        bBall.body?.setLinearVelocity(0f, 0f)
                        bLine.id = BodyId.LINE
                        when (ballCount) {
                            2 -> b2.drawable =
                                TextureRegionDrawable(SpriteManager.GameRegion.MINI_NOT.region)
                            1 -> b3.drawable =
                                TextureRegionDrawable(SpriteManager.GameRegion.MINI_NOT.region)
                        }
                        draggedArea.enable()
                        joba?.cancel()
                    }
                }
            }
            if (it.id == BodyId.LINE) {
                bLine.id = BodyId.NONE
                ballCount--
                draggedArea.disable()

                if (ballCount == 0) runGDX {
                    menu.setOrigin(Align.center)
                    menu.addAction(Actions.forever(Actions.sequence(
                        Actions.scaleBy(0.3f, 0.3f, 0.5f),
                        Actions.scaleBy(-1f, -1f, 0.5f),
                        Actions.scaleTo(1f, 1f, 0.5f),
                        Actions.delay(1f)
                    )))
                } else { if (ballCount >= 0) joba = coroutine.launch {
                    delay(6000)

                    if (isActive) {
                        when (ballCount) {
                            2 -> b2.drawable = TextureRegionDrawable(SpriteManager.GameRegion.MINI_NOT.region)
                            1 -> b3.drawable = TextureRegionDrawable(SpriteManager.GameRegion.MINI_NOT.region)
                        }

                        runGDX {
                            bLine.id = BodyId.LINE
                            bBall.body?.setTransform(sizeConverterUIToBox.getSize(189f, 88f).vector2, 0f)
                            bBall.body?.setLinearVelocity(0f, 0f)
                        }

                        draggedArea.enable()
                    }
                } }
            }
        }
    }

    private fun createB_Line() {
        bLine.create(413f, 97f, 30f, 464f)
    }

    private fun createB_Topa() {
        bTopa.create(targetPos.addNew(7f, 17f), Size(98f, 108f))
    }

    private fun createB_Target() {
        bTarg.create(targetPos.addNew(73f, 22f), Size(35f, 35f))
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

//    private fun createBG_Borders() {
//        bgBorders.create(Vector2(0f, 0f), Size(WIDTH, HEIGHT))
//    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    val j: AbstractJoint<MouseJoint> by lazy { AbstractJoint<MouseJoint>(this@LevelScreen) }

    private fun getListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            runGDX {
                j.create(MouseJointDef().apply {
                    bodyA = bLea.body
                    bodyB = bBall.body
                    target.set(bBall.body?.position)
                    maxForce = 150f
                    collideConnected = true
                })
            }

            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            runGDX {
                j.joint?.target = sizeConverterUIToBox.getSize(121f+x, 88f+y).vector2

                if (x > draggedArea.x+draggedArea.width) j.startDestroy()
            }
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            runGDX {
                j.startDestroy()
            }
        }

    }
}