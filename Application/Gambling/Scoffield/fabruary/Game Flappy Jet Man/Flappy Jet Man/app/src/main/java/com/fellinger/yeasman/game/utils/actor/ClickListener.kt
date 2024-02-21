package com.fellinger.yeasman.game.utils.actor

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.fellinger.yeasman.game.screens.happy_pop

class ClickListener(val actor: Actor) {

    private var onClickBlock: (Actor) -> Unit = { }



    private fun Actor.getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            happy_pop?.play(1f)

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