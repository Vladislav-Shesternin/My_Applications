package com.pharhaslo.slo7.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.pharhaslo.slo7.game.actors.ButtonClickable
import com.pharhaslo.slo7.game.actors.CheckBox
import com.pharhaslo.slo7.game.actors.ProgressBar
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.advanced.AdvancedScreen
import com.pharhaslo.slo7.game.advanced.AdvancedStage
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.MusicUtil
import com.pharhaslo.slo7.game.assets.util.SoundUtil
import com.pharhaslo.slo7.game.languageSprite
import com.pharhaslo.slo7.game.manager.CheckBoxManager
import com.pharhaslo.slo7.game.manager.LanguageManager
import com.pharhaslo.slo7.game.utils.*
import com.pharhaslo.slo7.game.manager.NavigationManager
import com.pharhaslo.slo7.game.utils.language.SpriteRU
import com.pharhaslo.slo7.game.utils.language.SpriteUK
import com.pharhaslo.slo7.game.utils.language.SpriteUS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import com.pharhaslo.slo7.game.utils.ProgressBar as PBar

private var progressSoundVolume = 0
private var progressMusicVolume = 0
private var checkedLanguage = LanguageManager.language

class OptionsScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineCheckBoxManager = CoroutineScope(Dispatchers.Default)

    private val checkBoxManager = CheckBoxManager()



    override fun show() {
        super.show()
        setBackground(SpriteManager.OptionsSprite.BACKGROUND.data.texture)
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
        val image = Image(com.pharhaslo.slo7.game.assets.SpriteManager.OptionsSprite.STATIC.data.texture).apply {
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
                { com.pharhaslo.slo7.game.screens.OptionsScreen.CheckBoxHandler.handlerUK() },
                { com.pharhaslo.slo7.game.screens.OptionsScreen.CheckBoxHandler.handlerRU() },
                { com.pharhaslo.slo7.game.screens.OptionsScreen.CheckBoxHandler.handlerUS() },
            )

            var newY: Float

            val checkBoxList = List(3) { CheckBox(com.pharhaslo.slo7.game.actors.CheckBox.Style.style_1) }.onEachIndexed { index, checkBox ->
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
        val image = Image(com.pharhaslo.slo7.game.assets.SpriteManager.OptionsSprite.PROGRESS_RANGE.data.texture).apply {
            setBoundsFigmaY(Options.PROGRESS_RANGE_SOUND_X, Options.PROGRESS_RANGE_SOUND_Y, Options.PROGRESS_RANGE_W, Options.PROGRESS_RANGE_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgressRangeMusic() {
        val image = Image(com.pharhaslo.slo7.game.assets.SpriteManager.OptionsSprite.PROGRESS_RANGE.data.texture).apply {
            setBoundsFigmaY(Options.PROGRESS_RANGE_MUSIC_X, Options.PROGRESS_RANGE_MUSIC_Y, Options.PROGRESS_RANGE_W, Options.PROGRESS_RANGE_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgressBarSound() {
        val progressBar = ProgressBar().apply {
            setPositionFigmaY(Options.PROGRESS_BAR_SOUND_X, Options.PROGRESS_BAR_SOUND_Y, PBar.H)
            if (com.pharhaslo.slo7.game.screens.progressSoundVolume == 0) com.pharhaslo.slo7.game.screens.progressSoundVolume = com.pharhaslo.slo7.game.manager.AudioManager.volumeLevelFrom_0_to_100
            setProgress(com.pharhaslo.slo7.game.screens.progressSoundVolume)
            progressBlock = {
                com.pharhaslo.slo7.game.screens.progressSoundVolume = it
                SoundUtil.volumeLevel.value = (it / 100f)
                log("sound p = $it")
            }
        }
        addActor(progressBar)
    }

    private fun AdvancedStage.addProgressBarMusic() {
        val progressBar = ProgressBar().apply {
            setPositionFigmaY(Options.PROGRESS_BAR_MUSIC_X, Options.PROGRESS_BAR_MUSIC_Y, PBar.H)
            if (com.pharhaslo.slo7.game.screens.progressMusicVolume == 0) com.pharhaslo.slo7.game.screens.progressMusicVolume = com.pharhaslo.slo7.game.manager.AudioManager.volumeLevelFrom_0_to_100
            setProgress(com.pharhaslo.slo7.game.screens.progressMusicVolume)
            progressBlock = {
                com.pharhaslo.slo7.game.screens.progressMusicVolume = it
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