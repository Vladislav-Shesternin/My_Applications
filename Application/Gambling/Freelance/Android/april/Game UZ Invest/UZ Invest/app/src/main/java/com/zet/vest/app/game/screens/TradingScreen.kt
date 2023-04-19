package com.zet.vest.app.game.screens

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.options.models.timeScaleOptions
import com.tradingview.lightweightcharts.api.series.models.*
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import com.zet.vest.app.R
import com.zet.vest.app.game.navigationManager
import com.zet.vest.app.game.util.*
import com.zet.vest.app.game.util.Layout.Trading as LT

class TradingScreen(
    val activity    : FragmentActivity,
    val crypto      : CryptoUtil.Crypto,
    val cryptoIndex : Int,
): Screen(activity) {

    private val symbolText = TextView(activity)
    private val nameText   = TextView(activity)
    private val priceText  = TextView(activity)
    private val chartsView = ChartsView(activity)
    private val sellButton = Button(activity)
    private val buyButton  = Button(activity)

    private val debugList = listOf<View>(
        chartsView
    )

    override fun show(parentStage: ConstraintLayout) {
        super.show(parentStage)
        //parent.setBackgroundResource(R.drawable.background)

        debugList.onEach {
            it.setBackgroundResource(R.drawable.debug)
        }
    }

    override fun ViewGroup.addActorsOnStage() {
        addChartsView()
        addSymbolText()
        addNameText()
        addPriceText()
        addButtons()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addSymbolText() {
        addView(symbolText)

        symbolText.apply {
            text = crypto.symbol
            setTextColor(ContextCompat.getColor(activity, R.color.game_dark_green))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LT.symbol)
        }
    }

    private fun ViewGroup.addNameText() {
        addView(nameText)

        nameText.apply {
            text = crypto.name
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LT.name)
        }
    }

    private fun ViewGroup.addPriceText() {
        addView(priceText)

        priceText.apply {
            text = "$".plus(crypto.price.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_green))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LT.price)
        }
    }

    private fun ViewGroup.addButtons() {
        addView(sellButton)
        addView(buyButton)

        sellButton.apply {
            setBackgroundResource(R.drawable.sell)
            sizeConverter.setBounds(this, LT.sell)
            setOnClickListener {
                if (BalanceScreen.cryptoIndexList.contains(cryptoIndex)) {
                    BalanceScreen.cryptoIndexList.remove(cryptoIndex)
                    BalanceUtil.flow.value -= crypto.price
                }
                navigationManager.navigate(BalanceScreen(activity), TradingScreen(activity, crypto, cryptoIndex))
            }
        }

        buyButton.apply {
            setBackgroundResource(R.drawable.buy)
            sizeConverter.setBounds(this, LT.buy)
            setOnClickListener {
                if (BalanceScreen.cryptoIndexList.contains(cryptoIndex).not()) {
                    BalanceScreen.cryptoIndexList.add(cryptoIndex)
                    BalanceUtil.flow.value += crypto.price
                }
                navigationManager.navigate(BalanceScreen(activity), TradingScreen(activity, crypto, cryptoIndex))
            }
        }
    }

    private fun ViewGroup.addChartsView() {
        addView(chartsView)

        val data = listOf(
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 10), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 9), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 8), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 7), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 6), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 5), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 4), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 3), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 2), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY - 1), getPriceValue()),
            LineData(Time.BusinessDay(YEAR, MONTH.inc(), DAY), crypto.price.toFloat()),
        )

        chartsView.apply {
            sizeConverter.setBounds(this, LT.chartsView)

            api.addAreaSeries (onSeriesCreated = { series -> series.setData(data) })
            api.applyOptions {
                layout = layoutOptions {
                    textColor  = Color.WHITE.toIntColor()
                }
                localization = localizationOptions {
                    locale = "en-EN"
                    priceFormatter = PriceFormatter(template = "{price:#2:#3}$")
                    timeFormatter = TimeFormatter(
                        locale = "en-EN",
                        dateTimeFormat = DateTimeFormat.DATE_TIME
                    )
                }
                timeScale = timeScaleOptions {
                    fixLeftEdge = true
                    fixRightEdge = true
                }
            }
            api.timeScale.fitContent()
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getPriceValue(): Float {
        val hundredths = 1 / (1..10).shuffled().first()
        val minPrice = (crypto.price / 2).toInt()
        val maxPrice = (crypto.price * 2).toInt()
        return ((minPrice..maxPrice).shuffled().first() + hundredths).toFloat()
    }

}