package com.mariam.cleverfinancier.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.mariam.cleverfinancier.game.actors.scroll.VerticalGroup
import com.mariam.cleverfinancier.game.manager.FontTTFManager
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.GameColor
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen

class OperciiScreen: AdvancedScreen() {

    // MainGroup
    private val allOperImage   = Image(SpriteManager.GameRegion.OLL_OPERATSIA.region)
    private val menuheImage    = Image(SpriteManager.GameRegion.M3.region)
    private val allDohodImage  = Image(SpriteManager.GameRegion.VSE_MOI_DOHODI.region)
    private val balanecLabel   = Label("$${number(10, 99, 1)},${number(100, 999, 1)}.${number(0, 9, 2)}", Label.LabelStyle(
        FontTTFManager.Regular.font_33.font, GameColor.blacka))

    private val verticGroup1 = VerticalGroup(21f)
    private val scrollPanes1 = ScrollPane(verticGroup1)
    private val verticGroup2 = VerticalGroup(21f)
    private val scrollPanes2 = ScrollPane(verticGroup2)


    override fun AdvancedGroup.addActorsOnGroup() {
        addOllOperatsa()
        addOllDohod()
        addBalanece()
        addItems12()
        addMenu()
        addBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addOllOperatsa() {
        addActor(allOperImage)
        allOperImage.setBounds(21f, 1037f, 588f, 249f)
    }

    private fun AdvancedGroup.addOllDohod() {
        addActor(allDohodImage)
        allDohodImage.setBounds(21f, 551f, 588f, 29f)
    }

    private fun AdvancedGroup.addBalanece() {
        addActor(balanecLabel)
        balanecLabel.setBounds(21f, 1150f, 359f, 40f)
    }

    private fun AdvancedGroup.addItems12() {
        addActors(scrollPanes1, scrollPanes2)
        scrollPanes1.setBounds(21f, 626f, 588f, 381f)
        scrollPanes2.setBounds(21f, 140f, 588f, 381f)

        SpriteManager.ListRegion.S.regionList.shuffled().onEach {
            verticGroup1.addActor(Image(it).apply { setSize(588f, 112f) })
        }
        SpriteManager.ListRegion.S.regionList.shuffled().onEach {
            verticGroup2.addActor(Image(it).apply { setSize(588f, 112f) })
        }
    }
    private fun AdvancedGroup.addMenu() {
        addActor(menuheImage)
        menuheImage.setBounds(0f, 0f, 631f, 176f)

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()

        addActors(c1, c2, c3, c4)
        c1.apply {
            setBounds(45f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(GlavniyScreen(), OperciiScreen()) }
        }
        c2.apply {
            setBounds(201f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(StistikaScreen(), OperciiScreen()) }
        }
        c3.apply {
            setBounds(362f, 21f, 62f, 62f)
            setOnClickListener {  }
        }
        c4.apply {
            setBounds(523f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(GraphiikScreen(), OperciiScreen()) }
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