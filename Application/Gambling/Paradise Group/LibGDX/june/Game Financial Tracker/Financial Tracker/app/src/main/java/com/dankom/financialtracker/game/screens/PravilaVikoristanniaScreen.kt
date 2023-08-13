package com.dankom.financialtracker.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dankom.financialtracker.game.actors.button.AButton
import com.dankom.financialtracker.game.actors.button.AButtonStyle
import com.dankom.financialtracker.game.actors.checkbox.ACheckBox
import com.dankom.financialtracker.game.actors.checkbox.ACheckBoxStyle
import com.dankom.financialtracker.game.game
import com.dankom.financialtracker.game.manager.GameDataStoreManager
import com.dankom.financialtracker.game.manager.NavigationManager
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.actor.setOnClickListener
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup
import com.dankom.financialtracker.game.utils.advanced.AdvancedScreen
import com.dankom.financialtracker.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

val PP = "https://ahtemmavu10.github.io/Financial-Tracker/pfgfdsdsdssffgfg"
val TT = "https://ahtemmavu10.github.io/Financial-Tracker/tkfgkfgkgfkjgkf"

class PravilaVikoristanniaScreen: AdvancedScreen() {

    private val personaImg  = Image(SpriteManager.GameRegion.PEOPLE.region)
    private val leftImg     = Image(SpriteManager.GameRegion.LEFT.region)
    private val rightImg    = Image(SpriteManager.GameRegion.RIGHT.region)
    private val agreeBox    = ACheckBox(ACheckBoxStyle.agree)
    private val agreeBtn    = AButton(AButtonStyle.agree)
    private val pravoImg    = Image(SpriteManager.GameRegion.USLOVIA.region)

    //private val agreeLabel    = Label("Согласиться и Продолжить", Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.BLACK))

    private var isAgree = false



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addLeftRight()
                addPerson()
                addUslovia()
                addCbox()
                addAgreeButton()
            }

            animLeftRight()
            personaImg.animShow(0.4f)
            pravoImg.animShow(0.4f)
            agreeBox.animShow(0.4f)
            agreeBtn.animShow(0.4f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addPerson() {
        addActor(personaImg)
        personaImg.apply {
            setBounds(125f, 518f, 358f, 610f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addLeftRight() {
        addActors(leftImg, rightImg)
        leftImg.apply {
            setBounds(-503f, 0f, 503f, 683f)
        }
        rightImg.apply {
            setBounds(655f, 0f, 655f, 518f)
        }
    }

    private fun AdvancedGroup.addUslovia() {
        addActor(pravoImg)
        pravoImg.apply {
            setBounds(67f, 214f, 571f, 84f)
            addAction(Actions.alpha(0f))
        }

        val p1 = Actor()
        val p2 = Actor()
        val t1 = Actor()
        val t2 = Actor()
        addActors(p1, p2, t1, t2)
        p1.apply {
            setBounds(444f, 275f, 175f, 23f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PP))) }
        }
        p2.apply {
            setBounds(67f, 245f, 206f, 23f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PP))) }
        }
        t1.apply {
            setBounds(295f, 245f, 342f, 23f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TT))) }
        }
        t2.apply {
            setBounds(67f, 211f, 125f, 23f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TT))) }
        }
    }

    private fun AdvancedGroup.addCbox() {
        addActor(agreeBox)
        agreeBox.apply {
            addAction(Actions.alpha(0f))
            setBounds(15f, 249f, 35f, 35f)

            setOnCheckListener {
                isAgree = it
                if (isAgree) agreeBtn.enable() else agreeBtn.disable()
            }
        }
    }

    private fun AdvancedGroup.addAgreeButton() {
        addActor(agreeBtn)
        agreeBtn.apply {
            addAction(Actions.alpha(0f))
            setBounds(15f, 66f, 633f, 115f)
            disable()

            setOnClickListener {
                if (isAgree) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        mainGroup.animHide(0.5f)
                        runGDX { NavigationManager.navigate(AppleScreen()) }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun animLeftRight() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            leftImg.addAction(Actions.moveTo(0f, 0f, 0.5f))
            rightImg.addAction(Actions.sequence(
                Actions.moveTo(0f, 0f, 0.5f),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun Actor.animShow(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.fadeIn(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun Actor.animHide(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

}