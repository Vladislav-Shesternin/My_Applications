package com.jungle.jumping.bird.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.jungle.jumping.bird.game.manager.FontTTFManager

object ALabelStyle {

    val font_45 get() = Label.LabelStyle(FontTTFManager.AladinFont.font_45.font, Color.WHITE)
    val font_100 get() = Label.LabelStyle(FontTTFManager.AladinFont.font_100.font, Color.WHITE)

}