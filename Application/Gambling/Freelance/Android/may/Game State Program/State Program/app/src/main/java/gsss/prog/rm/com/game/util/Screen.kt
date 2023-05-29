package gsss.prog.rm.com.game.util

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.*
import androidx.fragment.app.FragmentActivity
import gsss.prog.rm.com.game.navigationManager
import gsss.prog.rm.com.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Screen(
    private val activity: FragmentActivity,
    val WIDTH   : Float = 805f,
    val HEIGHT  : Float = 1708f,
    val ratio   : String = "1:2.121739"
) {

    val name: String = javaClass.name

    val stage: ViewGroup by lazy { FrameLayout(activity) }

    val coroutine = CoroutineScope(Dispatchers.Default)

    val disposableList = mutableListOf<Disposable>()

    lateinit var sizeConverter: SizeConverter

    lateinit var parentStage: ConstraintLayout

    init {
        onBackPressed()
    }

    open fun show(parentStage: ConstraintLayout) {
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