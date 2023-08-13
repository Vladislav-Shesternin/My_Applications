package com.nicoledeonnit.cryptosignals.game.screens

import android.content.Intent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.nicoledeonnit.cryptosignals.BuildConfig
import com.nicoledeonnit.cryptosignals.R
import com.nicoledeonnit.cryptosignals.game.actors.PalermonTov
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBox
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBoxGroup
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBoxStyle
import com.nicoledeonnit.cryptosignals.game.game
import com.nicoledeonnit.cryptosignals.game.manager.FontTTFManager
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.Stepan
import com.nicoledeonnit.cryptosignals.game.utils.actor.animHide
import com.nicoledeonnit.cryptosignals.game.utils.actor.animpokazat
import com.nicoledeonnit.cryptosignals.game.utils.actor.setOnClickListener
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedStage
import com.nicoledeonnit.cryptosignals.game.utils.numStr
import com.nicoledeonnit.cryptosignals.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VoznagradaScreen: AdvancedScreen() {

    private val dvaPalcaImg = Image(SpriteManager.Palas.DVA_PALCA.region)
    private val chasikisImg = Image(SpriteManager.Palas.CHASIKI.region)
    private val bilgates by lazy { Label("${numStr(1000, 30000, 1)}.${numStr(0, 9, 2)}", Label.LabelStyle(
        FontTTFManager.Medium.font_72.font, Color.WHITE)) }
    private val tifli by lazy { Label("${numStr(1, 99, 1)}.${numStr(0, 9, 2)}%", Label.LabelStyle(
        FontTTFManager.Medium.font_23.font, Stepan.giga)) }
    private val aska = Image(SpriteManager.Palas.MANUG.region)
    private val palermo = PalermonTov()


    override fun AdvancedStage.addActorsOnStageUI() {
        kalashik()
        muraha()
        cbos()
        addActors(bilgates, tifli)
        bilgates.setBounds(68f, 1403f, 379f, 89f)
        tifli.setBounds(702f, 1433f, 82f, 29f)
        root.animpokazat(0.8f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.kalashik() {
        addActor(dvaPalcaImg)
        dvaPalcaImg.setBounds(68f, 1001f,756f, 689f)
        addActor(chasikisImg)
        chasikisImg.setBounds(68f, 932f,791f, 48f)
        addActor(palermo)
        palermo.setBounds(68f, 154f, 748f, 718f)
        palermo.bclikaock = { stageUI.root.animHide(0.7f) { NavigationManager.navigate(CardanoAdaScreen(), VoznagradaScreen())} }
        addActor(aska)
        aska.setBounds(0f, 0f,878f, 154f)
    }

    private fun AdvancedStage.muraha() {
        val a = Actor()
        val b = Actor()
        val v = Actor()
        val g = Actor()
        addActors(a,b,v,g)
        a.apply {
            setBounds(212f, 29f, 97f, 97f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.navigate(AboutWithHigherScreen(), VoznagradaScreen()) } }
        }
        b.apply {
            setBounds(390f, 29f, 97f, 97f)
            setOnClickListener {
                val text = game.activity.getString(R.string.app_name)

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
        v.apply {
            setBounds(567f, 29f, 97f, 97f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.navigate(CardanoAdaScreen(), VoznagradaScreen())} }
        }
        g.apply {
            setBounds(725f, 29f, 97f, 97f)
            setOnClickListener { stageUI.root.animHide(0.7f) { NavigationManager.navigate(CardanoAdaScreen(), VoznagradaScreen())} }
        }
    }

    private fun AdvancedStage.cbos() {
        val p24h   = ACheckBox(ACheckBoxStyle.p24h)
        val hot    = ACheckBox(ACheckBoxStyle.hot)
        val pribil = ACheckBox(ACheckBoxStyle.pribil)
        val podim  = ACheckBox(ACheckBoxStyle.podim)
        val poteri = ACheckBox(ACheckBoxStyle.poteri)
        addActors(p24h, hot, pribil, podim, poteri)
        val gurok = ACheckBoxGroup()
        p24h.apply {
            setBounds(68f, 932f, 148f, 48f)
            checkBoxGroup = gurok
            setOnCheckListener { if (it) kikBrains() }
        }
        hot.apply {
            setBounds(233f, 932f, 97f, 48f)
            checkBoxGroup = gurok
            setOnCheckListener { if (it) kikBrains() }
        }
        pribil.apply {
            setBounds(348f, 932f, 173f, 48f)
            checkBoxGroup = gurok
            setOnCheckListener { if (it) kikBrains() }
        }
        podim.apply {
            setBounds(538f, 932f, 155f, 48f)
            checkBoxGroup = gurok
            setOnCheckListener { if (it) kikBrains() }
        }
        poteri.apply {
            setBounds(711f, 932f, 149f, 48f)
            checkBoxGroup = gurok
            setOnCheckListener { if (it) kikBrains() }
        }
        p24h.check()
    }

    var i = 0
    private fun kikBrains() {
        coroutine.launch {
            delay(1000)
            runGDX {
                if (i != 0) {
                    bilgates.setText("${numStr(1000, 30000, 1)}.${numStr(0, 9, 2)}")
                    tifli.setText("${numStr(1, 99, 1)}.${numStr(0, 9, 2)}%")
                    palermo.saksagonkiDen()
                }
                i++
            }
        }
    }

}