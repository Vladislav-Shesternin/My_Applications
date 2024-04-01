package com.gen.bettermeditatio.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.gen.bettermeditatio.game.manager.FontTTFManager

object LabelStyle {

    val akronimWhite_60 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_60.font, Color.WHITE)
    val akronimWhite_40 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_40.font, Color.WHITE)

}