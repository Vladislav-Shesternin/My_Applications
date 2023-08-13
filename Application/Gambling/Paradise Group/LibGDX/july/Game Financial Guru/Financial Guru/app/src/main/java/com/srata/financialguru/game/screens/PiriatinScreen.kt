package com.srata.financialguru.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.srata.financialguru.R
import com.srata.financialguru.game.actors.button.AButton
import com.srata.financialguru.game.actors.button.AButtonStyle
import com.srata.financialguru.game.actors.checkbox.ACheckBox
import com.srata.financialguru.game.actors.checkbox.ACheckBoxStyle
import com.srata.financialguru.game.colorSKY
import com.srata.financialguru.game.game
import com.srata.financialguru.game.manager.GameDataStoreManager
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.actor.animSuspendVisible
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PiriatinScreen: AdvancedScreen() {

    companion object {
        const val PRIVACY = "https://maksimvasuk65.github.io/InvestmentManager/pwewerwroowriwor"
        const val TERMS   = "https://maksimvasuk65.github.io/InvestmentManager/tjkfjgfjgkfgjfkgjk"
    }

    private val grayImg    = Image(SpriteManager.GameRegion.GRAY_PAN.region)
    private val flowerImg  = Image(SpriteManager.GameRegion.MAN_WITH_FLOWER.region)
    private val goooooImg  = Image(SpriteManager.GameRegion.GOOOO.region)
    private val sagaImg    = Image(SpriteManager.GameRegion.TESTIKJ.region)
    private val soglaBox   = ACheckBox(ACheckBoxStyle.triangle_circ)
    private val soglaBtn   = AButton(AButtonStyle.nex)

    private var isSogla = false


    override fun AdvancedGroup.addActorsOnGroup() {
        colorSKY = Color.WHITE
        game.activity.setNavigationBarColor(R.color.white)

        coroutine.launch {
            runGDX {
                addTopka()
                addCbAndBtn()
            }
            animTopka(0.5f)
            delay(100)
            flowerImg.animSuspendVisible(0.4f)
            animGooo(0.5f)
            sagaImg.animFadeAndScale(0.5f)
            soglaBox.animFadeAndScale(0.5f)
            soglaBtn.animFadeAndScale(0.5f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addTopka() {
        addActors(grayImg, flowerImg, goooooImg)
        grayImg.apply {
            setBounds(0f, 1494f, 691f, 763f)
            animInvisible()
        }
        flowerImg.apply {
            setBounds(65f, 731f, 559f, 501f)
            animInvisible()
        }
        goooooImg.apply {
            setBounds(54f, 504f, 579f, 62f)
            animInvisible()
            addAction(Actions.scaleTo(0f, 0f))
        }
    }

    private fun AdvancedGroup.addCbAndBtn() {
        addActors(soglaBox, soglaBtn, sagaImg)
        soglaBox.apply {
            setBounds(55f, 223f,53f, 53f)
            animInvisible()
            addAction(Actions.scaleTo(0f, 0f))
            setOrigin(Align.center)

            setOnCheckListener {
                isSogla = it
                if (isSogla) soglaBtn.enable() else soglaBtn.disable()
            }
        }
        soglaBtn.apply {
            setBounds(55f, 58f,579f, 92f)
            animInvisible()
            addAction(Actions.scaleTo(0f, 0f))
            setOrigin(Align.center)
            disable()

            setOnClickListener {
                if (isSogla) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        mainGroup.animInvisible(0.3f) { runGDX { NavigationManager.navigate(VasiliyScreen()) } }
                    }
                }
            }
        }
        sagaImg.apply {
            setBounds(108f, 183f,549f, 93f)
            setOrigin(Align.center)
            animInvisible()
            addAction(Actions.scaleTo(0f, 0f))
        }

        val pa = Actor()
        val pb = Actor()
        val ta = Actor()
        val tb = Actor()
        addActors(pa, pb, ta, tb)
        pa.apply {
            setBounds(511f, 250f, 108f, 43f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY))) }
        }
        pb.apply {
            setBounds(108f, 218f, 216f, 43f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY))) }
        }
        ta.apply {
            setBounds(344f, 206f, 269f, 43f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TERMS))) }
        }
        tb.apply {
            setBounds(104f, 172f, 220f, 43f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TERMS))) }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animTopka(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            grayImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(0f, 731f, time),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animGooo(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            goooooImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.scaleTo(1f, 1f, time, Interpolation.swing),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun Actor.animFadeAndScale(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.parallel(
                    Actions.scaleTo(1f, 1f, time, Interpolation.swing),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

}