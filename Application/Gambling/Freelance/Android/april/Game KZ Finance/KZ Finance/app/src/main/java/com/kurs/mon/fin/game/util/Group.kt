package com.kurs.mon.fin.game.util

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Group(
    private val activity: FragmentActivity,
): Disposable {

    val stage: ViewGroup by lazy { FrameLayout(activity) }

    val coroutine = CoroutineScope(Dispatchers.Default)

    val disposableList = mutableListOf<Disposable>()

    lateinit var sizeConverter: SizeConverter


    fun initialize(size: Size, block: () -> Unit = {}) {
        with(stage) {
            doOnPreDraw {
                if (width > 0 && height > 0) {
                    sizeConverter = SizeConverter(size, Size(stage.width.toFloat(), stage.height.toFloat()))
                    addActorsOnStage()
                    block()
                } else throw Exception("Перед initialize нужно добавить stage на screen или group и задать setBounds() потом вызывать этот метод!")
            }
        }
    }

    protected abstract fun ViewGroup.addActorsOnStage()


    override fun dispose() {
        cancelCoroutinesAll(coroutine)
        disposableList.disposeAll()
    }

}