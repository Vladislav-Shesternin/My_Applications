package com.obezana.playtocrypto.game.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport

val Texture.region: TextureRegion get() = TextureRegion(this)
val Float.toMS: Long get() = (this * 1000).toLong()
val Viewport.zeroScreenVector: Vector2 get() = project(Vector2(0f, 0f))
val TextureEmpty get() = Texture(1, 1, Pixmap.Format.RGBA8888)

fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}
fun List<Disposable>.disposeAll() {
    onEach { it.dispose() }
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
fun runGDX(block: () -> Unit) {
    Gdx.app.postRunnable { block() }
}
fun Float.divOr0(num: Float) = try { this / num } catch (e: Exception) { 0f }