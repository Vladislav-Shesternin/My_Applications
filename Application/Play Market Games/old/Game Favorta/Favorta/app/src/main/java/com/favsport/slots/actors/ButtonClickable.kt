package com.favsport.slots.actors

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.favsport.slots.SOUND_VOLUME
import com.favsport.slots.advanced.AdvancedGroup

open class ButtonClickable(
    style: Style? = null
) : AdvancedGroup() {

    private val image = Image().apply { isVisible = false }
    private val defaultImage = if (style != null) Image(style.default) else Image()
    private val pressedImage = (if (style != null) Image(style.pressed) else Image()).apply { isVisible = false }

    private var onClickBlock: () -> Unit = { }

    var sound: Sound? = null



    init {
        addListener(getListener())
        addAndFillActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        image,
        defaultImage,
        pressedImage,
    )



    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.play(SOUND_VOLUME)
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
            if (isWithin) pressed() else unpressed()
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                unpressed()
                isWithin = false
                onClickBlock()
            }
        }
    }



    fun pressed() {
        defaultImage.isVisible = false
        pressedImage.isVisible = true
    }

    fun unpressed() {
        defaultImage.isVisible = true
        pressedImage.isVisible = false
    }

    fun pressedAndDisable() {
        defaultImage.isVisible = false
        pressedImage.isVisible = true
        disable()
    }

    fun unpressedAndEnabled() {
        defaultImage.isVisible = true
        pressedImage.isVisible = false
        enable()
    }

    fun enable() {
        touchable = Touchable.enabled
        resetImage()
    }

    fun disable(texture: Texture? = null) {
        touchable = Touchable.disabled
        texture?.let { setImage(texture) }
    }

    fun setStyle(style: Style) {
        defaultImage.drawable = TextureRegionDrawable(style.default)
        pressedImage.drawable = TextureRegionDrawable(style.pressed)
    }

    fun setImage(texture: Texture) {
        image.apply {
            drawable = TextureRegionDrawable(texture)
            isVisible = true
        }
        defaultImage.isVisible = false
        pressedImage.isVisible = false
    }

    fun resetImage() {
        image.isVisible = false
        unpressed()
    }

    fun setOnClickListener(sound: Sound? = null, block: () -> Unit) {
        this.sound = sound
        onClickBlock = block
    }



    data class Style(
        val default: Texture,
        val pressed: Texture,
    )

}