package com.goplaytoday.guildofhero.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.goplaytoday.guildofhero.game.actors.button.AButton.Static.AButtonStyle
import com.goplaytoday.guildofhero.game.actors.button.AButton.Static.Type
import com.goplaytoday.guildofhero.game.screens.SettScreen
import com.goplaytoday.guildofhero.game.screens.staticSound_Click
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedGroup
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen

open class AButton(
    override val screen: AdvancedScreen,
    type: Type? = null
) : AdvancedGroup() {

    private val defaultImage  by lazy {  if (type != null) Image(getStyleByType(type).default)  else Image() }
    private val pressedImage  by lazy { (if (type != null) Image(getStyleByType(type).pressed)  else Image()).apply { isVisible = false } }
    private val disabledImage by lazy { (if (type != null) Image(getStyleByType(type).disabled) else Image()).apply { isVisible = false } }

    private var onClickBlock    : () -> Unit = { }

    var touchDownBlock   : AButton.(x: Float, y: Float) -> Unit = { _, _ -> }
    var touchDraggedBlock: AButton.(x: Float, y: Float) -> Unit = { _, _ -> }
    var touchUpBlock     : AButton.(x: Float, y: Float) -> Unit = { _, _ -> }

    private var area: Actor? = null

    private val spriteUtil by lazy { screen.game.commonAssets }


    override fun addActorsOnGroup() {
        addAndFillActors(getActors())
        addListener(getListener())
    }


    private fun getActors() = listOf<Actor>(
        defaultImage,
        pressedImage,
        disabledImage,
    )



    private fun getListener() = object : InputListener() {
        var isWithin     = false
        var isWithinArea = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDownBlock(x, y)
            touchDragged(event, x, y, pointer)

            if (SettScreen.isPlaySound) staticSound_Click?.play()

            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            touchDraggedBlock(x, y)

            isWithin = x in 0f..width && y in 0f..height
            area?.let { isWithinArea = x in 0f..it.width && y in 0f..it.height }


            if (isWithin || isWithinArea) press() else unpress()

        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            touchUpBlock(x, y)

            if (isWithin || isWithinArea) {
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

    fun disable(useDisabledStyle: Boolean = true) {
        touchable = Touchable.disabled

        if (useDisabledStyle) {
            defaultImage.isVisible  = false
            pressedImage.isVisible  = false
            disabledImage.isVisible = true
        }

    }

    fun enable() {
        touchable = Touchable.enabled

        defaultImage.isVisible  = true
        pressedImage.isVisible  = false
        disabledImage.isVisible = false

    }

    fun pressAndDisable(useDisabledStyle: Boolean = false) {
        press()
        disable(useDisabledStyle)
    }

    fun unpressAndEnable() {
        unpress()
        enable()
    }

    fun setStyle(style: AButtonStyle) {
        defaultImage.drawable  = TextureRegionDrawable(style.default)
        pressedImage.drawable  = TextureRegionDrawable(style.pressed)
        disabledImage.drawable = TextureRegionDrawable(style.disabled)
    }

    fun setOnClickListener(block: () -> Unit) {
        onClickBlock = block
    }

    fun addArea(actor: Actor) {
        area = actor
        actor.addListener(getListener())
    }

    private fun getStyleByType(type: Type) = when(type) {
        Type.Play -> AButtonStyle(
            default = spriteUtil.play_def,
            pressed = spriteUtil.play_press,
            disabled = spriteUtil.play_press,
        )
        Type.Sett -> AButtonStyle(
            default  = spriteUtil.sett_def,
            pressed  = spriteUtil.sett_press,
            disabled = spriteUtil.sett_press,
        )
        Type.Exit -> AButtonStyle(
            default  = spriteUtil.exit_def,
            pressed  = spriteUtil.exit_press,
            disabled = spriteUtil.exit_press,
        )
        Type.Menu -> AButtonStyle(
            default  = spriteUtil.menu_def,
            pressed  = spriteUtil.menu_press,
            disabled = spriteUtil.menu_press,
        )
        Type.Sparkle -> AButtonStyle(
            default  = spriteUtil.go_def,
            pressed  = spriteUtil.go_press,
            disabled = spriteUtil.go_press,
        )
        Type.Pl -> AButtonStyle(
            default  = spriteUtil.plusic_def,
            pressed  = spriteUtil.plusic_press,
            disabled = spriteUtil.plusic_press,
        )
        Type.Ms -> AButtonStyle(
            default  = spriteUtil.minus_def,
            pressed  = spriteUtil.minus_press,
            disabled = spriteUtil.minus_press,
        )
    }

    // ---------------------------------------------------
    // Style
    // ---------------------------------------------------

    object Static {
        data class AButtonStyle(
            val default: TextureRegion,
            val pressed: TextureRegion,
            val disabled: TextureRegion? = null,
        )

        enum class Type {
            Play, Sett, Exit, Menu, Sparkle, Pl, Ms
        }
    }

}