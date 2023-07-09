package com.zaykoa.investmentanalyzer.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.zaykoa.investmentanalyzer.BuildConfig
import com.zaykoa.investmentanalyzer.R
import com.zaykoa.investmentanalyzer.game.actors.FilterGroup
import com.zaykoa.investmentanalyzer.game.actors.scroll.HorizontalGroup
import com.zaykoa.investmentanalyzer.game.game
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.animShow
import com.zaykoa.investmentanalyzer.game.utils.actor.animSuspendShow
import com.zaykoa.investmentanalyzer.game.utils.actor.disable
import com.zaykoa.investmentanalyzer.game.utils.actor.enable
import com.zaykoa.investmentanalyzer.game.utils.actor.setBounds
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class MainScreen: AdvancedScreen() {

    private val topikImg = Image(SpriteManager.GameRegion.TOPIK_NA_MOTIK.region)
    private val companys = List(20) { CompanyData(companyNames[it], SpriteManager.ListRegion.COMPANYS.regionList[it]) }.shuffled()
    private val horizontalGroup1 = HorizontalGroup(24f)
    private val scrollPane1      = ScrollPane(horizontalGroup1)
    private val horizontalGroup2 = HorizontalGroup(24f)
    private val scrollPane2      = ScrollPane(horizontalGroup2)
    private val menuSearchImg    = Image(SpriteManager.GameRegion.SEARCH.region)
    private val filterGroup      = FilterGroup()
    private val imgis1 = List(10) { Image() }
    private val imgis2 = List(10) { Image() }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addTopik()
                addCompanies()
                addMenuSearch()
                addFilter()
            }
            topikImg.animSuspendShow(0.5f)
            horizontalGroup1.animSuspendShow(0.4f)
            horizontalGroup2.animSuspendShow(0.4f)
            menuSearchImg.animSuspendShow(0.5f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addTopik() {
        addActor(topikImg)
        topikImg.apply {
            setBounds(-1f, 548f, 744f, 950f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addCompanies() {
        addActors(scrollPane1, scrollPane2)
        scrollPane1.setBounds(2f, 610f, 690f, 423f)
        scrollPane2.setBounds(2f, 116f, 690f, 423f)
        horizontalGroup1.animHide()
        horizontalGroup2.animHide()

        companys.take(10).onEachIndexed { index, com ->
            horizontalGroup1.addActor(imgis1[index].apply {
                drawable = TextureRegionDrawable(com.companyRegion)
                setSize(406f, 423f)

                addListener(object : ClickListener(){
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        mainGroup.animHide(0.3f) {
                            namesik = com.companyName
                            NavigationManager.navigate(BuySellChartScreen(), MainScreen())
                        }
                    }
                })
            })
        }
        companys.takeLast(10).onEachIndexed { index, com ->
            horizontalGroup2.addActor(imgis2[index].apply {
                drawable = TextureRegionDrawable(com.companyRegion)
                setSize(406f, 423f)

                addListener(object : ClickListener(){
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        mainGroup.animHide(0.3f) {
                            namesik = com.companyName
                            NavigationManager.navigate(BuySellChartScreen(), MainScreen())
                        }
                    }
                })
            })
        }
    }

    private fun AdvancedGroup.addMenuSearch() {
        addActor(menuSearchImg)
        menuSearchImg.apply {
            setBounds(0f, 0f, 692f, 122f)
            addAction(Actions.alpha(0f))
        }

        val investA = Actor()
        val profilA = Actor()
        addActors(investA, profilA)
        investA.apply {
            setBounds(300f, 16f, 86f, 86f)
            setOnClickListener { mainGroup.animHide { NavigationManager.navigate(MyInvestmentsScreen(), MainScreen()) } }
        }
        profilA.apply {
            setBounds(535f, 16f, 86f, 86f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
    }

    private fun AdvancedGroup.addFilter() {
        val filterClick = Actor()
        addActor(filterClick)
        filterClick.apply {
            setBounds(568f, 1145f, 90f, 90f)
            setOnClickListener {
                filterGroup.enable()
                filterGroup.animShow(0.7f)
            }
        }

        addActor(filterGroup)
        filterGroup.apply {
            disable()
            setBounds(27f, 219f, 667f, 1004f)
            addAction(Actions.alpha(0f))

            blockich = {
                companys.shuffled().also { compansss ->
                    compansss.take(10).onEachIndexed { index, companyData ->
                        imgis1[index].drawable = TextureRegionDrawable(companyData.companyRegion)
                    }
                    compansss.takeLast(10).onEachIndexed { index, companyData ->
                        imgis2[index].drawable = TextureRegionDrawable(companyData.companyRegion)
                    }
                }
            }
        }
    }

}

val companyNames = listOf(
    "Apple Inc",
    "Adobe Systems",
    "Advanced Micro",
    "Amazon.com Inc",
    "Activision Blizzard",
    "Boeing Company",
    "Alibaba Group",
    "Bank of America",
    "Meta Platforms...",
    "First Solar Inc",
    "Berkshire Hath...",
    "Cisco Systems",
    "Chevron Corp",
    "Delta Air Lines Inc",
    "Walt Disney Co",
    "Electronic Arts",
    "eBay Inc",
    "Ford Motor",
    "General Electric",
    "General Motors...",
)

data class CompanyData(
    val companyName  : String,
    val companyRegion: TextureRegion
)