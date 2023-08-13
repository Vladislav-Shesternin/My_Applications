package com.logic.exchangewizard.game.screens

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.R
import com.logic.exchangewizard.game.util.Screen
import com.logic.exchangewizard.game.util.numStr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LinearScreen(override val activity: MainActivity): Screen(activity) {

    private val removeView    = View(activity)
    private val timekaView    = View(activity)
    private val linearView    = View(activity)
    private val inputLbl      = TextView(activity)
    private val resultLbl     = TextView(activity)

    private val inputFlow = MutableStateFlow("")



    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.linear_flow)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addRemoveView()
        addText()
        addBtns()

        asyncCollectInput()
    }

    // game.navigationManager.navigate(TextScreen(activity), BlackScreen(activity))

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addRemoveView() {
        addView(removeView)

        removeView.apply {
            setBounds(523f, 521f, 72f, 42f)
            setOnClickListener { inputFlow.run { value = value.dropLast(1)} }
        }

        addView(timekaView)

        timekaView.apply {
            setBounds(29f, 513f, 56f, 56f)
            setOnClickListener { game.navigationManager.navigate(SipSokScreen(activity), LinearScreen(activity)) }
        }

        addView(linearView)

        linearView.apply {
            setBounds(113f, 500f, 84f, 84f)
            setOnClickListener { game.navigationManager.navigate(ConvertationScreen(activity), LinearScreen(activity)) }
        }
    }

    private fun ViewGroup.addText() {
        addView(inputLbl)
        addView(resultLbl)
        inputLbl.apply {
            setBounds(30f, 217f, 564f, 44f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl.apply {
            setBounds(37f, 271f, 557f, 77f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-SemiBold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
    }

    private fun ViewGroup.addBtns() {
        val btns = List(20) { View(activity) }
        var nx = 35f
        var ny = 633f

        btns.onEachIndexed { index, view ->
            addView(view)
            view.setBounds(nx, ny, 130f, 130f)
            nx += 144f
            if (index.inc() % 4 == 0) {
                nx = 35f
                ny += 149f
            }
//            view.setBackgroundResource(R.drawable.debug)
        }

        btns[0].setOnClickListener { inputFlow.value = "0" }
        btns[1].setOnClickListener { inputFlow.run { value += "()" } }
        btns[2].setOnClickListener { inputFlow.run { value += "%" } }
        btns[3].setOnClickListener { inputFlow.run { value += "/" } }
        btns[4].setOnClickListener { inputFlow.run { value += "7" } }
        btns[5].setOnClickListener { inputFlow.run { value += "8" } }
        btns[6].setOnClickListener { inputFlow.run { value += "9" } }
        btns[7].setOnClickListener { inputFlow.run { value += "*" } }
        btns[8].setOnClickListener { inputFlow.run { value += "4" } }
        btns[9].setOnClickListener { inputFlow.run { value += "5" } }
        btns[10].setOnClickListener { inputFlow.run { value += "6" } }
        btns[11].setOnClickListener { inputFlow.run { value += "-" } }
        btns[12].setOnClickListener { inputFlow.run { value += "1" } }
        btns[13].setOnClickListener { inputFlow.run { value += "2" } }
        btns[14].setOnClickListener { inputFlow.run { value += "3" } }
        btns[15].setOnClickListener { inputFlow.run { value += "+" } }
        btns[16].setOnClickListener { inputFlow.run { value += "+/-" } }
        btns[17].setOnClickListener { inputFlow.run { value += "0" } }
        btns[18].setOnClickListener { inputFlow.run { value += "." } }
        btns[19].setOnClickListener { inputFlow.run { value += "=" } }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun from_1_to_9() = (1..9).random()

    @SuppressLint("SetTextI18n")
    private fun asyncCollectInput() {
        coroutine.launch(Dispatchers.Default) {
            inputFlow.collectIndexed { index, value ->
                if (index != 0) withContext(Dispatchers.Main){
                    inputLbl.text = value
                    resultLbl.text = "${numStr(0, 9, from_1_to_9())}"
                }
            }
        }
    }

}