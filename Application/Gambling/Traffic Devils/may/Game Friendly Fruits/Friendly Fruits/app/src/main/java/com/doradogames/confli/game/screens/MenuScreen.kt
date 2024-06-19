package com.doradogames.confli.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.doradogames.confli.game.LibGDXGame
import com.doradogames.confli.game.screens.GameScreen.Companion.RECORD
import com.doradogames.confli.game.utils.TIME_ANIM
import com.doradogames.confli.game.utils.actor.animHide
import com.doradogames.confli.game.utils.actor.animShow
import com.doradogames.confli.game.utils.actor.setOnClickListener
import com.doradogames.confli.game.utils.advanced.AdvancedScreen
import com.doradogames.confli.game.utils.advanced.AdvancedStage
import com.doradogames.confli.game.utils.font.FontParameter
import com.doradogames.confli.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parmezan = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(150)
    private val font     = fontGenerator_Kanit.generateFont(parmezan)

    private val aRecordLbl = Label(RECORD.toString(), Label.LabelStyle(font, Color.WHITE))

    private val menu = Image(game.assets.menu)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.load.Loading.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(204f, 365f, 656f, 853f) })
        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(204f, 1031f, 656f, 187f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(204f, 809f, 656f, 187f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(204f, 587f, 656f, 187f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(204f, 365f, 656f, 187f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActor(Image(game.assets.record).apply { setBounds(107f, 1385f, 850f, 396f) })
        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(107f, 1392f, 850f,224f)
            setAlignment(Align.center)
        }
    }

}