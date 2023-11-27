package plinko.games.mega.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import plinko.games.mega.game.LibGDXGame
import plinko.games.mega.game.actors.AWinScore
import plinko.games.mega.game.actors.AWinStar
import plinko.games.mega.game.actors.button.AButton
import plinko.games.mega.game.box2d.AbstractBody
import plinko.games.mega.game.box2d.BodyId
import plinko.games.mega.game.box2d.WorldUtil
import plinko.games.mega.game.box2d.bodies.BBall
import plinko.games.mega.game.box2d.bodies.BBig
import plinko.games.mega.game.box2d.bodies.BHorizontal
import plinko.games.mega.game.box2d.bodies.BPlatform
import plinko.games.mega.game.box2d.bodies.BStar
import plinko.games.mega.game.utils.Layout
import plinko.games.mega.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.games.mega.game.utils.actor.animHide
import plinko.games.mega.game.utils.actor.animShow
import plinko.games.mega.game.utils.actor.disable
import plinko.games.mega.game.utils.actor.setOnClickListener
import plinko.games.mega.game.utils.advanced.AdvancedBox2dScreen
import plinko.games.mega.game.utils.advanced.AdvancedStage
import plinko.games.mega.game.utils.region
import plinko.games.mega.game.utils.runGDX
import plinko.games.mega.util.log

class GameScreen_1(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val aTop  = Image(game.gameAssets.BH_TOP)
    private val aTop2 = Image(game.gameAssets.BLACK_HOLE)
    private val aPane = Image(game.gameAssets.PANEL)

    // Body
    private val bHor1     = BHorizontal(this)
    private val bHor2     = BHorizontal(this)
    private val bBotom    = BBig(this)
    private val bBall     = BBall(this)
    private val bPlatform = List(8) { BPlatform(this) }
    private val bStar     = List(3) { BStar(this) }
    private val winS      = AWinStar(this)

    // Fields
    private val countFlow = MutableStateFlow(0)

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

        addBHTop()
        addPanelStar()
        addMenu()
        addPause()
        addStart()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

        collectStar()
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

    private fun AdvancedStage.addWS() {
        addActor(winS)
//        winS.animHide()
        winS.setBounds(111f, 478f, 858f, 923f)
    }

    private fun AdvancedStage.addPanelStar() {
        addActor(aPane)
        aPane.setBounds(450f, 1817f, 208f, 74f)

    }

    private fun AdvancedStage.addMenu() {
        val palanet = AButton(this@GameScreen_1, AButton.Static.Type.MENU)
        addActor(palanet)
        palanet.setBounds(57f, 1780f, 124f, 106f)
        palanet.setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addPause() {
        val palanet = AButton(this@GameScreen_1, AButton.Static.Type.PAUSE)
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

    private fun AdvancedStage.addStart() {
        val palanet = Image(game.gameAssets.START)
        addActor(palanet)
        palanet.setBounds(418f, 25f, 243f, 128f)
        palanet.setOnClickListener(game.soundUtil) {
            palanet.disable()
            palanet.animHide(0.3f)

            runGDX {
                bBall.body?.apply {
                    gravityScale = 1f
                    applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                }
            }
        }
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

            create(0f, 0f, 1080f, 297f)
        }
    }

    private fun createB_Ball() {
        bBall.apply {
            id = BodyId.Game.BALL
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.BALL, BodyId.Game.STATIC, BodyId.Game.STAR))

            create((430..590).random().toFloat(), 1814f, 84f, 84f)
            body?.gravityScale = 0f

            beginContactBlockArray.add(AbstractBody.ContactBlock {
                when (it.id) {
                    BodyId.BORDERS -> coroutine?.launch {
                        delay(100)
                        log("destroy BALL")
                        runGDX {
                            destroy()

                            isWorldPause = true

                            val iii = Image(drawerUtil.getRegion(Color.valueOf("2D1F85").apply { a = 0.62f }))
                            iii.animHide()
                            stageUI.addActor(iii)
                            iii.setSize(WIDTH, HEIGHT)
                            iii.animShow(0.3f) {
                                stageUI.addWS()
                                winS.apply {
                                    restart.setOnClickListener(game.soundUtil) {
                                        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                                            game.navigationManager.navigate(GameScreen_1::class.java.name)
                                        }
                                    }
                                    next.setOnClickListener(game.soundUtil) {
                                        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                                            game.navigationManager.navigate( GameScreen_2::class.java.name)
                                        }
                                    }
                                }
                                winS.updateScore(countFlow.value)
//                                winS.animShow(0.5f)
                            }
                            if (levelBallIndex != -1) {
                                leveles[levelBallIndex] = countFlow.value
                                levelBallIndex = -1
                            }
                        }
                    }
                    BodyId.Game.STAR -> {
                        it.id = BodyId.NONE
                        it.actor?.animHide(0.2f)
                        countFlow.value++
                    }
                }
            })
        }
    }

    private fun createB_Platform() {
        val listok = Layout.Game.game1.random()
        val starto = listok.shuffled().take(3)
        bPlatform.onEachIndexed { index, platform ->
            platform.apply {
                id = BodyId.Game.STATIC
                collisionList.add(BodyId.Game.BALL)

                val l = listok[index]
                create(l.position(), l.size())
            }
        }

        bStar.onEachIndexed  { index,itr -> itr.apply {
            id = BodyId.Game.STAR
            collisionList.add(BodyId.Game.BALL)

            val l = starto[index].position().add(53f, 105f)
            create(l, Vector2(84f, 84f))

        } }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun collectStar() {
        coroutine?.launch {
            countFlow.collect {
                if (it > 0) { runGDX {
                    log("get Star $it")

                    val star = Image(game.gameAssets.STAR)
                    stageUI.addActor(star)
                    star.setBounds(Layout.Game.panelStars[it.dec()], 1814f, 84f, 84f)

                } }
            }
        }
    }

}