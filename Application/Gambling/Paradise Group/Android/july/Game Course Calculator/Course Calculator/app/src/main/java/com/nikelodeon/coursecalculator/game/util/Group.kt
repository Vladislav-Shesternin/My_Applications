package com.nikelodeon.coursecalculator.game.util

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentActivity
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.util.cancelCoroutinesAll
import com.nikelodeon.coursecalculator.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Group(open val activity: MainActivity): Disposable {

    val stage by lazy { FrameLayout(activity) }

    val coroutine      = CoroutineScope(Dispatchers.Default)
    val disposableList = mutableListOf<Disposable>()

    var meterUI = 1f
        private set



    fun show(width: Float) {
        with(stage) { doOnPreDraw { if (width > 0 && height > 0) {
            meterUI = width / this.width.toFloat()
            addActorsOnStage()
        } else throw Exception("Перед show() нужно stage добавить и разместить на Screen/Group") } }
    }

    protected abstract fun ViewGroup.addActorsOnStage()


    override fun dispose() {
        cancelCoroutinesAll(coroutine)
        disposableList.disposeAll()
    }

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

    protected fun View.setBounds(layoutData: Layout.LayoutData) {
        with(layoutData) {
            setPosition(x, y)
            setSize(w, h)
        }
    }

}