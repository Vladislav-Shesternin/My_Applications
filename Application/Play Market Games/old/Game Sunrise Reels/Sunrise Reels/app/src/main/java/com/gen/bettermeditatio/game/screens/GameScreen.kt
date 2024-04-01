package com.gen.bettermeditatio.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gen.bettermeditatio.adAppOpen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.gen.bettermeditatio.game.actors.button.ButtonClickable
import com.gen.bettermeditatio.game.actors.button.ButtonClickableStyle
import com.gen.bettermeditatio.game.actors.label.LabelStyle
import com.gen.bettermeditatio.game.actors.label.spinning.SpinningLabel
import com.gen.bettermeditatio.game.actors.slot.SlotGroup
import com.gen.bettermeditatio.game.actors.slot.SpinResult
import com.gen.bettermeditatio.game.game
import com.gen.bettermeditatio.game.manager.GameDataStoreManager
import com.gen.bettermeditatio.game.manager.SpriteManager
import com.gen.bettermeditatio.game.util.advanced.AdvancedGroup
import com.gen.bettermeditatio.game.util.advanced.AdvancedScreen
import com.gen.bettermeditatio.game.util.advanced.AdvancedStage
import com.gen.bettermeditatio.game.util.transformToBalanceFormat
import com.gen.bettermeditatio.log
import kotlinx.coroutines.delay
import com.gen.bettermeditatio.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val balanceImage = AdvancedGroup()
    private val balanceText  = SpinningLabel("", LabelStyle.akronimWhite_40)
    private val spinButton   = ButtonClickable(ButtonClickableStyle.spin)
    private val slotGroup    = SlotGroup()



    override fun show() {
        adAppOpen?.showAd(game.activity)

        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch { GameDataStoreManager.Balance.update { it ?: 0L } }
        super.show()

        var isShowAppOpenAd = false
        adAppOpen?.let { ad ->
            coroutineMain.launch(Dispatchers.Default) {
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
        addSlotGroup()
        addBalanceGroup()
        addSpinButton()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBalanceGroup() {
        addActors(balanceImage)

        balanceImage.apply {
            addAndFillActor(Image(SpriteManager.GameRegion.BALANCE.region))
            with(LG.balancePanel) { setBounds(x, y, w, h) }

            addActor(balanceText)
            balanceText.apply {
                with(LG.balanceText) { setBounds(x, y, w, h) }
                updateBalance()
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
                 Gdx.app.postRunnable { balanceText.setText(balance!!.transformToBalanceFormat()) }
            }
        }
    }

    private var spinCounter   = 0
    private var spinAdsNumber = (2..5).random()

    private fun spinHandler() {
        coroutineMain.launch {
            spinButton.disable()
            spinAndSetResult()

            log("spin num = $spinAdsNumber")

            spinCounter++
            if (spinCounter % spinAdsNumber == 0) {
                spinCounter = 0
                spinAdsNumber = (2..5).random()
                log("new spin num = $spinAdsNumber")
                game.activity.apply { adInterstitial.showAd(this) }
            }

            spinButton.enable()
        }
    }

    private suspend fun spinAndSetResult() {
        slotGroup.spin().apply { calculateAndSetResult() }
    }

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceCoff }?.sum() ?: 0f
        val sumPrice = ((100..1000).shuffled().first() * slotItemPriceFactor).toLong()
        coroutineMain.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

}