package com.veldan.bigwinslots777.actors.label

import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.bigwinslots777.manager.assets.FontBMPManager
import com.veldan.bigwinslots777.manager.assets.FontTTFManager
import com.veldan.bigwinslots777.manager.assets.util.FontTTFUtil
import com.veldan.bigwinslots777.utils.Color.DARK_BLUE
import com.veldan.bigwinslots777.utils.Color.GREEN
import com.veldan.bigwinslots777.utils.Color.RED

object LabelStyle {

    val gold_200 get() = Label.LabelStyle(FontBMPManager.GoldFont.font_200.font, null)
    val gold_70  get() = Label.LabelStyle(FontBMPManager.GoldFont.font_70.font, null)

    val rockwell_60 get() = Label.LabelStyle(FontTTFManager.RockwellFont.font_60.font, DARK_BLUE)

    val font_30        get() = Label.LabelStyle(FontTTFUtil.FONT.font_30.font, WHITE)
    val font_50        get() = Label.LabelStyle(FontTTFUtil.FONT.font_50.font, WHITE)
    val font_60        get() = Label.LabelStyle(FontTTFUtil.FONT.font_60.font, WHITE)
    val font_red_100   get() = Label.LabelStyle(FontTTFUtil.FONT.font_100.font, RED)
    val font_green_100 get() = Label.LabelStyle(FontTTFUtil.FONT.font_100.font, GREEN)
}