package com.gs.vest.ask.game.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.R
import com.gs.vest.ask.game.util.Screen
import com.gs.vest.ask.game.actors.BondItem
import com.gs.vest.ask.game.util.Size
import com.gs.vest.ask.game.actors.VerticalScroll
import com.gs.vest.ask.game.navigationManager

import com.gs.vest.ask.game.util.data.BondUtil
import kotlinx.coroutines.*
import com.gs.vest.ask.game.util.Layout.Bonds as LB

class BondsScreen(
    val activity: FragmentActivity
) : Screen(activity) {

    private val panelImage  = ImageView(activity)
    private val scroll      = VerticalScroll(activity)


//    private val debugList = listOf<View>(
//        scroll.stage
//    )

    override fun show(parentStage: ConstraintLayout) {
        super.show(parentStage)
        //parent.setBackgroundResource(R.drawable.background)

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }
    }

    override fun ViewGroup.addActorsOnStage() {
        addPanelImage()
        addScroll()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addPanelImage() {
        addView(panelImage)
        panelImage.apply {
            setImageResource(R.drawable.obl_panel)
            sizeConverter.setBounds(this, LB.panel)
        }
    }

    private fun ViewGroup.addScroll() {
        addView(scroll.stage)

        sizeConverter.setBounds(scroll.stage, LB.scroll)


        scroll.apply {
            initialize(LB.scroll.size()) {

                var ny = 0f
                BondUtil.bondList.onEachIndexed { index, bond ->
                    BondItem(activity, bond).apply {
                        container.addView(stage)
                        stage.layoutParams = FrameLayout.LayoutParams(0, 0)
                        scroll.sizeConverter.setBounds(this.stage,0f, ny, 423f, 502f)
                        ny += 502f + 24f
                        initialize(Size(423f, 502f)) {
                            stage.doOnPreDraw { updateContainer((stage.y + stage.height).toInt()) }
                        }

                        block = {
                            navigationManager.navigate(BondScreen(activity, bond, index), BondsScreen(activity))
                        }
                    }
                }
            }
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}