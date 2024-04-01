package com.liquidfun.playground.game.utils.actor

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.liquidfun.playground.game.manager.util.SoundUtil
import kotlin.math.round

fun Actor.setOnClickListener(sound: SoundUtil, block: (Actor) -> Unit) {
    addListener(object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound.apply { play(DROP) }

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
}

fun Actor.setOnTouchListener(sound: SoundUtil, radius: Int = 1, block: (Actor) -> Unit) {
    val touchPointDown = Vector2()
    val touchPointUp   = Vector2()
    addListener(object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound.apply { play(DROP) }
            touchPointDown.set(round(x), round(y))
            return true
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            touchPointUp.set(round(x), round(y))
            if (touchPointDown.x in (touchPointUp.x - radius..touchPointUp.x + radius) &&
                touchPointDown.y in (touchPointUp.y - radius..touchPointUp.y + radius)) block(this@setOnTouchListener)
        }
    })
}

fun List<Actor>.setFillParent() {
    onEach { actor ->
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
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

fun Actor.disable() {
    touchable = Touchable.disabled
}
fun Actor.enable() {
    touchable = Touchable.enabled
}