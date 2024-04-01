package com.riseofgiza.velsolution.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.riseofgiza.velsolution.game.actors.button.FontTTFManager
import com.riseofgiza.velsolution.game.util.GameColor

object LabelStyle {

    val akronimGold_20 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_80.font, GameColor.gold)
    val akronimGold_10 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_40.font, GameColor.gold)

}