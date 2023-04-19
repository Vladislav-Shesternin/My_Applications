package com.pharhaslo.slo7.game.actors.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.pharhaslo.slo7.game.actors.ButtonClickable
import com.pharhaslo.slo7.game.actors.CheckBox
import com.pharhaslo.slo7.game.actors.Roulette
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.assets.FontTTFManager
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.MusicUtil
import com.pharhaslo.slo7.game.utils.*
import com.pharhaslo.slo7.game.languageSprite
import com.pharhaslo.slo7.game.manager.CheckBoxManager
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import com.pharhaslo.slo7.game.utils.SuperGame as SG

class SuperGameGroup: AdvancedGroup() {

    companion object {
        private const val SEGMENT = 36
        // seconds
        const val TIME_SHOW_GROUP = 1f
        const val TIME_SHOW_ACTOR = 1f

        // milliseconds
        // необходимо для 100% завершения анимации
        // Пример: анимация идет 1с потом задержка 1с, на несколько милисекунд анимация может быть дольше и время задержки меньше, и процес может нарушиться
        // поэтому к основному времени ожидания добавляем 50мс которые не заметны пользователю но решают проблему.
        const val TIME_EXTRA = 50L
    }

    private val rouletteItemList = listOf<Roulette.RouletteItem>(
        Roulette.RouletteItem(color = Roulette.Color.BLUE, segment = (SEGMENT * 0f) + 1f to (SEGMENT * 1f)),
        Roulette.RouletteItem(color = Roulette.Color.RED , segment = (SEGMENT * 1f) + 1f to (SEGMENT * 2f)),
        Roulette.RouletteItem(color = Roulette.Color.BLUE, segment = (SEGMENT * 2f) + 1f to (SEGMENT * 3f)),
        Roulette.RouletteItem(color = Roulette.Color.RED , segment = (SEGMENT * 3f) + 1f to (SEGMENT * 4f)),
        Roulette.RouletteItem(color = Roulette.Color.BLUE, segment = (SEGMENT * 4f) + 1f to (SEGMENT * 5f)),
        Roulette.RouletteItem(color = Roulette.Color.RED , segment = (SEGMENT * 5f) + 1f to (SEGMENT * 6f)),
        Roulette.RouletteItem(color = Roulette.Color.BLUE, segment = (SEGMENT * 6f) + 1f to (SEGMENT * 7f)),
        Roulette.RouletteItem(color = Roulette.Color.RED , segment = (SEGMENT * 7f) + 1f to (SEGMENT * 8f)),
        Roulette.RouletteItem(color = Roulette.Color.BLUE, segment = (SEGMENT * 8f) + 1f to (SEGMENT * 9f)),
        Roulette.RouletteItem(color = Roulette.Color.RED , segment = (SEGMENT * 9f) + 1f to (SEGMENT * 10f)),
    )

    private val coroutineRoulette = CoroutineScope(Dispatchers.Default)
    private val coroutineBalance  = CoroutineScope(Dispatchers.Default)

    private val roulette        = Roulette(rouletteItemList)
    private val indicator       = Image(SpriteManager.GameSprite.INDICATOR.data.texture)
    private val goButton        = ButtonClickable(ButtonClickable.Style.go)
    private val takeButton      = ButtonClickable(ButtonClickable.Style.go)
    private val goTextImage     = Image(languageSprite.GO)
    private val takeTextImage   = Image(languageSprite.TAKE)
    private val colorRed        = Image(SpriteManager.GameSprite.RED.data.texture)
    private val colorBlue       = Image(SpriteManager.GameSprite.BLUE.data.texture)
    private val checkboxRed     = CheckBox(CheckBox.Style.style_1)
    private val checkboxBlue    = CheckBox(CheckBox.Style.style_1)
    private val factorImage     = Image()
    private val balancePanel    = AdvancedGroup()

    private val checkBoxManager = CheckBoxManager()

