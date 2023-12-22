package mobile.jogodobicho.lucky.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.launch
import mobile.jogodobicho.lucky.game.actors.progress.AProgressVolume
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen

class APanelOptions(override val screen: AdvancedScreen): AdvancedGroup() {

    private val music = AProgressVolume(screen)
    private val sound = AProgressVolume(screen)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.OPTIONALE))
        addMusic()
        addSound()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addMusic() {
        addActor(music)
        music.apply {
            setBounds(354f, 364f, 382f, 49f)
            setProgressPercent(screen.game.musicUtil.volumeLevelFlow.value * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.musicUtil.volumeLevelFlow.value = it / 100f
                }
            }
        }
    }

    private fun addSound() {
        addActor(sound)
        sound.apply {
            setBounds(354f, 161f, 382f, 49f)
            setProgressPercent(screen.game.soundUtil.volumeLevel * 100f)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.soundUtil.volumeLevel = it / 100f
                }
            }
        }
    }

}