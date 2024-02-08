package sinet.startup.indrive.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import sinet.startup.indrive.game.actors.button.ButtonClickable
import sinet.startup.indrive.game.actors.button.ButtonClickableStyle
import sinet.startup.indrive.game.actors.label.LabelStyle
import sinet.startup.indrive.game.actors.label.spinning.SpinningLabel
import sinet.startup.indrive.game.actors.masks.normal.Mask
import sinet.startup.indrive.game.actors.slot.SlotGroup
import sinet.startup.indrive.game.actors.slot.SpinResult
import sinet.startup.indrive.game.manager.GameDataStoreManager
import sinet.startup.indrive.game.manager.SpriteManager
import sinet.startup.indrive.game.util.AutospinState
import sinet.startup.indrive.game.util.advanced.AdvancedGroup
import sinet.startup.indrive.game.util.advanced.AdvancedScreen
import sinet.startup.indrive.game.util.advanced.AdvancedStage
import sinet.startup.indrive.game.util.transformToBalanceFormat
import sinet.startup.indrive.util.Once
import sinet.startup.indrive.util.toMS
import sinet.startup.indrive.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val BET_STEP = 100_000L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 500_000L

        private const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimGold_22)
    private val betGroup     = AdvancedGroup()
    private val betMask      = Mask(SpriteManager.GameRegion.BET_MASK.region)
    private val betText      = SpinningLabel("", LabelStyle.akronimGold_22)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: 1_000_000L } }
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
            addActors(betMask, plusButton, minusButton)

            betMask.apply {
                with(LG.betMask) { setBounds(x, y, w, h) }

                addActor(betText)

                betText.apply {
                    with(LG.betText) { setBounds(x, y, w, h) }
                    updateBet()
                }
            }

            plusButton.apply {
                with(LG.plus) { setBounds(x, y, w, h) }
                setOnClickListener { plusHandler() }
            }

            minusButton.apply {
                with(LG.minus) { setBounds(x, y, w, h) }
                setOnClickListener { minusHandler() }
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