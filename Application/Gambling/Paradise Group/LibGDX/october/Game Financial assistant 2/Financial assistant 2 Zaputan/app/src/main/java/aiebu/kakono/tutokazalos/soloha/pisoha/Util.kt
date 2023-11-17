package aiebu.kakono.tutokazalos.soloha.pisoha

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
val TextureEmpty get() = Texture(1, 1, Pixmap.Format.Alpha)

fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}

fun Collection<Disposable>.disposeAll() {
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

fun Float.divOr0(num: Float): Float = try { this / num } catch (e: Exception) { 0f }

fun Texture.combineByCenter(texture: Texture): Texture {
    if (textureData.isPrepared.not()) textureData.prepare()
    val pixmap1 = textureData.consumePixmap()

    if (texture.textureData.isPrepared.not()) texture.textureData.prepare()
    val pixmap2 = texture.textureData.consumePixmap()

    pixmap1.drawPixmap(pixmap2,
        (width / 2) - (texture.width / 2),
        (height / 2) - (texture.height / 2),
    )
    val textureResult = Texture(pixmap1)

    if (pixmap1.isDisposed.not()) pixmap1.dispose()
    if (pixmap2.isDisposed.not()) pixmap2.dispose()

    dispose()
    texture.dispose()

    return textureResult
}

fun ByteArray.toTexture(): Texture {
    val pixmap  = Pixmap(this, 0, size)
    val texture = Texture(pixmap)
    pixmap.dispose()
    return texture
}

fun Vector2.divOr0(scalar: Float): Vector2 {
    x = x.divOr0(scalar)
    y = y.divOr0(scalar)
    return this
}

fun numStr(min: Int, max: Int, count: Int): String {
    var str = ""
    repeat(count) { str += (min..max).random() }
    return str
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)