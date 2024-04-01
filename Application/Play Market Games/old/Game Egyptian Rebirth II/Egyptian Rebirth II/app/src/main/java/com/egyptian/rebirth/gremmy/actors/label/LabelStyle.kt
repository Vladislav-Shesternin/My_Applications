package com.egyptian.rebirth.gremmy.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.egyptian.rebirth.gremmy.manager.FontTTFManager

object LabelStyle {

    val akronimWhite_65 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_65.font, Color.WHITE)
    val akronimWhite_50 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_50.font, Color.WHITE)

}