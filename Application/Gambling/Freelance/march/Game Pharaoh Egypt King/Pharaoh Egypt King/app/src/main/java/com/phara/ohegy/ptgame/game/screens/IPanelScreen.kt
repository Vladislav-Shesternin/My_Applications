package com.phara.ohegy.ptgame.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.phara.ohegy.ptgame.game.LibGDXGame
import com.phara.ohegy.ptgame.game.utils.TIME_ANIM
import com.phara.ohegy.ptgame.game.utils.actor.animHide
import com.phara.ohegy.ptgame.game.utils.actor.setOnClickListener
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedScreen
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedStage
import com.phara.ohegy.ptgame.game.utils.region

abstract class IPanelScreen(final override val game: LibGDXGame, private val type: ScreenType) : AdvancedScreen() {

    private val panelImg = Image(getPanelRegionByScreenType())
    private val xActor   = Actor()

    override fun show() {
        setBackBackground(game.loaderAssets.piramida.region)
        super.show()
    }

    final override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addXActor()

        addActorsOnStage()
    }

    open fun AdvancedStage.addActorsOnStage() {}

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActor(panelImg)
        panelImg.setBounds(147f, 507f, 797f, 873f)
    }

    private fun AdvancedStage.addXActor() {
        addActor(xActor)
        xActor.setBounds(813f, 507f, 130f, 133f)
        xActor.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getPanelRegionByScreenType() = when(type) {
        ScreenType.MENU     -> game.allAssets.menu
        ScreenType.SETTINGS -> game.allAssets.settings
        ScreenType.RULES    -> game.allAssets.rules
    }

}

enum class ScreenType {
    MENU, SETTINGS, RULES
}