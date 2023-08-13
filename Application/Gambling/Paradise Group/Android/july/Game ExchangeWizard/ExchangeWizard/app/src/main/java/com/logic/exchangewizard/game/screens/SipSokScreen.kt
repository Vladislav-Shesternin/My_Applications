package com.logic.exchangewizard.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.R
import com.logic.exchangewizard.game.util.Screen
import com.logic.exchangewizard.game.util.numStr

class SipSokScreen(override val activity: MainActivity): Screen(activity) {

    private val timeImg       = ImageView(activity)
    private val inputLbl      = TextView(activity)
    private val resultLbl     = TextView(activity)
    private val inputLbl2      = TextView(activity)
    private val resultLbl2     = TextView(activity)
    private val inputLbl3      = TextView(activity)
    private val resultLbl3     = TextView(activity)
    private val inputLbl4      = TextView(activity)
    private val resultLbl4     = TextView(activity)
    private val inputLbl5      = TextView(activity)
    private val resultLbl5     = TextView(activity)
    private val inputLbl6      = TextView(activity)
    private val resultLbl6     = TextView(activity)


    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.background_one)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addRemoveView()
        addText()
    }

    // game.navigationManager.navigate(TextScreen(activity), BlackScreen(activity))

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addRemoveView() {
        addView(timeImg)

        timeImg.apply {
            setBackgroundResource(R.drawable.time)
            setBounds(29f, 1259f, 56f, 56f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

    private fun ViewGroup.addText() {
        addView(inputLbl)
        addView(resultLbl)
        inputLbl.apply {
            setBounds(27f, 217f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl.apply {
            setBounds(27f, 271f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }

        addView(inputLbl2)
        addView(resultLbl2)
        inputLbl2.apply {
            setBounds(27f, 372f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl2.apply {
            setBounds(27f, 426f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }

        addView(inputLbl3)
        addView(resultLbl3)
        inputLbl3.apply {
            setBounds(27f, 527f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl3.apply {
            setBounds(27f, 581f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }

        addView(inputLbl4)
        addView(resultLbl4)
        inputLbl4.apply {
            setBounds(27f, 682f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl4.apply {
            setBounds(27f, 736f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }

        addView(inputLbl5)
        addView(resultLbl5)
        inputLbl5.apply {
            setBounds(27f, 837f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl5.apply {
            setBounds(27f, 891f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }

        addView(inputLbl6)
        addView(resultLbl6)
        inputLbl6.apply {
            setBounds(27f, 992f, 567f, 44f)
            text = getInput()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl6.apply {
            setBounds(27f, 1046f, 567f, 77f)
            text = getResult()
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun from_1_to_6() = (1..6).random()
    private fun from_1_to_12() = (1..12).random()

    private fun getInput() = "${numStr(0, 9, from_1_to_6())}${randomSimbol()}${numStr(0, 9, from_1_to_6())}${randomSimbol()}${numStr(0, 9, from_1_to_6())}${randomSimbol()}${numStr(0, 9, from_1_to_6())}="
    private fun getResult() = "${numStr(0, 9, from_1_to_12())}.${numStr(0, 9, 3)}"

    private fun randomSimbol() = listOf(
        "()",
        "%",
        "/",
        "7",
        "8",
        "9",
        "*",
        "4",
        "5",
        "6",
        "-",
        "1",
        "2",
        "3",
        "+",
        "+/-",
        "0",
        ".",
        "=",
    ).random()

}