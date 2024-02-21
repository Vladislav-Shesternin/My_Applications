package com.elastic.couben.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.elastic.couben.game.manager.FontTTFManager

object ALabelStyle {

    fun style(type: Type, color: Color = Color.WHITE) = Label.LabelStyle(type.font, color)

    interface Type {
        val font: BitmapFont
    }

    enum class Roboto(override val font: BitmapFont) : Type {
        _40(FontTTFManager.RobotoFont.font_40.font),
    }
}