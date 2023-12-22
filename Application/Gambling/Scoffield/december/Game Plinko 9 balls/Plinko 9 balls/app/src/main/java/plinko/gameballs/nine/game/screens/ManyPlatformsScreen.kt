package plinko.gameballs.nine.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import plinko.gameballs.nine.game.LibGDXGame
import plinko.gameballs.nine.game.actors.checkbox.ACheckBox
import plinko.gameballs.nine.game.box2d.AbstractBody
import plinko.gameballs.nine.game.box2d.BodyId
import plinko.gameballs.nine.game.box2d.WorldUtil
import plinko.gameballs.nine.game.box2d.bodies.BBall
import plinko.gameballs.nine.game.box2d.bodies.BPlatform
import plinko.gameballs.nine.game.box2d.bodies.BRect
import plinko.gameballs.nine.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.gameballs.nine.game.utils.actor.animHide
import plinko.gameballs.nine.game.utils.actor.animShow
import plinko.gameballs.nine.game.utils.actor.setOnClickListener
import plinko.gameballs.nine.game.utils.advanced.AdvancedBox2dScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedStage
import plinko.gameballs.nine.game.utils.font.FontParameter
import plinko.gameballs.nine.game.utils.region
import plinko.gameballs.nine.game.utils.runGDX
import plinko.gameballs.nine.game.utils.toB2

class ManyPlatformsScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    private val fontParams = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + ",").setSize(68)
    private val font       = fontGeneratorInterSemiBold.generateFont(fontParams)

    // Actor
    private val lbl = Label("0", Label.LabelStyle(font, Color.WHITE))

    // Field
    private var counter = 0f

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.B_GAME.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addSound()
        addLbl()

        createB_Platform()
        createB_Rect()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA) {
            createB_Ball()
        }

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBack() {
        val tetra = Image(game.gameAssets.TETRA)
        addActor(tetra)
        tetra.setBounds(55f, 1731f, 150f, 150f)
        tetra.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.back()
            }
        }
    }

    private fun AdvancedStage.addSound() {
        val sound = ACheckBox(this@ManyPlatformsScreen, ACheckBox.Static.Type.SOUND)
        addActor(sound)
        sound.setBounds(875f, 1731f, 150f, 150f)

        if (game.soundUtil.isPause) sound.check(false)

        sound.setOnCheckListener { game.soundUtil.isPause = it }
    }

    private fun AdvancedStage.addLbl() {
        addActor(lbl)
        lbl.apply {
            setBounds(379f, 132f, 349f, 89f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Ball() {
        val ballFlow = MutableSharedFlow<BBall>(replay = 20)

        repeat(20) {
            BBall(this).also { bBall ->
                bBall.id = BodyId.Game.BALL
                bBall.setNoneId()
                bBall.collisionList.addAll(arrayOf(
                    BodyId.Game.BALL,
                    BodyId.Game.PLATFORM,
                    BodyId.Game.RECT,
                ))

                bBall.renderBlockArray.add(AbstractBody.RenderBlock {
                    bBall.body?.let {
                        if (it.position.y <= 0f) {
                            if (bBall.atomicBoolean.getAndSet(false)) {
                                ballFlow.tryEmit(bBall)
                            }
                        }
                    }
                })

                bBall.beginContactBlockArray.add(AbstractBody.ContactBlock { bEnemy ->
                    when(bEnemy.id) {
                        BodyId.Game.RECT -> {
                            if (bBall.atomicBoolean.getAndSet(false)) {
                                ballFlow.tryEmit(bBall)
                                counter += (1..5).random() * ((7..50).random() / 10f)
                                lbl.setText(String.format("%.3f", counter).replace('.', ','))
                            }
                        }
                    }
                })

                bBall.bodyDef.gravityScale = 0f
                bBall.create(-80f, 0f, 40f, 40f)

                ballFlow.tryEmit(bBall)
            }
        }

        coroutine?.launch {
            ballFlow.collect { bBall ->
                runGDX {
                    bBall.body?.apply {
                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake      = false

                        setTransform(Vector2(540f, 1534f).toB2, 0f)

                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                runGDX {
                    bBall.setOriginalId()
                    bBall.atomicBoolean.set(true)
                }
                delay((200L..700L).random())
            }
        }
    }

    private fun createB_Platform() {
        val listFirstX = listOf(409f, 359f, 309f, 259f, 209f, 159f, 109f, 59f)
        var firstCount = 3
        var nx = 0f
        var ny = 1301f

        listFirstX.onEach { xxx ->
            nx  = xxx
            ny -= 102f

            repeat(firstCount) {
                BPlatform(this).also { bPlatform ->
                    bPlatform.id = BodyId.Game.PLATFORM
                    bPlatform.collisionList.add(BodyId.Game.BALL)

                    bPlatform.create(nx, ny, 57f, 57f)
                    nx += 103f
                }
            }
            firstCount++

        }
    }

    private fun createB_Rect() {
        var nx = 25f
        repeat(8) {
            BRect(this).also { bRect ->
                bRect.id = BodyId.Game.RECT
                bRect.collisionList.add(BodyId.Game.BALL)

                bRect.create(nx, 352f, 108f, 37f)
                nx += 132f
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

}