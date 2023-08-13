package com.metanit.metrixmanager.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.metanit.metrixmanager.R
import com.metanit.metrixmanager.game.game
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.oranged
import com.metanit.metrixmanager.game.utils.GameColor
import com.metanit.metrixmanager.game.utils.actor.animHide
import com.metanit.metrixmanager.game.utils.actor.animShow
import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
import com.metanit.metrixmanager.game.utils.advanced.AdvancedScreen
import com.metanit.metrixmanager.game.utils.advanced.AdvancedStage

class PrivetSergioScreen: AdvancedScreen() {

    private val adraImg = Image(SpriteManager.GamePlay.privetik_vlad.region)


    override fun AdvancedStage.addActorsOnStageUI() {
        oranged = Color.WHITE
        game.activity.setNavigationBarColor(R.color.white)
        addAsdaGugliky()
        addMENUFA()
        root.animShow(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAsdaGugliky() {
        addActor(adraImg)
        adraImg.setBounds(0f, 0f,704f, 1413f)
    }

    private fun AdvancedStage.addMENUFA() {
        val a = Actor()
        val b = Actor()
        val v = Actor()
        val g = Actor()
        addActors(a,b,v,g)
        a.apply {
            setBounds(39f, 40f, 87f, 87f)
            //setOnClickListener { stageUI.root.animHide(0.7f) {} }
        }
        b.apply {
            setBounds(219f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(AprelloScreen(), PrivetSergioScreen())} }
        }
        v.apply {
            setBounds(400f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(DetaliZadachiVashoiScreen(), PrivetSergioScreen())} }
        }
        g.apply {
            setBounds(580f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(OChemPoetKukushkaScreen(), PrivetSergioScreen())} }
        }
    }

}