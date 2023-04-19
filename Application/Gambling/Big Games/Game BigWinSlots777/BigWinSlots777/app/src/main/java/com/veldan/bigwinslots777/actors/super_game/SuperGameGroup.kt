package com.veldan.bigwinslots777.actors.super_game

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.bigwinslots777.R
import com.veldan.bigwinslots777.actors.button.ButtonClickable
import com.veldan.bigwinslots777.actors.button.ButtonClickableStyle
import com.veldan.bigwinslots777.actors.label.LabelStyle
import com.veldan.bigwinslots777.actors.label.spinning.SpinningLabel
import com.veldan.bigwinslots777.actors.roulette.RouletteGroup
import com.veldan.bigwinslots777.actors.roulette.superGameRoulette.RouletteCoefficient
import com.veldan.bigwinslots777.actors.roulette.superGameRoulette.RouletteSpin
import com.veldan.bigwinslots777.actors.roulette.superGameRoulette.RouletteWild
import com.veldan.bigwinslots777.actors.roulette.superGameRoulette.SuperGameRouletteItem
import com.veldan.bigwinslots777.actors.super_game.elementGroup.SuperGameElementGroup
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup
import com.veldan.bigwinslots777.advanced.group.AdvancedGroup
import com.veldan.bigwinslots777.layout.Layout
import com.veldan.bigwinslots777.manager.assets.SpriteManager
import com.veldan.bigwinslots777.utils.disable
import com.veldan.bigwinslots777.utils.enable
import com.veldan.bigwinslots777.utils.hideAnim
import com.veldan.bigwinslots777.utils.language.Language
import com.veldan.bigwinslots777.utils.listeners.toClickable
import com.veldan.bigwinslots777.utils.log
import com.veldan.bigwinslots777.layout.Layout.SuperGameGroup as LSG

class SuperGameGroup: AbstractAdvancedGroup() {
    override val controller = SuperGameGroupController(this)

    // roulette
    private val roulettePanel  = Image(SpriteManager.SuperGameRegion.ROULETTE_PANEL.region)
    private val indicatorImage = Image(SpriteManager.SuperGameRegion.INDICATOR.region)
    private val centerImage    = Image(SpriteManager.SuperGameRegion.CENTER.region)
    val rouletteList           = listOf<RouletteGroup<SuperGameRouletteItem>>(
        RouletteSpin(),
        RouletteCoefficient().apply { addAction(Actions.alpha(0f)) },
        RouletteWild().apply { addAction(Actions.alpha(0f)) },
    )
    // spin
    val spinButton             = ButtonClickable(ButtonClickableStyle.spin)
    private val spinTextLabel  by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyle.font_50) }
    // dialog
    val dialogGroup    = AdvancedGroup()
    val dialogPanel    = Image(SpriteManager.GameRegion.DIALOG_PANEL.region)
    var dialogText     = SpinningLabel("", LabelStyle.font_red_100)
    // elementGroup
    val elementGroup   = SuperGameElementGroup()



    init {
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addRoulettePanel()
        addRouletteList()
        addIndicatorImage()
        addCenterImage()
        addSpinButton()
        addElementGroup()
        addDialogGroup()
    }

    private fun AdvancedGroup.addRoulettePanel() {
        addActor(roulettePanel)
        roulettePanel.setBounds(LSG.ROULETTE_PANEL_X, LSG.ROULETTE_PANEL_Y, LSG.ROULETTE_PANEL_W, LSG.ROULETTE_PANEL_H)
    }

    private fun AdvancedGroup.addRouletteList() {
        rouletteList.onEach { roulette ->
            addActor(roulette)
            roulette.setBounds(LSG.ROULETTE_X, LSG.ROULETTE_Y, LSG.ROULETTE_W, LSG.ROULETTE_H)
        }
    }

    private fun AdvancedGroup.addIndicatorImage() {
        addActor(indicatorImage)
        indicatorImage.setBounds(LSG.INDICATOR_X, LSG.INDICATOR_Y, LSG.INDICATOR_W, LSG.INDICATOR_H)
    }

    private fun AdvancedGroup.addCenterImage() {
        addActor(centerImage)
        centerImage.setBounds(LSG.CENTER_X, LSG.CENTER_Y, LSG.CENTER_W, LSG.CENTER_H)
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LSG.SPIN_X, LSG.SPIN_Y, LSG.SPIN_W, LSG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LSG.SPIN_TEXT_X, LSG.SPIN_TEXT_Y, LSG.SPIN_TEXT_W, LSG.SPIN_TEXT_H)

            controller.setOnClickListener { this@SuperGameGroup.controller.spin() }
        }

    }

    private fun AdvancedGroup.addElementGroup() {
        elementGroup.elementImageList.onEach { it.addAction(Actions.alpha(0f)) }
        elementGroup.elementLabelList.onEach { it.addAction(Actions.alpha(0f)) }

        addActor(elementGroup)
        elementGroup.setPosition(LSG.ELEMENT_X, LSG.ELEMENT_Y)
    }

    // ------------------------------------------------------------------------
    // Dialog
    // ------------------------------------------------------------------------
    private fun AdvancedGroup.addDialogGroup() {
        dialogGroup.addAction(Actions.alpha(0f))
        addActor(dialogGroup)

        dialogGroup.apply {
            disable()
            setBounds(LSG.DIALOG_X, LSG.DIALOG_Y, LSG.DIALOG_W, LSG.DIALOG_H)
            addAndFillActor(dialogPanel)
        }
    }

    fun addDialogFail() {
        dialogText.controller.setText(Language.getStringResource(R.string.fail))

        dialogGroup.apply {
            enable()
            addActor(dialogText)
            dialogText.setBounds(LSG.Dialog.TEXT_X, LSG.Dialog.FAIL_Y, LSG.Dialog.TEXT_W, LSG.Dialog.TEXT_H)

            toClickable().setOnClickListener { this@SuperGameGroup.controller.doAfterFinish(null) }
        }
    }

    fun addDialogWin() {
        dialogText.controller.apply {
            setStyle(LabelStyle.font_green_100)
            setText(Language.getStringResource(R.string.win))
        }

        dialogGroup.apply {
            enable()
            addActor(dialogText)
            dialogText.setBounds(LSG.Dialog.TEXT_X, LSG.Dialog.WIN_Y, LSG.Dialog.TEXT_W, LSG.Dialog.TEXT_H)

            elementGroup.setPosition(LSG.Dialog.ELEMENT_X, LSG.Dialog.ELEMENT_Y)
            addActor(elementGroup)

            toClickable().setOnClickListener { this@SuperGameGroup.controller.apply { doAfterFinish(numberList) } }
        }
    }



}