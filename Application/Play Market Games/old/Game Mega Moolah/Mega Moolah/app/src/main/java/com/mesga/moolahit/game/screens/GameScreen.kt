package com.mesga.moolahit.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.mesga.moolahit.game.actors.button.AButton
import com.mesga.moolahit.game.actors.button.AButtonStyle
import com.mesga.moolahit.game.actors.label.ALabelStyle
import com.mesga.moolahit.game.actors.label.spinning.SpinningLabel
import com.mesga.moolahit.game.actors.slot.SlotGroup
import com.mesga.moolahit.game.actors.slot.SpinResult
import com.mesga.moolahit.game.box2d.bodies.BodyId
import com.mesga.moolahit.game.box2d.bodies.balance.Balance
import com.mesga.moolahit.game.box2d.bodies.bonus.Bonus
import com.mesga.moolahit.game.box2d.bodies.duplo.Duplo
import com.mesga.moolahit.game.manager.GameDataStoreManager
import com.mesga.moolahit.game.manager.SpriteManager
import com.mesga.moolahit.game.util.AutospinState
import com.mesga.moolahit.game.util.Size
import com.mesga.moolahit.game.util.advanced.AdvancedBox2dScreen
import com.mesga.moolahit.game.util.advanced.AdvancedGroup
import com.mesga.moolahit.game.util.advanced.AdvancedStage
import com.mesga.moolahit.game.util.transformToBalanceFormat
import com.mesga.moolahit.Once
import com.mesga.moolahit.adAppOpen
import com.mesga.moolahit.game.game
import com.mesga.moolahit.log
import com.mesga.moolahit.runGDX
import com.mesga.moolahit.toMS
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.mesga.moolahit.game.util.Layout.Game as LG
import com.mesga.moolahit.game.util.Layout.Bonus as LB

class GameScreen: AdvancedBox2dScreen(1280f, 720f, 20f, 11.25f) {

