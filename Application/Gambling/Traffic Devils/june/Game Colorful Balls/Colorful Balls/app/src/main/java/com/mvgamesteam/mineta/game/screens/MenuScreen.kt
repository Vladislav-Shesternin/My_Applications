package com.mvgamesteam.mineta.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.actors.AButton
import com.mvgamesteam.mineta.game.utils.TIME_ANIM
import com.mvgamesteam.mineta.game.utils.actor.animHide
import com.mvgamesteam.mineta.game.utils.actor.animShow
import com.mvgamesteam.mineta.game.utils.actor.setOnClickListener
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedStage
import com.mvgamesteam.mineta.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.Jer.PSR)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Sap.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(490f, 144f, 541f, 612f) })
        val play     = Actor()
        val settings = Actor()
        val rls      = Actor()
        val exit     = AButton(this@MenuScreen, AButton.Static.Type.Exit)

        addActors(play, rls, settings, exit)

        play.apply {
            setBounds(490f, 576f, 541f, 180f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(490f, 360f, 541f, 180f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rls.apply {
            setBounds(490f, 144f, 541f, 180f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(1275f, 19f, 190f, 125f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}