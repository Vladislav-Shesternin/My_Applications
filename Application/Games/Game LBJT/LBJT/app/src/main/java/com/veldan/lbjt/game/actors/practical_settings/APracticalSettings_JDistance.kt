package com.veldan.lbjt.game.actors.practical_settings

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.progress.AProgressPractical
import com.veldan.lbjt.game.utils.Layout
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.launch

class APracticalSettings_JDistance(_screen: AdvancedScreen): AAbstractPracticalSettings(_screen) {

    companion object {
        var lengthValue      : Float = 10f
        var frequencyHzValue : Float = 0.5f
        var dampingRatioValue: Float = 0.2f
    }

    // Actor
    private val lengthLbl            = Label("", valueLabelStyle)
    private val frequencyHzLbl       = Label("", valueLabelStyle)
    private val dampingRatioLbl      = Label("", valueLabelStyle)
    private val lengthProgress       = AProgressPractical(screen)
    private val frequencyHzProgress  = AProgressPractical(screen)
    private val dampingRatioProgress = AProgressPractical(screen)


    override fun addActorsOnGroup() {
        super.addActorsOnGroup()

        addLabel(R.string.length, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 763f, 114f, 42f))
        addLabel(R.string.frequencyHz, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 554f, 222f, 42f))
        addLabel(R.string.dampingRatio, Static.LabelFont.Inter_Regular_35, Layout.LayoutData(34f, 345f, 237f, 42f))

        addValueLbls()
        addProgresses()
        addPoints()
        addValues()

        collectProgresses()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addValueLbls() {
        addActors(lengthLbl, frequencyHzLbl, dampingRatioLbl)
        lengthLbl.setBounds(168f, 763f, 100f, 42f)
        frequencyHzLbl.setBounds(276f, 554f, 100f, 42f)
        dampingRatioLbl.setBounds(291f, 345f, 100f, 42f)
    }

    private fun addProgresses() {
        addActors(lengthProgress,frequencyHzProgress,dampingRatioProgress)
        lengthProgress.setBounds(34f, 676f, 631f, 76f)
        frequencyHzProgress.setBounds(34f, 467f, 631f, 76f)
        dampingRatioProgress.setBounds(34f, 258f, 631f, 76f)
    }

    private fun addPoints() {
        // lengthX
        listOf(188f, 347f, 507f, 660f).onEach { addPointImg(it, 700f) }
        // frequencyX | dampingX
        listOf(221f, 442f, 660f).onEach {
            addPointImg(it, 491f)
            addPointImg(it, 282f)
        }
    }

    private fun addValues() {
        val listLengthX     = listOf(34f, 179f, 329f, 489f, 643f)
        val listLengthValue = listOf("0", "5.0", "10.0", "15.0", "20")

        listLengthValue.onEachIndexed { index, value -> addLabelValue(value, listLengthX[index], 676f) }

        val listFrDmX     = listOf(34f, 209f, 429f, 643f)
        val listFrDmValue = listOf("0", "1.0", "5.0", "10")

        listFrDmValue.onEachIndexed { index, value ->
            addLabelValue(value, listFrDmX[index], 467f)
            addLabelValue(value, listFrDmX[index], 258f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun collectProgresses() {
        coroutine?.launch {
            // maxForce
            launch { collectProgress_0_20(lengthValue, lengthProgress, lengthLbl) { lengthValue = it } }
            // frequencyHz
            launch { collectProgress_0_10(frequencyHzValue, frequencyHzProgress, frequencyHzLbl) { frequencyHzValue = it } }
            // dampingRatio
            launch { collectProgress_0_10(dampingRatioValue, dampingRatioProgress, dampingRatioLbl) { dampingRatioValue = it } }
        }
    }

}