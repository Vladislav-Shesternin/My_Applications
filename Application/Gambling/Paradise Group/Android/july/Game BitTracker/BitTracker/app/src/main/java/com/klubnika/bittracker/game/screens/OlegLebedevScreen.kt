package com.klubnika.bittracker.game.screens

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.klubnika.bittracker.MainActivity
import com.klubnika.bittracker.R
import com.klubnika.bittracker.game.actors.VerticalScroll
import com.klubnika.bittracker.game.manager.NavigationManager
import com.klubnika.bittracker.game.util.Screen
import com.klubnika.bittracker.game.util.numStr

class OlegLebedevScreen(override val activity: MainActivity): Screen(activity) {

    private val balanceLbl = TextView(activity)
    private val percentLbl = TextView(activity)
    private val shototaLbl = TextView(activity)
    private val verticalScroll = VerticalScroll(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.oleglebedev)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addText()
        addVScroll()
        addNav()

        val b = View(activity)
        addView(b)
        b.apply {
            setBounds(30f, 334f, 493f, 100f)
            setOnClickListener { game.navigationManager.navigate(TelephoneSetScreen(activity), OlegLebedevScreen(activity)) }
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
        balanceLbl.apply {
            setBounds(31f, 229f, 303f, 62f)

            text = "${numStr(1, 100, 1)},${numStr(100, 999,1)}.${numStr(100, 999,1)}"
            gravity = Gravity.START
            setTextColor(ContextCompat.getColor(activity, R.color.black))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        percentLbl.apply {
            setBounds(361f, 217f, 163f, 29f)

            text = "${numStr(100, 999,1)}.${numStr(0, 9,2)}%"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.gelro))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        shototaLbl.apply {
            setBounds(361f, 246f, 163f, 29f)

            text = "${numStr(1, 99,1)}.${numStr(100, 999,1)}.${numStr(0, 9,2)}"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.herma))
            typeface = Typeface.createFromAsset(activity.assets, "Inter-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
    }

    private fun ViewGroup.addVScroll() {
        addView(verticalScroll.stage)
        verticalScroll.stage.setBounds(31f, 820f, 493f, 302f)
        verticalScroll.show(493f)

        var ny = 0f
        listOf(
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6,
            R.drawable.i7,
            R.drawable.i8,
            R.drawable.i9,
            R.drawable.i10,
            R.drawable.i11,
            R.drawable.i12,
            R.drawable.i13,
            R.drawable.i14,
            R.drawable.i15,
        ).onEach { resID ->
            val img = ImageView(activity).apply {
                setImageResource(resID)
                layoutParams = FrameLayout.LayoutParams(0, 0)
                setBounds(0f, ny, 493f, 57f)
            }

            verticalScroll.addView(img)
            ny += 57f + 26f
        }

    }

    private fun ViewGroup.addNav() {
        val vina = View(activity)
        addView(vina)
        vina.apply {
            setBounds(0f, 1122f, 555f, 111f)
            setOnClickListener { game.navigationManager.navigate(ETHandESDTScreen(activity), OlegLebedevScreen(activity)) }
        }
    }

}
















