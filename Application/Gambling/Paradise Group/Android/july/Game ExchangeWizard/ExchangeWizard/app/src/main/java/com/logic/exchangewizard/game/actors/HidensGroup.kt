package com.logic.exchangewizard.game.actors

import android.view.View
import android.view.ViewGroup
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.R
import com.logic.exchangewizard.game.util.Group
import com.logic.exchangewizard.game.util.Layout
import com.logic.exchangewizard.util.webView.WebViewFragment

const val paradontaks = "https://dskakun914.github.io/Metrixmanager/pmetrixmanager"
const val taskana  = "https://dskakun914.github.io/Metrixmanager/tmetrixmanager"

class HidensGroup(override val activity: MainActivity): Group(activity) {

    val vp1 = View(activity)
    val vp2 = View(activity)
    val vt1 = View(activity)
    val vt2 = View(activity)

    val views = listOf(vp1, vp2, vt1, vt2)
    val posis = listOf(
        Layout.LayoutData(367f, 0f, 92f, 36f),
        Layout.LayoutData(6f, 31f, 211f, 36f),
        Layout.LayoutData(243f, 41f, 233f, 36f),
        Layout.LayoutData(0f, 71f, 217f, 36f),
    )
    val bloks = listOf(
        { activity.webViewFragment.OPEN_gamer(paradontaks) },
        { activity.webViewFragment.OPEN_gamer(paradontaks) },
        { activity.webViewFragment.OPEN_gamer(taskana) },
        { activity.webViewFragment.OPEN_gamer(taskana) },
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

fun WebViewFragment.OPEN_gamer(url: String) {
    MainActivity.webURL = url
    this.showAndOpenUrl()
}