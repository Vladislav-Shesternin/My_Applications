package com.veldan.pinup.screens.game

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.R
import com.veldan.pinup.actors.button.ButtonClickable
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.actors.label.spinning.SpinningLabel
import com.veldan.pinup.actors.miniGame.MiniGame
import com.veldan.pinup.actors.superGame.SuperGame
import com.veldan.pinup.actors.slot.slotGroup.SlotGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.advanced.AdvancedScreen
import com.veldan.pinup.advanced.AdvancedStage
import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.manager.assets.util.SoundUtil
import com.veldan.pinup.utils.disable
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.layout.Layout.Game as LG

class GameScreen: AdvancedScreen() {
    override val controller = GameScreenController(this)

    val gameGroup         = AdvancedGroup()
    var miniGame          = MiniGame()
    var superGame         = SuperGame()
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameSprite.BALANCE_PANEL.data.texture)
    val balanceTextLabel  = SpinningLabel("", LabelStyleUtil.amaranteWhite60)
    val betPanelGroup     = AdvancedGroup()
    val betPanelImage     = Image(SpriteManager.GameSprite.BET_PANEL.data.texture)
    val betTextLabel      = SpinningLabel("", LabelStyleUtil.amaranteWhite60)
    val betLabel          by lazy { SpinningLabel(Language.getStringResource(R.string.bet), LabelStyleUtil.white50) }
    val betPlusButton     = ButtonClickable(ButtonClickable.Style.plus)
    val betMinusButton    = ButtonClickable(ButtonClickable.Style.minus)
    val menuButton        = ButtonClickable(ButtonClickable.Style.menu)
    val autoSpinButton    = ButtonClickable(ButtonClickable.Style.autoSpin)
    val spinButton        = ButtonClickable(ButtonClickable.Style.spin)
    val spinTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyleUtil.white96) }
    val slotGroup         = SlotGroup()


    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.GameSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addGameGroup()
    }

    private fun AdvancedStage.addGameGroup() {
        addAndFillActor(gameGroup)
        gameGroup.addActorsOnGroup()
    }

    private fun AdvancedGroup.addActorsOnGroup() {
        addBalancePanel()
        addBetPanel()
        addBetPlusButton()
        addBetMinusButton()
        addMenuButton()
        addAutoSpinButton()
        addSpinButton()
        addSlotGroup()
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
            addActors(betTextLabel, betLabel)

            betTextLabel.apply {
                setBounds(LG.BET_TEXT_X, LG.BET_TEXT_Y, LG.BET_TEXT_W, LG.BET_TEXT_H)
                this@GameScreen.controller.updateBet()
            }
            
            betLabel.setBounds(LG.BET_LABEL_X, LG.BET_LABEL_Y, LG.BET_LABEL_W, LG.BET_LABEL_H)
        }
    }
    
    private fun AdvancedGroup.addBetPlusButton() {
        addActor(betPlusButton)
        betPlusButton.apply { 
            setBounds(LG.BET_PLUS_X, LG.BET_PLUS_Y, LG.BET_PLUS_W, LG.BET_PLUS_H)
            setOnClickListener(SoundUtil.PLUS_MINUS) { this@GameScreen.controller.betPlusHandler() }
        }
    }

    private fun AdvancedGroup.addBetMinusButton() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBounds(LG.BET_MINUS_X, LG.BET_MINUS_Y, LG.BET_MINUS_W, LG.BET_MINUS_H)
            setOnClickListener(SoundUtil.PLUS_MINUS) { this@GameScreen.controller.betMinusHandler() }
        }
    }

    private fun AdvancedGroup.addMenuButton() {
        addActor(menuButton)
        menuButton.apply { 
            setBounds(LG.MENU_X, LG.MENU_Y, LG.MENU_W, LG.MENU_H)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addAutoSpinButton() {
        addActor(autoSpinButton)
        autoSpinButton.apply {
            setBounds(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, LG.AUTO_SPIN_W, LG.AUTO_SPIN_H)
            setOnClickListener { this@GameScreen.controller.autoSpinHandler() }
        }
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LG.SPIN_X, LG.SPIN_Y, LG.SPIN_W, LG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LG.SPIN_TEXT_X, LG.SPIN_TEXT_Y, LG.SPIN_TEXT_W, LG.SPIN_TEXT_H)

            setOnClickListener { this@GameScreen.controller.spinHandler() }
        }
    }

    private fun AdvancedGroup.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPosition(LG.SLOT_GROUP_X, LG.SLOT_GROUP_Y)
    }



    fun addMiniGame() {
        miniGame = MiniGame()
        with(stage) {
            addAndFillActor(miniGame)
            miniGame.apply {
                disable()
                addAction(Actions.alpha(0f))
            }
        }
    }

    fun addSuperGame() {
        superGame = SuperGame()
        with(stage) {
            addAndFillActor(superGame)
            superGame.apply {
                disable()
                addAction(Actions.alpha(0f))
            }
        }
    }



}