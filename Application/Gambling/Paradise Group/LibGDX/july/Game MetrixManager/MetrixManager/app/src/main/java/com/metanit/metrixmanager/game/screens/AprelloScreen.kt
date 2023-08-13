package com.metanit.metrixmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.metanit.metrixmanager.game.actors.DerbiNaVij
import com.metanit.metrixmanager.game.actors.checkbox.ACheckBox
import com.metanit.metrixmanager.game.actors.checkbox.ACheckBoxGroup
import com.metanit.metrixmanager.game.actors.checkbox.ACheckBoxStyle
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.utils.actor.animHide
import com.metanit.metrixmanager.game.utils.actor.animShow
import com.metanit.metrixmanager.game.utils.actor.disable
import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
import com.metanit.metrixmanager.game.utils.advanced.AdvancedScreen
import com.metanit.metrixmanager.game.utils.advanced.AdvancedStage

class AprelloScreen: AdvancedScreen() {

    private val mayImg = Image(SpriteManager.GamePlay.may.region).apply { disable() }
    private val cbGrrip = List(4) { ACheckBox(ACheckBoxStyle.doru) }
    private val randimka = Image(SpriteManager.Bazuki.GAGI.regionList.random())
    private val derbNaVjk = DerbiNaVij()

    override fun AdvancedStage.addActorsOnStageUI() {
        addAsdaGugliky()
        addMENUFA()
        root.animShow(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAsdaGugliky() {
        val g = ACheckBoxGroup()
        var anx = -31f
        cbGrrip.onEach {
            addActor(it)
            it.checkBoxGroup = g
            it.setBounds(anx, 894f, 253f, 347f)
            anx += 171f

            it.setOnCheckListener { randimka.drawable = TextureRegionDrawable(SpriteManager.Bazuki.GAGI.regionList.random()) }
        }
        addActor(derbNaVjk)
        derbNaVjk.setBounds(201f, 153f,474f, 376f)
        derbNaVjk.block = { stageUI.root.animHide(0.7f) {NavigationManager.navigate(DetaliZadachiVashoiScreen(), AprelloScreen())} }
        cbGrrip[1].check()
        addActor(mayImg)
        mayImg.setBounds(0f, 0f,704f, 1415f)
        val bk = Actor()
        addActor(bk)
        bk.apply {
            setBounds(45f, 1309f, 105f, 105f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.back() } }
        }
        addActor(randimka)
        randimka.setBounds(201f, 592f,473f, 174f)
        randimka.setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(DetaliZadachiVashoiScreen(), AprelloScreen())} }

    }

    private fun AdvancedStage.addMENUFA() {
        val a = Actor()
        val b = Actor()
        val v = Actor()
        val g = Actor()
        addActors(a,b,v,g)
        a.apply {
            setBounds(39f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.navigate(PrivetSergioScreen(), AprelloScreen()) } }
        }
        b.apply {
            setBounds(219f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {} }
        }
        v.apply {
            setBounds(400f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(DetaliZadachiVashoiScreen(), AprelloScreen())} }
        }
        g.apply {
            setBounds(580f, 40f, 87f, 87f)
            setOnClickListener { stageUI.root.animHide(0.7f) {NavigationManager.navigate(OChemPoetKukushkaScreen(), PrivetSergioScreen())} }
        }
    }

}