package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.utils.TIME_ANIM
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.actor.setOnClickListener
import com.centurygames.idlecouri.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.font.FontParameter
import com.centurygames.idlecouri.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var counter_1 = 0
        var counter_2 = 0
        var counter_3 = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(30)
    private val font          = fontGenerator_JotiOne.generateFont(fontParameter)

    // Actor
    private val menu = Image(game.alpha.aaa)

    private val lbl1 = Label(counter_1.toString(), Label.LabelStyle(font, Color.valueOf("F8F808")))
    private val lbl2 = Label(counter_2.toString(), Label.LabelStyle(font, Color.valueOf("373ED2")))
    private val lbl3 = Label(counter_3.toString(), Label.LabelStyle(font, Color.valueOf("680818")))

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(38f, 100f, 552f, 939f) })

        val play     = Actor()
        val settings = Actor()
        val rules    = Actor()
        val exit     = Actor()

        addActors(play, settings, rules, exit)

        play.apply {
            setBounds(83f, 627f, 463f, 138f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(PerekakalaScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(83f, 449f, 463f, 138f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(83f, 271f, 463f, 138f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(157f, 100f, 315f, 92f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActors(lbl1, lbl2, lbl3)
        lbl1.apply {
            setBounds(93f, 850f, 53f, 40f)
            setAlignment(Align.center)
        }
        lbl2.apply {
            setBounds(288f, 850f, 53f, 40f)
            setAlignment(Align.center)
        }
        lbl3.apply {
            setBounds(483f, 850f, 53f, 40f)
            setAlignment(Align.center)
        }
    }

}