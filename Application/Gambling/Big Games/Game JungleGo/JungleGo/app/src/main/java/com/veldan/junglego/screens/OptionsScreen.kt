package com.veldan.junglego.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.junglego.HEIGHT
import com.veldan.junglego.WIDTH
import com.veldan.junglego.actors.ButtonClickable
import com.veldan.junglego.actors.CheckBox
import com.veldan.junglego.actors.ProgressBar
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.advanced.AdvancedScreen
import com.veldan.junglego.advanced.AdvancedStage
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.MusicUtil
import com.veldan.junglego.assets.util.SoundUtil
import com.veldan.junglego.languageSprite
import com.veldan.junglego.manager.AudioManager
import com.veldan.junglego.manager.CheckBoxManager
import com.veldan.junglego.manager.LanguageManager
import com.veldan.junglego.manager.NavigationManager
import com.veldan.junglego.utils.Options
import com.veldan.junglego.utils.language.SpriteRU
import com.veldan.junglego.utils.language.SpriteUK
import com.veldan.junglego.utils.language.SpriteUS
import com.veldan.junglego.utils.log
import com.veldan.junglego.utils.setBoundsFigmaY
import com.veldan.junglego.utils.setPositionFigmaY
import java.util.*

private var progressSoundVolume = 0
private var progressMusicVolume = 0
private var checkedLanguage = ""
var isUseBird = true

class OptionsScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
        background = SpriteManager.OptionsSprite.BACKGROUND.textureData.texture
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addStatic()
        addBack()
        addCheckBoxGroup()
        addCheckBoxBird()
        addProgressBarSound()
        addProgressBarMusic()
    }


    private fun AdvancedStage.addStatic() {
        val image = Image(SpriteManager.OptionsSprite.STATIC.textureData.texture).apply {
            setBoundsFigmaY(Options.STATIC_X, Options.STATIC_Y, Options.STATIC_W, Options.STATIC_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addBack() {
        val button = ButtonClickable(ButtonClickable.Style.style_2).apply {
            setBoundsFigmaY(Options.BACK_X, Options.BACK_Y, Options.BACK_W, Options.BACK_H)
            setOnClickListener(SoundUtil.CLICK) { NavigationManager.back() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addCheckBoxBird() {
        val checkBox = CheckBox(CheckBox.Style.style_1).apply {
            if (isUseBird) check()
            setBoundsFigmaY(Options.CHECK_BOX_BIRD_X, Options.CHECK_BOX_BIRD_Y, Options.CHECK_BOX_BIRD_W, Options.CHECK_BOX_BIRD_H)
            setOnCheckListener(SoundUtil.CLICK) { isUseBird = it }
        }
        addActor(checkBox)
    }

    private fun AdvancedStage.addCheckBoxGroup() {
        val group = AdvancedGroup().apply {
            setBoundsFigmaY(Options.CHECK_BOX_GROUP_X, Options.CHECK_BOX_GROUP_Y, Options.CHECK_BOX_GROUP_W, Options.CHECK_BOX_GROUP_H)
            val onCheckBlockList = listOf(
                { CheckBoxHandler.handlerUA() },
                { CheckBoxHandler.handlerRU() },
                { CheckBoxHandler.handlerUS() },
            )

            var newX: Float

            val checkBoxList = List(3) { CheckBox(CheckBox.Style.style_1) }.onEachIndexed { index, checkBox ->
                newX = (Options.CHECK_BOX_W * index) + (Options.CHECK_BOX_SPACE_HORIZONTAL * index)
                checkBox.setBounds(newX, 0f, Options.CHECK_BOX_W, Options.CHECK_BOX_H)
                checkBox.setOnCheckListener { onCheckBlockList[index].invoke() }
                addActor(checkBox)
            }
            CheckBoxManager(*checkBoxList.toTypedArray())
            if (checkedLanguage.isEmpty()) checkedLanguage = LanguageManager.language
            checkBoxList.checkByCurrentLanguage()
        }
        addActor(group)
    }

    private fun AdvancedStage.addProgressBarSound() {
        val progressBar = ProgressBar().apply {
            setPositionFigmaY(Options.PROGRESS_BAR_SOUND_X, Options.PROGRESS_BAR_SOUND_Y, Options.PROGRESS_BAR_H)
            if (progressSoundVolume == 0) progressSoundVolume = AudioManager.volumeLevelFrom_0_to_100
            setProgress(progressSoundVolume)
            progressBlock = {
                progressSoundVolume = it
                SoundUtil.volumeLevel.value = (it / 100f)
                log("sound p = $it")
            }
        }
        addActor(progressBar)
    }

    private fun AdvancedStage.addProgressBarMusic() {
        val progressBar = ProgressBar().apply {
            setPositionFigmaY(Options.PROGRESS_BAR_MUSIC_X, Options.PROGRESS_BAR_MUSIC_Y, Options.PROGRESS_BAR_H)
            if (progressMusicVolume == 0) progressMusicVolume = AudioManager.volumeLevelFrom_0_to_100
            setProgress(progressMusicVolume)
            progressBlock = {
                progressMusicVolume = it
                MusicUtil.volumeLevel.value = it
                log("music p = $it")
            }
        }
        addActor(progressBar)
        AudioManager
    }



    private fun List<CheckBox>.checkByCurrentLanguage() {
        log("checkedLanguage = $checkedLanguage")
        when(checkedLanguage) {
            "uk" -> get(0).checkAndDisable()
            "ru" -> get(1).checkAndDisable()
            "us" -> get(2).checkAndDisable()
        }
    }



    private object CheckBoxHandler {
        fun handlerUA() {
            log("UK")
            checkedLanguage = "uk"
            languageSprite = SpriteUK
        }

        fun handlerRU() {
            log("RU")
            checkedLanguage = "ru"
            languageSprite = SpriteRU
        }

        fun handlerUS() {
            log("US")
            checkedLanguage = "us"
            languageSprite = SpriteUS
        }
    }


}