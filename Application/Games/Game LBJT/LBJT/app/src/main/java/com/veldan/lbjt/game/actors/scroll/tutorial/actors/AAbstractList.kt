package com.veldan.lbjt.game.actors.scroll.tutorial.actors

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.game.actors.scroll.HorizontalGroup
import com.veldan.lbjt.game.actors.scroll.VerticalGroup
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.util.log

abstract class AAbstractList(
    final override val screen : AdvancedScreen,
): AdvancedGroup() {

    abstract val strings   : List<String>
    abstract val align     : Align
    abstract val symbol    : Symbol
    abstract val symbolFont: BitmapFont

    // Actor
    protected val verticalGroup = VerticalGroup(screen, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)

    // Field
    private var number = 0

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun Symbol.getSymbol(): String = when(this) {
        Symbol.Bullet -> " • "
        Symbol.Number -> " ${++number}. "
    }

    enum class Align {
        Left, Center
    }

    enum class Symbol {
        Bullet, Number
    }

}