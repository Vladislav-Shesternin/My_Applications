package com.rcks.scaloi.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.rcks.scaloi.game.manager.FontTTFManager

object LabelStyle {

    val font_150 get() = Label.LabelStyle(FontTTFManager.AreYouSeriousFont.font_150.font, Color.WHITE)
    val font_60 get() = Label.LabelStyle(FontTTFManager.AreYouSeriousFont.font_60.font, Color.WHITE)
    val font_40 get() = Label.LabelStyle(FontTTFManager.AreYouSeriousFont.font_40.font, Color.WHITE)
}