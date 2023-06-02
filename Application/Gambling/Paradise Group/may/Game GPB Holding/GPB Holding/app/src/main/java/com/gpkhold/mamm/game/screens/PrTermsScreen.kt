package com.gpkhold.mamm.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gpkhold.mamm.game.actors.button.AButton
import com.gpkhold.mamm.game.actors.button.AButtonStyle
import com.gpkhold.mamm.game.actors.checkbox.ACheckBox
import com.gpkhold.mamm.game.actors.checkbox.ACheckBoxStyle
import com.gpkhold.mamm.game.game
import com.gpkhold.mamm.game.manager.GameDataStoreManager
import com.gpkhold.mamm.game.manager.NavigationManager
import com.gpkhold.mamm.game.manager.SpriteManager
import com.gpkhold.mamm.game.utils.actor.disable
import com.gpkhold.mamm.game.utils.actor.setBounds
import com.gpkhold.mamm.game.utils.actor.setOnClickListener
import com.gpkhold.mamm.game.utils.advanced.AdvancedGroup
import com.gpkhold.mamm.game.utils.advanced.AdvancedScreen
import com.gpkhold.mamm.game.utils.runGDX
import kotlinx.coroutines.launch
import com.gpkhold.mamm.game.utils.Layout.Privacy as LP


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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://bezhaskaleno21.github.io/Xolding/pdfdff"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://bezhaskaleno21.github.io/Xolding/tkfgkfgjf"))) }
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