package com.prochenkoa.businessplanner.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.prochenkoa.businessplanner.game.actors.VerList
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBox
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBoxGroup
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBoxStyle
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.manager.SpriteManager
import com.prochenkoa.businessplanner.game.utils.actor.animHide
import com.prochenkoa.businessplanner.game.utils.actor.animShow
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedStage

class RatingScreen: AdvancedScreen() {

    private val ratingImg = Image(SpriteManager.GameRegion.RETIG.region)
    private val cbMes = ACheckBox(ACheckBoxStyle.mesac)
    private val cbyea = ACheckBox(ACheckBoxStyle.god)
    private val cbVse = ACheckBox(ACheckBoxStyle.vse)
    private val Andrey_Listok_Zapolni = VerList()

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Andrey_Listok_Zapolni)
        Andrey_Listok_Zapolni.setBounds(41f, 0f, 657f, 1362f)
        Andrey_Listok_Zapolni.block = {
            stageUI.root.animHide(0.5f) { NavigationManager.navigate(NazadScreen(), RatingScreen()) }
        }

        addRetig()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addRetig() {
        addActor(ratingImg)
        ratingImg.setBounds(0f, 1361f, 739f, 281f)
        val back = Actor()
        addActor(back)
        back.setBounds(41f, 1534f,49f, 49f)
        val cbekaG = ACheckBoxGroup()
        addActors(cbMes, cbyea, cbVse)
        cbMes.apply {
            setBounds(41f, 1362f, 124f, 81f)
            checkBoxGroup = cbekaG
            setOnCheckListener {
                if (it) metodGdeAndreyRabotaet()
            }
        }
        cbyea.apply {
            setBounds(297f, 1362f, 88f, 82f)
            checkBoxGroup = cbekaG
            setOnCheckListener {
                if (it) metodGdeAndreyRabotaet()
            }
        }
        cbVse.apply {
            setBounds(519f, 1362f, 178f, 82f)
            checkBoxGroup = cbekaG
            check()
            setOnCheckListener {
                if (it) metodGdeAndreyRabotaet()
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun metodGdeAndreyRabotaet() {
        Andrey_Listok_Zapolni.idiPogulaySinok()
    }

}