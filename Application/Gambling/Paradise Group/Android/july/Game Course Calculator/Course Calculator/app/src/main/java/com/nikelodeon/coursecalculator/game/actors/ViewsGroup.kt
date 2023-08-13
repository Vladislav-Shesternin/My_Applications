package com.nikelodeon.coursecalculator.game.actors

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.R
import com.nikelodeon.coursecalculator.game.util.Group
import com.nikelodeon.coursecalculator.game.util.Layout
import com.nikelodeon.coursecalculator.util.webView.WebViewFragment

const val paste = "https://dskakun914.github.io/Metrixmanager/pmetrixmanager"
const val take  = "https://dskakun914.github.io/Metrixmanager/tmetrixmanager"

class ViewsGroup(override val activity: MainActivity): Group(activity) {

    val vp1 = View(activity)
    val vp2 = View(activity)
    val vt1 = View(activity)
    val vt2 = View(activity)

    val views = listOf(vp1, vp2, vt1, vt2)
    val posis = listOf(
        Layout.LayoutData(352f, 0f, 143f, 32f),
        Layout.LayoutData(0f, 32f, 208f, 32f),
        Layout.LayoutData(232f, 36f, 230f, 32f),
        Layout.LayoutData(0f, 72f, 230f, 32f),
    )
    val bloks = listOf(
        { activity.webViewFragment.OPEN(paste) },
        { activity.webViewFragment.OPEN(paste) },
        { activity.webViewFragment.OPEN(take) },
        { activity.webViewFragment.OPEN(take) },
    )

    override fun ViewGroup.addActorsOnStage() {
//        setBackgroundResource(R.drawable.debug)
        views.onEachIndexed { index, view ->
            addView(view)
            view.setBounds(posis[index])
            view.setOnClickListener { bloks[index]() }
//            view.setBackgroundResource(R.drawable.debug)
        }
    }
}

fun WebViewFragment.OPEN(url: String) {
    MainActivity.webURL = url
    this.showAndOpenUrl()
}