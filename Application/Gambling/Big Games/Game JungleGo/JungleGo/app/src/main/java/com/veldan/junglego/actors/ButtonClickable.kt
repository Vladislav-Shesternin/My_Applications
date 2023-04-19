package com.veldan.junglego.actors

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.SoundUtil
import com.veldan.junglego.utils.log

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

    fun unpressAndEnabled(useDisabledStyle: Boolean = false) {
        unpress()
        enable(useDisabledStyle)
    }

    fun enable(useDisabledStyle: Boolean = false) {
        touchable = Touchable.enabled
        if (useDisabledStyle.not()) resetImage()
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
            val style_1 = Style(
                default = SpriteManager.MenuSprite.BUTTON_DEF_1.textureData.texture,
                pressed = SpriteManager.MenuSprite.BUTTON_PRESS_1.textureData.texture,
            )
            val style_2 = Style(
                default = SpriteManager.OptionsSprite.BACK_DEF.textureData.texture,
                pressed = SpriteManager.OptionsSprite.BACK_PRESS.textureData.texture,
            )
            val style_3 = Style(
                default = SpriteManager.GameSprite.SPIN_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.SPIN_PRESS.textureData.texture,
                disabled = SpriteManager.GameSprite.SPIN_DIS.textureData.texture,
            )
            val style_4 = Style(
                default = SpriteManager.GameSprite.AUTOSPIN_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.AUTOSPIN_PRESS.textureData.texture,
                disabled = SpriteManager.GameSprite.AUTOSPIN_DIS.textureData.texture,
            )
            val style_5 = Style(
                default = SpriteManager.GameSprite.MENU_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.MENU_PRESS.textureData.texture,
            )
            val style_6 = Style(
                default = SpriteManager.GameSprite.BONUS_DONE_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.BONUS_DONE_PRESS.textureData.texture,
            )
            val style_7 = Style(
                default = SpriteManager.GameSprite.PLUS_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.PLUS_PRESS.textureData.texture,
            )
            val style_8 = Style(
                default = SpriteManager.GameSprite.MINUS_DEF.textureData.texture,
                pressed = SpriteManager.GameSprite.MINUS_PRESS.textureData.texture,
            )
        }
    }

}