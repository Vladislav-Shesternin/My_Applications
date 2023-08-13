package com.foot.ball.quiz.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.foot.ball.quiz.game.manager.FontTTFManager

object LabelStyle {

    val aleoBlack_70 get() = Label.LabelStyle(FontTTFManager.Aleo.font_70.font, Color.BLACK)
    val aleoWhite_50 get() = Label.LabelStyle(FontTTFManager.Aleo.font_50.font, Color.WHITE)

}