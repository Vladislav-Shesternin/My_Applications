package com.avietor.onlaneslets.game.screens

import com.avietor.onlaneslets.game.manager.NavigationManager
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.screens.levels.*
import com.avietor.onlaneslets.game.utils.actor.setOnClickListener
import com.avietor.onlaneslets.game.utils.advanced.AdvancedGroup
import com.avietor.onlaneslets.game.utils.advanced.AdvancedScreen
import com.avietor.onlaneslets.util.log
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class LevelsScreen: AdvancedScreen() {

    private val panel = Image(SpriteManager.GameRegion.MUNI.region)
    private val v1    = Actor()
    private val v2    = Actor()
    private val v3    = Actor()
    private val v4    = Actor()
    private val v5    = Actor()
    private val menu  = Actor()

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARAKUDA.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
        addAll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(panel)
        panel.setBounds(50f, 64f, 603f, 1262f)
    }

    private fun AdvancedGroup.addAll() {
        addActors(v1, v2, v3, v4, v5, menu)
        v1.apply {
            setBounds(50f, 997f, 602f, 164f)
            setOnClickListener { NavigationManager.navigate(Level_1_Screen(), LevelsScreen()) }
        }
        v2.apply {
            setBounds(50f, 807f, 602f, 164f)
            setOnClickListener { NavigationManager.navigate(Level_2_Screen(), LevelsScreen()) }
        }
        v3.apply {
            setBounds(50f, 618f, 602f, 164f)
            setOnClickListener { NavigationManager.navigate(Level_3_Screen(), LevelsScreen()) }
        }
        v4.apply {
            setBounds(50f, 428f, 602f, 164f)
            setOnClickListener { NavigationManager.navigate(Level_4_Screen(), LevelsScreen()) }
        }
        v5.apply {
            setBounds(50f, 239f, 602f, 164f)
            setOnClickListener { NavigationManager.navigate(Level_5_Screen(), LevelsScreen()) }
        }
        menu.apply {
            setBounds(549f, 64f, 104f, 79f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}