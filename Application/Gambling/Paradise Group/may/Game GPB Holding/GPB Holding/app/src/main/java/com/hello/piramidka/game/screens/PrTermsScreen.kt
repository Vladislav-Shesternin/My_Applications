package com.hello.piramidka.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hello.piramidka.game.actors.button.AButton
import com.hello.piramidka.game.actors.button.AButtonStyle
import com.hello.piramidka.game.actors.checkbox.ACheckBox
import com.hello.piramidka.game.actors.checkbox.ACheckBoxStyle
import com.hello.piramidka.game.game
import com.hello.piramidka.game.manager.FontTTFManager
import com.hello.piramidka.game.manager.GameDataStoreManager
import com.hello.piramidka.game.manager.NavigationManager
import com.hello.piramidka.game.manager.SpriteManager
import com.hello.piramidka.game.utils.GameColor
import com.hello.piramidka.game.utils.actor.disable
import com.hello.piramidka.game.utils.actor.setBounds
import com.hello.piramidka.game.utils.actor.setOnClickListener
import com.hello.piramidka.game.utils.advanced.AdvancedGroup
import com.hello.piramidka.game.utils.advanced.AdvancedScreen
import com.hello.piramidka.game.utils.runGDX
import kotlinx.coroutines.launch
import com.hello.piramidka.game.utils.Layout.Privacy as LP


class PrTermsScreen: AdvancedScreen() {

    private val panelImage    = Image(SpriteManager.GameRegion.PR_TR_TEXT.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.prterms)
    private val agreeButton   = AButton(AButtonStyle.pbt)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false


    override fun AdvancedGroup.addActorsOnGroup() {
        addButtons()
        addPanel()
        addCbox()
        addAgreeButton()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(panelImage)
        panelImage.apply {
            setBounds(LP.panel)
            disable()
        }
    }

    private fun AdvancedGroup.addButtons() {
        addActors(privacyButton, termsButton)
        privacyButton.apply {
            setBounds(LP.privacy)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("ggg"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("ggg"))) }
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
                        runGDX { NavigationManager.navigate(MenuScreen()) }
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