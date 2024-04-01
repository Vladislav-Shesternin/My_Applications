package com.riseofgiza.velsolution.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.riseofgiza.velsolution.adAppOpen
import com.riseofgiza.velsolution.game.actors.button.ButtonClickable
import com.riseofgiza.velsolution.game.actors.button.ButtonClickableStyle
import com.riseofgiza.velsolution.game.actors.label.LabelStyle
import com.riseofgiza.velsolution.game.actors.label.spinning.SpinningLabel
import com.riseofgiza.velsolution.game.actors.slot.SlotGroup
import com.riseofgiza.velsolution.game.actors.slot.SpinResult
import com.riseofgiza.velsolution.game.game
import com.riseofgiza.velsolution.game.manager.GameDataStoreManager
import com.riseofgiza.velsolution.game.manager.SpriteManager
import com.riseofgiza.velsolution.game.util.advanced.AdvancedGroup
import com.riseofgiza.velsolution.game.util.advanced.AdvancedScreen
import com.riseofgiza.velsolution.game.util.advanced.AdvancedStage
import com.riseofgiza.velsolution.game.util.transformToBalanceFormat
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.riseofgiza.velsolution.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

        private var BET_STEP = 1_000L
        private var BET_MIN  = BET_STEP
        private var BET_MAX  = 5_000L


    private val betFlow = MutableStateFlow(BET_MIN)

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimGold_20)
    private val betGroup     = AdvancedGroup()
    private val betText      = SpinningLabel("", LabelStyle.akronimGold_10)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val spinText     = Label("GO", LabelStyle.akronimGold_10)
    private val slotGroup    = SlotGroup()



    override fun show() {
        adAppOpen?.showAd(game.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: 9_000L } }
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addBalanceGroup()
        addBetGroup()
        addPlusButton()
        addMinusButton()
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
            addAndFillActor(Image(SpriteManager.GameRegion.BALANCE.region))
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
            addAndFillActor(Image(SpriteManager.GameRegion.BET.region))
            addActor(betText)

            betText.apply {
                with(LG.betText) { setBounds(x, y, w, h) }
                updateBet()
            }
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

    private var spinCounter = 0

    private fun AdvancedStage.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            with(LG.spin) { setBounds(x, y, w, h) }
            setOnClickListener { spinHandler() }

            addActor(spinText)
            spinText.apply {
                setAlignment(Align.center)
                with(LG.spinText) { setBounds(x, y, w, h) }
            }
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
                 Gdx.app.postRunnable { balanceText.setText(balance!!.transformToBalanceFormat()+"$") }
            }
        }
    }

    private fun updateBet() {
        coroutineMain.launch {
            betFlow.collect { bet -> betText.setText(bet.transformToBalanceFormat() + "$") }
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
        listOf(spinButton, plusButton, minusButton).onEach { it.disable() }

        coroutineMain.launch {
            if (checkBalance()) spinAndSetResult()

            spinCounter++
            if (spinCounter % 2 == 0) game.activity.apply { adInterstitial.showAd(this) }

            listOf(spinButton, plusButton, minusButton).onEach { it.enable() }
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