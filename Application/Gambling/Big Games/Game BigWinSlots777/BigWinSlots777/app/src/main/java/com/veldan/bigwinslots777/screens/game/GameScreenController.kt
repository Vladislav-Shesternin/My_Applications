package com.veldan.bigwinslots777.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.veldan.bigwinslots777.actors.button.ButtonClickable
import com.veldan.bigwinslots777.actors.miniGameGroup.MiniGameGroup
import com.veldan.bigwinslots777.actors.slot.util.Bonus
import com.veldan.bigwinslots777.actors.slot.util.SpinResult
import com.veldan.bigwinslots777.manager.DataStoreManager
import com.veldan.bigwinslots777.manager.assets.util.MusicUtil
import com.veldan.bigwinslots777.utils.*
import com.veldan.bigwinslots777.utils.controller.ScreenController
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import com.veldan.bigwinslots777.layout.Layout.Game as LG

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP              = 50L
        const val BET_MIN               = 50L
        const val BET_MAX               = 1000L

        var numberSpin = -1
            private set
        var numberCoefficient = -1
            private set
        var numberWild = -1
            private set

        // seconds
        const val TIME_WAIT_AFTER_AUTOSPIN = 1f
        const val TIME_HIDE_GROUP          = 1f
        const val TIME_SHOW_GROUP          = 1f
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutoSpin = Once()

    val isStartMiniGameFlow  = MutableStateFlow(false)
    val isFinishMiniGameFlow = MutableStateFlow(false)

    var miniGameSum = 0L



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
        if (numberSpin.dec() >= 0) {
            numberSpin--
            screen.superGameGroup.elementGroup.elementLabelList[0].setText(numberSpin)
            continuation.complete(true)
        } else {
            DataStoreManager.Balance.update { balance ->
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
        }
    }.await()

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        var sumPrice = (betFlow.value * slotItemPriceFactor).toLong()

        if (MiniGameGroup.isCheckWild.value) {
            miniGameSum = sumPrice
        }

        if (sumPrice > 0 && numberCoefficient > 0) {
            useCoefficient()
            sumPrice *= numberCoefficient
        }

        DataStoreManager.Balance.update { it + sumPrice }

        if (numberSpin == 0) finishSuperGame()
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin().apply {
            when (bonus) {
                Bonus.MINI_GAME  -> startMiniGame()
                Bonus.SUPER_GAME -> startSuperGame()
                else             -> {}
            }
            calculateAndSetResult()
        }
    }

    private suspend fun startSuperGame() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            with(MusicUtil) { currentMusic = SUPER_GAME }
            with(screen) {
                addSuperGameGroup()
                coroutineMain.launch {
                    gameGroup.disable()
                    gameGroup.hideAnim(TIME_HIDE_GROUP)

                    superGameGroup.showAnim(TIME_SHOW_GROUP)
                    superGameGroup.enable()
                    superGameGroup.controller.doAfterFinish = {
                        coroutineMain.launch {
                            doAfterSuperGameFinish(it)
                            continuation.complete(true)
                        }
                    }
                }
            }
        }
    }.await()

    private suspend fun doAfterSuperGameFinish(numberList: List<Int>?) = CompletableDeferred<Boolean>().also { continuation ->
        with(screen) {
            if (numberList == null) {
                superGameGroup.hideAnim(TIME_HIDE_GROUP)
                removeSuperGameGroup()
                gameGroup.showAnim(TIME_SHOW_GROUP)
                gameGroup.enable()
            }
            else {
                numberSpin        = numberList[0]
                numberCoefficient = numberList[1]
                numberWild        = numberList[2]

                superGameGroup.hideAnim(TIME_HIDE_GROUP)
                removeSuperGameGroup()

                addSuperGameElementGroup()
                balancePanelGroup.setPosition(LG.BALANCE_PANEL_SUPER_X, LG.BALANCE_PANEL_SUPER_Y)
                spinButton.setPosition(LG.SPIN_SUPER_X, LG.SPIN_SUPER_Y)
                autoSpinButton.setPosition(LG.AUTO_SPIN_SUPER_X, LG.AUTO_SPIN_SUPER_Y)

                gameGroup.showAnim(TIME_SHOW_GROUP)
                gameGroup.enable()
            }
            with(MusicUtil) { currentMusic = MAIN }
            continuation.complete(true)
        }
    }.await()

    private suspend fun useCoefficient() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            screen.superGameGroup.elementGroup.elementImageList[1].apply {
                setOrigin(Align.center)
                addAction(Actions.sequence(
                    Actions.rotateTo(360f, 0.5f),
                    Actions.rotateTo(-360f, 0.5f),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()

    private suspend fun finishSuperGame() {
        numberSpin        = -1
        numberCoefficient = -1
        numberWild        = -1

        with(screen) {
            superGameGroup.elementGroup.hideAnim(TIME_HIDE_GROUP)
            removeSuperGameElementGroup()

            Gdx.app.postRunnable {
                balancePanelGroup.addAction(Actions.moveTo(LG.BALANCE_PANEL_X, LG.BALANCE_PANEL_Y, 1f))
                spinButton.addAction(Actions.moveTo(LG.SPIN_X, LG.SPIN_Y, 1f))
                autoSpinButton.addAction(Actions.moveTo(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, 1f))
            }
        }
    }

    private suspend fun startMiniGame() = CompletableDeferred<Boolean>().also { continuation ->
        with(screen) {
            isStartMiniGameFlow.emit(false)

            Gdx.app.postRunnable {
                with(MusicUtil) { currentMusic = MINI_GAME }
                addMiniGameStartDialog()
                gameGroup.disable()
            }

            dialogGroup.showAnim(TIME_SHOW_GROUP)

            CoroutineScope(Dispatchers.Default).launch {
                isStartMiniGameFlow.collect {
                    if (it) {
                        dialogGroup.hideAnim(TIME_HIDE_GROUP)
                        Gdx.app.postRunnable {
                            removeMiniGameDialog()
                            gameGroup.enable()
                            gameGroup.children.onEach { actor ->
                                if (actor is ButtonClickable) actor.controller.disable()
                                else actor.disable()
                            }
                            if (autoSpinStateFlow.value == AutospinState.GO) {
                                autoSpinButton.controller.enable()
                                autoSpinButton.controller.press()
                            }
                            musicCheckBox.enable()
                            with(slotGroup) {
                                addMiniGameGroup()
                                enable()
                            }
                        }
                        cancel()
                    }
                }
            }.join()

            CoroutineScope(Dispatchers.Default).launch {
                MiniGameGroup.isCheckWild.collect {
                    if (it) {
                        spinAndSetResult()
                        Gdx.app.postRunnable { addMiniGameFinishDialog() }
                        dialogGroup.showAnim(TIME_SHOW_GROUP)

                        isFinishMiniGameFlow.emit(false)

                        isFinishMiniGameFlow.collect { isFinishMini ->
                            if (isFinishMini) {
                                dialogGroup.hideAnim(TIME_HIDE_GROUP)
                                Gdx.app.postRunnable {
                                    removeMiniGameDialog()
                                    MiniGameGroup.apply {
                                        miniGameSum = 0
                                        slotIndex = -1
                                        rowIndex = -1
                                        isCheckWild.value = false
                                    }
                                    if (autoSpinStateFlow.value == AutospinState.DEFAULT) {
                                        gameGroup.children.onEach { actor ->
                                            if (actor is ButtonClickable) actor.controller.enable()
                                            else actor.enable()
                                        }
                                    }
                                }
                                cancel()
                            }
                        }
                    }
                }
            }.join()

            with(MusicUtil) { currentMusic = MAIN }
            continuation.complete(true)
        }

    }.await()



    fun updateBalance() {
        coroutineMain.launch { DataStoreManager.Balance.collect { balance -> Gdx.app.postRunnable {
            screen.balanceTextLabel.controller.setText(balance.transformToBalanceFormat())
        } } }
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