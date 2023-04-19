package com.veldan.fantasticslots.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import com.veldan.fantasticslots.utils.HEIGHT
import com.veldan.fantasticslots.utils.WIDTH
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.utils.addProcessors
import com.veldan.fantasticslots.utils.beginend
import com.veldan.fantasticslots.utils.controller.ScreenController

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {

    abstract val viewport  : Viewport
    abstract val controller: ScreenController

    val stage by lazy { AdvancedStage(viewport) }
    val inputMultiplexer = InputMultiplexer()
    var background: Texture? = null

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
        if (controller is Disposable) (controller as Disposable).dispose()
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