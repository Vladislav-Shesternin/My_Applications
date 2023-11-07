package uniwersal.pictures.present.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.Actor
import uniwersal.pictures.present.game.utils.advanced.AdvancedGroup
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen

class VerticalGroup(
    override val screen: AdvancedScreen,
    val gap      : Float = 0f,
    val startGap : Float = gap,
    val endGap   : Float = gap,
    val alignment: Alignment = Alignment.BOTTOM,
    val direction: Direction = Direction.UP,
) : AdvancedGroup() {

    private var ny = 0f
    private var newHeight = 0f

    override fun getPrefWidth(): Float {
        return width
    }

    override fun getPrefHeight(): Float {
        newHeight = 0f
        children.onEach { newHeight += it.height + gap }

        newHeight -= gap
        newHeight += startGap + endGap

        if (alignment == Alignment.TOP && parent.height > newHeight) newHeight = parent.height
        return newHeight
    }

    override fun addActorsOnGroup() {}

    override fun addActor(actor: Actor) {
        super.addActor(actor)

        when (alignment) {
            Alignment.TOP    -> {
                ny = prefHeight

                when (direction) {
                    Direction.DOWN -> children.onEachIndexed { index, a -> a.moveFromTOP(index) }
                    Direction.UP   -> children.reversed().onEachIndexed { index, a -> a.moveFromTOP(index) }
                }
            }
            Alignment.BOTTOM -> {
                ny = 0f

                when (direction) {
                    Direction.DOWN -> children.reversed().onEachIndexed { index, a -> a.moveFromBOTTOM(index) }
                    Direction.UP   -> children.onEachIndexed { index, a -> a.moveFromBOTTOM(index) }
                }
            }
        }

    }

    private fun Int.gap() = (if (this==0) startGap else gap)

    private fun Actor.moveFromTOP(index: Int) {
        ny = ny - index.gap() - height
        y  = ny
    }

    private fun Actor.moveFromBOTTOM(index: Int) {
        ny += index.gap()
        y  = ny
        ny += height
    }


    enum class Direction {
        UP, DOWN
    }

    enum class Alignment {
        TOP, BOTTOM
    }

}