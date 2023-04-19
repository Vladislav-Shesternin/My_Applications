package com.pharhaslo.slo7.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.pharhaslo.slo7.game.actors.ButtonClickable
import com.pharhaslo.slo7.game.actors.slot.Bonus
import com.pharhaslo.slo7.game.actors.slot.GoResult
import com.pharhaslo.slo7.game.actors.slot.SlotGroup
import com.pharhaslo.slo7.game.actors.slot.SuperGameGroup
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.advanced.AdvancedScreen
import com.pharhaslo.slo7.game.advanced.AdvancedStage
import com.pharhaslo.slo7.game.assets.FontTTFManager
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.MusicUtil
import com.pharhaslo.slo7.game.utils.*
import com.pharhaslo.slo7.game.languageSprite
import com.pharhaslo.slo7.game.manager.DataStoreManager
import com.pharhaslo.slo7.game.manager.NavigationManager
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import com.pharhaslo.slo7.game.utils.SlotGroup as SG
import com.pharhaslo.slo7.game.advanced.AdvancedGroup.AlignmentHorizontal as AH
import com.pharhaslo.slo7.game.advanced.AdvancedGroup.AlignmentVertical as AV

class GameScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    companion object {
        const val BET_STEP = 50L
        const val BET_MIN  = 50L
        const val BET_MAX  = 1000L

        // seconds
        const val TIME_SHOW_FREE_GO = 1f

