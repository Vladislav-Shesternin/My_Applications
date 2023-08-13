package com.academy.financeacademy.game.actors

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.game.util.Group

class HorizontalScroll(override val activity: MainActivity): Group(activity) {

    private val scroll       = HorizontalScrollView(activity)
    private val frameLayout  = FrameLayout(activity)

    private val container = FrameLayout(activity)



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

    fun addView(view: View) {
        container.addView(view)
        view.doOnPreDraw {
            container.layoutParams = FrameLayout.LayoutParams(
                (it.x + it.width).toInt(),
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

}