package plinko.testyouluck.com.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.testyouluck.com.game.LibGDXGame
import plinko.testyouluck.com.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.testyouluck.com.game.utils.actor.animHide
import plinko.testyouluck.com.game.utils.actor.animShow
import plinko.testyouluck.com.game.utils.actor.setOnClickListener
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedStage
import plinko.testyouluck.com.game.utils.region

class PlinkoRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.PLINKO_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAndFillActor(Image(game.gameAssets.TOP_BOTTOM_BALLS))
        addBackMenu()
        addRules()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBackMenu() {
        val menuBtn = Image(game.gameAssets.MENU_BTN)
        addActor(menuBtn)
        menuBtn.setBounds(47f, 1785f, 113f, 103f)

        menuBtn.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addRules() {
        val rulesBar = Image(game.gameAssets.RULES_BAR)
        addActor(rulesBar)
        rulesBar.setBounds(143f, 333f, 795f, 1254f)
    }

}