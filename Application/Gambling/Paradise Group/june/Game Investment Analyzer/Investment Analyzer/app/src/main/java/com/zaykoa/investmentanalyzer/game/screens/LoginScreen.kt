package com.zaykoa.investmentanalyzer.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.zaykoa.investmentanalyzer.game.actors.button.AButton
import com.zaykoa.investmentanalyzer.game.actors.button.AButtonStyle
import com.zaykoa.investmentanalyzer.game.manager.GameDataStoreManager
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginScreen: AdvancedScreen() {

    private val loginBackImg = Image(SpriteManager.GameRegion.LOGIN_BACKGROUND.region)
    private val investImg    = Image(SpriteManager.GameRegion.INVEST_ANALIZATOR.region)
    private val zapovitImg   = Image(SpriteManager.GameRegion.ZAPOVID.region)
    private val goToWork     = AButton(AButtonStyle.go_to_work)


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addLogo()
                addInvAnal()
                addZapovid()
                addGoTOWo()
            }
            animLoginBack(0.5f)
            animInvAnal(0.5f)
            animZapovid(0.7f)
            animGoToWo(1f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addLogo() {
        addActor(loginBackImg)
        loginBackImg.apply {
            setBounds(-695f, -300f, 691f, 1497f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addInvAnal() {
        addActor(investImg)
        investImg.apply {
            setBounds(214f, 603f, 514f, 175f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addZapovid() {
        addActor(zapovitImg)
        zapovitImg.apply {
            setBounds(40f, 566f, 608f, 57f)
            addAction(Actions.alpha(0f))
            setOrigin(Align.center)
            addAction(Actions.scaleTo(0.5f, 0.5f))
        }
    }

    private fun AdvancedGroup.addGoTOWo() {
        addActor(goToWork)
        goToWork.apply {
            setBounds(64f, 99f, 562f, 107f)
            addAction(Actions.alpha(0f))
            setOrigin(Align.center)
            addAction(Actions.scaleTo(0.5f, 0.5f))

            setOnClickListener {
                coroutine.launch(Dispatchers.IO) {
                    val isAgree = GameDataStoreManager.Agree.get() ?: false
                    runGDX {
                        mainGroup.animHide(0.5f) {
                            NavigationManager.navigate(if (isAgree) MainScreen() else PrivacyTermsConfidenceScreen())
                        }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animLoginBack(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            loginBackImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.moveTo(0f, 0f, time),
                        Actions.fadeIn(time),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private suspend fun animInvAnal(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            investImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.moveTo(88f, 763f, time),
                        Actions.fadeIn(time),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private suspend fun animZapovid(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            zapovitImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.moveTo(40f, 632f, time),
                        Actions.fadeIn(time),
                        Actions.scaleTo(1f, 1f, time),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private suspend fun animGoToWo(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            goToWork.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.fadeIn(time),
                        Actions.scaleTo(1f, 1f, time),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

}