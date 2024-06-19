package com.internetdes.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.internetdes.game.LibGDXGame
import com.internetdes.game.utils.TIME_ANIM
import com.internetdes.game.utils.actor.animHide
import com.internetdes.game.utils.actor.animShow
import com.internetdes.game.utils.actor.setOnClickListener
import com.internetdes.game.utils.advanced.AdvancedScreen
import com.internetdes.game.utils.advanced.AdvancedStage
import com.internetdes.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.aALL.BLEF)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLOA.BAGRATION.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(37f, 0f, 1558f, 803f) })
        val play     = Actor()
        val rules    = Actor()
        val settings = Actor()
        val exit     = Actor()

        addActors(play, rules, settings, exit)

        play.apply {
            setBounds(340f, 605f, 523f, 197f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        rules.apply {
            setBounds(37f, 351f, 523f, 197f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        settings.apply {
            setBounds(643f, 351f, 523f, 197f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(340f, 97f, 523f, 197f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}