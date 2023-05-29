package gsss.prog.rm.com.game.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.size
import androidx.fragment.app.FragmentActivity
import gsss.prog.rm.com.R
import gsss.prog.rm.com.game.actors.HorizontalScroll
import gsss.prog.rm.com.game.actors.VerticalScroll
import gsss.prog.rm.com.game.util.Screen
import gsss.prog.rm.com.game.util.Size
import gsss.prog.rm.com.game.util.Layout.List as LL

class D8Screen(val activity: FragmentActivity) : DetailScreen(activity, R.drawable.i8) {

    private val imgs = ImageView(activity).apply { setImageResource(R.drawable.d8_paenlla) }

    private val scroll = HorizontalScroll(activity)

    private val list = listOf(
        R.drawable.d8_1,
        R.drawable.d8_2,
        R.drawable.d8_3,
        R.drawable.d8_4,
        R.drawable.d8_5,
        R.drawable.d8_6,
    )
    override fun ViewGroup.addActorsOnStageDetail() {
        addView(imgs)
        sizeConverter.setBounds(imgs, 0f, 502f, 805f, 483f)

        addScroll()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addScroll() {
        addView(scroll.stage)

        sizeConverter.apply { setBounds(scroll.stage, 59f, 1084f, 706f, 380f) }
        scroll.initialize(Size(706f, 380f)) {
            var nx = 0f

            list.onEach { ddd ->
                ImageView(activity).also { img ->
                    img.setImageResource(ddd)
                    scroll.container.addView(img)
                    img.layoutParams = FrameLayout.LayoutParams(0, 0)
                    scroll.sizeConverter.setBounds(img, nx, 0f, 208f, 380f)
                    nx += 208f + 41f
                    img.doOnPreDraw { scroll.updateContainer((it.x + it.width).toInt()) }
                }
            }
        }
    }

}