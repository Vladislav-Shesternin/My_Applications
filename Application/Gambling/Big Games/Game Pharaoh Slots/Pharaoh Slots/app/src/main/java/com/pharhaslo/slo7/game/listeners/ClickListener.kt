package com.pharhaslo.slo7.game.listeners

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.pharhaslo.slo7.game.assets.util.SoundUtil

class ClickListener(private val actor: Actor) {

    private var onClickBlock: () -> Unit = { }



    private fun Actor.getListener(sound: Sound? = null) = object : InputListener() {
        var isWithin = false

        override fun enter(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
            super.enter(event, x, y, pointer, fromActor)
        }



        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.run { SoundUtil.apply { if (isPause.not()) play(volumeLevel.value) } }
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                isWithin = false
                onClickBlock()
            }
        }
    }



    fun setOnClickListener(sound: Sound? = null, block: () -> Unit) {
        onClickBlock = block
        with(actor) { addListener(getListener(sound)) }
    }





}

fun Actor.toClickable() = ClickListener(this)