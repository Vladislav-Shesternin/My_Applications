package com.tsabekaa.finhelper.game.screens

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.actors.button.AButton
import com.tsabekaa.finhelper.game.actors.button.AButtonStyle
import com.tsabekaa.finhelper.game.actors.checkbox.ACheckBox
import com.tsabekaa.finhelper.game.actors.checkbox.ACheckBoxStyle
import com.tsabekaa.finhelper.game.game
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.GameDataStoreManager
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.utils.GameColor
import com.tsabekaa.finhelper.game.utils.actor.disable
import com.tsabekaa.finhelper.game.utils.actor.setBounds
import com.tsabekaa.finhelper.game.utils.actor.setOnClickListener
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedScreen
import com.tsabekaa.finhelper.game.utils.runGDX
import kotlinx.coroutines.launch
import com.tsabekaa.finhelper.game.utils.Layout.Privacy as LP


class PrivacyScreen: AdvancedScreen() {

    private val panelImage    = Image(SpriteManager.GameRegion.GREEN_PANEL.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.privacy)
    private val agreeButton   = AButton(AButtonStyle.btn)
    private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BoldFont.font_35.font, GameColor.blue))

    private var isAgree = false

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://cabekaartem467.github.io/Finhelper/pfdfdff"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://cabekaartem467.github.io/Finhelper/tfgfgg"))) }
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

        addActor(agreeLabel)
        agreeLabel.apply {
            setBounds(72f, 168f, 479f, 92f)
            setAlignment(Align.center)
            disable()
        }
    }

}