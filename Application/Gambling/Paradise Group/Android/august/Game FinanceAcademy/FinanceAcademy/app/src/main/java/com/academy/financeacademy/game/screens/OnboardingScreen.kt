package com.academy.financeacademy.game.screens

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.R
import com.academy.financeacademy.game.util.Screen

class OnboardingScreen(override val activity: MainActivity): Screen(activity) {

    private val finka = ImageView(activity)
    private val cente = ImageView(activity)
    private val prod  = Button(activity)
//    private val verticalScroll = VerticalScroll(activity)

//
//    override fun show(parentStage: ConstraintLayout) {
////        stage.setBackgroundResource(R.drawable.oleglebedev)
//        super.show(parentStage)
//    }

    override fun ViewGroup.addActorsOnStage() {
        addView(finka)
        finka.apply {
            setImageResource(R.drawable.finanse)
            setBounds(207f, 109f, 189f, 42f)
        }

        addView(cente)
        cente.apply {
            setImageResource(R.drawable.o1)
            setBounds(-70f, 204f, 743f, 868f)
        }

        val listok = listOf(R.drawable.o2, R.drawable.o3)
        var iii = 0
        addView(prod)
        prod.apply {
            setBackgroundResource(R.drawable.selector_button)
            setBounds(56f, 1125f, 490f, 86f)
            setOnClickListener {
                if (iii <= listok.lastIndex) {
                    cente.setImageResource(listok[iii])
                } else game.navigationManager.navigate(HomeScreen(activity))
                iii++
            }
        }
    }

}
















