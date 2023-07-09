package com.vitaliyi.financeanalizator.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.vitaliyi.financeanalizator.game.actors.button.AButton
import com.vitaliyi.financeanalizator.game.actors.button.AButtonStyle
import com.vitaliyi.financeanalizator.game.actors.checkbox.ACheckBox
import com.vitaliyi.financeanalizator.game.actors.checkbox.ACheckBoxStyle
import com.vitaliyi.financeanalizator.game.game
import com.vitaliyi.financeanalizator.game.manager.GameDataStoreManager
import com.vitaliyi.financeanalizator.game.manager.NavigationManager
import com.vitaliyi.financeanalizator.game.manager.SpriteManager
import com.vitaliyi.financeanalizator.game.utils.actor.disable
import com.vitaliyi.financeanalizator.game.utils.actor.setBounds
import com.vitaliyi.financeanalizator.game.utils.actor.setOnClickListener
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGroup
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedScreen
import com.vitaliyi.financeanalizator.game.utils.runGDX
import kotlinx.coroutines.launch
import com.vitaliyi.financeanalizator.game.utils.Layout.Privacy as LP

val politka = "https://vitoilyina2100213.github.io/FinanceAnalizator/pdfdlfdlkf"
val termsak = "https://vitoilyina2100213.github.io/FinanceAnalizator/tfkklgkfkl"
class EtoPrivatDetkaScreen: AdvancedScreen() {

    private val rubelikImage  = Image(SpriteManager.GameRegion.R.region)
    private val prTextImage   = Image(SpriteManager.GameRegion.TEXT.region)
    private val pravilaImage  = Image(SpriteManager.GameRegion.CONTINUE.region)
    private val prtrImage     = Image(SpriteManager.GameRegion.POLITIK_TERMS.region)
    private val privacyButton = Actor()
    private val termsButton   = Actor()
    private val agreeButton   = AButton(AButtonStyle.contik)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.checkedlistener)
        //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.PARDON.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActors(rubelikImage, pravilaImage, prtrImage)
        rubelikImage.setBounds(320f, 1331f, 70f, 70f)
        pravilaImage.setBounds(71f, 356f, 590f, 87f)
        prtrImage.setBounds(188f, 36f, 335f, 104f)
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
            setBounds(LP.conucyP)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(politka))) }
        }
        termsButton.apply {
            setBounds(LP.terms)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termsak))) }
        }
    }

    private fun AdvancedGroup.addCbox() {
        addActor(privacyCBox)
        privacyCBox.apply {
            setBounds(LP.bix)

            setOnCheckListener {
                isAgree = it
                if (isAgree) agreeButton.enable() else agreeButton.disable()
            }
        }
    }

    private fun AdvancedGroup.addAgreeButton() {
        addActor(agreeButton)
        agreeButton.apply {
            setBounds(LP.nbt)
            disable()

            setOnClickListener {
                if (isAgree) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        runGDX { NavigationManager.navigate(PatrachenoScreen()) }
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