package plinko.originalwin.com.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.originalwin.com.game.LibGDXGame
import plinko.originalwin.com.game.utils.TIME_ANIM_ALPHA
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.animShow
import plinko.originalwin.com.game.utils.actor.setOnClickListener
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedStage

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        val panel = Image(game.allAssets.SUPER_PLINKO)
        addActor(panel)
        panel.setBounds(0f, 240f, 1080f, 1440f)
    }

    private fun AdvancedStage.addMenu() {
        val play = Actor()
        addActor(play)
        play.setBounds(359f, 657f, 362f, 125f)

        play.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
            }
        }

    }

}