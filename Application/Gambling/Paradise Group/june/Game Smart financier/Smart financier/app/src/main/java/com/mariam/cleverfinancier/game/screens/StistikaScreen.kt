package com.mariam.cleverfinancier.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.mariam.cleverfinancier.game.actors.progress.AProgress
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.launch

class StistikaScreen: AdvancedScreen() {

    // MainGroup
    private val statkaImage = Image(SpriteManager.GameRegion.STATAS.region)
    private val panImage    = Image(SpriteManager.ListRegion.PAN.regionList.shuffled().first())
    private val stavkaImage = Image(SpriteManager.GameRegion.PROGRESSOCHKO.region)
    private val gafImage    = Image(SpriteManager.ListRegion.GAF.regionList.shuffled().first())
    private val menuheImage = Image(SpriteManager.GameRegion.M2.region)
    private val progresso   = AProgress()

    override fun AdvancedGroup.addActorsOnGroup() {
        addStatka()
        addPan()
        addSTk()
        addGaf()
        addMenu()
        addBack()

        addActor(progresso)
        progresso.setBounds(21f, 853f, 588f, 24f)
        progresso.progressPercentFlow.value = 50f


        coroutine.launch {
            var i = 0
            progresso.progressPercentFlow.collect {
                if(i != it.toInt() / 10) {
                    i = it.toInt() / 10
                    gafImage.drawable = TextureRegionDrawable(SpriteManager.ListRegion.GAF.regionList[if (i < 10) i else 9])
                }
            }
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addStatka() {
        addActor(statkaImage)
        statkaImage.setBounds(21f, 1226f, 588f, 61f)
    }

    private fun AdvancedGroup.addPan() {
        addActor(panImage)
        panImage.setBounds(0f, 960f, 631f, 215f)
    }

    private fun AdvancedGroup.addSTk() {
        addActor(stavkaImage)
        stavkaImage.setBounds(21f, 816f, 589f, 125f)
    }

    private fun AdvancedGroup.addGaf() {
        addActor(gafImage)
        gafImage.setBounds(-52f, 132f, 735f, 644f)
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
            setOnClickListener { NavigationManager.navigate(GlavniyScreen(), StistikaScreen()) }
        }
        c2.apply {
            setBounds(201f, 21f, 62f, 62f)
            setOnClickListener {  }
        }
        c3.apply {
            setBounds(362f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(OperciiScreen(), StistikaScreen()) }
        }
        c4.apply {
            setBounds(523f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(GraphiikScreen(), StistikaScreen()) }
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