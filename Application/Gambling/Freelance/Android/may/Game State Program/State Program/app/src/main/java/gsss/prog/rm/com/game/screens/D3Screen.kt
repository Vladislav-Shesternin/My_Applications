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

class D3Screen(val activity: FragmentActivity) : DetailScreen(activity, R.drawable.i3) {

    private val imgs = ImageView(activity).apply { setImageResource(R.drawable.d3_panella) }

    override fun ViewGroup.addActorsOnStageDetail() {
        addView(imgs)
        sizeConverter.setBounds(imgs, 43f, 471f, 718f, 1138f)
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

}