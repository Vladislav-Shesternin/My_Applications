package com.academy.financeacademy.game.screens

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.R
import com.academy.financeacademy.game.actors.VerticalScroll
import com.academy.financeacademy.game.util.Screen

class TransactionsScreen(override val activity: MainActivity): Screen(activity) {

    private val verticalScroll = VerticalScroll(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.transactions)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addVScroll()
        val back = View(activity)
        addView(back)
        back.apply {
            setBounds(15f, 73f, 75f, 75f)
            setOnClickListener { game.navigationManager.back() }
        }
        val homek = View(activity)
        addView(homek)
        homek.apply {
            setBounds(59f, 1163f, 103f, 93f)
            setOnClickListener { game.navigationManager.navigate(HomeScreen(activity)) }
        }
        val stata = View(activity)
        addView(stata)
        stata.apply {
            setBounds(434f, 1163f, 99f, 98f)
            setOnClickListener { game.navigationManager.navigate(WalletScreen(activity), TransactionsScreen(activity)) }
        }
    }

    private fun ViewGroup.addVScroll() {
        addView(verticalScroll.stage)
        verticalScroll.stage.setBounds(40f, 581f, 523f, 571f)
        verticalScroll.show(523f)

        var ny = 0f
        listOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
        ).shuffled().onEach { resID ->
            val img = ImageView(activity).apply {
                setImageResource(resID)
                layoutParams = FrameLayout.LayoutParams(0, 0)
                setBounds(0f, ny, 460f, 80f)
                setOnClickListener { game.navigationManager.navigate(SendMoneyScreen(activity), TransactionsScreen(activity)) }
            }

            verticalScroll.addView(img)
            ny += 80f + 16f
        }

    }

}
















