package com.ukracc.finproject.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.ukracc.finproject.game.actors.scroll.VerticalGroup
import com.ukracc.finproject.game.manager.NavigationManager
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.soundUtil
import com.ukracc.finproject.game.utils.actor.setBounds
import com.ukracc.finproject.game.utils.advanced.AdvancedGroup
import com.ukracc.finproject.game.utils.advanced.AdvancedScreen
import com.ukracc.finproject.game.utils.Layout.List as LL
class ListScreen: AdvancedScreen() {

    private val topSeparatorImage = Image(SpriteManager.GameRegion.SEPARATOR.region)
    private val botSeparatorImage = Image(SpriteManager.GameRegion.SEPARATOR.region)
    private val verticalGroup     = VerticalGroup(59f)
    private val scroll            = ScrollPane(verticalGroup)

    private val items = List(16) { Item(SpriteManager.ListRegion.ITEMS.regionList[it], it) }.shuffled()

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addSeparators()
        addScroll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addSeparators() {
        addActors(topSeparatorImage, botSeparatorImage)
        topSeparatorImage.apply {
            setBounds(LL.topSepar)
        }
        botSeparatorImage.apply {
            setBounds(LL.botSepar)
        }
    }

    private fun AdvancedGroup.addScroll() {
        addActor(scroll)
        scroll.apply {
            setBounds(LL.scroll)
        }
        verticalGroup.addItems()
    }

    private fun AdvancedGroup.addItems() {
        items.onEach { item ->
            addActor(Image(item.region).apply {
                setSize(660f, 320f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        soundUtil.apply { play(CLICK) }
                        NavigationManager.navigate(DetailScreen(), ListScreen(), key = item.index)
                    }
                })
            })
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    data class Item(
        val region: TextureRegion,
        val index: Int,
    )

}