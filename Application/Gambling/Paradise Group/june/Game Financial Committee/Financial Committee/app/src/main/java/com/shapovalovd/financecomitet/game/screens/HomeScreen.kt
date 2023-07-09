package com.shapovalovd.financecomitet.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.shapovalovd.financecomitet.game.actors.Budrek
import com.shapovalovd.financecomitet.game.actors.CoinPanel
import com.shapovalovd.financecomitet.game.actors.scroll.HorizontalGroup
import com.shapovalovd.financecomitet.game.actors.scroll.VerticalGroup
import com.shapovalovd.financecomitet.game.manager.FontTTFManager
import com.shapovalovd.financecomitet.game.manager.GameDataStoreManager
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.GameColor
import com.shapovalovd.financecomitet.game.utils.actor.setOnClickListener
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedScreen
import com.shapovalovd.financecomitet.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreen: AdvancedScreen() {

    private val balancePercent = (0..45).shuffled().first()
    private val balancePDot    = (0..79).shuffled().first()

    private val homeImage          = Image(SpriteManager.GameRegion.HOMEBALANSE.region)
    private val settingsPanelImage = Image(SpriteManager.GameRegion.SETTING_PANNEL.region)
    private val balanceLabel       = Label("$ " + (((1..50).shuffled().first() * 1000) + (1..9).shuffled().first() * 100).toDouble().toString(), Label.LabelStyle(FontTTFManager.PopSemiBold.font_34.font, Color.WHITE))
    private val balancePerceLabel  = Label("+ $balancePercent.$balancePDot%", Label.LabelStyle(FontTTFManager.PopMedium.font_15.font, GameColor.gri))
    private val horizontalGroup    = HorizontalGroup(17f)
    private val scrollPane         = ScrollPane(horizontalGroup)
    private val vertGroup          = VerticalGroup(18f, startGap = 150f)
    private val vscrollPane        = ScrollPane(vertGroup)
    private val budrek             = Budrek()
    private val settinges          = Actor()
    private val exitInfo           = Actor()

    override fun AdvancedGroup.addActorsOnGroup() {
        addHome()
        addVerScr()
        addSetPanel()
        addBalance()
        addScrollPane()
        addBudrek()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addHome() {
        addActor(homeImage)
        homeImage.setBounds(0f, 0f, 677f, 1394f)
    }
    private fun AdvancedGroup.addSetPanel() {
        addActor(settingsPanelImage)
        settingsPanelImage.setBounds(-48f, -131f, 726f, 348f)

        addActors(settinges, exitInfo)
        settinges.apply {
            setBounds(308f, 38f, 61f, 62f)
            setOnClickListener { budrek.showAct() }
        }
        exitInfo.apply {
            setBounds(525f, 22f, 98f, 93f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

    private fun AdvancedGroup.addBalance() {
        addActor(balanceLabel)
        balanceLabel.setBounds(160f, 1241f, 357f, 40f)
        balanceLabel.setAlignment(Align.center)

        addActor(balancePerceLabel)
        balancePerceLabel.setBounds(301f, 1292f, 89f, 23f)
        balancePerceLabel.setAlignment(Align.center)
    }

    private fun AdvancedGroup.addScrollPane() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 787f, 677f, 230f)

        SpriteManager.ListRegion.CARTOCHKI.regionList.shuffled().onEach { reg ->
            horizontalGroup.addActor(Image(reg).apply { setSize(423f, 230f) })
        }
    }

    private fun AdvancedGroup.addVerScr() {
        addActor(vscrollPane)
        vscrollPane.setBounds(34f, 35f, 607f, 607f)

        val nameste = listOf("BTC", "USDT", "ETH", "BNB", "XRP", "ADA", "DOGE", "LEO", "UNI", "XLM", "ICP", "TON", "FGC", "RPL", "EOS", "QNT", "APE", "GRT",).shuffled()

        SpriteManager.ListRegion.COINS.regionList.shuffled().onEachIndexed { ing, reg ->
            vertGroup.addActor(CoinPanel(reg, nameste[ing]).apply { setSize(607f, 138f) })
        }
    }

    private fun AdvancedGroup.addBudrek() {
        addActor(budrek)
        budrek.setBounds(0f, -1466f, 677f, 1466f)
    }

}