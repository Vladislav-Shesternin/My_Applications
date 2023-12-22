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

class MenuButtonsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.B_LIGHTNING.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAndFillActor(Image(game.splashAssets.B_LOADING))

        addMenu()
        addSound()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        val buttons = Image(game.gameAssets.BUTTONS)
        addActor(buttons)
        buttons.setBounds(221f, 248f, 639f, 1318f)

        val ids = listOf(
            ManyPlatformsScreen::class.java.name,
            SelectBallScreen::class.java.name,
            RulesEverythingScreen::class.java.name,
            SettingsPlayScreen::class.java.name,
            "OUT",
        )

        var ny = 1346f

        repeat(5) { index ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(221f, ny, 639f, 219f)
            ny -= 55f+219f

            btn.setOnClickListener(game.soundUtil) { navigateGo(ids[index]) }
        }
    }

    private fun AdvancedStage.addSound() {
        val sound = ACheckBox(this@MenuButtonsScreen, ACheckBox.Static.Type.SOUND)
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
            if (id=="OUT") game.navigationManager.exit()
            else game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}