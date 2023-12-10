package com.veldan.lbjt.game.actors.practical_settings

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.progress.AProgressPractical
import com.veldan.lbjt.game.utils.Layout
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.util.log
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class APracticalSettings_JMouse(_screen: AdvancedScreen): AAbstractPracticalSettings(_screen) {

    companion object {
        var maxForceValue    : Float = 1000f
        var frequencyHzValue : Float = 5.0f
        var dampingRatioValue: Float = 0.7f
    }

    // Actor
    private val maxForceLbl          = Label("", valueLabelStyle)
    private val frequencyHzLbl       = Label("", valueLabelStyle)
    private val dampingRatioLbl      = Label("", valueLabelStyle)
    private val maxForceProgress     = AProgressPractical(screen)
    private val frequencyHzProgress  = AProgressPractical(screen)
    private val dampingRatioProgress = AProgressPractical(screen)



    override fun addActorsOnGroup() {
        super.addActorsOnGroup()

        addLabel(R.string.maxForce, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 1049f, 172f, 42f))
        addLabel(R.string.frequencyHz, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 840f, 222f, 42f))
        addLabel(R.string.dampingRatio, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 631f, 237f, 42f))

        addValueLbls()
        addProgresses()

        collectProgresses()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addValueLbls() {
        addActors(maxForceLbl, frequencyHzLbl, dampingRatioLbl)
        maxForceLbl.setBounds(226f, 1049f, 200f, 42f)
        frequencyHzLbl.setBounds(276f, 840f, 100f, 42f)
        dampingRatioLbl.setBounds(291f, 631f, 100f, 42f)
    }

    private fun addProgresses() {
        addActors(maxForceProgress,frequencyHzProgress,dampingRatioProgress)
        maxForceProgress.setBounds(34f, 962f, 631f, 76f)
        frequencyHzProgress.setBounds(34f, 753f, 631f, 76f)
        dampingRatioProgress.setBounds(34f, 544f, 631f, 76f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun collectProgresses() {
        coroutine?.launch {
            // maxForce
            launch { collectProgress_0_10k(maxForceValue, maxForceProgress, maxForceLbl) { maxForceValue = it } }
            // frequencyHz
            launch { collectProgress_m5_10(frequencyHzValue, frequencyHzProgress, frequencyHzLbl) { frequencyHzValue = it } }
            // dampingRatio
            launch { collectProgress_m5_10(dampingRatioValue, dampingRatioProgress, dampingRatioLbl) { dampingRatioValue = it } }
        }
    }

}