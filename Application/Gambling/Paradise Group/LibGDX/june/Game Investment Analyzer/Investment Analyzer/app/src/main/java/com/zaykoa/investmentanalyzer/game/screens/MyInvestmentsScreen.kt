package com.zaykoa.investmentanalyzer.game.screens

import android.content.Intent
import android.graphics.Paint.Align
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.zaykoa.investmentanalyzer.BuildConfig
import com.zaykoa.investmentanalyzer.R
import com.zaykoa.investmentanalyzer.game.actors.scroll.HorizontalGroup
import com.zaykoa.investmentanalyzer.game.actors.scroll.VerticalGroup
import com.zaykoa.investmentanalyzer.game.game
import com.zaykoa.investmentanalyzer.game.manager.FontTTFManager
import com.zaykoa.investmentanalyzer.game.manager.NavigationManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.animShow
import com.zaykoa.investmentanalyzer.game.utils.actor.animSuspendShow
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.numStr
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyInvestmentsScreen: AdvancedScreen() {

    private val investisiImg = Image(SpriteManager.GameRegion.INVESTISI.region)
    private val companys     = List(20) { CompanyData(companyNames[it], SpriteManager.ListRegion.COMPANYS_MINI.regionList[it]) }.shuffled()
    private val verGroup     = VerticalGroup(gap = -12f, 70f, 20f)
    private val scrollPane   = ScrollPane(verGroup)
    private val menuInvestIm = Image(SpriteManager.GameRegion.INVEST_MENU.region)
    private val listCompansy = List(20) { Image() }
    private val graphImg     = Image(SpriteManager.GameRegion.CIRCLE_COLOROVA.region)
    private val balankik     = Label(
        "$${numStr(10,783, 1)},${numStr(100,999,1)}.${numStr(0,9,2)}",
        Label.LabelStyle(FontTTFManager.PopSemBold.font_48.font, Color.BLACK)
    )

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addInvestisi()
                addCompanies()
                addMenuSearch()
                addCircleColorova()
                addBalank()
                addBackNopka()
            }

            animInvestisi(0.7f)
            animGraph(0.6f)
            balankik.animSuspendShow(0.5f)
            launch {
                listCompansy.onEach { it.animSuspendShow(0.4f) }
            }
            delay(1000)
            menuInvestIm.animSuspendShow(0.5f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addInvestisi() {
        addActor(investisiImg)
        investisiImg.apply {
            setBounds(23f, 770f, 645f, 521f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addCompanies() {
        addActors(scrollPane)
        scrollPane.setBounds(23f, 70f, 645f, 795f)

        companys.onEachIndexed { index, comp ->
            verGroup.addActor(listCompansy[index].apply {
                drawable = TextureRegionDrawable(comp.companyRegion)

                setSize(645f, 169f)
                addAction(Actions.alpha(0f))

                addListener(object : ClickListener(){
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        mainGroup.animHide(0.3f) {
                            namesik = comp.companyName
                            NavigationManager.navigate(BuySellChartScreen(), MyInvestmentsScreen())
                        }
                    }
                })
            })
        }
    }

    private fun AdvancedGroup.addMenuSearch() {
        addActor(menuInvestIm)
        menuInvestIm.apply {
            setBounds(0f, 0f, 692f, 122f)
            addAction(Actions.alpha(0f))
        }

        val homeA   = Actor()
        val profilA = Actor()
        addActors(homeA, profilA)
        homeA.apply {
            setBounds(77f, 16f, 86f, 86f)
            setOnClickListener { NavigationManager.back() }
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

    private fun AdvancedGroup.addBackNopka() {
        val backc = Actor()
        addActor(backc)
        backc.apply {
            setBounds(0f, 1330f, 113f, 113f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addCircleColorova() {
        addActor(graphImg)
        graphImg.apply {
            setBounds(201f, 1001f, 156f, 156f)
            animHide()
        }
    }

    private fun AdvancedGroup.addBalank() {
        addActor(balankik)
        balankik.apply {
            setBounds(83f, 1202f, 470f, 57f)
            animHide()
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animInvestisi(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            investisiImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(23f, 888f, time, Interpolation.exp5),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animGraph(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            graphImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(83f, 1001f, time, Interpolation.exp10Out),
                    Actions.fadeIn(time),
                ),
                Actions.run {
                    graphImg.setOrigin(com.badlogic.gdx.utils.Align.center)
                    graphImg.addAction(Actions.forever(Actions.rotateBy(-360f, 3.5f, Interpolation.pow5)))
                    continuation.complete(Unit)
                }
            ))
        }
    }.await()

}