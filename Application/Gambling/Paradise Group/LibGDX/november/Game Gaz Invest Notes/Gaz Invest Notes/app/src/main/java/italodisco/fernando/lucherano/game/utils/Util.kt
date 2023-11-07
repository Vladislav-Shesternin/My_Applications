package italodisco.fernando.lucherano.game.utils

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

fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}

fun Collection<Disposable>.disposeAll() {
    onEach { it.dispose() }
}

fun InputMultiplexer.addProcessors(vararg processor: InputProcessor) {
    processor.onEach { addProcessor(it) }
}

fun runGDX(block: () -> Unit) {
    Gdx.app.postRunnable { block() }
}

fun Float.divOr0(num: Float): Float = try { this / num } catch (e: Exception) { 0f }

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