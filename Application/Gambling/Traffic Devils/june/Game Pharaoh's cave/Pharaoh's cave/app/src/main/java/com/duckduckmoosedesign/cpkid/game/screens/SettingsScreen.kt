package com.duckduckmoosedesign.cpkid.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.duckduckmoosedesign.cpkid.game.LibGDXGame
import com.duckduckmoosedesign.cpkid.game.actors.AButton
import com.duckduckmoosedesign.cpkid.game.actors.AProgress
import com.duckduckmoosedesign.cpkid.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpkid.game.utils.actor.animHide
import com.duckduckmoosedesign.cpkid.game.utils.actor.animShow
import com.duckduckmoosedesign.cpkid.game.utils.actor.setBounds
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpkid.game.utils.font.FontParameter
import com.duckduckmoosedesign.cpkid.game.utils.region
import com.duckduckmoosedesign.cpkid.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parmezan = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Music:Sound%").setSize(62)
    private val font     = fontGenerator_Kalam.generateFont(parmezan)

    private val aMusicLbl = Label("Music: 0%", Label.LabelStyle(font, Color.WHITE))
    private val aSoundLbl = Label("Sound: 0%", Label.LabelStyle(font, Color.WHITE))

    private val settings = Image(game.allAss.soundersia)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loadAss.BEDROOM.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(733f, 248f, 708f, 495f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(1309f, 21f, 202f, 96f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActors(aMusicLbl, aSoundLbl)
        aMusicLbl.apply {
            setBounds(924f, 682f, 326f, 99f)
            setAlignment(Align.center)
        }
        aSoundLbl.apply {
            setBounds(924f, 386f, 326f, 99f)
            setAlignment(Align.center)
        }

        addPHANAMERA()
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@SettingsScreen)
        val progressSound = AProgress(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(733f, 544f, 708f, 199f)

            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.musicUtil.volumeLevelFlow.value = it
                        aMusicLbl.setText("Music: " + it.toInt().toString()+"%")
                    }
                }
            }
        }
        progressSound.apply {
            setBounds(733f, 248f, 708f, 199f)

            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.soundUtil.volumeLevel = it
                        aSoundLbl.setText("Sound: " + it.toInt().toString()+"%")
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addPHANAMERA() {
        val img = Image(game.allAss.paramon)
        addActor(img)
        img.apply {
            setBounds(63f, 0f, 557f, 693f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveTo(0f, 0f, 1f),
                Actions.moveTo(199f, 0f, 2f),
                Actions.moveTo(63f, 0f, 0.5f),
            )))
        }
    }

}