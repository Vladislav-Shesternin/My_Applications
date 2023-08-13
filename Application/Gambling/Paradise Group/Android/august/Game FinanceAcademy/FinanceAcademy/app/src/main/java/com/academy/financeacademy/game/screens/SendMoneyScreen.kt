package com.academy.financeacademy.game.screens

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.R
import com.academy.financeacademy.game.actors.HorizontalScroll
import com.academy.financeacademy.game.util.Screen

class SendMoneyScreen(override val activity: MainActivity): Screen(activity) {

    private val imageTop       = ImageView(activity)
    private val imageBot       = ImageView(activity)
    private val buton          = Button(activity)
    private val horizontalScroll = HorizontalScroll(activity)


//    override fun show(parentStage: ConstraintLayout) {
//        stage.setBackgroundResource(R.drawable.transactions)
//        super.show(parentStage)
//    }

    override fun ViewGroup.addActorsOnStage() {
        addView(imageTop)
        imageTop.apply {
            setImageResource(R.drawable.otpr_dengi)
            setBounds(30f, 58f, 368f, 73f)
        }
        addView(imageBot)
        imageBot.apply {
            setImageResource(R.drawable.profi)
            setBounds(51f, 617f, 497f, 498f)
        }
        val back = View(activity)
        addView(back)
        back.apply {
            setBounds(15f, 73f, 75f, 75f)
            setOnClickListener { game.navigationManager.back() }
        }
        addView(buton)
        buton.apply {
            setBackgroundResource(R.drawable.selector_button)
            setBounds(54f, 1180f, 490f, 86f)
            setOnClickListener { game.navigationManager.back() }
        }
        addHScroll()
    }

    private fun ViewGroup.addHScroll() {
        addView(horizontalScroll.stage)
        horizontalScroll.stage.setBounds(0f, 228f, 603f, 325f)
        horizontalScroll.show(603f)

        var nx = 0f
        listOf(
            R.drawable.ca1,
            R.drawable.ca2,
            R.drawable.ca3,
        ).shuffled().onEach { resID ->
            val img = ImageView(activity).apply {
                setImageResource(resID)
                layoutParams = FrameLayout.LayoutParams(0, 0)
                setBounds(nx, 0f, 522f, 279f)
               // setOnClickListener { game.navigationManager.navigate(KriptoBirjaScreen(activity), OnboardingScreen(activity)) }
            }

            horizontalScroll.addView(img)
            nx += 522f + 24f
        }

    }

}
















