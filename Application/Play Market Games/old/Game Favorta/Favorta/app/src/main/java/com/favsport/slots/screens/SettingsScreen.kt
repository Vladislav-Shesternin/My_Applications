package com.favsport.slots.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.favsport.slots.*
import com.favsport.slots.actors.ButtonClickable
import com.favsport.slots.actors.ProgressAudio
import com.favsport.slots.advanced.AdvancedScreen
import com.favsport.slots.advanced.AdvancedStage
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.screens.SettingsScreen.Language.*
import com.favsport.slots.utils.*
import com.favsport.slots.utils.language.SpriteRU
import com.favsport.slots.utils.language.SpriteUK
import com.favsport.slots.utils.language.SpriteUS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

var currentBoxLanguage = UA

class SettingsScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineLanguage = CoroutineScope(Dispatchers.Default)

    private var currentLanguage = MutableStateFlow(currentBoxLanguage)

    private val boxUA = ButtonClickable()
    private val boxRU = ButtonClickable()
    private val boxUS = ButtonClickable()


    override fun show() {
        super.show()
        stage.addActorsOnStage()
        determineLanguage()
    }



    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineLanguage)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addStatic()
        addBack()
        addBoxUA()
        addBoxRU()
        addBoxUS()
        addProgressSound()
        addProgressMusic()
    }



    private fun AdvancedStage.addStatic() {
        val image = Image(SpriteManager.SettingsSprite.SETTINGS_STATIC.textureData.texture).apply {
            setBoundsFigmaY(STATIC_X, STATIC_Y, STATIC_W, STATIC_H)
        }
        addActor(image)
    }

    lateinit var backButton: ButtonClickable
    private fun AdvancedStage.addBack() {
        backButton = ButtonClickable(
            ButtonClickable.Style(
                default = languageSprite.back_def,
                pressed = languageSprite.back_press,
            )
        ).apply {
            setBoundsFigmaY(BACK_X, BACK_Y, BACK_W, BACK_H)
            setOnClickListener(SoundUtil.CLICK) { backHandler() }
        }
        addActor(backButton)
    }

    private fun AdvancedStage.addBoxUA() {
        val button = boxUA.apply {
            setStyle(
                ButtonClickable.Style(
                    default = SpriteManager.SettingsSprite.BOX_DEF.textureData.texture,
                    pressed = SpriteManager.SettingsSprite.BOX_PRESS.textureData.texture,
                )
            )
            setBoundsFigmaY(BOX_UA_X, BOX_Y, BOX_W, BOX_H)
            setOnClickListener(SoundUtil.CLICK) { languageBoxHandler(UA) }
        }
        addActor(button)
    }

    private fun AdvancedStage.addBoxRU() {
        val button = boxRU.apply {
            setStyle(
                ButtonClickable.Style(
                    default = SpriteManager.SettingsSprite.BOX_DEF.textureData.texture,
                    pressed = SpriteManager.SettingsSprite.BOX_PRESS.textureData.texture,
                )
            )
            setBoundsFigmaY(BOX_RU_X, BOX_Y, BOX_W, BOX_H)
            setOnClickListener(SoundUtil.CLICK) { languageBoxHandler(RU) }
        }
        addActor(button)
    }

    private fun AdvancedStage.addBoxUS() {
        val button = boxUS.apply {
            setStyle(
                ButtonClickable.Style(
                    default = SpriteManager.SettingsSprite.BOX_DEF.textureData.texture,
                    pressed = SpriteManager.SettingsSprite.BOX_PRESS.textureData.texture,
                )
            )
            setBoundsFigmaY(BOX_US_X, BOX_Y, BOX_W, BOX_H)
            setOnClickListener(SoundUtil.CLICK) { languageBoxHandler(US) }
        }
        addActor(button)
    }

    private fun AdvancedStage.addProgressSound() {
        val image = ProgressAudio().apply {
            currentVolume = SOUND_VOLUME
            setBoundsFigmaY(PROGRESS_X, PROGRESS_SOUND_Y, PROGRESS_W, PROGRESS_H)
            progressBlock = { SOUND_VOLUME = it }
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgressMusic() {
        val image = ProgressAudio().apply {
            currentVolume = MUSIC_VOLUME
            setBoundsFigmaY(PROGRESS_X, PROGRESS_MUSIC_Y, PROGRESS_W, PROGRESS_H)
            progressBlock = {
                if (it == 0f) MusicUtil.currentMusic.pause() else MusicUtil.currentMusic.play()
                MUSIC_VOLUME = it
                MusicUtil.currentMusic.volume = it
            }
        }
        addActor(image)
    }



    private fun ButtonClickable.backHandler() {
        NavigationUtil.back()
    }

    private fun ButtonClickable.languageBoxHandler(language: Language) {
        when (language) {
            UA -> currentLanguage.value = UA
            RU -> currentLanguage.value = RU
            US -> currentLanguage.value = US
        }
    }



    private fun determineLanguage() {
        coroutineLanguage.launch {
            currentLanguage.collect {
                when (it) {
                    UA -> {
                        currentBoxLanguage = UA
                        boxUA.pressedAndDisable()
                        boxRU.unpressedAndEnabled()
                        boxUS.unpressedAndEnabled()
                        languageSprite = SpriteUK
                        updateBack()
                    }
                    RU -> {
                        currentBoxLanguage = RU
                        boxUA.unpressedAndEnabled()
                        boxRU.pressedAndDisable()
                        boxUS.unpressedAndEnabled()
                        languageSprite = SpriteRU
                        updateBack()
                    }
                    US -> {
                        currentBoxLanguage = US
                        boxUA.unpressedAndEnabled()
                        boxRU.unpressedAndEnabled()
                        boxUS.pressedAndDisable()
                        languageSprite = SpriteUS
                        updateBack()
                    }
                }
            }
        }
    }



    private fun updateBack() {
        backButton.setStyle(ButtonClickable.Style(
                default = languageSprite.back_def,
                pressed = languageSprite.back_press,
        ))
    }



    enum class Language {
        UA, RU, US
    }

}