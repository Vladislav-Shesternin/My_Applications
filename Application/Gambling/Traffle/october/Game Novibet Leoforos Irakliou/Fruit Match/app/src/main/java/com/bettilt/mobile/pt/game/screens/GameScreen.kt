package com.bettilt.mobile.pt.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.actors.AWin
import com.bettilt.mobile.pt.game.box2d.AbstractBody
import com.bettilt.mobile.pt.game.box2d.AbstractJoint
import com.bettilt.mobile.pt.game.box2d.BodyId
import com.bettilt.mobile.pt.game.box2d.WorldContactListener
import com.bettilt.mobile.pt.game.box2d.bodies.BFruit
import com.bettilt.mobile.pt.game.box2d.bodies.BHorizontalSensor
import com.bettilt.mobile.pt.game.box2d.bodies.BMenu
import com.bettilt.mobile.pt.game.box2d.bodiesGroup.BGBorders
import com.bettilt.mobile.pt.game.box2d.destroyAll
import com.bettilt.mobile.pt.game.utils.DEGTORAD
import com.bettilt.mobile.pt.game.utils.HEIGHT_BOX2D
import com.bettilt.mobile.pt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bettilt.mobile.pt.game.utils.WIDTH_BOX2D
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.animShow
import com.bettilt.mobile.pt.game.utils.actor.enable
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedMouseScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedStage
import com.bettilt.mobile.pt.game.utils.font.FontParameter
import com.bettilt.mobile.pt.game.utils.region
import com.bettilt.mobile.pt.game.utils.runGDX
import com.bettilt.mobile.pt.game.utils.toB2
import com.bettilt.mobile.pt.util.currentTimeMinus
import com.bettilt.mobile.pt.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class GameScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    companion object {
        private var LEVEL       = 0
        private var FRUIT_COUNT = 45
    }

    private var SCORE = 100 + (25 * LEVEL)

    private val parameterBtn = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "/").setSize(25)
    private val font25       = fontGeneratorKadwa_Bold.generateFont(parameterBtn)

    // Actor
    private val aPanelImg = Image(game.spriteUtil.PANEL)
    private val aPanelLbl = Label("", Label.LabelStyle(font25, Color.WHITE))
    private val aWin      = AWin(this)

    // Body
    private val bMenu   = BMenu(this)
    private val bSensor = BHorizontalSensor(this)

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Fields
    private val scoreFlow  = MutableStateFlow(0)
    private val bFruitFlow = MutableSharedFlow<BFruit>(FRUIT_COUNT)

    private val gluedList = hashMapOf<String, MutableList<BFruit>>()

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.spriteUtil.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        initB_Menu()
        initB_Sensor()

        createBG_Borders()
        createB_Menu()
        createB_Sensor()

        addWorldBeginContactBlock()
        collectB_FruitFlow()

        repeat(FRUIT_COUNT) { createB_Fruit() }

        addPanel()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(bgBorders).destroyAll()
        super.dispose()
    }

    // ---------------------------------------------------
    // Init Body
    // ---------------------------------------------------

    private fun initB_Menu() {
        bMenu.apply {
            id = BodyId.Game.BUTTON
            collisionList.add(BodyId.Game.FRUIT)

            getActor().setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun initB_Sensor() {
        bSensor.apply {
            id = BodyId.Game.SENSOR
            collisionList.add(BodyId.Game.FRUIT_SENSOR)

            endContactBlockArray.add(AbstractBody.ContactBlock { bContact ->
                when(bContact.id) {
                    BodyId.Game.FRUIT_SENSOR -> {
                        bContact.setOriginalId()
                    }
                }
            })
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPanel() {
        addActors(aPanelImg, aPanelLbl)
        aPanelImg.setBounds(3f, 704f, 214f, 96f)
        aPanelLbl.apply {
            setBounds(18f, 719f, 184f, 66f)
            setAlignment(Align.center)
        }

        collectScore()
    }

    private fun AdvancedStage.addWin() {
        addAndFillActor(aWin)
        aWin.animHide()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Menu() {
        bMenu.create(1305f,0f,256f,209f)
    }

    private fun createB_Sensor() {
        bSensor.create(0f,720f,1561f,50f)
    }

    private fun createB_Fruit() {
        val bFruit = BFruit(this, BFruit.Type.values().random()).apply {
            id = BodyId.Game.FRUIT
            id = BodyId.Game.FRUIT_SENSOR

            collisionList.addAll(
                arrayOf(
                    BodyId.BORDERS,
                    BodyId.Game.BUTTON,
                    BodyId.Game.FRUIT,
                    BodyId.Game.SENSOR
                )
            )

            freeze()
            create(0f, 800f, 125f, 125f)
        }

        bFruitFlow.tryEmit(bFruit)
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f,0f,WIDTH,HEIGHT)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun collectScore() {
        coroutine?.launch {
            var isWin = false
            scoreFlow.collect { score ->
                runGDX {
                    aPanelLbl.setText("$score / $SCORE")
                    if (score >= SCORE) {
                        if (isWin.not()) {
                            isWin = true
                            worldStepTime = 0f

                            stageUI.addWin()
                            aWin.apply {
                                setScore(score, SCORE)
                                animShow(TIME_ANIM_SCREEN_ALPHA) { enable() }
                            }
                            LEVEL++
                        }
                    }
                }
            }
        }
    }

    private fun collectB_FruitFlow() {
        coroutine?.launch {
            var X: Float
            var Y: Float
            var A: Float

            bFruitFlow.collect { bFruit ->
                X = (bFruit.center.x.toInt()..(WIDTH_BOX2D-bFruit.center.x).toInt()).random().toFloat()
                Y = HEIGHT_BOX2D+bFruit.center.y
                A = (0..360).random() * DEGTORAD

                runGDX {
                    bFruit.body?.setTransform(X, Y, A)
                    bFruit.unfreeze()
                }

                delay((70..200L).random())
            }
        }
    }

    private fun addWorldBeginContactBlock() {

        var jDistance: AbstractJoint<DistanceJoint, DistanceJointDef>
        var time      = 0L
        val timeDelay = 200L

        worldUtil.contactListener.beginContactBlockArray.add(WorldContactListener.ContactBlock { bodyA, bodyB ->
            runGDX {
                if (bodyA is BFruit && bodyB is BFruit) {

                    if (bodyA.type == bodyB.type) {
                        if (currentTimeMinus(time) >= timeDelay) {
                            time = System.currentTimeMillis()

                            jDistance = AbstractJoint(this@GameScreen)
                            jDistance.create(DistanceJointDef().apply {
                                this.bodyA = bodyA.body
                                this.bodyB = bodyB.body
                                length = 125f.toB2
                            })

                            bodyA.getActor().animGlued()
                            bodyB.getActor().animGlued()

                            when {
                                (bodyA.gluedKey == null) and (bodyB.gluedKey == null) -> {
                                    UUID.randomUUID().toString().also { key ->
                                        bodyA.gluedKey = key
                                        bodyB.gluedKey = key

                                        gluedList[key] = mutableListOf(bodyA, bodyB)
                                    }
                                }
                                (bodyA.gluedKey != null) and (bodyB.gluedKey != null) -> {
                                    gluedList.apply {
                                        coroutine?.launch {
                                            filterKeys { it == bodyA.gluedKey || it == bodyB.gluedKey }.keys.onEach { key ->
                                                get(key)?.onEach { bFruit ->
                                                    runGDX { bFruit.advancedDestroy() }
                                                    delay(timeDelay)
                                                }
                                            }
                                            remove(bodyA.gluedKey)
                                            remove(bodyB.gluedKey)
                                        }
                                    }
                                }
                                (bodyA.gluedKey != null) and (bodyB.gluedKey == null) -> {
                                    coroutine?.launch {
                                        gluedList[bodyA.gluedKey]?.onEach { bFruit ->
                                            runGDX { bFruit.advancedDestroy() }
                                            delay(timeDelay)
                                        }
                                        runGDX { bodyB.advancedDestroy() }
                                    }
                                }
                                (bodyA.gluedKey == null) and (bodyB.gluedKey != null) -> {
                                    coroutine?.launch {
                                        gluedList[bodyB.gluedKey]?.onEach { bFruit ->
                                            runGDX { bFruit.advancedDestroy() }
                                            delay(timeDelay)
                                        }
                                        runGDX { bodyA.advancedDestroy() }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        })

    }

    private fun BFruit.advancedDestroy() {
        destroyFruit { createB_Fruit() }
        scoreFlow.value++
    }

}