package com.maxgames.stickwarl.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.maxgames.stickwarl.game.LibGDXGame
import com.maxgames.stickwarl.game.utils.TIME_ANIM
import com.maxgames.stickwarl.game.utils.actor.animHide
import com.maxgames.stickwarl.game.utils.actor.animShow
import com.maxgames.stickwarl.game.utils.actor.setOnClickListener
import com.maxgames.stickwarl.game.utils.advanced.AdvancedScreen
import com.maxgames.stickwarl.game.utils.advanced.AdvancedStage
import com.maxgames.stickwarl.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.assets.BURBAS)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.splash.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(161f, 445f, 741f, 933f) })

        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(161f, 1195f, 741f, 183f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(161f, 945f, 741f, 183f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(161f, 695f, 741f, 183f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(161f, 445f, 741f, 183f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}