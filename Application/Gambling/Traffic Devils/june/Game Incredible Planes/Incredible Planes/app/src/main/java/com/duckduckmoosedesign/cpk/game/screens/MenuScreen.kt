package com.duckduckmoosedesign.cpk.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.actors.AButton
import com.duckduckmoosedesign.cpk.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.animShow
import com.duckduckmoosedesign.cpk.game.utils.actor.setOnClickListener
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menu = Image(game.Ser.MNS)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Mis.Firster.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(menu.apply { setBounds(83f, 90f, 1293f, 679f) })
        val planes     = Actor()
        val config     = Actor()
        val manual     = Actor()
        val exit       = AButton(this@MenuScreen, AButton.Static.Type.Exit)

        addActors(planes, config, manual, exit)

        planes.apply {
            setBounds(83f, 573f, 329f, 196f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(PlanesScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        config.apply {
            setBounds(1047f, 391f, 329f, 196f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(ConfigScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        manual.apply {
            setBounds(247f, 90f, 329f, 196f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(ManualScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        exit.apply {
            setBounds(1229f, 0f, 147f, 141f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
            }
        }
    }

}