package com.sheluderes.cryptotradehub.game.actors

import android.view.View
import android.view.ViewGroup
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.game.util.Group
import com.sheluderes.cryptotradehub.game.util.Layout
import com.sheluderes.cryptotradehub.util.webView.WebViewFragment

class SalidolGroup(override val activity: MainActivity): Group(activity) {

    companion object {
        const val pardone = "https://dskakun914.github.io/Metrixmanager/pmetrixmanager"
        const val tapoher  = "https://dskakun914.github.io/Metrixmanager/tmetrixmanager"

        fun WebViewFragment.goWEB(url: String) {
            MainActivity.webURL = url
            this.showAndOpenUrl()
        }
    }

    val vp1 = View(activity)
    val vp2 = View(activity)
    val vt1 = View(activity)
    val vt2 = View(activity)

    val views = listOf(vp1, vp2, vt1, vt2)
    val posis = listOf(
        Layout.LayoutData(395f, 972f, 109f, 23f),
        Layout.LayoutData(77f, 997f, 186f, 23f),
        Layout.LayoutData(286f, 997f, 205f, 23f),
        Layout.LayoutData(77f, 1026f, 189f, 23f),
    )
    val bloks = listOf(
        { activity.webViewFragment.goWEB(pardone) },
        { activity.webViewFragment.goWEB(pardone) },
        { activity.webViewFragment.goWEB(tapoher) },
        { activity.webViewFragment.goWEB(tapoher) },
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