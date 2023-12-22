package plinko.gameballs.nine.game.screens

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

class RulesEverythingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.B_LIGHTNING.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addRulles()
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

    private fun AdvancedStage.addRulles() {
        val rulles = Image(game.gameAssets.RULLES)
        addActor(rulles)
        rulles.setBounds(93f, 141f, 951f, 1521f)
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
        val sound = ACheckBox(this@RulesEverythingScreen, ACheckBox.Static.Type.SOUND)
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