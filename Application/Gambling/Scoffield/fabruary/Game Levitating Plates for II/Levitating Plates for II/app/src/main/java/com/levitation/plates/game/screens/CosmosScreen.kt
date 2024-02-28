package com.levitation.plates.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.levitation.plates.game.LibGDXGame
import com.levitation.plates.game.box2d.AbstractBody
import com.levitation.plates.game.box2d.AbstractJoint
import com.levitation.plates.game.box2d.BodyId
import com.levitation.plates.game.box2d.bodies.BPlanet
import com.levitation.plates.game.box2d.bodies.BTarelkaBlue
import com.levitation.plates.game.box2d.bodies.BTarelkaGreen
import com.levitation.plates.game.box2d.bodies.BVertical
import com.levitation.plates.game.box2d.bodiesGroup.BGBorders
import com.levitation.plates.game.utils.HEIGHT_BOX2D
import com.levitation.plates.game.utils.HEIGHT_UI
import com.levitation.plates.game.utils.WIDTH_BOX2D
import com.levitation.plates.game.utils.WIDTH_UI
import com.levitation.plates.game.utils.advanced.AdvancedStage
import com.levitation.plates.game.utils.advanced.AdvancedCosmoScreen
import com.levitation.plates.game.utils.font.FontParameter
import com.levitation.plates.game.utils.region
import com.levitation.plates.game.utils.runGDX
import com.levitation.plates.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CosmosScreen(_game: LibGDXGame): AdvancedCosmoScreen(_game) {

    private val fParameters = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(80)
    private val font80      = fontGenerator_CorinthiaRegular.generateFont(fParameters)

    // Actor
    private val aGreenLbl = Label("0", Label.LabelStyle(font80, Color.WHITE))
    private val aBlueLbl  = Label("0", Label.LabelStyle(font80, Color.WHITE))

    // Body
    private val bGreen = BTarelkaGreen(this)
    private val bBlue  = BTarelkaBlue(this)
    private val bVorotaGreen = BVertical(this)
    private val bVorotaBlue  = BVertical(this)
    private val bTopPlanetList    = List(22) { BPlanet(this, it) }
    private val bBottomPlanetList = List(22) { BPlanet(this, it) }

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Joint
    private val jMotorA = AbstractJoint<MotorJoint, MotorJointDef>(this)
    private val jMotorB = AbstractJoint<MotorJoint, MotorJointDef>(this)

    private var counterGreen = 0
    private var counterBlue  = 0

    private val flowTopPlanet    = MutableSharedFlow<BPlanet>(replay = 22)
    private val flowBottomPlanet = MutableSharedFlow<BPlanet>(replay = 22)

    private val listDelay  = listOf(600L, 700L, 800L, 1000L)
    private val minPlanetX = (3f * 10f).toInt()
    private val maxPlanetX = ((WIDTH_BOX2D-3f) * 10f).toInt()
    private val getPlanetX: Float get() = (minPlanetX..maxPlanetX).random() / 10f

    override fun show() {
        setBackBackground(game.allAssets.MAIN.region)
        super.show()
    }

    override fun dispose() {
        bgBorders.destroy()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()

        createB_Tarelkas()

        createJ_Tarelka()
        createB_Vorota()
        createB_TopPlanets()
        createB_BottomPlanets()

        addCounterLbls()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addCounterLbls() {
        addActors(aGreenLbl, aBlueLbl)
        aGreenLbl.apply {
            setBounds(1814f, 0f, 71f, 97f)
            setAlignment(Align.right)
        }
        aBlueLbl.setBounds(56f, 12f, 71f, 97f)
    }

    // ---------------------------------------------------
    // create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH_UI, HEIGHT_UI)
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_Tarelkas() {
        // Green
        bGreen.create(1680f, 438f, 203f, 203f)

        // Blue
        bBlue.create(36f, 438f, 203f, 203f)
    }

    private fun createB_TopPlanets() {
        bTopPlanetList.onEach { bp ->
            bp.id = BodyId.Cosmos.PLANETA
            bp.setNoneId()
            bp.create(0f, 1200f, 93f, 93f)
            flowTopPlanet.tryEmit(bp)

            bp.renderBlockArray.add(AbstractBody.RenderBlock {
                if (bp.body!!.position.y <= -1f) {
                    if (bp.isStart.getAndSet(false)) flowTopPlanet.tryEmit(bp)
                }
            })
        }

        coroutine?.launch {
            // toStart
            launch {
                flowTopPlanet.collect { bp ->
                    runGDX {
                        bp.setNoneId()
                        bp.body?.apply {
                            setLinearVelocity(0f, 0f)
                            isAwake = false
                            gravityScale = 0f
                            setTransform(0f, HEIGHT_BOX2D + 1f, 0f)
                        }
                    }
                }
            }
            // toReady
            launch {
                flowTopPlanet.collect { bp ->
                    delay(listDelay.random())
                    runGDX {
                        bp.body?.apply {
                            isAwake = true
                            gravityScale = (2..10).random() / 10f
                            setTransform(getPlanetX, HEIGHT_BOX2D + 1f, 0f)
                        }
                        bp.setOriginalId()
                        bp.isStart.set(true)
                    }
                }
            }

        }
    }

    private fun createB_BottomPlanets() {
        bBottomPlanetList.onEach { bp ->
            bp.id = BodyId.Cosmos.PLANETA
            bp.setNoneId()
            bp.create(0f, -200f, 93f, 93f)
            flowBottomPlanet.tryEmit(bp)

            bp.renderBlockArray.add(AbstractBody.RenderBlock {
                if (bp.body!!.position.y >= HEIGHT_BOX2D + 1f) {
                    if (bp.isStart.getAndSet(false)) flowBottomPlanet.tryEmit(bp)
                }
            })
        }

        coroutine?.launch {
            // toStart
            launch {
                flowBottomPlanet.collect { bp ->
                    runGDX {
                        bp.setNoneId()
                        bp.body?.apply {
                            setLinearVelocity(0f, 0f)
                            isAwake = false
                            gravityScale = 0f
                            setTransform(0f, -2f, 0f)
                        }
                    }
                }
            }
            // toReady
            launch {
                flowBottomPlanet.collect { bp ->
                    delay(listDelay.random())
                    runGDX {
                        bp.body?.apply {
                            isAwake = true
                            gravityScale = -((2..10).random() / 10f)
                            setTransform(getPlanetX, -2f, 0f)
                        }
                        bp.setOriginalId()
                        bp.isStart.set(true)
                    }
                }
            }

        }
    }

    private fun createB_Vorota() {
        // Green
        bVorotaGreen.apply {
            id = BodyId.Cosmos.VOROTA
            collisionList.add(BodyId.Cosmos.PLANETA)

            create(1910f, 0f, 10f, HEIGHT_UI)

            beginContactBlockArray.add(AbstractBody.ContactBlock {
                game.soundUtil.apply { play(futuristic_smg_sound_effect_win, volumeLevel * 0.3f) }
                counterGreen++
                aGreenLbl.setText(counterGreen)
            })
        }

        // Blue
        bVorotaBlue.apply {
            id = BodyId.Cosmos.VOROTA
            collisionList.add(BodyId.Cosmos.PLANETA)

            create(0f, 0f, 10f, HEIGHT_UI)

            beginContactBlockArray.add(AbstractBody.ContactBlock {
                game.soundUtil.apply { play(futuristic_smg_sound_effect_win, volumeLevel * 0.3f) }
                counterBlue++
                aBlueLbl.setText(counterBlue)
            })
        }
    }

    // ---------------------------------------------------
    // create Joint
    // ---------------------------------------------------

    private fun createJ_Tarelka() {
        // Green
        jMotorA.create(MotorJointDef().apply {
            bodyA = bgBorders.bDown.body
            bodyB = bGreen.body
            collideConnected = true

            maxForce  = 200f
            maxTorque = 100f
            correctionFactor = 0.5f
            linearOffset.set(Vector2(1783f, 540f).toB2)
        })

        // Blue
        jMotorB.create(MotorJointDef().apply {
            bodyA = bgBorders.bDown.body
            bodyB = bBlue.body
            collideConnected = true

            maxForce  = 200f
            maxTorque = 100f
            correctionFactor = 0.5f
            linearOffset.set(Vector2(138f, 540f).toB2)
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

//    private fun toStartBall() {
//        if (isStartBall.getAndSet(true).not()) {
//            runGDX {
//                game.soundUtil.apply { play(gool, /*volumeLevel * 0.2f*/) }
//
//                bBall.body?.apply {
//                    setLinearVelocity(0f, 0f)
//                    isAwake = false
//                    setTransform(Vector2(960f, 692f).toB2, 0f)
//                }
//
//                createJ_Ball()
//
//                isDestroyJoint.set(false)
//            }
//        }
//    }

}