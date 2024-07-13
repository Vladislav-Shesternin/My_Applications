package com.kongregate.mobile.burrit.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.kongregate.mobile.burrit.game.LibGDXGame
import com.kongregate.mobile.burrit.game.utils.TIME_ANIM
import com.kongregate.mobile.burrit.game.utils.actor.animHide
import com.kongregate.mobile.burrit.game.utils.actor.animShow
import com.kongregate.mobile.burrit.game.utils.actor.setOnClickListener
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedScreen
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedStage
import com.kongregate.mobile.burrit.game.utils.font.FontParameter
import com.kongregate.mobile.burrit.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var counter_1 = 0
        var counter_2 = 0
        var counter_3 = 0
        var counter_FULL = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(70)
    private val font          = fontGenerator_Hubballi.generateFont(fontParameter)

    // Actor
    private val menu = Image(game.alpha.plsttstarext)
    private val tri  = Image(game.alpha.ott)
    private val pf   = Image(game.alpha.pflfne)

    private val lbl1    = Label(counter_1.toString(), Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val lbl2    = Label(counter_2.toString(), Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val lbl3    = Label(counter_3.toString(), Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val lblFULL = Label(counter_FULL.toString(), Label.LabelStyle(font, Color.valueOf("F6ED36")))

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.bacMini.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(222f, 96f, 637f, 1039f) })

        val play     = Actor()
        val settings = Actor()
        val rules    = Actor()
        val exit     = Actor()

        addActors(play, settings, rules, exit)

        play.apply {
            setBounds(349f, 735f, 381f, 400f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(222f, 378f, 266f, 280f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(592f, 378f, 267f, 280f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(348f, 96f, 384f, 155f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActors(tri, pf)

        tri.setBounds(17f, 1451f, 1046f, 469f)
        pf.setBounds(353f, 1235f, 374f, 151f)

        addActors(lbl1, lbl2, lbl3, lblFULL)
        lbl1.apply {
            setBounds(83f, 1488f, 232f, 67f)
            setAlignment(Align.center)
        }
        lbl2.apply {
            setBounds(424f, 1488f, 232f, 67f)
            setAlignment(Align.center)
        }
        lbl3.apply {
            setBounds(765f, 1488f, 232f, 67f)
            setAlignment(Align.center)
        }
        lblFULL.apply {
            setBounds(425f, 1276f, 228f, 67f)
            setAlignment(Align.center)
        }


    }

}