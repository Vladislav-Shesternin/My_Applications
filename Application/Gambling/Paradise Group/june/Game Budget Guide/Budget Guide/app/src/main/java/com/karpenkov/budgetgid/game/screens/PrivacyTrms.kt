package com.karpenkov.budgetgid.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.karpenkov.budgetgid.game.actors.checkbox.ACheckBox
import com.karpenkov.budgetgid.game.actors.checkbox.ACheckBoxStyle
import com.karpenkov.budgetgid.game.game
import com.karpenkov.budgetgid.game.manager.GameDataStoreManager
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.utils.actor.disable
import com.karpenkov.budgetgid.game.utils.actor.enable
import com.karpenkov.budgetgid.game.utils.actor.setBounds
import com.karpenkov.budgetgid.game.utils.actor.setOnClickListener
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedScreen
import com.karpenkov.budgetgid.game.utils.runGDX
import kotlinx.coroutines.launch
import com.karpenkov.budgetgid.game.utils.Layout.Privacy as LP


class PrivacyTrms: AdvancedScreen() {

    private val rivacyImage   = Image(SpriteManager.GameRegion.PIRIVVACTY.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = Actor()
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.privatry)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://karpenkovalentin009.github.io/Budgetgid/pdfdfdf"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://karpenkovalentin009.github.io/Budgetgid/tfgfkgfg"))) }
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
                        runGDX { NavigationManager.navigate(CurrencyConverter()) }
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