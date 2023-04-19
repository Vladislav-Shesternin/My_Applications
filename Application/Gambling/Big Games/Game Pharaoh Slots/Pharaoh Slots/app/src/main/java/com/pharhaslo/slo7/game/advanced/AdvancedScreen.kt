package com.pharhaslo.slo7.game.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.pharhaslo.slo7.game.manager.NavigationManager
import com.pharhaslo.slo7.game.utils.*

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {

    abstract val viewport  : Viewport
    val backViewport by lazy { ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    val backStage by lazy { AdvancedStage(backViewport) }

    val stage by lazy { AdvancedStage(viewport) }
    val inputMultiplexer = InputMultiplexer()

    val backBackgroundImage = Image()
    val backgroundImage = Image()



    override fun show() {
        backStage.addActor(backBackgroundImage)
        stage.addActor(backgroundImage)
        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stage) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun resize(width: Int, height: Int) {
        backViewport.update(width, height, true)
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        backStage.render()
        stage.render()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        backStage.dispose()
        stage.dispose()
        inputMultiplexer.clear()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.BACK) NavigationManager.back()
        return super.keyDown(keycode)
    }

    fun setBackBackground(texture: Texture) {
        backBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(backViewport.worldWidth, backViewport.worldHeight)
        }
    }

    fun setBackground(texture: Texture) {
        backgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }

        setBackBackground(texture)
    }

}