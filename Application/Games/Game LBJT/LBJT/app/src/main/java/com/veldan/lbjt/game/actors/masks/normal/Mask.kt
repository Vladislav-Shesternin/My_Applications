package com.veldan.lbjt.game.actors.masks.normal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.Vector2
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.combineByCenter
import com.veldan.lbjt.game.utils.zeroScreenVector
import com.veldan.lbjt.util.log

class Mask(
    override val screen: AdvancedScreen,
    val mask: Texture? = null,
) : AdvancedGroup() {

    companion object {
        private const val PIXMAP_W = 2000
        private const val PIXMAP_H = 2000
    }

    private val maskTexture: Texture by lazy {
        val pixmap = Pixmap(PIXMAP_W, PIXMAP_H, Pixmap.Format.RGBA8888)
        pixmap.setColor(0f, 0f, 0f, 0f)
        pixmap.fill()

        val texture = if (mask == null) {
            pixmap.setColor(0f, 0f, 0f, 1f)
            pixmap.fillRectangle(
                (PIXMAP_W / 2) - (width.toInt() / 2),
                (PIXMAP_H / 2) - (height.toInt() / 2),
                width.toInt(), height.toInt()
            )
            Texture(pixmap)
        } else Texture(pixmap).combineByCenter(mask)

        if (pixmap.isDisposed.not()) pixmap.dispose()
        texture
    }

    override fun addActorsOnGroup() {}

    override fun dispose() {
        super.dispose()
        mask?.dispose()
        maskTexture.dispose()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (stage != null) {
            batch?.flush()
            batch?.drawMask(parentAlpha)
        }
    }

    private fun drawSuper(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    private fun Batch.drawMask(parentAlpha: Float) {
        Gdx.gl.glColorMask(false, false, false, true)

        setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO)

        setColor(0f, 0f, 0f, parentAlpha)
        draw(maskTexture,
            x + (width / 2) - (maskTexture.width / 2),
            y + (height /2) - (maskTexture.height / 2),
            maskTexture.width.toFloat(), maskTexture.height.toFloat()
        )

        drawMasked(parentAlpha)
    }

    private fun Batch.drawMasked(parentAlpha: Float) {
        setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA)
        drawSuper(this, parentAlpha)
        flush()

        Gdx.gl.glColorMask(true, true, true, true)
        setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA)
        drawSuper(this, parentAlpha)
        flush()

        setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
    }

}