    private lateinit var checkedColor: Roulette.Color

    private val balanceFlow        = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val takeFlow           = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    init {
        addAction(Actions.alpha(0f))
        setSize(WIDTH, HEIGHT)
        addActorsOnGroup()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineRoulette, coroutineBalance)
    }



    private fun addActorsOnGroup(){
        addBackground()
        addRoulette()
        addIndicator()
        addGo()
        addTake()
        addColors()
        addCheckBoxes()
        addBalancePanel()
        addFactor()
    }

    private fun addBackground() {
        val image = Image(SpriteManager.GameSprite.BACKGROUND.data.texture)
        addAndFillActor(image)
    }

    private fun addRoulette() {
        roulette.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(SG.ROULETTE_X, SG.ROULETTE_Y, SG.ROULETTE_W, SG.ROULETTE_H)
        }
        addActor(roulette)
    }

    private fun addIndicator() {
        indicator.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(SG.INDICATOR_X, SG.INDICATOR_Y, SG.INDICATOR_W, SG.INDICATOR_H)
        }
        addActor(indicator)
    }

    private fun addGo() {
        goButton.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(SG.GO_X, SG.GO_Y, SG.GO_W, SG.GO_H)
            setOnClickListener { goHandler() }

            goTextImage.apply { setSize(SG.GO_TEXT_W, SG.GO_TEXT_H) }
            addAlignActor(goTextImage, AlignmentHorizontal.CENTER, AlignmentVertical.CENTER)
        }
        addActor(goButton)
    }

    private fun addTake() {
        takeButton.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(SG.TAKE_X, SG.TAKE_Y, SG.TAKE_W, SG.TAKE_H)
            setOnClickListener { takeHandler() }

            takeTextImage.apply { setSize(SG.TAKE_TEXT_W, SG.TAKE_TEXT_H) }
            addAlignActor(takeTextImage, AlignmentHorizontal.CENTER, AlignmentVertical.CENTER)
        }
        addActor(takeButton)
    }

    private fun addBalancePanel() {
        balancePanel.apply {
            addAction(Actions.alpha(0f))
            setBoundsFigmaY(SG.BALANCE_PANEL_X, SG.BALANCE_PANEL_Y, SG.BALANCE_PANEL_W, SG.BALANCE_PANEL_H)

            val image = Image(SpriteManager.GameSprite.BALANCE_PANEL.data.texture)
            val label = Label("", Label.LabelStyle(FontTTFManager.EnumFont.SIGMAR_ONE_45.data.font, Color.WHITE)).apply {
                setAlignment(Align.center)
                setBoundsFigmaY(SG.BALANCE_TEXT_X, SG.BALANCE_TEXT_Y, SG.BALANCE_TEXT_W, SG.BALANCE_TEXT_H, SG.BALANCE_PANEL_H)
                coroutineBalance.launch { balanceFlow.collect { balance -> Gdx.app.postRunnable {
                    setText(balance.transformToBalanceFormat())
                } } }
            }
            addAndFillActor(image)
            addActor(label)
        }
        addActor(balancePanel)
    }

    private fun addColors() {
        listOf(colorRed, colorBlue).onEachIndexed { index, color ->
            val newX = SG.COLOR_FIRST_X + (SG.COLOR_W + SG.COLOR_SPACE_HORIZONTAL) * index
            color.apply {
                addAction(Actions.alpha(0f))
                setBoundsFigmaY(newX, SG.COLOR_Y, SG.COLOR_W, SG.COLOR_H)
            }
            addActor(color)
        }
    }

    private fun addCheckBoxes() {
        val checkboxList = listOf(checkboxRed, checkboxBlue)
        checkBoxManager.manageCheckBox(checkboxList)
        checkboxList.first().check()
        checkedColor = Roulette.Color.RED

        checkboxList.onEachIndexed { index, color ->
            val newX = SG.CHECKBOX_FIRST_X + (SG.CHECKBOX_W + SG.CHECKBOX_SPACE_HORIZONTAL) * index
            color.apply {
                addAction(Actions.alpha(0f))
                setBoundsFigmaY(newX, SG.CHECKBOX_Y, SG.CHECKBOX_W, SG.CHECKBOX_H)

                setOnCheckListener { if (it) { checkedColor = if (index == 0) Roulette.Color.RED else Roulette.Color.BLUE } }
            }
            addActor(color)
        }

    }

    private fun addFactor() {
        factorImage.apply {
            setBoundsFigmaY(SG.FACTOR_X, SG.FACTOR_Y, SG.FACTOR_W, SG.FACTOR_H)
            setOrigin(Align.center)
            addAction(Actions.alpha(0f))
            addAction(Actions.scaleTo(0f, 0f))
        }
        addActor(factorImage)
    }



    private fun ButtonClickable.goHandler() {
        disable()
        takeButton.disable()
        checkboxRed.disable()
        checkboxBlue.disable()
        coroutineRoulette.launch {
            roulette.spin().getRouletteResult()
            enable()
            takeButton.enable()
            checkboxRed.enable()
            checkboxBlue.enable()
        }
    }

    private fun takeHandler() {
        coroutineBalance.launch { takeFlow.emit(balanceFlow.first()) }
    }



    private suspend fun Roulette.RouletteItem.getRouletteResult() {
        if (color == checkedColor) factorImage.showFactor(Factor.X2)
        else factorImage.showFactor(Factor.X0)
    }



    private suspend fun Image.showFactor(factor: Factor) {
        val texture = when(factor) {
            Factor.X0 -> SpriteManager.GameSprite.X0.data.texture
            Factor.X2 -> SpriteManager.GameSprite.X2.data.texture
        }

        drawable = TextureRegionDrawable(texture)

        Gdx.app.postRunnable { addAction(Actions.parallel(
            Actions.fadeIn(TIME_SHOW_ACTOR),
            Actions.scaleTo(1f, 1f, TIME_SHOW_ACTOR),
        )) }
        delay(TIME_SHOW_ACTOR.toDelay + TIME_EXTRA)

        balanceFlow.apply { emit(first() * factor.value) }

        Gdx.app.postRunnable { addAction(Actions.parallel(
            Actions.fadeOut(TIME_SHOW_ACTOR),
            Actions.scaleTo(0f, 0f, TIME_SHOW_ACTOR),
        )) }
        delay(TIME_SHOW_ACTOR.toDelay + TIME_EXTRA)

        if (factor == Factor.X0) takeFlow.emit(0L)
    }



    suspend fun show(bet: Long) = CompletableDeferred<Long>().also { continuation ->
        MusicUtil.apply { currentMusic = SUPER_GAME }

        balanceFlow.emit(bet)

        Gdx.app.postRunnable { addAction(Actions.fadeIn(TIME_SHOW_GROUP)) }
        delay(TIME_SHOW_GROUP.toDelay + TIME_EXTRA)


        listOf<Actor>(
            roulette, indicator,
            goButton, takeButton,
            colorRed, colorBlue,
            checkboxRed, checkboxBlue,
            balancePanel,
        ).onEach { actor ->
            Gdx.app.postRunnable { actor.addAction(Actions.fadeIn(TIME_SHOW_ACTOR)) }
            delay((TIME_SHOW_ACTOR.toDelay / 2) + TIME_EXTRA)
        }

        takeFlow.take(1).collect { price -> continuation.complete(price) }

    }.await()

    suspend fun hide() {
        Gdx.app.postRunnable { addAction(Actions.fadeOut(TIME_SHOW_GROUP)) }
        delay(TIME_SHOW_GROUP.toDelay + TIME_EXTRA)
    }



    private enum class Factor(val value: Int) {
        X0(0), X2(2)
    }

}