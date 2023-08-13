package com.leto.advancedcalculator.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.leto.advancedcalculator.game.utils.advanced.AdvancedGroup

class ImageGif(
    val regions: List<TextureRegion>
): AdvancedGroup() {

    private val image     = Image()
    private val animation = Animation(0.025f, *regions.toTypedArray())

    private var time = 0f

    override fun addActorsOnGroup() {
        addAndFillActor(image)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        Gdx.graphics.deltaTime.also { deltaTime ->
            image.drawable = TextureRegionDrawable(animation.getKeyFrame(time, true))
            time += deltaTime
        }
    }

}