package com.nikelodeon.coursecalculator.game.screens

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.R
import com.nikelodeon.coursecalculator.game.util.Screen
import com.nikelodeon.coursecalculator.game.util.numStr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlackScreen(override val activity: MainActivity): Screen(activity) {

    private val menuImg   = ImageView(activity)
    private val numberImg = ImageView(activity)
    private val inputLbl  = TextView(activity)
    private val resultLbl = TextView(activity)

    private val inputFlow = MutableStateFlow("")


    override fun ViewGroup.addActorsOnStage() {
        addMenuImg()
        addNumberImg()
        addText()
        addBtns()

        asyncCollectInput()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addMenuImg() {
        addView(menuImg)

        menuImg.apply {
            setImageResource(R.drawable.white_trip)
            setBounds(574f, 68f, 40f, 40f)
            setOnClickListener { game.navigationManager.navigate(TextScreen(activity), BlackScreen(activity)) }
        }
    }

    private fun ViewGroup.addNumberImg() {
        addView(numberImg)

        numberImg.apply {
            setImageResource(R.drawable.black_number)
            setBounds(34f, 596f, 567f, 710f)

        }
    }

    private fun ViewGroup.addText() {
        addView(inputLbl)
        addView(resultLbl)
        inputLbl.apply {
            setBounds(34f, 433f, 567f, 34f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.gray))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 20f
        }
        resultLbl.apply {
            setBounds(34f, 477f, 572f, 75f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 20f
        }
    }

    private fun ViewGroup.addBtns() {
        val btns = List(20) { View(activity) }
        var nx = 34f
        var ny = 596f

        btns.onEachIndexed { index, view ->
            addView(view)
            view.setBounds(nx, ny, 139f, 139f)
            nx += 143f
            if (index.inc() % 4 == 0) {
                nx = 34f
                ny += 143f
            }
        }

        btns[0].setOnClickListener { inputFlow.value = "0" }
        btns[1].setOnClickListener { inputFlow.run { value = value.dropLast(1)} }
        btns[2].setOnClickListener { inputFlow.run { value += "%" } }
        btns[3].setOnClickListener { inputFlow.run { value += "<" } }
        btns[4].setOnClickListener { inputFlow.run { value += "7" } }
        btns[5].setOnClickListener { inputFlow.run { value += "8" } }
        btns[6].setOnClickListener { inputFlow.run { value += "9" } }
        btns[7].setOnClickListener { inputFlow.run { value += ":" } }
        btns[8].setOnClickListener { inputFlow.run { value += "4" } }
        btns[9].setOnClickListener { inputFlow.run { value += "5" } }
        btns[10].setOnClickListener { inputFlow.run { value += "6" } }
        btns[11].setOnClickListener { inputFlow.run { value += "*" } }
        btns[12].setOnClickListener { inputFlow.run { value += "1" } }
        btns[13].setOnClickListener { inputFlow.run { value += "2" } }
        btns[14].setOnClickListener { inputFlow.run { value += "3" } }
        btns[15].setOnClickListener { inputFlow.run { value += "-" } }
        btns[16].setOnClickListener { inputFlow.run { value += "0" } }
        btns[17].setOnClickListener { inputFlow.run { value += "." } }
        btns[18].setOnClickListener { inputFlow.run { value += "=" } }
        btns[19].setOnClickListener { inputFlow.run { value += "+" } }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun from_1_to_6() = (1..6).random()

    @SuppressLint("SetTextI18n")
    private fun asyncCollectInput() {
        coroutine.launch(Dispatchers.Default) {
            inputFlow.collectIndexed { index, value ->
                if (index != 0) withContext(Dispatchers.Main){
                    inputLbl.text = value
                    resultLbl.text = "=${numStr(0, 9, from_1_to_6())}"
                }
            }
        }
    }

}