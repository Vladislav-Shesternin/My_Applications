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

class FTWMenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val FTWImg   = Image(game.allAssets.FTW)
    private val panelImg = Image(game.allAssets.menu)

    override fun show() {
        //stageUI.root.animHide()
        setBackBackground(game.loaderAssets.FTWBackground.region)
        super.show()
        //stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addFTWImg()
        addPanelImg()
        addBtns()
        addExit()
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

    private fun AdvancedStage.addBtns() {
        var ny = 727f

        listOf(
            FTWMenuScreen::class.java.name,
            FTWRulesScreen::class.java.name,
            FTWSettingsScreen::class.java.name,
        ).onEach { sn ->
            val a = Actor()
            addActor(a)
            a.setBounds(717f, ny, 538f, 152f)
            ny -= 30+152

            a.setOnClickListener(game.soundUtil) {
                game.navigationManager.navigate(sn, FTWMenuScreen::class.java.name)
            }
        }

    }

    private fun AdvancedStage.addExit() {
        val exit = Actor()
        addActor(exit)
        exit. setBounds(717f, 178f, 538f, 152f)
        exit.setOnClickListener { game.navigationManager.exit() }
    }

}