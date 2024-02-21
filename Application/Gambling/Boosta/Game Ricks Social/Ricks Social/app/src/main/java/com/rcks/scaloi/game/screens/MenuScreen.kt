package com.rcks.scaloi.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rcks.scaloi.game.manager.NavigationManager
import com.rcks.scaloi.game.manager.SpriteManager
import com.rcks.scaloi.game.util.advanced.AdvancedScreen
import com.rcks.scaloi.game.util.advanced.AdvancedStage
import com.rcks.scaloi.game.util.listeners.toClickable

class MenuScreen: AdvancedScreen() {

    private val snow1      = Image(SpriteManager.CommonRegion.SNOW.region)
    private val snow2      = Image(SpriteManager.CommonRegion.SNOW.region)

    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addActors(snow1, snow2)

        snow1.setBounds(0f, 0f, com.rcks.scaloi.game.util.WIDTH, com.rcks.scaloi.game.util.HEIGHT)
        snow2.setBounds(0f,
            com.rcks.scaloi.game.util.HEIGHT,
            com.rcks.scaloi.game.util.WIDTH,
            com.rcks.scaloi.game.util.HEIGHT
        )

        snow1.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))
        snow2.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))

        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addMenu() {
        val img = Image(SpriteManager.GameRegion.MENU.region)
        addActor(img)
        img.setBounds(678f, 164f, 564f, 753f)

        var ny = 758f
        repeat(4) { index ->
            val actor = Actor()
            addActor(actor)
            actor.setBounds(678f, ny, 564f, 159f)
            ny -= 39 + 159

            actor.toClickable().setOnClickListener {
                when(index) {
                    0 -> NavigationManager.navigate(GameScreen(), MenuScreen())
                    1 -> NavigationManager.navigate(SettingsScreen(), MenuScreen())
                    2 -> NavigationManager.navigate(RulesScreen(), MenuScreen())
                    3 -> NavigationManager.exit()
                }
            }
        }

    }

}