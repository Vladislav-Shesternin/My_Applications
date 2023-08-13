package com.veldan.lbjt.game.utils

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
import java.util.ArrayList
import java.util.concurrent.CopyOnWriteArrayList

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

fun<T> CopyOnWriteArrayList<T>.onEachAndRemove(block: (T) -> Unit) = onEach {
    block(it)
    remove(it)
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

fun Texture.combineByCenter(texture: Texture): Texture {
    if (this.textureData.isPrepared.not()) this.textureData.prepare()
    val pixmap1 = this.textureData.consumePixmap()

    if (texture.textureData.isPrepared.not()) texture.textureData.prepare()
    val pixmap2 = texture.textureData.consumePixmap()

    pixmap1.drawPixmap(pixmap2,
        (this.width / 2) - (texture.width / 2),
        (this.height / 2) - (texture.height / 2)
    )
    val textureResult = Texture(pixmap1)

    if (pixmap1.isDisposed.not()) pixmap1.dispose()
    if (pixmap2.isDisposed.not()) pixmap2.dispose()

    return textureResult
}

fun TextureRegion.toTexture(isDisposePixmap: Boolean = true): Texture {
    val pixmap = Pixmap(regionWidth, regionHeight, Pixmap.Format.RGBA8888)
    if (texture.textureData.isPrepared.not()) texture.textureData.prepare()

    pixmap.drawPixmap(texture.textureData.consumePixmap(), 0, 0, regionX, regionY, regionWidth, regionHeight)

    val newTexture = Texture(pixmap)
    if (isDisposePixmap) pixmap.dispose()

    return newTexture
}
