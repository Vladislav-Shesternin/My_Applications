package com.academy.financeacademy.game.screens

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.R
import com.academy.financeacademy.game.actors.VerticalScroll
import com.academy.financeacademy.game.util.Screen

class HomeScreen(override val activity: MainActivity): Screen(activity) {

    private val verticalScroll = VerticalScroll(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.hhhome)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addVScroll()

        val activno = View(activity)
        addView(activno)
        activno.apply {
            setBounds(235f, 1163f, 96f, 93f)
            setOnClickListener { game.navigationManager.navigate(TransactionsScreen(activity), HomeScreen(activity)) }
        }
        val stata = View(activity)
        addView(stata)
        stata.apply {
            setBounds(405f, 1163f, 128f, 98f)
            setOnClickListener { game.navigationManager.navigate(WalletScreen(activity), HomeScreen(activity)) }
        }
        val ratush = View(activity)
        addView(ratush)
        ratush.apply {
            setBounds(48f, 588f, 506f, 213f)
            setOnClickListener { game.navigationManager.navigate(MoneySentScreen(activity), HomeScreen(activity)) }
        }
    }

    private fun ViewGroup.addVScroll() {
        addView(verticalScroll.stage)
        verticalScroll.stage.setBounds(48f, 914f, 507f, 243f)
        verticalScroll.show(507f)

        var ny = 0f
        listOf(
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m3,
        ).shuffled().onEach { resID ->
            val img = ImageView(activity).apply {
                setImageResource(resID)
                layoutParams = FrameLayout.LayoutParams(0, 0)
                setBounds(0f, ny, 506f, 91f)
               // setOnClickListener { game.navigationManager.navigate(KriptoBirjaScreen(activity), OnboardingScreen(activity)) }
            }

            verticalScroll.addView(img)
            ny += 91f + 16f
        }

    }

}
















