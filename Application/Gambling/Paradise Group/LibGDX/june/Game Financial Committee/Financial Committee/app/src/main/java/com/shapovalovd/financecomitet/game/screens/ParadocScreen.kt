package com.shapovalovd.financecomitet.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.shapovalovd.financecomitet.game.actors.button.AButton
import com.shapovalovd.financecomitet.game.actors.button.AButtonStyle
import com.shapovalovd.financecomitet.game.actors.checkbox.ACheckBox
import com.shapovalovd.financecomitet.game.actors.checkbox.ACheckBoxStyle
import com.shapovalovd.financecomitet.game.game
import com.shapovalovd.financecomitet.game.manager.GameDataStoreManager
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.actor.disable
import com.shapovalovd.financecomitet.game.utils.actor.setBounds
import com.shapovalovd.financecomitet.game.utils.actor.setOnClickListener
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedScreen
import com.shapovalovd.financecomitet.game.utils.runGDX
import kotlinx.coroutines.launch
import com.shapovalovd.financecomitet.game.utils.Layout.Privacy as LP


class ParadocScreen: AdvancedScreen() {

    private val rivacyImage   = Image(SpriteManager.GameRegion.LUDKA.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = AButton(AButtonStyle.sw)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.huy)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        //setBackgrounds(SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
        super.show()
    }

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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://dmitrijsapavalov43.github.io/Financecomitet/pdkdkf"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://dmitrijsapavalov43.github.io/Financecomitet/tkfkgjf"))) }
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
                        runGDX { NavigationManager.navigate(StartScreen()) }
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