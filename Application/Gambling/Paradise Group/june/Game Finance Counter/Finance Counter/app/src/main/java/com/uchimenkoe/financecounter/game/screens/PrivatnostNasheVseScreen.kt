package com.uchimenkoe.financecounter.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.uchimenkoe.financecounter.game.actors.button.AButton
import com.uchimenkoe.financecounter.game.actors.button.AButtonStyle
import com.uchimenkoe.financecounter.game.actors.checkbox.ACheckBox
import com.uchimenkoe.financecounter.game.actors.checkbox.ACheckBoxStyle
import com.uchimenkoe.financecounter.game.game
import com.uchimenkoe.financecounter.game.manager.GameDataStoreManager
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.utils.actor.disable
import com.uchimenkoe.financecounter.game.utils.actor.setBounds
import com.uchimenkoe.financecounter.game.utils.actor.setOnClickListener
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGroup
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedScreen
import com.uchimenkoe.financecounter.game.utils.runGDX
import kotlinx.coroutines.launch
import com.uchimenkoe.financecounter.game.utils.Layout.Privacy as LP


class PrivatnostNasheVseScreen: AdvancedScreen() {

    private val rivacyImage   = Image(SpriteManager.GameRegion.PRADSIK.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = AButton(AButtonStyle.soglasie)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.galochka)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

//    override fun show() {
//        //setBackgrounds(SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
//        super.show()
//    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addButtons()
        addPanel()
        addAgreeButton()
        addCbox()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(rivacyImage)
        rivacyImage.apply {
            setBounds(LP.panel)
            disable()
        }
    }

    private fun AdvancedGroup.addButtons() {
        addActors(privacyButton, termsButton)
        privacyButton.apply {
            setBounds(LP.privacy)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://andrijsicuk.github.io/FinanceCounter/posdosdsd"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://andrijsicuk.github.io/FinanceCounter/tkfkdjfdkf"))) }
        }
    }

    private fun AdvancedGroup.addCbox() {
        addActor(privacyCBox)
        privacyCBox.apply {
            setBounds(LP.cBox)

            setOnCheckListener {
                isAgree = it
                if (isAgree) agreeButton.enable() else agreeButton.disable()
            }
        }
    }

    private fun AdvancedGroup.addAgreeButton() {
        addActor(agreeButton)
        agreeButton.apply {
            setBounds(LP.btn)
            disable()

            setOnClickListener {
                if (isAgree) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        runGDX { NavigationManager.navigate(CalculatorScreen()) }
                    }
                }
            }
        }

//        addActor(agreeLabel)
//        agreeLabel.apply {
//            setBounds(LP.btn)
//            setAlignment(Align.center)
//            disable()
//        }
    }

}