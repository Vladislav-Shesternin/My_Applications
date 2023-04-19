package com.csmttt.medus.play.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.csmttt.medus.play.game.manager.FontTTFManager

object ALabelStyle {

    val fontWhite_141 get() = Label.LabelStyle(FontTTFManager.Font.font_141.font, Color.WHITE)
    val fontBlack_38  get() = Label.LabelStyle(FontTTFManager.Font.font_38.font, Color.BLACK)

}