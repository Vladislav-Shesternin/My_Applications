package com.gusarove.digitalexchange.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gusarove.digitalexchange.game.actors.button.AButton
import com.gusarove.digitalexchange.game.actors.button.AButtonStyle
import com.gusarove.digitalexchange.game.actors.checkbox.ACheckBox
import com.gusarove.digitalexchange.game.actors.checkbox.ACheckBoxStyle
import com.gusarove.digitalexchange.game.game
import com.gusarove.digitalexchange.game.manager.GameDataStoreManager
import com.gusarove.digitalexchange.game.manager.NavigationManager
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.actor.disable
import com.gusarove.digitalexchange.game.utils.actor.setBounds
import com.gusarove.digitalexchange.game.utils.actor.setOnClickListener
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedScreen
import com.gusarove.digitalexchange.game.utils.runGDX
import kotlinx.coroutines.launch
import com.gusarove.digitalexchange.game.utils.Layout.Privacy as LP


class PiriviciScreen: AdvancedScreen() {

    private val rivacyImage   = Image(SpriteManager.GameRegion.PURINAONE.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = AButton(AButtonStyle.pol)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.cbix)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.GARAG.region)
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
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gusaroveug12311.github.io/Digitalexchange/psddd"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gusaroveug12311.github.io/Digitalexchange/ttfkdfkd"))) }
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
                        runGDX { NavigationManager.navigate(GoodNightScreen()) }
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