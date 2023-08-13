package com.mariam.cleverfinancier.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.mariam.cleverfinancier.game.manager.FontTTFManager
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.GameColor
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen

class GraphiikScreen: AdvancedScreen() {

    // MainGroup
    private val balanecLabel   = Label("$${number(1, 99, 1)},${number(100, 999, 1)}", Label.LabelStyle(
        FontTTFManager.Regular.font_54.font, GameColor.blacka))


    override fun show() {
        setUIBackground(SpriteManager.GameRegion.GRAPHIK.region)
        super.show()
    }
    override fun AdvancedGroup.addActorsOnGroup() {
        addBalanece()
        addMenu()
        addBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addBalanece() {
        addActor(balanecLabel)
        balanecLabel.setBounds(165f, 806f, 308f, 66f)
        balanecLabel.setAlignment(Align.center)
    }


    private fun AdvancedGroup.addMenu() {

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()

        addActors(c1, c2, c3, c4)
        c1.apply {
            setBounds(45f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(GlavniyScreen(), GraphiikScreen()) }
        }
        c2.apply {
            setBounds(201f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(StistikaScreen(), GraphiikScreen()) }
        }
        c3.apply {
            setBounds(362f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(OperciiScreen(), GraphiikScreen()) }
        }
        c4.apply {
            setBounds(523f, 21f, 62f, 62f)
            setOnClickListener {  }
        }
    }

    private fun AdvancedGroup.addBack() {
        val back = Actor()
        addActor(back)
        back.setBounds(0f, 1211f, 79f, 91f)
        back.setOnClickListener { NavigationManager.back() }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}