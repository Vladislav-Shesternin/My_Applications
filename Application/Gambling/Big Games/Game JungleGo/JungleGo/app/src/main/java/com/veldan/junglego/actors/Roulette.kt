package com.veldan.junglego.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.veldan.junglego.advanced.AdvancedActor
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.utils.region
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue

class Roulette(
    val items: List<RouletteItem>
) : AdvancedActor() {

    private val roulette = SpriteManager.GameSprite.ROULETTE.textureData.texture

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(roulette.region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
    }

    private fun determineItem(degree: Float): RouletteItem {
        return items.firstOrNull { degree in (it.segment.first..it.segment.second) } ?: items.first()
    }

    suspend fun spin() = suspendCoroutine<RouletteItem> { continuation ->
        Gdx.app.postRunnable {
            setOrigin(Align.center)

            val degree = (1600..3600).random().toFloat()

            addAction(
                Actions.sequence(
                    Actions.rotateBy(degree, 7f, Interpolation.pow4),
                    Actions.run { continuation.resume(determineItem(rotation.absoluteValue)) }
                )
            )
        }
    }

    data class RouletteItem(
        val price: Long,
        val segment: Pair<Float, Float>,
    )

}