    companion object {
        private const val BET_STEP = 250L
        private const val BET_MIN  = BET_STEP
        private const val BET_MAX  = 1000L

        private const val TIME_WAIT_AFTER_AUTOSPIN = 1.1f
    }

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)
    private val onceStartAutoSpin = Once()

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = SpinningLabel("", ALabelStyle.fontWhite_25)
    private val betGroup     = AdvancedGroup()
    private val betText      = SpinningLabel("", ALabelStyle.fontWhite_25)
    private val plusButton   = AButton(AButtonStyle.plus)
    private val minusButton  = AButton(AButtonStyle.minus)
    private val spinButton   = AButton(AButtonStyle.spin)
    private val slotGroup    = SlotGroup()

    private val bonusList    = List(20) { Bonus() }
    private val bonusOutFlow = MutableSharedFlow<Bonus>(20)



    override fun show() {
        adAppOpen?.showAd(game.activity)

        super.show()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutineMain.launch(Dispatchers.IO) { GameDataStoreManager.Balance.update { it ?: (BET_MAX * 5) } }


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

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutineMain.launch {
            createBalance()
            createDuploList()

            addBalanceGroup()
            addBetGroup()
            addPlusButton()
            addMinusButton()
            addSpinButton()
            addSlotGroup()

            createBonusList()
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBalance() {
        Balance().also {
            it.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(LG.balancePanel.x, LG.balancePanel.y),
                Size(LG.balancePanel.w, LG.balancePanel.h)
            )
        }

        Bonus().also {
            it.id = BodyId.BALANCE_SENSOR
            it.fixtureDef.isSensor = true
            it.bodyDef.type = BodyDef.BodyType.StaticBody
            it.actor.drawable = TextureRegionDrawable(SpriteManager.GameRegion.DUPLO.region)
            it.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(LB.balanceSensor.x, LB.balanceSensor.y),
                Size(LB.balanceSensor.w, LB.balanceSensor.h),
            )

            it.setBeginContactBlock { abstractBody ->
                when (abstractBody) {
                    is Bonus -> {
                        if (abstractBody.atomicBoolean.getAndSet(false)) {
                            bonus?.play(0.1f)

                            abstractBody.bodyOut()
                            bonusOutFlow.tryEmit(abstractBody)

                            coroutineMain.launch(Dispatchers.IO) {
                                GameDataStoreManager.Balance.update { it!! + 1000L }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createDuploList() {
        LB.duploList.onEach { position ->
            Duplo().also {
                it.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    position,
                    LB.duploSize,
                )
            }
        }
    }

    private suspend fun createBonusList() {
        coroutineMain.launch(Dispatchers.Default) {
            bonusList.onEachIndexed { _, bonus ->
                delay(1f.toMS)

                runGDX {
                    bonus.initialize(
                        stageUI,
                        sizeConverterUIToBox,
                        sizeConverterBoxToUI,
                        Bonus.getPositionUI(),
                        LB.bonusSize
                    )

                    bonus.setRenderBlock {
                        if (bonus.actor.x + bonus.actor.width < 0f || bonus.actor.y + bonus.actor.height < 0f) {
                            if (bonus.atomicBoolean.getAndSet(false)) {
                                bonus.bodyOut()
                                bonusOutFlow.tryEmit(bonus)
                            }
                        }
                    }
                }

            }
        }

        coroutineMain.launch(Dispatchers.Default) {
            bonusOutFlow.collect { bonus ->
                delay((500..1000L).shuffled().first())

                runGDX {
                    Bonus.getPositionUI().also {
                        bonus.body.setTransform(
                            sizeConverterUIToBox.getSizeX(it.x),
                            sizeConverterUIToBox.getSizeY(it.y),
                            0f
                        )
                    }
                    bonus.id = BodyId.BONUS
                    bonus.body.gravityScale = 1f
                    bonus.body.isAwake = true
                }

                delay(300)
                bonus.atomicBoolean.set(true)

            }
        }

    }

    private fun Bonus.bodyOut() {
        runGDX {
            id = BodyId.NONE
            body.apply {
                linearVelocity = Vector2(0f, 0f)
                setTransform(7f, 12f, 0f)
                isAwake = false
                gravityScale = 0f
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBalanceGroup() {
        addActor(balanceGroup)
        balanceGroup.apply {
            with(LG.balancePanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(SpriteManager.GameRegion.BALANCE.region))
            addActor(balanceText)

            balanceText.apply {
                with(LG.balanceText) { setBounds(x, y, w, h) }
                updateBalance()
            }
        }

    }

    private fun AdvancedStage.addBetGroup() {
        addActor(betGroup)
        betGroup.apply {
            with(LG.betPanel) { setBounds(x, y, w, h) }
            addAndFillActor(Image(SpriteManager.GameRegion.BET.region))
            addActor(betText)

            betText.apply {
                with(LG.betText) { setBounds(x, y, w, h) }
                updateBet()
            }
        }

    }

    private fun AdvancedStage.addPlusButton() {
        addActor(plusButton)
        plusButton.apply {
            with(LG.plus) { setBounds(x, y, w, h) }
            setOnClickListener { plusHandler() }
        }

    }

    private fun AdvancedStage.addMinusButton() {
        addActor(minusButton)
        minusButton.apply {
            with(LG.minus) { setBounds(x, y, w, h) }
            setOnClickListener { minusHandler() }
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

    private fun updateBet() {
        coroutineMain.launch {
            betFlow.collect { bet -> betText.setText(bet.transformToBalanceFormat()) }
        }
    }

    private fun plusHandler() {
        plus?.play()

        coroutineMain.launch {
            with(betFlow) {
                value = if ((value + BET_STEP) < BET_MAX) value + BET_STEP else BET_MAX
            }
        }
    }

    private fun minusHandler() {
        plus?.play(1f)

        coroutineMain.launch {
            with(betFlow) {
                value = if ((value - BET_STEP) > BET_MIN) value - BET_STEP else BET_MIN
            }
        }
    }

    private fun spinHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }

    private var spinCounter = 0

    private fun startAutospin() {
        coroutineMain.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        spinButton.press()
                        listOf(plusButton, minusButton).onEach { it.disable() }

                        coroutineMain.launch {
                            while (autoSpinStateFlow.value == AutospinState.GO) {
                                if (checkBalance()) {
                                    spinAndSetResult()
                                    delay(TIME_WAIT_AFTER_AUTOSPIN.toMS)
                                } else autoSpinStateFlow.value = AutospinState.DEFAULT
                            }

                            spinCounter++
                            if (spinCounter % 2 == 0) {
                                spinCounter = 0
                                game.activity.apply { adInterstitial.showAd(this) }
                            }

                            spinButton.enable()
                            listOf(plusButton, minusButton).onEach { it.enable() }
                        }
                    }

                    AutospinState.DEFAULT -> spinButton.disable()
                }
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
            GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

}