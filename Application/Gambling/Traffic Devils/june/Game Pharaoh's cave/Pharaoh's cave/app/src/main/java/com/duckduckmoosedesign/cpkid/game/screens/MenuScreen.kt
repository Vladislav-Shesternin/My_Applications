package com.duckduckmoosedesign.cpkid.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.duckduckmoosedesign.cpkid.game.LibGDXGame
import com.duckduckmoosedesign.cpkid.game.actors.AButton
import com.duckduckmoosedesign.cpkid.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpkid.game.utils.actor.animHide
import com.duckduckmoosedesign.cpkid.game.utils.actor.animShow
import com.duckduckmoosedesign.cpkid.game.utils.actor.setBounds
import com.duckduckmoosedesign.cpkid.game.utils.actor.setOnClickListener
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpkid.game.utils.font.FontParameter
import com.duckduckmoosedesign.cpkid.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var recorDSMAN = 0
    }

    private val parmezan = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Record:").setSize(62)
    private val font     = fontGenerator_Kalam.generateFont(parmezan)

    private val aRecordLbl = Label("Record: " + recorDSMAN.toString(), Label.LabelStyle(font, Color.WHITE))

    private val menu = Image(game.allAss.kilotonna)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loadAss.BEDROOM.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(756f, 69f, 418f, 762f) })
        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = AButton(this@MenuScreen, AButton.Static.Type.Exit)

        addActors(play, rules, settings, exit)

        settings.apply {
            setBounds(792f, 589f, 346f, 228f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        play.apply {
            setBounds(792f, 336f, 346f, 228f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(792f, 83f, 346f, 228f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(1309f, 21f, 202f, 96f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActor(Image(game.allAss.esmiraldo).apply { setBounds(60f, 742f, 564f, 89f) })
        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(139f, 745f, 407f,72f)
            setAlignment(Align.center)
        }

        addPHANAMERA()
    }

    private fun AdvancedStage.addPHANAMERA() {
        val img = Image(game.allAss.paramon)
        addActor(img)
        img.apply {
            setBounds(63f, 0f, 557f, 693f)
            addAction(Actions.forever(Actions.sequence(
                Actions.moveTo(0f, 0f, 1f),
                Actions.moveTo(199f, 0f, 2f),
                Actions.moveTo(63f, 0f, 0.5f),
            )))
        }
    }

}