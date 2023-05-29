package com.polovinka.gurieva.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.polovinka.gurieva.game.actors.button.AButton
import com.polovinka.gurieva.game.actors.button.AButtonStyle
import com.polovinka.gurieva.game.actors.checkbox.ACheckBox
import com.polovinka.gurieva.game.actors.checkbox.ACheckBoxStyle
import com.polovinka.gurieva.game.game
import com.polovinka.gurieva.game.manager.GameDataStoreManager
import com.polovinka.gurieva.game.manager.NavigationManager
import com.polovinka.gurieva.game.manager.SpriteManager
import com.polovinka.gurieva.game.utils.actor.disable
import com.polovinka.gurieva.game.utils.actor.setBounds
import com.polovinka.gurieva.game.utils.actor.setOnClickListener
import com.polovinka.gurieva.game.utils.advanced.AdvancedGroup
import com.polovinka.gurieva.game.utils.advanced.AdvancedScreen
import com.polovinka.gurieva.game.utils.runGDX
import kotlinx.coroutines.launch
import com.polovinka.gurieva.game.utils.Layout.Privacy as LP


class PicivyScreen: AdvancedScreen() {

    private val rivacyImage   = Image(SpriteManager.GameRegion.RARIVOCY.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.rivac)
    private val agreeButton   = AButton(AButtonStyle.picavcy)
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
                        runGDX { NavigationManager.navigate(CardScreen()) }
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