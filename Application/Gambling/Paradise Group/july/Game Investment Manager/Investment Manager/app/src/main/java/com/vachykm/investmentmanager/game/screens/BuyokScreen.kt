package com.vachykm.investmentmanager.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.vachykm.investmentmanager.BuildConfig
import com.vachykm.investmentmanager.R
import com.vachykm.investmentmanager.game.actors.ScrollPaneCompany
import com.vachykm.investmentmanager.game.actors.button.AButton
import com.vachykm.investmentmanager.game.actors.button.AButtonStyle
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBox
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBoxGroup
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBoxStyle
import com.vachykm.investmentmanager.game.colorit
import com.vachykm.investmentmanager.game.game
import com.vachykm.investmentmanager.game.manager.FontTTFManager
import com.vachykm.investmentmanager.game.manager.NavigationManager
import com.vachykm.investmentmanager.game.manager.SpriteManager
import com.vachykm.investmentmanager.game.utils.GameColor
import com.vachykm.investmentmanager.game.utils.actor.animHide
import com.vachykm.investmentmanager.game.utils.actor.animShow
import com.vachykm.investmentmanager.game.utils.actor.animSuspendShow
import com.vachykm.investmentmanager.game.utils.actor.setOnClickListener
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedScreen
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedStage
import com.vachykm.investmentmanager.game.utils.numStr
import com.vachykm.investmentmanager.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

var logogoStatik: TextureRegion? = null
var namemeStatik = "Apple"

class BuyokScreen: AdvancedScreen() {

    private val listDiagra = listOf(
        SpriteManager.GameRegion.T1.region,
        SpriteManager.GameRegion.T2.region,
        SpriteManager.GameRegion.T3.region,
        SpriteManager.GameRegion.T4.region,
        SpriteManager.GameRegion.T5.region,
    )

    private val headerImg     = Image(SpriteManager.GameRegion.HEADER_DARK.region)
    private val uverhImg      = Image(SpriteManager.GameRegion.UVERH.region)
    private val logoImg       = Image(logogoStatik ?: SpriteManager.ListRegion.ITEMS.regionList.first())
    private val nameLbl       = Label(namemeStatik, Label.LabelStyle(FontTTFManager.GilSemBold.font_25.font, Color.BLACK))
    private val vncImg        = Image(SpriteManager.GameRegion.V_N_C.region)
    private val timeLImg      = Image(SpriteManager.GameRegion.TIME_LIST.region)
    private val firstCB       = ACheckBox(ACheckBoxStyle._1h)
    private val diagrammaImg  = Image(listDiagra.random())
    private val buynaBtn      = AButton(AButtonStyle.buyna)
    private val selodkaBtn    = AButton(AButtonStyle.selodka)
    private val perceLbl      = Label("+${numStr(1,9,1)}.${numStr(1,99,1)}%", Label.LabelStyle(FontTTFManager.GilBold.font_19.font, GameColor.purple))
    private val visokoLbl     = Label("$${numStr(10,999,1)}.${numStr(10,999,1)}", Label.LabelStyle(FontTTFManager.GilMed.font_22.font, Color.BLACK))
    private val nizkoLbl      = Label("$${numStr(10,999,1)}.${numStr(10,999,1)}", Label.LabelStyle(FontTTFManager.GilMed.font_22.font, Color.BLACK))
    private val sredneLbl     = Label("$${numStr(10,999,1)}.${numStr(10,999,1)}", Label.LabelStyle(FontTTFManager.GilMed.font_22.font, Color.BLACK))

    private val menuImg       = Image(SpriteManager.GameRegion.M_BUYOK.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addHeader()
                addUverh()
                addNAmLoGo()
                addVNC()
                addTimeL()
                addDiagro()
                addBS()
                addLabeleses()

                add3tochki()
                stageUI.addMenu()
            }
            headerImg.animSuspendShow(0.4f)
            uverhImg.animSuspendShow(0.4f)
            logoImg.animSuspendShow(0.4f)
            nameLbl.animSuspendShow(0.4f)
            vncImg.animSuspendShow(0.4f)
            timeLImg.animSuspendShow(0.4f)
            firstCB.check()
            diagrammaImg.animSuspendShow(0.4f)
            buynaBtn.animSuspendShow(0.4f)
            selodkaBtn.animSuspendShow(0.4f)
            perceLbl.animSuspendShow(0.4f)
            visokoLbl.animSuspendShow(0.4f)
            nizkoLbl.animSuspendShow(0.4f)
            sredneLbl.animSuspendShow(0.4f)

