package com.bezdrodovam.cryptoinsightspro.game.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable

val Texture.region: TextureRegion get() = TextureRegion(this)

val TextureEmpty get() = Texture(1, 1, Pixmap.Format.RGBA8888)



fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}

fun InputMultiplexer.addProcessors(vararg processor: InputProcessor) {
    processor.onEach { addProcessor(it) }
}

fun runGDX(block: () -> Unit) {
    Gdx.app.postRunnable { block() }
}

data class Size(
    var width : Float = 0f,
    var height: Float = 0f,
)

fun numStr(min: Int, max: Int, count: Int): Long {
    var numStr = ""
    repeat(count) { numStr += (min..max).shuffled().first() }
    return numStr.toLong()
}