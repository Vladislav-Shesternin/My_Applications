package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import qbl.bisriymyach.QuickBall.tidams.mim

fun Actor.gol(sound: mim, block: (Actor) -> Unit) {
    addListener(object : InputListener() {
        var isWithin = false

        override fun touchDown(
            event: InputEvent?,
            x: Float,
            y: Float,
            pointer: Int,
            button: Int
        ): Boolean {
            sound.apply { play(clk) }
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {


            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                isWithin = false
                block(this@gol)
            }
        }
    })
}


fun Actor.frop() {
    touchable = Touchable.enabled
}

fun Actor.piolka() {
    touchable = Touchable.disabled
}

fun Actor.tehnos(position: Vector2, size: Vector2) {
    setBounds(position.x, position.y, size.x, size.y)
}

fun List<Actor>.hjg() {
    onEach { actor ->
        when (actor) {
            is Widget -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
}


fun Actor.setPosition(position: Vector2) {
    setPosition(position.x, position.y)
}

fun Actor.setOrigin(vector: Vector2) {
    setOrigin(vector.x, vector.y)
}

fun Actor.zahovaqka(time: Float = 0f, block: () -> Unit = {}) {
    addAction(
        Actions.sequence(
            Actions.fadeIn(time),
            Actions.run(block)
        )
    )
}

fun Actor.hrom(time: Float = 0f, block: () -> Unit = {}) {
    addAction(
        Actions.sequence(
            Actions.fadeOut(time),
            Actions.run(block)
        )
    )
}