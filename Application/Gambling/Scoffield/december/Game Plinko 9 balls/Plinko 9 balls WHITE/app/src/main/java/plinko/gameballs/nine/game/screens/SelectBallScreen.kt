package plinko.gameballs.nine.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.gameballs.nine.game.LibGDXGame
import plinko.gameballs.nine.game.actors.checkbox.ACheckBox
import plinko.gameballs.nine.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.gameballs.nine.game.utils.actor.animHide
import plinko.gameballs.nine.game.utils.actor.animShow
import plinko.gameballs.nine.game.utils.actor.setOnClickListener
import plinko.gameballs.nine.game.utils.advanced.AdvancedScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedStage
import plinko.gameballs.nine.game.utils.region

var selectedBall: TextureRegion? = null

class SelectBallScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.B_LIGHTNING.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addBalls()
        addSound()

        val play = Actor()
        addActor(play)
        play.apply {
            setBounds(310f, 141f, 484f, 166f)
            setOnClickListener(game.soundUtil) { navigateGo(MenuButtonsScreen::class.java.name) }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBalls() {
        val ballImg = Image(game.gameAssets.BALL_PANEL)
        addActor(ballImg)
        ballImg.setBounds(221f, 1442f, 639f, 220f)

        var nx = 27f
        var ny = 919f

        game.gameAssets.BALLS.onEachIndexed { index, ballRegion ->
            addActor(Image(ballRegion).apply {
                setBounds(nx, ny, 285f, 285f)
                nx += 80f+285f
                if (index.inc() % 3 == 0) {
                    nx = 27f
                    ny -= 112f+285f
                }

                setOnClickListener(game.soundUtil) {
                    selectedBall = ballRegion
                    navigateGo(MenuButtonsScreen::class.java.name)
                }
            })
        }

    }

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
        val sound = ACheckBox(this@SelectBallScreen, ACheckBox.Static.Type.SOUND)
        addActor(sound)
        sound.setBounds(875f, 1731f, 150f, 150f)

        if (game.soundUtil.isPause) sound.check(false)

        sound.setOnCheckListener { game.soundUtil.isPause = it }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}