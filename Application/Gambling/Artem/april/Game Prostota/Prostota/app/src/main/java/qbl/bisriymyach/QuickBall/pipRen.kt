package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import qbl.bisriymyach.QuickBall.fastergan.TextureEpidpty
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.fastergan.mp489
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.sudams.Denca_Kuduro
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi

open class pipRen(
    override val screen: suchka,
    type: Static.Type? = null,
) : Oi_oi_uoi() {

    private val defaultImage by lazy { if (type != null) Image(getStyleByType(type).default) else Image() }
    private val checkImage by lazy {
        (if (type != null) Image(getStyleByType(type).checked) else Image()).apply {
            isVisible = false
        }
    }

    private var onCheckBlock: (Boolean) -> Unit = { }

    private var isInvokeCheckBlock: Boolean = true
    var checkBoxGroup: Denca_Kuduro? = null

    val checkFlow = MutableStateFlow(false)

    private val assets by lazy { screen.game.allAssets }


    override fun addActorsOnGroup() {
        addAndFillActors(getActors())
        addListener(openerta())
        ioaik987()
    }

    private fun getActors() = listOf<Actor>(
        defaultImage,
        checkImage,
    )

    private fun ioaik987() {
        coroutine?.launch {
            checkFlow.collect { isCheck ->
                if (isInvokeCheckBlock) onCheckBlock(isCheck)
            }
        }
    }

    open fun openerta() = object : InputListener() {
        var isWithin = false

        override fun touchDown(
            event: InputEvent?,
            x: Float,
            y: Float,
            pointer: Int,
            button: Int
        ): Boolean {
            screen.game.soundUtil.apply { play(clk) }
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
        checkImage.isVisible = true

        checkFlow.value = true
    }

    fun uncheck(isInvokeCheckBlock: Boolean = true) {
        this.isInvokeCheckBlock = isInvokeCheckBlock

        defaultImage.isVisible = true
        checkImage.isVisible = false

        checkFlow.value = false
    }

    fun disable() {
        touchable = Touchable.disabled
    }

    fun setOnCheckListener(block: (Boolean) -> Unit) {
        onCheckBlock = block
    }

    fun getStyleByType(type: Static.Type) = when (type) {
        Static.Type.BALL -> Static.ACheckBoxStyle(
            default = TextureEpidpty.mp489,
            checked = assets.crokl,
        )

        Static.Type.BACKGROUND -> Static.ACheckBoxStyle(
            default = TextureEpidpty.mp489,
            checked = assets.kvadr,
        )

        Static.Type.NO -> Static.ACheckBoxStyle(
            default = TextureEpidpty.mp489,
            checked = assets.kars,
        )

        Static.Type.PORD -> Static.ACheckBoxStyle(
            default = TextureEpidpty.mp489,
            checked = assets.podarok,
        )
    }

    object Static {
        data class ACheckBoxStyle(
            val default: TextureRegion,
            val checked: TextureRegion,
        )

        enum class Type {
            BALL, BACKGROUND, NO, PORD
        }
    }

}