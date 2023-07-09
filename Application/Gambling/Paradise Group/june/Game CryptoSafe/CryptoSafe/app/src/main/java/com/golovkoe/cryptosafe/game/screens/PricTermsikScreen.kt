package com.golovkoe.cryptosafe.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.golovkoe.cryptosafe.game.actors.button.AButton
import com.golovkoe.cryptosafe.game.actors.button.AButtonStyle
import com.golovkoe.cryptosafe.game.actors.checkbox.ACheckBox
import com.golovkoe.cryptosafe.game.actors.checkbox.ACheckBoxStyle
import com.golovkoe.cryptosafe.game.game
import com.golovkoe.cryptosafe.game.manager.GameDataStoreManager
import com.golovkoe.cryptosafe.game.manager.NavigationManager
import com.golovkoe.cryptosafe.game.manager.SpriteManager
import com.golovkoe.cryptosafe.game.utils.actor.disable
import com.golovkoe.cryptosafe.game.utils.actor.setBounds
import com.golovkoe.cryptosafe.game.utils.actor.setOnClickListener
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGroup
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedScreen
import com.golovkoe.cryptosafe.game.utils.runGDX
import kotlinx.coroutines.launch
import com.golovkoe.cryptosafe.game.utils.Layout.Privacy as LP


class PricTermsikScreen: AdvancedScreen() {

    private val prTextImage   = Image(SpriteManager.GameRegion.PR_TEXT.region)
    private val pravilaImage  = Image(SpriteManager.GameRegion.PRAVILA.region)
    private val prtrImage     = Image(SpriteManager.GameRegion.PR_TR.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = AButton(AButtonStyle.buttonchick)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.boxik)
    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActors(pravilaImage, prtrImage)
        pravilaImage.setBounds(78f, 371f, 567f, 58f)
        prtrImage.setBounds(110f, 48f, 489f, 66f)
        addButtons()
        addPanel()
        addAgreeButton()
        addCbox()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(prTextImage)
        prTextImage.apply {
            setBounds(LP.panel)
            disable()
        }
    }

    private fun AdvancedGroup.addButtons() {
        addActors(privacyButton, termsButton)
        privacyButton.apply {
            setBounds(LP.privacy)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://golovkoevgenij835.github.io/CryptoSafe/pdfdfdf"))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://golovkoevgenij835.github.io/CryptoSafe/tdfodfo"))) }
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
                        runGDX { NavigationManager.navigate(FirstScreen()) }
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