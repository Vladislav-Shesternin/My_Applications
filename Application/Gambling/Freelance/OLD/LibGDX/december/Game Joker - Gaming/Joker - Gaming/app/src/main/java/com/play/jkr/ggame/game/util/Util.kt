package com.play.jkr.ggame.game.util

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport

val Texture.region: TextureRegion get() = TextureRegion(this)

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

fun List<Actor>.setFillParent() {
    onEach { actor ->
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
}

data class Size(
    var width : Float = 0f,
    var height: Float = 0f,
) {
    fun set(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    fun set(size: Size) {
        width = size.width
        height = size.height
    }

}


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun isDevMode(context: Context): Boolean {
    return when {
        Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN  -> {
            Settings.Secure.getInt(context.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
        }
        Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> {
            @Suppress("DEPRECATION")
            Settings.Secure.getInt(context.contentResolver,
                Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
        }
        else                                                    -> false
    }
}

fun isUSB(context: Context): Boolean = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ADB_ENABLED, 0) != 0
