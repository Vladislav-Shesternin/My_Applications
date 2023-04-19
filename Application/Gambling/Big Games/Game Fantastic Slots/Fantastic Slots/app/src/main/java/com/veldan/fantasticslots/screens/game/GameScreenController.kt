package com.veldan.fantasticslots.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.roulette.SuperRoulette
import com.veldan.fantasticslots.actors.slot.SlotItem
import com.veldan.fantasticslots.actors.slot.SpinResult
import com.veldan.fantasticslots.assets.util.MusicUtil
import com.veldan.fantasticslots.manager.DataStoreManager
import com.veldan.fantasticslots.screens.options.OptionsScreen
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.controller.ScreenController
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class GameScreenController(
    override val screen: GameScreen
): ScreenController, Disposable {

    companion object {
        const val BET_STEP = 50L
        const val BET_MIN  = 50L
        const val BET_MAX  = 1000L

        const val BONUS_SPINS = 5

        // seconds
        const val TIME_SHOW_STARTING_BALANCE_SELECTION_DIALOG = 1f
        const val TIME_HIDE_STARTING_BALANCE_SELECTION_DIALOG = 1f
        const val TIME_SHOW_TRAINING_DIALOG                   = 1f
        const val TIME_HIDE_TRAINING_DIALOG                   = 1f
        const val TIME_WAIT_SHOW_NEW_BALANCE                  = 1f
        const val TIME_ACTOR_FADE_OUT                         = 0.4f
        const val TIME_ACTOR_FADE_IN                          = 0.4f
        const val TIME_SUPER_ACTOR_FADE_OUT                   = 1f
        const val TIME_SUPER_ACTOR_FADE_IN                    = 1f

        var superGameSlotItem: SlotItem? = null
            private set
    }

    val coroutineBalance    = CoroutineScope(Dispatchers.Default)
    val coroutineBet        = CoroutineScope(Dispatchers.Default)
    val coroutineAutospin   = CoroutineScope(Dispatchers.Default)
    val coroutineSpin       = CoroutineScope(Dispatchers.Default)
    val coroutineBonusSpins = CoroutineScope(Dispatchers.Default)

    val betFlow                   = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val autospinStateFlow         = MutableStateFlow(AutospinState.DEFAULT)
    val bonusSpinsFlow            = MutableStateFlow<Int?>(null)
    val bonusSpinsDialogPanelFlow = MutableStateFlow<Boolean?>(null)

    var isSuperGame = false
        private set

    var balanceBonusSpins = 0L
        private set

    var spinBlock: () -> Unit = { spinHandler() }
        private set

    val onceStartAutospin = Once()
    val tutorial = Tutorial(this)



    override fun dispose() {
        tutorial.dispose()
        cancelCoroutinesAll(coroutineBalance, coroutineBet, coroutineAutospin, coroutineSpin, coroutineBonusSpins)
    }



    private fun startAutospin() {
        coroutineAutospin.launch {
            autospinStateFlow.collect { state ->

                when (state) {
                    AutospinState.GO      -> {
                        with(screen) {
                            autospinButton.press()
                            spinButton.disable()
                            betPlusButton.disable()
                            betMinusButton.disable()

                            CoroutineScope(Dispatchers.Default).launch {
                                while (autospinStateFlow.value == AutospinState.GO) {
                                    if (checkBalance()) {
                                        spinAndGetResult()
                                        if (isSuperGame.not()) delay(TIME_WAIT_SHOW_NEW_BALANCE.toDelay)
                                    }
                                    else autospinStateFlow.value = AutospinState.DEFAULT
                                }

                                if (isSuperGame.not()) {
                                    MusicUtil.apply { currentMusic = MAIN }
                                    autospinButton.enable()
                                    spinButton.enable()
                                    betPlusButton.enable()
                                    betMinusButton.enable()
                                }

                                cancel()
                            }
                        }
                    }

                    AutospinState.DEFAULT -> screen.autospinButton.disable()
                }
            }
        }
    }

    private fun spinHandler() {
        with(screen) {
            spinButton.pressAndDisable(false)
            autospinButton.disable()
            betPlusButton.disable()
            betMinusButton.disable()

            coroutineSpin.launch {
                if (checkBalance())
                    spinAndGetResult()
                if (isSuperGame.not()) {
                    spinButton.enable()
                    autospinButton.enable()
                    betPlusButton.enable()
                    betMinusButton.enable()
                }
            }
        }
    }

    private fun superSpinHandler() {
        MusicUtil.apply { currentMusic = ROULETTE }
        with(screen) {
            Gdx.app.postRunnable {
                spinButton.disable(false)
                spinButton.addAction(Actions.fadeOut(TIME_ACTOR_FADE_OUT))
            }

            coroutineSpin.launch {
                superRoulette.spin().apply { (this as SuperRoulette.RouletteItem).apply {
                    MusicUtil.apply { currentMusic = MAIN }
                    superGameSlotItem = slotItem
                    slotItemEquallyWildImage.drawable = TextureRegionDrawable(slotItem.texture)
                } }

                isSuperGame = false
                bonusSpinsFlow.value = BONUS_SPINS
                spinBlock = { spinHandler() }

                listOf<Actor>(
                    equallyWildImage, slotItemEquallyWildImage,
                    bonusSpinsLabel,
                    balanceLabel    , balancePanelGroup       ,
                    betLabel        , betPanelGroup           ,
                    betMinusButton  , betPlusButton           ,
                    menuButton      ,
                ).onEach { actor ->
                    Gdx.app.postRunnable { actor.addAction(Actions.fadeIn(TIME_ACTOR_FADE_IN)) }
                    delay(TIME_ACTOR_FADE_IN.toDelay)
                    if (actor is ButtonClickable) actor.enable() else actor.enable()
                }
                Gdx.app.postRunnable {
                    superRoulette.addAction(Actions.fadeOut(TIME_SUPER_ACTOR_FADE_OUT))
                    indicatorImage.addAction(Actions.fadeOut(TIME_SUPER_ACTOR_FADE_OUT))
                    slotGroup.addAction(Actions.fadeIn(TIME_SUPER_ACTOR_FADE_IN))
                }
                delay(TIME_SUPER_ACTOR_FADE_IN.toDelay)

                listOf<ButtonClickable>(autospinButton, spinButton).onEach { button ->
                    Gdx.app.postRunnable { button.addAction(Actions.fadeIn(TIME_ACTOR_FADE_IN)) }
                    delay(TIME_ACTOR_FADE_IN.toDelay)
                    button.enable()
                }

            }

        }
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

    private suspend fun checkBonusSpins() = CompletableDeferred<Boolean>().also { continuation ->
        if (bonusSpinsFlow.value == 0) {
            MusicUtil.apply { currentMusic = SUPER_WIN }

            Gdx.app.postRunnable { with(screen) {
                listOf<Actor>(slotItemEquallyWildImage, equallyWildImage, bonusSpinsLabel).onEach { actor -> actor.addAction(Actions.fadeOut(TIME_ACTOR_FADE_OUT)) }
                dialogPanelBottomLabel.apply { setText(activityResources.getString(R.string.bonus_spins_earnings) + balanceBonusSpins.toString()) }
                dialogPanelGroup.apply {
                    addAction(Actions.fadeIn(TIME_SUPER_ACTOR_FADE_IN))
                    enable()
                }
            } }

            coroutineBonusSpins.launch {
                bonusSpinsDialogPanelFlow.collect { it?.let {
                    Gdx.app.postRunnable { screen.dialogPanelGroup.apply {
                        addAction(Actions.fadeOut(TIME_SUPER_ACTOR_FADE_OUT))
                        disable()
                    } }
                    delay(TIME_SUPER_ACTOR_FADE_OUT.toDelay + TIME_EXTRA)

                    bonusSpinsDialogPanelFlow.value = null
                    bonusSpinsFlow.value            = null
                    superGameSlotItem               = null
                    balanceBonusSpins               = 0L

                    continuation.complete(true)
                }
                }
            }

        } else continuation.complete(true)
    }.await()

    private suspend fun spinAndGetResult() {
        bonusSpinsFlow.value?.let { bonusSpinsFlow.value = it - 1 }

        screen.slotGroup.spin().apply {
            calculateAndSetResult()
            checkBonusSpins()
            bonus?.let { startSuperGame() }
        }

        MusicUtil.apply { currentMusic = MAIN }
    }

    private suspend fun startSuperGame() {
        MusicUtil.apply { currentMusic = SUPER_GAME }
        isSuperGame = true
        if (autospinStateFlow.value == AutospinState.GO) autospinStateFlow.value = AutospinState.DEFAULT

        spinBlock = { superSpinHandler() }

        with(screen) {
            listOf<Actor>(
                balanceLabel  , balancePanelGroup,
                betLabel      , betPanelGroup    ,
                betMinusButton, betPlusButton    ,
                autospinButton, menuButton       ,
            ).onEach { actor ->
                Gdx.app.postRunnable {
                    actor.disable()
                    actor.addAction(Actions.fadeOut(TIME_ACTOR_FADE_OUT))
                }
                delay(TIME_ACTOR_FADE_OUT.toDelay)
            }
            Gdx.app.postRunnable {
                slotGroup.addAction(Actions.fadeOut(TIME_SUPER_ACTOR_FADE_OUT))
                superRoulette.addAction(Actions.fadeIn(TIME_SUPER_ACTOR_FADE_IN))
            }
            delay(TIME_SUPER_ACTOR_FADE_IN.toDelay)
            Gdx.app.postRunnable { indicatorImage.addAction(Actions.fadeIn(TIME_ACTOR_FADE_IN)) }
            delay(TIME_ACTOR_FADE_IN.toDelay + TIME_EXTRA)
            spinButton.enable()
        }
    }

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        val sumPrice = (betFlow.first() * slotItemPriceFactor).toLong()
        DataStoreManager.updateBalance { it + sumPrice }

        bonusSpinsFlow.value?.let { balanceBonusSpins += sumPrice }
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

    fun autospinHandler() {
        autospinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutospin.once { startAutospin() }
    }

    fun collectBonusSpins() {
        coroutineBonusSpins.launch { bonusSpinsFlow.collect { it?.let { count ->
            screen.bonusSpinsLabel.apply { setText("$text $count") }
        } } }
    }

    fun showStartingBalanceSelectionDialog() {
        screen.startingBalanceSelectionDialog.apply {
            Gdx.app.postRunnable { addAction(Actions.fadeIn(TIME_SHOW_STARTING_BALANCE_SELECTION_DIALOG)) }

            doAfterSelectBalance = {
                Gdx.app.postRunnable { addAction(Actions.sequence(
                    Actions.parallel(
                        Actions.fadeOut(TIME_HIDE_STARTING_BALANCE_SELECTION_DIALOG),
                        Actions.run { if (OptionsScreen.isCheckedTraining) screen.addTrainingDialog() }
                    ),
                    Actions.run { remove() },
                )) }
            }
        }
    }

    fun showTrainingDialog() {
        screen.trainingDialog.apply {
            Gdx.app.postRunnable { addAction(Actions.fadeIn(TIME_SHOW_TRAINING_DIALOG)) }

            doAfterSkip = {
                OptionsScreen.isCheckedTraining = false
                Gdx.app.postRunnable { addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_TRAINING_DIALOG),
                    Actions.run { remove() }
                )) }
            }
            doAfterStart = {
                Gdx.app.postRunnable { addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_TRAINING_DIALOG),
                    Actions.run {
                        remove()
                        tutorial.start()
                    }
                )) }
            }
        }
    }
    


    enum class AutospinState {
        DEFAULT, GO,
    }

}