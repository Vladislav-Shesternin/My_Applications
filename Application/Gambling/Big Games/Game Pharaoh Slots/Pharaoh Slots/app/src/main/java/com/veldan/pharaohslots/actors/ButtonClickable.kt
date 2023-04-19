package com.veldan.pharaohslots.actors

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.pharaohslots.advanced.AdvancedGroup
import com.veldan.pharaohslots.assets.SpriteManager
import com.veldan.pharaohslots.assets.util.SoundUtil

class ButtonClickable(
    style: Style? = null
) : AdvancedGroup() {

    private var _style = style

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
            sound?.run { SoundUtil.apply { if (isPause.not()) play(volumeLevel.value) } }
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
            if (isWithin) press() else unpress()
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                unpress()
                onClickBlock()
            }
        }
    }



    fun press() {
        defaultImage.isVisible = false
        pressedImage.isVisible = true
    }

    fun unpress() {
        defaultImage.isVisible = true
        pressedImage.isVisible = false
    }

    fun pressAndDisable(useDisabledStyle: Boolean = true) {
        press()
        disable(useDisabledStyle)
    }

    fun unpressAndEnabled() {
        enable()
    }

    fun enable() {
        touchable = Touchable.enabled
        resetImage()
    }

    fun disable(useDisabledStyle: Boolean = true) {
        touchable = Touchable.disabled
        _style?.disabled?.let { if (useDisabledStyle) setImage(it) }
    }

    fun setStyle(style: Style) {
        _style = style
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
        unpress()
    }

    fun setOnClickListener(sound: Sound? = SoundUtil.CLICK, block: () -> Unit) {
        this.sound = sound
        onClickBlock = block
    }



    data class Style(
        val default: Texture,
        val pressed: Texture,
        val disabled: Texture? = null,
    ) {
        companion object {
            val button_1 = Style(
                default = SpriteManager.MenuSprite.BUTTON_DEF_1.data.texture,
                pressed = SpriteManager.MenuSprite.BUTTON_PRESS_1.data.texture,
            )
            val back = Style(
                default = SpriteManager.OptionsSprite.BACK_DEF.data.texture,
                pressed = SpriteManager.OptionsSprite.BACK_PRESS.data.texture,
            )
            val go = Style(
                default  = SpriteManager.GameSprite.GO_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.GO_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.GO_DIS.data.texture,
            )
            val autogo = Style(
                default  = SpriteManager.GameSprite.AUTOGO_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.AUTOGO_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.AUTOGO_DIS.data.texture,
            )
            val menu = Style(
                default = SpriteManager.GameSprite.MENU_DEF.data.texture,
                pressed = SpriteManager.GameSprite.MENU_PRESS.data.texture,
            )
            val plus = Style(
                default  = SpriteManager.GameSprite.PLUS_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.PLUS_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.PLUS_DIS.data.texture,
            )
            val minus = Style(
                default  = SpriteManager.GameSprite.MINUS_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.MINUS_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.MINUS_DIS.data.texture,
            )
        }
    }

}