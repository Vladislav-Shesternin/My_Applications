package com.hk.stck.nord.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class VerticalGroup(private val direction: Direction = Direction.UP) : AdvancedGroup() {

    override fun getPrefWidth(): Float {
        return width
    }

    override fun getPrefHeight(): Float {
        var maxY = 0f
        var minY = 0f

        children.onEach {
            maxY = max(maxY, it.y + it.height)
            minY = min(minY, it.y)
        }

        return maxY + minY.absoluteValue
    }


    private var upY       = 0f
    private var lastIndex = 0

    override fun addActor(actor: Actor) {
        super.addActor(actor)
        invalidateHierarchy()

        if (direction == Direction.DOWN) {
            upY       = actor.y.absoluteValue
            lastIndex = children.size-1
            children.onEachIndexed { index, a ->
                if (index != lastIndex) a.y += upY else a.y = 0f
            }
        }
    }



    enum class Direction {
        UP, DOWN
    }

}