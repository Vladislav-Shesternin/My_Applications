package com.gs.vest.ask.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.R
import com.gs.vest.ask.game.util.BalanceUtil
import com.gs.vest.ask.game.util.Screen
import com.gs.vest.ask.game.actors.BondItem
import com.gs.vest.ask.game.util.Size
import com.gs.vest.ask.game.actors.HorizontalScroll
import com.gs.vest.ask.game.navigationManager
import com.gs.vest.ask.game.timerUtil

import com.gs.vest.ask.game.util.data.BondUtil
import com.gs.vest.ask.game.util.toBalance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.gs.vest.ask.game.util.Layout.Profile as LP

class ProfileScreen(
    val activity: FragmentActivity
): Screen(activity) {

    companion object {
        val portfelObligationIndexSet = mutableSetOf<Int>()
    }

    private val gosInvestImage   = ImageView(activity)
    private val timerText        = TextView(activity)
    private val balanceText      = TextView(activity)
    private val profitText       = TextView(activity)
    private val oblButton        = Button(activity)
    private val scroll           = HorizontalScroll(activity)


//    private val debugList = listOf<View>(
//        balanceTitleText,
//        balanceText,
//        startTradingButton,
//        portfolioImage,
//        emptyImage
//    )

    override fun show(parentStage: ConstraintLayout) {
        super.show(parentStage)
        //parent.setBackgroundResource(R.drawable.background)

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }
    }

    override fun ViewGroup.addActorsOnStage() {
        addGosInvestImage()
        addTimerText()
        addPortfel()
        addBalanceText()
        addProfitText()
        addOblButton()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addGosInvestImage() {
        addView(gosInvestImage)

        gosInvestImage.apply {
            setImageResource(R.drawable.gos_invest)
            sizeConverter.setBounds(this, LP.gosInvest)
        }
    }

    private fun ViewGroup.addTimerText() {
        addView(timerText)

        timerText.apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LP.timer)
        }

        timerUtil.blockList.add { time ->
            timerText.text = time.toString()

            if (time == 1) {
                coroutine.launch(Dispatchers.Main) {
                    portfelObligationIndexSet.onEach { bondIndex ->
                        BondUtil.bondList[bondIndex].apply {
                            generateCost()
                            generatePercent()
                        }
                    }
                    navigationManager.navigate(ProfileScreen(activity))
                }
            }
        }
    }

    private fun ViewGroup.addPortfel() {
        if (portfelObligationIndexSet.isEmpty()) {
            val emptyImage = ImageView(activity)
            addView(emptyImage)

            emptyImage.apply {
                setImageResource(R.drawable.not_oblig)
                sizeConverter.setBounds(this, LP.notOblig)
                setOnClickListener { navToBonds() }
            }
        } else {
            addScroll()
        }
    }

    private fun ViewGroup.addBalanceText() {
        addView(balanceText)

        balanceText.apply {
            text = "$".plus(" ").plus(BalanceUtil.flow.value.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_yellow))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LP.balance)
        }

    }

    private fun ViewGroup.addProfitText() {
        addView(profitText)

        profitText.apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_yellow))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LP.profit)
        }

        var profit = 0L
        portfelObligationIndexSet.onEach { porOblIndex ->
            profit += BondUtil.bondList[porOblIndex].profit
        }

        profitText.text = "$".plus(" ").plus(profit.toBalance())

    }

    private fun ViewGroup.addOblButton() {
        addView(oblButton)

        oblButton.apply {
            setBackgroundResource(R.drawable.bund)
            sizeConverter.setBounds(this, LP.button)

            setOnClickListener { navToBonds() }
        }

    }

    private fun ViewGroup.addScroll() {
        addView(scroll.stage)

        sizeConverter.setBounds(scroll.stage, LP.scroll)


        scroll.apply {
            initialize(LP.scroll.size()) {

                var nx = 0f
                portfelObligationIndexSet.onEach { bondIndex ->
                    BondUtil.bondList[bondIndex].also { bond ->
                        BondItem(activity, bond).apply {
                            container.addView(stage)
                            stage.layoutParams = FrameLayout.LayoutParams(0, 0)
                            scroll.sizeConverter.setBounds(stage, nx, 0f, 423f, 502f)
                            nx += 423f + 24f
                            initialize(Size(423f, 502f)) {
                                stage.doOnPreDraw { updateContainer((stage.x + stage.width).toInt()) }
                            }

                            block = {
                                navigationManager.navigate(
                                    BondScreen(activity, bond, bondIndex),
                                    ProfileScreen(activity)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun navToBonds() {
        navigationManager.navigate(BondsScreen(activity), ProfileScreen(activity))
    }

}