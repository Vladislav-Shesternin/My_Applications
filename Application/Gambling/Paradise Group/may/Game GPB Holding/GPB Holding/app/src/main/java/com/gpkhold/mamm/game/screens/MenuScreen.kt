package com.gpkhold.mamm.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gpkhold.mamm.game.manager.NavigationManager
import com.gpkhold.mamm.game.utils.actor.setOnClickListener
import com.gpkhold.mamm.game.utils.advanced.AdvancedGroup
import com.gpkhold.mamm.game.utils.advanced.AdvancedScreen
import com.gpkhold.mamm.util.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var currenciesMap: Map<String, Double>? = null
    private set
class MenuScreen: AdvancedScreen() {

    private val panelImage = Image(themeUtil.MEN)
    private val v1Button   = Actor()
    private val v2Button   = Actor()
    private val v3Button   = Actor()


    override fun show() {
        coroutine.launch(Dispatchers.IO) {
            currenciesMap = NetworkUtil.service.getCurrencies().rub
        }
        super.show()
    }
    override fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
        addButtons()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(panelImage)
        panelImage.setBounds(69f, 350f, 663f, 1068f)
    }

    private fun AdvancedGroup.addButtons() {
        addActors(v1Button, v2Button, v3Button)
        v1Button.apply {
            setBounds(137f, 850f, 526f, 202f)
            setOnClickListener { NavigationManager.navigate(ConverterScreen(), MenuScreen()) }
        }
        v2Button.apply {
            setBounds(137f, 600f, 526f, 202f)
            setOnClickListener { NavigationManager.navigate(ThemeScreen(), MenuScreen()) }
        }
        v3Button.apply {
            setBounds(137f, 350f, 526f, 202f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

}