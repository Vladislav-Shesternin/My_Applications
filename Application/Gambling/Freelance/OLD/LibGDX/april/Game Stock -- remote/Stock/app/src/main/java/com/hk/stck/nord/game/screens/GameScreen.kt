package com.hk.stck.nord.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hk.stck.nord.game.actors.Detail
import com.hk.stck.nord.game.actors.Stock
import com.hk.stck.nord.game.actors.VerticalGroup
import com.hk.stck.nord.game.actors.button.AButton
import com.hk.stck.nord.game.actors.button.AButtonStyle
import com.hk.stck.nord.game.actors.button.AButtonText
import com.hk.stck.nord.game.manager.FontTTFManager
import com.hk.stck.nord.game.manager.NavigationManager
import com.hk.stck.nord.game.manager.SpriteManager
import com.hk.stck.nord.game.utils.Layout
import com.hk.stck.nord.game.utils.actor.disable
import com.hk.stck.nord.game.utils.actor.setBounds
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import com.hk.stck.nord.game.utils.advanced.AdvancedScreen
import com.hk.stck.nord.game.utils.runGDX
import com.hk.stck.nord.util.log
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import io.finnhub.api.models.StockSymbol
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.hk.stck.nord.game.utils.Layout.Game as LG

class GameScreen : AdvancedScreen() {

    companion object {
        private val COUNT = 100

        var stockList = mutableListOf<StockSymbol>()
            private set
    }

    private val verticalGroup = VerticalGroup(VerticalGroup.Direction.DOWN)
    private val scrollPane    = ScrollPane(verticalGroup)
    private val detail        = Detail()
    private val btn           = AButtonText("menu", AButtonStyle.mini, Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Regular.font_71.font, Color.WHITE))

    private var btnBlock: () -> Unit = { NavigationManager.back() }


    override fun show() {
        ApiClient.apiKey["token"] = "cglgpmhr01qrjukr6hlgcglgpmhr01qrjukr6hm0"

        stageUI.addAction(Actions.alpha(0f))
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addScrollPane()

                addStocks()
                addActor(Image(SpriteManager.GameRegion.B.region).apply {
                    setBounds(0f, 0f, WIDTH, HEIGHT)
                    disable()
                })

                addActor(detail.apply {
                    setBounds(LG.detail)
                    addAction(Actions.alpha(0f))
                })

                addActor(btn)
                btn.apply {
                    setBounds(LG.btn)
                    setOnClickListener { btnBlock() }
                }
            }
            showStage()
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addScrollPane() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 185f, 700f, 1233f)
    }

    private fun AdvancedGroup.addStocks() {
        val stocks = List(COUNT) { Stock() }.onEach { stock ->
            stock.setBounds(80f, 211f, 539f, 178f)
            verticalGroup.addActor(stock)
        }

        coroutine.launch {
            if (stockList.isEmpty()) DefaultApi().stockSymbols("US", "", "", "").take(COUNT).also {
                stockList.addAll(it)
                log("stockList - ${stockList.size} ---- ${stockList.first()}")
            }

            runGDX {
                stockList.onEachIndexed { index, stock ->
                    stocks[index].apply {
                        update(stock.description ?: "NO DESCRIPTION")
                        block = {
                            scrollPane.addAction(Actions.sequence(
                                Actions.fadeOut(0.5f),
                                Actions.run {
                                    detail.update(stock.description, stock.currency, stock.displaySymbol, stock.figi, stock.mic, stock.symbol, stock.type)
                                    detail.addAction(Actions.fadeIn(0.5f))

                                    btn.label.setText("back")
                                    btnBlock = {
                                        detail.addAction(Actions.sequence(
                                            Actions.fadeOut(0.5f),
                                            Actions.run {
                                                scrollPane.addAction(Actions.fadeIn(0.5f))
                                                btn.label.setText("menu")

                                                btnBlock = { NavigationManager.back() }
                                            }
                                        ))
                                    }
                                }
                            ))
                        }
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun showStage() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            stageUI.addAction(Actions.sequence(
                Actions.fadeIn(0.4f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

}