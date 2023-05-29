package com.hello.piramidka.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hello.piramidka.game.actors.ACurrency
import com.hello.piramidka.game.actors.button.AButton
import com.hello.piramidka.game.actors.button.AButtonStyle
import com.hello.piramidka.game.actors.label.spinning.SpinningLabel
import com.hello.piramidka.game.actors.scroll.VerticalGroup
import com.hello.piramidka.game.manager.FontTTFManager
import com.hello.piramidka.game.manager.NavigationManager
import com.hello.piramidka.game.manager.SpriteManager
import com.hello.piramidka.game.utils.Layout
import com.hello.piramidka.game.utils.actor.disable
import com.hello.piramidka.game.utils.actor.enable
import com.hello.piramidka.game.utils.actor.setOnClickListener
import com.hello.piramidka.game.utils.advanced.AdvancedGroup
import com.hello.piramidka.game.utils.advanced.AdvancedScreen
import com.hello.piramidka.game.utils.runGDX
import com.hello.piramidka.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConverterScreen: AdvancedScreen() {

    private val currencyFlow = MutableStateFlow<Pair<String, Double>>((currenciesMap?.keys?.first() ?: "") to (currenciesMap?.values?.first() ?: 0.0))

    private val panelImage = Image(themeUtil.CONVER)
    private val usdy       = Actor()
    private val backBnt    = AButton(AButtonStyle.back)
    private val currentUsd = SpinningLabel(currencyFlow.value.first, Label.LabelStyle(FontTTFManager.BlackFont.font_102.font, Color.WHITE))
    private val currentBotUsd = SpinningLabel(currencyFlow.value.first, Label.LabelStyle(FontTTFManager.BlackFont.font_40.font, Color.WHITE))
    private val verticalG  = VerticalGroup(gap = 20f)
    private val scrollPane = ScrollPane(verticalG)
    private val whiter     = Image(SpriteManager.GameRegion.WHITER.region)

    private val rub = SpinningLabel("1.0", Label.LabelStyle(FontTTFManager.BlackFont.font_60.font, themeUtil.color))
    private val usd = SpinningLabel(currencyFlow.value.second.toString(), Label.LabelStyle(FontTTFManager.BlackFont.font_60.font, themeUtil.color))
    override fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
        addBack()
        addLabel()
        addUSDT()
        addScroll()

        coroutine.launch {
            currencyFlow.collect {
                runGDX {
                    currentUsd.setText(it.first)
                    currentBotUsd.setText(it.first)
                    usd.setText(it.second.toString())
                }
            }
        }

    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(panelImage)
        panelImage.setBounds(55f, 557f, 690f, 968f)
    }
    private fun AdvancedGroup.addBack() {
        addActor(backBnt)
        backBnt.apply {
            setBounds(240f, 154f, 320f, 202f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addLabel() {
        addActor(currentUsd)
        currentUsd.apply {
            setBounds(425f, 1173f, 320f, 121f)
        }

        addActors(rub, usd)
        rub.setBounds(232f, 755f, 497f, 96f)
        usd.setBounds(232f, 578f, 497f, 96f)
    }

    private fun AdvancedGroup.addUSDT() {
        addActors(usdy, currentBotUsd)
        usdy.setBounds(425f, 1073f, 320f, 320f)
        currentBotUsd.setBounds(55f, 602f, 138f, 47f)

        usdy.setOnClickListener { if (isShow) hideScroll() else showScroll() }

    }

    private fun AdvancedGroup.addScroll() {
        addActor(whiter)
        whiter.apply {
            setBounds(23f, 94f, 753f, 886f)
            addAction(Actions.alpha(0f))
            disable()
        }
        addActor(scrollPane)
        scrollPane.apply {
            setBounds(135f, 62f, 530f, 928f)
            addAction(Actions.alpha(0f))
            disable()
        }

        (currenciesMap ?: mapOf()).onEach {
            verticalG.addActor(ACurrency(it.key, it.value).apply {
                setSize(530f, 138f)
                blockSelectCurrency = { name, cost ->
                    currencyFlow.value = name to cost
                    hideScroll()
                }
            })
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    var isShow = false

    private fun showScroll() {
        isShow = true
        whiter.clearActions()
        scrollPane.clearActions()
        whiter.addAction(Actions.fadeIn(0.4f))
        scrollPane.addAction(Actions.fadeIn(0.4f))
        scrollPane.enable()
    }

    private fun hideScroll() {
        isShow = false
        whiter.clearActions()
        scrollPane.clearActions()
        whiter.addAction(Actions.fadeOut(0.4f))
        scrollPane.addAction(Actions.fadeOut(0.4f))
        scrollPane.disable()
    }



}