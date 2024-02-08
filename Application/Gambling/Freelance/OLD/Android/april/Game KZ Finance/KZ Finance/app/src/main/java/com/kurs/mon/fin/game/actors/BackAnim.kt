package com.kurs.mon.fin.game.actors

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.R
import com.kurs.mon.fin.game.util.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BackAnim(val activity: FragmentActivity): Group(activity) {

    private val topImage    = ImageView(activity)
    private val bottomImage = ImageView(activity)

    override fun ViewGroup.addActorsOnStage() {
        addTop()
        addBottom()

        startAnim()
    }

    // ---------------------------------------------------
    // Add View
    // ---------------------------------------------------

    private fun ViewGroup.addTop() {
        addView(topImage)

        topImage.apply {
            setImageResource(R.drawable.lines)
            sizeConverter.setBounds(this, -221f, -1302f, 1021f, 1847f)
        }
    }

    private fun ViewGroup.addBottom() {
        addView(bottomImage)

        bottomImage.apply {
            setImageResource(R.drawable.lines)
            sizeConverter.setBounds(this, -221f, 865f, 1021f, 1847f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun startAnim() {
        coroutine.launch(Dispatchers.Main) {
            var time = 0L
            MutableStateFlow(0).also { flow ->
                flow.collect {
                    time = getRandomTime()
                    topImage.animate().apply {
                        translationY(sizeConverter.getSizeY(865f))
                        duration = time
                        withEndAction {
                            topImage.animate().apply {
                                translationY(sizeConverter.getSizeY(-1302f))
                                duration = time
                                withEndAction { flow.value++ }
                            }
                        }
                    }
                }
            }
        }
        coroutine.launch(Dispatchers.Main) {
            var time = 0L
            MutableStateFlow(0).also { flow ->
                flow.collect {
                    time = getRandomTime()
                    bottomImage.animate().apply {
                        translationY(sizeConverter.getSizeY(-1302f))
                        duration = time
                        withEndAction {
                            bottomImage.animate().apply {
                                translationY(sizeConverter.getSizeY(865f))
                                duration = time
                                withEndAction { flow.value++ }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRandomTime() = (5..10).shuffled().first() * 1000L

}