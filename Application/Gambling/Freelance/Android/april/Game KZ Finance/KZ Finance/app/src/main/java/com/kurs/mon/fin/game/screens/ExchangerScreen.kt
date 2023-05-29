package com.kurs.mon.fin.game.screens

import android.graphics.Typeface
import android.os.Build
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.R
import com.kurs.mon.fin.game.actors.BackAnim
import com.kurs.mon.fin.game.actors.CurrencyScroll
import com.kurs.mon.fin.game.util.Screen
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kurs.mon.fin.game.util.Layout.Exchanger as LE

class ExchangerScreen(
    val activity: FragmentActivity
) : Screen(activity) {

    companion object {
        var currenciesMap: Map<String, Double>? = null
    }

    private val backAnim        = BackAnim(activity)
    private val panelImage      = ImageView(activity)
    private val leftInputImage  = ImageView(activity)
    private val rightInputImage = ImageView(activity)
    private val currenciesImage = ImageView(activity)
    private val currencyScroll  = CurrencyScroll(activity, currenciesMap ?: mapOf())
    private val currencyText    = TextView(activity)
    private val leftEdit        = EditText(activity)
    private val rightEdit       = EditText(activity)

    private val currentCurrencyCostFlow = MutableStateFlow<Double>(currenciesMap?.values?.first() ?: 0.0)

    override fun show(parentStage: ConstraintLayout) {
        parentStage.alpha = 0f
        stage.setBackgroundResource(R.drawable.background)
        super.show(parentStage)

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }

    }

    override fun ViewGroup.addActorsOnStage() {
        coroutine.launch(Dispatchers.Main) {
            addBackAnim()
            addPanelImage()
            addInputs()
            addCurrenciesImage()
            addCurrencyScroll()
            addCurrencyText()
            addEdits()

            animBackground()
            animBack()
            animPanel()
            animInputs()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addBackAnim() {
        addView(backAnim.stage)
        backAnim.apply {
            this@ExchangerScreen.sizeConverter.setBounds(stage, LE.backAnim)
            initialize(LE.backAnim.size())
            stage.alpha = 0f
        }
    }
    private fun ViewGroup.addPanelImage() {
        addView(panelImage)
        panelImage.apply {
            this@ExchangerScreen.sizeConverter.setBounds(this, LE.panelStart, LE.panelSize)
            setImageResource(R.drawable.panel)
            alpha = 0f
        }

        val v = View(activity)

        addView(v)

        v.apply {
            var isShowCurrencies = false

            this@ExchangerScreen.sizeConverter.setBounds(this, LE.click)
            setOnClickListener {
                if (isShowCurrencies) {
                    isShowCurrencies = false
                    animHideCurrencies()
                } else {
                    isShowCurrencies = true
                    animShowCurrencies()
                }
            }
        }

    }
    private fun ViewGroup.addInputs() {
        addView(leftInputImage)
        leftInputImage.apply {
            this@ExchangerScreen.sizeConverter.setBounds(this, LE.leftStart, LE.inputSize)
            setImageResource(R.drawable.input)
            alpha = 0f
        }

        addView(rightInputImage)
        rightInputImage.apply {
            this@ExchangerScreen.sizeConverter.setBounds(this, LE.rightStart, LE.inputSize)
            setImageResource(R.drawable.input)
            alpha = 0f
        }
    }
    private fun ViewGroup.addCurrenciesImage() {
        addView(currenciesImage)
        currenciesImage.apply {
            setImageResource(R.drawable.currencyes)
            this@ExchangerScreen.sizeConverter.setBounds(this, LE.currencies)
            alpha = 0f
        }
    }
    private fun ViewGroup.addCurrencyText() {
        addView(currencyText)
        currencyText.apply {
            text = currenciesMap?.keys?.first()
            isAllCaps = true
            gravity = Gravity.CENTER
            typeface = Typeface.createFromAsset(activity.assets, "DMMono-Medium.ttf")
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LE.name)
            alpha = 0f
        }
    }
    private fun ViewGroup.addCurrencyScroll() {
        addView(currencyScroll.stage)
        currencyScroll.apply {
            this@ExchangerScreen.sizeConverter.setBounds(stage, LE.scroll)
            initialize(LE.scroll.size())
            stage.alpha = 0f

            blockSelectCurrency = { name, cost ->
                currencyText.text = name
                currentCurrencyCostFlow.value = cost
            }
        }
    }

    private fun ViewGroup.addEdits() {
        addView(leftEdit)
        leftEdit.apply {
            setText("1.0")

            this@ExchangerScreen.sizeConverter.setBounds(this, LE.left)
            gravity = Gravity.CENTER
            typeface = Typeface.createFromAsset(activity.assets, "DMMono-Medium.ttf")
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            setBackgroundResource(android.R.color.transparent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                textCursorDrawable = ContextCompat.getDrawable(activity, R.drawable.cursor)
            }
            inputType = InputType.TYPE_CLASS_NUMBER
            keyListener = DigitsKeyListener.getInstance("0123456789.")

            alpha = 0f

            doOnTextChanged { text, start, before, count ->
                if (isFocused) {
                try {
                    (text.toString().toDouble() * currentCurrencyCostFlow.value).also { rightEdit.setText("%.5f".format(it).replace(",", ".")) }
                } catch (e: Exception) { rightEdit.setText("ERROR") }
            } }
        }

        addView(rightEdit)
        rightEdit.apply {
            this@ExchangerScreen.sizeConverter.setBounds(this, LE.right)
            gravity = Gravity.CENTER
            typeface = Typeface.createFromAsset(activity.assets, "DMMono-Medium.ttf")
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            setBackgroundResource(android.R.color.transparent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                textCursorDrawable = ContextCompat.getDrawable(activity, R.drawable.cursor)
            }
            inputType = InputType.TYPE_CLASS_NUMBER
            keyListener = DigitsKeyListener.getInstance("0123456789.")

            alpha = 0f

            doOnTextChanged { text, start, before, count ->
                if (isFocused) {
                try {
                    (text.toString().toDouble() / currentCurrencyCostFlow.value).also { leftEdit.setText("%.5f".format(it).replace(",", ".")) }
                } catch (e: Exception) { leftEdit.setText("ERROR") }
                    }
            }
        }

        coroutine.launch(Dispatchers.Default) {
            currentCurrencyCostFlow.collect { cost ->
                withContext(Dispatchers.Main) {
                    try {
                        (leftEdit.text.toString().toDouble() * cost).also { rightEdit.setText("%.5f".format(it).replace(",", ".")) }
                    } catch (e: Exception) { leftEdit.setText("ERROR") }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun animBackground() = CompletableDeferred<Unit>().also { continuation ->
        parentStage.animate().apply {
            alpha(1f)
            duration = 400
            withEndAction { continuation.complete(Unit) }
        }.start()
    }.await()
    private suspend fun animBack() = CompletableDeferred<Unit>().also { continuation ->
        backAnim.stage.animate().apply {
            alpha(1f)
            duration = 500
            withEndAction { continuation.complete(Unit) }
        }.start()
    }.await()
    private suspend fun animPanel() = CompletableDeferred<Unit>().also { continuation ->
        panelImage.animate().apply {
            val pos = sizeConverter.getSize(LE.panelEnd)
            translationX(pos.x)
            translationY(pos.y)
            alpha(1f)
            duration = 700
            withEndAction {
                currencyText.animate().apply {
                    alpha(1f)
                    duration = 1000
                }.start()
                continuation.complete(Unit)
            }
        }.start()
    }.await()
    private suspend fun animInputs() = CompletableDeferred<Unit>().also { continuation ->
        leftInputImage.animate().apply {
            val pos = sizeConverter.getSize(LE.leftEnd)
            translationX(pos.x)
            translationY(pos.y)
            alpha(1f)
            duration = 1000
        }.start()

        rightInputImage.animate().apply {
            val pos = sizeConverter.getSize(LE.rightEnd)
            translationX(pos.x)
            translationY(pos.y)
            alpha(1f)
            duration = 1000
            withEndAction {
                leftEdit.animate().apply {
                    alpha(1f)
                    duration = 1000
                }.start()
                rightEdit.animate().apply {
                    alpha(1f)
                    duration = 1000
                }.start()
                continuation.complete(Unit)
            }
        }.start()
    }.await()

    private fun animShowCurrencies() {
        currenciesImage.animate().apply {
            alpha(1f)
            duration = 600
        }.start()

        currencyScroll.stage.animate().apply {
            alpha(1f)
            duration = 600
        }.start()
    }

    private fun animHideCurrencies() {
        currencyScroll.stage.animate().apply {
            alpha(0f)
            duration = 600
        }.start()

        currenciesImage.animate().apply {
            alpha(0f)
            duration = 600
        }.start()
    }


}