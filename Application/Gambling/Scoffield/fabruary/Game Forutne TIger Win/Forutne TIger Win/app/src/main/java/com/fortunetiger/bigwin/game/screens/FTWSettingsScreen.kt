package com.fortunetiger.bigwin.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.bigwin.game.LibGDXGame
import com.fortunetiger.bigwin.game.actors.progress.FTWProgress
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedStage
import com.fortunetiger.bigwin.game.utils.region
import kotlinx.coroutines.launch

class FTWSettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val FTWImg   = Image(game.allAssets.FTW)
    private val panelImg = Image(game.allAssets.settings)
    private val musicProgress = FTWProgress(this)
    private val soundProgress = FTWProgress(this)

    override fun show() {
        //stageUI.root.animHide()
        setBackBackground(game.loaderAssets.FTWBackground.region)
        super.show()
        //stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addFTWImg()
        addPanelImg()
        addMusicSound()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addFTWImg() {
        addActor(FTWImg)
        FTWImg. setBounds(0f, 0f, 469f, 634f)
    }

    private fun AdvancedStage.addPanelImg() {
        addActor(panelImg)
        panelImg. setBounds(629f, 60f, 716f, 961f)
    }

    private fun AdvancedStage.addMusicSound() {
        addActors(musicProgress, soundProgress)
        musicProgress.setBounds(715f, 615f, 561f, 82f)
        soundProgress.setBounds(715f, 291f, 561f, 82f)

        // sound
        soundProgress.apply {
            setProgressPercent(screen.game.soundUtil.volumeLevel)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.soundUtil.volumeLevel = it
                }
            }
        }
        // music
        musicProgress.apply {
            setProgressPercent(screen.game.musicUtil.volumeLevelFlow.value)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.musicUtil.volumeLevelFlow.value = it
                }
            }
        }
    }

}