package com.hello.piramidka.game.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.hello.piramidka.game.manager.FontTTFManager
import com.hello.piramidka.game.screens.themeUtil
import com.hello.piramidka.game.utils.actor.setOnClickListener
import com.hello.piramidka.game.utils.advanced.AdvancedGroup

class ACurrency(val names: String, val cost: Double) : AdvancedGroup() {

    private val panel = Image(themeUtil.FRAME)
    private val text  = Label(names, Label.LabelStyle(FontTTFManager.BlackFont.font_60.font, themeUtil.color))

    var blockSelectCurrency: (String, Double) -> Unit = { a, b -> }

    override fun sizeChanged() {
        if (width > 0 && height > 0) addAcros()
    }

    private fun addAcros() {
        addAndFillActor(panel)
        addActor(text)
        text.apply {
            setAlignment(Align.center)
            setBounds(17f, 21f, 497f, 96f)
        }

        addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                blockSelectCurrency(names, cost)
            }
        })
    }

}