package com.klubnika.bittracker.game.actors

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.core.view.doOnPreDraw
import com.klubnika.bittracker.MainActivity
import com.klubnika.bittracker.game.util.Group

class VerticalScroll(override val activity: MainActivity): Group(activity) {

    private val scroll       = ScrollView(activity)
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
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            addContainer()
        }
    }

    private fun ViewGroup.addContainer() {
        addView(container)

        container.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0
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
                ViewGroup.LayoutParams.MATCH_PARENT,
                (it.y + it.height).toInt()
            )
        }
    }

}