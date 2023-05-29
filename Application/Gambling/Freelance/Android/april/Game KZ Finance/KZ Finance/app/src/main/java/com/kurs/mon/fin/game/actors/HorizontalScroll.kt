package com.kurs.mon.fin.game.actors

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.game.util.Group

class HorizontalScroll(val activity: FragmentActivity): Group(activity) {

    private val scroll       = HorizontalScrollView(activity)
    private val frameLayout  = FrameLayout(activity)

    val container = FrameLayout(activity)



    override fun ViewGroup.addActorsOnStage() {
        addScroll()
    }

    // ---------------------------------------------------
    // Add View
    // ---------------------------------------------------

    private fun ViewGroup.addScroll() {
        addView(scroll)

        scroll.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addFrameLayout()
        }
    }

    private fun ViewGroup.addFrameLayout() {
        addView(frameLayout)

        frameLayout.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addContainer()
        }
    }

    private fun ViewGroup.addContainer() {
        addView(container)

        container.apply {
            layoutParams = FrameLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )

        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateContainer(width: Int) {
        container.layoutParams = FrameLayout.LayoutParams(
            width,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
    }

}