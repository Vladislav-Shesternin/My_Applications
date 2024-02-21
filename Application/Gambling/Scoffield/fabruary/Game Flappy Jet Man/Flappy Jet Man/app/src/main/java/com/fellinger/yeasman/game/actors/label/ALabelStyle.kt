package com.fellinger.yeasman.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.fellinger.yeasman.game.manager.FontTTFManager

object ALabelStyle {

    val red_100   get() = Label.LabelStyle(FontTTFManager.KottaOneFont.font_100.font, Color.RED)
    val black_150 get() = Label.LabelStyle(FontTTFManager.KottaOneFont.font_150.font, Color.BLACK)

}