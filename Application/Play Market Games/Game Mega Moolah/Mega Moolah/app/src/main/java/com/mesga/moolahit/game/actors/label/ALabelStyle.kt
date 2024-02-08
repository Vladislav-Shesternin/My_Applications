package com.mesga.moolahit.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.mesga.moolahit.game.manager.FontTTFManager
import com.mesga.moolahit.game.util.GameColor

object ALabelStyle {

    val fontGreen_100 get() = Label.LabelStyle(FontTTFManager.Font.font_100.font, GameColor.green)
    val fontWhite_25  get() = Label.LabelStyle(FontTTFManager.Font.font_25.font, Color.WHITE)

}