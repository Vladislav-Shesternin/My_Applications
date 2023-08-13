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

class ConvertationScreen(override val activity: MainActivity): Screen(activity) {

    private val backView    = View(activity)
    private val textusView    = View(activity)
    private val inputLbl      = TextView(activity)
    private val resultLbl     = TextView(activity)

    private val inputFlow = MutableStateFlow("")



    override fun show(parentStage: ConstraintLayout) {
        stage.setBackgroundResource(R.drawable.convertation_layer)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        addRemoveView()
        addText()
        addBtns()

        addView(textusView)
        textusView.apply {
            setBounds(35f, 166f, 623f, 62f)
            setOnClickListener { game.navigationManager.navigate(QvestPistoletosScreen(activity), ConvertationScreen(activity)) }
        }

        asyncCollectInput()
    }

    // game.navigationManager.navigate(TextScreen(activity), BlackScreen(activity))

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addRemoveView() {
        addView(backView)

        backView.apply {
            setBounds(17f, 75f, 57f, 57f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

    private fun ViewGroup.addText() {
        addView(inputLbl)
        addView(resultLbl)
        inputLbl.apply {
            setBounds(35f, 407f, 559f, 44f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
        resultLbl.apply {
            setBounds(35f, 628f, 559f, 44f)

            text = "0"
            gravity = Gravity.END
            setTextColor(ContextCompat.getColor(activity, R.color.oranka))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 25f
        }
    }

    private fun ViewGroup.addBtns() {
        val btns = List(16) { View(activity) }
        var nx = 35f
        var ny = 738f

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

        btns[0].setOnClickListener { inputFlow.run { value += "7" } }
        btns[1].setOnClickListener { inputFlow.run { value += "8" } }
        btns[2].setOnClickListener { inputFlow.run { value += "9" } }
        btns[3].setOnClickListener { inputFlow.run { value = value.dropLast(1) } }
        btns[4].setOnClickListener { inputFlow.run { value += "4" } }
        btns[5].setOnClickListener { inputFlow.run { value += "5" } }
        btns[6].setOnClickListener { inputFlow.run { value += "6" } }
        btns[7].setOnClickListener { inputFlow.value = "0" }
        btns[8].setOnClickListener { inputFlow.run { value += "1" } }
        btns[9].setOnClickListener { inputFlow.run { value += "2" } }
        btns[10].setOnClickListener { inputFlow.run { value += "3" } }
        btns[11].setOnClickListener { inputFlow.run { value += "up" } }
        btns[12].setOnClickListener { inputFlow.run { value += "+/-" } }
        btns[13].setOnClickListener { inputFlow.run { value += "0" } }
        btns[14].setOnClickListener { inputFlow.run { value += "." } }
        btns[15].setOnClickListener { inputFlow.run { value += "down" } }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun from_1_to_9() = (1..9).random()
    private fun from_1_to_5() = (1..5).random()

    @SuppressLint("SetTextI18n")
    private fun asyncCollectInput() {
        coroutine.launch(Dispatchers.Default) {
            inputFlow.collectIndexed { index, value ->
                if (index != 0) withContext(Dispatchers.Main){
                    inputLbl.text = value
                    resultLbl.text = "${numStr(0, 9, from_1_to_9())} ${numStr(0, 9, from_1_to_5())} m*2"
                }
            }
        }
    }

}