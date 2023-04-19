package com.jettylucketjet1wincasino.onewinslots1win.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.FontTTFManager

object ALabelStyle {

    fun style(type: Type, color: Color = Color.WHITE) = Label.LabelStyle(type.font, color)

    interface Type {
        val font: BitmapFont
    }

    enum class Bold(override val font: BitmapFont) : Type {
        _35(FontTTFManager.BoldFont.font_35.font),
        _21(FontTTFManager.BoldFont.font_21.font),
        _19(FontTTFManager.BoldFont.font_19.font),
    }

    enum class Regular(override val font: BitmapFont) : Type {
        _35(FontTTFManager.RegularFont.font_35.font),
        _19(FontTTFManager.RegularFont.font_19.font),
    }

}