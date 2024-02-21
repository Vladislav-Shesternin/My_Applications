package com.rcks.scaloi.game.screens

import com.rcks.scaloi.game.actors.button.ButtonClickable
import com.rcks.scaloi.game.actors.button.ButtonClickableStyle
import com.rcks.scaloi.game.actors.checkbox.CheckBox
import com.rcks.scaloi.game.actors.checkbox.CheckBoxGroup
import com.rcks.scaloi.game.actors.checkbox.CheckBoxStyle
import com.rcks.scaloi.game.actors.label.LabelStyle
import com.rcks.scaloi.game.actors.slot.SlotGroup
import com.rcks.scaloi.game.actors.slot.SpinResult
import com.rcks.scaloi.game.manager.SpriteManager
import com.rcks.scaloi.game.util.advanced.AdvancedGroup
import com.rcks.scaloi.game.util.advanced.AdvancedScreen
import com.rcks.scaloi.game.util.advanced.AdvancedStage
import com.rcks.scaloi.game.util.disable
import com.rcks.scaloi.game.util.transformToBalanceFormat
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.rcks.scaloi.game.manager.GameDataStoreManager
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.rcks.scaloi.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val snow1      = Image(SpriteManager.CommonRegion.SNOW.region)
    private val snow2      = Image(SpriteManager.CommonRegion.SNOW.region)
    private val svetchenie = Image(SpriteManager.GameRegion.SVETCHENIE.region)

    private val santaImage = Image()
    private val santaAnim  = Animation(0.050f, *SpriteManager.ListRegion.ANIMS.regionList.toTypedArray())
    private var santaTime  = 0f

    private val balanceGroup = AdvancedGroup()
    private val balanceText  = Label("", LabelStyle.font_60)
    private val playButton   = ButtonClickable(ButtonClickableStyle.knopa)
    private val slotGroup    = SlotGroup()

    private val listBet = listOf(1_000, 5_000, 10_000)

    private val betFlow = MutableStateFlow(listBet.first())



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        coroutine.launch { GameDataStoreManager.Balance.update { it ?: 15_000L } }
        super.show()
    }

    override fun render(delta: Float) {
        super.render(delta)
        santaImage.drawable = TextureRegionDrawable(santaAnim.getKeyFrame(santaTime, true))
        santaTime += delta
    }

    override fun AdvancedStage.addActorsOnStage() {
        addActors(snow1, snow2)

        snow1.setBounds(0f, 0f, com.rcks.scaloi.game.util.WIDTH, com.rcks.scaloi.game.util.HEIGHT)
        snow2.setBounds(0f,
            com.rcks.scaloi.game.util.HEIGHT,
            com.rcks.scaloi.game.util.WIDTH,
            com.rcks.scaloi.game.util.HEIGHT
        )

        snow1.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))
        snow2.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))

        addActor(svetchenie)
        svetchenie.apply {
            with(LG.svetchenie) { setBounds(x, y, w, h) }
            setOrigin(Align.center)
            addAction(Actions.sequence(
                Actions.scaleTo(0.5f, 0.5f),
                Actions.forever(Actions.sequence(
                    Actions.scaleBy(0.5f, 0.5f, 3f),
                    Actions.scaleBy(-0.5f, -0.5f, 3f),
                ))
            ))
        }

        addActor(santaImage)
        santaImage.apply {
            with(LG.santa) { setBounds(x, y, w, h) }
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.sequence(
                Actions.rotateBy(-45f, 2f),
                Actions.rotateBy(45f, 2f),
            )))
        }

        addBalanceGroup()
        addBetGroup()
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
            addAndFillActor(Image(SpriteManager.GameRegion.BULANCE.region))
            addAndFillActor(balanceText)

            balanceText.apply {
                setAlignment(Align.center)
                updateBalance()
            }
        }

    }

    val listCB = mutableListOf<CheckBox>()

    private fun AdvancedStage.addBetGroup() {
        val cbGroup = CheckBoxGroup()
        var newX    = LG.box.x

        List(3) { CheckBox(CheckBoxStyle.stavka) }.zip(listBet).onEachIndexed { index, pair ->
            with(pair.first) {
                listCB.add(this)
                val label = Label(pair.second.toString(), LabelStyle.font_40)
                this@addBetGroup.addActor(this)
                with(LG.box) {
                    setBounds(newX, y, w, h)
                    newX += w + hs
                    addAndFillActor(label)
                    label.disable()
                    label.setAlignment(Align.center)
                }
                checkBoxGroup = cbGroup

                setOnCheckListener { if (it) betFlow.value = pair.second }

                if (index == 0) check()
            }
        }

    }

    private fun AdvancedStage.addSpinButton() {
        addActor(playButton)
        playButton.apply {
            with(LG.knopa) { setBounds(x, y, w, h) }
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
        coroutine.launch(Dispatchers.IO) {
            GameDataStoreManager.Balance.collect { balance ->
                 Gdx.app.postRunnable { balanceText.setText(balance!!.transformToBalanceFormat()) }
            }
        }
    }

    private fun spinHandler() {
        listCB.onEach { it.disable() }
        playButton.disable()
        coroutine.launch(Dispatchers.Default) {

            if (checkBalance()) spinAndSetResult()

            listCB.onEach { it.enable() }
            playButton.enable()
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
        coroutine.launch(Dispatchers.IO) {
            com.rcks.scaloi.game.manager.GameDataStoreManager.Balance.update { it!! + sumPrice }
        }
    }

}