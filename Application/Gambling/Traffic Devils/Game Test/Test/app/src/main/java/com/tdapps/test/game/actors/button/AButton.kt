package com.tdapps.test.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.tdapps.test.game.utils.advanced.AdvancedGroup
import com.tdapps.test.game.utils.advanced.AdvancedScreen

open class AButton(
    override val screen: AdvancedScreen,
    type: Type? = null
) : AdvancedGroup() {

    private val defaultImage  by lazy {  if (type != null) Image(getStyleByType(type).default)  else Image() }
    private val pressedImage  by lazy { (if (type != null) Image(getStyleByType(type).pressed)  else Image()).apply { isVisible = false } }
    private val disabledImage by lazy { (if (type != null) Image(getStyleByType(type).disabled) else Image()).apply { isVisible = false } }

    private var onClickBlock    : () -> Unit = { }

    var touchDownBlock   : AButton.(x: Float, y: Float) -> Unit = { x, y -> }
    var touchDraggedBlock: AButton.(x: Float, y: Float) -> Unit = { x, y -> }
    var touchUpBlock     : AButton.(x: Float, y: Float) -> Unit = { x, y -> }

    private var area: Actor? = null

    private val spriteUtil by lazy { screen.game.spriteUtil }


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
        Type.PL -> AButtonStyle(
            default  = spriteUtil.PL_DEF,
            pressed  = spriteUtil.PL_PRS,
            disabled = spriteUtil.PL_PRS,
        )
        Type.EX -> AButtonStyle(
            default  = spriteUtil.EX_DEF,
            pressed  = spriteUtil.EX_PRS,
            disabled = spriteUtil.EX_PRS,
        )
        Type.SN -> AButtonStyle(
            default  = spriteUtil.SP_DEF,
            pressed  = spriteUtil.SP_WAIT,
            disabled = spriteUtil.SP_WAIT,
        )
        Type.PLUS -> AButtonStyle(
            default  = spriteUtil.PULSEK_DEF,
            pressed  = spriteUtil.PULSEK_PRS,
            disabled = spriteUtil.PULSEK_PRS,
        )
        Type.MNUS -> AButtonStyle(
            default  = spriteUtil.MINUAS_DEF,
            pressed  = spriteUtil.MINUAS_PERS,
            disabled = spriteUtil.MINUAS_PERS,
        )
    }

    // ---------------------------------------------------
    // Style
    // ---------------------------------------------------

    data class AButtonStyle(
        val default : TextureRegion,
        val pressed : TextureRegion,
        val disabled: TextureRegion? = null,
    )

    enum class Type {
        PL, EX, SN, PLUS, MNUS
    }

}