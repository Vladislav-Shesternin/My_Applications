package com.bango.weld.androit.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.bango.weld.androit.game.box2d.AbstractJoint
import com.bango.weld.androit.game.box2d.WorldUtil
import com.bango.weld.androit.game.box2d.bodies.BCircle
import com.bango.weld.androit.game.box2d.bodies.BLeft
import com.bango.weld.androit.game.box2d.bodies.BRight
import com.bango.weld.androit.game.box2d.bodies.BSmile
import com.bango.weld.androit.game.manager.FontTTFManager
import com.bango.weld.androit.game.manager.NavigationManager
import com.bango.weld.androit.game.manager.SpriteManager
import com.bango.weld.androit.game.utils.*
import com.bango.weld.androit.game.utils.advanced.AdvancedBox2dScreen
import com.bango.weld.androit.game.utils.advanced.AdvancedGroup
import com.bango.weld.androit.game.utils.advanced.AdvancedStage
import com.bango.weld.androit.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class IgraScreen: AdvancedBox2dScreen(WorldUtil()) {

    private val randomSmileIndex get() = (0..SpriteManager.ListRegion.SMILE.regionList.lastIndex).shuffled().first()
    private val smileIndex       = randomSmileIndex

    // Actors
    private val backgran   = Image(SpriteManager.GameRegion.BADABPUMBA.region)
    private val catch      = Image(SpriteManager.GameRegion.CATCH.region)
    private val smile      = Image(SpriteManager.ListRegion.SMILE.regionList[smileIndex])
    private val countLabel = Label("0/0", Label.LabelStyle(FontTTFManager.Jim.font_75.font, GameColor.green))

    // Bodies
    private val bSmileList = List(30) { BSmile(this) }

    private val bCircLeft  = BCircle(this)
    private val bCircRight = BCircle(this)
    private val bRukaLeft  = BLeft(this)
    private val bRukaRight = BRight(this)

    // Fields
    private val countFlow = MutableStateFlow(0)
    private val count     = (3..7).shuffled().first()
    private val smileFlow = MutableSharedFlow<BSmile>(replay = 30)

    // Joints
    private val jointLeft  = AbstractJoint<RevoluteJoint>(this)
    private val jointRight = AbstractJoint<RevoluteJoint>(this)

    override fun AdvancedStage.addActorsOnStageUI() {
        addBackgran()
        addCatchAndSmile()
        addCount()

        createB_SmileList()
        createB_Left()
        createB_Right()

        mainGroup.addListener(getInputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedStage.addBackgran() {
        addActor(backgran)

        backgran.apply {
            setBounds(0f, -44f, 1754f, 856f)

            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, 44f, 3f),
                Actions.moveBy(0f, -88f, 6f),
                Actions.moveBy(0f, 44f, 3f),
            )))
        }
    }

    private fun AdvancedStage.addCatchAndSmile() {
        addActors(catch, smile)

        catch.setBounds(1297f, 658f, 145f, 106f)
        smile.apply {
            setBounds(1460f, 668f, 96f, 96f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.sequence(
                Actions.scaleBy(-0.3f, -0.3f, 0.5f),
                Actions.scaleBy(0.3f, 0.3f, 0.5f),
            )))
        }
    }

    private fun AdvancedStage.addCount() {
        addActor(countLabel)

        countLabel.setBounds(1556f, 663f, 192f, 106f)
        countLabel.setAlignment(Align.center)

        coroutine.launch {
            countFlow.collect {
                runGDX {
                    countLabel.setText("$it/$count")

                    if (it >= count) NavigationManager.navigate(IgraScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Create Bodies
    // ---------------------------------------------------

    private fun createB_SmileList() {
        coroutine.launch {
            runGDX {
                bSmileList.onEach { bSmile ->
                    bSmile.create(-200f, 1200f, 96f, 96f)


                    bSmile.renderBlock = {
                        if (bSmile.isEmit.get()) bSmile.body?.position?.let {
                            if (it.y < 0) {
                                smileFlow.tryEmit(bSmile)
                                bSmile.isEmit.set(false)
                            }
                        }
                    }

                    smileFlow.tryEmit(bSmile)
                }
            }

            launch {
                smileFlow.collect { bSmile ->
                    runGDX {
                        bSmile.apply {
                            this.smileIndex = randomSmileIndex
                            this.actor.drawable = TextureRegionDrawable(SpriteManager.ListRegion.SMILE.regionList[this.smileIndex])
                        }
                        bSmile.body?.fixtureList?.first()?.apply {
                            restitution = kotlin.random.Random.nextFloat()
                            friction    = kotlin.random.Random.nextFloat()
                        }
                        bSmile.body?.apply {
                            linearDamping = (5..30).shuffled().first() / 10f
                            isAwake = false
                            setLinearVelocity(0f, 0f)
                            setTransform(sizeConverterUIToBox.getSize((48..1706).shuffled().first().toFloat(), 800f).vector2, 0f)
                            isAwake = true
                        }
                    }
                    delay((200..300).shuffled().first().toLong())
                    bSmile.isEmit.set(true)
                }
            }
        }
    }

    private fun createB_Left() {
        bCircLeft.create(421f, 402f, 9f, 9f)
        bRukaLeft.create(375f, 166f, 82f, 271f)

        jointLeft.create(RevoluteJointDef().apply {
            bodyA = bCircLeft.body
            bodyB = bRukaLeft.body
            enableMotor    = true
            motorSpeed     = 90 * DEGTORAD
            maxMotorTorque = 3000f
        })
    }

    private fun createB_Right() {
        bCircRight.create(1325f, 402f, 9f, 9f)
        bRukaRight.create(1297f, 166f, 82f, 271f)

        jointRight.create(RevoluteJointDef().apply {
            bodyA = bCircRight.body
            bodyB = bRukaRight.body
            enableMotor    = true
            motorSpeed     = -90 * DEGTORAD
            maxMotorTorque = 3000f
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getInputListener() = object : InputListener() {

        val clickPoint = Vector2()
        val callback   = QueryCallback {
            (it.body?.userData as? BSmile)?.let { bSmile ->
                if (bSmile.smileIndex == smileIndex) {
                    countFlow.value += 1
                    runGDX {
                        bSmile.body?.setTransform(0f, 40f, 0f)
                    }
                    smileFlow.tryEmit(bSmile)
                    return@QueryCallback false
                }
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            clickPoint.set(sizeConverterUIToBox.getSize(x, y).vector2)

            worldUtil.world.QueryAABB(callback,
                clickPoint.x - 0.1f, clickPoint.y - 0.1f,
                clickPoint.x + 0.1f, clickPoint.y + 0.1f,
            )

            return true
        }
    }
 }