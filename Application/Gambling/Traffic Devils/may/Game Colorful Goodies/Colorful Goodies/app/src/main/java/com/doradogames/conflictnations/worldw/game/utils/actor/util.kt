package com.doradogames.conflictnations.worldw.game.utils.actor

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.doradogames.conflictnations.worldw.game.actors.button.AButton
import com.doradogames.conflictnations.worldw.game.manager.util.SoundUtil
import com.doradogames.conflictnations.worldw.game.utils.Block
import com.doradogames.conflictnations.worldw.game.utils.HEIGHT_UI
import com.doradogames.conflictnations.worldw.game.utils.Layout
import com.doradogames.conflictnations.worldw.game.utils.WIDTH_UI
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedGroup
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.round

fun Actor.setOnClickListener(soundUtil: SoundUtil? = null, block: (Actor) -> Unit) {
    addListener(object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            soundUtil?.apply { play(click) }
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
}

fun Actor.disable() = when(this) {
    is AButton -> disable()
    else       -> touchable = Touchable.disabled
}

fun Actor.enable() = when(this) {
    is AButton -> enable()
    else       -> touchable = Touchable.enabled
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

fun Actor.setBounds(position: Vector2, size: Vector2) {
    setBounds(position.x, position.y, size.x, size.y)
}

fun Actor.setPosition(position: Vector2) {
    setPosition(position.x, position.y)
}

fun Actor.setOrigin(vector: Vector2) {
    setOrigin(vector.x, vector.y)
}

fun Actor.animShow(time: Float=0f, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.fadeIn(time),
        Actions.run(block)
    ))
}
fun Actor.animHide(time: Float=0f, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.fadeOut(time),
        Actions.run(block)
    ))
}

// Anim Suspend
suspend fun animShowPanelSuspend(panel: AdvancedGroup) = suspendCoroutine<Unit> { continuation ->
    panel.addAction(Actions.sequence(
        Actions.parallel(
            Actions.fadeIn(0.7f),
            Actions.moveTo(0f, 0f, 0.7f, Interpolation.swingOut)
        ),
        Actions.run { continuation.resume(Unit) }
    ))
}

suspend fun animHidePanelSuspend(panel: AdvancedGroup, block: Block = Block {}) = suspendCoroutine<Unit> { continuation ->
    panel.addAction(Actions.sequence(
        Actions.parallel(
            Actions.fadeOut(0.5f),
            Actions.moveTo(WIDTH_UI, 0f, 0.5f, Interpolation.swingIn)
        ),
        Actions.run {
            block.invoke()
            continuation.resume(Unit)
        }
    ))
}

suspend fun Actor.animShowSuspend(time: Float) = suspendCoroutine<Unit> { continuation ->
    animShow(time) { continuation.resume(Unit) }
}

suspend fun Actor.animHideSuspend(time: Float) = suspendCoroutine<Unit> { continuation ->
    animHide(time) { continuation.resume(Unit) }
}