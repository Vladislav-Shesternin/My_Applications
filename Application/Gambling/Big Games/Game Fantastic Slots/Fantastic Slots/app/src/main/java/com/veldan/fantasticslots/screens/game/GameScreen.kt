package com.veldan.fantasticslots.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.starting_balance_selection_dialog.StartingBalanceSelectionDialog
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.actors.roulette.SuperRoulette
import com.veldan.fantasticslots.actors.slot.slotGroup.SlotGroup
import com.veldan.fantasticslots.actors.trainingDialog.TrainingDialog
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.advanced.AdvancedGroup
import com.veldan.fantasticslots.advanced.AdvancedScreen
import com.veldan.fantasticslots.advanced.AdvancedStage
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.assets.util.SoundUtil
import com.veldan.fantasticslots.manager.DataStoreManager
import com.veldan.fantasticslots.screens.game.GameScreenController.Companion.BET_MIN
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.screens.options.OptionsScreen
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.layout.setPositionFigmaY
import kotlinx.coroutines.launch
import com.veldan.fantasticslots.utils.SlotGroup as SG

class GameScreen : AdvancedScreen() {
    override val viewport   = FitViewport(WIDTH, HEIGHT)
    override val controller = GameScreenController(this)

    val balanceLabel                   = RollingLabel(activityResources.getString(R.string.balance), LabelStyleUtil.robotoMono60)
    val balancePanelGroup              = AdvancedGroup()
    val balancePanelImage              = Image(SpriteManager.GameSprite.BALANCE_PANEL.data.texture)
    val balancePanelLabel              = RollingLabel("", LabelStyleUtil.robotoMono60)
    val betLabel                       = RollingLabel(activityResources.getString(R.string.bet), LabelStyleUtil.robotoMono60)
    val betPanelGroup                  = AdvancedGroup()
    val betPanelImage                  = Image(SpriteManager.GameSprite.BET_PANEL.data.texture)
    val betPanelLabel                  = RollingLabel("", LabelStyleUtil.robotoMono60)
    val betPlusButton                  = ButtonClickable(ButtonClickable.Style.plus)
    val betMinusButton                 = ButtonClickable(ButtonClickable.Style.minus)
    val menuButton                     = ButtonClickable(ButtonClickable.Style.menu)
    val autospinButton                 = ButtonClickable(ButtonClickable.Style.autospin)
    val spinButton                     = ButtonClickable(ButtonClickable.Style.spin)
    val spinLabel                      = RollingLabel(activityResources.getString(R.string.spin), LabelStyleUtil.robotoMono60)
    val slotGroup                      = SlotGroup()
    val superRoulette                  = SuperRoulette.roulette
    val indicatorImage                 = Image(SpriteManager.GameSprite.INDICATOR.data.texture)
    val equallyWildImage               = Image(SpriteManager.GameSprite.EQUALLY_WILD.data.texture)
    val slotItemEquallyWildImage       = Image()
    val bonusSpinsLabel                = RollingLabel(activityResources.getString(R.string.bonus_spins), LabelStyleUtil.robotoMono30)
    val dialogPanelGroup               = AdvancedGroup()
    val dialogPanelImage               = Image(SpriteManager.GameSprite.DIALOG_PANEL.data.texture)
    val dialogPanelTopLabel            = RollingLabel(activityResources.getString(R.string.bonus_spins_are_over), LabelStyleUtil.robotoMono60)
    val dialogPanelBottomLabel         = Label("", LabelStyleUtil.robotoMono60)
    val dialogPanelButton              = ButtonClickable(ButtonClickable.Style.done)
    val startingBalanceSelectionDialog = StartingBalanceSelectionDialog()
    val trainingDialog                 = TrainingDialog()



    override fun show() {
        super.show()
        background = SpriteManager.GameSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addBalanceLabel()
        addBalancePanel()
        addBetLabel()
        addBetPanel()
        addBetPlus()
        addBetMinus()
        addMenu()
        addAutospin()
        addSpin()
        addSlotGroup()
        addRoulette()
        addIndicator()
        addEquallyWild()
        addSlotItemEquallyWild()
        addBonusSpinsLabel()
        addDialogPanelGroup()
        if (OptionsScreen.isCheckedStartingBalance) addStartingBalanceSelectionDialog()
        else if (OptionsScreen.isCheckedTraining) addTrainingDialog()
    }

    private fun AdvancedStage.addBalanceLabel() {
        addActor(balanceLabel)
        balanceLabel.setBoundsFigmaY(Game.BALANCE_LABEL_X, Game.BALANCE_LABEL_Y, Game.BALANCE_LABEL_W, Game.BALANCE_LABEL_H)
    }

    private fun AdvancedStage.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBoundsFigmaY(Game.BALANCE_PANEL_X, Game.BALANCE_PANEL_Y, Game.BALANCE_PANEL_W, Game.BALANCE_PANEL_H)

