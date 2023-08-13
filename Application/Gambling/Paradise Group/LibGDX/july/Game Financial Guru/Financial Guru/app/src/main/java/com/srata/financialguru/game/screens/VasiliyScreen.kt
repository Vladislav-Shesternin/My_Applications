package com.srata.financialguru.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.srata.financialguru.BuildConfig
import com.srata.financialguru.R
import com.srata.financialguru.game.actors.CountryListWithShadow
import com.srata.financialguru.game.colorSKY
import com.srata.financialguru.game.game
import com.srata.financialguru.game.manager.FontTTFManager
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.actor.animShow
import com.srata.financialguru.game.utils.actor.animSuspendVisible
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.game.utils.advanced.AdvancedStage
import com.srata.financialguru.game.utils.numStr
import com.srata.financialguru.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class VasiliyScreen: AdvancedScreen() {

    private val vasaImg   = Image(SpriteManager.GameRegion.VASA.region)
    private val balImg    = Image(SpriteManager.GameRegion.BAL.region)
    private val balLbl    = Label("$${numStr(10,99, 1)},000", Label.LabelStyle(FontTTFManager.GilMed.font_70.font, Color.WHITE))
    private val menuGaImg = Image(SpriteManager.GameRegion.MENU_GA.region)
    private val countrySp = CountryListWithShadow()
    private val menuImg   = Image(SpriteManager.GameRegion.MENU_HOMI.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        colorSKY = Color.WHITE
        game.activity.setNavigationBarColor(R.color.white)

        coroutine.launch {
            runGDX {
                addVasaBalance()
                addMenuGa()
                stageUI.addListWithShadow()
                stageUI.addMenu()
            }
            vasaImg.animSuspendVisible(0.4f)
            balImg.animSuspendVisible(0.4f)
            balLbl.animSuspendVisible(0.4f)
            menuGaImg.animSuspendVisible(0.4f)
            countrySp.animSuspendVisible(0.4f)

            animMenu()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addVasaBalance() {
        addActor(vasaImg)
        vasaImg.apply {
            setBounds(31f, 1292f, 626f, 113f)
            animInvisible()
        }
        addActor(balImg)
        balImg.apply {
            setBounds(31f, 1064f, 626f, 192f)
            animInvisible()
        }
        addActor(balLbl)
        balLbl.apply {
            setBounds(60f, 1093f, 438f, 86f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addMenuGa() {
        addActor(menuGaImg)
        menuGaImg.apply {
            setBounds(31f, 876f, 626f, 132f)
            animInvisible()
        }

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()
        addActors(c1,c2,c3,c4)
        c1.apply {
            setBounds(31f, 876f, 105f, 132f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(MoyBalansScreen(), VasiliyScreen())
                }
            }
        }
        c2.apply {
            setBounds(219f, 876f, 91f, 132f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(KursiValutScreen(), VasiliyScreen())
                }
            }
        }
        c3.apply {
            setBounds(393f, 876f, 104f, 132f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(AnalizScreen(), VasiliyScreen())
                }
            }
        }
        c4.apply {
            setBounds(579f, 883f, 79f, 125f)
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

    private fun AdvancedStage.addListWithShadow() {
        addActor(countrySp)
        countrySp.apply {
            setBounds(28f, -449f, 635f, 1271f)
            animInvisible()
            showBlock = { mainGroup.animInvisible(0.2f) }
            hideBlock = { mainGroup.animShow(0.4f) }
        }
        val whe = Image(SpriteManager.GameRegion.WHITE.region)
        addActor(whe)
        whe.setBounds(0f,0f, 691f, 181f)
    }

    private fun AdvancedStage.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(10f, -185f, 670f, 185f)

        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()
        addActors(c2,c3,c4)
        c2.apply {
            setBounds(273f, 58f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(AnalizScreen(), VasiliyScreen())
                }
            }
        }
        c3.apply {
            setBounds(402f, 58f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(MoyBalansScreen(), VasiliyScreen())
                }
            }
        }
        c4.apply {
            setBounds(528f, 58f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(KursiValutScreen(), VasiliyScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            menuImg.addAction(
                Actions.sequence(
                    Actions.moveTo(10f, -3f, 0.33f, Interpolation.swing),
                    Actions.run { continuation.complete(Unit) }
                ))

        }
    }.await()

}