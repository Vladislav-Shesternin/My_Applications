package com.aztec.firevoll.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.aztec.firevoll.game.actors.button.ButtonClickable
import com.aztec.firevoll.game.actors.button.ButtonClickableStyle
import com.aztec.firevoll.game.actors.label.LabelStyle
import com.aztec.firevoll.game.actors.label.spinning.SpinningLabel
import com.aztec.firevoll.game.actors.slot.SlotGroup
import com.aztec.firevoll.game.actors.slot.SpinResult
import com.aztec.firevoll.game.manager.GameDataStoreManager
import com.aztec.firevoll.game.manager.SpriteManager
import com.aztec.firevoll.game.util.AutospinState
import com.aztec.firevoll.game.util.advanced.AdvancedGroup
import com.aztec.firevoll.game.util.advanced.AdvancedScreen
import com.aztec.firevoll.game.util.advanced.AdvancedStage
import com.aztec.firevoll.game.util.transformToBalanceFormat
import com.aztec.firevoll.Once
import com.aztec.firevoll.adAppOpen
import com.aztec.firevoll.game.game
import com.aztec.firevoll.log
import com.aztec.firevoll.toMS
import com.aztec.firevoll.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val TIME_WAIT_AFTER_AUTOSPIN = 1f
    }

    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimRed_50)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val autoButton   = ButtonClickable(ButtonClickableStyle.auto)
    private val slotGroup    = SlotGroup()
    private val panelImage   = Image(SpriteManager.GameRegion.PANEL.region)



    override fun show() {

        adAppOpen?.showAd(game.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutine.launch(Dispatchers.IO) { GameDataStoreManager.Balance.update { it ?: 0L } }
        super.show()

        var isShowAppOpenAd = false
        adAppOpen?.let { ad ->
            coroutine.launch(Dispatchers.Default) {
                while (isShowAppOpenAd.not()) {
                    delay(3_000L)

                    log("show after 3s")

                    if (ad.isLoaded) {
                        isShowAppOpenAd = true
                        ad.showAd(game.activity)
                    }
                }
            }
        }
    }

    override fun AdvancedStage.addActorsOnStage() {
        addBalanceGroup()
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
            addAndFillActor(
                Image(SpriteManager.GameRegion.BALANCE_PANEL.region).apply {
                    setOrigin(Align.center)
                    addAction(
                        Actions.forever(
                            Actions.sequence(
                                Actions.scaleBy(0.1f, 0.1f, 1f),
                                Actions.scaleBy(-0.1f, -0.1f, 1f),
                            )
                        )
                    )
                }
            )
            addActor(balanceText)

            balanceText.apply {
                with(LG.balanceText) { setBounds(x, y, w, h) }
                updateBalance()
            }
        }

        addActor(panelImage)
        with(LG.panel) { panelImage.setBounds(x, y, w, h) }

    }

    private fun AdvancedStage.addSpinButton() {
        addActors(spinButton, autoButton)
        spinButton.apply {
            with(LG.spin) { setBounds(x, y, w, h) }
            setOnClickListener { spinHandler() }
        }
        autoButton.apply {
            with(LG.auto) { setBounds(x, y, w, h) }
            setOnClickListener { autoHandler() }
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
        coroutine.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.collect { balance ->
                 Gdx.app.postRunnable { balanceText.setText(balance?.transformToBalanceFormat().toString()) }
            }
        }
    }

    private var spinCounter   = 0
    private var spinAdsNumber = (2..5).random()

    private fun spinHandler() {
        spinButton.disable()
        autoButton.disable()
        coroutine.launch {
            spinAndSetResult()

            spinCounter++
            if (spinCounter % spinAdsNumber == 0) {
                spinCounter   = 0
                spinAdsNumber = (2..5).random()
                log("new spin num = $spinAdsNumber")
                game.activity.apply { adInterstitial.showAd(this) }
            }

            spinButton.enable()
            autoButton.enable()
        }
    }

    private fun autoHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }

    private var autoSpinCounter = 0


    private fun startAutospin() {
        coroutine.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        autoButton.press()
                        spinButton.disable()

                        coroutine.launch {
                            while (autoSpinStateFlow.value == AutospinState.GO) {
                                spinAndSetResult()
                                delay(TIME_WAIT_AFTER_AUTOSPIN.toMS)
                            }

                            autoSpinCounter++
                            if (autoSpinCounter % 2 == 0) {
                                autoSpinCounter = 0
                                game.activity.apply { adInterstitial.showAd(this) }
                            }

                            autoButton.enable()
                            spinButton.enable()
                        }
                    }

                    AutospinState.DEFAULT -> autoButton.disable()
                }
            }
        }
    }

    private suspend fun spinAndSetResult() {
        slotGroup.spin().apply { calculateAndSetResult() }
    }

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceCoff }?.sum() ?: 0f
        val sumPrice = (1000L * slotItemPriceFactor).toLong()
        coroutine.launch(Dispatchers.IO) { GameDataStoreManager.Balance.update { it!! + sumPrice } }
    }

}