package com.zaykoa.investmentanalyzer.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.zaykoa.investmentanalyzer.game.actors.button.AButton
import com.zaykoa.investmentanalyzer.game.actors.button.AButtonStyle
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBox
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBoxStyle
import com.zaykoa.investmentanalyzer.game.game
import com.zaykoa.investmentanalyzer.game.manager.GameDataStoreManager
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.animSuspendShow
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

val privacY = "https://dankomaksim01.github.io/Investment_Analyzer/prkrktjr"
val termS   = "https://dankomaksim01.github.io/Investment_Analyzer/tthgkhgkhh"

class PrivacyTermsConfidenceScreen: AdvancedScreen() {

    private val topImg      = Image(SpriteManager.GameRegion.SUBSTRACT_TOP.region)
    private val logoImg     = Image(SpriteManager.GameRegion.INVEST_ANALIZATOR.region)
    private val uslovieImg  = Image(SpriteManager.GameRegion.TEXTOPOLITIKA.region)
    private val agreeBox    = ACheckBox(ACheckBoxStyle.checkerao)
    private val agreeBtn    = AButton(AButtonStyle.begin)

    private var isAgree = false



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addTop()
                addAnalizator()
                addUslovia()
                addCbox()
                addAgreeButton()
            }

            topImg.animSuspendShow(0.4f)
            animAnalizator(0.7f)
            animUslovia(0.7f)
            agreeBox.animSuspendShow(0.4f)
            animAgree(0.4f)

        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addTop() {
        addActor(topImg)
        topImg.apply {
            setBounds(0f, 1229f, 692f, 268f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addAnalizator() {
        addActor(logoImg)
        logoImg.apply {
            setBounds(88f, 570f, 514f, 175f)
            addAction(Actions.alpha(0f))
            addAction(Actions.scaleTo(0.3f, 0.3f))
        }
    }

    private fun AdvancedGroup.addUslovia() {
        addActor(uslovieImg)
        uslovieImg.apply {
            setBounds(691f, 400f, 518f, 160f)
            addAction(Actions.alpha(0f))
        }

        val p = Actor()
        val t = Actor()
        addActors(p,t)
        p.apply {
            setBounds(109f, 380f, 480f, 49f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(privacY))) }
        }
        t.apply {
            setBounds(109f, 295f, 480f, 71f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termS))) }
        }
    }

    private fun AdvancedGroup.addCbox() {
        addActor(agreeBox)
        agreeBox.apply {
            addAction(Actions.alpha(0f))
            setBounds(44f, 398f, 53f, 55f)

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
            setBounds(64f, -107f, 562f, 107f)
            disable()

            setOnClickListener {
                if (isAgree) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        mainGroup.animHide(0.4f) {
                            runGDX { NavigationManager.navigate(MainScreen()) }
                        }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun animAnalizator(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            logoImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.fadeIn(time),
                        Actions.scaleTo(1f, 1f, time),
                        Actions.moveTo(88f, 907f, time, Interpolation.bounceOut)
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private suspend fun animUslovia(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            uslovieImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.fadeIn(time),
                        Actions.moveTo(108f, 295f, time, Interpolation.bounceOut)
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private suspend fun animAgree(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            agreeBtn.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.fadeIn(time),
                        Actions.moveTo(64f, 116f, time, Interpolation.bounceOut)
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

}