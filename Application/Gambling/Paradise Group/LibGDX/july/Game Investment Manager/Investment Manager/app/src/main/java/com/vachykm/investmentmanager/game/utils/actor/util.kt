package com.vachykm.investmentmanager.game.utils.actor

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.vachykm.investmentmanager.game.utils.Layout
import com.vachykm.investmentmanager.game.utils.Size
import com.vachykm.investmentmanager.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred

/*fun Actor.setOnClickListener(block: (Actor) -> Unit) {
    addListener(object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                isWithin = false
                block(this@setOnClickListener)
            }
        }
    })
}*/

fun Actor.setOnClickListener(block: (Actor) -> Unit) {
    addListener(object : ClickListener() {
        override fun clicked(event: InputEvent?, x: Float, y: Float) {
            block(this@setOnClickListener)
        }
    })
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

fun Actor.setBounds(layoutData: Layout.LayoutData) {
    with(layoutData) { setBounds(x, y, w, h) }
}

fun Actor.setBounds(position: Vector2, size: Size) {
    setBounds(position.x, position.y, size.width, size.height)
}
fun Actor.getSizeByPercentX(percent: Float) = (width / 100f) * percent

fun Actor.getSizeByPercentY(percent: Float) = (height / 100f) * percent

suspend fun Actor.animSuspendShow(time: Float = 0f) = CompletableDeferred<Unit>().also { continuation ->
    runGDX {
        addAction(Actions.sequence(
            Actions.fadeIn(time),
            Actions.run { continuation.complete(Unit) }
        ))
    }
}.await()

suspend fun Actor.animSuspendHide(time: Float = 0f) = CompletableDeferred<Unit>().also { continuation ->
    runGDX {
        addAction(
            Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { continuation.complete(Unit) }
            ))
    }
}.await()

fun Actor.animShow(time: Float = 0f, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.fadeIn(time),
        Actions.run { block() }
    ))
}

fun Actor.animHide(time: Float = 0f, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.fadeOut(time),
        Actions.run { block() }
    ))
}