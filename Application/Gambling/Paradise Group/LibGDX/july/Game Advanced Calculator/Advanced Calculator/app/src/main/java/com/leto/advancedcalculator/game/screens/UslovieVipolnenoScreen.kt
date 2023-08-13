package com.leto.advancedcalculator.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.leto.advancedcalculator.NewVebkaZaebcaActivity
import com.leto.advancedcalculator.game.actors.ImageGif
import com.leto.advancedcalculator.game.actors.checkbox.ACheckBox
import com.leto.advancedcalculator.game.actors.checkbox.ACheckBoxStyle
import com.leto.advancedcalculator.game.game
import com.leto.advancedcalculator.game.manager.GameDataStoreManager
import com.leto.advancedcalculator.game.manager.NavigationManager
import com.leto.advancedcalculator.game.manager.SpriteManager
import com.leto.advancedcalculator.game.utils.actor.animHide
import com.leto.advancedcalculator.game.utils.actor.animShow
import com.leto.advancedcalculator.game.utils.actor.disable
import com.leto.advancedcalculator.game.utils.actor.enable
import com.leto.advancedcalculator.game.utils.actor.setOnClickListener
import com.leto.advancedcalculator.game.utils.advanced.AdvancedScreen
import com.leto.advancedcalculator.game.utils.advanced.AdvancedStage
import com.leto.advancedcalculator.game.utils.runGDX
import kotlinx.coroutines.launch


val pariz   get() =  "https://fornoviherreromarta.github.io/Smart_Converter/pdfkdffdkdf"



val tatarka get() = "https://fornoviherreromarta.github.io/Smart_Converter/tkfgksmartfjgkjg"

class UslovieVipolnenoScreen: AdvancedScreen() {

    private val alibabaImg = Image(SpriteManager.GameRegion.ALIBABA.region)
    private val pasasaBox    = ACheckBox(ACheckBoxStyle.bb)
    private val pokprivetBtn    = Actor()

    private val loader = ImageGif(SpriteManager.ListRegion.LOADER.regionList)
    private val text   = Image(SpriteManager.SRegion.TEXT.region)



    private var inDaNeZiA = false


    override fun AdvancedStage.addActorsOnStageUI() {
        addAllAktos()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAllAktos() {
        addActor(loader)
        loader.setBounds(256f, 758f, 267f, 244f)
        addActor(text)
        text.setBounds(137f, 599f, 505f, 138f)
        // Panel
        addActor(alibabaImg)
        alibabaImg.setBounds(48f, 94f,686f, 244f)
        // Box
        addActor(pasasaBox)
        pasasaBox.apply {
            setBounds(48f, 275f,64f, 62f)
            setOnCheckListener {
                inDaNeZiA = it
                if (inDaNeZiA) pokprivetBtn.enable() else pokprivetBtn.disable()
            }
        }
        // Button
        addActor(pokprivetBtn)
        pokprivetBtn.apply {
            setBounds(48f, 94f,683f, 121f)
            disable()
            setOnClickListener {
                if (inDaNeZiA) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.ChtoEto.update { true }
                        stageUI.root.animHide(0.4f) { runGDX { NavigationManager.navigate(LetaSonceJaraaJaRabotkayouScreen()) } }
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
            setBounds(561f, 304f, 132f, 60f)
            setOnClickListener { openPrivacy() }
        }
        para2.apply {
            setBounds(128f, 268f, 257f, 60f)
            setOnClickListener { openPrivacy() }
        }
        tara.apply {
            setBounds(410f, 238f, 283f, 60f)
            setOnClickListener { openTerms() }
        }
        tara2.apply {
            setBounds(127f, 219f, 263f, 40f)
            setOnClickListener { openTerms() }
        }
    }

    private fun openPrivacy() {
        NewVebkaZaebcaActivity.webViewURL = pariz
        game.activity.webViewFragment.showAndOpenUrl()
    }

    private fun openTerms() {
        NewVebkaZaebcaActivity.webViewURL = tatarka
        game.activity.webViewFragment.showAndOpenUrl()
    }

}