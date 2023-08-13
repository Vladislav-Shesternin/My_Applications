package com.prochenkoa.businessplanner.game.utils.actor

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.prochenkoa.businessplanner.game.utils.Layout
import com.prochenkoa.businessplanner.game.utils.Size
import com.prochenkoa.businessplanner.game.utils.WIDTH

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


fun Actor.animShow(time: Float, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.parallel(
            Actions.fadeIn(time, Interpolation.sineIn),
            //Actions.moveTo(0f, 0f, time, Interpolation.swingOut)
        ),
        Actions.run { block() }
    ))
}

fun Actor.animHide(time: Float, block: () -> Unit = {}) {
    addAction(Actions.sequence(
        Actions.parallel(
            Actions.fadeOut(time, Interpolation.sineOut),
            //Actions.moveTo(WIDTH, 0f, time, Interpolation.sineIn)
        ),
        Actions.run { block() }
    ))
}