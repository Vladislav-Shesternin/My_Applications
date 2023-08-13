package com.guess.pair.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.guess.pair.game.manager.FontTTFManager

object ALabelStyle {

    fun style(type: Type, color: Color = Color.WHITE) = Label.LabelStyle(type.font, color)

    interface Type {
        val font: BitmapFont
    }

    enum class Regular(override val font: BitmapFont) : Type {
        _60(FontTTFManager.RegularFont.font_60.font),
        _300(FontTTFManager.RegularFont.font_300.font),
    }

}