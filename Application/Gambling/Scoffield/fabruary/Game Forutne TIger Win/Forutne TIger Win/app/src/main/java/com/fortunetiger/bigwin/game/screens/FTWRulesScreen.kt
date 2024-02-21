package com.fortunetiger.bigwin.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.bigwin.game.LibGDXGame
import com.fortunetiger.bigwin.game.utils.TIME_ANIM_ALPHA
import com.fortunetiger.bigwin.game.utils.actor.animHide
import com.fortunetiger.bigwin.game.utils.actor.setOnClickListener
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedStage
import com.fortunetiger.bigwin.game.utils.region

class FTWRulesScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val FTWImg   = Image(game.allAssets.FTW)
    private val panelImg = Image(game.allAssets.rules)

    override fun show() {
        //stageUI.root.animHide()
        setBackBackground(game.loaderAssets.FTWBackground.region)
        super.show()
        //stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addFTWImg()
        addPanelImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addFTWImg() {
        addActor(FTWImg)
        FTWImg. setBounds(0f, 0f, 469f, 634f)
    }

    private fun AdvancedStage.addPanelImg() {
        addActor(panelImg)
        panelImg. setBounds(629f, 60f, 716f, 961f)
    }

}