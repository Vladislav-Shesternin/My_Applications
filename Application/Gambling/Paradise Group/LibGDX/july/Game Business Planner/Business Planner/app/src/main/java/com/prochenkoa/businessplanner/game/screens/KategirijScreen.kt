package com.prochenkoa.businessplanner.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.manager.SpriteManager
import com.prochenkoa.businessplanner.game.utils.actor.animHide
import com.prochenkoa.businessplanner.game.utils.actor.animShow
import com.prochenkoa.businessplanner.game.utils.actor.setOnClickListener
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedStage

class KategirijScreen: AdvancedScreen() {

    private val kakaImg = Image(SpriteManager.Kedr.katka_v_dotku.region)


    override fun AdvancedStage.addActorsOnStageUI() {
        addKartinki()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addKartinki() {
        addActor(kakaImg)
        kakaImg.setBounds(0f, 466f,739f, 1176f)
        kakaImg.setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(RatingScreen(), KategirijScreen()) } }
    }

}