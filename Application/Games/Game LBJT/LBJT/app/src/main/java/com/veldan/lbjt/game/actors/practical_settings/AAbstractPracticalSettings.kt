package com.veldan.lbjt.game.actors.practical_settings

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.lbjt.game.actors.progress.AProgressPractical
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.Layout
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.actor.enable
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.runGDX
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

abstract class AAbstractPracticalSettings(final override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private const val ONE_PERCENT_0_10k = 10_000f / 100f

        private const val NEGATIVE_m5_10             = 30
        private const val POSITIVE_ONE_PERCENT_m5_10 = 10f / 70f
        private const val NEGATIVE_ONE_PERCENT_m5_10 = 5f / 30f
    }

    // Font
    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)

    private val fontInter_Black_35   = screen.fontGeneratorInter_Black.generateFont(parameter.setSize(35))
    private val fontInter_Regular_35 = screen.fontGeneratorInter_Regular.generateFont(parameter.setSize(35))
    private val fontInter_Regular_20 = screen.fontGeneratorInter_Regular.generateFont(parameter.setSize(20))

    // Actor
    val valueLabelStyle = Label.LabelStyle(fontInter_Black_35, GameColor.textRed)

    // Field
    var isOpen = false
        private set


    override fun addActorsOnGroup() {
        disable()
        color.a = 0f
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun showAndEnabled(block: () -> Unit = {}) {
        animShow(TIME_ANIM_SCREEN_ALPHA) {
            isOpen = true
            enable()
            block()
        }
    }

    fun hideAndDisabled(block: () -> Unit = {}) {
        animHide(TIME_ANIM_SCREEN_ALPHA) {
            isOpen = false
            disable()
            block()
        }
    }

    // ---------------------------------------------------
    // Tools Actor
    // ---------------------------------------------------

    fun addLabel(
        stringId  : Int,
        labelFont : Static.LabelFont,
        layoutData: Layout.LayoutData,
        color     : Color = GameColor.textGreen,
        align     : Int = Align.left,
    ) {
        addActor(Label(screen.game.languageUtil.getStringResource(stringId), Label.LabelStyle(labelFont.getFont(), color)).also {
            it.setBounds(layoutData)
            it.setAlignment(align)
        })
    }

    // ---------------------------------------------------
    // Collect
    // ---------------------------------------------------

    suspend fun collectProgress_0_10k(currentValue: Float, progress: AProgressPractical, label: Label, block: (Float) -> Unit) {
        var tmpValue = 0f

        progress.apply {
            setProgressPercent(currentValue / 100f)
            progressPercentFlow.collect { progress ->
                runGDX {
                    tmpValue = progress * ONE_PERCENT_0_10k
                    label.setText("${tmpValue.roundToInt()}")
                    block(tmpValue)
                }
            }
        }
    }

    suspend fun collectProgress_m5_10(currentValue: Float, progress: AProgressPractical, label: Label, block: (Float) -> Unit) {
        var tmpValue = 0f

        progress.apply {
            tmpValue = if (currentValue >= 0) {
                NEGATIVE_m5_10 + (currentValue / POSITIVE_ONE_PERCENT_m5_10)
            } else {
                NEGATIVE_m5_10 - (currentValue.absoluteValue / NEGATIVE_ONE_PERCENT_m5_10)
            }
            setProgressPercent(tmpValue)

            progressPercentFlow.collect { progress ->
                runGDX {
                    if (progress >= NEGATIVE_m5_10) {
                        tmpValue = (progress - NEGATIVE_m5_10) * POSITIVE_ONE_PERCENT_m5_10
                        label.setText(String.format("%.1f", tmpValue).replace(',', '.'))
                        block(tmpValue)
                    } else {
                        tmpValue = (NEGATIVE_m5_10 - progress) * NEGATIVE_ONE_PERCENT_m5_10
                        label.setText("-" + String.format("%.1f", tmpValue).replace(',', '.'))
                        block(-tmpValue)
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Static
    // ---------------------------------------------------

    private fun Static.LabelFont.getFont(): BitmapFont = when(this) {
        Static.LabelFont.Inter_Regular_35 -> fontInter_Regular_35
        Static.LabelFont.Inter_Regular_20 -> fontInter_Regular_20
    }

    object Static {
        enum class LabelFont {
            Inter_Regular_35,
            Inter_Regular_20,
        }
    }

}