package com.gs.vest.ask.game.screens

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Build
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.R
import com.gs.vest.ask.game.navigationManager
import com.gs.vest.ask.game.util.BalanceUtil
import com.gs.vest.ask.game.util.Screen
import com.gs.vest.ask.game.util.data.BondUtil
import com.gs.vest.ask.game.util.toBalance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import com.gs.vest.ask.game.util.Layout.Bond as LB

class BondScreen(
    val activity: FragmentActivity,
    val bond: BondUtil.Bond,
    val index: Int
) : Screen(activity) {

    private val panelImage  = ImageView(activity)
    private val logoImage   = ImageView(activity)
    private val nameText    = TextView(activity)
    private val costText    = TextView(activity)
    private val percentText = TextView(activity)
    private val amountText  = TextView(activity)
    private val profitText  = TextView(activity)
    private val buyCostText    = TextView(activity)
    private val buyProfitText  = TextView(activity)
    private val amountEditText = AppCompatEditText(activity)
    private val sellButton     = Button(activity)
    private val buyButton      = Button(activity)

    private val buyProfitFlow = MutableStateFlow(0L)
    private var amount = 0


    override fun ViewGroup.addActorsOnStage() {
        addPanelImage()
        addNameText()
        addLogoImage()
        addCostText()
        addPercentText()
        addAmountText()
        addProfitText()
        addBuyCostText()
        addBuyProfitText()
        addAmountEditText()
        addSellButton()
        addBuyButton()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addLogoImage() {
        addView(logoImage)
        logoImage.apply {
            setImageResource(bond.logo)
            sizeConverter.setBounds(this, LB.logo)
        }
    }

    private fun ViewGroup.addPanelImage() {
        addView(panelImage)
        panelImage.apply {
            setImageResource(R.drawable.obl_info)
            sizeConverter.setBounds(this, LB.panel)
        }
    }

    private fun ViewGroup.addNameText() {
        addView(nameText)
        nameText.apply {
            text = bond.name
            gravity = Gravity.CENTER
            setPadding(0, 30, 0, 30)
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Black.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.name)
        }
    }

    private fun ViewGroup.addCostText() {
        addView(costText)

        costText.apply {
            text = "$".plus(" ").plus(bond.cost.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_yellow))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.cost)
        }
    }

    private fun ViewGroup.addPercentText() {
        addView(percentText)

        percentText.apply {
            text = "%".plus(" ").plus(DecimalFormat("#.##").format(bond.percent)).replace(",",".")
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_dark_green))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.percent)
        }
    }

    private fun ViewGroup.addAmountText() {
        addView(amountText)

        amountText.apply {
            text = "У Вас есть: ".plus(bond.amount.toString())
            gravity = Gravity.CENTER
            setPadding(0, 30, 0, 30)
            setTextColor(ContextCompat.getColor(activity, R.color.game_blue))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.amount)
        }
    }

    private fun ViewGroup.addProfitText() {
        addView(profitText)

        profitText.apply {
            text = "Прибыль: ".plus("$ ").plus(bond.profit.toBalance())
            gravity = Gravity.CENTER
            setPadding(0, 30, 0, 30)
            setTextColor(ContextCompat.getColor(activity, R.color.game_blue))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.profit)
        }
    }

    private fun ViewGroup.addBuyCostText() {
        addView(buyCostText)

        buyCostText.apply {
            text = "0"
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.buyCost)
        }
    }

    private fun ViewGroup.addBuyProfitText() {
        addView(buyProfitText)

        buyProfitText.apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.buyProfit)

            coroutine.launch(Dispatchers.Main) {
                buyProfitFlow.collect { profit ->
                    text = profit.toBalance()
                }
            }
        }
    }

    private fun ViewGroup.addAmountEditText() {
        addView(amountEditText)

        amountEditText.apply {
            gravity = Gravity.CENTER
            inputType = (InputType.TYPE_CLASS_NUMBER)
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.amountEdit)

            doOnTextChanged { text, start, before, count ->
                try {
                    amount = text.toString().toInt()
                    buyCostText.text = (amount * bond.cost).toBalance()
                    buyProfitFlow.value = (((bond.cost * text.toString().toLong()) / 100) * bond.percent).toLong()
                }
                catch (e: Exception) {
                    amount = 0
                    buyCostText.text = "0"
                    buyProfitFlow.value = 0L
                }
            }
        }
    }

    private fun ViewGroup.addSellButton() {
        addView(sellButton)

        sellButton.apply {
            setBackgroundResource(R.drawable.sell)
            sizeConverter.setBounds(this, LB.sell)
            setOnClickListener {
                if (amount <= bond.amount) {
                    bond.amount -= amount
                    BalanceUtil.flow.value += (amount * bond.cost)
                    if (bond.amount <= 0) ProfileScreen.portfelObligationIndexSet.remove(index)
                    navigationManager.navigate(BondScreen(activity, bond, index))
                }
            }
        }
    }

    private fun ViewGroup.addBuyButton() {
        addView(buyButton)

        buyButton.apply {
            setBackgroundResource(R.drawable.buy)
            sizeConverter.setBounds(this, LB.buy)
            setOnClickListener {
                if (amount > 0 && (BalanceUtil.flow.value - (amount * bond.cost)) >= 0) {
                    bond.amount += amount
                    ProfileScreen.portfelObligationIndexSet.add(index)
                    BalanceUtil.flow.value -= (amount * bond.cost)
                    navigationManager.navigate(BondScreen(activity, bond, index))
                }
            }
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}