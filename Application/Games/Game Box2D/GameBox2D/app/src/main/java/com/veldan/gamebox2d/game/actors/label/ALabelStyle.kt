package com.veldan.gamebox2d.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.gamebox2d.game.manager.FontTTFManager

object ALabelStyle {

    fun style(type: Type, color: Color = Color.WHITE) = Label.LabelStyle(type.font, color)

    interface Type {
        val font: BitmapFont
    }

    enum class Roboto(override val font: BitmapFont) : Type {
        _40(FontTTFManager.RobotoRegularFont.font_75.font),
    }
}