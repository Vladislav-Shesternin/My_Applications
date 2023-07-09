package com.mariam.cleverfinancier.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mariam.cleverfinancier.game.actors.button.AButton
import com.mariam.cleverfinancier.game.actors.button.AButtonStyle
import com.mariam.cleverfinancier.game.actors.checkbox.ACheckBox
import com.mariam.cleverfinancier.game.actors.checkbox.ACheckBoxStyle
import com.mariam.cleverfinancier.game.game
import com.mariam.cleverfinancier.game.manager.GameDataStoreManager
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen
import com.mariam.cleverfinancier.game.utils.runGDX
import kotlinx.coroutines.launch

val palitka = "https://mmakovecka01.github.io/CleverFinancier/pdfldf"
val termisu = "https://mmakovecka01.github.io/CleverFinancier/tflkgfkl"
class SamsaPrikaScreen: AdvancedScreen() {

    private val pardoseImage  = Image(SpriteManager.GameRegion.PARDASE.region)
    private val agreeButton   = AButton(AButtonStyle.vkl)
    private val privacyCBox   = ACheckBox(ACheckBoxStyle.boxidstka)

    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false

    override fun show() {
        //setBackgrounds(SpriteManager.GameRegion.PARDON.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActors(pardoseImage, agreeButton, privacyCBox)
        pardoseImage.setBounds(21f, 260f, 594f, 720f)
        agreeButton.setBounds(15f, 124f, 603f, 104f)
        privacyCBox.setBounds(188f, 36f, 335f, 104f)
        addAgreeButton()
        addCbox()

        val p1 = Actor()
        val p2 = Actor()
        val t1 = Actor()
        val t2 = Actor()
        addActors(p1, p2, t1, t2)
        p1.apply {
            setBounds(427f, 318f, 106f, 18f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(palitka))) }
        }
        p2.apply {
            setBounds(64f, 292f, 199f, 18f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(palitka))) }
        }
        t1.apply {
            setBounds(285f, 292f, 330f, 18f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termisu))) }
        }
        t2.apply {
            setBounds(64f, 260f, 116f, 18f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termisu))) }
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addCbox() {
        addActor(privacyCBox)
        privacyCBox.apply {
            setBounds(15f, 304f, 33f, 33f)

            setOnCheckListener {
                isAgree = it
                if (isAgree) agreeButton.enable() else agreeButton.disable()
            }
        }
    }

    private fun AdvancedGroup.addAgreeButton() {
        addActor(agreeButton)
        agreeButton.apply {
            setBounds(15f, 124f, 603f, 104f)
            disable()

            setOnClickListener {
                if (isAgree) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        runGDX { NavigationManager.navigate(GlavniyScreen()) }
                    }
                }
            }
        }

    }

}