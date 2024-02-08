package com.kurs.mon.fin.game.actors

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.game.util.Group
import com.kurs.mon.fin.game.util.Size

class CurrencyScroll(val activity: FragmentActivity, val currencies: Map<String, Double>): Group(activity) {

    private val currencyList = List(currencies.size) { Currency(activity, currencies.keys.elementAt(it), currencies.values.elementAt(it)) }
    private val scroll       = VerticalScroll(activity)

    var blockSelectCurrency: (String, Double) -> Unit = { a, b -> }

    override fun ViewGroup.addActorsOnStage() {
        addItems()
    }

    // ---------------------------------------------------
    // Add View
    // ---------------------------------------------------

    private fun ViewGroup.addItems() {
        addView(scroll.stage)

        sizeConverter.apply { setBounds(scroll.stage, 0f, 0f, fromSize.width, fromSize.height) }
        scroll.initialize(sizeConverter.fromSize) {
            var ny = 0f
            currencyList.onEach { currency ->
                scroll.container.addView(currency.stage)
                currency.stage.layoutParams = FrameLayout.LayoutParams(0, 0)
                scroll.sizeConverter.setBounds(currency.stage, 0f, ny, 208f, 72f)
                ny += 72f + 35f
                currency.initialize(Size(208f, 72f)) {
                    scroll.updateContainer((currency.stage.y + currency.stage.height).toInt())
                }

                currency.blockSelectCurrency = blockSelectCurrency
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

}