package com.phara.ohegy.ptgame.game.screens

import com.phara.ohegy.ptgame.game.LibGDXGame
import com.phara.ohegy.ptgame.game.actors.ASettings
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedStage

class SettingsScreen(ame: LibGDXGame) : IPanelScreen(ame, ScreenType.SETTINGS) {

    private val settings = ASettings(this)

    override fun AdvancedStage.addActorsOnStage() {
        addSetting()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addSetting() {
        addActor(settings)
        settings.setBounds(242f, 733f, 607f, 328f)
    }

}