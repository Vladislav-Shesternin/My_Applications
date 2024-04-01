package com.book.of.dead.deluxe.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.book.of.dead.deluxe.adAppOpen
import com.book.of.dead.deluxe.game.actors.bonus.Bonus
import com.book.of.dead.deluxe.game.actors.button.ButtonClickable
import com.book.of.dead.deluxe.game.actors.button.ButtonClickableStyle
import com.book.of.dead.deluxe.game.actors.label.LabelStyle
import com.book.of.dead.deluxe.game.actors.label.spinning.SpinningLabel
import com.book.of.dead.deluxe.game.actors.slot.SlotGroup
import com.book.of.dead.deluxe.game.actors.slot.SpinResult
import com.book.of.dead.deluxe.game.game
import com.book.of.dead.deluxe.game.manager.GameDataStoreManager
import com.book.of.dead.deluxe.game.manager.SpriteManager
import com.book.of.dead.deluxe.game.util.advanced.AdvancedGroup
import com.book.of.dead.deluxe.game.util.advanced.AdvancedScreen
import com.book.of.dead.deluxe.game.util.advanced.AdvancedStage
import com.book.of.dead.deluxe.game.util.disable
import com.book.of.dead.deluxe.game.util.transformToBalanceFormat
import com.book.of.dead.deluxe.log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import com.book.of.dead.deluxe.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    companion object {
        private const val BET_STEP = 10_000L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 50_000L

    }

    private val betFlow = MutableStateFlow(BET_MIN)

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimGold_30)
    private val betGroup     = AdvancedGroup()
    private val betText      = SpinningLabel("", LabelStyle.akronimGold_30)
    private val plusButton   = ButtonClickable(ButtonClickableStyle.plus)
    private val minusButton  = ButtonClickable(ButtonClickableStyle.minus)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()
    private val bonus        = Bonus()
    private val infoPanel    = AdvancedGroup()
    private val infoImage    = Image()
    private val infoButton   = ButtonClickable(ButtonClickableStyle.info)



    override fun show() {
        adAppOpen?.showAd(game.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND_2.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: 100_000L } }
        super.show()

        var isShowAppOpenAd = false
        adAppOpen?.let { ad ->
            coroutineMain.launch(Dispatchers.Default) {
                while (isShowAppOpenAd.not()) {
                kotlinx.coroutines.delay(3_000L)
                    
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
        addBetGroup()
        addSpinButton()
        addSlotGroup()
        addBonus()
        addInfo()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBalanceGroup() {
        addActor(balanceGroup)
        balanceGroup.apply {
            with(LG.balancePanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(com.book.of.dead.deluxe.game.manager.SpriteManager.GameRegion.BALANCE.region))
            addActor(balanceText)

            balanceText.apply {
                with(LG.balanceText) { setBounds(x, y, w, h) }
                updateBalance()
            }
        }

    }

    private fun AdvancedStage.addBetGroup() {
        addActors(betGroup, plusButton, minusButton)
        betGroup.apply {
            with(LG.betPanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(com.book.of.dead.deluxe.game.manager.SpriteManager.GameRegion.BET.region))
            addActor(betText)

            betText.apply { with(LG.betText) { setBounds(x, y, w, h) } }
        }

        plusButton.apply {
            with(LG.plus) { setBounds(x, y, w, h) }
            setOnClickListener { plusHandler() }
        }

        minusButton.apply {
            with(LG.minus) { setBounds(x, y, w, h) }
            setOnClickListener { minusHandler() }
        }

        updateBet()

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

    private fun AdvancedStage.addBonus() {
        addActor(bonus)
        with(LG.bonus) { bonus.setBounds(x, y, w, h) }
        collectBonusIsWin()
    }

    private fun AdvancedStage.addInfo() {
        addActors(infoButton, infoPanel)

        infoButton.apply {
            with(LG.infoButton) { setBounds(x, y, w, h) }
            setOnClickListener { showAndHideInfo(com.book.of.dead.deluxe.game.manager.SpriteManager.GameRegion.INFO_TEXT_1.region) }
        }

        infoPanel.apply {
            addAndFillActor(Image(com.book.of.dead.deluxe.game.manager.SpriteManager.GameRegion.INFO.region))
            addActor(infoImage)

            with(LG.infoPanel) { setBounds(x, y, w, h) }
            disable()
            addAction(Actions.alpha(0f))

            infoImage.apply { with(LG.infoImage) { setBounds(x, y, w, h) } }
        }

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun updateBalance() {
        coroutineMain.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.collect { balance ->
                 Gdx.app.postRunnable { balanceText.setText(balance!!.transformToBalanceFormat()) }
            }
        }
    }

    private fun updateBet() {
        coroutineMain.launch {
            betFlow.collect { bet -> betText.setText(bet.transformToBalanceFormat()) }
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

    private var spinCounter   = 0
    private var spinAdsNumber = (2..5).random()

    private fun spinHandler() {
        listOf(spinButton, plusButton, minusButton).apply {
            onEach { it.disable() }

            coroutineMain.launch {
                if (checkBalance()) spinAndSetResult()

                log("spin num = $spinAdsNumber")

                spinCounter++
                if (spinCounter % spinAdsNumber == 0) {
                    spinCounter = 0
                    spinAdsNumber = (2..5).random()
                    log("new spin num = $spinAdsNumber")
                    game.activity.apply { adInterstitial.showAd(this) }
                }

                bonus.countFlow.tryEmit(slotGroup.bonusCount)

                onEach { it.enable() }
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
            com.book.of.dead.deluxe.game.manager.GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

    private fun collectBonusIsWin() {
        coroutineMain.launch {
            bonus.isWinFlow.collect {
                if (it) {
                    showAndHideInfo(SpriteManager.GameRegion.INFO_TEXT_2.region)
                    GameDataStoreManager.Balance.update { balance -> balance!! + 100_000L }
                    bonus.hideAll()
                }
            }
        }
    }

    private fun showAndHideInfo(info: TextureRegion) {
        infoImage.drawable = TextureRegionDrawable(info)
        infoPanel.addAction(Actions.sequence(
            Actions.fadeIn(1f),
            Actions.delay(1f),
            Actions.fadeOut(1f),
        ))
    }

}