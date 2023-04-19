package com.veldan.pharaohslots.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.veldan.pharaohslots.advanced.AdvancedGroup
import com.veldan.pharaohslots.utils.endbegin
import com.veldan.pharaohslots.utils.getFigmaY
import com.veldan.pharaohslots.utils.log

class ScissorGroup(val viewport: Viewport) : AdvancedGroup() {

    private var scissorRectangle = Rectangle()
    private var debugRectangle = Rectangle()

    private val shapeRenderer = ShapeRenderer()

    private val _0_WorldToPixelVector by lazy { viewport.project(Vector2(0f, 0f)) }

    private var isDraw = false

    var isDrawScissorDebug = false



    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (isDraw) drawScissor(batch, parentAlpha) else super.draw(batch, parentAlpha)
    }



    private fun drawScissor(batch: Batch?, parentAlpha: Float) {
        batch?.run { endbegin { if (isDrawScissorDebug) drawScissorDebug() } }

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST)
        with(scissorRectangle) {
            Gdx.gl.glScissor(
                x.toInt() + viewport.project(Vector2()).x.toInt() - _0_WorldToPixelVector.x.toInt(),
                y.toInt() + viewport.project(Vector2()).y.toInt() - _0_WorldToPixelVector.y.toInt(),
                width.toInt(),
                height.toInt()
            )
        }
        super.draw(batch, parentAlpha)
        batch?.flush()
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST)
    }



    private fun drawScissorDebug() {
        shapeRenderer.apply {
            projectionMatrix = viewport.camera.combined

            Gdx.gl.glLineWidth(5f)
            begin(ShapeRenderer.ShapeType.Line)
            color = Color.RED
            with(debugRectangle) { rect(x, y, width, height) }
            end()
            Gdx.gl.glLineWidth(1f)
        }
    }



    fun setBoundsScissor(x: Float, y: Float, width: Float, height: Float) {
       // log("x = $x y = $y w = $width h = $height")

        val coordinate = viewport.project(Vector2(x, getFigmaY(y, height)))
        val size = viewport.project(Vector2(width, height))

       // log("cx = ${coordinate.x} cy = ${coordinate.y} sx = ${size.x} sy = ${size.y}")
        scissorRectangle = Rectangle(coordinate.x, coordinate.y, size.x, size.y)
        debugRectangle = Rectangle(x, getFigmaY(y, height), width, height)
        isDraw = true


    }

}





