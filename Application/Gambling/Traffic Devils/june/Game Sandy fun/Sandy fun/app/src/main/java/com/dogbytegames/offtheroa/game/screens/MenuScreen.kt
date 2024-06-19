package com.dogbytegames.offtheroa.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dogbytegames.offtheroa.game.LibGDXGame
import com.dogbytegames.offtheroa.game.utils.TIME_ANIM
import com.dogbytegames.offtheroa.game.utils.actor.animHide
import com.dogbytegames.offtheroa.game.utils.actor.animShow
import com.dogbytegames.offtheroa.game.utils.actor.setOnClickListener
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedScreen
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedStage
import com.dogbytegames.offtheroa.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val icon = Image(game.aAlibaba.nubasina)
    private val menu = Image(game.aAlibaba.buttons)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLdnr.LDR.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(0f, 0f, 638f, 656f) })
        addActor(menu.apply { setBounds(638f, 50f, 710f, 688f) })

        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(638f, 596f, 710f, 142f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(638f, 414f, 710f, 142f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(638f, 232f, 710f, 142f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(915f, 50f, 433f, 142f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}