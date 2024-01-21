package com.favsport.slots.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.favsport.slots.advanced.AdvancedGroup

class ScissorGroup(
    viewport: Viewport,
    rect: Rectangle,
) : AdvancedGroup() {

    private val coord by lazy { viewport.project(Vector2(rect.x, rect.y)) }
    private val size by lazy { viewport.project(Vector2(rect.width, rect.height)) }

    val group = AdvancedGroup().apply { addActor(this@ScissorGroup) }


    override fun draw(batch: Batch?, parentAlpha: Float) {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST)
        Gdx.gl.glScissor(coord.x.toInt(), coord.y.toInt(), size.x.toInt(), size.y.toInt())
        super.draw(batch, parentAlpha)
        batch?.flush()
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST)
    }

}