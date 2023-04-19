package com.veldan.pinup.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.actors.slot.util.Bonus
import com.veldan.pinup.actors.slot.util.SpinResult
import com.veldan.pinup.manager.DataStoreManager
import com.veldan.pinup.manager.assets.util.MusicUtil
import com.veldan.pinup.utils.*
import com.veldan.pinup.utils.controller.ScreenController
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP = 50L
        const val BET_MIN  = 50L
        const val BET_MAX  = 1000L

        // seconds
        const val TIME_SHOW_SCREEN = 1f
        const val TIME_HIDE_SCREEN = 1f
    }

    private val coroutineBalance        = CoroutineScope(Dispatchers.Default)
    private val coroutineBet            = CoroutineScope(Dispatchers.Default)
    private val coroutineSpin           = CoroutineScope(Dispatchers.Default)
    private val coroutineAutoSpin       = CoroutineScope(Dispatchers.Default)
    private val coroutineMiniGame       = CoroutineScope(Dispatchers.Default)
    private val coroutineSuperGame      = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutoSpin = Once()



    override fun dispose() {
        cancelCoroutinesAll(coroutineBalance, coroutineBet, coroutineSpin, coroutineAutoSpin, coroutineMiniGame, coroutineSuperGame)
    }



    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        DataStoreManager.updateBalance { balance ->
            if ((balance - betFlow.first()) >= 0) {
                // Достаточно средств для запуска
                continuation.complete(true)
                balance - betFlow.first()
            } else {
                // Недостаточно средств для запуска
                continuation.complete(false)
                balance
            }
        }
    }.await()

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        val sumPrice = (betFlow.first() * slotItemPriceFactor).toLong()
        DataStoreManager.updateBalance { it + sumPrice }
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin().apply {
            when (bonus) {
                Bonus.MINI_GAME  -> startMiniGame()
                Bonus.SUPER_GAME -> startSuperGame()
                else -> {}
            }
            calculateAndSetResult()
        }
    }

    private fun startAutospin() {
        coroutineAutoSpin.launch {
            autoSpinStateFlow.collect { state -> when (state) {
                AutospinState.GO      -> { with(screen) {
                    autoSpinButton.press()
                    spinButton.disable()
                    betPlusButton.disable()
                    betMinusButton.disable()

                    CoroutineScope(Dispatchers.Default).launch {
                        while (autoSpinStateFlow.value == AutospinState.GO) {
                            if (checkBalance()) spinAndSetResult()
                            else autoSpinStateFlow.value = AutospinState.DEFAULT
                        }

                        autoSpinButton.enable()
                        spinButton.enable()
                        betPlusButton.enable()
                        betMinusButton.enable()

                        cancel()
                    }
                } }

                AutospinState.DEFAULT -> screen.autoSpinButton.disable()
            } }
        }
    }

    private suspend fun startMiniGame() = CompletableDeferred<Boolean>().also { continuation ->
        coroutineMiniGame.launch {
            with(MusicUtil) { currentMusic = MINI_GAME }
            screen.addMiniGame()
            val completableAnimHideGame = CompletableDeferred<Boolean>()

            Gdx.app.postRunnable {
                screen.gameGroup.addAction(
                    Actions.sequence(
                        Actions.fadeOut(TIME_HIDE_SCREEN),
                        Actions.run {
                            screen.gameGroup.disable()
                            completableAnimHideGame.complete(true)
                        },
                    )
                )
            }

            completableAnimHideGame.await()

            screen.miniGame.apply {
                enable()
                addAction(Actions.alpha(1f))
                controller.start(betFlow.first())

                controller.doAfterFinish = { bonus ->
                    Gdx.app.postRunnable {
                        with(MusicUtil) { currentMusic = MAIN }
                        addAction(
                            Actions.sequence(
                                Actions.fadeOut(TIME_HIDE_SCREEN),
                                Actions.removeActor()
                            )
                        )

                        screen.gameGroup.addAction(
                            Actions.sequence(
                                Actions.fadeIn(TIME_SHOW_SCREEN),
                                Actions.run {
                                    coroutineBalance.launch { DataStoreManager.updateBalance { it + bonus } }
                                    screen.gameGroup.enable()
                                    continuation.complete(true)
                                },
                            )
                        )
                    }
                }
            }

        }
    }.await()

    private suspend fun startSuperGame() = CompletableDeferred<Boolean>().also { continuation ->
        coroutineSuperGame.launch {
            with(MusicUtil) { currentMusic = SUPER_GAME }
            screen.addSuperGame()
            val completableAnimHideGame = CompletableDeferred<Boolean>()

            Gdx.app.postRunnable {
                screen.gameGroup.addAction(
                    Actions.sequence(
                        Actions.fadeOut(TIME_HIDE_SCREEN),
                        Actions.run {
                            screen.gameGroup.disable()
                            completableAnimHideGame.complete(true)
                        },
                    )
                )
            }

            completableAnimHideGame.await()

            screen.superGame.apply {
                enable()
                addAction(Actions.fadeIn(TIME_SHOW_SCREEN))
                controller.start(betFlow.first())

                controller.doAfterFinish = { bonus ->
                    Gdx.app.postRunnable {
                        with(MusicUtil) { currentMusic = MAIN }
                        addAction(
                            Actions.sequence(
                                Actions.fadeOut(TIME_HIDE_SCREEN),
                                Actions.removeActor()
                            )
                        )

                        screen.gameGroup.addAction(
                            Actions.sequence(
                                Actions.fadeIn(TIME_SHOW_SCREEN),
                                Actions.run {
                                    coroutineBalance.launch { DataStoreManager.updateBalance { it + bonus } }
                                    screen.gameGroup.enable()
                                    continuation.complete(true)
                                },
                            )
                        )

                    }
                }
            }
        }
    }.await()


    fun updateBalance() {
        coroutineBalance.launch { DataStoreManager.collectBalance { balance -> Gdx.app.postRunnable {
            screen.balanceTextLabel.setText(balance.transformToBalanceFormat())
        } } }
    }

    fun updateBet() {
        coroutineBet.launch { with(betFlow) {
            emit(BET_MIN)
            collect { bet -> Gdx.app.postRunnable { screen.betTextLabel.setText(bet.transformToBalanceFormat()) } }
        } }
    }

    fun betPlusHandler() {
        coroutineBet.launch { with(betFlow) {
            val emitValue = if ((first() + BET_STEP) < BET_MAX) first() + BET_STEP else BET_MAX
            emit(emitValue)
        } }
    }

    fun betMinusHandler() {
        coroutineBet.launch { with(betFlow) {
            val emitValue = if ((first() - BET_STEP) > BET_MIN) first() - BET_STEP else BET_MIN
            emit(emitValue)
        } }
    }

    fun spinHandler() {
        with(screen) {
            spinButton.pressAndDisable(false)
            autoSpinButton.disable()
            betPlusButton.disable()
            betMinusButton.disable()

            coroutineSpin.launch {
                if (checkBalance()) {
                    spinAndSetResult()

                    spinButton.enable()
                    autoSpinButton.enable()
                    betPlusButton.enable()
                    betMinusButton.enable()
                }
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