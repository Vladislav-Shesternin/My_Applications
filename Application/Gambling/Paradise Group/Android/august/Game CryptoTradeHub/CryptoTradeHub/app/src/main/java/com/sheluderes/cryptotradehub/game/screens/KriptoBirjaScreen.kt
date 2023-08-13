package com.sheluderes.cryptotradehub.game.screens

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.R
import com.sheluderes.cryptotradehub.game.actors.Box
import com.sheluderes.cryptotradehub.game.util.Screen

class KriptoBirjaScreen(override val activity: MainActivity): Screen(activity) {

    private val www = listOf(
        R.drawable.w1,
        R.drawable.w2,
        R.drawable.w3,
        R.drawable.w4,
        R.drawable.w5,
        R.drawable.w6,
        R.drawable.w7,
    )

    private val deyas = ImageView(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.lakalukas)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addWWW()
        addD()

        val b = View(activity)
        addView(b)
        b.apply {
            setBounds(52f, 98f, 74f, 65f)
            setOnClickListener { game.navigationManager.back() }
        }

        val bv = View(activity)
        addView(bv)
        bv.apply {
            setBounds(26f, 1288f, 627f, 118f)
            setOnClickListener { game.navigationManager.navigate(ChuvstvuyScoroPovishenieYxBlaScreen(activity), KriptoBirjaScreen(activity)) }
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addWWW() {
        addView(deyas)
        deyas.setImageResource(www.first())
        deyas.setBounds(8f, 397f, 514f, 290f)
    }

    private fun ViewGroup.addD() {
        var prevBox: Box? = null

        val h1 = Box(activity, R.drawable.selector_box_1h)
        addView(h1.stage)
        h1.stage.setBounds(60f, 853f, 17f, 28f)
        h1.show(17f)
        h1.apply {
            prevBox = this
            check()

            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
        val h2 = Box(activity, R.drawable.selector_box_2h)
        addView(h2.stage)
        h2.stage.setBounds(166f, 853f, 23f, 28f)
        h2.show(23f)
        h2.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
        val h3 = Box(activity, R.drawable.selector_box_3h)
        addView(h3.stage)
        h3.stage.setBounds(277f, 853f, 19f, 28f)
        h3.show(19f)
        h3.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
        val h4 = Box(activity, R.drawable.selector_box_4h)
        addView(h4.stage)
        h4.stage.setBounds(376f, 853f, 32f, 28f)
        h4.show(32f)
        h4.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
        val h5 = Box(activity, R.drawable.selector_box_5h)
        addView(h5.stage)
        h5.stage.setBounds(492f, 854f, 24f, 28f)
        h5.show(24f)
        h5.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
        val h6 = Box(activity, R.drawable.selector_box_6h)
        addView(h6.stage)
        h6.stage.setBounds(596f, 853f, 26f, 28f)
        h6.show(26f)
        h6.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    deyas.setImageResource(www.random())
                }
            }
        }
    }

}