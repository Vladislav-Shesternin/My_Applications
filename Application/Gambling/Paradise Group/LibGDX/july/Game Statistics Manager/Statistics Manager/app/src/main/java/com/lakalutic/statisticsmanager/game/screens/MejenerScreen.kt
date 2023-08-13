package com.lakalutic.statisticsmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.lakalutic.statisticsmanager.LambarginiActivity
import com.lakalutic.statisticsmanager.game.actors.checkbox.ACheckBox
import com.lakalutic.statisticsmanager.game.actors.checkbox.ACheckBoxStyle
import com.lakalutic.statisticsmanager.game.game
import com.lakalutic.statisticsmanager.game.manager.GameDataStoreManager
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.manager.SpriteManager
import com.lakalutic.statisticsmanager.game.utils.actor.animHide
import com.lakalutic.statisticsmanager.game.utils.actor.animShow
import com.lakalutic.statisticsmanager.game.utils.actor.disable
import com.lakalutic.statisticsmanager.game.utils.actor.enable
import com.lakalutic.statisticsmanager.game.utils.actor.setOnClickListener
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedScreen
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedStage
import com.lakalutic.statisticsmanager.game.utils.runGDX
import kotlinx.coroutines.launch


val pasternak get() = "https://fornoviherreromarta.github.io/Smart_Converter/pdfkdffdkdf"
val tatarenok get() = "https://fornoviherreromarta.github.io/Smart_Converter/tkfgksmartfjgkjg"

class MejenerScreen: AdvancedScreen() {

    private val lordImg = Image(SpriteManager.GameRegion.LORD.region)
    private val mendImg = Image(SpriteManager.GameRegion.MENEDJER.region)
    private val prodImg = Image(SpriteManager.GameRegion.PRODOLJIT.region)
    private val Box     = ACheckBox(ACheckBoxStyle.kek)
    private val Btn     = Actor()

    private var pisok = false


    override fun AdvancedStage.addActorsOnStageUI() {
        addBetTrip()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addBetTrip() {
        addActor(lordImg)
        lordImg.setBounds(270f, 1058f, 229f, 229f)
        lordImg.setOrigin(Align.center)
        lordImg.addAction(Actions.forever(Actions.rotateBy(360f, 2f)))
        addActor(mendImg)
        mendImg.setBounds(147f, 979f, 475f, 50f)
        addActor(prodImg)
        prodImg.setBounds(80f, 56f, 610f, 218f)
        // Box
        addActor(Box)
        Box.apply {
            setBounds(80f, 218f,56f, 56f)
            setOnCheckListener {
                pisok = it
                if (pisok) Btn.enable() else Btn.disable()
            }
        }
        // Button
        addActor(Btn)
        Btn.apply {
            setBounds(80f, 56f,610f, 108f)
            disable()
            setOnClickListener {
                if (pisok) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Lotos.update { true }
                        stageUI.root.animHide(0.5f) { runGDX { NavigationManager.navigate(SVozvratomDrugScreen()) } }
                    }
                }
            }
        }

        val para  = Actor()
        val para2 = Actor()
        val tara  = Actor()
        val tara2 = Actor()
        addActors(para, para2, tara, tara2)
        para.apply {
            setBounds(523f, 246f, 127f, 43f)
            setOnClickListener { openPrivacy() }
        }
        para2.apply {
            setBounds(136f, 212f, 229f, 43f)
            setOnClickListener { openPrivacy() }
        }
        tara.apply {
            setBounds(387f, 199f, 257f, 43f)
            setOnClickListener { openTerms() }
        }
        tara2.apply {
            setBounds(117f, 167f, 257f, 43f)
            setOnClickListener { openTerms() }
        }
    }

    private fun openPrivacy() {
        LambarginiActivity.webViewURL = pasternak
        game.activity.webViewFragment.showAndOpenUrl()
    }

    private fun openTerms() {
        LambarginiActivity.webViewURL = tatarenok
        game.activity.webViewFragment.showAndOpenUrl()
    }

}