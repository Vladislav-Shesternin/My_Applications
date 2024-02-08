package avia.adventure.wings.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import avia.adventure.wings.game.LibGDXGame
import avia.adventure.wings.game.actors.checkbox.ACheckBox
import avia.adventure.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.adventure.wings.game.utils.actor.animHide
import avia.adventure.wings.game.utils.actor.animShow
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedScreen
import avia.adventure.wings.game.utils.advanced.AdvancedStage
import avia.adventure.wings.game.utils.region

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