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

class WalletScreen(override val activity: MainActivity): Screen(activity) {

    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.wwwallet)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
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
        val activno = View(activity)
        addView(activno)
        activno.apply {
            setBounds(242f, 1163f, 96f, 93f)
            setOnClickListener { game.navigationManager.navigate(TransactionsScreen(activity), WalletScreen(activity)) }
        }
    }

}
















