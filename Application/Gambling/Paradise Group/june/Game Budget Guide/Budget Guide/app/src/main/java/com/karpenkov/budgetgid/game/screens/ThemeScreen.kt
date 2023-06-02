package com.karpenkov.budgetgid.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.karpenkov.budgetgid.game.actors.button.AButton
import com.karpenkov.budgetgid.game.actors.button.AButtonStyle
import com.karpenkov.budgetgid.game.actors.scroll.HorizontalGroup
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedScreen


var background: TextureRegion? = null

class ThemeScreen: AdvancedScreen() {

    private val horizontalGroup = HorizontalGroup(39f)
    private val scrollPane      = ScrollPane(horizontalGroup)
    private val back            = AButton(AButtonStyle.backower)

    override fun show() {
        setBackgrounds(background ?: SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 602f, 690f, 450f)

        SpriteManager.ListRegion.BACKGROUND.regionList.onEach {
            horizontalGroup.addActor(Image(it).apply {
                setSize(238f, 450f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        background = it
                        setBackgrounds(it)
                    }
                })
            })
        }


        addActor(back)
        back.apply {
            setBounds(208f, 117f, 274f, 274f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}