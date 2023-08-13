package com.klubnika.bittracker.game.screens

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.klubnika.bittracker.MainActivity
import com.klubnika.bittracker.R
import com.klubnika.bittracker.game.actors.Box
import com.klubnika.bittracker.game.util.Screen
import com.klubnika.bittracker.game.util.numStr

class ETHandESDTScreen(override val activity: MainActivity): Screen(activity) {

    private val glist = listOf(
        R.drawable.g1,
        R.drawable.g2,
        R.drawable.g3,
        R.drawable.g4,
    )

    private val balanceLbl = TextView(activity)
    private val percentLbl = TextView(activity)
    private val shototaLbl = TextView(activity)
    private val bilisheLbl = TextView(activity)
    private val menesheLbl = TextView(activity)
    private val gImg  = ImageView(activity)
    private val deyas = ImageView(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.ethandesdt)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addText()
        addG()
        addD()

        val b = View(activity)
        addView(b)
        b.apply {
            setBounds(7f, 84f, 98f, 77f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    @SuppressLint("SetTextI18n")
    private fun ViewGroup.addText() {
        addView(balanceLbl)
        addView(percentLbl)
        addView(shototaLbl)
        addView(bilisheLbl)
        addView(menesheLbl)
        balanceLbl.apply {
            setBounds(31f, 182f, 191f, 42f)

            text = "${numStr(1, 90, 1)},${numStr(100, 999,1)}.${numStr(100, 999,1)}"
            gravity = Gravity.START
            setTextColor(ContextCompat.getColor(activity, R.color.black))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        percentLbl.apply {
            setBounds(121f, 224f, 101f, 29f)

            text = "+${numStr(100, 999,1)}.${numStr(0, 9,2)}%"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.gelro))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        shototaLbl.apply {
            setBounds(31f, 224f, 84f, 29f)

            text = "${numStr(1, 9,1)}.${numStr(100, 999,1)}.${numStr(0, 9,2)}"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.herma))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        bilisheLbl.apply {
            setBounds(316f, 224f, 82f, 29f)

            text = "${numStr(1, 9, 1)}.${numStr(100, 999,1)}"
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.black))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
        }
        menesheLbl.apply {
            setBounds(419f, 224f, 87f, 29f)

            text = "${numStr(1, 9, 1)}.${numStr(100, 999,1)}"
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.black))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
        }
    }

    private fun ViewGroup.addG() {
        addView(gImg)
        gImg.setImageResource(glist.first())
        gImg.setBounds(0f, 314f, 555f, 587f)
    }

    private fun ViewGroup.addD() {
        addView(deyas)
        deyas.setImageResource(R.drawable.hollewood)
        deyas.setBounds(31f, 852f, 491f, 31f)

        var prevBox: Box? = null

        val h1 = Box(activity, R.drawable.selector_box_1h)
        addView(h1.stage)
        h1.stage.setBounds(31f, 852f, 23f, 31f)
        h1.show(23f)
        h1.apply {
            prevBox = this
            check()

            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
        val h2 = Box(activity, R.drawable.selector_box_2h)
        addView(h2.stage)
        h2.stage.setBounds(123f, 852f, 24f, 31f)
        h2.show(24f)
        h2.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
        val h3 = Box(activity, R.drawable.selector_box_3h)
        addView(h3.stage)
        h3.stage.setBounds(216f, 852f, 29f, 31f)
        h3.show(29f)
        h3.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
        val h4 = Box(activity, R.drawable.selector_box_4h)
        addView(h4.stage)
        h4.stage.setBounds(315f, 852f, 25f, 31f)
        h4.show(25f)
        h4.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
        val h5 = Box(activity, R.drawable.selector_box_5h)
        addView(h5.stage)
        h5.stage.setBounds(409f, 852f, 21f, 31f)
        h5.show(21f)
        h5.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
        val h6 = Box(activity, R.drawable.selector_box_6h)
        addView(h6.stage)
        h6.stage.setBounds(499f, 852f, 23f, 31f)
        h6.show(23f)
        h6.apply {
            img.setOnClickListener {
                if (isCheck.not()) {
                    prevBox?.uncheck()
                    prevBox = this
                    check()
                    gImg.setImageResource(glist.random())
                }
            }
        }
    }

}