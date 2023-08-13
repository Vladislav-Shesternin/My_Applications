package com.dankom.financialtracker.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dankom.financialtracker.game.manager.NavigationManager
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.actor.setOnClickListener
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup
import com.dankom.financialtracker.game.utils.advanced.AdvancedScreen
import com.dankom.financialtracker.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class AppleScreen: AdvancedScreen() {

    private val logoImg   = Image(SpriteManager.GameRegion.LOGO.region)
    private val nachatImg = Image(SpriteManager.GameRegion.NACHAT.region)
    private val start     = Actor()

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addLogo()
                addNachat()
            }

            logoImg.animShow(0.5f)
            animNachat()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addLogo() {
        addActor(logoImg)
        logoImg.apply {
            setBounds(0f, 647f, 655f, 772f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addNachat() {
        addActor(nachatImg)
        nachatImg.setBounds(0f, -715f, 655f, 715f)
        nachatImg.addAction(Actions.alpha(0f))

        addActor(start)
        start.apply {
            setBounds(66f, 49f, 522f, 98f)
            setOnClickListener {
                mainGroup.animHide(0.5f) { NavigationManager.navigate(HelloScreen(), AppleScreen()) }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

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

    private fun Actor.animHide(time: Float, block: () -> Unit) {
        runGDX {
            addAction(Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { block() }
            ))
        }
    }

    private suspend fun animNachat() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            nachatImg.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.moveTo(0f, 0f, 0.6f),
                        Actions.fadeIn(1f),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

}