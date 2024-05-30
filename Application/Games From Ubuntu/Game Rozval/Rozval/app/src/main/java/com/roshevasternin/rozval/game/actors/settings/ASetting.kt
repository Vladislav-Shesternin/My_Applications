package com.roshevasternin.rozval.game.actors.settings

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.roshevasternin.rozval.game.actors.button.AButton
import com.roshevasternin.rozval.game.actors.progress.AProgress
import com.roshevasternin.rozval.game.utils.GameColor
import com.roshevasternin.rozval.game.utils.advanced.AdvancedGroup
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen
import com.roshevasternin.rozval.game.utils.font.FontParameter
import com.roshevasternin.rozval.game.utils.runGDX
import kotlinx.coroutines.launch

class ASetting(override val screen: AdvancedScreen, val type: Static.Type): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "%")
    private val font30    = screen.fontGenerator_LondrinaSolid_Regular.generateFont(parameter.setSize(30))

    private val progress    = AProgress(screen)
    private val applyBtn    = AButton(screen, AButton.Static.Type.Apply)
    private val progressLbl = Label("", Label.LabelStyle(font30, GameColor.white))

    // Field
    var applyBlock = {}

    override fun addActorsOnGroup() {
        addProgress()
        addApplyBtn()
        addProgressLbl()

        collectProgress()
    }

    // Actors ------------------------------------------------------------------------

    private fun addProgress() {
        addActor(progress)
        progress.apply {
            setBounds(41f, 298f, 76f, 736f)
            progressPercentFlow.value = when(type) {
                Static.Type.Music -> screen.game.musicUtil.volumeLevelFlow.value
                Static.Type.Sound -> screen.game.soundUtil.volumeLevel
            }
        }
    }

    private fun addApplyBtn() {
        addActor(applyBtn)
        applyBtn.apply {
            setBounds(18f, 35f, 124f, 51f)
            setOnClickListener { applyBlock() }
        }
    }

    private fun addProgressLbl() {
        addActor(progressLbl)
        progressLbl.apply {
            setBounds(5f, 116f, 150f, 35f)
            setAlignment(Align.center)
        }
    }

    // Logic ------------------------------------------------------------------------

    private fun collectProgress() {
        coroutine?.launch {
            progress.progressPercentFlow.collect {
                runGDX {
                    progressLbl.setText("${it.toInt()}%")

                    when(type) {
                        Static.Type.Music -> screen.game.musicUtil.volumeLevelFlow.value = it
                        Static.Type.Sound -> screen.game.soundUtil.volumeLevel = it
                    }
                }
            }
        }
    }

    object Static {
        enum class Type {
            Music, Sound
        }
    }

}