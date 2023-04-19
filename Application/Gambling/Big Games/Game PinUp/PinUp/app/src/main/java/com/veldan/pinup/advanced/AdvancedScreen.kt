package com.veldan.pinup.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.utils.*
import com.veldan.pinup.utils.controller.ScreenController

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {
    abstract val controller: ScreenController

    private val backViewport by lazy { ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val backStage    by lazy { AdvancedStage(backViewport) }

    val viewport by lazy { FitViewport(WIDTH, HEIGHT) }
    val stage    by lazy { AdvancedStage(viewport) }

    val inputMultiplexer = InputMultiplexer()

    val backBackgroundImage  = Image()
    val backgroundImage      = Image()



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
        disposeAll(backStage, stage)
        inputMultiplexer.clear()
        if (controller is Disposable) (controller as Disposable).dispose()
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
    }

    fun setBackgrounds(backTexture: Texture, frontTexture: Texture = backTexture) {
        setBackBackground(backTexture)
        setBackground(frontTexture)
    }

}