package com.karpenkov.budgetgid.game.utils.advanced

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.doOnPreDraw
import com.karpenkov.budgetgid.MainActivity
import com.karpenkov.budgetgid.game.utils.Size
import com.karpenkov.budgetgid.game.utils.sizeConverter.AndroidSizeConverter
import kotlinx.coroutines.*

abstract class AdvancedAndroidScreen(
    val WIDTH: Float = 763f,
    val HEIGHT: Float = 1589f,
    val ratio: String = "1:2.08256"
) {
    abstract val activity: MainActivity

    val name: String = javaClass.name

    val stage: ViewGroup by lazy { FrameLayout(activity) }

    val coroutine = CoroutineScope(Dispatchers.Main)
    lateinit var sizeConverter: AndroidSizeConverter

    val parentStage by lazy { MainActivity.binding.rootLayout }

    open fun show() {
        coroutine.launch {
            stage.apply {
                id = View.generateViewId()
                layoutParams = ViewGroup.LayoutParams(0, 0)
            }
            parentStage.apply {
                removeAllViews()
                addView(stage)
            }

            ConstraintSet().apply {
                clone(parentStage)

                val stageId = stage.id
                val parentStageId = parentStage.id

                connect(stageId, ConstraintSet.START, parentStageId, ConstraintSet.START)
                connect(stageId, ConstraintSet.END, parentStageId, ConstraintSet.END)
                connect(stageId, ConstraintSet.TOP, parentStageId, ConstraintSet.TOP)
                connect(stageId, ConstraintSet.BOTTOM, parentStageId, ConstraintSet.BOTTOM)

                setDimensionRatio(stageId, ratio)

                applyTo(parentStage)
            }

            stage.doOnPreDraw {
                sizeConverter = AndroidSizeConverter(
                    Size(WIDTH, HEIGHT),
                    Size(stage.width.toFloat(), stage.height.toFloat())
                )
                stage.addActorsOnStage()
            }
        }
    }

    open fun dispose() {
        coroutine.launch {
            parentStage.removeAllViews()
            coroutine.cancel()
        }
    }
    protected abstract fun ViewGroup.addActorsOnStage()

}