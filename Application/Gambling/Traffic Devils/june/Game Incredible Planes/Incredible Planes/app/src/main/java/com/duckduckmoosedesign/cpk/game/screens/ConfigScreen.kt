package com.duckduckmoosedesign.cpk.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.actors.AButton
import com.duckduckmoosedesign.cpk.game.actors.AProgress
import com.duckduckmoosedesign.cpk.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.animShow
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.font.FontParameter
import com.duckduckmoosedesign.cpk.game.utils.region
import com.duckduckmoosedesign.cpk.game.utils.runGDX
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.launch

class ConfigScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"%").setSize(71)
    private val font          = fntScript.generateFont(fontParameter)

    // Actor
    private val musicLbl = Label("", Label.LabelStyle(font, Color.WHITE))
    private val soundLbl = Label("", Label.LabelStyle(font, Color.WHITE))

    private val settings = Image(game.Ser.SMS)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Mis.Firster.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(150f, 141f, 981f, 663f) })
        addPanel()

        addActor(back)
        back.apply {
            setBounds(1188f, 43f, 185f, 141f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActors(musicLbl, soundLbl)
        musicLbl.touchable = Touchable.disabled
        soundLbl.touchable = Touchable.disabled
        musicLbl.setBounds(398f, 446f, 169f, 104f)
        soundLbl.setBounds(714f, 224f, 169f, 104f)
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@ConfigScreen)
        val progressSound = AProgress(this@ConfigScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(197f, 418f, 570f, 160f)

            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.musicUtil.volumeLevelFlow.value = it
                        musicLbl.setText("${it.toInt()}%")
                    }
                }
            }
        }
        progressSound.apply {
            setBounds(513f, 196f, 570f, 160f)

            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.soundUtil.volumeLevel = it
                        soundLbl.setText("${it.toInt()}%")
                    }
                }
            }
        }
    }

}