package com.balstar.linecomedian.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.balstar.linecomedian.game.LibGDXGame
import com.balstar.linecomedian.game.utils.TIME_ANIM
import com.balstar.linecomedian.game.utils.actor.animHide
import com.balstar.linecomedian.game.utils.actor.animShow
import com.balstar.linecomedian.game.utils.advanced.AdvancedScreen
import com.balstar.linecomedian.game.utils.advanced.AdvancedStage
import com.balstar.linecomedian.game.utils.region

class PinkRulesScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val panelImg = Image(game.allAssets.rules)

    override fun show() {
        stageUI.root.animHide(TIME_ANIM)
        setBackBackground(game.startAssets._3.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActors(panelImg)
        panelImg.setBounds(118f, 344f, 845f, 1362f)
    }

}