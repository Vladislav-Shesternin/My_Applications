package com.aztec.blaze.game.screens

import com.aztec.blaze.adAppOpen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.aztec.blaze.game.actors.button.ButtonClickable
import com.aztec.blaze.game.actors.button.ButtonClickableStyle
import com.aztec.blaze.game.actors.label.LabelStyle
import com.aztec.blaze.game.actors.label.spinning.SpinningLabel
import com.aztec.blaze.game.actors.slot.SlotGroup
import com.aztec.blaze.game.actors.slot.SpinResult
import com.aztec.blaze.game.game
import com.aztec.blaze.game.manager.GameDataStoreManager
import com.aztec.blaze.game.manager.SpriteManager
import com.aztec.blaze.game.util.AutospinState
import com.aztec.blaze.game.util.advanced.AdvancedGroup
import com.aztec.blaze.game.util.advanced.AdvancedScreen
import com.aztec.blaze.game.util.advanced.AdvancedStage
import com.aztec.blaze.game.util.disable
import com.aztec.blaze.game.util.enable
import com.aztec.blaze.game.util.transformToBalanceFormat
import com.aztec.blaze.util.Once
import com.aztec.blaze.util.toMS
import com.aztec.blaze.game.util.Layout.Game as LG

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
    private val betText      = SpinningLabel("", LabelStyle.akronimGold_40)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()



    override fun show() {
        adAppOpen?.showAd(game.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: (BET_MAX * 2) } }
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addBalanceGroup()
        addBetGroup()
        addPlusButton()
        addMinusButton()
        addSpinButton()
        addSlotGroup()

        coroutineMain.launch {
            val g = AdvancedGroup()
            val i = Image(SpriteManager.GameRegion.ANIM_WILD.region)
            val mutable = MutableStateFlow((-360..360).random().toFloat())

            addActor(g)
            g.apply {
                with(LG.animGroup) { setBounds(x, y, w, h) }
                setOrigin(Align.center)

                addActor(i)
                addAction(
                    Actions.forever(
                        Actions.rotateBy(360f, 5f)
                    )
                )
            }

            i.apply {
                with(LG.animGroup) { setSize(w, w) }
                setOrigin(Align.center)

                mutable.collect {
                    addAction(Actions.sequence(
                        Actions.rotateBy(it, 0.5f),
                        Actions.run { mutable.value = (-360..360).random().toFloat() }
                    ))
                }
            }
        }
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

    private var spinCounter = 0

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

                            spinCounter++
                            if (spinCounter % 2 == 0) game.activity.apply { adInterstitial.showAd(this) }

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
            com.aztec.blaze.game.manager.GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

}