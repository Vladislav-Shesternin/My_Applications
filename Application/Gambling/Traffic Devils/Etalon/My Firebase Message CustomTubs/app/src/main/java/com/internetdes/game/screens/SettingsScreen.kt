package com.internetdes.game.screens

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.internetdes.game.LibGDXGame
import com.internetdes.game.actors.AButton
import com.internetdes.game.actors.AProgress
import com.internetdes.game.utils.GColor
import com.internetdes.game.utils.TIME_ANIM
import com.internetdes.game.utils.actor.animHide
import com.internetdes.game.utils.actor.animShow
import com.internetdes.game.utils.advanced.AdvancedScreen
import com.internetdes.game.utils.advanced.AdvancedStage
import com.internetdes.game.utils.font.FontParameter
import com.internetdes.game.utils.region
import com.internetdes.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "%").setSize(57)
    private val font          = fontGenerator_HoltwoodOneSC.generateFont(fontParameter)

    private val aMusicLbl  = Label("", Label.LabelStyle(font, GColor.ykrallow))
    private val aSoundLbl  = Label("", Label.LabelStyle(font, GColor.ykrallow))
    private val settings   = Image(game.aALL.ARSIA)
    private val back       = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLOA.BAGRATION.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(0f, 0f, 1527f, 887f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(44f, 702f, 185f, 185f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@SettingsScreen)
        val progressSound = AProgress(this@SettingsScreen)

        addActors(progressMusic, progressSound)
        addActors(aMusicLbl, aSoundLbl)

        aMusicLbl.apply {
            setBounds(841f, 523f, 229f, 94f)
            setAlignment(Align.center)
            touchable = Touchable.disabled
        }
        aSoundLbl.apply {
            setBounds(841f, 170f, 229f, 94f)
            setAlignment(Align.center)
            touchable = Touchable.disabled
        }

        progressMusic.apply {
            setBounds(661f, 534f, 589f, 74f)

            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.musicUtil.volumeLevelFlow.value = it
                        aMusicLbl.setText("${it.toInt()}%")
                    }
                }
            }
        }
        progressSound.apply {
            setBounds(661f, 181f, 589f, 74f)

            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.soundUtil.volumeLevel = it
                        aSoundLbl.setText("${it.toInt()}%")
                    }
                }
            }
        }
    }

}