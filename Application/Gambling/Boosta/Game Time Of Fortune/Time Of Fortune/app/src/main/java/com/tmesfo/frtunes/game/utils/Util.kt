package com.tmesfo.frtunes.game.utils

import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport

val Any.region: TextureRegion get() = if (this is Texture) TextureRegion(this) else throw TypeCastException("Type is not Texture.")

val Viewport.zeroScreenVector: Vector2 get() = project(Vector2(0f, 0f))



fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}

fun Batch.beginend(block: Batch.() -> Unit = { }) {
    begin()
    block()
    end()
}

fun Batch.endbegin(block: Batch.() -> Unit = { }) {
    end()
    block()
    begin()
}

fun InputMultiplexer.addProcessors(vararg processor: InputProcessor) {
    processor.onEach { addProcessor(it) }
}

fun Actor.disable() {
    touchable = Touchable.disabled
}

fun Actor.enable() {
    touchable = Touchable.enabled
}