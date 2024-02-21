package com.jungle.jumping.bird.game.utils.actor

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener

class ClickListener(val actor: Actor) {

    private var onClickBlock: (Actor) -> Unit = { }



    private fun Actor.getListener() = object : InputListener() {
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
                onClickBlock(actor)
            }
        }
    }

    fun setOnClickListener(block: (Actor) -> Unit) {
        onClickBlock = block
        with(actor) { addListener(getListener()) }
    }

}

fun Actor.toClickable() = ClickListener(this)