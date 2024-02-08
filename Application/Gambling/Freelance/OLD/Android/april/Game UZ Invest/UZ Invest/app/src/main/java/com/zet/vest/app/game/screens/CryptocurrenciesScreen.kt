package com.zet.vest.app.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentActivity
import com.zet.vest.app.R
import com.zet.vest.app.game.actors.CryptoItem
import com.zet.vest.app.game.navigationManager
import com.zet.vest.app.game.util.*
import com.zet.vest.app.util.log
import kotlinx.coroutines.*
import com.zet.vest.app.game.util.Layout.Cryptocurrencies as LC

class CryptocurrenciesScreen(
    val activity: FragmentActivity
) : Screen(activity) {

    private val titleText = TextView(activity)
    private val separatorImage = ImageView(activity)
    private val cryptoPanel = FrameLayout(activity)
    private val cryptoItemList = List(CryptoUtil.cryptoList.size) { CryptoItem(activity) }

    private val debugList = listOf<View>(
//        titleText,
//        separatorImage,
//        cryptoItemList.first().stage,
//        cryptoItemList[1].stage,
//        cryptoPanel,

    )

    override fun show(parentStage: ConstraintLayout) {
        super.show(parentStage)
        //parent.setBackgroundResource(R.drawable.background)

        debugList.onEach {
            it.setBackgroundResource(R.drawable.debug)
        }
    }

    override fun ViewGroup.addActorsOnStage() {
        coroutine.launch(Dispatchers.Main) {
            addTitle()
            addSeparatorImage()
            addCryptoPanel()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addTitle() {
        addView(titleText)

        titleText.apply {
            text = activity.getString(R.string.cryptocurrency)
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(
                TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM
            ) else textSize = 15f
            sizeConverter.setBounds(this, LC.title)
        }

    }

    private fun ViewGroup.addSeparatorImage() {
        addView(separatorImage)
        separatorImage.apply {
            setImageResource(R.drawable.separator)
            sizeConverter.setBounds(this, LC.separator)
        }
    }

    private suspend fun ViewGroup.addCryptoPanel() {
        addView(cryptoPanel)
        sizeConverter.setBounds(cryptoPanel, LC.panel)

        val scroll = ScrollView(activity)
        scroll.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        cryptoPanel.addView(scroll)

        scroll.addCryptoItem()
    }

    private suspend fun ViewGroup.addCryptoItem() {
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
        addView(frameLayout)

        CompletableDeferred<Unit>().also { continuation -> cryptoPanel.doOnPreDraw { continuation.complete(Unit) } }.await()

        val sizeConverterPanel = SizeConverter(LC.panel.size(), Size(cryptoPanel.width.toFloat(), cryptoPanel.height.toFloat()))

        var ny = LC.cryptoItem.y
        CryptoUtil.cryptoList.onEachIndexed { index, crypto ->
            cryptoItemList[index].also { cryptoItem ->
                container.addView(cryptoItem.stage)

                cryptoItem.stage.layoutParams = FrameLayout.LayoutParams(0, 0)
                with(LC.cryptoItem) {
                    sizeConverterPanel.setBounds(cryptoItem.stage, x, ny, w, h)
                    ny += h + vs
                }
                cryptoItem.setLogoAndName(crypto.logo, crypto.name, crypto.symbol)
                cryptoItem.initialize(LC.cryptoItem.size())

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

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}