package plinko.testyouluck.com.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import plinko.testyouluck.com.game.utils.actor.animHide
import plinko.testyouluck.com.game.utils.actor.animShow
import plinko.testyouluck.com.game.utils.actor.setOnClickListener
import plinko.testyouluck.com.game.utils.advanced.AdvancedGroup
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen

class ASettingsGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private var volumeSound = 0
        private var volumeMusic = 0
    }

    private val volumeList = listOf(0f, 20f, 40f, 50f, 60f, 80f, 100f)

    private val assets    = screen.game.gameAssets
    private val soundUtil = screen.game.soundUtil
    private val musicUtil = screen.game.musicUtil

    private val soundImgList = List(6) { Image(assets.DIVISION) }
    private val musicImgList = List(6) { Image(assets.DIVISION) }

    override fun addActorsOnGroup() {
        volumeList.onEachIndexed { index, value ->
            if ((soundUtil.volumeLevel) in (value-15f)..value) volumeSound = index.inc()
        }
        volumeList.onEachIndexed { index, value ->
            if ((musicUtil.volumeLevelFlow.value) in (value-15f)..value) volumeMusic = index.inc()
        }

        addAndFillActor(Image(assets.SETTINGS_BAR))
        addVolumeSound()
        addVolumeMusic()
        addSoundBtn()
        addMusicBtn()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addVolumeSound() {
        var nx = 225f
        soundImgList.onEachIndexed { index, image ->
            addActor(image)
            image.setBounds(nx, 549f, 51f, 63f)
            nx += 5f+51f

            if (index >= volumeSound) image.animHide()
        }
    }

    private fun addVolumeMusic() {
        var nx = 225f
        musicImgList.onEachIndexed { index, image ->
            addActor(image)
            image.setBounds(nx, 261f, 51f, 63f)
            nx += 5f+51f

            if (index >= volumeMusic) image.animHide()
        }
    }

    private fun addSoundBtn() {
        val minus = Actor()
        val plus  = Actor()

        addActors(minus, plus)

        minus.apply {
            setBounds(87f, 535f, 91f, 84f)
            setOnClickListener(soundUtil) {
                if (volumeSound-1 >= 0) {
                    volumeSound -= 1
                    if (volumeSound in 0..5) {
                        soundImgList[volumeSound].animHide(0.2f)
                        soundUtil.volumeLevel = volumeList[volumeSound]
                    }
                }
            }
        }
        plus.apply {
            setBounds(613f, 537f, 87f, 80f)
            setOnClickListener(soundUtil) {
                if (volumeSound+1 <= 6) {
                    if (volumeSound in 0..5) {
                        soundImgList[volumeSound].animShow(0.2f)
                        soundUtil.volumeLevel = volumeList[volumeSound]
                    }
                    volumeSound += 1
                }
            }
        }
    }

    private fun addMusicBtn() {
        val minus = Actor()
        val plus  = Actor()

        addActors(minus, plus)

        minus.apply {
            setBounds(87f, 248f, 91f, 84f)
            setOnClickListener(soundUtil) {
                if (volumeMusic-1 >= 0) {
                    volumeMusic -= 1
                    if (volumeMusic in 0..5) {
                        musicImgList[volumeMusic].animHide(0.2f)
                        musicUtil.volumeLevelFlow.value = volumeList[volumeMusic]
                    }
                }
            }
        }
        plus.apply {
            setBounds(613f, 250f, 87f, 80f)
            setOnClickListener(soundUtil) {
                if (volumeMusic+1 <= 6) {
                    if (volumeMusic in 0..5) {
                        musicImgList[volumeMusic].animShow(0.2f)
                        musicUtil.volumeLevelFlow.value = volumeList[volumeMusic]
                    }
                    volumeMusic += 1
                }
            }
        }
    }

}