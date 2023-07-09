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

class WalletScreen: AdvancedScreen() {

    private val headerImg     = Image(SpriteManager.GameRegion.HEADER_DARK.region)
    private val deatelnostImg = Image(SpriteManager.GameRegion.DEATELNOST.region)
    private val astsActImg    = Image(SpriteManager.GameRegion.ASSETS_ACT.region)
    private val menuImg       = Image(SpriteManager.GameRegion.M_WALLET.region)
    private val totalUsdtImg  = Image(SpriteManager.GameRegion.TOTAL_USD.region)
    private val balLbl        = Label("$${numStr(1,100, 1)},${numStr(100, 999, 1)}", Label.LabelStyle(FontTTFManager.GilMed.font_25.font, Color.WHITE))
    private val totLbl        = Label("$${numStr(10,100, 1)},${numStr(100, 999, 1)}", Label.LabelStyle(FontTTFManager.GilSemBold.font_28.font, GameColor.puple))
    private val dolLbl        = Label("$${numStr(100,999, 1)} (+${numStr(1,9,1)},${numStr(1,9,1)}%)", Label.LabelStyle(FontTTFManager.GilMed.font_19.font, GameColor.purple))



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addHeader()
                addDeatelnost()
                addAstsAct()
                addTotalUSDT()
                addLbls()
                add3tochki()
                stageUI.addMenu()
            }
            headerImg.animSuspendShow(0.4f)
            animDeatelnost(0.6f)
            animAstsAct(0.6f)
            animTotUsd(0.6f)
            animMenu()
            balLbl.animSuspendShow(0.4f)
            totLbl.animSuspendShow(0.4f)
            dolLbl.animSuspendShow(0.4f)
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

    private fun AdvancedGroup.addDeatelnost() {
        addActors(deatelnostImg)
        deatelnostImg.apply {
            setBounds(650f, 811f, 596f, 460f)
            animHide()
        }
    }

    private fun AdvancedGroup.addAstsAct() {
        addActors(astsActImg)
        astsActImg.apply {
            setBounds(-747f, 503f, 747f, 287f)
            animHide()
        }
    }

    private fun AdvancedGroup.addTotalUSDT() {
        addActors(totalUsdtImg)
        totalUsdtImg.apply {
            setBounds(650f, 72f, 744f, 536f)
            animHide()
        }
    }

    private fun AdvancedGroup.addLbls() {
        addActors(balLbl, totLbl, dolLbl)
        balLbl.apply {
            setBounds(404f, 640f, 194f, 30f)
            setAlignment(Align.right)
            animHide()
        }
        totLbl.apply {
            setBounds(169f, 444f, 184f, 35f)
            animHide()
        }
        dolLbl.apply {
            setBounds(386f, 446f, 208f, 39f)
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
                    NavigationManager.navigate(HomeSapienceScreen(), WalletScreen())
                }
            }
        }
        c2.apply {
            setBounds(217f, 25f, 56f, 56f)
            setOnClickListener {  }
        }
        c3.apply {
            setBounds(378f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(DebtsScreen(), WalletScreen())
                }
            }
        }
        c4.apply {
            setBounds(539f, 25f, 56f, 56f)
            setOnClickListener {
                stageUI.root.animHide(0.4f) {
                    NavigationManager.navigate(BuyokScreen(), WalletScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animDeatelnost(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            deatelnostImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(25f, 811f, time, Interpolation.elasticOut),
                    Actions.fadeIn(time)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animAstsAct(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            astsActImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(-49f, 503f, time, Interpolation.elasticOut),
                    Actions.fadeIn(time)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animTotUsd(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            totalUsdtImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(-48f, 72f, time, Interpolation.elasticOut),
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