package rainbowriches.lucky.start.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.actors.progress.AMusicSound
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen

class APanelMusicSound(override val screen: AdvancedScreen): AdvancedGroup() {

    private val panelImg      = APanelka(screen, APanelka.Static.Type.SETTINGS)
    private val musicSoundImg = Image(screen.game.gameAssets.SOUND_MUSIC)
    private val sound         = AMusicSound(screen)
    private val music         = AMusicSound(screen)

    override fun addActorsOnGroup() {
        addAndFillActor(panelImg)
        addMusicSound()
        addSound()
        addMusic()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addMusicSound() {
        addActor(musicSoundImg)
        musicSoundImg.apply {
            setBounds(127f, 383f, 337f, 466f)
        }
    }

    private fun addSound() {
        addActor(sound)
        sound.apply {
            setBounds(132f, 593f, 565f, 110f)
            setProgressPercent(screen.game.soundUtil.volumeLevel * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.soundUtil.volumeLevel = it / 100f
                }
            }
        }
    }

    private fun addMusic() {
        addActor(music)
        music.apply {
            setBounds(132f, 256f, 565f, 110f)
            setProgressPercent(screen.game.musicUtil.volumeLevelFlow.value * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.musicUtil.volumeLevelFlow.value = it / 100f
                }
            }
        }
    }

}