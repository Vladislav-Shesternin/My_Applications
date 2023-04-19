package com.veldan.junglego.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.junglego.HEIGHT
import com.veldan.junglego.WIDTH
import com.veldan.junglego.actors.ButtonClickable
import com.veldan.junglego.actors.animation.Bird
import com.veldan.junglego.actors.slot.SlotGroup
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.advanced.AdvancedScreen
import com.veldan.junglego.advanced.AdvancedStage
import com.veldan.junglego.assets.FontManager
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.MusicUtil
import com.veldan.junglego.languageSprite
import com.veldan.junglego.manager.DataStoreManager
import com.veldan.junglego.manager.NavigationManager
import com.veldan.junglego.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random
import com.veldan.junglego.advanced.AdvancedGroup.AlignmentHorizontal as H
import com.veldan.junglego.advanced.AdvancedGroup.AlignmentVertical as V
import com.veldan.junglego.utils.SlotGroup as SG

class GameScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    companion object {
        const val ONE_BET = 1_000L
    }

    private val spinText       = Image(languageSprite.SPIN)
    private val balanceText    = Label("", Label.LabelStyle(FontManager.EnumFont.RATIONALE_70.fontData.font, Color.BLACK))
    private val bird           = Bird()
    private val slotGroup      = SlotGroup(viewport)
    private val spinButton     = ButtonClickable(ButtonClickable.Style.style_3)
    private val autospinButton = ButtonClickable(ButtonClickable.Style.style_4)
    private val betPlus        = ButtonClickable(ButtonClickable.Style.style_7)
    private val betMinus       = ButtonClickable(ButtonClickable.Style.style_8)

    private val timeFlyBirdFlow   = MutableSharedFlow<Float>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val betFlow           = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val autospinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val coroutineBalance  = CoroutineScope(Dispatchers.Default)
    private val coroutineFlyBird  = CoroutineScope(Dispatchers.Default)
    private val coroutineSpin     = CoroutineScope(Dispatchers.Default)
    private val coroutineAutospin = CoroutineScope(Dispatchers.Default)
    private val coroutineBet      = CoroutineScope(Dispatchers.Default)

    private val onceStartAutospin = Once()

    private var birdFlyNewX = 0f
    private var isSpin      = AtomicBoolean()



    override fun show() {
        super.show()
        background = SpriteManager.GameSprite.BACKGROUND.textureData.texture
        stage.addActorsOnStage()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineBalance, coroutineFlyBird, coroutineSpin, coroutineAutospin, coroutineBet)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val xy = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        if (xy.x in bird.x..bird.x+bird.width && xy.y in bird.y..bird.y+bird.height) coroutineBalance.launch { DataStoreManager.updateBalance { it + 100L } }
        return super.touchDown(screenX, screenY, pointer, button)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addBalancePanel()
        addBalance()
        if (isUseBird) addBird()
        addSlotGroup()
        addBet()
        addBetPanel()
        addBetPlus()
        addBetMinus()
        addSpin()
        addAutoSpin()
        addMenu()
    }


    private fun AdvancedStage.addBalancePanel() {
        val image = Image(SpriteManager.GameSprite.BALANCE_PANEL.textureData.texture).apply { 
            setBoundsFigmaY(Game.BALANCE_PANEL_X, Game.BALANCE_PANEL_Y, Game.BALANCE_PANEL_W, Game.BALANCE_PANEL_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addBalance() {
        balanceText.apply {
            setAlignment(Align.center)
            setBoundsFigmaY(Game.BALANCE_X, Game.BALANCE_Y, Game.BALANCE_W, Game.BALANCE_H)
            coroutineBalance.launch { DataStoreManager.collectBalance { Gdx.app.postRunnable {
                    setText(it.transformToBalanceFormat())
                    if (it >= ONE_BET && autospinStateFlow.value == AutospinState.DEFAULT) betPlus.unpressAndEnabled() else betPlus.pressAndDisable()
            } } }
        }
        addActor(balanceText)
    }



    private fun AdvancedStage.addBird() {
        bird.apply {
            setBoundsFigmaY(Game.BIRD_START_X, Game.BIRD_Y, Game.BIRD_W, Game.BIRD_H)

            coroutineFlyBird.launch {
                timeFlyBirdFlow.apply {
                    emit(3f)
                    collect { time -> addActionFlyBird(time) { emit(Random.nextInt(1, 5).toFloat()) } }
                }
            }
        }
        addActor(bird)
    }

    private fun AdvancedStage.addSlotGroup() {
        slotGroup.apply {
            setBoundsFigmaY(SG.X, SG.Y, SG.W, SG.H)
        }
        addActor(slotGroup)
    }

    private fun AdvancedStage.addSpin() {
        spinButton.apply {
            setBoundsFigmaY(Game.SPIN_X, Game.SPIN_Y, Game.SPIN_W, Game.SPIN_H)

            val image = spinText.apply { setSize(Game.SPIN_TEXT_W, Game.SPIN_TEXT_H) }
            addAlignActor(image, H.CENTER, V.CENTER)

            setOnClickListener { spinHandler() }
        }
        addActor(spinButton)
    }

    private fun AdvancedStage.addAutoSpin() {
        autospinButton.apply {
            setBoundsFigmaY(Game.AUTO_SPIN_X, Game.AUTO_SPIN_Y, Game.AUTO_SPIN_W, Game.AUTO_SPIN_H)
            setOnClickListener { autospinHandler() }
        }
        addActor(autospinButton)
    }

    private fun AdvancedStage.addMenu() {
        val button = ButtonClickable(ButtonClickable.Style.style_5).apply {
            setBoundsFigmaY(Game.MENU_X, Game.MENU_Y, Game.MENU_W, Game.MENU_H)
            setOnClickListener { NavigationManager.back() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addBet() {
        val image = Image(languageSprite.BET).apply {
            setBoundsFigmaY(Game.BET_X, Game.BET_Y, Game.BET_W, Game.BET_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addBetPanel() {
        val group = AdvancedGroup().apply {
            setBoundsFigmaY(Game.BET_PANEL_X, Game.BET_PANEL_Y, Game.BET_PANEL_W, Game.BET_PANEL_H)

            val image = Image(SpriteManager.GameSprite.BALANCE_PANEL.textureData.texture)
            val text = Label("", Label.LabelStyle(FontManager.EnumFont.RATIONALE_50.fontData.font, Color.BLACK)).apply {
                setAlignment(Align.center)
                setSize(Game.BET_TEXT_W, Game.BET_TEXT_H)

                coroutineBet.launch {
                    DataStoreManager.updateBalance {
                        if (it >= ONE_BET) {
                            betFlow.emit(ONE_BET)
                            it - ONE_BET
                        } else {
                            betFlow.emit(it)
                            0L
                        }
                    }

                    betFlow.collect { bet -> Gdx.app.postRunnable {
                            setText(bet.transformToBalanceFormat())
                            if (bet >= ONE_BET && autospinStateFlow.value == AutospinState.DEFAULT)
                                betMinus.unpressAndEnabled() else betMinus.pressAndDisable()

                            if (bet > 0) {
                                if (autospinStateFlow.value == AutospinState.DEFAULT) {
                                    spinButton.unpressAndEnabled()
                                    autospinButton.unpressAndEnabled()
                                }
                                isSpin.set(true)
                            } else {
                                if (autospinStateFlow.value == AutospinState.DEFAULT) {
                                    spinButton.pressAndDisable()
                                    autospinButton.pressAndDisable()
                                }
                                isSpin.set(false)
                            }
                    } }
                }
            }

            addAndFillActor(image)
            addAlignActor(text, H.CENTER, V.CENTER)
        }
        addActor(group)
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
    
    
    private fun ButtonClickable.spinHandler() {
        pressAndDisable(false)
        spinText.drawable = TextureRegionDrawable(languageSprite.WAIT)
        autospinButton.disable()
        betPlus.pressAndDisable()
        betMinus.pressAndDisable()

        coroutineSpin.launch {
            spinAndGetResult()
            spinText.drawable = TextureRegionDrawable(languageSprite.SPIN)
            delay(1f.toDelay)
            updateBalanceAndBet()
        }
    }


    private fun ButtonClickable.autospinHandler() {
        autospinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.SPIN else AutospinState.DEFAULT
        }
        onceStartAutospin.once { startAutospin() }
    }



    private fun ButtonClickable.startAutospin() {
        coroutineAutospin.launch {
            autospinStateFlow.collect { state ->

                when(state) {
                    AutospinState.SPIN -> {
                        press()
                        spinButton.apply {
                            pressAndDisable()
                            spinText.drawable = TextureRegionDrawable(languageSprite.WAIT)
                        }
                        betPlus.pressAndDisable()
                        betMinus.pressAndDisable()

                        CoroutineScope(Dispatchers.Default).launch {
                            while (isSpin.get()) {
                                spinAndGetResult()
                                delay(1f.toDelay)
                                updateBalanceAndBet()

                                if (autospinStateFlow.value == AutospinState.DEFAULT){
                                    isSpin.set(false)
                                    cancel()
                                }
                            }
                            cancel()
                        }
                    }

                    AutospinState.DEFAULT -> disable()
                }
            }
        }
    }



    private suspend fun spinAndGetResult() {
        MusicUtil.apply { currentMusic = SPIN }

        slotGroup.spin().also { spinResult ->
            val winPrice = (spinResult.slotItemList.map { it.price_factor }.sum() * betFlow.first()).toLong()
            val bonusPrice = (spinResult.bonus?.price ?: 0)
            val sumPrice = winPrice + bonusPrice
            DataStoreManager.updateBalance { it + sumPrice }
        }

        MusicUtil.apply { currentMusic = MAIN }
    }



    private suspend fun updateBalanceAndBet() {
        DataStoreManager.updateBalance { balance ->
            if (balance - betFlow.first() >= 0) {
                betFlow.apply { emit(first()) }
                balance - betFlow.first()
            } else {
                betFlow.emit(balance)
                0L
            }
        }
    }



    private suspend fun Bird.addActionFlyBird(time: Float, doAfterEnd: suspend () -> Unit) {
        birdFlyNewX = Game.BIRD_END_X - x

        Gdx.app.postRunnable {
            clearActions()

            addAction(Actions.sequence(
                Actions.moveTo(birdFlyNewX, y, time),
                Actions.moveTo(Game.BIRD_START_X, y, time),
            ))
        }

        // * 2 - две анимации движения
        delay((time * 2).toDelay)
        doAfterEnd()
    }



    private fun ButtonClickable.betPlusHandler() {
        coroutineBalance.launch {
            DataStoreManager.updateBalance {
                betFlow.apply { emit( first() + ONE_BET) }
                it - ONE_BET
            }
        }
    }

    private fun ButtonClickable.betMinusHandler() {
        coroutineBet.launch {
            DataStoreManager.updateBalance {
                betFlow.apply { emit( first() - ONE_BET) }
                it + ONE_BET
            }
        }
    }



    private enum class AutospinState {
        DEFAULT, SPIN,
    }
}