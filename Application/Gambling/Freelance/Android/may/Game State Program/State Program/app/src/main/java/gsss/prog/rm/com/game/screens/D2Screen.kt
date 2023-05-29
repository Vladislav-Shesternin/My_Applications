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
import gsss.prog.rm.com.game.navigationManager
import gsss.prog.rm.com.game.util.Screen
import gsss.prog.rm.com.game.util.Size
import gsss.prog.rm.com.game.util.Layout.List as LL

class D2Screen(val activity: FragmentActivity) : DetailScreen(activity, R.drawable.i2) {

    private val meta      = ImageView(activity).apply { setImageResource(R.drawable.d222222) }
    private val scrollTop = HorizontalScroll(activity)
    private val scrollBot = HorizontalScroll(activity)

    private val listTop = listOf(
        R.drawable.d2_1,
        R.drawable.d2_2,
        R.drawable.d2_3,
        R.drawable.d2_4,
    )
    private val listBot = listOf(
        R.drawable.d2_11,
        R.drawable.d2_12,
        R.drawable.d2_13,
        R.drawable.d2_14,
        R.drawable.d2_15,
        R.drawable.d2_16,
        R.drawable.d2_17,
        R.drawable.d2_18,
        R.drawable.d2_19,
    )

    override fun ViewGroup.addActorsOnStageDetail() {
        addView(meta)
        sizeConverter.setBounds(meta, 59f, 436f, 689f, 828f)

        addTopScroll()
        addBottomScroll()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addTopScroll() {
        addView(scrollTop.stage)

        sizeConverter.apply { setBounds(scrollTop.stage, 62f, 526f, 686f, 379f) }
        scrollTop.initialize(Size(686f, 379f)) {
            var nx = 0f

            listTop.onEach { topI ->
                ImageView(activity).also { img ->
                    img.setImageResource(topI)
                    scrollTop.container.addView(img)
                    img.layoutParams = FrameLayout.LayoutParams(0, 0)
                    scrollTop.sizeConverter.setBounds(img, nx, 0f, 326f, 379f)
                    nx += 326f + 34f
                    img.doOnPreDraw { scrollTop.updateContainer((it.x + it.width).toInt()) }
                }
            }
        }
    }

    private fun ViewGroup.addBottomScroll() {
        addView(scrollBot.stage)

        sizeConverter.apply { setBounds(scrollBot.stage, 62f, 1286f, 686f, 379f) }
        scrollBot.initialize(Size(686f, 379f)) {
            var nx = 0f

            listBot.onEach { botI ->
                ImageView(activity).also { img ->
                    img.setImageResource(botI)
                    scrollBot.container.addView(img)
                    img.layoutParams = FrameLayout.LayoutParams(0, 0)
                    scrollBot.sizeConverter.setBounds(img, nx, 0f, 326f, 379f)
                    nx += 326f + 34f
                    img.doOnPreDraw { scrollBot.updateContainer((it.x + it.width).toInt()) }
                }
            }
        }
    }

}