package avia.adventure.wings.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import avia.adventure.wings.game.manager.AudioManager
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedGroup
import avia.adventure.wings.game.utils.advanced.AdvancedScreen

class ASettingsGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private const val ONE_PERCENT = 100f / 6f
        private val currentVolumeLevelPercent = AudioManager.volumeLevelPercent

        // min 0 | max 6
        private var soundCounter = (currentVolumeLevelPercent / ONE_PERCENT).toInt()
        private var musicCounter = (currentVolumeLevelPercent / ONE_PERCENT).toInt()
    }

    private val assets    = screen.game.gameAssets
    private val soundUtil = screen.game.soundUtil
    private val musicUtil = screen.game.musicUtil

    private val soundVolumeGroup = AVolumeGroup(screen)
    private val musicVolumeGroup = AVolumeGroup(screen)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(assets.Settings))
        addSoundVolumeGroup()
        addMusicVolumeGroup()
        addSoundBtn()
        addMusicBtn()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addSoundVolumeGroup() {
        addActor(soundVolumeGroup)
        soundVolumeGroup.setBounds(172f, 531f, 247f, 50f)

        soundVolumeGroup.update(soundCounter)
        soundUtil.volumeLevel = soundCounter * ONE_PERCENT
    }

    private fun addMusicVolumeGroup() {
        addActor(musicVolumeGroup)
        musicVolumeGroup.setBounds(177f, 342f, 247f, 50f)

        musicVolumeGroup.update(musicCounter)
        musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
    }

    private fun addSoundBtn() {
        val minus = Actor()
        val plus  = Actor()

        addActors(minus, plus)

        minus.apply {
            setBounds(86f, 522f, 63f, 63f)
            setOnClickListener(soundUtil) {
                if (soundCounter-1 >= 0) {
                    soundCounter--
                    soundVolumeGroup.update(soundCounter)
                    soundUtil.volumeLevel = soundCounter * ONE_PERCENT
                }
            }
        }
        plus.apply {
            setBounds(439f, 522f, 63f, 63f)
            setOnClickListener(soundUtil) {
                if (soundCounter+1 <= 6) {
                    soundCounter++
                    soundVolumeGroup.update(soundCounter)
                    soundUtil.volumeLevel = soundCounter * ONE_PERCENT
                }
            }
        }
    }

    private fun addMusicBtn() {
        val minus = Actor()
        val plus  = Actor()

        addActors(minus, plus)

        minus.apply {
            setBounds(86f, 335f, 63f, 63f)
            setOnClickListener(soundUtil) {
                if (musicCounter-1 >= 0) {
                    musicCounter--
                    musicVolumeGroup.update(musicCounter)
                    musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
                }
            }
        }
        plus.apply {
            setBounds(439f, 335f, 63f, 63f)
            setOnClickListener(soundUtil) {
                if (musicCounter+1 <= 6) {
                    musicCounter++
                    musicVolumeGroup.update(musicCounter)
                    musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
                }
            }
        }
    }

}