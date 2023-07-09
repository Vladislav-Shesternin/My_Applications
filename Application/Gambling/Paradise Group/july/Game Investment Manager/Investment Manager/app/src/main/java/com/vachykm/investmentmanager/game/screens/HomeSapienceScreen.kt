package com.vachykm.investmentmanager.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vachykm.investmentmanager.BuildConfig
import com.vachykm.investmentmanager.R
import com.vachykm.investmentmanager.game.actors.ListikGroup
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBox
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBoxGroup
import com.vachykm.investmentmanager.game.actors.checkbox.ACheckBoxStyle
import com.vachykm.investmentmanager.game.colorit
import com.vachykm.investmentmanager.game.game
import com.vachykm.investmentmanager.game.manager.FontTTFManager
import com.vachykm.investmentmanager.game.manager.NavigationManager
import com.vachykm.investmentmanager.game.manager.SpriteManager
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

class HomeSapienceScreen: AdvancedScreen() {

    private val headerImg = Image(SpriteManager.GameRegion.HEADER_DARK.region)
    private val balImg    = Image(SpriteManager.GameRegion.OBSCH_BAL.region)
    private val balLbl    = Label("$${numStr(1,100, 1)},${numStr(100, 999, 1)}.00", Label.LabelStyle(FontTTFManager.GilMed.font_62.font, Color.WHITE))
    private val boxGroup  = ACheckBoxGroup()
    private val depozBox  = ACheckBox(ACheckBoxStyle.depoz)
    private val otzivBox  = ACheckBox(ACheckBoxStyle.otziv)
    private val listik    = ListikGroup()
    private val menuImg  = Image(SpriteManager.GameRegion.M_HOME.region)



    override fun AdvancedGroup.addActorsOnGroup() {
        colorit = Color.WHITE
        game.activity.setNavigationBarColor(R.color.white)

        coroutine.launch {
            runGDX {
                addHeader()
                addBal()
                addDepOtz()
                add3tochki()
                stageUI.addListik()
                stageUI.addMenu()
            }
            headerImg.animSuspendShow(0.4f)
            balImg.animSuspendShow(0.4f)
            balLbl.animSuspendShow(0.4f)
            depozBox.check()
            animListik(0.7f)
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

    private fun AdvancedGroup.addBal() {
        addActors(balImg, balLbl)
        balImg.apply {
            setBounds(23f, 832f, 606f, 362f)
            animHide()
        }
        balLbl.apply {
            setBounds(101f, 1095f, 465f, 76f)
            setAlignment(Align.center)
            animHide()
        }
    }

    private fun AdvancedGroup.addDepOtz() {
        addActors(depozBox, otzivBox)
        depozBox.apply {
            checkBoxGroup = boxGroup
            setBounds(37f, 824f, 319f, 75f)
            setOnCheckListener { if (it) listik.update() }
        }
        otzivBox.apply {
            checkBoxGroup = boxGroup
            setBounds(311f, 824f, 319f, 75f)
            setOnCheckListener { if (it) listik.update() }
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

    private fun AdvancedStage.addListik() {
        addActor(listik)
        listik.apply {
            setBounds(22f, -1261f, 606f, 1261f)
            animHide()
            showBlock = { mainGroup.animHide(0.2f) }
            hideBlock = { mainGroup.animShow(0.4f) }
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
            setOnClickListener {  }
        }
        c2.apply {
            setBounds(217f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(WalletScreen(), HomeSapienceScreen())
                }
            }
        }
        c3.apply {
            setBounds(378f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(DebtsScreen(), HomeSapienceScreen())
                }
            }
        }
        c4.apply {
            setBounds(539f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(BuyokScreen(), HomeSapienceScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animListik(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            listik.addAction(
                Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(22f, -460f, time, Interpolation.bounceOut),
                    Actions.fadeIn(time),
                ),
                Actions.run { continuation.complete(Unit) }
            ))

        }
    }.await()

    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            menuImg.addAction(
                Actions.sequence(
                    Actions.moveTo(-76f, -79f, 0.5f, Interpolation.bounceOut),
                    Actions.run { continuation.complete(Unit) }
                ))

        }
    }.await()

}