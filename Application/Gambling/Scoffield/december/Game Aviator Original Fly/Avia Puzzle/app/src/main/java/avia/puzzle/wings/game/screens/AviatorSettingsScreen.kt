package avia.puzzle.wings.game.screens

import avia.puzzle.wings.game.LibGDXGame
import avia.puzzle.wings.game.actors.button.AButton
import avia.puzzle.wings.game.actors.checkbox.ACheckBox
import avia.puzzle.wings.game.actors.progress.APink
import avia.puzzle.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.puzzle.wings.game.utils.actor.animHide
import avia.puzzle.wings.game.utils.actor.setOnClickListener
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.advanced.AdvancedStage
import avia.puzzle.wings.game.utils.region
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.launch

class AviatorSettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB     = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val menuBtn     = AButton(this, AButton.Static.Type.MENU)
    private val settingsImg = Image(game.gameAssets.settings)
    private val exitBtn     = Image(game.gameAssets.exit)
    private val soundPink   = APink(this)
    private val musicPink   = APink(this)

    override fun show() {
        setUIBackground(game.splashAssets.AviatorLoading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusicCB()
        addMenuBtn()
        addExitBtn()
        addSettings()
        addPinks()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(505f, 1211f, 76f, 75f)
            if (isMusic.not()) check(false)

            setOnCheckListener { isCheck ->
                if (isCheck) {
                    isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(19f, 1211f, 76f, 75f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addExitBtn() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(182f, 78f, 236f, 90f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addSettings() {
        addActor(settingsImg)
        settingsImg.setBounds(27f, 353f, 547f, 594f)
    }

    private fun AdvancedStage.addPinks() {
        addActors(soundPink, musicPink)
        soundPink.apply {
            setBounds(129f, 664f, 342f, 47f)
            setProgressPercent(screen.game.soundUtil.volumeLevel * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.soundUtil.volumeLevel = it / 100f
                }
            }
        }
        musicPink.apply {
            setBounds(129f, 485f, 342f, 47f)
            setProgressPercent(screen.game.musicUtil.volumeLevelFlow.value * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.musicUtil.volumeLevelFlow.value = it / 100f
                }
            }
        }
    }

}