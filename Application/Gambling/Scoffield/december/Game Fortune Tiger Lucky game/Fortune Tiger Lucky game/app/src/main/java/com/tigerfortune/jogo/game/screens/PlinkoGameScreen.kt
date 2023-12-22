//package com.tigerfortune.jogo.game.screens
//
//import com.badlogic.gdx.graphics.Color
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.scenes.scene2d.Touchable
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.badlogic.gdx.scenes.scene2d.ui.Label
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.isActive
//import kotlinx.coroutines.launch
//import com.tigerfortune.jogo.game.LibGDXGame
//import com.tigerfortune.jogo.game.actors.AResultGroup
//import com.tigerfortune.jogo.game.actors.ASettingsGroup
//import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
//import com.tigerfortune.jogo.game.box2d.AbstractBody
//import com.tigerfortune.jogo.game.box2d.BodyId
//import com.tigerfortune.jogo.game.box2d.WorldUtil
//import com.tigerfortune.jogo.game.box2d.bodies.BBalk
//import com.tigerfortune.jogo.game.box2d.bodies.BBall
//import com.tigerfortune.jogo.game.box2d.bodies.BPlayground
//import com.tigerfortune.jogo.game.box2d.bodies.BWin
//import com.tigerfortune.jogo.game.box2d.bodies.BallType
//import com.tigerfortune.jogo.game.box2d.bodies.StaticBallType
//import com.tigerfortune.jogo.game.box2d.bodies.StaticIsRed
//import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
//import com.tigerfortune.jogo.game.utils.actor.animHide
//import com.tigerfortune.jogo.game.utils.actor.animShow
//import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedBox2dScreen
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
//import com.tigerfortune.jogo.game.utils.font.FontParameter
//import com.tigerfortune.jogo.game.utils.region
//import com.tigerfortune.jogo.game.utils.runGDX
//import com.tigerfortune.jogo.game.utils.toB2
//import com.tigerfortune.jogo.util.log
//
//var StaticTime = 120
//var StaticBombCost = -100
//
//class PlinkoGameScreen(override val game: LibGDXGame) : AdvancedBox2dScreen(WorldUtil()) {
//
//    companion object {
//        private const val BALL_COUNT = 20
//    }
//
//    private val assets = game.gameAssets
//
//    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(50)
//    private val font          = fontGeneratorDefault.generateFont(fontParameter)
//
//    // Actor
//    private val coinPanelImg = Image(assets.COINS_BAR)
//    private val timePanelImg = Image(assets.TIMER_BAR)
//    private val coinLbl      = Label("0", Label.LabelStyle(font, Color.WHITE))
//    private val timeLbl      = Label("0${StaticTime / 60}:00", Label.LabelStyle(font, Color.WHITE))
//    private val result       = AResultGroup(this).apply {
//        color.a = 0f
//        touchable = Touchable.disabled
//    }
//
//    // Field
//    private var coinCounter = 0
//
//    override fun show() {
//        stageUI.root.animHide()
//        setBackgrounds(game.splashAssets.PLINKO_BACKGROUND.region)
//        super.show()
//        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
//    }
//
//    override fun AdvancedStage.addActorsOnStageUI() {
//        addBackMenu()
//        addPause()
//        addPortal()
//        addCoinPanel()
//        addTimePanel()
//
//        createB_Playground()
//        createB_Balk()
//        createB_Win()
//        createB_Ball()
//
//        addAndFillActor(result)
//    }
//
//    override fun dispose() {
//        StaticBallType = BallType.DEFAULT
//        StaticIsRed    = false
//        StaticTime     = 120
//        StaticBombCost = -100
//        StaticAllCoin += coinCounter
//
//        super.dispose()
//    }
//
//    // ------------------------------------------------------------------------
//    // Add Actors
//    // ------------------------------------------------------------------------
//
//    private fun AdvancedStage.addBackMenu() {
//        val menuBtn = Image(game.gameAssets.MENU_BTN)
//        addActor(menuBtn)
//        menuBtn.setBounds(47f, 1785f, 113f, 103f)
//
//        menuBtn.setOnClickListener(game.soundUtil) {
//            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
//        }
//    }
//
//    private fun AdvancedStage.addPause() {
//        val pause = ACheckBox(this@PlinkoGameScreen, ACheckBox.Static.Type.PAUSE)
//        addActor(pause)
//        pause.setBounds(921f, 1785f, 113f, 103f)
//
//        pause.setOnCheckListener { isWorldPause = it }
//    }
//
//    private fun AdvancedStage.addPortal() {
//        val portalImg = Image(assets.PORTAL)
//        addActor(portalImg)
//        portalImg.setBounds(440f, 1418f, 200f, 200f)
//    }
//
//    private fun AdvancedStage.addCoinPanel() {
//        addActors(coinPanelImg, coinLbl)
//        coinPanelImg.setBounds(419f, 1779f, 243f, 80f)
//        coinLbl.setBounds(520f, 1789f, 98f, 59f)
//    }
//
//    private fun AdvancedStage.addTimePanel() {
//        addActors(timePanelImg, timeLbl)
//        timePanelImg.setBounds(419f, 1659f, 243f, 80f)
//        timeLbl.setBounds(510f, 1669f, 138f, 60f)
//
//        coroutine?.launch {
//            var timer  = StaticTime
//            var minute = 0
//            var second = 0
//            while (isActive && timer >= 0) {
//                delay(1000)
//                if (isWorldPause.not()) {
//                    timer -= 1
//
//                    minute = timer / 60
//                    second = timer % 60
//
//                    runGDX {
//                        timeLbl.setText("0$minute:${if (second < 10) "0$second" else second}")
//                    }
//                }
//            }
//            if (timer < 0) showResult()
//        }
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createB_Playground() {
//        BPlayground(this).apply {
//            id = BodyId.Game.STATIC
//            collisionList.add(BodyId.Game.BALL)
//
//            create(156f, 215f, 769f, 1152f)
//        }
//    }
//
//    private fun createB_Balk() {
//        repeat(21) {
//            BBalk(this).apply {
//                id = BodyId.Game.STATIC
//                collisionList.add(BodyId.Game.BALL)
//
//                create(BPlayground.Static.positionsBalk[it], BPlayground.Static.sizeBalk)
//            }
//        }
//    }
//
//    private fun createB_Win() {
//        var nx = 174f
//
//        listOf(
//            BWin(this, BWin.Static.Type.BOMB),
//            BWin(this, BWin.Static.Type.BOMB),
//            BWin(this, BWin.Static.Type.X2),
//            BWin(this, BWin.Static.Type.X2),
//            BWin(this, BWin.Static.Type.X5),
//            BWin(this, BWin.Static.Type.X5),
//        ).shuffled().onEach { bWin ->
//            bWin.apply {
//                collisionList.add(BodyId.Game.BALL)
//
//                create(nx, 138f, 114f, 77f)
//                nx += 10f+114f
//            }
//        }
//    }
//
//    private fun createB_Ball() {
//        val ballFlow = MutableSharedFlow<BBall>(replay = BALL_COUNT)
//
//        repeat(BALL_COUNT) {
//            BBall(this).also { bBall ->
//                bBall.id = BodyId.Game.BALL
//                bBall.setNoneId()
//                bBall.collisionList.addAll(arrayOf(
//                    BodyId.Game.BALL,
//                    BodyId.Game.STATIC,
//                    BodyId.Game.BOMB,
//                    BodyId.Game.X2,
//                    BodyId.Game.X5,
//                ))
//
//                bBall.renderBlockArray.add(AbstractBody.RenderBlock {
//                    bBall.body?.let {
//                        if (it.position.y <= 0f) {
//                            if (bBall.atomicBoolean.getAndSet(false)) {
//                                ballFlow.tryEmit(bBall)
//                            }
//                        }
//                    }
//                })
//
//                bBall.beginContactBlockArray.add(AbstractBody.ContactBlock { bEnemy ->
//                    when(bEnemy.id) {
//                        BodyId.Game.BOMB -> {
//                            if (bBall.atomicBoolean.getAndSet(false)) {
//                                ballFlow.tryEmit(bBall)
//                                coinCounter += StaticBombCost
//                                coinLbl.setText(coinCounter)
//                                game.soundUtil.apply { play(bomb) }
//                            }
//                        }
//                        BodyId.Game.X2 -> {
//                            if (bBall.atomicBoolean.getAndSet(false)) {
//                                ballFlow.tryEmit(bBall)
//                                coinCounter += 20
//                                coinLbl.setText(coinCounter)
//                                game.soundUtil.apply { play(success) }
//                            }
//                        }
//                        BodyId.Game.X5 -> {
//                            if (bBall.atomicBoolean.getAndSet(false)) {
//                                ballFlow.tryEmit(bBall)
//                                coinCounter += 50
//                                coinLbl.setText(coinCounter)
//                                game.soundUtil.apply { play(success) }
//                            }
//                        }
//                    }
//                })
//
//                bBall.bodyDef.gravityScale = 0f
//                bBall.create(-100f, 0f, 71f, 71f)
//
//                ballFlow.tryEmit(bBall)
//            }
//        }
//
//        coroutine?.launch {
//            ballFlow.collect { bBall ->
//                runGDX {
//                    bBall.body?.apply {
//                        bBall.setNoneId()
//
//                        setLinearVelocity(0f, 0f)
//                        gravityScale = 0f
//                        isAwake = false
//
//                        setTransform(Vector2(-100f, 0f).toB2, 0f)
//                    }
//                }
//            }
//        }
//        coroutine?.launch {
//            ballFlow.collect { bBall ->
//                delay((500L..1000L).random())
//                runGDX {
//                    bBall.body?.apply {
//                        setTransform(Vector2(540f, 1518f).toB2, 0f)
//
//                        gravityScale = 1f
//                        isAwake      = true
//                        applyLinearImpulse((-10..10).random().toFloat(), (10..35).random().toFloat(), worldCenter.x, worldCenter.y, true)
//                    }
//                }
//                delay(100)
//                runGDX {
//                    bBall.setOriginalId()
//                    bBall.atomicBoolean.set(true)
//                }
//            }
//        }
//    }
//
//    // ---------------------------------------------------
//    // Logic
//    // ---------------------------------------------------
//
//    private fun showResult() {
//        result.apply {
//            isWorldPause = true
//            updateCoin(coinCounter)
//            animShow(TIME_ANIM_SCREEN_ALPHA)
//            touchable = Touchable.enabled
//        }
//        game.soundUtil.apply { play(win) }
//    }
//
//}