package com.sheluderes.cryptotradehub.game.screens

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.R
import com.sheluderes.cryptotradehub.game.actors.VerticalScroll
import com.sheluderes.cryptotradehub.game.util.Screen

class PashloVseDoDomuScreen(override val activity: MainActivity): Screen(activity) {

    private val spiska = ImageView(activity)
    private val manika = ImageView(activity)
    private val verticalScroll = VerticalScroll(activity)


    override fun show(parentStage: ConstraintLayout) {
//        stage.setBackgroundResource(R.drawable.oleglebedev)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addView(spiska)
        spiska.apply {
            setImageResource(R.drawable.pirochenzas)
            setBounds(40f, 161f, 600f, 645f)
        }

        addVScroll()

        addView(manika)
        manika.apply {
            setImageResource(R.drawable.manka)
            setBounds(0f, 1263f, 680f, 208f)
        }


        val b = View(activity)
        addView(b)
        b.apply {
            setBounds(295f, 1280f, 354f, 159f)
            setOnClickListener { game.navigationManager.navigate(OsnovaScreen(activity), PashloVseDoDomuScreen(activity)) }
        }

        val bv = View(activity)
        addView(bv)
        bv.apply {
            setBounds(15f, 1280f, 241f, 159f)
            setOnClickListener { game.navigationManager.navigate(ChuvstvuyScoroPovishenieYxBlaScreen(activity), PashloVseDoDomuScreen(activity)) }
        }
    }

    private fun ViewGroup.addVScroll() {
        addView(verticalScroll.stage)
        verticalScroll.stage.setBounds(61f, 835f, 580f, 443f)
        verticalScroll.show(580f)

        var ny = 0f
        listOf(
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
            R.drawable.wa,
            R.drawable.wb,
            R.drawable.wc,
            R.drawable.wd,
        ).shuffled().onEach { resID ->
            val img = ImageView(activity).apply {
                setImageResource(resID)
                layoutParams = FrameLayout.LayoutParams(0, 0)
                setBounds(0f, ny, 578f, 244f)
                setOnClickListener { game.navigationManager.navigate(KriptoBirjaScreen(activity), PashloVseDoDomuScreen(activity)) }
            }

            verticalScroll.addView(img)
            ny += 244f + 25f
        }

    }

}
















