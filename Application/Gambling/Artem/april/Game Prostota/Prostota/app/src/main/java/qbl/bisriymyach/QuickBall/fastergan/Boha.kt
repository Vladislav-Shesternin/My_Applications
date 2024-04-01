package qbl.bisriymyach.QuickBall.fastergan

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable




fun currentTimeMinus(time: Long) = System.currentTimeMillis().minus(time)
fun Collection<Disposable>.hshshshJ() {
    onEach { it.dispose() }
}

fun InputMultiplexer.papkaproc(vararg processor: InputProcessor) {
    processor.onEach {

                addProcessor(it) }
}

val Texture.mp489: TextureRegion get() = TextureRegion(this)
val TextureEpidpty get() = Texture(1, 1, Pixmap.Format.Alpha)

fun hshshshJ(vararg disposable: Disposable) {
                 disposable.forEach { it.dispose() }
}




                    fun interface Block {
    fun invoke()
}

fun runGDX(block: Block) {
    Gdx.app.postRunnable { block.invoke() }
}

fun Float.divOr0(


            num: Float): Float = try { this / num } catch (e: Exception) { 0f }

fun Vector2.divOr0(scalar: Float): Vector2 {
    x = x.divOr0(scalar)
                            y = y.divOr0(scalar)
    return this
}