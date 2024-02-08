package com.egyptian.rebirth.dop_papka

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.egyptian.rebirth.Once
import com.egyptian.rebirth.adAppOpen
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.egyptian.rebirth.gremmy.actors.button.ButtonClickable
import com.egyptian.rebirth.gremmy.actors.button.ButtonClickableStyle
import com.egyptian.rebirth.gremmy.actors.label.LabelStyle
import com.egyptian.rebirth.gremmy.actors.label.spinning.SpinningLabel
import com.egyptian.rebirth.gremmy.actors.slot.SlotGroup
import com.egyptian.rebirth.gremmy.actors.slot.SpinResult
import com.egyptian.rebirth.gremmy.GameDataStoreManager
import com.egyptian.rebirth.gremmy.manager.SpriteManager
import com.egyptian.rebirth.gremmy.util.AutospinState
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedGroup
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedScreen
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedStage
import com.egyptian.rebirth.gremmy.util.transformToBalanceFormat
import com.egyptian.rebirth.log

import com.egyptian.rebirth.toMS
import com.egyptian.rebirth.gremmy.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val BET_STEP = 2_500L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 10_000L

        private const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimWhite_50)
    private val betGroup     = AdvancedGroup()
    private val betText      = SpinningLabel("", LabelStyle.akronimWhite_50)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()



    override fun show() {
        adAppOpen?.showAd(gamed.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: (BET_MAX + BET_MAX) } }
        super.show()

        var isShowAppOpenAd = false
        adAppOpen?.let { ad ->
            coroutineMain.launch(Dispatchers.Default) {
                while (isShowAppOpenAd.not()) {
                    delay(3_000L)

                    log("show after 3s")

                    if (ad.isLoaded) {
                        isShowAppOpenAd = true
                        ad.showAd(gamed.activity)
                    }
                }
            }
        }
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

    private var spinCounter   = 0

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
                        listOf(plusButton, minusButton).onEach { it.disable() }

                        coroutineMain.launch {
                            while (autoSpinStateFlow.value == AutospinState.GO) {
                                if (checkBalance()) {
                                    spinAndSetResult()
                                    delay(TIME_WAIT_AFTER_AUTOSPIN.toMS)
                                } else autoSpinStateFlow.value = AutospinState.DEFAULT
                            }

                            spinCounter++
                            if (spinCounter % 2 == 0) gamed.activity.apply { adInterstitial.showAd(this) }

                            spinButton.enable()
                            listOf(plusButton, minusButton).onEach { it.enable() }
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