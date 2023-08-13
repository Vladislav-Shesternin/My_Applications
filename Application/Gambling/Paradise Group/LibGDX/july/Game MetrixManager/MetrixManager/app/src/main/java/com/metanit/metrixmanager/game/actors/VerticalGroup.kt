package com.metanit.metrixmanager.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.metanit.metrixmanager.game.utils.advanced.AdvancedGroup

class VerticalGroup(
    val gap     : Float = 0f,
    val startGap: Float = gap,
    val endGap  : Float = gap,
    val direction: Direction = Direction.DOWN,
) : AdvancedGroup() {

    override fun getPrefWidth(): Float {
        return width
    }

    override fun getPrefHeight(): Float {
        var newHeight = 0f
        children.onEach { newHeight += it.height + gap }

        newHeight -= gap
        newHeight += startGap + endGap
        return newHeight
    }


    private var lastIndex = 0

    override fun addActor(actor: Actor) {
        super.addActor(actor)
        invalidateHierarchy()

        lastIndex = children.size-1

        if (direction == Direction.DOWN) {
            children.onEachIndexed { index, a ->
                if (index == lastIndex) a.y = startGap
                else a.y += actor.height + gap
            }
        } else {
            if (children.size > 1) actor.y = children[lastIndex - 1].y + children[lastIndex - 1].height + gap
            else actor.y = startGap
        }
    }



    enum class Direction {
        DOWN, UP
    }

}