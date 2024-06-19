package com.bandagames.mpuzzle.g.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.utils.TIME_ANIM
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.actor.animShow
import com.bandagames.mpuzzle.g.game.utils.actor.setOnClickListener
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.Aoll.mesu)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Aoll.begi.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(81f, 79f, 918f, 1761f) })
        val play     = Actor()
        val stall    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, stall, settings, exit)

        play.apply {
            setBounds(81f, 1403f, 459f, 437f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        stall.apply {
            setBounds(540f, 966f, 459f, 437f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(StallScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(81f, 516f, 459f, 437f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(540f, 79f, 459f, 437f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}