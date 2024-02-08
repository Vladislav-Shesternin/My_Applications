package com.tmesfo.frtunes.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.tmesfo.frtunes.game.manager.assets.FontTTFManager
import com.tmesfo.frtunes.game.manager.assets.util.FontTTFUtil
import com.badlogic.gdx.graphics.Color as GdxColor
import com.tmesfo.frtunes.game.utils.Color as GameColor

object LabelStyle {

    val abrilFatface_white_80  get() = Label.LabelStyle(FontTTFManager.AbrilFatfaceFont.font_80.font, GdxColor.WHITE)
    val abrilFatface_white2_80 get() = Label.LabelStyle(FontTTFManager.AbrilFatfaceFont.font_80.font, GameColor.WHITE_2)
    val abrilFatface_white2_70 get() = Label.LabelStyle(FontTTFManager.AbrilFatfaceFont.font_70.font, GameColor.WHITE_2)

    val font_black2_70 get() = Label.LabelStyle(FontTTFUtil.FONT.font_70.font, GameColor.BLACK_2)
    val font_black2_80 get() = Label.LabelStyle(FontTTFUtil.FONT.font_80.font, GameColor.BLACK_2)

}