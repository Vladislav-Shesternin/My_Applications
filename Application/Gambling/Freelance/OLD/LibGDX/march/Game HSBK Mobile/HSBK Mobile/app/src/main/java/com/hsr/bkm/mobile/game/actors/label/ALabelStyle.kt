package com.hsr.bkm.mobile.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.hsr.bkm.mobile.game.manager.FontTTFManager

object ALabelStyle {

    fun style(type: Type, color: Color = Color.WHITE) = Label.LabelStyle(type.font, color)

    interface Type {
        val font: BitmapFont
    }

    object Inter {
        enum class Regular(override val font: BitmapFont) : Type {
            _63(FontTTFManager.Inter.RegularFont.font_63.font),
            _30(FontTTFManager.Inter.RegularFont.font_30.font),
        }
    }

}