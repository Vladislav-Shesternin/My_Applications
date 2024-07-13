package com.amayasoft.cars.kids.toddlers.garage.ga.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.amayasoft.cars.kids.toddlers.garage.ga.game.LibGDXGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AButton
import com.amayasoft.cars.kids.toddlers.garage.ga.game.screens.GameScreen.Companion.fruitses
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.TIME_ANIM
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animHide
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animShow
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.setOnClickListener
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedStage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.font.FontParameter
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.region
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.toProbelchiky
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars).setSize(43)
    private val font          = fntBold.generateFont(fontParameter)
    private val aRecordLbl    = Label(fruitses.toProbelchiky(), Label.LabelStyle(font, Color.WHITE))


    private val menu = Image(game.G1.ERTTTYU)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.S3.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(112f, 160f, 540f, 164f) })
        val play     = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, settings, exit)

        play.apply {
            setBounds(112f, 160f, 164f, 164f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(PreGameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(303f, 160f, 164f, 164f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(488f, 160f, 164f, 164f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        val pnlImg = Image(game.G1.pinrel)
        addActor(pnlImg)
        pnlImg.setBounds(204f, 355f, 355f, 78f)

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(320f, 368f, 195f, 52f)
            setAlignment(Align.center)
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