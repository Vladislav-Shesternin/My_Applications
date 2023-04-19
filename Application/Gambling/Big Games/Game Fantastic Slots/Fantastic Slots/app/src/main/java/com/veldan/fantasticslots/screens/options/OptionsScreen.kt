package com.veldan.fantasticslots.screens.options

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.checkbox.CheckBox
import com.veldan.fantasticslots.actors.progressBar.ProgressBar
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.advanced.AdvancedScreen
import com.veldan.fantasticslots.advanced.AdvancedStage
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.assets.util.MusicUtil
import com.veldan.fantasticslots.assets.util.SoundUtil
import com.veldan.fantasticslots.manager.AudioManager
import com.veldan.fantasticslots.manager.DataStoreManager
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.layout.setPositionFigmaY
import kotlinx.coroutines.launch
import com.veldan.fantasticslots.utils.ProgressBar as PBar

private var progressSoundVolume = -1
private var progressMusicVolume = -1

class OptionsScreen : AdvancedScreen() {
    override val viewport   = FitViewport(WIDTH, HEIGHT)
    override val controller = OptionsScreenController(this)

    companion object {
        var isCheckedTraining = true
        var isCheckedStartingBalance = true
            private set
    }

    private val soundProgressBar        = ProgressBar()
    private val musicProgressBar        = ProgressBar()
    private val soundLabel              = Label("", LabelStyleUtil.robotoMono60)
    private val musicLabel              = Label("", LabelStyleUtil.robotoMono60)
    private val soundImage              = Image(SpriteManager.OptionsSprite.SOUND.data.texture)
    private val musicImage              = Image(SpriteManager.OptionsSprite.MUSIC.data.texture)
    private val backButton              = ButtonClickable(ButtonClickable.Style.back)
    private val trainingCheckBox        = CheckBox(CheckBox.Style.style_1)
    private val startingBalanceCheckBox = CheckBox(CheckBox.Style.style_1)
    private val trainingLabel           = RollingLabel(activityResources.getString(R.string.training)        , LabelStyleUtil.robotoMono60, alignment = Align.left)
    private val startingBalanceLabel    = RollingLabel(activityResources.getString(R.string.starting_balance), LabelStyleUtil.robotoMono60, alignment = Align.left)



    override fun show() {
        super.show()
        background = SpriteManager.OptionsSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgressBarSound()
        addProgressBarMusic()
        addSoundImage()
        addMusicImage()
        addTrainingCheckBox()
        addStartingBalanceCheckBox()
        addTrainingLabel()
        addStartingBalanceLabel()
        addBackButton()
        addSoundLabel()
        addMusicLabel()
    }



    private fun AdvancedStage.addProgressBarSound() {
        addActor(soundProgressBar)
        soundProgressBar.apply {
            setPositionFigmaY(Options.PROGRESS_BAR_SOUND_X, Options.PROGRESS_BAR_SOUND_Y, PBar.H)
            if (progressSoundVolume == -1) progressSoundVolume = AudioManager.volumeLevelFrom_0_to_100
            setProgress(progressSoundVolume)
            progressBlock = {
                progressSoundVolume = it
                soundLabel.setText(it)
                SoundUtil.volumeLevel.value = (it / 100f)
                log("sound p = $it")
            }
        }
    }

    private fun AdvancedStage.addProgressBarMusic() {
        addActor(musicProgressBar)
        musicProgressBar.apply {
            setPositionFigmaY(Options.PROGRESS_BAR_MUSIC_X, Options.PROGRESS_BAR_MUSIC_Y, PBar.H)
            if (progressMusicVolume == -1) progressMusicVolume = AudioManager.volumeLevelFrom_0_to_100
            setProgress(progressMusicVolume)
            progressBlock = {
                progressMusicVolume = it
                musicLabel.setText(it)
                MusicUtil.volumeLevel.value = it
                log("music p = $it")
            }
        }
    }

    private fun AdvancedStage.addSoundImage() {
        addActor(soundImage)
        soundImage.apply { setBoundsFigmaY(Options.SOUND_X, Options.SOUND_Y, Options.SOUND_W, Options.SOUND_H) }
    }

    private fun AdvancedStage.addMusicImage() {
        addActor(musicImage)
        musicImage.apply { setBoundsFigmaY(Options.MUSIC_X, Options.MUSIC_Y, Options.MUSIC_W, Options.MUSIC_H) }
    }

    private fun AdvancedStage.addBackButton() {
        addActor(backButton)
        backButton.apply {
            setBoundsFigmaY(Options.BACK_X, Options.BACK_Y, Options.BACK_W, Options.BACK_H)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedStage.addTrainingCheckBox() {
        addActor(trainingCheckBox)
        trainingCheckBox.apply {
            setBoundsFigmaY(Options.CHECK_BOX_TRAINING_X, Options.CHECK_BOX_TRAINING_Y, Options.CHECK_BOX_TRAINING_W, Options.CHECK_BOX_TRAINING_H)
            setOnCheckListener { isCheck ->
                isCheckedTraining = isCheck
                controller.coroutineDataStore.launch { DataStoreManager.updateTraining { isCheck } }
            }
            controller.coroutineDataStore.launch {
                isCheckedTraining = if (DataStoreManager.getTraining()) {
                    check()
                    true
                } else false
            }
        }
    }

    private fun AdvancedStage.addStartingBalanceCheckBox() {
        addActor(startingBalanceCheckBox)
        startingBalanceCheckBox.apply {
            setBoundsFigmaY(Options.CHECK_BOX_STARTING_BALANCE_X, Options.CHECK_BOX_STARTING_BALANCE_Y, Options.CHECK_BOX_STARTING_BALANCE_W, Options.CHECK_BOX_STARTING_BALANCE_H)
            setOnCheckListener { isCheck -> isCheckedStartingBalance = isCheck }
            if (isCheckedStartingBalance) check()
        }
    }

    private fun AdvancedStage.addTrainingLabel() {
        addActor(trainingLabel)
        trainingLabel.apply {
            setBoundsFigmaY(Options.LABEL_TRAINING_X, Options.LABEL_TRAINING_Y, Options.LABEL_TRAINING_W, Options.LABEL_TRAINING_H)
        }
    }

    private fun AdvancedStage.addStartingBalanceLabel() {
        addActor(startingBalanceLabel)
        startingBalanceLabel.apply {
            setBoundsFigmaY(Options.LABEL_STARTING_BALANCE_X, Options.LABEL_STARTING_BALANCE_Y, Options.LABEL_STARTING_BALANCE_W, Options.LABEL_STARTING_BALANCE_H)
        }
    }

    private fun AdvancedStage.addSoundLabel() {
        addActor(soundLabel)
        soundLabel.apply {
            setText(progressSoundVolume)
            setBoundsFigmaY(Options.SOUND_LABEL_X, Options.SOUND_LABEL_Y, Options.SOUND_LABEL_W, Options.SOUND_LABEL_H)
        }
    }

    private fun AdvancedStage.addMusicLabel() {
        addActor(musicLabel)
        musicLabel.apply {
            setText(progressMusicVolume)
            setBoundsFigmaY(Options.MUSIC_LABEL_X, Options.MUSIC_LABEL_Y, Options.MUSIC_LABEL_W, Options.MUSIC_LABEL_H)
        }
    }

}