package com.gs.vest.ask.game.util

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.*
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.game.navigationManager
import com.gs.vest.ask.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Screen(
    private val activity: FragmentActivity,
    val WIDTH   : Float = 500f,
    val HEIGHT  : Float = 1200f,
    val ratio   : String = "1:2.4"
) {

    val name: String = javaClass.name

    val stage: ViewGroup by lazy { FrameLayout(activity) }

    val coroutine = CoroutineScope(Dispatchers.Default)

    val disposableList = mutableListOf<Disposable>()

    lateinit var sizeConverter: SizeConverter


    init {
        onBackPressed()
    }

    open fun show(parentStage: ConstraintLayout) {
        stage.apply {
            id           = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(0, 0)
        }

        parentStage.apply {
            removeAllViews()
            addView(stage)
        }

        ConstraintSet().apply {
            clone(parentStage)

            val stageId       = stage.id
            val parentStageId = parentStage.id

            connect(stageId, ConstraintSet.START, parentStageId, ConstraintSet.START)
            connect(stageId, ConstraintSet.END, parentStageId, ConstraintSet.END)
            connect(stageId, ConstraintSet.TOP, parentStageId, ConstraintSet.TOP)
            connect(stageId, ConstraintSet.BOTTOM, parentStageId, ConstraintSet.BOTTOM)

            setDimensionRatio(stageId, ratio)

            applyTo(parentStage)
        }

        stage.doOnPreDraw {
            sizeConverter = SizeConverter(Size(WIDTH, HEIGHT), Size(stage.width.toFloat(), stage.height.toFloat()))
            stage.addActorsOnStage()
        }
    }

    open fun hide() {
        disposableList.onEach { it.dispose() }
        cancelCoroutinesAll(coroutine)
    }

    private fun onBackPressed() {
        activity.onBackPressedDispatcher.addCallback(activity) { navigationManager.back() }
    }

    protected abstract fun ViewGroup.addActorsOnStage()

}