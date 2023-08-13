package com.lakalutic.statisticsmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.lakalutic.statisticsmanager.game.actors.HorizontalList_Rect
import com.lakalutic.statisticsmanager.game.actors.VerticalList_CBox
import com.lakalutic.statisticsmanager.game.actors.VerticalList_Colors
import com.lakalutic.statisticsmanager.game.actors.VerticalList_Items
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.manager.SpriteManager
import com.lakalutic.statisticsmanager.game.utils.actor.animHide
import com.lakalutic.statisticsmanager.game.utils.actor.animShow
import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedScreen
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedStage

class DetaliZadachiScreen: AdvancedScreen() {

    private val dobroeYtoImg = Image(SpriteManager.GameRegion.AGENSTVO.region)
    private val menuImg      = Image(SpriteManager.GameRegion.MEN_TRIGUGOLNIK.region)
    private val cblistkkkkxs = VerticalList_CBox()

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
        dobroeYtoImg.setBounds(26f, 996f,720f, 585f)
        addActor(cblistkkkkxs)
        cblistkkkkxs.setBounds(26f, 123f,720f, 836f)

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(13f, 1514f, 118f, 106f)
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
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(SVozvratomDrugScreen(), DetaliZadachiScreen()) } }
        }
        actorCal.apply {
            setBounds(210f, 37f, 58f, 56f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(DeatelnostScreen(), DetaliZadachiScreen()) } }
        }
        actorLin.apply {
            setBounds(514f, 37f, 58f, 56f)
            setOnClickListener { }
        }
        actorPro.apply {
            setBounds(649f, 37f, 58f, 56f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(HelloVladimirScreen(), DetaliZadachiScreen()) } }
        }
        actorPLS.apply {
            setBounds(335f, 79f, 105f, 105f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.navigate(DobroeYtroScreen(), DetaliZadachiScreen()) } }
        }
    }

}