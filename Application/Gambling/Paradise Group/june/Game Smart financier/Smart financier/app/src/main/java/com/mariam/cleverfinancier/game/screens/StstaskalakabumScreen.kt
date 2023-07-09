package com.mariam.cleverfinancier.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.mariam.cleverfinancier.game.actors.scroll.VerticalGroup
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen

class StstaskalakabumScreen: AdvancedScreen() {

    // MainGroup
    private val statkaImage = Image(SpriteManager.GameRegion.STATKAMA.region)
    private val panImage    = Image(SpriteManager.ListRegion.STSTSTST.regionList.shuffled().first())
    private val verticGroup1 = VerticalGroup(21f)
    private val scrollPanes1 = ScrollPane(verticGroup1)

    override fun AdvancedGroup.addActorsOnGroup() {
        addPan()
        addStatka()
        addBack()
        addItems()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addStatka() {
        addActor(statkaImage)
        statkaImage.setBounds(21f, 1135f, 609f, 152f)
    }

    private fun AdvancedGroup.addPan() {
        addActor(panImage)
        panImage.setBounds(-52f, 612f, 735f, 548f)
    }

    private fun AdvancedGroup.addBack() {
        val back = Actor()
        addActor(back)
        back.setBounds(0f, 1211f, 79f, 91f)
        back.setOnClickListener { NavigationManager.back() }
    }
    private fun AdvancedGroup.addItems() {
        addActors(scrollPanes1)
        scrollPanes1.setBounds(21f, 0f, 588f, 643f)

        SpriteManager.ListRegion.S.regionList.shuffled().onEach {
            verticGroup1.addActor(Image(it).apply { setSize(588f, 112f) })
        }
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