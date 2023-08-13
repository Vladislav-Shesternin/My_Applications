package com.srata.financialguru.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.srata.financialguru.game.actors.CountryListWithShadowItem
import com.srata.financialguru.game.actors.checkbox.ACheckBox
import com.srata.financialguru.game.actors.checkbox.ACheckBoxGroup
import com.srata.financialguru.game.actors.checkbox.ACheckBoxStyle
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.Layout
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.actor.animSuspendVisible
import com.srata.financialguru.game.utils.actor.setBounds
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.game.utils.advanced.AdvancedStage
import com.srata.financialguru.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch


var regS: TextureRegion? = null
var ttlS: String = "USD"

class AnalizScreen: AdvancedScreen() {

    private val dL = listOf(
        SpriteManager.GameRegion.D1.region,
        SpriteManager.GameRegion.D2.region,
        SpriteManager.GameRegion.D3.region,
        SpriteManager.GameRegion.D4.region,
        SpriteManager.GameRegion.D5.region,
        SpriteManager.GameRegion.D6.region,
        SpriteManager.GameRegion.D7.region,
        SpriteManager.GameRegion.D8.region,
        SpriteManager.GameRegion.D9.region,
    )

    private val golovaImg = Image(SpriteManager.GameRegion.ANALIZE.region)
    private val item      = CountryListWithShadowItem(regS ?: SpriteManager.ListRegion.ITEMS.regionList.random(), ttlS)
    private val nedela    = Image(SpriteManager.GameRegion.NEDELA.region)
    private val digerAM   = Image(dL.random())
    private val dniNeda   = Image(SpriteManager.GameRegion.DNI_NEDELA.region)
    private val mon       = ACheckBox(ACheckBoxStyle.MON)
    private val kp        = Image(SpriteManager.GameRegion.KUP_PROD.region)
    private val menuImg   = Image(SpriteManager.GameRegion.MENU_ANALIZE.region)



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addGolova()
                addItem()
                addNedela()
                addDiagraDM()
                addDniNeda()
                addKP()
                addMenu()
            }
            golovaImg.animSuspendVisible(0.41f)
            item.animSuspendVisible(0.41f)
            nedela.animSuspendVisible(0.41f)
            digerAM.animSuspendVisible(0.41f)
            dniNeda.animSuspendVisible(0.41f)
            mon.check()
            kp.animSuspendVisible(0.41f)
            animMenu()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGolova() {
        addActor(golovaImg)
        golovaImg.apply {
            setBounds(Layout.golova)
            animInvisible()
        }
        val nazad = Actor()
        addActor(nazad)
        nazad.apply {
            setBounds(11f, 1335f, 84f, 84f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addItem() {
        addActor(item)
        item.apply {
            setBounds(28f, 1164f, 635f, 152f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addNedela() {
        addActor(nedela)
        nedela.apply {
            setBounds(31f, 1088f, 279f, 30f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addDiagraDM() {
        addActor(digerAM)
        digerAM.apply {
            setBounds(31f, 589f, 626f, 442f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addDniNeda() {
        addActor(dniNeda)
        dniNeda.apply {
            setBounds(-41f, 450f, 775f, 194f)
            animInvisible()
        }

        val cbGGG = ACheckBoxGroup()
        var cbX = 32f
        listOf(
            mon,
            ACheckBox(ACheckBoxStyle.TUE),
            ACheckBox(ACheckBoxStyle.WED),
            ACheckBox(ACheckBoxStyle.THU),
            ACheckBox(ACheckBoxStyle.FRI),
            ACheckBox(ACheckBoxStyle.SAT),
            ACheckBox(ACheckBoxStyle.SUN),
        ).onEach { cb ->
            cb.checkBoxGroup = cbGGG
            addActor(cb)
            cb.setBounds(cbX, 532f, 70f, 42f)
            cbX += 93f
            cb.setOnCheckListener { digerAM.drawable = TextureRegionDrawable(dL.random()) }
        }

    }

    private fun AdvancedGroup.addKP() {
        addActor(kp)
        kp.apply {
            setBounds(31f, 249f, 626f, 88f)
            animInvisible()
        }
        val k = Actor()
        val p = Actor()
        addActors(k, p)
        k.apply {
            setBounds(31f, 249f, 296f, 88f)
            setOnClickListener { NavigationManager.back() }
        }
        p.apply {
            setBounds(352f, 249f, 296f, 88f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(10f, -185f, 670f, 185f)

        val c1 = Actor()
        val c3 = Actor()
        val c4 = Actor()
        addActors(c1,c3,c4)
        c1.apply {
            setBounds(67f, 98f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(VasiliyScreen(), AnalizScreen())
                }
            }
        }
        c3.apply {
            setBounds(411f, 98f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(MoyBalansScreen(), AnalizScreen())
                }
            }
        }
        c4.apply {
            setBounds(528f, 98f, 89f, 89f)
            setOnClickListener {
                stageUI.root.animInvisible(0.4f) {
                    NavigationManager.navigate(KursiValutScreen(), AnalizScreen())
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
                    Actions.moveTo(10f, 31f, 0.33f, Interpolation.swing),
                    Actions.run { continuation.complete(Unit) }
                ))

        }
    }.await()


}