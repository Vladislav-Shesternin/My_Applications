package com.veldan.fantasticslots.actors.mask

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.utils.zeroScreenVector

class Mask(
    val mask: Texture? = null,
) : AbstractAdvancedGroup() {
    override val controller = MaskController(this)

    private lateinit var localToScreenCoordinates: Vector2
    private lateinit var localToScreenSize       : Vector2

    private var mX: Float = 0f
    private var mY: Float = 0f
    private var mW: Float = 0f
    private var mH: Float = 0f



    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (stage != null) {
            updateCoordinatesAndSize()
            batch?.flush()
            drawScissor(batch, parentAlpha)
        }
    }



    private fun drawScissor(batch: Batch?, parentAlpha: Float) {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST)
        Gdx.gl.glScissor(mX.toInt(), mY.toInt(), mW.toInt(), mH.toInt())

        if (mask != null) batch?.drawMask(parentAlpha) else super.draw(batch, parentAlpha)

        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST)
    }



    private fun updateCoordinatesAndSize() {
        localToScreenCoordinates = localToScreenCoordinates(Vector2())
        localToScreenSize        = stage.viewport.project(Vector2(width, height))

        mX = localToScreenCoordinates.x
        mY = Gdx.graphics.height - localToScreenCoordinates.y
        mW = localToScreenSize.x - stage.viewport.zeroScreenVector.x
        mH = localToScreenSize.y - stage.viewport.zeroScreenVector.y
    }



    private fun Batch.drawMask(parentAlpha: Float) {
        Gdx.gl.glColorMask(false, false, false, true)

        setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO)

        setColor(0f, 0f, 0f, parentAlpha)
        draw(mask, x, y, width, height)

        drawMasked(parentAlpha)
    }

    private fun Batch.drawMasked(parentAlpha: Float) {
        setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA)
        super.draw(this, parentAlpha)
        flush()

        Gdx.gl.glColorMask(true, true, true, true)
        setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA)
        super.draw(this, parentAlpha)
        flush()

        setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
    }

}