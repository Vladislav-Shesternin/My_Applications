package com.veldan.fantasticslots.actors.starting_balance_selection_dialog

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.BalanceButton
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.listeners.toClickable
import com.veldan.fantasticslots.manager.DataStoreManager
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.log
import kotlinx.coroutines.launch
import com.veldan.fantasticslots.utils.StartingBalanceSelectionDialog as SBSD

class StartingBalanceSelectionDialog: AbstractAdvancedGroup() {

    override val controller = StartingBalanceSelectionDialogController(this)

    private val backgroundImage   = Image(SpriteManager.GameSprite.STARTING_BALANCE_BACKGROUND.data.texture)
    private val backArrowImage    = Image(SpriteManager.GameSprite.BACK_ARROW.data.texture)
    private val textLabel         = RollingLabel(activityResources.getString(R.string.choose_starting_balance), LabelStyleUtil.robotoMono60)
    private val currentLabel      = RollingLabel(activityResources.getString(R.string.current), LabelStyleUtil.robotoMono60)
    private val balanceButtonList = MutableList(3) { BalanceButton() }

    var doAfterSelectBalance: () -> Unit = { }



    init {
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addBackground()
        addTextLabel()
        addBackArrow()
        addBalanceButtonGroup()
    }

    private fun addBackground() {
        addAndFillActor(backgroundImage)
    }

    private fun addTextLabel() {
        addActor(textLabel)
        textLabel.setBoundsFigmaY(SBSD.TEXT_X, SBSD.TEXT_Y, SBSD.TEXT_W, SBSD.TEXT_H)
    }

    private fun addBackArrow() {
        addActor(backArrowImage)
        backArrowImage.apply {
            setBoundsFigmaY(SBSD.BACK_ARROW_X, SBSD.BACK_ARROW_Y, SBSD.BACK_ARROW_W, SBSD.BACK_ARROW_H)
            toClickable().setOnClickListener { NavigationManager.back() }
        }
    }

    private fun addBalanceButtonGroup() {
        var newX       = 0f

        controller.coroutineDataStore.launch { DataStoreManager.getBalance().also { balance ->
            val layout = if (balance != 0L) {
                balanceButtonList.add(BalanceButton())
                controller.balanceList.add(0, balance)
                addCurrentLabel()
                SBSD.WithCurrent
            } else SBSD.WithoutCurrent

            newX = layout.FIRST_X
            balanceButtonList.onEachIndexed { index, button ->
                addActor(button)

                button.apply {
                    setBalance(controller.balanceList[index])
                    setBoundsFigmaY(newX, SBSD.BUTTON_Y, SBSD.BUTTON_W, SBSD.BUTTON_H)
                    newX += SBSD.BUTTON_W + layout.SPACE_HORIZONTAL
                    button.setOnClickListener {
                        controller.coroutineDataStore.launch {
                            DataStoreManager.updateBalance { controller.balanceList[index] }
                            doAfterSelectBalance()
                        }
                    }
                }
            }
        } }
    }
    
    private fun addCurrentLabel() {
        addActor(currentLabel)
        currentLabel.setBoundsFigmaY(SBSD.CURRENT_X, SBSD.CURRENT_Y, SBSD.CURRENT_W, SBSD.CURRENT_H)
    }

}