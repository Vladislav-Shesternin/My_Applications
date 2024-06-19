package com.hepagame.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hepagame.game.LibGDXGame
import com.hepagame.game.utils.TIME_ANIM
import com.hepagame.game.utils.actor.animHide
import com.hepagame.game.utils.actor.animShow
import com.hepagame.game.utils.actor.setOnClickListener
import com.hepagame.game.utils.advanced.AdvancedScreen
import com.hepagame.game.utils.advanced.AdvancedStage
import com.hepagame.game.utils.font.FontParameter
import com.hepagame.game.utils.region
import com.hepagame.game.utils.toBalanceFormat

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var RECORDS = 0
    }

    private val paramsFont = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Treasures:.").setSize(84)
    private val font       = fontGenerator_Kings.generateFont(paramsFont)

    private val aRecordLbl = Label("Treasures: ${RECORDS.toBalanceFormat()}", Label.LabelStyle(font, Color.WHITE))

    private val menu = Image(game.assasinAll.PKD)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.assasinLoader.PAGRAN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(530f, 229f, 466f, 628f) })
        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(559f, 676f, 409f, 126f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(559f, 527f, 409f, 126f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(559f, 378f, 409f, 126f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(559f, 229f, 409f, 126f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(426f, 37f, 675f,119f)
            setAlignment(Align.center)
        }

        addTreasures()
    }

    private fun AdvancedStage.addTreasures() {
        val left  = Image(game.assasinAll.SS)
        val right = Image(game.assasinAll.DD)

        addActors(left, right)
        left.apply {
            setBounds(57f, 75f, 320f, 309f)
            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(-20f, 0f, 1f),
                Actions.moveBy(50f, 0f, 2f),
                Actions.moveBy(-30f, 0f, 1.5f),
            )))
        }
        right.apply {
            setBounds(1149f, 52f, 347f, 353f)
            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(20f, 0f, 1f),
                Actions.moveBy(-50f, 0f, 2f),
                Actions.moveBy(30f, 0f, 1.5f),
            )))
        }
    }

}