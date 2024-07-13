package com.bitmango.go.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bitmango.go.game.LibGDXGame
import com.bitmango.go.game.utils.TIME_ANIM
import com.bitmango.go.game.utils.actor.animHide
import com.bitmango.go.game.utils.actor.animShow
import com.bitmango.go.game.utils.actor.setOnClickListener
import com.bitmango.go.game.utils.advanced.AdvancedScreen
import com.bitmango.go.game.utils.advanced.AdvancedStage
import com.bitmango.go.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {


    // Actor
    private val menu = Image(game.bbb.ewr)
    private val icon = Image(game.bbb.items.random())

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aaa.back.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(133f, 90f, 275f, 594f) })

        val play     = Actor()
        val settings = Actor()
        val rules    = Actor()
        val exit     = Actor()

        addActors(play, settings, rules, exit)

        play.apply {
            setBounds(133f, 558f, 275f, 125f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(133f, 393f, 275f, 125f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(133f, 239f, 275f, 125f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(176f, 90f, 188f, 78f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }

        addActor(icon)
        icon.setBounds(201f, 755f, 139f, 139f)

    }

}