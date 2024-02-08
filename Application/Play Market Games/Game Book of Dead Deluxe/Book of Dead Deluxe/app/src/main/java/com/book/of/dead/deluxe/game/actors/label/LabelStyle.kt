package com.book.of.dead.deluxe.game.actors.label

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.book.of.dead.deluxe.game.manager.FontTTFManager
import com.book.of.dead.deluxe.game.util.GameColor

object LabelStyle {

    val akronimGold_60 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_60.font, GameColor.gold)
    val akronimGold_30 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_30.font, GameColor.gold)

}