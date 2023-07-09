package com.vachykm.investmentmanager.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.vachykm.investmentmanager.game.actors.button.AButton
import com.vachykm.investmentmanager.game.actors.button.AButtonStyle
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBox
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBoxStyle
import com.vachykm.investmentmanager.game.game
import com.vachykm.investmentmanager.game.manager.GameDataStoreManager
import com.vachykm.investmentmanager.game.manager.NavigationManager
import com.vachykm.investmentmanager.game.manager.SpriteManager
import com.vachykm.investmentmanager.game.utils.Layout
import com.vachykm.investmentmanager.game.utils.actor.animHide
import com.vachykm.investmentmanager.game.utils.actor.animSuspendShow
import com.vachykm.investmentmanager.game.utils.actor.setBounds
import com.vachykm.investmentmanager.game.utils.actor.setOnClickListener
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedScreen
import com.vachykm.investmentmanager.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class ProduktWelcomeScreen: AdvancedScreen() {

    companion object {
        const val PRIVACY = "https://maksimvasuk65.github.io/InvestmentManager/pwewerwroowriwor"
        const val TERMS   = "https://maksimvasuk65.github.io/InvestmentManager/tjkfjgfjgkfgjfkgjk"
    }

    private val headerImg  = Image(SpriteManager.SplashRegion.HEADER.region)
    private val receiptImg = Image(SpriteManager.GameRegion.RECEIPT.region)
    private val money1Img  = Image(SpriteManager.GameRegion.MONEY1.region)
    private val money2Img  = Image(SpriteManager.GameRegion.MONEY2.region)
    private val soglaImg   = Image(SpriteManager.GameRegion.TEKAS.region)
    private val soglaBox   = ACheckBox(ACheckBoxStyle.rect_circ)
    private val soglaBtn   = AButton(AButtonStyle.btn_otkl)

    private var isSogla = false


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addHeader()
                addMoney()
                addCbAndBtn()
            }
            headerImg.animSuspendShow(0.4f)
            animMoney(0.7f)
            animSogla(0.4f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addHeader() {
        addActor(headerImg)
        headerImg.apply {
            setBounds(Layout.header)
            animHide()
        }
    }

    private fun AdvancedGroup.addMoney() {
        addActors(receiptImg, money1Img, money2Img)
        receiptImg.apply {
            setBounds(41f, 947f, 355f, 337f)
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
            addAction(Actions.rotateTo(-30f))
        }
        money1Img.apply {
            setBounds(-62f, 571f, 320f, 298f)
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
            addAction(Actions.rotateTo(80f))
        }
        money2Img.apply {
            setBounds(475f, 1123f, 155f, 99f)
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
            addAction(Actions.rotateTo(-50f))
        }
    }

    private fun AdvancedGroup.addCbAndBtn() {
        addActors(soglaBox, soglaBtn, soglaImg)
        soglaBox.apply {
            setBounds(45f, -47f,47f, 47f)
            animHide()

            setOnCheckListener {
                isSogla = it
                if (isSogla) soglaBtn.enable() else soglaBtn.disable()
            }
        }
        soglaBtn.apply {
            setBounds(47f, -94f,555f, 94f)
            animHide()
            disable()

            setOnClickListener {
                if (isSogla) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Agree.update { true }
                        mainGroup.animHide(0.4f) { runGDX { NavigationManager.navigate(HomeSapienceScreen()) } }
                    }
                }
            }
        }
        soglaImg.apply {
            setBounds(111f, 81f,503f, 81f)
            animHide()
        }

        val pa = Actor()
        val pb = Actor()
        val ta = Actor()
        val tb = Actor()
        addActors(pa, pb, ta, tb)
        pa.apply {
            setBounds(466f, 306f, 118f, 28f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY))) }
        }
        pb.apply {
            setBounds(111f, 274f, 192f, 28f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY))) }
        }
        ta.apply {
            setBounds(321f, 274f, 234f, 28f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TERMS))) }
        }
        tb.apply {
            setBounds(111f, 241f, 192f, 28f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TERMS))) }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animMoney(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            receiptImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(141f, 774f, time),
                    Actions.rotateTo(0f, time),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))
            money1Img.addAction(Actions.parallel(
                Actions.moveTo(14f, 753f, time),
                Actions.rotateTo(0f, time),
                Actions.fadeIn(time),
            ))
            money2Img.addAction(Actions.parallel(
                Actions.moveTo(406f, 971f, time),
                Actions.rotateTo(0f, time),
                Actions.fadeIn(time),
            ))

            receiptImg.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, 15f, 0.3f, Interpolation.sineOut),
                Actions.moveBy(0f, -30f, 0.6f, Interpolation.sine),
                Actions.moveBy(0f, 15f, 0.3f, Interpolation.sineIn),
            )))
            money1Img.addAction(Actions.forever(Actions.sequence(
                Actions.rotateBy(-15f, 0.25f, Interpolation.sineOut),
                Actions.rotateBy( 30f, 0.5f, Interpolation.sine),
                Actions.rotateBy( -15f, 0.25f, Interpolation.sineIn),
            )))
            money2Img.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(10f, 0f, 0.2f, Interpolation.sineOut),
                Actions.moveBy(-20f, 0f, 0.4f, Interpolation.sine),
                Actions.moveBy(10f, 0f, 0.2f, Interpolation.sineIn),
            )))
        }
    }.await()

    private suspend fun animSogla(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            soglaBtn.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(47f, 347f, time),
                    Actions.fadeIn(time),
                ),
                Actions.run {
                    soglaImg.addAction(Actions.sequence(
                        Actions.delay(time/2),
                        Actions.parallel(
                            Actions.moveTo(111f, 247f, time),
                            Actions.fadeIn(time),
                        ),
                        Actions.run {
                            soglaBox.addAction(Actions.sequence(
                                Actions.delay(time/2),
                                Actions.parallel(
                                    Actions.moveTo(45f, 281f, time),
                                    Actions.fadeIn(time),
                                ),
                                Actions.run { continuation.complete(Unit) }
                            ))
                        }
                    ))
                }
            ))
        }
    }.await()

}