package com.pharhaslo.slo7.game.actors

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.SoundUtil
import kotlinx.coroutines.flow.MutableStateFlow

class CheckBox(
    style: Style? = null,
) : AdvancedGroup() {

    private val defaultImage = if (style != null) Image(style.default) else Image()
    private val checkImage = (if (style != null) Image(style.checked) else Image()).apply { isVisible = false }

    private var onCheckBlock: (Boolean) -> Unit = { }

    var sound: Sound? = null
    var isControlCheckBoxManager = false
    val isCheckedFlow = MutableStateFlow(false)



    init {
        addListener(getListener())
        addAndFillActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        defaultImage,
        checkImage,
    )



    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.run { if (SoundUtil.isPause.not()) play(SoundUtil.volumeLevel.value) }
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                if (isControlCheckBoxManager) {
                    // Включение происходит только в CheckBoxManager при нажатии на другие CheckBox
                    if (isCheckedFlow.value.not()) checkAndDisable()
                } else {
                    if (isCheckedFlow.value) uncheck() else check()
                }
                onCheckBlock(isCheckedFlow.value)
            }
        }
    }



    fun check() {
        defaultImage.isVisible = false
        checkImage.isVisible = true
        isCheckedFlow.value = true
    }

    fun uncheck() {
        defaultImage.isVisible = true
        checkImage.isVisible = false
        isCheckedFlow.value = false
    }

    fun checkAndDisable() {
        check()
        disable()
    }

    fun uncheckAndEnabled() {
        uncheck()
        enable()
    }

    fun enable() {
        touchable = Touchable.enabled
    }

    fun disable() {
        touchable = Touchable.disabled
    }

    fun setStyle(style: Style) {
        defaultImage.drawable = TextureRegionDrawable(style.default)
        checkImage.drawable = TextureRegionDrawable(style.checked)
    }

    fun setOnCheckListener(sound: Sound? = SoundUtil.CHECK, block: (Boolean) -> Unit) {
        this.sound = sound
        onCheckBlock = block
    }



    data class Style(
        val default: Texture,
        val checked: Texture,
    ) {
        companion object {
            val style_1 get() = Style(
                default = SpriteManager.OptionsSprite.BOX_DEF.data.texture,
                checked = SpriteManager.OptionsSprite.BOX_PRESS.data.texture,
            )
        }
    }

}