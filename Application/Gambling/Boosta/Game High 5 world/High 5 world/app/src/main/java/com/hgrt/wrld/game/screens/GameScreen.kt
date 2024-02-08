package com.hgrt.wrld.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.hgrt.wrld.game.actors.button.ButtonClickable
import com.hgrt.wrld.game.actors.button.ButtonClickableStyle
import com.hgrt.wrld.game.actors.checkbox.CheckBox
import com.hgrt.wrld.game.actors.checkbox.CheckBoxGroup
import com.hgrt.wrld.game.actors.label.LabelStyle
import com.hgrt.wrld.game.actors.label.spinning.SpinningLabel
import com.hgrt.wrld.game.actors.slot.SlotGroup
import com.hgrt.wrld.game.actors.slot.SpinResult
import com.hgrt.wrld.game.manager.GameDataStoreManager
import com.hgrt.wrld.game.manager.SpriteManager
import com.hgrt.wrld.game.util.AutospinState
import com.hgrt.wrld.game.util.advanced.AdvancedGroup
import com.hgrt.wrld.game.util.advanced.AdvancedScreen
import com.hgrt.wrld.game.util.advanced.AdvancedStage
import com.hgrt.wrld.game.util.disable
import com.hgrt.wrld.game.util.enable
import com.hgrt.wrld.game.util.transformToBalanceFormat
import com.hgrt.wrld.util.Once
import com.hgrt.wrld.util.toMS
import com.hgrt.wrld.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val BET_STEP = 1_000L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 5_000L

        private const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimGold_40)
    private val betGroup     = AdvancedGroup()
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: (BET_MAX * 2) } }
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addBalanceGroup()
        addBetGroup()
        addSpinButton()
        addSlotGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBalanceGroup() {
        addActor(balanceGroup)
        balanceGroup.apply {
            with(LG.balancePanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(com.hgrt.wrld.game.manager.SpriteManager.GameRegion.BALANCE.region))
            addActor(balanceText)

            balanceText.apply {
                with(LG.balanceText) { setBounds(x, y, w, h) }
                updateBalance()
            }
        }

    }

    private fun AdvancedStage.addBetGroup() {
        addActor(betGroup)
        betGroup.apply {
            with(LG.betPanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(com.hgrt.wrld.game.manager.SpriteManager.GameRegion.BET.region))
        }

        val cbGroup = CheckBoxGroup()
        var newY = LG.betCheckBox.y

        List(4) { CheckBox(com.hgrt.wrld.game.actors.checkbox.CheckBoxStyle.bet) }.zip(listOf(50, 100, 500, 1000)).onEachIndexed { index, pair ->
            with(pair.first) {
                betGroup.addActor(this)
                with(LG.betCheckBox) {
                    setBounds(x, newY, w, h)
                    newY += h + vs
                }
                checkBoxGroup = cbGroup

                setOnCheckListener {
                    if (it) betFlow.value = pair.second.toLong()
                }

                if (index == 0) check()
            }
        }

    }

    private fun AdvancedStage.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            with(LG.spin) { setBounds(x, y, w, h) }
            setOnClickListener { spinHandler() }
        }

    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        with(LG.slotGroup) { slotGroup.setBounds(x, y, w, h) }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun updateBalance() {
        coroutineMain.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.collect { balance ->
                 Gdx.app.postRunnable { balanceText.setText(balance!!.transformToBalanceFormat()) }
            }
        }
    }

    private fun spinHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }

    private fun startAutospin() {
        coroutineMain.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        spinButton.press()
                        betGroup.disable()

                        coroutineMain.launch {
                            while (autoSpinStateFlow.value == AutospinState.GO) {
                                if (checkBalance()) {
                                    spinAndSetResult()
                                    delay(TIME_WAIT_AFTER_AUTOSPIN.toMS)
                                } else autoSpinStateFlow.value = AutospinState.DEFAULT
                            }

                            spinButton.enable()
                            betGroup.enable()
                        }
                    }

                    AutospinState.DEFAULT -> spinButton.disable()
                }
            }
        }
    }

    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        GameDataStoreManager.Balance.update {
            var balance = it!!
            if ((balance - betFlow.value) >= 0) { // Достаточно средств для запуска
                continuation.complete(true)
                balance -= betFlow.value
            } else continuation.complete(false) // Недостаточно средств для запуска
            balance
        }
    }.await()

    private suspend fun spinAndSetResult() {
        slotGroup.spin().apply { calculateAndSetResult() }
    }

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceCoff }?.sum() ?: 0f
        val sumPrice = (betFlow.value * slotItemPriceFactor).toLong()
        coroutineMain.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

}