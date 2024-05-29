package com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.AProgress
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.GameColor
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.font.FontParameter
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.runGDX
import kotlinx.coroutines.launch

class APanelSettings(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "%").setSize(70)
    private val font      = screen.fontGenerator_MiltonianTattoo.generateFont(parameter)

    val panelImg      = Image(screen.game.assetsAll.music_adn_sound)
    val backBtn       = AButton(screen, AButton.Static.Type.Back)
    val musicProgress = AProgress(screen)
    val soundProgress = AProgress(screen)
    val musicLbl      = Label("", Label.LabelStyle(font, GameColor.golden))
    val soundLbl      = Label("", Label.LabelStyle(font, GameColor.golden))

    override fun addActorsOnGroup() {
        addMusicSound()
        addBack()
        addProgress()
        addPercentage()
    }

    // Actors ------------------------------------------------------------------------
    private fun addMusicSound() {
        addActor(panelImg)
        panelImg.setBounds(202f, 315f, 1202f, 486f)
    }

    private fun addBack() {
        addActor(backBtn)
        backBtn.setBounds(1392f, 11f, 166f, 166f)
    }

    private fun addProgress() {
        addActors(musicProgress, soundProgress)
        // Music
        musicProgress.apply {
            setBounds(210f, 619f, 1186f, 74f)
            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        musicLbl.setText("${it.toInt()}%")
                        screen.game.musicUtil.volumeLevelFlow.value = it
                    }
                }
            }
        }
        // Sound
        soundProgress.apply {
            setBounds(210f, 323f, 1186f, 74f)
            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        soundLbl.setText("${it.toInt()}%")
                        screen.game.soundUtil.volumeLevel = it
                    }
                }
            }
        }
    }

    private fun addPercentage() {
        addActors(musicLbl, soundLbl)
        musicLbl.setBounds(836f, 718f, 185f, 83f)
        soundLbl.setBounds(836f, 422f, 185f, 83f)
    }

}