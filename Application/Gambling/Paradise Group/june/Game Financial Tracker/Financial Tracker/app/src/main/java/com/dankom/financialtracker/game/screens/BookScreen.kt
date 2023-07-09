package com.dankom.financialtracker.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.dankom.financialtracker.game.actors.ListGroup
import com.dankom.financialtracker.game.actors.progress.AProgress
import com.dankom.financialtracker.game.manager.FontTTFManager
import com.dankom.financialtracker.game.manager.NavigationManager
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.GameColor
import com.dankom.financialtracker.game.utils.actor.setOnClickListener
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup
import com.dankom.financialtracker.game.utils.advanced.AdvancedScreen
import com.dankom.financialtracker.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class BookScreen: AdvancedScreen() {

    private val detalizaciaImg = Image(SpriteManager.GameRegion.DETALIZACIA.region)
    private val statistaImg    = Image(SpriteManager.ListRegion.STATISTICA.regionList.shuffled().first())
    private val detacImg       = Image(SpriteManager.GameRegion.DATIK.region)
    private val deatelnostImg  = Image(SpriteManager.GameRegion.DEATELNOST.region)
    private val listG          = ListGroup()
    private val numberLbl      = Label("${number(0,9,7)}", Label.LabelStyle(FontTTFManager.GilroySemibold.font_27.font, GameColor.gray))
    private val dolgLbl        = Label("$${number(0,9,1)},${number(1,9,3)}.00", Label.LabelStyle(FontTTFManager.GilroySemibold.font_27.font, GameColor.gray))
    private val dataLbl        = Label("${number(1,31,1)}.0${number(1,9,1)}.2023", Label.LabelStyle(FontTTFManager.GilroySemibold.font_27.font, GameColor.gray))
    private val balonLbl       = Label("$ ${number(10,99,1)},${number(1,9,3)}.00", Label.LabelStyle(FontTTFManager.GilroySemibold.font_45.font, GameColor.background))
    private val diagram        = AProgress()


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addDetalizacia()
                addStatistica()
                addDetac()
                addDeatelnost()
                addListG()
                addNumbersicks()
                addProgress()
            }
            detalizaciaImg.animShow(0.4f)
            statistaImg.animShow(0.4f)
            detacImg.animShow(0.4f)
            deatelnostImg.animShow(0.4f)
            listG.animShow(0.4f)
            numberLbl.animShow(0.4f)
            dolgLbl.animShow(0.4f)
            dataLbl.animShow(0.4f)
            balonLbl.animShow(0.4f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addDetalizacia() {
        addActor(detalizaciaImg)
        detalizaciaImg.setBounds(47f, 844f, 562f, 518f)
        detalizaciaImg.addAction(Actions.alpha(0f))

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(47f, 1268f, 95f, 95f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addStatistica() {
        addActor(statistaImg)
        statistaImg.setBounds(52f, 570f, 554f, 211f)
        statistaImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addDetac() {
        addActor(detacImg)
        detacImg.setBounds(47f, 477f, 560f, 32f)
        detacImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addDeatelnost() {
        addActor(deatelnostImg)
        deatelnostImg.setBounds(47f, 378f, 560f, 38f)
        deatelnostImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addListG() {
        addActor(listG)
        listG.apply {
            setBounds(46f, 0f, 563f, 349f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addNumbersicks() {
        addActors(numberLbl, dolgLbl, dataLbl, balonLbl)
        numberLbl.apply {
            setBounds(426f, 1206f, 182f, 34f)
            addAction(Actions.alpha(0f))
            setAlignment(Align.right)
        }
        dolgLbl.apply {
            setBounds(426f, 1132f, 182f, 34f)
            addAction(Actions.alpha(0f))
            setAlignment(Align.right)
        }
        dataLbl.apply {
            setBounds(355f, 1058f, 253f, 34f)
            addAction(Actions.alpha(0f))
            setAlignment(Align.right)
        }
        balonLbl.apply {
            setBounds(47f, 847f, 349f, 55f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addProgress() {
        addActor(diagram)
        diagram.apply {
            setBounds(52f, 570f, 554f, 211f)

            coroutine.launch {
                progressPercentFlow.collect { progress ->
                    (progress.toInt() / 10).also {
                        statistaImg.drawable = TextureRegionDrawable(SpriteManager.ListRegion.STATISTICA.regionList[if ( it == 10) 9 else it ])
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun Actor.animShow(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.fadeIn(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}