            addAndFillActors(balancePanelImage, balancePanelLabel)
            controller.coroutineBalance.launch { DataStoreManager.collectBalance { balance -> Gdx.app.postRunnable {
                balancePanelLabel.setText(balance.transformToBalanceFormat())
            } } }
        }
    }

    private fun AdvancedStage.addBetLabel() {
        addActor(betLabel)
        betLabel.setBoundsFigmaY(Game.BET_LABEL_X, Game.BET_LABEL_Y, Game.BET_LABEL_W, Game.BET_LABEL_H)
    }

    private fun AdvancedStage.addBetPanel() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBoundsFigmaY(Game.BET_PANEL_X, Game.BET_PANEL_Y, Game.BET_PANEL_W, Game.BET_PANEL_H)

            addAndFillActors(betPanelImage, betPanelLabel)
            controller.coroutineBet.launch { with(controller.betFlow) {
                emit(BET_MIN)
                collect { bet -> Gdx.app.postRunnable { betPanelLabel.setText(bet.toString()) } }
            } }
        }
    }

    private fun AdvancedStage.addBetPlus() {
        addActor(betPlusButton)
        betPlusButton.apply {
            setBoundsFigmaY(Game.BET_PLUS_X, Game.BET_PLUS_Y, Game.BET_PLUS_W, Game.BET_PLUS_H)
            setOnClickListener(SoundUtil.CLICK_PLUS_MINUS) { controller.betPlusHandler() }
        }
    }

    private fun AdvancedStage.addBetMinus() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBoundsFigmaY(Game.BET_MINUS_X, Game.BET_MINUS_Y, Game.BET_MINUS_W, Game.BET_MINUS_H)
            setOnClickListener(SoundUtil.CLICK_PLUS_MINUS) { controller.betMinusHandler() }
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menuButton)
        menuButton.apply {
            setBoundsFigmaY(Game.MENU_X, Game.MENU_Y, Game.MENU_W, Game.MENU_H)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedStage.addAutospin() {
        addActor(autospinButton)
        autospinButton.apply {
            setBoundsFigmaY(Game.AUTOSPIN_X, Game.AUTOSPIN_Y, Game.AUTOSPIN_W, Game.AUTOSPIN_H)
            setOnClickListener { controller.autospinHandler() }
        }
    }

    private fun AdvancedStage.addSpin() {
        addActor(spinButton)
        spinButton.apply {
            setBoundsFigmaY(Game.SPIN_X, Game.SPIN_Y, Game.SPIN_W, Game.SPIN_H)
            setOnClickListener { controller.spinBlock() }
            addAndFillActor(spinLabel)
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPositionFigmaY(Game.SLOT_GROUP_X, Game.SLOT_GROUP_Y, SG.H)
    }

    private fun AdvancedStage.addRoulette() {
        addActor(superRoulette)
        superRoulette.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.ROULETTE_X, Game.ROULETTE_Y, Game.ROULETTE_W, Game.ROULETTE_H)
        }
    }

    private fun AdvancedStage.addIndicator() {
        addActor(indicatorImage)
        indicatorImage.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.INDICATOR_X, Game.INDICATOR_Y, Game.INDICATOR_W, Game.INDICATOR_H)
        }
    }

    private fun AdvancedStage.addEquallyWild() {
        addActor(equallyWildImage)
        equallyWildImage.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.EQUALLY_WILD_X, Game.EQUALLY_WILD_Y, Game.EQUALLY_WILD_W, Game.EQUALLY_WILD_H)
        }
    }

    private fun AdvancedStage.addSlotItemEquallyWild() {
        addActor(slotItemEquallyWildImage)
        slotItemEquallyWildImage.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.SLOT_ITEM_EQUALLY_WILD_X, Game.SLOT_ITEM_EQUALLY_WILD_Y, Game.SLOT_ITEM_EQUALLY_WILD_W, Game.SLOT_ITEM_EQUALLY_WILD_H)
        }
    }

    private fun AdvancedStage.addBonusSpinsLabel() {
        addActor(bonusSpinsLabel)
        bonusSpinsLabel.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.BONUS_SPINS_X, Game.BONUS_SPINS_Y, Game.BONUS_SPINS_W, Game.BONUS_SPINS_H)
            controller.collectBonusSpins()
        }
    }

    private fun AdvancedStage.addDialogPanelGroup() {
        addActor(dialogPanelGroup)
        dialogPanelGroup.apply {
            disable()
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.DIALOG_PANEL_X, Game.DIALOG_PANEL_Y, Game.DIALOG_PANEL_W, Game.DIALOG_PANEL_H)

            addAndFillActors(dialogPanelImage)
            addActors(dialogPanelTopLabel, dialogPanelBottomLabel, dialogPanelButton)

            dialogPanelTopLabel.setBoundsFigmaY(Game.BonusSpinsDialogPanel.LABEL_TOP_X, Game.BonusSpinsDialogPanel.LABEL_TOP_Y, Game.BonusSpinsDialogPanel.LABEL_TOP_W, Game.BonusSpinsDialogPanel.LABEL_TOP_H, Game.DIALOG_PANEL_H)
            dialogPanelBottomLabel.apply {
                wrap = true
                setAlignment(Align.center, Align.center)
                setBoundsFigmaY(Game.BonusSpinsDialogPanel.LABEL_BOTTOM_X, Game.BonusSpinsDialogPanel.LABEL_BOTTOM_Y, Game.BonusSpinsDialogPanel.LABEL_BOTTOM_W, Game.BonusSpinsDialogPanel.LABEL_BOTTOM_H, Game.DIALOG_PANEL_H)
            }
            dialogPanelButton.apply {
                setBoundsFigmaY(Game.BonusSpinsDialogPanel.BUTTON_X, Game.BonusSpinsDialogPanel.BUTTON_Y, Game.BonusSpinsDialogPanel.BUTTON_W, Game.BonusSpinsDialogPanel.BUTTON_H, Game.DIALOG_PANEL_H)
                setOnClickListener { controller.bonusSpinsDialogPanelFlow.value = true }
            }

        }
    }

    private fun AdvancedStage.addStartingBalanceSelectionDialog() {
        addAndFillActor(startingBalanceSelectionDialog)
        startingBalanceSelectionDialog.apply {
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
        }

        controller.showStartingBalanceSelectionDialog()
    }

    private fun AdvancedStage.addTrainingDialog() {
        addAndFillActor(trainingDialog)
        trainingDialog.apply {
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
        }
        controller.showTrainingDialog()
    }



    fun addTrainingDialog() {
        stage.addTrainingDialog()
    }

}