        // milliseconds
        const val TIME_EXTRA = 50L
    }

    private val coroutineBalance  = CoroutineScope(Dispatchers.Default)
    private val coroutineBet      = CoroutineScope(Dispatchers.Default)
    private val coroutineGo       = CoroutineScope(Dispatchers.Default)
    private val coroutineAutogo   = CoroutineScope(Dispatchers.Default)
    private val coroutineFreeGo   = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val autogoStateFlow   = MutableStateFlow(AutogoState.DEFAULT)
    private val freeGoFlow        = MutableStateFlow(0)

    private val goButton       = ButtonClickable(ButtonClickable.Style.go)
    private val autogoButton   = ButtonClickable(ButtonClickable.Style.autogo)
    private val betPlus        = ButtonClickable(ButtonClickable.Style.plus)
    private val betMinus       = ButtonClickable(ButtonClickable.Style.minus)
    private val goTextImage    = Image(languageSprite.GO)
    private val freeGoImage    = Image(languageSprite.FREE_GO)

    private lateinit var superGameGroup: SuperGameGroup

    private val slotGroup by lazy { SlotGroup(viewport) }

    private val onceStartAutospin = Once()



    override fun show() {
        super.show()
        setBackground(SpriteManager.GameSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineBalance, coroutineGo, coroutineAutogo, coroutineBet, coroutineFreeGo)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addBalancePanel()
        addBetPanel()
        addGo()
        addBetPlus()
        addBetMinus()
        addMenu()
        addAutogo()
        addSlotGroup()
        addFreeGoImage()
        addFreeGoCount()
    }



    private fun AdvancedStage.addBalancePanel() {
        val group = AdvancedGroup().apply {
            setBoundsFigmaY(Game.BALANCE_PANEL_X, Game.BALANCE_PANEL_Y, Game.BALANCE_PANEL_W, Game.BALANCE_PANEL_H)

            val image = Image(com.pharhaslo.slo7.game.assets.SpriteManager.GameSprite.BALANCE_PANEL.data.texture)
            val label = Label("", Label.LabelStyle(FontTTFManager.EnumFont.SIGMAR_ONE_53.data.font, Color.WHITE)).apply {
                setAlignment(Align.center)
                setBoundsFigmaY(Game.BALANCE_TEXT_X, Game.BALANCE_TEXT_Y, Game.BALANCE_TEXT_W, Game.BALANCE_TEXT_H, Game.BALANCE_PANEL_H)
                coroutineBalance.launch { DataStoreManager.collectBalance { balance -> Gdx.app.postRunnable {
                    setText(balance.transformToBalanceFormat())
                } } }
            }
            addAndFillActor(image)
            addActor(label)
        }
        addActor(group)
    }

    private fun AdvancedStage.addBetPanel() {
        val group = AdvancedGroup().apply {
            setBoundsFigmaY(Game.BET_PANEL_X, Game.BET_PANEL_Y, Game.BET_PANEL_W, Game.BET_PANEL_H)

            val imagePanel = Image(com.pharhaslo.slo7.game.assets.SpriteManager.GameSprite.BET_PANEL.data.texture)
            val imageBet = Image(languageSprite.BET).apply { setSize(Game.BET_LABEL_W, Game.BET_LABEL_H) }
            val label = Label("", Label.LabelStyle(FontTTFManager.EnumFont.SIGMAR_ONE_35.data.font, Color.WHITE)).apply {
                setAlignment(Align.center)
                setBoundsFigmaY(Game.BET_TEXT_X, Game.BET_TEXT_Y, Game.BET_TEXT_W, Game.BET_TEXT_H, Game.BET_PANEL_H)

                coroutineBet.launch { with(betFlow) {
                        emit(BET_MIN)
                        collect { bet -> Gdx.app.postRunnable { setText(bet.toString()) } }
                } }
            }
            addAndFillActor(imagePanel)
            addAlignActor(imageBet, AH.CENTER, AV.TOP)
            addActor(label)
        }
        addActor(group)
    }



    private fun AdvancedStage.addGo() {
        goButton.apply {
            setBoundsFigmaY(Game.GO_X, Game.GO_Y, Game.GO_W, Game.GO_H)
            setOnClickListener { goHandler() }

            goTextImage.apply { setSize(Game.GO_TEXT_W, Game.GO_TEXT_H) }
            addAlignActor(goTextImage, AH.CENTER, AV.CENTER)
        }
        addActor(goButton)
    }

    private fun AdvancedStage.addBetPlus() {
        betPlus.apply {
            setBoundsFigmaY(Game.BET_PLUS_X, Game.BET_PLUS_Y, Game.BET_PLUS_W, Game.BET_PLUS_H)
            setOnClickListener { betPlusHandler() }
        }
        addActor(betPlus)
    }

    private fun AdvancedStage.addBetMinus() {
        betMinus.apply {
            setBoundsFigmaY(Game.BET_MINUS_X, Game.BET_MINUS_Y, Game.BET_MINUS_W, Game.BET_MINUS_H)
            setOnClickListener { betMinusHandler() }
        }
        addActor(betMinus)
    }

    private fun AdvancedStage.addMenu() {
        val button = ButtonClickable(ButtonClickable.Style.menu).apply {
            setBoundsFigmaY(Game.MENU_X, Game.MENU_Y, Game.MENU_W, Game.MENU_H)
            setOnClickListener { NavigationManager.back() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addAutogo() {
        autogoButton.apply {
            setBoundsFigmaY(Game.AUTOGO_X, Game.AUTOGO_Y, Game.AUTOGO_W, Game.AUTOGO_H)
            setOnClickListener { autogoHandler() }
        }
        addActor(autogoButton)
    }

    private fun AdvancedStage.addSlotGroup() {
        slotGroup.apply { setPositionFigmaY(Game.SLOT_GROUP_X, Game.SLOT_GROUP_Y, SG.H) }
        addActor(slotGroup)
    }

    private fun AdvancedStage.addFreeGoImage() {
        freeGoImage.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.FREE_GO_X, Game.FREE_GO_Y, Game.FREE_GO_W, Game.FREE_GO_H)
        }
        addActor(freeGoImage)
    }

    private fun AdvancedStage.addFreeGoCount() {
        val label = Label("", Label.LabelStyle(FontTTFManager.EnumFont.SIGMAR_ONE_53.data.font, Color.WHITE)).apply {
            setAlignment(Align.left)
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(Game.FREE_GO_COUNT_X, Game.FREE_GO_COUNT_Y, Game.FREE_GO_COUNT_W, Game.FREE_GO_COUNT_H)
            coroutineFreeGo.launch { freeGoFlow.collect { count -> Gdx.app.postRunnable {
                setText(count)
                if (count > 0) listOf(freeGoImage, this@apply).onEach { it.addAction(Actions.fadeIn(
                    TIME_SHOW_FREE_GO
                )) }
                else listOf(freeGoImage, this@apply).onEach { it.addAction(Actions.fadeOut(
                    TIME_SHOW_FREE_GO
                )) }
            } } }
        }
        addActor(label)
    }

    private fun AdvancedStage.addSuperGameGroup() {
        superGameGroup = SuperGameGroup()
        addActor(superGameGroup)
    }



    private fun ButtonClickable.goHandler() {
        pressAndDisable(false)
        autogoButton.disable()
        betPlus.disable()
        betMinus.disable()

        coroutineGo.launch {
            if (checkBalance()) {
                goAndGetResult()
                delay(1f.toDelay)
            }

            unpressAndEnabled()
            autogoButton.enable()
            betPlus.enable()
            betMinus.enable()
        }
    }

    private fun betPlusHandler() {
        coroutineBet.launch { betFlow.apply {
            val emitValue = if((first() + BET_STEP) < BET_MAX) first() + BET_STEP else BET_MAX
            emit(emitValue)
        } }
    }

    private fun betMinusHandler() {
        coroutineBet.launch { betFlow.apply {
            val emitValue = if((first() - BET_STEP) > BET_MIN) first() - BET_STEP else BET_MIN
            emit(emitValue)
        } }
    }

    private fun ButtonClickable.autogoHandler() {
          autogoStateFlow.apply {
              value = if (value == AutogoState.DEFAULT) AutogoState.GO else AutogoState.DEFAULT
          }
          onceStartAutospin.once { startAutogo() }
    }



    private fun ButtonClickable.startAutogo() {
        coroutineAutogo.launch {
            autogoStateFlow.collect { state ->

                when (state) {
                    AutogoState.GO -> {
                        press()
                        goButton.disable()
                        betPlus.disable()
                        betMinus.disable()

                        CoroutineScope(Dispatchers.Default).launch {
                            while (autogoStateFlow.value == AutogoState.GO) {
                                if (checkBalance()) {
                                    goAndGetResult(false)
                                    delay(1f.toDelay)
                                } else autogoStateFlow.value = AutogoState.DEFAULT
                            }

                            // Если закончились деньги а состояние осталось в положении GO
                            if (autogoStateFlow.value == AutogoState.GO) autogoStateFlow.value =
                                AutogoState.DEFAULT

                            MusicUtil.apply { currentMusic = MAIN }

                            enable()
                            goButton.enable()
                            betPlus.enable()
                            betMinus.enable()
                            cancel()
                        }
                    }

                    AutogoState.DEFAULT -> disable()
                }
            }
        }
    }



    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        if (freeGoFlow.value == 0) {
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
        } else {
            freeGoFlow.value -= 1
            continuation.complete(true)
        }
    }.await()



    private suspend fun goAndGetResult(isEnableMainAfterGo: Boolean = true) {
        MusicUtil.apply { currentMusic = SPIN }
        slotGroup.spin().apply { getGoResult() }
        if (isEnableMainAfterGo) MusicUtil.apply { currentMusic = MAIN}
    }



    private suspend fun GoResult.getGoResult() {
        val winPriceFactor = fillResultList?.map { it.slotItem.price_factor }?.sum() ?: 0f
        val bonusPrice     = bonus?.getBonusPrice() ?: 0L

        val sumPrice = (betFlow.first() * (winPriceFactor + extraFactor)).toLong() + bonusPrice
        DataStoreManager.updateBalance { it + sumPrice }
    }



    private suspend fun Bonus.getBonusPrice() = CompletableDeferred<Long>().also { continuation ->
        when(this) {
            Bonus.MINI_GAME -> {
                log("SHOW MINI GAME")
                freeGoFlow.value += 3
                continuation.complete(0L)
            }
            Bonus.SUPER_GAME -> {
                log("SHOW SUPER GAME")
                continuation.complete(showSuperGameGroup().apply { hideSuperGameGroup() })
            }
        }
    }.await()



    private suspend fun showSuperGameGroup() = CompletableDeferred<Long>().also { continuation ->
        stage.addSuperGameGroup()
        continuation.complete(superGameGroup.show(betFlow.first()))
    }.await()

    private suspend fun hideSuperGameGroup() {
        superGameGroup.hide()
        Gdx.app.postRunnable { stage.root.removeActor(superGameGroup) }
    }



    private enum class AutogoState {
        DEFAULT, GO,
    }
}
