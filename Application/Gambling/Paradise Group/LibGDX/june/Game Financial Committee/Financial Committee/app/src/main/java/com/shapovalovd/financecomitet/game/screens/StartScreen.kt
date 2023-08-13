package com.shapovalovd.financecomitet.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedScreen
import com.shapovalovd.financecomitet.util.log

class StartScreen: AdvancedScreen() {

    private val testxtImage = Image(SpriteManager.GameRegion.TESTXT.region)
    private val clikker     = Image(SpriteManager.GameRegion.STRELKA.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        addSplash()
        addButtons()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addSplash() {
        val image = Image(SpriteManager.SplashRegion.SPLSH.region)
        addActor(image)
        image.setBounds(0f, 281f, 677f, 1185f)
    }

    private fun AdvancedGroup.addButtons() {
        addActors(testxtImage, clikker)
        testxtImage.setBounds(21f, 173f, 634f, 529f)
        clikker.setBounds(30f, 182f, 81f, 81f)

        val actor = Actor()

        addActor(actor)

        actor.apply {
            setBounds(21f, 172f, 634f, 99f)

            addListener(object : InputListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    touchDragged(event, x, y, pointer)
                    return true
                }

                override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                    when {
                        x < 30 -> clikker.x = 30f
                        x > 530 -> NavigationManager.navigate(HomeScreen())
                        else -> clikker.x = x - 40f
                    }
                }

            })
        }
    }

}