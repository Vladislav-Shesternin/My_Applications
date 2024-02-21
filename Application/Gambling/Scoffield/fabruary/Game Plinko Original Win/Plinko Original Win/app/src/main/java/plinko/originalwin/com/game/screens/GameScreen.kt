package plinko.originalwin.com.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import plinko.originalwin.com.game.LibGDXGame
import plinko.originalwin.com.game.actors.AArrows
import plinko.originalwin.com.game.actors.ANumberPanel
import plinko.originalwin.com.game.actors.ASettings
import plinko.originalwin.com.game.actors.AText
import plinko.originalwin.com.game.box2d.AbstractBody
import plinko.originalwin.com.game.box2d.BodyId
import plinko.originalwin.com.game.box2d.WorldUtil
import plinko.originalwin.com.game.box2d.bodies.BBall
import plinko.originalwin.com.game.box2d.bodies.BCircle
import plinko.originalwin.com.game.box2d.bodies.BFinishSensor
import plinko.originalwin.com.game.box2d.bodies.BGreenCoin
import plinko.originalwin.com.game.box2d.bodies.BPlayground
import plinko.originalwin.com.game.utils.HEIGHT_BOX2D
import plinko.originalwin.com.game.utils.HEIGHT_UI
import plinko.originalwin.com.game.utils.TIME_ANIM_ALPHA
import plinko.originalwin.com.game.utils.WIDTH_UI
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.animShow
import plinko.originalwin.com.game.utils.actor.disable
import plinko.originalwin.com.game.utils.actor.enable
import plinko.originalwin.com.game.utils.actor.setOnClickListener
import plinko.originalwin.com.game.utils.advanced.AdvancedBox2dScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedStage
import plinko.originalwin.com.game.utils.font.FontParameter
import plinko.originalwin.com.game.utils.region
import plinko.originalwin.com.game.utils.runGDX
import plinko.originalwin.com.game.utils.toB2

class GameScreen(override val game: LibGDXGame) : AdvancedBox2dScreen(WorldUtil()) {

    companion object {
        var balance = 0
            private set
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "X$").setSize(60)
    private val font          = fontGenerator_ZenDots_Regular.generateFont(fontParameter)