            animMenu()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addHeader() {
        addActor(headerImg)
        headerImg.apply {
            setBounds(27f, 1285f, 601f, 62f)
            animHide()
        }
    }

    private fun AdvancedGroup.addUverh() {
        addActor(uverhImg)
        uverhImg.apply {
            setBounds(463f, 1165f, 143f, 55f)
            animHide()
        }
    }

    private fun AdvancedGroup.addNAmLoGo() {
        addActors(logoImg, nameLbl)
        logoImg.apply {
            setBounds(44f, 1159f, 67f, 67f)
            animHide()
        }
        nameLbl.apply {
            setBounds(129f, 1168f, 315f, 48f)
            animHide()
        }
    }

    private fun AdvancedGroup.addVNC() {
        addActor(vncImg)
        vncImg.apply {
            setBounds(72f, 1055f, 509f, 23f)
            animHide()
        }
    }

    private fun AdvancedGroup.addTimeL() {
        addActor(timeLImg)
        timeLImg.apply {
            setBounds(72f, 908f, 504f, 23f)
            animHide()
        }

        val cbg = ACheckBoxGroup()

        val listX = listOf(48f, 146f, 243f, 340f, 438f, 535f)

        listOf(
            firstCB,
            ACheckBox(ACheckBoxStyle._6h),
            ACheckBox(ACheckBoxStyle._24h),
            ACheckBox(ACheckBoxStyle._1w),
            ACheckBox(ACheckBoxStyle._1m),
            ACheckBox(ACheckBoxStyle._1y),
        ).onEachIndexed { index, cb ->
            addActor(cb)
            cb.checkBoxGroup = cbg
            cb.setBounds(listX[index], 896f, 66f, 42f)
            cb.setOnCheckListener { if (it) diagrammaImg.drawable = TextureRegionDrawable(listDiagra.random()) }
        }
    }

    private fun AdvancedGroup.addDiagro() {
        addActor(diagrammaImg)
        diagrammaImg.apply {
            setBounds(22f, 268f, 599f, 595f)
            animHide()
        }
    }

    private fun AdvancedGroup.addBS() {
        addActors(buynaBtn, selodkaBtn)
        buynaBtn.apply {
            setBounds(28f, 155f, 281f, 59f)
            animHide()
            setOnClickListener {
                disable()
                NavigationManager.back()
            }
        }
        selodkaBtn.apply {
            setBounds(332f, 155f, 281f, 59f)
            animHide()
            setOnClickListener {
                disable()
                NavigationManager.back()
            }
        }
    }

    private fun AdvancedGroup.addLabeleses() {
        addActors(perceLbl, visokoLbl, nizkoLbl, sredneLbl)
        perceLbl.apply {
            setBounds(471f, 1176f, 87f, 32f)
            setAlignment(Align.center)
            animHide()
        }
        visokoLbl.apply {
            setBounds(45f, 1019f, 140f, 27f)
            setAlignment(Align.center)
            animHide()
        }
        nizkoLbl.apply {
            setBounds(258f, 1019f, 140f, 27f)
            setAlignment(Align.center)
            animHide()
        }
        sredneLbl.apply {
            setBounds(467f, 1019f, 140f, 27f)
            setAlignment(Align.center)
            animHide()
        }
    }

    private fun AdvancedGroup.add3tochki() {
        val _3toc = Actor()
        addActor(_3toc)
        _3toc.apply {
            setBounds(577f, 1279f, 73f, 73f)
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

    private fun AdvancedStage.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(-76f, -265f, 802f, 265f)

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()
        addActors(c1,c2,c3,c4)
        c1.apply {
            setBounds(56f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(HomeSapienceScreen(), BuyokScreen())
                }
            }
        }
        c2.apply {
            setBounds(217f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(WalletScreen(), BuyokScreen())
                }
            }
        }
        c3.apply {
            setBounds(378f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(DebtsScreen(), BuyokScreen())
                }
            }
        }
        c4.apply {
            setBounds(539f, 25f, 56f, 56f)
            setOnClickListener {  }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            menuImg.addAction(Actions.sequence(
                Actions.moveTo(-76f, -79f, 0.5f, Interpolation.bounceOut),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

}