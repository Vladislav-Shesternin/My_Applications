package com.veldan.junglego.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.viewport.Viewport
import com.veldan.junglego.HEIGHT
import com.veldan.junglego.WIDTH
import com.veldan.junglego.manager.NavigationManager
import com.veldan.junglego.utils.addProcessors
import com.veldan.junglego.utils.beginend

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {

    protected abstract val viewport: Viewport

    protected var background: Texture? = null
    protected val stage by lazy { AdvancedStage(viewport) }
    protected val inputMultiplexer = InputMultiplexer()

    override fun show() {
        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stage) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        drawBackground()
        stage.render()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        stage.dispose()
        inputMultiplexer.clear()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.BACK) NavigationManager.back()
        return super.keyDown(keycode)
    }

    private fun drawBackground() {
        stage.batch.apply {
            disableBlending()
            beginend { background?.let { draw(it, 0f, 0f, WIDTH, HEIGHT) } }
            enableBlending()
        }
    }

}