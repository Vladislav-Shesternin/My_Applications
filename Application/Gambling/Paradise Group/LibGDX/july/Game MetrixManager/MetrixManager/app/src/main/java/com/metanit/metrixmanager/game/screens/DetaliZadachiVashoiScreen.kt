package com.metanit.metrixmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.metanit.metrixmanager.game.actors.Cbghshjd
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.utils.actor.animHide
import com.metanit.metrixmanager.game.utils.actor.animShow
import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
import com.metanit.metrixmanager.game.utils.advanced.AdvancedScreen
import com.metanit.metrixmanager.game.utils.advanced.AdvancedStage

class DetaliZadachiVashoiScreen: AdvancedScreen() {

    private val shoImg = Image(SpriteManager.GamePlay.SHO_SKAZAV_ALLO.region)
    private val cbHelos = Cbghshjd()

    override fun AdvancedStage.addActorsOnStageUI() {
        addAsdaGugliky()
        addMENUFA()
        root.animShow(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAsdaGugliky() {
        addActor(shoImg)
        shoImg.setBounds(45f, 806f,642f, 609f)
        addActor(cbHelos)
        cbHelos.setBounds(35f, 148f,635f, 640f)
        val back = Actor()
        val imdg = Image(SpriteManager.GamePlay.A_ETO_U_NAS_MENU.region)
        addActors(back, imdg)
        back.apply {
            setBounds(45f, 1309f, 105f, 105f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.back() } }
        }
        imdg.setBounds(0f, 0f, 705f, 220f)
    }

    private fun AdvancedStage.addMENUFA() {
        val a = Actor()
        val b = Actor()
        val v = Actor()
        val g = Actor()
        addActors(a,b,v,g)
        a.apply {
            setBounds(39f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(PrivetSergioScreen(), DetaliZadachiVashoiScreen( ))} }
        }
        b.apply {
            setBounds(219f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(AprelloScreen(), DetaliZadachiVashoiScreen( ))} }
        }
        v.apply {
            setBounds(400f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {} }
        }
        g.apply {
            setBounds(580f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(OChemPoetKukushkaScreen(), PrivetSergioScreen())} }
        }
    }

}