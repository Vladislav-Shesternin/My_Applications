package com.cosmo.plinko.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.actors.AWinScore
import com.cosmo.plinko.game.actors.button.AButton
import com.cosmo.plinko.game.box2d.AbstractBody
import com.cosmo.plinko.game.box2d.BodyId
import com.cosmo.plinko.game.box2d.WorldUtil
import com.cosmo.plinko.game.box2d.bodies.BBall
import com.cosmo.plinko.game.box2d.bodies.BHorizontal
import com.cosmo.plinko.game.box2d.bodies.BMini
import com.cosmo.plinko.game.box2d.bodies.BWhite
import com.cosmo.plinko.game.box2d.bodies.BWin
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.animShow
import com.cosmo.plinko.game.utils.actor.setOnClickListener
import com.cosmo.plinko.game.utils.advanced.AdvancedBox2dScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.font.FontParameter
import com.cosmo.plinko.game.utils.region
import com.cosmo.plinko.game.utils.runGDX
import com.cosmo.plinko.game.utils.toB2
import java.util.Random

class GameScreen_2(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    private val parameter = FontParameter()
    private val countFlow = MutableStateFlow(0)

    // Actor
    private val aTop  = Image(game.gameAssets.BH_TOP)
    private val aTop2 = Image(game.gameAssets.BLACK_HOLE)
    private val aPane = Image(game.gameAssets.PANEL)
    private val tPane = Image(game.gameAssets.TIMER)
    private val cnLbl = Label(countFlow.value.toString(), Label.LabelStyle(fontGeneratorMachineGunk.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS).setSize(49)), Color.WHITE))
    private val tmLbl = Label("00:00", Label.LabelStyle(fontGeneratorMachineGunk.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(49)), Color.WHITE))
    private val winS  = AWinScore(this)

    // Body
    private val bHor1     = BHorizontal(this)
    private val bHor2     = BHorizontal(this)
    private val bBotom    = BMini(this)
    private val bBalls    = List(10) { BBall(this) }
    private val bWin      = List(10) { BWin(this) }

    // Fields
    private val ballFlow  = MutableSharedFlow<BBall>(10)

    val xxx = listOf(491f, 591f)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Hor()
        createB_Big()
        createB_Platform()
        createB_Ball()
        createB_Win()

        addBHTop()
        addPanelStar()
        addTimer()
        addMenu()
        addPause()
        addNumbers()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

        collectStar()
        collectBall()
    }

    override fun dispose() {
//        listOf(bgBorders).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBHTop() {
        addActor(aTop)
        aTop.setBounds(0f, 1717f, 1080f, 203f)
        addActor(aTop2)
        aTop2.setBounds(44f, 1456f, 992f, 436f)
    }

    private fun AdvancedStage.addPanelStar() {
        addActor(aPane)
        aPane.setBounds(450f, 1817f, 208f, 74f)
        addActor(cnLbl)
        cnLbl.apply {
            setBounds(465f, 1829f, 173f, 46f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedStage.addWS() {
        addActor(winS)
//        winS.animHide()
        winS.setBounds(111f, 478f, 858f, 923f)
    }

    private fun AdvancedStage.addTimer() {
        addActor(tPane)
        tPane.setBounds(417f, 57f, 245f, 94f)
        addActor(tmLbl)
        tmLbl.apply {
            setBounds(500f, 81f, 146f, 46f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            var time   = listOf(60, 120, 180, 240, 300).random()
            var minute: Int
            var secund: Int

            while (time > 0) {
                runGDX {
                    minute = time/60
                    secund = time%60
                    tmLbl.setText("0${minute}:${secund.ifMense10()}")
                }
                --time
                delay(1000)
            }

            isWorldPause = true

            runGDX {
                val iii = Image(drawerUtil.getRegion(Color.valueOf("2D1F85").apply { a = 0.62f }))
                iii.animHide()
                addActor(iii)
                iii.setSize(WIDTH, HEIGHT)
                iii.animShow(0.3f) {
                    addWS()
                    winS.apply {
                        restart.setOnClickListener(game.soundUtil) {
                            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                                game.navigationManager.navigate(GameScreen_2::class.java.name)
                            }
                        }
                        next.setOnClickListener(game.soundUtil) {
                            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                                game.navigationManager.navigate(GameScreen_1::class.java.name)
                            }
                        }
                    }
                    winS.updateScore(countFlow.value)

                    if (levelBallIndex != -1) {
                        leveles[levelBallIndex] = when {
                            countFlow.value in 1..50   -> 1
                            countFlow.value in 51..500 -> 2
                            countFlow.value > 500            -> 3
                            else                             -> 0
                        }
                        levelBallIndex = -1
                    }

//                    winS.animShow(0.5f)
                }
            }


        }
    }

    private fun AdvancedStage.addMenu() {
        val palanet = AButton(this@GameScreen_2, AButton.Static.Type.MENU)
        addActor(palanet)
        palanet.setBounds(57f, 1780f, 124f, 106f)
        palanet.setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addPause() {
        val palanet = AButton(this@GameScreen_2, AButton.Static.Type.PAUSE)
        addActor(palanet)
        palanet.setBounds(899f, 1780f, 124f, 106f)

        var isPress = false
        palanet.setOnClickListener {
            if (isPress) {
                isPress = false
                palanet.unpress()
                isWorldPause = false
            } else {
                isPress = true
                palanet.press()
                isWorldPause = true
            }
        }
    }

    private fun AdvancedStage.addNumbers() {
        val palanet = Image(game.gameAssets.NUMBERS)
        addActor(palanet)
        palanet.setBounds(23f, 275f, 1034f, 90f)
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------
    private fun createB_Hor() {
        bHor1.apply {
            id = BodyId.BORDERS
            collisionList.add(BodyId.Game.BALL)

            create(-960f,0f/*-91f*/,1500f,52f)
        }
        bHor2.apply {
            id = BodyId.BORDERS
            collisionList.add(BodyId.Game.BALL)

            create(540f,0f/*-91f*/,1500f,52f)
        }
    }

    private fun createB_Big() {
        bBotom.apply {
            id = BodyId.BORDERS
            collisionList.add(BodyId.Game.BALL)

            create(0f, 0f, 1080f, 306f)
        }
    }

    private fun createB_Ball() {
        bBalls.onEach { it.apply {
            id = BodyId.Game.BALL
            setNoneId()
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.BALL, BodyId.Game.STATIC, BodyId.Game.STAR))

            create(xxx.random(), 1776f, 40f, 40f)
            body?.gravityScale = 0f

            toStartBall()
            ballFlow.tryEmit(this)

            beginContactBlockArray.add(AbstractBody.ContactBlock {  en ->
                when (en.id) {
                    BodyId.BORDERS -> coroutine?.launch {
                        delay(100)
                        toStartBall()
                        ballFlow.tryEmit(this@apply)
                    }
                }
            })
        } }

    }

    private fun createB_Platform() {
        coroutine?.launch {
            var nx = 405f
            var ny = 1168f
            var increment = 0f

            val isRepeatDelay = Random().nextBoolean()

            MutableStateFlow(3).also { flow ->
                flow.collect { count ->
                    if (count > 10) return@collect
                    repeat(count) { index ->
                        runGDX {
                            if (isActive) {
                                BWhite(this@GameScreen_2).apply {
                                    id = BodyId.Game.STATIC
                                    collisionList.add(BodyId.Game.BALL)
                                    create(nx, ny, 60f, 60f)
                                    nx += 104f

                                    if (index.inc() == count) {
                                        increment += 53f
                                        nx = (405 - increment)
                                        ny -= 101f
                                        flow.value++
                                    }
                                }
                            }
                        }
                        if (isRepeatDelay) delay(80)
                    }
                    if (isRepeatDelay.not()) delay(120)
                }
            }
        }
    }

    private fun createB_Win() {
        var nx = 38f
        bWin.onEachIndexed { index, bWin ->
            runGDX {
                bWin.apply {
                    id = BodyId.Game.STAR
                    collisionList.add(BodyId.Game.BALL)

                    create(nx, 284f, 61f, 61f)
                }

                val img = Image(game.gameAssets.SALUTE)
                stageUI.addActor(img)
                img.setBounds(nx - 32f, 251f, 126f, 126f)
                img.animHide()

                nx += 105

                bWin.beginContactBlockArray.add(AbstractBody.ContactBlock { it.checkBall(img) })
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun AbstractBody.checkBall(img: Image) {
        runGDX {
            if (this.id == BodyId.Game.BALL) {
                this as BBall
                if (this.atomBool.getAndSet(false)) {
                    img.addAction(
                        Actions.sequence(
                            Actions.fadeIn(0.2f),
                            Actions.delay(0.5f),
                            Actions.fadeOut(0.3f),
                        )
                    )
                    countFlow.value += (10..30).random()
                    toStartBall()
                    ballFlow.tryEmit(this)
                }
            }
        }
    }

    private fun collectStar() {
        coroutine?.launch {
            countFlow.collect {
                runGDX { cnLbl.setText(it.toString()) }
            }
        }
    }

    private fun collectBall() {
        coroutine?.launch {
            ballFlow.collect { ball ->
                runGDX {
                    ball.body?.apply {
                        gravityScale = 1f
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
                delay(100)
                ball.setOriginalId()
                ball.atomBool.set(true)
                delay((500..2000L).random())
            }
        }
    }

    private fun BBall.toStartBall() {
        runGDX {
            this.setNoneId()
            this.body?.apply {
                fixtureList.first().apply {
                    density = (10..20).random()/10f
                    restitution = (10..60).random()/100f
                    friction = (10..60).random()/100f
                }
                gravityScale = 0f
                setLinearVelocity(0f, 0f)
                setTransform(xxx.random().toB2, 1776f.toB2, 0f)
            }
        }
    }

    private fun Int.ifMense10(): String = if (this<10) "0$this" else this.toString()

}