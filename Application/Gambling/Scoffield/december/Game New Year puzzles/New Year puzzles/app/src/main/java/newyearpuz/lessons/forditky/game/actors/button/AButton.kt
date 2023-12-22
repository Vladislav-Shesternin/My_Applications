package newyearpuz.lessons.forditky.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedGroup
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.region

open class AButton(
    override val screen: AdvancedScreen,
    type: Static.Type? = null
) : AdvancedGroup() {

    private val defaultImage  by lazy {  if (type != null) Image(getStyleByType(type).default)  else Image() }
    private val pressedImage  by lazy { (if (type != null) Image(getStyleByType(type).pressed)  else Image()).apply { isVisible = false } }
    private val disabledImage by lazy { (if (type != null) Image(getStyleByType(type).disabled) else Image()).apply { isVisible = false } }

    private var onClickBlock    : () -> Unit = { }

    var touchDownBlock   : AButton.(x: Float, y: Float) -> Unit = { x, y -> }
    var touchDraggedBlock: AButton.(x: Float, y: Float) -> Unit = { x, y -> }
    var touchUpBlock     : AButton.(x: Float, y: Float) -> Unit = { x, y -> }

    private var area: Actor? = null

    private val assets by lazy { screen.game.gameAssets }


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

            screen.game.soundUtil.apply { play(click) }

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

    fun setStyle(style: Static.AButtonStyle) {
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

    private fun getStyleByType(type: Static.Type) = when(type) {
        Static.Type.next    -> Static.AButtonStyle(
            default = assets.next,
            pressed = assets.nextp,
            disabled = assets.nextp,
        )
        Static.Type.exit    -> Static.AButtonStyle(
            default = assets.exit,
            pressed = assets.exitp,
            disabled = assets.exitp,
        )
        Static.Type._3    -> Static.AButtonStyle(
            default = assets.a3,
            pressed = assets.a3p,
            disabled = assets.a3p,
        )
        Static.Type._4    -> Static.AButtonStyle(
            default = assets.a4,
            pressed = assets.a4p,
            disabled = assets.a4p,
        )
        Static.Type._5    -> Static.AButtonStyle(
            default = assets.a5,
            pressed = assets.a5p,
            disabled = assets.a5p,
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
            next, _3, _4, _5, exit
        }
    }

}