    // Actor
    private val aSettings = ASettings(this)
    private val aNumPanel = ANumberPanel(this)
    private val aArrows   = AArrows(this)
    private val aText     = AText(this)
    private val aListCoins     = List(10) { Actor() }
    private val aStartCountLbl = Label("X 7", Label.LabelStyle(font, Color.WHITE))
    private val aWinLbl        = Label("$0", Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bPlayground  = BPlayground(this)
    private val bList9Circle = List(45) { BCircle(this) }
    private val bList8Circle = List(40) { BCircle(this) }
    private val bBall        = BBall(this)
    private val bList9GreenCoin   = List(45) { BGreenCoin(this) }
    private val bList10GreenCoin  = List(40) { BGreenCoin(this) }
    private val bListFinishSensor = List(9) { BFinishSensor(this) }

    // Field
    private val tmpPosVector   = Vector2()
    private val startCountFlow = MutableStateFlow(7)
    private val winFlow        = MutableStateFlow(0)

    override fun show() {
        game.soundUtil.apply { play(sound_start) }

        balance = 0

        stageUI.root.animHide()
        setUIBackground(game.allAssets.GAME_PANEL.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addSettings()
        addSettingsBtn()
        addNumberPanel()
        addArrows()
        addStartCounterLbl()
        addCoins()
        addText()
        addWinLbl()

        createB_Playground()
        createB_ListCircle()
        createB_ListGreenCoin()
        createB_ListFinishSensor()
        createB_Ball()

        generateGreenCoin()

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addSettings() {
        aSettings.apply {
            color.a = 0f
            disable()
        }
        addActor(aSettings)
        aSettings.setBounds(610f, 1746f, 244f, 151f)
    }

    private fun AdvancedStage.addSettingsBtn() {
        val play = Actor()
        addActor(play)
        play.setBounds(876f, 1746f, 111f, 151f)

        var isOpen = false
        play.setOnClickListener(game.soundUtil) {
            if (isOpen) {
                isOpen = false
                aSettings.animHide(0.2f) { aSettings.disable() }
            } else {
                isOpen = true
                aSettings.animShow(0.2f) { aSettings.enable() }
            }
        }

    }

    private fun AdvancedStage.addNumberPanel() {
        addActor(aNumPanel)
        aNumPanel.setBounds(123f, 219f, 834f, 206f)
    }

    private fun AdvancedStage.addArrows() {
        addActor(aArrows)
        aArrows.setBounds(135f, 1620f, 810f, 66f)
    }

    private fun AdvancedStage.addCoins() {
        var nx = 125f
        aListCoins.onEach { actor ->
            addActor(actor)
            actor.apply {
                setBounds(nx, 1529f, 82f, 82f)
                nx += 83f

                setOnClickListener(game.soundUtil) {
                    aListCoins.onEach { it.disable() }
                    startCountFlow.value -= 1

                    bBall.body?.apply {
                        setLinearVelocity(0f, 0f)
                        setTransform(tmpPosVector.set(actor.x, actor.y).add(1f, 11f).toB2.add(bBall.center), 0f)
                        gravityScale = 1f
                        isAwake      = true
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addText() {
        addActor(aText)
        aText.color.a = 0f
        aText.disable()
        aText.setBounds(94f, 1555f, 893f, 154f)
    }

    private fun AdvancedStage.addStartCounterLbl() {
        addActor(aStartCountLbl)
        aStartCountLbl.apply {
            setBounds(732f, 84f, 243f, 72f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            startCountFlow.collect { count ->
                if (count == 7) return@collect
                runGDX {
                    aArrows.animHide(0.2f)

                    aStartCountLbl.setText("X $count")
                    val text = if (count == 0) "GAME OVER" else "PLAYING..."

                    aText.setText(text)
                    aText.animShow(0.2f)
                }
            }
        }
    }

    private fun AdvancedStage.addWinLbl() {
        addActor(aWinLbl)
        aWinLbl.apply {
            setBounds(122f, 84f, 331f, 72f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            var counter: Int
            winFlow.collect { cost ->
                counter = 0
                while (isActive && counter < cost) {
                    counter += 1
                    balance += 1
                    runGDX { aWinLbl.setText("$$balance") }
                    delay((7..10L).random())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------
    private fun createB_Playground() {
        bPlayground.apply {
            id = BodyId.Game.STATIC
            collisionList.add(BodyId.Game.BALL)
        }

        bPlayground.create(0f, 0f, WIDTH_UI, HEIGHT_UI)
    }

    private fun createB_ListCircle() {
        var nx9 = 197f
        var ny9 = 1500f
        bList9Circle.onEachIndexed { index, bCircle ->
            bCircle.apply {
                id = BodyId.Game.STATIC
                collisionList.add(BodyId.Game.BALL)
            }

            bCircle.create(nx9, ny9, 21f, 21f)
            nx9 += 62 + 21
            if (index.inc() % 9 == 0) {
                nx9 = 197f
                ny9 -= 166 + 21
            }
        }

        var nx8 = 239f
        var ny8 = 1409f
        bList8Circle.onEachIndexed { index, bCircle ->
            bCircle.apply {
                id = BodyId.Game.STATIC
                collisionList.add(BodyId.Game.BALL)
            }

            bCircle.create(nx8, ny8, 21f, 21f)
            nx8 += 62 + 21
            if (index.inc() % 8 == 0) {
                nx8 = 239f
                ny8 -= 169 + 21
            }
        }
    }

    private fun createB_ListGreenCoin() {
        var nx9 = 183f
        var ny9 = 1394f
        bList9GreenCoin.onEachIndexed { index, bCoin ->
            bCoin.apply {
                id = BodyId.Game.GREEN_COIN
                collisionList.add(BodyId.Game.BALL)

                beginContactBlockArray.add(AbstractBody.ContactBlock {
                    if (it.id == BodyId.Game.BALL) {
                        winFlow.value = value
                        setNoneId()
                        getActor()?.animTake()
                    }
                })
            }

            bCoin.create(nx9, ny9, 50f, 50f)
            nx9 += 33 + 50
            if (index.inc() % 9 == 0) {
                nx9 = 183f
                ny9 -= 140 + 50
            }
        }

        var nx10 = 141f
        var ny10 = 1298f
        bList10GreenCoin.onEachIndexed { index, bCoin ->
            bCoin.apply {
                id = BodyId.Game.GREEN_COIN
                collisionList.add(BodyId.Game.BALL)

                beginContactBlockArray.add(AbstractBody.ContactBlock {
                    if (it.id == BodyId.Game.BALL) {
                        winFlow.value = value
                        setNoneId()
                        getActor()?.animTake()
                    }
                })
            }

            bCoin.create(nx10, ny10, 50f, 50f)
            nx10 += 33 + 50
            if (index.inc() % 10 == 0) {
                nx10 = 141f
                ny10 -= 137 + 50
            }
        }
    }

    private fun createB_Ball() {
        bBall.id = BodyId.Game.BALL
        bBall.collisionList.addAll(arrayOf(BodyId.Game.STATIC, BodyId.Game.GREEN_COIN, BodyId.Game.FINISH))

        bBall.create(0f, HEIGHT_UI, 60f, 60f)
        bBall.body?.apply {
            gravityScale = 0f
            isAwake      = false
            setLinearVelocity(0f, 0f)
        }
    }

    private fun createB_ListFinishSensor() {
        var nx = 139f

        val tmpActor = Actor()
        stageUI.addActor(tmpActor)

        val winList = listOf(50, 0, 250, 0, 500, 0, 250, 0, 50)
        bListFinishSensor.onEachIndexed { index, bFinishSensor ->
            bFinishSensor.apply {
                id = BodyId.Game.FINISH
                collisionList.add(BodyId.Game.BALL)

                beginContactBlockArray.add(AbstractBody.ContactBlock {
                    if (it.id == BodyId.Game.BALL) {
                        runGDX {
                            winFlow.value = winList[index]

                            val text = if (winList[index] == 0) {
                                game.soundUtil.apply { play(sound_nowin) }
                                "YOU DIDN`T WIN"
                            } else {
                                game.soundUtil.apply { play(sound_score) }
                                "YOU WON $${winList[index]}"
                            }
                            aText.setText(text)

                            tmpActor.addAction(Actions.sequence(
                                Actions.delay(1f),
                                Actions.run {
                                    aListCoins.onEach { coin -> coin.enable() }

                                    bBall.body?.apply {
                                        gravityScale = 0f
                                        isAwake = false
                                        setLinearVelocity(0f, 0f)
                                        setTransform(
                                            tmpPosVector.set(0f, HEIGHT_BOX2D).add(bBall.center), 0f
                                        )
                                    }

                                    generateGreenCoin()

                                    aText.apply {
                                        disable()
                                        animHide(0.2f)
                                    }
                                    aArrows.animShow(0.2f)

                                    if (startCountFlow.value == 0) stageUI.root.animHide(TIME_ANIM_ALPHA) {
                                        game.navigationManager.navigate(GameOverScreen::class.java.name)
                                    }
                                }
                            ))
                        }
                    }
                })
            }

            bFinishSensor.create(nx, 449f, 50f, 50f)
            nx += 44 + 50
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun generateGreenCoin() {
        listOf(bList9GreenCoin, bList10GreenCoin).flatten().apply {
            onEach { coin ->
                coin.getActor()?.animHideCoin()
                coin.setNoneId()
            }
            shuffled().take((15..30).random()).onEach { coin ->
                coin.setValue((1..10).random())
                coin.getActor()?.animStart()
                coin.setOriginalId()
            }
        }
    }

}