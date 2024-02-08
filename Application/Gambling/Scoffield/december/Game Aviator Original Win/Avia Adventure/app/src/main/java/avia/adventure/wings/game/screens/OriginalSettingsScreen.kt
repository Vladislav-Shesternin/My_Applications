package avia.adventure.wings.game.screens

import avia.adventure.wings.game.LibGDXGame
import avia.adventure.wings.game.actors.ASettingsGroup
import avia.adventure.wings.game.actors.button.AButton
import avia.adventure.wings.game.actors.checkbox.ACheckBox
import avia.adventure.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.adventure.wings.game.utils.actor.animHide
import avia.adventure.wings.game.utils.actor.animShow
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedScreen
import avia.adventure.wings.game.utils.advanced.AdvancedStage
import avia.adventure.wings.game.utils.region

class OriginalSettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.MainBackground.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addSettings()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@OriginalSettingsScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(22f, 1281f, 99f, 99f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@OriginalSettingsScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(520f, 1281f, 98f, 98f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addSettings() {
        val settings = ASettingsGroup(this@OriginalSettingsScreen)
        addActor(settings)
        settings.setBounds(17f, 236f, 614f, 929f)
    }

}