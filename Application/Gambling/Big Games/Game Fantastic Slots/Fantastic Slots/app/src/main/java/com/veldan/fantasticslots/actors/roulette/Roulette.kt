package com.veldan.fantasticslots.actors.roulette

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.veldan.fantasticslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class Roulette(
    texture             : Texture,
    val rouletteItemList: List<RouletteItem>,
    val timeRotation    : Float = TIME_ROTATION,
    val direction       : Direction = Direction.CLOCKWISE
) : Image(texture) {

    companion object {
        const val TIME_ROTATION = 5f
    }



    private fun determineItem(degree: Float): RouletteItem {
        return rouletteItemList.firstOrNull { degree in (it.segment.first..it.segment.second) } ?: rouletteItemList.first()
    }



    suspend fun spin() = suspendCoroutine<RouletteItem> { continuation ->
        val degree = (1600..3600).random().toFloat()

        Gdx.app.postRunnable {
            setOrigin(Align.center)
            addAction(Actions.sequence(
                    Actions.rotateBy(degree * direction.value, timeRotation, Interpolation.pow4),
                    Actions.run { continuation.resume(determineItem(rotation.absoluteValue)) }
            ))
        }
    }



    enum class Direction(val value: Int) {
        CLOCKWISE(-1), COUNTERCLOCKWISE(1)
    }

    interface RouletteItem {
        val segment: Pair<Float, Float>
    }

}