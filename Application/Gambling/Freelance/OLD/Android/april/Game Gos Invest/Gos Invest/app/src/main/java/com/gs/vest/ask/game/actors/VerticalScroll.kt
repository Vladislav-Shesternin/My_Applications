package com.gs.vest.ask.game.actors

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.game.util.Group

class VerticalScroll(val activity: FragmentActivity): Group(activity) {

    private val scroll       = ScrollView(activity)
    private val frameLayout  = FrameLayout(activity)

    val container = FrameLayout(activity)



    override fun ViewGroup.addActorsOnStage() {
        addScroll()
    }

    // ---------------------------------------------------
    // Add View
    // ---------------------------------------------------

    private fun ViewGroup.addScroll() {
        addView(scroll)

        scroll.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addFrameLayout()
        }
    }

    private fun ViewGroup.addFrameLayout() {
        addView(frameLayout)

        frameLayout.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            addContainer()
        }
    }

    private fun ViewGroup.addContainer() {
        addView(container)

        container.apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0
            )

        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateContainer(height: Int) {
        container.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            height
        )
    }

//    private suspend fun ViewGroup.addCryptoPanel() {
//        addView(cryptoPanel)
//        sizeConverter.setBounds(cryptoPanel, LC.panel)
//
//        val scroll = ScrollView(activity)
//        scroll.layoutParams = FrameLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//
//        cryptoPanel.addView(scroll)
//
//        scroll.addCryptoItem()
//    }
//
//    private suspend fun ViewGroup.addCryptoItem() {
//        val frameLayout = FrameLayout(activity).apply {
//            layoutParams = FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//        }
//
//        val container = FrameLayout(activity).apply {
//            layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
//        }
//
//        frameLayout.addView(container)
//        addView(frameLayout)
//
//        CompletableDeferred<Unit>().also { continuation -> cryptoPanel.doOnPreDraw { continuation.complete(Unit) } }.await()
//
//        val sizeConverterPanel = SizeConverter(LC.panel.size(), Size(cryptoPanel.width.toFloat(), cryptoPanel.height.toFloat()))
//
//        var ny = LC.cryptoItem.y
//        CryptoUtil.cryptoList.onEachIndexed { index, crypto ->
//            cryptoItemList[index].also { cryptoItem ->
//                container.addView(cryptoItem.stage)
//
//                cryptoItem.stage.layoutParams = FrameLayout.LayoutParams(0, 0)
//                with(LC.cryptoItem) {
//                    sizeConverterPanel.setBounds(cryptoItem.stage, x, ny, w, h)
//                    ny += h + vs
//                }
//                cryptoItem.setLogoAndName(crypto.logo, crypto.name, crypto.symbol)
//                cryptoItem.initialize(LC.cryptoItem.size())
//
//                cryptoItem.stage.doOnPreDraw {
//                    container.layoutParams = FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        (it.y + it.height).toInt()
//                    )
//                }
//
//                cryptoItem.stage.setOnClickListener {
//                    navigationManager.navigate(TradingScreen(activity, crypto, index), BondsScreen(activity))
//                }
//            }
//        }
//
//    }

}