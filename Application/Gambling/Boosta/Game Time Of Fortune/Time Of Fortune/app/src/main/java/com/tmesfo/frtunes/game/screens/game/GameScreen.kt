package com.tmesfo.frtunes.game.screens.game

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.*
import com.tmesfo.frtunes.R
import com.tmesfo.frtunes.game.actors.button.ButtonClickable
import com.tmesfo.frtunes.game.actors.button.ButtonClickableStyle
import com.tmesfo.frtunes.game.actors.label.LabelStyle
import com.tmesfo.frtunes.game.actors.label.spinning.SpinningLabel
import com.tmesfo.frtunes.game.actors.slot.slotGroup.SlotGroup
import com.tmesfo.frtunes.game.advanced.AdvancedScreen
import com.tmesfo.frtunes.game.advanced.AdvancedStage
import com.tmesfo.frtunes.game.advanced.group.AdvancedGroup
import com.tmesfo.frtunes.game.manager.NavigationManager
import com.tmesfo.frtunes.game.manager.assets.SpriteManager
import com.tmesfo.frtunes.game.manager.assets.util.MusicUtil
import com.tmesfo.frtunes.game.utils.language.Language
import com.tmesfo.frtunes.game.utils.region
import com.tmesfo.frtunes.game.layout.Layout.Game as LG

class GameScreen : AdvancedScreen() {
    override val controller = GameScreenController(this)

    // gameGroup
    val gameGroup         = AdvancedGroup()
    // menu
    val menuButton        = ButtonClickable(ButtonClickableStyle.menu)
    // balance
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameRegion.BALANCE_PANEL.region)
    val balanceTextLabel  = SpinningLabel("", LabelStyle.abrilFatface_white2_80)
    // bet
    val betPanelGroup     = AdvancedGroup()
    val betPanelImage     = Image(SpriteManager.GameRegion.BET_PANEL.region)
    val betTextLabel      = SpinningLabel("", LabelStyle.abrilFatface_white2_70)
    val betPlusButton     = ButtonClickable(ButtonClickableStyle.plus)
    val betMinusButton    = ButtonClickable(ButtonClickableStyle.minus)
    // autospin
    val autoSpinButton    = ButtonClickable(ButtonClickableStyle.autoSpin)
    // spin
    val spinButton        = ButtonClickable(ButtonClickableStyle.spin)
    val spinTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyle.font_black2_70) }
    // slotGroup
    val slotGroup         = SlotGroup()



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.SourceTexture.BACKGROUND.data.texture.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addGameGroup()
    }

    // ------------------------------------------------------------------------
    // GameGroup
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addGameGroup() {
        addAndFillActor(gameGroup)
        addActorsOnGameGroup()
    }

    private fun addActorsOnGameGroup() {
        with(gameGroup) {
            addMenuButton()
            addBalancePanel()
            addBetPanel()
            addBetPlusButton()
            addBetMinusButton()
            addAutoSpinButton()
            addSpinButton()
            addSlotGroup()
        }
    }

    private fun AdvancedGroup.addMenuButton() {
        addActor(menuButton)
        menuButton.apply {
            setBounds(LG.MENU_X, LG.MENU_Y, LG.MENU_W, LG.MENU_H)
            controller.setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBounds(LG.BALANCE_PANEL_X, LG.BALANCE_PANEL_Y, LG.BALANCE_PANEL_W, LG.BALANCE_PANEL_H)

            addAndFillActor(balancePanelImage)
            addActor(balanceTextLabel)

            balanceTextLabel.apply {
                setBounds(LG.BALANCE_TEXT_X, LG.BALANCE_TEXT_Y, LG.BALANCE_TEXT_W, LG.BALANCE_TEXT_H)
                this@GameScreen.controller.updateBalance()
            }
        }
    }

    private fun AdvancedGroup.addBetPanel() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LG.BET_PANEL_X, LG.BET_PANEL_Y, LG.BET_PANEL_W, LG.BET_PANEL_H)

            addAndFillActor(betPanelImage)
            addActors(betTextLabel)

            betTextLabel.apply {
                setBounds(LG.BET_TEXT_X, LG.BET_TEXT_Y, LG.BET_TEXT_W, LG.BET_TEXT_H)
                this@GameScreen.controller.updateBet()
            }
        }
    }

    private fun AdvancedGroup.addBetPlusButton() {
        addActor(betPlusButton)
        betPlusButton.apply {
            setBounds(LG.BET_PLUS_X, LG.BET_PLUS_Y, LG.BET_PLUS_W, LG.BET_PLUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betPlusHandler() }
        }
    }

    private fun AdvancedGroup.addBetMinusButton() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBounds(LG.BET_MINUS_X, LG.BET_MINUS_Y, LG.BET_MINUS_W, LG.BET_MINUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betMinusHandler() }
        }
    }

    private fun AdvancedGroup.addAutoSpinButton() {
        addActor(autoSpinButton)
        autoSpinButton.apply {
            setBounds(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, LG.AUTO_SPIN_W, LG.AUTO_SPIN_H)
            controller.setOnClickListener { this@GameScreen.controller.autoSpinHandler() }
        }
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LG.SPIN_X, LG.SPIN_Y, LG.SPIN_W, LG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LG.SPIN_TEXT_X, LG.SPIN_TEXT_Y, LG.SPIN_TEXT_W, LG.SPIN_TEXT_H)

            controller.setOnClickListener { this@GameScreen.controller.spinHandler() }
        }
    }

    private fun AdvancedGroup.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPosition(LG.SLOT_GROUP_X, LG.SLOT_GROUP_Y)
    }

}