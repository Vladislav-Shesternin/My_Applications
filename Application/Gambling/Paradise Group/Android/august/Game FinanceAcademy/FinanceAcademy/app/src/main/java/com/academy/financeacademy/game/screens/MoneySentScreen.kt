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

class MoneySentScreen(override val activity: MainActivity): Screen(activity) {

    private val imageTop       = ImageView(activity)


    override fun ViewGroup.addActorsOnStage() {
        addView(imageTop)
        imageTop.apply {
            setImageResource(R.drawable.melani_yord)
            setBounds(0f, 168f, 603f, 1137f)
        }

        val back = View(activity)
        addView(back)
        back.apply {

            setBounds(0f, 152f, 180f, 180f)
            setOnClickListener { game.navigationManager.back() }
        }

    }

}
















