package golov.lomaka.sudjoken.game

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import golov.lomaka.sudjoken.game.utils.TextureEmpty
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedGroup
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedScreen
import golov.lomaka.sudjoken.game.utils.region
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class ACheckBox(
    override val screen: AdvancedScreen,
    type: Type? = null,
) : AdvancedGroup() {

    private val defaultImage by lazy {  if (type != null) Image(getStyleByType(type).default) else Image() }
    private val checkImage   by lazy { (if (type != null) Image(getStyleByType(type).checked) else Image()).apply { isVisible = false } }

    private var onCheckBlock: (Boolean) -> Unit = { }

    private var isInvokeCheckBlock: Boolean = true
    var checkBoxGroup: ACheckBoxGroup? = null

    val checkFlow = MutableStateFlow(false)

    private val spriteUtil by lazy { screen.game.spriteUtil }


    override fun addActorsOnGroup() {
        addAndFillActors(getActors())
        addListener(getListener())
        asyncCollectCheckFlow()
    }

    private fun getActors() = listOf<Actor>(
        defaultImage,
        checkImage,
    )

    private fun asyncCollectCheckFlow() {
        coroutine?.launch { checkFlow.collect { isCheck -> if (isInvokeCheckBlock) onCheckBlock(isCheck) } }
    }

    open fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                if (checkBoxGroup != null) {
                    if (checkFlow.value.not()) check()
                } else {
                    if (checkFlow.value) uncheck() else check()
                }
            }
        }
    }



    fun check(isInvokeCheckBlock: Boolean = true) {
        this.isInvokeCheckBlock = isInvokeCheckBlock

        checkBoxGroup?.let {
            it.currentCheckedCheckBox?.uncheck()
            it.currentCheckedCheckBox = this
        }

        defaultImage.isVisible = false
        checkImage.isVisible   = true

        checkFlow.value = true
    }

    fun uncheck(isInvokeCheckBlock: Boolean = true) {
        this.isInvokeCheckBlock = isInvokeCheckBlock

        defaultImage.isVisible = true
        checkImage.isVisible   = false

        checkFlow.value = false
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

    fun setStyle(style: ACheckBoxStyle) {
        defaultImage.drawable = TextureRegionDrawable(style.default)
        checkImage.drawable   = TextureRegionDrawable(style.checked)
    }

    fun setOnCheckListener(block: (Boolean) -> Unit) {
        onCheckBlock = block
    }

    fun getStyleByType(type: Type) = when(type) {
        Type.T_0 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[0] else spriteUtil.GAME.b_LIST[0],
        )
        Type.T_1 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[1] else spriteUtil.GAME.b_LIST[1],
        )
        Type.T_2 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[2] else spriteUtil.GAME.b_LIST[2],
        )
        Type.T_3 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[3] else spriteUtil.GAME.b_LIST[3],
        )
        Type.T_4 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[4] else spriteUtil.GAME.b_LIST[4],
        )
        Type.T_5 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[5] else spriteUtil.GAME.b_LIST[5],
        )
        Type.T_6 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[6] else spriteUtil.GAME.b_LIST[6],
        )
        Type.T_7 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[7] else spriteUtil.GAME.b_LIST[7],
        )
        Type.T_8 -> ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = if(screen.game.isWhite) spriteUtil.GAME.w_LIST[8] else spriteUtil.GAME.b_LIST[8],
        )
        Type.DEF -> ACheckBoxStyle(
            default = spriteUtil.GAME.DEF,
            checked = spriteUtil.GAME.CHK,
        )
    }

    // ---------------------------------------------------
    // Style
    // ---------------------------------------------------

    data class ACheckBoxStyle(
        val default: TextureRegion,
        val checked: TextureRegion,
    )

    enum class Type {
        T_0, T_1, T_2, T_3, T_4, T_5, T_6, T_7, T_8,
        DEF
    }

}