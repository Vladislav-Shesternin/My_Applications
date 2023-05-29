package com.vbbb.time.game.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.Actor
import com.vbbb.time.game.game.utils.advanced.AdvancedGroup
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class HorizontalGroup(
    val gap     : Float = 0f,
    val startGap: Float = gap,
    val endGap  : Float = gap,
    val direction: Direction = Direction.RIGHT,
) : AdvancedGroup() {

    override fun getPrefWidth(): Float {
        var newWidth = 0f
        children.onEach { newWidth += it.width + gap }

        newWidth -= gap
        newWidth += startGap + endGap
        return newWidth
    }

    override fun getPrefHeight(): Float {
        return height
    }


    private var lastIndex = 0

    override fun addActor(actor: Actor) {
        super.addActor(actor)
        invalidateHierarchy()

        lastIndex = children.size-1

        if (direction == Direction.LEFT) {
            children.reversed().onEachIndexed { index, a ->
                if (index == 0) a.x = startGap
                else a.x += actor.width + gap
            }
        } else {
            if (children.size > 1) actor.x = children[lastIndex - 1].x + children[lastIndex - 1].width + gap
            else actor.x = startGap
        }
    }



    enum class Direction {
        RIGHT, LEFT
    }

}