package com.YovoGames.magicBo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.YovoGames.magicBo.game.LibGDXGame
import com.YovoGames.magicBo.game.utils.TIME_ANIM
import com.YovoGames.magicBo.game.utils.WIDTH_UI
import com.YovoGames.magicBo.game.utils.actor.animHide
import com.YovoGames.magicBo.game.utils.actor.animShow
import com.YovoGames.magicBo.game.utils.actor.setOnClickListener
import com.YovoGames.magicBo.game.utils.actor.setOrigin
import com.YovoGames.magicBo.game.utils.advanced.AdvancedScreen
import com.YovoGames.magicBo.game.utils.advanced.AdvancedStage
import com.YovoGames.magicBo.game.utils.region
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.jakarta.poreste)

    override fun show() {
        stageUI.root.x = -WIDTH_UI
        setBackBackground(game.surgut.Soloha.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(100f, 228f, 341f, 504f) })

        val play     = Actor()
        val settings = Actor()
        val rules    = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(100f, 627f, 341f, 105f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(100f, 494f, 341f, 105f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(100f, 361f, 341f, 105f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(100f, 228f, 341f, 105f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        val a1 = Image(game.jakarta.items[2])
        val a2 = Image(game.jakarta.items[0])
        val a3 = Image(game.jakarta.items[4])
        val a4 = Image(game.jakarta.items[3])

        addActors(a1, a2, a3, a4)
        a1.apply {
            setBounds(51f,656f,119f,119f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.sequence(
                Actions.scaleBy(-0.3f, -0.3f, 0.3f),
                Actions.scaleBy(0.3f, 0.3f, 0.3f),
            )))
        }
        a2.apply {
            setBounds(394f,505f,94f,94f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(30f, 30f, 0.3f),
                Actions.moveBy(-60f, -60f, 0.6f),
                Actions.moveBy(30f, 30f, 0.3f),
            )))
        }
        a3.apply {
            setBounds(55f,368f,90f,90f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(-360f, 4f)))
        }
        a4.apply {
            setBounds(381f,239f,83f,83f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.sequence(
                Actions.rotateBy(-30f, 1f),
                Actions.rotateBy(60f, 2f),
                Actions.rotateBy(-30f, 1f),
            )))
        }
    }

}