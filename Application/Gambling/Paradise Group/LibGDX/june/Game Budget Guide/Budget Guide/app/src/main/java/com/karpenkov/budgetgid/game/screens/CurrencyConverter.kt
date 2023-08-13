package com.karpenkov.budgetgid.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.karpenkov.budgetgid.game.actors.Borger
import com.karpenkov.budgetgid.game.actors.CurrencyItem
import com.karpenkov.budgetgid.game.actors.ListCurrency
import com.karpenkov.budgetgid.game.manager.FontTTFManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.utils.actor.setBounds
import com.karpenkov.budgetgid.game.utils.actor.setOnClickListener
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedScreen
import com.karpenkov.budgetgid.game.utils.Layout.Converter as LCC


private val valutaList     = listOf(
    "ALL",
    "ARS",
    "CLF",
    "SMT",
    "KLF",
    "EPT",
    "IIU",
    "OLK",
    "HJG",
    "LMN",
    "NPL",
    "UAH",
)
private val countryList    = listOf(
    "Албания",
    "Аргентина",
    "Чили",
    "Германия",
    "Гайана",
    "Египет",
    "Куба",
    "Доминикана",
    "Индонезия",
    "Люксембург",
    "Непал",
    "Украина",
)
private val dolarRatioList = List<Double>(12) { (90..5000).shuffled().first() / 100.0 }

data class Currency(
    val flagRegion: TextureRegion,
    val valuta    : String,
    val country   : String,
    val dolarRatio: Double,
)
class CurrencyConverter: AdvancedScreen() {

    private val converterPanelImage = Image(SpriteManager.GameRegion.CONVERTER.region)
    private val topI                =  CurrencyItem()
    private val botI                =  CurrencyItem()
    private val valutaLabelTop      = Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val valutaLabelBot      = Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val dolarRatioLabelTop  = Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val dolarRatioLabelBot  = Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val flagImageTop        = Image()
    private val flagImageBot        = Image()
    private val listCurrency        = ListCurrency()
    private val borger              = Borger()

    private lateinit var currencyList: List<Currency>

    private var isTop = true

    override fun show() {
        currencyList = List(12) { Currency(
            SpriteManager.ListRegion.FLAG.regionList[it],
            valutaList[it],
            countryList[it],
            dolarRatioList[it]
        ) }

        setBackgrounds(background ?: SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
        addItem()
        addTopBot()
        addBorger()
        addListCurr()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addPanel() {
        addActor(converterPanelImage)
        converterPanelImage.setBounds(LCC.panel)
    }

    private fun AdvancedGroup.addItem() {
        addActors(topI, botI)
        topI.apply {
            setItem(currencyList.first().flagRegion, currencyList.first().valuta)
            setBounds(41f, 864f, 243f, 243f)
            setOnClickListener {
                isTop = true
                showListCurr() }
        }
        botI.apply {
            setItem(currencyList.last().flagRegion, currencyList.last().valuta)
            setBounds(399f, 502f, 243f, 243f)
            setOnClickListener {
                isTop = false
                showListCurr() }
        }
    }

    private fun AdvancedGroup.addTopBot() {
        addActors(
            valutaLabelTop, valutaLabelBot,
            flagImageTop, flagImageBot,
            dolarRatioLabelTop, dolarRatioLabelBot,
        )

        // Top
        valutaLabelTop.apply {
            setBounds(86f, 396f, 173f, 59f)
            setText(currencyList.first().valuta)
        }
        flagImageTop.apply {
            setBounds(514f, 316f, 88f, 68f)
            drawable = TextureRegionDrawable(currencyList.first().flagRegion)
        }
        dolarRatioLabelTop.apply {
            setBounds(82f, 316f, 416f, 68f)
            setText(currencyList.first().dolarRatio.toString())
        }

        // Bot
        valutaLabelBot.apply {
            setBounds(86f, 208f, 173f, 59f)
            setText(currencyList.last().valuta)
        }
        flagImageBot.apply {
            setBounds(514f, 128f, 88f, 68f)
            drawable = TextureRegionDrawable(currencyList.last().flagRegion)
        }
        dolarRatioLabelBot.apply {
            setBounds(82f, 128f, 416f, 68f)
            setText(currencyList.last().dolarRatio.toString())
        }

    }

    private fun AdvancedGroup.addListCurr() {
        addActor(listCurrency)
        listCurrency.setBounds(0f, -1301f, 690f, 1301f)
        listCurrency.addAction(Actions.alpha(0f))
        listCurrency.initialize(currencyList) {
            if (isTop) {
                topI.setItem(it.flagRegion, it.valuta)
                valutaLabelTop.setText(it.valuta)
                flagImageTop.drawable = TextureRegionDrawable(it.flagRegion)
                dolarRatioLabelTop.setText(it.dolarRatio.toString())
            } else {
                botI.setItem(it.flagRegion, it.valuta)
                valutaLabelBot.setText(it.valuta)
                flagImageBot.drawable = TextureRegionDrawable(it.flagRegion)
                dolarRatioLabelBot.setText(it.dolarRatio.toString())
            }
            hideListCurr()
        }

    }

    private fun AdvancedGroup.addBorger() {
        addActor(borger)
        borger.setBounds(-549f, 0f, 642f, 1301f)
        borger.block = {
            if (it) borger.addAction(Actions.moveTo(0f, 0f, 0.5f))
            else borger.addAction(Actions.moveTo(-549f, 0f, 0.5f))
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun showListCurr() {
        listCurrency.addAction(Actions.parallel(
            Actions.fadeIn(0.5f),
            Actions.moveTo(0f, 0f, 0.5f)
        ))
    }

    private fun hideListCurr() {
        listCurrency.addAction(Actions.parallel(
            Actions.fadeOut(0.5f),
            Actions.moveTo(0f, -1301f, 0.5f)
        ))
    }

}