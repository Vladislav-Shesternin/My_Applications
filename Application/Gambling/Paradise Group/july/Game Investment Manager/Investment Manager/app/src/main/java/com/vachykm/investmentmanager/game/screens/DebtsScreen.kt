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
import com.vachykm.investmentmanager.game.actors.ScrollPaneCompany
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

class DebtsScreen: AdvancedScreen() {

    private val headerImg     = Image(SpriteManager.GameRegion.HEADER_DARK.region)
    private val searchImg     = Image(SpriteManager.GameRegion.SEARCH_FIL.region)
    private val menuAllImg    = Image(SpriteManager.GameRegion.MENU_ALL.region)
    private val firstCB       = ACheckBox(ACheckBoxStyle.oill)
    private val spComp        = ScrollPaneCompany()
    private val menuImg       = Image(SpriteManager.GameRegion.M_DEBTS.region)


    override fun AdvancedGroup.addActorsOnGroup() {

        coroutine.launch {
            runGDX {
                addHeader()
                addSearchFil()
                addMenuAll()
                addSPComp()

                add3tochki()
                stageUI.addMenu()
            }
            headerImg.animSuspendShow(0.4f)
            searchImg.animSuspendShow(0.4f)
            menuAllImg.animSuspendShow(0.4f)
            firstCB.check()
            animSpComp(0.6f)

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

    private fun AdvancedGroup.addSearchFil() {
        addActor(searchImg)
        searchImg.apply {
            setBounds(33f, 1162f, 588f, 86f)
            animHide()
        }
    }

    private fun AdvancedGroup.addMenuAll() {
        addActor(menuAllImg)
        menuAllImg.apply {
            setBounds(31f, 1087f, 577f, 28f)
            animHide()
        }

        val cbg = ACheckBoxGroup()

        val listX = listOf(-7f, 122f, 271f, 397f, 525f)

        listOf(
            firstCB,
            ACheckBox(ACheckBoxStyle.sp500),
            ACheckBox(ACheckBoxStyle.forex),
            ACheckBox(ACheckBoxStyle.indeX),
            ACheckBox(ACheckBoxStyle.stock),
        ).onEachIndexed { index, cb ->
            addActor(cb)
            cb.checkBoxGroup = cbg
            cb.setBounds(listX[index], 1070f, 112f, 63f)
            cb.setOnCheckListener { if (it) spComp.update() }
        }

    }

    private fun AdvancedGroup.addSPComp() {
        addActor(spComp)
        spComp.apply {
            setBounds(26f, -895f, 606f, 895f)
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
                    NavigationManager.navigate(HomeSapienceScreen(), DebtsScreen())
                }
            }
        }
        c2.apply {
            setBounds(217f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(WalletScreen(), DebtsScreen())
                }
            }
        }
        c3.apply {
            setBounds(378f, 25f, 56f, 56f)
            setOnClickListener {  }
        }
        c4.apply {
            setBounds(539f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(BuyokScreen(), DebtsScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animSpComp(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            spComp.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(26f, 147f, time, Interpolation.elasticOut),
                    Actions.fadeIn(time)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            menuImg.addAction(Actions.sequence(
                Actions.moveTo(-76f, -79f, 0.5f, Interpolation.bounceOut),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

}