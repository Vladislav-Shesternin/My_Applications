package com.lakalutic.statisticsmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.lakalutic.statisticsmanager.game.actors.VerticalList_Items
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.manager.SpriteManager
import com.lakalutic.statisticsmanager.game.utils.actor.animHide
import com.lakalutic.statisticsmanager.game.utils.actor.animShow
import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedScreen
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedStage

class SVozvratomDrugScreen: AdvancedScreen() {

    private val vozvratImg = Image(SpriteManager.GameRegion.SVOZVRATOM.region)
    private val verticItms = VerticalList_Items()
    private val menuImg    = Image(SpriteManager.GameRegion.MEN_HOMIK.region)
    private val addActor   = Actor()


    override fun AdvancedStage.addActorsOnStageUI() {
        addNaglaDevka()
        addMenuka()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addNaglaDevka() {
        addActor(vozvratImg)
        vozvratImg.setBounds(26f, 627f,720f, 956f)
        addActor(verticItms)
        verticItms.setBounds(26f, 127f,721f, 470f)
        addActor(addActor)
        addActor.setBounds(601f, 630f,144f, 27f)
        addActor.setOnClickListener { verticItms.update() }
    }

    private fun AdvancedStage.addMenuka() {
        addActor(menuImg)
        menuImg.setBounds(0f, 0f,773f, 186f)

        val actorDom = Actor()
        val actorCal = Actor()
        val actorLin = Actor()
        val actorPro = Actor()
        val actorPLS = Actor()
        addActors(actorDom, actorCal, actorLin, actorPro, actorPLS)
        actorDom.apply {
            setBounds(66f, 37f, 58f, 56f)
            setOnClickListener { }
        }
        actorCal.apply {
            setBounds(210f, 37f, 58f, 56f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(DeatelnostScreen(), SVozvratomDrugScreen()) } }
        }
        actorLin.apply {
            setBounds(514f, 37f, 58f, 56f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(DetaliZadachiScreen(), SVozvratomDrugScreen()) } }
        }
        actorPro.apply {
            setBounds(649f, 37f, 58f, 56f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(HelloVladimirScreen(), SVozvratomDrugScreen()) } }
        }
        actorPLS.apply {
            setBounds(335f, 79f, 105f, 105f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(DobroeYtroScreen(), SVozvratomDrugScreen()) } }
        }
    }

}