package com.aztec.firevoll.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.aztec.firevoll.game.manager.FontTTFManager
import com.aztec.firevoll.game.util.GameColor

object LabelStyle {

    val akronimRed_50 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_50.font, GameColor.red)

}