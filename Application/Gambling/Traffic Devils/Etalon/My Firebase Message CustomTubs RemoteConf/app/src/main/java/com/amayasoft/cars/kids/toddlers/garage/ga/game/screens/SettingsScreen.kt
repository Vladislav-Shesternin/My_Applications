package com.amayasoft.cars.kids.toddlers.garage.ga.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.amayasoft.cars.kids.toddlers.garage.ga.game.LibGDXGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AButton
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AProgress
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.TIME_ANIM
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animHide
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animShow
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedStage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.font.FontParameter
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.region
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.runGDX
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Music:Sound%").setSize(43)
    private val font          = fntBold.generateFont(fontParameter)

    // Actor
    private val musicLbl = Label("", Label.LabelStyle(font, Color.WHITE))
    private val soundLbl = Label("", Label.LabelStyle(font, Color.WHITE))

    private val settings = Image(game.G1.sdr)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.S3.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(117f, 225f, 529f, 148f) })
        addPanel()

        addActor(back)
        back.apply {
            setBounds(673f, 263f, 78f, 78f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActors(musicLbl, soundLbl)
        musicLbl.touchable = Touchable.disabled
        soundLbl.touchable = Touchable.disabled
        musicLbl.setBounds(135f, 230f, 261f, 52f)
        soundLbl.setBounds(135f, 316f, 261f, 52f)


        val fruits = Image(game.G1.XCBVVBN)
        addActor(fruits)
        fruits.apply {
            setBounds(-3f, -42f, 769f, 230f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(0f, 39f, TIME_ANIM, Interpolation.sine),
                Actions.moveBy(0f, -39f, TIME_ANIM, Interpolation.sine),
            )))
        }
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@SettingsScreen)
        val progressSound = AProgress(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(117f, 225f, 529f, 62f)

            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.musicUtil.volumeLevelFlow.value = it
                        musicLbl.setText("Music: ${it.toInt()}%")
                    }
                }
            }
        }
        progressSound.apply {
            setBounds(117f, 311f, 529f, 62f)

            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.soundUtil.volumeLevel = it
                        soundLbl.setText("Sound: ${it.toInt()}%")
                    }
                }
            }
        }
    }

}