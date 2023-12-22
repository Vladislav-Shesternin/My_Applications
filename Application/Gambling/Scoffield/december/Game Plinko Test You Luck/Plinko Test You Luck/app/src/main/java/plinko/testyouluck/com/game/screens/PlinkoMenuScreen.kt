package plinko.testyouluck.com.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.testyouluck.com.game.LibGDXGame
import plinko.testyouluck.com.game.actors.checkbox.ACheckBox
import plinko.testyouluck.com.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.testyouluck.com.game.utils.actor.animHide
import plinko.testyouluck.com.game.utils.actor.animShow
import plinko.testyouluck.com.game.utils.actor.setOnClickListener
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedStage
import plinko.testyouluck.com.game.utils.region

class PlinkoMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.PLINKO_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAndFillActor(Image(game.gameAssets.TOP_BOTTOM_BALLS))
        addMusic()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@PlinkoMenuScreen, ACheckBox.Static.Type.SOUND)
        addActor(music)
        music.setBounds(921f, 1785f, 113f, 103f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addMenu() {
        val menuBar = Image(game.gameAssets.MENU_BAR)
        addActor(menuBar)
        menuBar.setBounds(143f, 333f, 795f, 1254f)

        val names = listOf(
            PlinkoGameScreen::class.java.name,
            PlinkoShopScreen::class.java.name,
            PlinkoRulesScreen::class.java.name,
            PlinkoSettingsScreen::class.java.name,
            "exit",
        )

        var ny = 1143f

        names.onEach { sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(251f, ny, 600f, 129f)
            ny -= 54f+129f

            btn.setOnClickListener(game.soundUtil) { navigateGo(sName) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(sName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (sName=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(sName, PlinkoMenuScreen::class.java.name)
        }
    }


}