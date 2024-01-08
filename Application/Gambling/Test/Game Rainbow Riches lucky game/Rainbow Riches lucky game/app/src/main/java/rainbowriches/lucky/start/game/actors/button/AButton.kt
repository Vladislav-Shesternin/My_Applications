package rainbowriches.lucky.start.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.region

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

            screen.game.soundUtil.apply { play(PUT) }

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
        Static.Type.PLAY -> Static.AButtonStyle(
            default = assets.PLAY_DEF.region,
            pressed = assets.PLAY_DIS.region,
            disabled = assets.PLAY_DIS.region,
        )
        Static.Type.MENU    -> Static.AButtonStyle(
            default = assets.MENU_DEF,
            pressed = assets.MENU_DIS,
            disabled = assets.MENU_DIS,
        )
        Static.Type.PAPER   -> Static.AButtonStyle(
            default = assets.PAPER_DEF,
            pressed = assets.PAPER_DIS,
            disabled = assets.PAPER_DIS,
        )
        Static.Type.GEAR   -> Static.AButtonStyle(
            default = assets.GEAR_DEF,
            pressed = assets.GEAR_DIS,
            disabled = assets.GEAR_DIS,
        )
        Static.Type.X   -> Static.AButtonStyle(
            default = assets.X_DEF,
            pressed = assets.X_DIS,
            disabled = assets.X_DIS,
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
            PLAY, MENU, PAPER, GEAR, X
        }
    }

}