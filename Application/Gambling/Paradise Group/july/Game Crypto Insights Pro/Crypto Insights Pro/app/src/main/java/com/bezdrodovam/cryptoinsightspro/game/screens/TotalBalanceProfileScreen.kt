package com.bezdrodovam.cryptoinsightspro.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.bezdrodovam.cryptoinsightspro.BuildConfig
import com.bezdrodovam.cryptoinsightspro.R
import com.bezdrodovam.cryptoinsightspro.game.actors.SuperScrollPane
import com.bezdrodovam.cryptoinsightspro.game.game
import com.bezdrodovam.cryptoinsightspro.game.manager.FontTTFManager
import com.bezdrodovam.cryptoinsightspro.game.manager.NavigationManager
import com.bezdrodovam.cryptoinsightspro.game.manager.SpriteManager
import com.bezdrodovam.cryptoinsightspro.game.utils.GameColor
import com.bezdrodovam.cryptoinsightspro.game.utils.Layout
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animAlpha0
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animSUSAlpha1
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.setBounds
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.setOnClickListener
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedGroup
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedScreen
import com.bezdrodovam.cryptoinsightspro.game.utils.numStr
import com.bezdrodovam.cryptoinsightspro.game.utils.runGDX
import kotlinx.coroutines.launch

class TotalBalanceProfileScreen: AdvancedScreen() {

    private val profileImg = Image(SpriteManager.IgraRegion.PROFILE_SEARCH_QUAR.region)
    private val totalBImg  = Image(SpriteManager.IgraRegion.TOT_BAL.region)
    private val balLbl     = Label("$${numStr(10,99, 1)}.00", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))
    private val v1Lbl      = Label("$${numStr(10,99, 1)}", Label.LabelStyle(FontTTFManager.GilBold.font_22.font, Color.WHITE))
    private val v2Lbl      = Label("$${numStr(10,99, 1)}", Label.LabelStyle(FontTTFManager.GilBold.font_22.font, Color.WHITE))
    private val d1Lbl      = Label("+$${numStr(10,99, 1)}", Label.LabelStyle(FontTTFManager.GilBold.font_22.font, GameColor.grinkin))
    private val d2Lbl      = Label("+$${numStr(10,99, 1)}%", Label.LabelStyle(FontTTFManager.GilBold.font_22.font, GameColor.grinkin))
    private val curInvImg  = Image(SpriteManager.IgraRegion.CUR_INV_RET_TOT.region)
    private val investeImg = Image(SpriteManager.IgraRegion.INVESTED_COINS.region)
    private val otherCoImg = Image(SpriteManager.IgraRegion.OTHER_COINS_TOT_INV.region)
    private val spSuper    = SuperScrollPane()
    private val menuImg    = Image(SpriteManager.IgraRegion.MENU_RIGHT.region)

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addImages()
                addVandD()
                addMenu()
                addProfMenu()
            }
            profileImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            totalBImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            balLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            curInvImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            investeImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            otherCoImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            spSuper.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            menuImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            listOf(v1Lbl, v2Lbl, d1Lbl, d2Lbl).onEach { it.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse) }
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImages() {
        addActors(profileImg, totalBImg, balLbl, curInvImg, investeImg, otherCoImg, spSuper)
        profileImg.apply {
            setBounds(Layout.profile)
            animAlpha0()
        }
        totalBImg.apply {
            setBounds(37f, 1336f, 215f, 32f)
            animAlpha0()
        }
        balLbl.apply {
            setBounds(38f, 1271f, 410f, 54f)
            animAlpha0()
        }
        curInvImg.apply {
            setBounds(37f, 990f, 604f, 222f)
            animAlpha0()
        }
        investeImg.apply {
            setBounds(37f, 699f, 507f, 239f)
            animAlpha0()
        }
        otherCoImg.apply {
            setBounds(37f, 621f, 224f, 23f)
            animAlpha0()
        }
        spSuper.apply {
            setBounds(19f, 104f, 642f, 496f)
            animAlpha0()
        }
    }

    private fun AdvancedGroup.addMenu() {
        addActor(menuImg)
        menuImg.apply {
            setBounds(Layout.menu)
            animAlpha0()
        }

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        addActors(c1,c2,c3)
        c1.apply {
            setBounds(113f, 7f, 89f, 89f)
            setOnClickListener { stageUI.root.animAlpha0(0.5f) { NavigationManager.navigate(MarketsBeginnersGuideScreen(), TotalBalanceProfileScreen()) } }
        }
        c2.apply {
            setBounds(301f, 7f, 89f, 89f)
            setOnClickListener { stageUI.root.animAlpha0(0.4f) { NavigationManager.navigate(BitcoinCoinPerformanceScreen(), MarketsBeginnersGuideScreen()) } }
        }
    }

    private fun AdvancedGroup.addProfMenu() {
        val c2 = Actor()
        val c3 = Actor()
        addActors(c2,c3)
        c2.apply {
            setBounds(500f, 1434f, 47f, 47f)
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
        c2.apply {
            setBounds(595f, 1434f, 47f, 47f)
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

    private fun AdvancedGroup.addVandD() {
        addActors(v1Lbl, v2Lbl, d1Lbl, d2Lbl)
        v1Lbl.apply {
            setBounds(57f, 1117f, 153f, 28f)
            animAlpha0()
        }
        v2Lbl.apply {
            setBounds(315f, 1117f, 153f, 28f)
            animAlpha0()
        }
        d1Lbl.apply {
            setBounds(57f, 1017f, 153f, 28f)
            animAlpha0()
        }
        d2Lbl.apply {
            setBounds(315f, 1017f, 153f, 28f)
            animAlpha0()
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

//    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
//        runGDX {
//            menuImg.addAction(
//                Actions.sequence(
//                    Actions.moveTo(10f, -3f, 0.33f, Interpolation.swing),
//                    Actions.run { continuation.complete(Unit) }
//                ))
//
//        }
//    }.await()

}