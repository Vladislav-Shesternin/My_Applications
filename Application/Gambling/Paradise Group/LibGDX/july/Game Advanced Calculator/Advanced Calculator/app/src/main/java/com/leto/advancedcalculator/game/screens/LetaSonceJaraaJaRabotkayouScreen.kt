package com.leto.advancedcalculator.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.leto.advancedcalculator.game.manager.NavigationManager
import com.leto.advancedcalculator.game.manager.SpriteManager
import com.leto.advancedcalculator.game.tri_color
import com.leto.advancedcalculator.game.utils.GameColor
import com.leto.advancedcalculator.game.utils.actor.animHide
import com.leto.advancedcalculator.game.utils.actor.animShow
import com.leto.advancedcalculator.game.utils.actor.setOnClickListener
import com.leto.advancedcalculator.game.utils.advanced.AdvancedGroup
import com.leto.advancedcalculator.game.utils.advanced.AdvancedScreen
import com.leto.advancedcalculator.game.utils.advanced.AdvancedStage

class LetaSonceJaraaJaRabotkayouScreen: AdvancedScreen() {

    private val himanImg = Image(SpriteManager.GameRegion.HIMAN.region)
    private val btn = Actor()

    override fun AdvancedStage.addActorsOnStageUI() {
        tri_color = GameColor.seriy
        addAllAktos()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAllAktos() {
        addActor(himanImg)
        himanImg.setBounds(41f, 489f,710f, 1064f)
        addActor(btn)
        btn.setBounds(41f, 489f,710f, 824f)
        btn.setOnClickListener {
            root.animHide(0.5f) { NavigationManager.navigate(PoraNaRabotaArmenScreen(), LetaSonceJaraaJaRabotkayouScreen()) }
        }
    }

}