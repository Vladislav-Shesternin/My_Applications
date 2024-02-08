package com.hellhot.competition.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.hellhot.competition.game.manager.FontTTFManager
import com.hellhot.competition.game.util.GameColor

object ALabelStyle {

    val fontRed_120 get() = Label.LabelStyle(FontTTFManager.Font.font_120.font, GameColor.red)
    val fontRed_25  get() = Label.LabelStyle(FontTTFManager.Font.font_25.font, GameColor.red)

}