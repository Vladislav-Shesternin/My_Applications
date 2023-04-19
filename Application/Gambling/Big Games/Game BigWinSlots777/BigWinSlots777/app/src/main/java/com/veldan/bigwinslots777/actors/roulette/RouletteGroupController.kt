package com.veldan.bigwinslots777.actors.roulette

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.veldan.bigwinslots777.utils.controller.GroupController
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue

class RouletteGroupController<T: RouletteGroupController.RouletteItem>(override val group: RouletteGroup<T>) : GroupController {

    private fun determineItem(degree: Float): T {
        return group.items.firstOrNull { degree in (it.segment.first..it.segment.second) } ?: group.items.first()
    }



    suspend fun spin() = suspendCoroutine<RouletteItem> { continuation ->
        Gdx.app.postRunnable {
            group.setOrigin(Align.center)

            val degree = (1600..3600).random().toFloat()

            group.addAction(Actions.sequence(
                Actions.rotateBy(degree, 3f, Interpolation.pow4),
                Actions.run { continuation.resume(determineItem(group.rotation.absoluteValue)) }
            ))
        }
    }



    interface RouletteItem {
        val segment: Pair<Float, Float>
    }

}