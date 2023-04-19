package com.veldan.sportslots.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.sportslots.HEIGHT
import com.veldan.sportslots.WIDTH
import com.veldan.sportslots.actors.ButtonClickable
import com.veldan.sportslots.actors.slot.SlotGroup
import com.veldan.sportslots.advanced.AdvancedScreen
import com.veldan.sportslots.advanced.AdvancedStage
import com.veldan.sportslots.assets.FontManager
import com.veldan.sportslots.assets.SpriteManager
import com.veldan.sportslots.languageSprite
import com.veldan.sportslots.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class GameScreen: AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineAutospin = CoroutineScope(Dispatchers.Main)
    private val coroutineSpin = CoroutineScope(Dispatchers.Main)
    private val coroutineBalance = CoroutineScope(Dispatchers.IO)

    private val slotGroup = SlotGroup(viewport)
    private val spin = ButtonClickable()
    private val autospin = ButtonClickable()
    private val balanceText = Label("", Label.LabelStyle(FontManager.rubik_33, Color(13f / 255, 0f, 73f / 255, 1f)))

    private var balance = AtomicInteger()
    private val autospinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutospin = Once()

    override fun show() {
        super.show()
        stage.addActorsOnStage()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineAutospin, coroutineSpin, coroutineBalance)
    }



    private fun AdvancedStage.addActorsOnStage(){
        addSlotGroupPanel()
        addBalancePanel()
        addBalanceText()
        addMenu()
        addCollection()
        addSpin()
        addAutospin()
        addActor(slotGroup)
    }

    private fun AdvancedStage.addSlotGroupPanel() {
        val image = Image(SpriteManager.GameSprite.SLOT_GROUP_PANEL.textureData.texture).apply {
            setBoundsFigmaY(SLOT_GROUP_PANEL_X, SLOT_GROUP_PANEL_Y, SLOT_GROUP_PANEL_W, SLOT_GROUP_PANEL_H)
        }
        addActor(image)
    }
    
    private fun AdvancedStage.addBalancePanel() {
        val image = Image(SpriteManager.GameSprite.BALANCE_PANEL.textureData.texture).apply {
            setBoundsFigmaY(BALANCE_PANEL_X, BALANCE_PANEL_Y, BALANCE_PANEL_W, BALANCE_PANEL_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addBalanceText(){
        val text = balanceText.apply {
            setBoundsFigmaY(BALANCE_TEXT_X, BALANCE_TEXT_Y, BALANCE_TEXT_W, BALANCE_TEXT_H)
            setAlignment(Align.center)
            wrap = true

            coroutineBalance.launch {
                DataStoreUtil.collectPrice {
                    setText(it)
                    balance.set(it)
                }
            }
        }
        addActor(text)
    }

    private fun AdvancedStage.addMenu(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = SpriteManager.GameSprite.MENU_DEF.textureData.texture,
            pressed = SpriteManager.GameSprite.MENU_PRESS.textureData.texture,
        )).apply {
            setBoundsFigmaY(MENU_X, MENU_Y, MENU_W, MENU_H)
            setOnClickListener(SoundUtil.CLICK) { menuHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addCollection(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = SpriteManager.GameSprite.COLLECTION_DEF.textureData.texture,
            pressed = SpriteManager.GameSprite.COLLECTION_PRESS.textureData.texture,
        )).apply {
            setBoundsFigmaY(COLLECTION_X, COLLECTION_Y, COLLECTION_W, COLLECTION_H)
            setOnClickListener(SoundUtil.CLICK) { collectionHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addSpin(){
        val button = spin.apply {
            setStyle(ButtonClickable.Style(
                    default = languageSprite.spin_def,
                    pressed = languageSprite.spin_press,
            ))
            setBoundsFigmaY(SPIN_X, SPIN_Y, SPIN_W, SPIN_H)
            setOnClickListener(SoundUtil.CLICK) { spinHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addAutospin(){
        val button = autospin.apply {
            setStyle(ButtonClickable.Style(
                default = SpriteManager.GameSprite.AUTOSPIN_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.AUTOSPIN_PRESS.textureData.texture,
            ))
            setBoundsFigmaY(AUTOSPIN_X, AUTOSPIN_Y, AUTOSPIN_W, AUTOSPIN_H)
            setOnClickListener(SoundUtil.CLICK) { autospinHandler() }
        }
        addActor(button)
    }



    private fun ButtonClickable.menuHandler() {
        NavigationUtil.back()
    }

    private fun ButtonClickable.collectionHandler() {
        NavigationUtil.navigate(CollectionScreen(), GameScreen())
    }

    private fun ButtonClickable.spinHandler() {
        disable()
        pressed()
        autospin.disable(SpriteManager.GameSprite.AUTOSPIN_DISABLE.textureData.texture)
        coroutineSpin.launch {
            spinAndGetResult()
            enable()
            unpressed()
            autospin.enable()
        }
    }

    private fun ButtonClickable.autospinHandler() {
        autospinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.SPIN else AutospinState.DEFAULT
        }

        onceStartAutospin.once { startAutospin() }
    }



    private fun startAutospin() {
        val coroutineSpin = CoroutineScope(Dispatchers.Main)

        coroutineAutospin.launch {
            autospinStateFlow.collect { state ->

                if (state == AutospinState.SPIN) {
                    autospin.pressed()
                    spin.disable(languageSprite.spin_disable)

                    coroutineSpin.launch {
                        while (balance.get() >= 1000) {
                            spinAndGetResult()

                            if (autospinStateFlow.value == AutospinState.DEFAULT) {
                                autospin.enable()
                                spin.enable()
                                cancel()
                            }
                        }
                    }
                }

                if (autospinStateFlow.value == AutospinState.DEFAULT) {
                    autospin.disable(SpriteManager.GameSprite.AUTOSPIN_DISABLE.textureData.texture)
                }

            }
        }
    }



    private suspend fun spinAndGetResult() {
        coroutineBalance.launch { DataStoreUtil.updatePrice { it - 1000 } }

        val result = slotGroup.spin()
        result.listSlotItem.onEach { slotItem ->
            coroutineBalance.launch { DataStoreUtil.updatePrice { it + slotItem.price } }
        }
        coroutineBalance.launch { DataStoreUtil.updatePrice { it + (result.bonus?.price ?: 0) } }
    }



    private enum class AutospinState {
        DEFAULT, SPIN,
    }

}