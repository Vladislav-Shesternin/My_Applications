package aviator.original.win.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import aviator.original.win.game.LibGDXGame
import aviator.original.win.game.actors.checkbox.ACheckBox
import aviator.original.win.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.win.game.utils.actor.animHide
import aviator.original.win.game.utils.actor.animShow
import aviator.original.win.game.utils.actor.setOnClickListener
import aviator.original.win.game.utils.advanced.AdvancedScreen
import aviator.original.win.game.utils.advanced.AdvancedStage
import aviator.original.win.game.utils.region

class OriginalMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.MainBackground.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusic()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@OriginalMenuScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(520f, 1281f, 98f, 98f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addMenu() {
        val menuBar = Image(game.gameAssets.Menu)
        addActor(menuBar)
        menuBar.setBounds(17f, 236f, 614f, 929f)

        val names = listOf(
            OriginalGameScreen::class.java.name,
            OriginalShopScreen::class.java.name,
            OriginalRulesScreen::class.java.name,
            OriginalSettingsScreen::class.java.name,
            "exit",
        )

        var ny = 877f

        names.onEach { sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(96f, ny, 454f, 85f)
            ny -= 22f+85f

            btn.setOnClickListener(game.soundUtil) { navigateGo(sName) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(sName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (sName=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(sName, OriginalMenuScreen::class.java.name)
        }
    }


}