package com.veldan.lbjt.game.actors.scroll.tutorial.actors

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.github.tommyettinger.textra.Font
import com.github.tommyettinger.textra.TypingLabel
import com.veldan.lbjt.game.actors.scroll.HorizontalGroup
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen

class AList_TypingLabel(
    val _screen : AdvancedScreen,
    font    : Font,
    override val strings: List<String>,
    override val align  : Align  = Align.Left,
    override val symbol : Symbol = Symbol.Bullet,
    override val symbolFont: BitmapFont
): AAbstractList(_screen) {

    // Actor
    private val labels = List(strings.size) { TypingLabel(strings[it], font) }

    // Field
    private val symbolLblStyle = Label.LabelStyle(symbolFont, GameColor.textGreen)


    override fun addActorsOnGroup() {
        addAndFillActor(verticalGroup)

        var tmpHorizontalGroup: HorizontalGroup
        var tmpSymbolLbl      : Label

        labels.onEach { lbl ->
            tmpHorizontalGroup = HorizontalGroup(screen)
            tmpSymbolLbl       = Label(symbol.getSymbol(), symbolLblStyle)

            when (align) {
                Align.Left   -> {
                    tmpHorizontalGroup.width = width
                    lbl.width                = (width - tmpSymbolLbl.prefWidth)
                }
                Align.Center -> {
                    tmpHorizontalGroup.width = width * 0.7f
                    tmpHorizontalGroup.x     = width * 0.3f
                    lbl.width                = ((width * 0.7f) - tmpSymbolLbl.prefWidth)
                }
            }
            lbl.wrap                  = true
            lbl.height                = lbl.prefHeight
            tmpHorizontalGroup.height = lbl.height
            tmpSymbolLbl.apply {
                width  = prefWidth
                height = lbl.height + 10
                setAlignment(com.badlogic.gdx.utils.Align.top)
            }

            tmpHorizontalGroup.addActors(tmpSymbolLbl, lbl)
            verticalGroup.addActor(tmpHorizontalGroup)
        }

        height = verticalGroup.prefHeight
    }

}