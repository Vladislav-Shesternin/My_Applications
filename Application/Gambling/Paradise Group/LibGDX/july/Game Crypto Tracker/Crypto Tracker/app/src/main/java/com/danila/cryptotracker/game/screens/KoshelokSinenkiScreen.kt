package com.danila.cryptotracker.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.danila.cryptotracker.game.actors.HorizontalCryptoList
import com.danila.cryptotracker.game.actors.HorizontalKadgatCryptoList
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.disable
import com.danila.cryptotracker.game.utils.actor.enable
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen

class KoshelokSinenkiScreen: AdvancedScreen() {

    //private val balLbl     = Label("$${numStr(10,99, 1)}.00", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))

    private val koshelekImg = Image(SpriteManager.GameRegion.KOSHELOK_SINENKI.region)
    private val dobavitA    = Actor()
    private val hScrList    = HorizontalCryptoList()
    private val hKadList    = HorizontalKadgatCryptoList()
    private val menImg     = Image(SpriteManager.GameRegion.GOLOVNA.region)


    override fun show() {
        mainGroup.animAlpha0()
        super.show()
        mainGroup.animAlpha1(0.7f)
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(koshelekImg)
        addVse()
        addMenushka()
    }

    private fun AdvancedGroup.addVse() {
        addActor(dobavitA)
        dobavitA.apply {
            setBounds(44f, 800f, 605f, 107f)
            setOnClickListener { NavigationManager.navigate(KupitBtcScreen(), KoshelokSinenkiScreen()) }
        }

        addActor(hScrList)
        hScrList.setBounds(0f, 574f, 695f, 101f)
        addActor(hKadList)
        hKadList.setBounds(0f, 169f, 695f, 248f)
        addActor(menImg)
        menImg.setBounds(0f, 0f, 695f, 120f)
    }

    private fun AdvancedGroup.addMenushka() {
        val a1 = Actor()
        val a2 = Actor()
        val a3 = Actor()
        val a4 = Actor()
        val a5 = Actor()
        addActors(a1,a2,a3,a4,a5)
        a1.apply {
            setBounds(17f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
        a2.apply {
            setBounds(168f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(VashBalancTrinadcatScreen(), KoshelokSinenkiScreen()) }
        }
        a3.apply {
            setBounds(323f, 27f, 75f, 75f)
            setOnClickListener { sendSEND() }
        }
        a4.apply {
            setBounds(452f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(RinokZahlobuetsaOtBaksovScreen(), KoshelokSinenkiScreen()) }
        }
        a5.apply {
            setBounds(590f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(ValutaBtcScreen(), KoshelokSinenkiScreen()) }
        }
    }

}