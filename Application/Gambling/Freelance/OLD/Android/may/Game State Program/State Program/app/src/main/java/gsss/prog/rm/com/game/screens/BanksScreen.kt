package gsss.prog.rm.com.game.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import gsss.prog.rm.com.R
import gsss.prog.rm.com.game.actors.VerticalScroll
import gsss.prog.rm.com.game.navigationManager
import gsss.prog.rm.com.game.util.Screen
import gsss.prog.rm.com.game.util.Size
import gsss.prog.rm.com.game.util.Layout.List as LL

var indices = (0..11).shuffled()
class BanksScreen(val activity: FragmentActivity) : Screen(activity) {

    private val scrollV = VerticalScroll(activity)
    private val listD   = listOf(
        R.drawable.i1 to D1Screen(activity),
        R.drawable.i2 to D2Screen(activity),
        R.drawable.i3 to D3Screen(activity),
        R.drawable.i4 to D4Screen(activity),
        R.drawable.i5 to D5Screen(activity),
        R.drawable.i6 to D6Screen(activity),
        R.drawable.i7 to D7Screen(activity),
        R.drawable.i8 to D8Screen(activity),
        R.drawable.i9 to D9Screen(activity),
        R.drawable.i10 to D10Screen(activity),
        R.drawable.i11 to D11Screen(activity),
        R.drawable.i12 to D12Screen(activity),
    )

    override fun show(parentStage: ConstraintLayout) {
        parentStage.setBackgroundResource(R.drawable.backgggr)
        super.show(parentStage)

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }

    }

    override fun ViewGroup.addActorsOnStage() {
        addList()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addList() {
        addView(scrollV.stage)

        sizeConverter.apply { setBounds(scrollV.stage, LL.scroll) }
        scrollV.initialize(LL.scroll.size()) {
            var ny = 0f


            indices.onEach { i ->
                listD[i].also { d ->
                    ImageView(activity).also { img ->
                        img.setImageResource(d.first)
                        scrollV.container.addView(img)
                        img.layoutParams = FrameLayout.LayoutParams(0, 0)
                        scrollV.sizeConverter.setBounds(img, 0f, ny, 661f, 315f)
                        ny += 315f + 50f
                        img.doOnPreDraw { scrollV.updateContainer((it.y + it.height).toInt()) }

                        img.setOnClickListener { navigationManager.navigate(d.second, BanksScreen(activity)) }
                    }
                }
            }
        }
    }

}