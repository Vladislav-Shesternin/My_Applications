package com.rcks.scaloi.game.util.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.rcks.scaloi.game.manager.NavigationManager
import com.rcks.scaloi.game.util.HEIGHT
import com.rcks.scaloi.game.util.WIDTH
import com.rcks.scaloi.game.util.addProcessors
import com.rcks.scaloi.game.util.disposeAll
import com.rcks.scaloi.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {

    private val backViewport by lazy { FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val backStage    by lazy { AdvancedStage(backViewport) }

    private val backBackgroundImage  = Image()
    private val backgroundImage      = Image()

    val viewport by lazy { FitViewport(WIDTH, HEIGHT) }
    val stage    by lazy { AdvancedStage(viewport) }

    val inputMultiplexer = InputMultiplexer()

    val coroutine = CoroutineScope(Dispatchers.Default)





    override fun show() {
        backStage.addActor(backBackgroundImage)
        stage.addActor(backgroundImage)
        stage.addActorsOnStage()

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
        cancelCoroutinesAll(coroutine)
        disposeAll(backStage, stage)
        inputMultiplexer.clear()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.BACK) NavigationManager.back()
        return super.keyDown(keycode)
    }



    abstract fun AdvancedStage.addActorsOnStage()



    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.apply {
            drawable = TextureRegionDrawable(region)
            setSize(backViewport.worldWidth, backViewport.worldHeight)
        }
    }

    fun setBackground(texture: TextureRegion) {
        backgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }
    }

    fun setBackgrounds(backRegion: TextureRegion, frontRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        //setBackground(frontRegion)
    }

}