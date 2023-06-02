package com.karpenkov.budgetgid.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.karpenkov.budgetgid.game.actors.scroll.VerticalGroup
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.screens.Currency
import com.karpenkov.budgetgid.game.screens.background
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
class ListCurrency : AdvancedGroup() {

    private val verticalGroup = VerticalGroup(52f)
    private val scroll        = ScrollPane(verticalGroup)


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(background ?: SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region))
            addActirs()
        }
    }

    private fun addActirs() {
        addActors(scroll)
        scroll.setBounds(30f, 0f, 630f, 1301f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------
    fun initialize(currencyList: List<Currency>, block: (Currency) -> Unit) {
        verticalGroup.apply {
            currencyList.onEach { curr ->
                addActor(CItem(curr).apply {
                    this.block = block
                    setSize(631f, 187f) })
            }
        }
    }

}