package gsss.prog.rm.com.game.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.size
import androidx.fragment.app.FragmentActivity
import gsss.prog.rm.com.R
import gsss.prog.rm.com.game.actors.VerticalScroll
import gsss.prog.rm.com.game.util.Screen
import gsss.prog.rm.com.game.util.Size
import gsss.prog.rm.com.game.util.Layout.List as LL

class D10Screen(val activity: FragmentActivity) : DetailScreen(activity, R.drawable.i10) {

    private val imgs = ImageView(activity).apply { setImageResource(R.drawable.d10_palanezza) }

    override fun ViewGroup.addActorsOnStageDetail() {
        addView(imgs)
        sizeConverter.setBounds(imgs, 47f, 403f, 711f, 1176f)
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

}