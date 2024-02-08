package com.wlfe.astiener.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.wlfe.astiener.game.actors.button.ButtonClickable
import com.wlfe.astiener.game.actors.button.ButtonClickableStyle
import com.wlfe.astiener.game.actors.label.LabelStyle
import com.wlfe.astiener.game.actors.label.spinning.SpinningLabel
import com.wlfe.astiener.game.actors.slot.SlotGroup
import com.wlfe.astiener.game.actors.slot.SpinResult
import com.wlfe.astiener.game.manager.GameDataStoreManager
import com.wlfe.astiener.game.manager.SpriteManager
import com.wlfe.astiener.game.util.AutospinState
import com.wlfe.astiener.game.util.advanced.AdvancedScreen
import com.wlfe.astiener.game.util.advanced.AdvancedStage
import com.wlfe.astiener.game.util.transformToBalanceFormat
import com.wlfe.astiener.util.Once
import com.wlfe.astiener.util.toMS
import com.wlfe.astiener.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val BET_STEP = 200L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 1_000L

        private const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceImage = Image(SpriteManager.GameRegion.BAL.region)
    private val balanceText  = SpinningLabel("", LabelStyle.akronimWhite_50, alignment = Align.right)
    private val betImage     = Image(SpriteManager.GameRegion.BET.region)
    private val betText      = SpinningLabel("", LabelStyle.akronimWhite_50, alignment = Align.right)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()
    private val greatImage   = Image(SpriteManager.ListRegion.GREATS.regionList.shuffled().first())

    private val groupImage   = Image(SpriteManager.CommonRegion.BACKGROUND.region)


    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: 5_000L } }
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addActor(groupImage)
        groupImage.setBounds(17f, 104f, 1379f, 594f)

        addSlotGroup()
        addBalanceGroup()
        addBetGroup()
        addPlusButton()
        addMinusButton()
        addGreatImage()
        addSpinButton()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBalanceGroup() {
        addActors(balanceImage, balanceText)

        balanceImage.apply {
            with(LG.balancePanel) { setBounds(x, y, w, h) }
        }

        balanceText.apply {
            with(LG.balanceText) { setBounds(x, y, w, h) }
            updateBalance()
        }

    }

    private fun AdvancedStage.addBetGroup() {
        addActors(betImage, betText)

        betImage.apply {
            with(LG.betPanel) { setBounds(x, y, w, h) }
        }

        betText.apply {
            with(LG.betText) { setBounds(x, y, w, h) }
            updateBet()
        }

    }

    private fun AdvancedStage.addPlusButton() {
        addActor(plusButton)
        plusButton.apply {
            with(LG.plus) { setBounds(x, y, w, h) }
            setOnClickListener { plusHandler() }
        }

    }

    private fun AdvancedStage.addMinusButton() {
        addActor(minusButton)
        minusButton.apply {
            with(LG.minus) { setBounds(x, y, w, h) }
            setOnClickListener { minusHandler() }
        }

    }

    private fun AdvancedStage.addSpinButton() {
        addActors(spinButton)
        spinButton.apply {
            with(LG.spin) { setBounds(x, y, w, h) }

            setOnClickListener { spinHandler() }
        }

    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        with(LG.slotGroup) { slotGroup.setBounds(x, y, w, h) }
    }

    private fun AdvancedStage.addGreatImage() {
        addActor(greatImage)
        greatImage.apply {
            with(LG.great) { setBounds(x, y, w, h) }

            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, LG.great.h, 5f),
                Actions.moveBy(0f, -LG.great.h, 5f),
            )))
        }

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

    private fun updateBet() {
        coroutineMain.launch {
            betFlow.collect { bet -> betText.setText(bet.transformToBalanceFormat()) }
        }
    }

    private fun plusHandler() {
        coroutineMain.launch {
            with(betFlow) {
                value = if ((value + BET_STEP) < BET_MAX) value + BET_STEP else BET_MAX
            }
        }
    }

    private fun minusHandler() {
        coroutineMain.launch {
            with(betFlow) {
                value = if ((value - BET_STEP) > BET_MIN) value - BET_STEP else BET_MIN
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
                        plusButton.disable()
                        minusButton.disable()

                        coroutineMain.launch {
                            while (autoSpinStateFlow.value == AutospinState.GO) {
                                if (checkBalance()) {
                                    spinAndSetResult()
                                    delay(TIME_WAIT_AFTER_AUTOSPIN.toMS)
                                } else autoSpinStateFlow.value = AutospinState.DEFAULT
                            }

                            spinButton.enable()
                            plusButton.enable()
                            minusButton.enable()
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