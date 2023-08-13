package com.leto.advancedcalculator.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.leto.advancedcalculator.NewVebkaZaebcaActivity
import com.leto.advancedcalculator.game.game
import com.leto.advancedcalculator.game.manager.FontTTFManager
import com.leto.advancedcalculator.game.manager.NavigationManager
import com.leto.advancedcalculator.game.manager.SpriteManager
import com.leto.advancedcalculator.game.utils.GameColor
import com.leto.advancedcalculator.game.utils.actor.animHide
import com.leto.advancedcalculator.game.utils.actor.animShow
import com.leto.advancedcalculator.game.utils.actor.disable
import com.leto.advancedcalculator.game.utils.actor.enable
import com.leto.advancedcalculator.game.utils.actor.setOnClickListener
import com.leto.advancedcalculator.game.utils.advanced.AdvancedGroup
import com.leto.advancedcalculator.game.utils.advanced.AdvancedScreen
import com.leto.advancedcalculator.game.utils.advanced.AdvancedStage
import com.leto.advancedcalculator.game.utils.numStr
import com.leto.advancedcalculator.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch


class PoraNaRabotaArmenScreen: AdvancedScreen() {

    private val nazadImg = Image(SpriteManager.GameRegion.NAZAR.region)
    private val samakImg = Image(SpriteManager.GameRegion.SAMAK.region)
    private val urusImg  = Image(SpriteManager.GameRegion.URUS.region)
    private val inputLbl  = Label("0", Label.LabelStyle(FontTTFManager.GilRegularro.font_83.font, GameColor.sirik))
    private val resultLbl = Label("0", Label.LabelStyle(FontTTFManager.GilMediummo.font_125.font, Color.BLACK))
    private val panel     = AdvancedGroup()
    private val dusaImg   = Image(SpriteManager.GameRegion.DUSA.region)

    private val inputFlow = MutableStateFlow("")


    override fun AdvancedStage.addActorsOnStageUI() {
        addAllAktos()
        root.animShow(0.5f) { collectInput() }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAllAktos() {
        addActor(nazadImg)
        nazadImg.apply {
            setBounds(0f, 1511f,250f, 117f)
            setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.back() } }
        }
        addActor(samakImg)
        samakImg.setBounds(41f, 282f,699f, 885f)
        addActor(inputLbl)
        inputLbl.apply {
            setBounds(41f, 1351f,699f, 100f)
            setAlignment(Align.right)
        }
        addActor(resultLbl)
        resultLbl.apply {
            setBounds(39f, 1171f,699f, 201f)
            setAlignment(Align.right)
        }
        addBtns()
        addActor(panel)
        panel.apply {
            setBounds(2f, 142f,783f, 691f)
            addAction(Actions.alpha(0f))
            disable()

            addAndFillActor(dusaImg)
            dusaImg.setOnClickListener { inputFlow.value += "0" }
        }
        addActor(urusImg)
        urusImg.apply {
            setBounds(100f, 125f,584f, 79f)
            setOnClickListener { animANNA() }
        }
    }

    private fun AdvancedStage.addBtns() {
        val btns = List(20) { Actor() }
        var nx = 41f
        var ny = 1017f

        btns.onEachIndexed { index, actor ->
            addActor(actor)
            actor.setBounds(nx, ny, 150f, 150f)
            nx += 185f
            if (index.inc() % 4 == 0) {
                nx = 41f
                ny -= 185f
            }
        }

        btns[0].setOnClickListener { inputFlow.value = "0" }
        btns[1].setOnClickListener { inputFlow.run { value = value.dropLast(1)} }
        btns[2].setOnClickListener { inputFlow.run { value += "%" } }
        btns[3].setOnClickListener { inputFlow.run { value += "/" } }
        btns[4].setOnClickListener { inputFlow.run { value += "7" } }
        btns[5].setOnClickListener { inputFlow.run { value += "8" } }
        btns[6].setOnClickListener { inputFlow.run { value += "9" } }
        btns[7].setOnClickListener { inputFlow.run { value += "*" } }
        btns[8].setOnClickListener { inputFlow.run { value += "4" } }
        btns[9].setOnClickListener { inputFlow.run { value += "5" } }
        btns[10].setOnClickListener { inputFlow.run { value += "6" } }
        btns[11].setOnClickListener { inputFlow.run { value += "-" } }
        btns[12].setOnClickListener { inputFlow.run { value += "1" } }
        btns[13].setOnClickListener { inputFlow.run { value += "2" } }
        btns[14].setOnClickListener { inputFlow.run { value += "3" } }
        btns[15].setOnClickListener { inputFlow.run { value += "+" } }
        btns[16].setOnClickListener { inputFlow.run { value += "00" } }
        btns[17].setOnClickListener { inputFlow.run { value += "0" } }
        btns[18].setOnClickListener { inputFlow.run { value += "." } }
        btns[19].setOnClickListener { inputFlow.run { value += "=" } }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun g1to6() = (1..6).random()

    private fun collectInput() {
        coroutine.launch {
            inputFlow.collectIndexed { index, value ->
                if (index != 0) runGDX {
                    inputLbl.setText(value)
                    resultLbl.setText("${numStr(0, 9, g1to6())}")
                }
            }
        }
    }


    var isShow = true
    private fun animANNA() {
        if (isShow) {
            isShow = false
            urusImg.addAction(Actions.sequence(
                Actions.moveTo(100f, 738f, 0.3f, Interpolation.swing),
                Actions.fadeOut(0.2f),
                Actions.run {
                    panel.apply {
                        addAction(Actions.fadeIn(0.2f))
                        enable()
                    }
                }
            ))
        } else {
            isShow = true
            panel.apply {
                disable()
                addAction(Actions.sequence(
                    Actions.fadeOut(0.2f),
                    Actions.run {
                        urusImg.addAction(Actions.sequence(
                            Actions.fadeIn(0.2f),
                            Actions.moveTo(100f, 125f, 0.3f, Interpolation.swing),
                        ))
                    }
                ))
            }
        }
    }

}