package com.veldan.pharaohslots.advanced

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Disposable
import com.veldan.pharaohslots.advanced.AdvancedGroup.AlignmentHorizontal.*
import com.veldan.pharaohslots.advanced.AdvancedGroup.AlignmentVertical.*
import com.veldan.pharaohslots.advanced.AdvancedGroup.AlignmentVertical.CENTER

open class AdvancedGroup : Group(), Disposable {

    private val chainList = mutableListOf<Actor>()



    override fun dispose() {
        children.onEach { if (it is Disposable) it.dispose() }
    }



    private fun List<Actor>.setFillParent() {
        onEach { actor ->
            when (actor) {
                is Widget      -> actor.setFillParent(true)
                is WidgetGroup -> actor.setFillParent(true)
            }
        }
    }



    fun addAlignActor(
        actor: Actor,
        alignmentHorizontal: AlignmentHorizontal = START,
        alignmentVertical: AlignmentVertical = BOTTOM,
    ) {

        // START to BOTTOM (DEFAULT)
        var newX = 0f
        var newY = 0f

        when (alignmentHorizontal to alignmentVertical) {
            START to CENTER                      -> {
                newY = (height / 2) - (actor.height / 2)
            }
            START to TOP                         -> {
                newY = height - actor.height
            }
            AlignmentHorizontal.CENTER to BOTTOM -> {
                newX = (width / 2) - (actor.width / 2)
            }
            AlignmentHorizontal.CENTER to CENTER -> {
                newX = (width / 2) - (actor.width / 2)
                newY = (height / 2) - (actor.height / 2)
            }
            AlignmentHorizontal.CENTER to TOP -> {
                newX = (width / 2) - (actor.width / 2)
                newY = height - actor.height
            }
            END to BOTTOM                        -> {
                newX = width - actor.width
            }
            END to CENTER                        -> {
                newX = width - actor.width
                newY = (height / 2) - (actor.height / 2)
            }
            END to TOP                           -> {
                newX = width - actor.width
                newY = height - actor.height
            }
        }
        actor.setPosition(newX, newY)
        addActor(actor)
    }

    fun addAndFillActor(actor: Actor) {
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
        addActor(actor)
    }

    fun addAndFillActors(actors: List<Actor>) {
        actors.setFillParent()
        actors.forEach { addActor(it) }
    }

    fun addActors(vararg actors: Actor) {
        actors.forEach { addActor(it) }
    }

    fun addActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
    }

    fun addActorChain(actor: Actor, style: ChainStyle, limit: Int, spaceX: Float, spaceY: Float) {
        if (limit == 0) throw Exception("Limit must be > 0.")

        when (style) {
            ChainStyle.START_TOP_END_BOTTOM -> styleStartTopEndBottom(actor, limit, spaceX, spaceY)
        }
    }



    private fun styleStartTopEndBottom(actor: Actor, limit: Int, spaceX: Float, spaceY: Float) {
        val x: Float
        val y: Float

        if (chainList.isEmpty()) {
            x = 0f
            y = this.height - actor.height
        } else {
            val firstActor = chainList.first()
            val lastActor = chainList.last()

            if (chainList.size % limit == 0) {
                x = firstActor.x
                y = lastActor.y - spaceY - actor.height
            } else {
                x = lastActor.x + lastActor.width + spaceX
                y = lastActor.y
            }
        }
        actor.setPosition(x, y)
        chainList.add(actor)
        addActor(actor)
    }



    enum class ChainStyle {
        START_TOP_END_BOTTOM
    }

    enum class AlignmentHorizontal { START, CENTER, END, }
    enum class AlignmentVertical { BOTTOM, CENTER, TOP, }

}