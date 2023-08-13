package com.zaykoa.investmentanalyzer.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.zaykoa.investmentanalyzer.game.actors.DataGroup
import com.zaykoa.investmentanalyzer.game.actors.button.AButton
import com.zaykoa.investmentanalyzer.game.actors.button.AButtonStyle
import com.zaykoa.investmentanalyzer.game.actors.scroll.HorizontalGroup
import com.zaykoa.investmentanalyzer.game.manager.FontTTFManager
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.animSuspendShow
import com.zaykoa.investmentanalyzer.game.utils.actor.disable
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.numStr
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
var namesik = "None.Inc"
class BuySellChartScreen: AdvancedScreen() {

    private val beckichWithImg = Image(SpriteManager.GameRegion.BEKICH_WITH_GREEN.region)
    private val namsikLbl      = Label(namesik, Label.LabelStyle(FontTTFManager.GilSemBold.font_51.font, Color.BLACK))
    private val balankik       = Label(
        "$${numStr(10,783, 1)},${numStr(100,999,1)}.${numStr(0,9,2)}",
        Label.LabelStyle(FontTTFManager.GilBold.font_44.font, Color.BLACK)
    )
    private val horG  = HorizontalGroup(22f)
    private val scPan = ScrollPane(horG)
    private val sls   = listOf("1h", "24h", "7d", "30d", "1y", "All")
    private val datal = List(6) { DataGroup(sls[it]) }
    private val sssss = listOf(
        SpriteManager.GameRegion._1.region,
        SpriteManager.GameRegion._2.region,
        SpriteManager.GameRegion._3.region,
        SpriteManager.GameRegion._4.region,
    )
    private val digta = Image(sssss.random())
    private val kup   = AButton(AButtonStyle.kup)
    private val prod  = AButton(AButtonStyle.prod)

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addBWithGreen()
                addManName()
                addBanker()
                addDataPan()
                addDigta()
                addKuProd()

                val back = Actor()
                addActor(back)
                back.apply {
                    setBounds(7f, 1342f, 98f, 87f)
                    setOnClickListener { NavigationManager.back() }
                }
            }
            beckichWithImg.animSuspendShow(0.4f)
            namsikLbl.animSuspendShow(0.4f)
            balankik.animSuspendShow(0.4f)
            scPan.animSuspendShow(0.5f)
            animDigta(0.8f)
            animKuProd(0.8f)

        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addBWithGreen() {
        addActor(beckichWithImg)
        beckichWithImg.apply {
            setBounds(36f, 1078f, 593f, 326f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addManName() {
        addActor(namsikLbl)
        namsikLbl.apply {
            setBounds(83f, 1354f, 525f, 62f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addBanker() {
        addActor(balankik)
        balankik.apply {
            setBounds(37f, 1150f, 432f, 59f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addDataPan() {
        addActor(scPan)
        scPan.apply {
            setBounds(0f, 902f, 692f, 121f)
            addAction(Actions.alpha(0f))
        }
        datal.first().cbox.check()
        datal.onEach {
            horG.addActor(it.apply {
                setSize(95f, 121f)
                it.cbox.setOnCheckListener { f ->
                    if (f) digta.drawable = TextureRegionDrawable(sssss.random())
                }
            })
        }
    }

    private fun AdvancedGroup.addDigta() {
        addActor(digta)
        digta.apply {
            setBounds(36f, 240f, 617f, 599f)
            addAction(Actions.alpha(0f))
            addAction(Actions.scaleTo(0f, 0f))
            setOrigin(Align.center)
        }
    }

    private fun AdvancedGroup.addKuProd() {
        addActors(kup, prod)
        kup.apply {
            setBounds(36f, 55f, 293f, 103f)
            addAction(Actions.alpha(0f))
            addAction(Actions.scaleTo(0f, 0f))
            setOrigin(Align.center)
            setOnClickListener {
                disable()
                mainGroup.animHide(0.5f)
                NavigationManager.back() }
        }
        prod.apply {
            setBounds(361f, 55f, 293f, 103f)
            addAction(Actions.alpha(0f))
            addAction(Actions.scaleTo(0f, 0f))
            setOrigin(Align.center)
            setOnClickListener {
                disable()
                mainGroup.animHide(0.5f)
                NavigationManager.back() }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animDigta(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            digta.addAction(
                Actions.sequence(
                    Actions.parallel(
                        Actions.scaleTo(1f, 1f, time, Interpolation.pow2),
                        Actions.fadeIn(time),
                    ),
                    Actions.run { continuation.complete(Unit) }
                ))
        }
    }.await()

    private fun animKuProd(time: Float) {
        runGDX {
            kup.addAction(Actions.parallel(
                Actions.scaleTo(1f, 1f, time, Interpolation.pow2),
                Actions.fadeIn(time),
            ))
            prod.addAction(Actions.parallel(
                Actions.scaleTo(1f, 1f, time, Interpolation.pow2),
                Actions.fadeIn(time),
            ))
        }
    }

}