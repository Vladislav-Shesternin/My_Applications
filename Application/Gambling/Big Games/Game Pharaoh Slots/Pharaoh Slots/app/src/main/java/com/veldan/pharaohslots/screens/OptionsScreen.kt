package com.veldan.pharaohslots.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.pharaohslots.actors.ButtonClickable
import com.veldan.pharaohslots.actors.CheckBox
import com.veldan.pharaohslots.actors.ProgressBar
import com.veldan.pharaohslots.advanced.AdvancedGroup
import com.veldan.pharaohslots.advanced.AdvancedScreen
import com.veldan.pharaohslots.advanced.AdvancedStage
import com.veldan.pharaohslots.assets.SpriteManager
import com.veldan.pharaohslots.assets.util.MusicUtil
import com.veldan.pharaohslots.assets.util.SoundUtil
import com.veldan.pharaohslots.languageSprite
import com.veldan.pharaohslots.manager.AudioManager
import com.veldan.pharaohslots.manager.CheckBoxManager
import com.veldan.pharaohslots.manager.LanguageManager
import com.veldan.pharaohslots.manager.NavigationManager
import com.veldan.pharaohslots.utils.*
import com.veldan.pharaohslots.utils.language.SpriteRU
import com.veldan.pharaohslots.utils.language.SpriteUK
import com.veldan.pharaohslots.utils.language.SpriteUS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import com.veldan.pharaohslots.utils.ProgressBar as PBar

private var progressSoundVolume = 0
private var progressMusicVolume = 0
private var checkedLanguage = LanguageManager.language

class OptionsScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineCheckBoxManager = CoroutineScope(Dispatchers.Default)

    private val checkBoxManager = CheckBoxManager()



    override fun show() {
        super.show()
        background = SpriteManager.OptionsSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineCheckBoxManager)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addStatic()
        addBack()
        addCheckBoxGroup()
        addProgressRangeSound()
        addProgressRangeMusic()
        addProgressBarSound()
        addProgressBarMusic()
    }


    private fun AdvancedStage.addStatic() {
        val image = Image(SpriteManager.OptionsSprite.STATIC.data.texture).apply {
            setBoundsFigmaY(Options.STATIC_X, Options.STATIC_Y, Options.STATIC_W, Options.STATIC_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addBack() {
        val button = ButtonClickable(ButtonClickable.Style.back).apply {
            setBoundsFigmaY(Options.BACK_X, Options.BACK_Y, Options.BACK_W, Options.BACK_H)
            setOnClickListener { NavigationManager.back() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addCheckBoxGroup() {
        val group = AdvancedGroup().apply {
            setBoundsFigmaY(Options.CHECK_BOX_GROUP_X, Options.CHECK_BOX_GROUP_Y, Options.CHECK_BOX_GROUP_W, Options.CHECK_BOX_GROUP_H)
            val onCheckBlockList = listOf(
                { CheckBoxHandler.handlerUK() },
                { CheckBoxHandler.handlerRU() },
                { CheckBoxHandler.handlerUS() },
            )

            var newY: Float

            val checkBoxList = List(3) { CheckBox(CheckBox.Style.style_1) }.onEachIndexed { index, checkBox ->
                newY = (Options.CHECK_BOX_H + Options.CHECK_BOX_SPACE_VERTICAL) * index
                checkBox.setBoundsFigmaY(0f, newY, Options.CHECK_BOX_W, Options.CHECK_BOX_H, Options.CHECK_BOX_GROUP_H)
                checkBox.setOnCheckListener { onCheckBlockList[index].invoke() }
                addActor(checkBox)
            }
            checkBoxManager.manageCheckBox(checkBoxList)
            checkBoxList.checkByCurrentLanguage()
        }
        addActor(group)
    }

    private fun AdvancedStage.addProgressRangeSound() {
        val image = Image(SpriteManager.OptionsSprite.PROGRESS_RANGE.data.texture).apply {
            setBoundsFigmaY(Options.PROGRESS_RANGE_SOUND_X, Options.PROGRESS_RANGE_SOUND_Y, Options.PROGRESS_RANGE_W, Options.PROGRESS_RANGE_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgressRangeMusic() {
        val image = Image(SpriteManager.OptionsSprite.PROGRESS_RANGE.data.texture).apply {
            setBoundsFigmaY(Options.PROGRESS_RANGE_MUSIC_X, Options.PROGRESS_RANGE_MUSIC_Y, Options.PROGRESS_RANGE_W, Options.PROGRESS_RANGE_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgressBarSound() {
        val progressBar = ProgressBar().apply {
            setPositionFigmaY(Options.PROGRESS_BAR_SOUND_X, Options.PROGRESS_BAR_SOUND_Y, PBar.H)
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
            setPositionFigmaY(Options.PROGRESS_BAR_MUSIC_X, Options.PROGRESS_BAR_MUSIC_Y, PBar.H)
            if (progressMusicVolume == 0) progressMusicVolume = AudioManager.volumeLevelFrom_0_to_100
            setProgress(progressMusicVolume)
            progressBlock = {
                progressMusicVolume = it
                MusicUtil.volumeLevel.value = it
                log("music p = $it")
            }
        }
        addActor(progressBar)
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
        fun handlerUK() {
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