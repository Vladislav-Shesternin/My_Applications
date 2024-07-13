package com.amayasoft.cars.kids.toddlers.garage.ga.game.screens

import com.amayasoft.cars.kids.toddlers.garage.ga.game.LibGDXGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AButton
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.ACircle
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.TIME_ANIM
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animHide
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animShow
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.setOnClickListener
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedStage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.region
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image

var isBlue = true

class PreGameScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val back = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.S3.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val blue = ACircle(this@PreGameScreen, game.G1.qwe)
        val pink = ACircle(this@PreGameScreen, game.G1.fghf)

        addActors(blue, pink)

        blue.apply {
            setBounds(123f, 207f, 220f, 228f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    isBlue = true
                    game.navigationManager.navigate(GameScreen::class.java.name, PreGameScreen::class.java.name)
                }
            }
        }
        pink.apply {
            setBounds(419f, 121f, 220f, 228f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    isBlue = false
                    game.navigationManager.navigate(GameScreen::class.java.name, PreGameScreen::class.java.name)
                }
            }
        }

        addActor(back)
        back.apply {
            setBounds(673f, 339f, 78f, 78f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val fruits = Image(game.G1.XCBVVBN)
        addActor(fruits)
        fruits.apply {
            setBounds(-3f, -42f, 769f, 230f)
            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, 39f, TIME_ANIM, Interpolation.sine),
                Actions.moveBy(0f, -39f, TIME_ANIM, Interpolation.sine),
            )))
        }
    }

}