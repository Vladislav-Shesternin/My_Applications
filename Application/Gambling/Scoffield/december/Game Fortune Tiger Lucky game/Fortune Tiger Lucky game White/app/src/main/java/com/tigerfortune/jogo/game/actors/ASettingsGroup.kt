package com.tigerfortune.jogo.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBoxGroup
import com.tigerfortune.jogo.game.manager.AudioManager
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen

class ASettingsGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private var isNotifications = true

        private const val ONE_PERCENT = 100f / 7f
        private val currentVolumeLevelPercent = AudioManager.volumeLevelPercent

        // min 0 | max 7
        private var soundCounter = (currentVolumeLevelPercent / ONE_PERCENT).toInt()
        private var musicCounter = (currentVolumeLevelPercent / ONE_PERCENT).toInt()
    }

    private val assets    = screen.game.gameAssets
    private val soundUtil = screen.game.soundUtil
    private val musicUtil = screen.game.musicUtil

    private val soundVolumeGroup = AVolumeGroup(screen)
    private val musicVolumeGroup = AVolumeGroup(screen)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(assets.PANEL_SETTINGS))
        addSoundVolumeGroup()
        addMusicVolumeGroup()
        addSoundBtn()
        addMusicBtn()
        addNotifications()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addSoundVolumeGroup() {
        addActor(soundVolumeGroup)
        soundVolumeGroup.setBounds(326f, 703f, 326f, 73f)

        soundVolumeGroup.update(soundCounter)
        soundUtil.volumeLevel = soundCounter * ONE_PERCENT
    }

    private fun addMusicVolumeGroup() {
        addActor(musicVolumeGroup)
        musicVolumeGroup.setBounds(326f, 462f, 326f, 73f)

        musicVolumeGroup.update(musicCounter)
        musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
    }

    private fun addSoundBtn() {
        val minus = Actor()
        val plus  = Actor()

        addActors(minus, plus)

        minus.apply {
            setBounds(221f, 709f, 66f, 66f)
            setOnClickListener(soundUtil) {
                if (soundCounter-1 >= 0) {
                    soundCounter--
                    soundVolumeGroup.update(soundCounter)
                    soundUtil.volumeLevel = soundCounter * ONE_PERCENT
                }
            }
        }
        plus.apply {
            setBounds(693f, 709f, 66f, 66f)
            setOnClickListener(soundUtil) {
                if (soundCounter+1 <= 7) {
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
            setBounds(221f, 468f, 66f, 66f)
            setOnClickListener(soundUtil) {
                if (musicCounter-1 >= 0) {
                    musicCounter--
                    musicVolumeGroup.update(musicCounter)
                    musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
                }
            }
        }
        plus.apply {
            setBounds(693f, 468f, 66f, 66f)
            setOnClickListener(soundUtil) {
                if (musicCounter+1 <= 7) {
                    musicCounter++
                    musicVolumeGroup.update(musicCounter)
                    musicUtil.volumeLevelFlow.value = musicCounter * ONE_PERCENT
                }
            }
        }
    }

    private fun addNotifications() {
        val on  = ACheckBox(screen, ACheckBox.Static.Type.CHECK_BOX)
        val off = ACheckBox(screen, ACheckBox.Static.Type.CHECK_BOX)
        val cbg = ACheckBoxGroup()

        addActors(on, off)

        on.apply {
            checkBoxGroup = cbg
            setBounds(343f, 226f, 73f, 73f)

            if (isNotifications) check(false)

            setOnCheckListener { if (it) isNotifications = true }
        }

        off.apply {
            checkBoxGroup = cbg
            setBounds(684f, 226f, 73f, 73f)

            if (isNotifications.not()) check(false)

            setOnCheckListener { if (it) isNotifications = false }
        }

    }

}