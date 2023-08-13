package com.lakalutic.statisticsmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.lakalutic.statisticsmanager.game.actors.HorizontalList_Rect
import com.lakalutic.statisticsmanager.game.actors.VerticalList_Colors
import com.lakalutic.statisticsmanager.game.actors.VerticalList_Items
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.manager.SpriteManager
import com.lakalutic.statisticsmanager.game.utils.actor.animHide
import com.lakalutic.statisticsmanager.game.utils.actor.animShow
import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedScreen
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedStage

class DobroeYtroScreen: AdvancedScreen() {

    private val dobroeYtoImg = Image(SpriteManager.GameRegion.DOBROE_UTRECHKO.region)
    private val moiZadachImg = Image(SpriteManager.GameRegion.MOI_ZADACHI.region)
    private val horizRect    = HorizontalList_Rect()
    private val verticItms   = VerticalList_Colors()
    private val menuImg      = Image(SpriteManager.GameRegion.MEN_HOMIK.region)
    private val actRect = Actor()
    private val actVert = Actor()

    override fun AdvancedStage.addActorsOnStageUI() {
        addNaglaDevka()
        addMenuka()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addNaglaDevka() {
        addActor(dobroeYtoImg)
        dobroeYtoImg.setBounds(26f, 1165f,720f, 416f)
        addActor(moiZadachImg)
        moiZadachImg.setBounds(26f, 700f,720f, 35f)
        addActor(horizRect)
        horizRect.setBounds(0f, 833f,773f, 295f)
        addActor(verticItms)
        verticItms.setBounds(26f, 112f,721f, 551f)
        addActors(actRect, actVert)
        actRect.apply {
            setBounds(625f, 1168f, 121f, 27f)
            setOnClickListener { horizRect.update() }
        }
        actVert.apply {
            setBounds(625f, 703f, 121f, 27f)
            setOnClickListener { verticItms.update() }
        }

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(6f, 1531f, 63f, 65f)
            setOnClickListener { NavigationManager.back() }
        }

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
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(SVozvratomDrugScreen()) } }
        }
    }

}