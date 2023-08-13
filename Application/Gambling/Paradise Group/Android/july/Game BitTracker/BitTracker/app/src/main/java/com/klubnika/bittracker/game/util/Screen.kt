package com.klubnika.bittracker.game.util

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.*
import com.klubnika.bittracker.MainActivity
import com.klubnika.bittracker.game.GameFragment
import com.klubnika.bittracker.util.cancelCoroutinesAll
import com.klubnika.bittracker.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Screen(
    open val activity: MainActivity,
    val WIDTH   : Float  = WIDTH_UI,
    val HEIGHT  : Float  = HEIGHT_UI,
    val ratio   : String = "1:${HEIGHT/WIDTH}"
) {

    val name: String = javaClass.name

    val stage by lazy { FrameLayout(activity) }

    val coroutine      = CoroutineScope(Dispatchers.Default)
    val disposableList = mutableListOf<Disposable>()

    lateinit var game         : GameFragment
    lateinit var parentStage  : ConstraintLayout

    var meterUI = 1f
        private set


    open fun show(parentStage: ConstraintLayout) {
        activity.webViewFragment.backBlock = { game.navigationManager.back() }

        this.parentStage = parentStage

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
            meterUI = WIDTH / it.width.toFloat()
            stage.addActorsOnStage()
        }
    }

    open fun hide() {
        disposableList.onEach { it.dispose() }
        cancelCoroutinesAll(coroutine)
    }

    protected abstract fun ViewGroup.addActorsOnStage()

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    protected fun View.setPosition(x: Float, y: Float) {
        this.x = x / meterUI
        this.y = y / meterUI
    }

    protected fun View.setSize(width: Float, height: Float) {
        updateLayoutParams {
            this.width  = (width / meterUI).toInt()
            this.height = (height / meterUI).toInt()
        }
    }

    protected fun View.setBounds(x: Float, y: Float, width: Float, height: Float) {
        setPosition(x, y)
        setSize(width, height)
    }

}