package com.ukracc.finproject.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.ukracc.finproject.game.actors.button.AButton
import com.ukracc.finproject.game.actors.button.AButtonStyle
import com.ukracc.finproject.game.actors.checkbox.ACheckBox
import com.ukracc.finproject.game.actors.checkbox.ACheckBoxStyle
import com.ukracc.finproject.game.game
import com.ukracc.finproject.game.manager.GameDataStoreManager
import com.ukracc.finproject.game.manager.NavigationManager
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.utils.actor.disable
import com.ukracc.finproject.game.utils.actor.setBounds
import com.ukracc.finproject.game.utils.advanced.AdvancedGroup
import com.ukracc.finproject.game.utils.advanced.AdvancedScreen
import com.ukracc.finproject.game.utils.runGDX
import kotlinx.coroutines.launch
import com.ukracc.finproject.game.utils.Layout.Privacy as LP


class PrivacyScreen: AdvancedScreen() {

    private val panelImage    = Image(SpriteManager.GameRegion.PRIVACY_PANEL.region)
    private val privacyButton = AButton(AButtonStyle.pBtn)
    private val termsButton   = AButton(AButtonStyle.pBtn)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.privacy)
    private val agreeButton   = AButton(AButtonStyle.btn)

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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pavlokravcenko2.github.io/Finproject/pkfdkfkdf"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pavlokravcenko2.github.io/Finproject/trjjgfjg"))) }
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
    }

}