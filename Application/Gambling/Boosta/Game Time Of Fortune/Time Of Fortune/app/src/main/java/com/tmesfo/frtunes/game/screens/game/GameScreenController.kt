package com.tmesfo.frtunes.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import com.tmesfo.frtunes.game.actors.slot.util.SpinResult
import com.tmesfo.frtunes.game.manager.GameDataStoreManager
import com.tmesfo.frtunes.game.utils.Once
import com.tmesfo.frtunes.game.utils.controller.ScreenController
import com.tmesfo.frtunes.util.cancelCoroutinesAll
import com.tmesfo.frtunes.util.toDelay
import com.tmesfo.frtunes.util.transformToBalanceFormat

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP              = 50L
        const val BET_MIN               = 50L
        const val BET_MAX               = 1000L

        // seconds
        const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutoSpin = Once()



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    private fun startAutospin() {
        coroutineMain.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        with(screen) {
                            autoSpinButton.controller.press()
                            spinButton.controller.disable()
                            betPlusButton.controller.disable()
                            betMinusButton.controller.disable()

                            CoroutineScope(Dispatchers.Default).launch {
                                while (autoSpinStateFlow.value == AutospinState.GO) {
                                    if (checkBalance()) {
                                        spinAndSetResult()
                                        delay(TIME_WAIT_AFTER_AUTOSPIN.toDelay)
                                    }
                                    else autoSpinStateFlow.value = AutospinState.DEFAULT
                                }

                                autoSpinButton.controller.enable()
                                spinButton.controller.enable()
                                betPlusButton.controller.enable()
                                betMinusButton.controller.enable()
                                cancel()
                            }
                        }
                    }

                    AutospinState.DEFAULT -> screen.autoSpinButton.controller.disable()
                }
            }
        }
    }

    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        GameDataStoreManager.Balance.updateNotNull { balance ->
            if ((balance - betFlow.value) >= 0) {
                // Достаточно средств для запуска
                continuation.complete(true)
                balance - betFlow.value
            } else {
                // Недостаточно средств для запуска
                continuation.complete(false)
                balance
            }
        }
    }.await()

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        val sumPrice = (betFlow.value * slotItemPriceFactor).toLong()
        GameDataStoreManager.Balance.updateNotNull { it + sumPrice }
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin().apply { calculateAndSetResult() }
    }



    fun updateBalance() {
        coroutineMain.launch { GameDataStoreManager.Balance.collectNotNull { balance ->
            Gdx.app.postRunnable { screen.balanceTextLabel.controller.setText(balance.transformToBalanceFormat()) }
        } }
    }

    fun updateBet() {
        coroutineMain.launch { with(betFlow) {
            emit(BET_MIN)
            collect { bet -> Gdx.app.postRunnable { screen.betTextLabel.controller.setText(bet.transformToBalanceFormat()) } }
        } }
    }

    fun betPlusHandler() {
        coroutineMain.launch { with(betFlow) {
            value = if ((value + BET_STEP) < BET_MAX) value + BET_STEP else BET_MAX
        } }
    }

    fun betMinusHandler() {
        coroutineMain.launch { with(betFlow) {
            value = if ((value - BET_STEP) > BET_MIN) value - BET_STEP else BET_MIN
        } }
    }

    fun spinHandler() {
        with(screen) {
            spinButton.controller.pressAndDisable(false)
            autoSpinButton.controller.disable()
            betPlusButton.controller.disable()
            betMinusButton.controller.disable()

            coroutineMain.launch {
                if (checkBalance()) spinAndSetResult()

                spinButton.controller.enable()
                autoSpinButton.controller.enable()
                betPlusButton.controller.enable()
                betMinusButton.controller.enable()
            }
        }
    }

    fun autoSpinHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }


    enum class AutospinState {
        DEFAULT, GO,
    }

}