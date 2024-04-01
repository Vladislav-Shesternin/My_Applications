package sinet.startup.indrive.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import sinet.startup.indrive.game.manager.FontTTFManager
import sinet.startup.indrive.game.util.GameColor

object LabelStyle {

    val akronimGold_60 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_60.font, Color.WHITE)
    val akronimGold_22 get() = Label.LabelStyle(FontTTFManager.AkronimFont.font_22.font, Color.WHITE)

}