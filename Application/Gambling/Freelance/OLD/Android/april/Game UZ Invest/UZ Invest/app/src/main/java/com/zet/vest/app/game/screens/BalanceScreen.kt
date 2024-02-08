package com.zet.vest.app.game.screens

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.text.Layout.Alignment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.FragmentActivity
import com.zet.vest.app.R
import com.zet.vest.app.game.actors.CryptoItem
import com.zet.vest.app.game.navigationManager
import com.zet.vest.app.game.util.*
import com.zet.vest.app.util.log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.round
import com.zet.vest.app.game.util.Layout.Balance as LB

class BalanceScreen(
    val activity: FragmentActivity
): Screen(activity) {

    companion object {
        val cryptoIndexList = mutableListOf<Int>()
    }

    private val balanceTitleText   = TextView(activity)
    private val balanceText        = TextView(activity)
    private val startTradingButton = Button(activity)
    private val portfolioImage     = ImageView(activity)
    private val emptyImage         = ImageView(activity)

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
        coroutine.launch(Dispatchers.Main) {
            addBalance()
            addStartTradingButton()
            addPortfolioImage()
            addEmptyImage()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addBalance() {
        addView(balanceTitleText)
        addView(balanceText)

        balanceTitleText.apply {
            text = activity.getString(R.string.balance)
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LB.balanceTitle)
        }

        balanceText.apply {
            text = "$".plus(BalanceUtil.flow.value.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LB.balance)
        }

    }

    private fun ViewGroup.addStartTradingButton() {
        addView(startTradingButton)

        startTradingButton.apply {
            setBackgroundResource(R.drawable.trading)
            sizeConverter.setBounds(this, LB.trading)
            setOnClickListener { startTrading() }
        }
    }

    private fun ViewGroup.addPortfolioImage() {
        addView(portfolioImage)

        portfolioImage.apply {
            setImageResource(R.drawable.portfolio)
            sizeConverter.setBounds(this, LB.portfolio)
        }
    }

    private suspend fun ViewGroup.addEmptyImage() {
        if (cryptoIndexList.isEmpty()) {
            addView(emptyImage)

            emptyImage.apply {
                setImageResource(R.drawable.empty)
                sizeConverter.setBounds(this, LB.empty)
                setOnClickListener { startTrading() }
            }
        } else {
            val cryptoPanel = FrameLayout(activity)
            addView(cryptoPanel)
            sizeConverter.setBounds(cryptoPanel, 66f, 903f, 594f, 570f)

            val scroll = ScrollView(activity)
            scroll.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            cryptoPanel.addView(scroll)

            val frameLayout = FrameLayout(activity).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            val container = FrameLayout(activity).apply {
                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
            }

            frameLayout.addView(container)
            scroll.addView(frameLayout)

            CompletableDeferred<Unit>().also { continuation -> cryptoPanel.doOnPreDraw { continuation.complete(Unit) } }.await()

            val sizeConverterPanel = SizeConverter(Size(594f, 570f), Size(cryptoPanel.width.toFloat(), cryptoPanel.height.toFloat()))

            var ny = 0f
            cryptoIndexList.onEach { index ->
                CryptoUtil.cryptoList[index].also { crypto ->
                    CryptoItem(activity).also { cryptoItem ->
                        container.addView(cryptoItem.stage)

                        cryptoItem.stage.layoutParams = FrameLayout.LayoutParams(0, 0)

                        sizeConverterPanel.setBounds(cryptoItem.stage, 0f, ny, 594f, 87f)
                        ny += 87f + 8f

                        cryptoItem.setLogoAndName(crypto.logo, crypto.name, crypto.symbol)
                        cryptoItem.initialize(Size(594f, 87f))

                        cryptoItem.stage.doOnPreDraw {
                            container.layoutParams = FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (it.y + it.height).toInt()
                            )
                        }

                        cryptoItem.stage.setOnClickListener {
                            navigationManager.navigate(TradingScreen(activity, crypto, index), CryptocurrenciesScreen(activity))
                        }
                    }
                }
            }
        }
    }


/*
    private fun ViewGroup.addExitButton() {
        addView(exitButton)

        exitButton.apply {
            isAllCaps = false
            setText(R.string.exit)
            setPadding(50 * DENSITY)
            setTextColor(GameColor.orange)
            typeface = Typeface.createFromAsset(activity.assets, "alef.ttf")
            setBackgroundResource(R.drawable.state_button)
            setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM)
            setBounds(LM.exit.x, LM.exit.y, LM.exit.w, LM.exit.h)

            setOnClickListener { NavigationManager.exit(activity) }
        }
    }*/

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun startTrading() {
        navigationManager.navigate(CryptocurrenciesScreen(activity), BalanceScreen(activity))
    }

}