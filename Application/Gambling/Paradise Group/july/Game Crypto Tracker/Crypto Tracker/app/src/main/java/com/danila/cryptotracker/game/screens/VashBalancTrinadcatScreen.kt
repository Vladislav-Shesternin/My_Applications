package com.danila.cryptotracker.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.danila.cryptotracker.game.actors.VerticalCryptoList
import com.danila.cryptotracker.game.manager.FontTTFManager
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen
import com.danila.cryptotracker.game.utils.numStr

class VashBalancTrinadcatScreen: AdvancedScreen() {

    private val mouyBalImg = Image(SpriteManager.GameRegion.MOYO_BALLA.region)
    private val menImg     = Image(SpriteManager.GameRegion.PORTFOLIO.region)
    private val vCryoList  = VerticalCryptoList()
    private val balLbl     = Label("$${numStr(10,9999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilBold.font_55.font, Color.BLACK))


    override fun show() {
        mainGroup.animAlpha0()
        super.show()
        mainGroup.animAlpha1(0.7f)
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addVse()
        addMenushka()
    }

    private fun AdvancedGroup.addVse() {
        addActor(mouyBalImg)
        mouyBalImg.setBounds(44f, 1423f, 151f, 38f)

        addActor(vCryoList)
        vCryoList.setBounds(41f, 44f, 612f, 1298f)
        vCryoList.block = { NavigationManager.navigate(ValutaBtcScreen(), VashBalancTrinadcatScreen()) }

        addActor(menImg)
        menImg.setBounds(0f, 0f, 695f, 120f)

        addActor(balLbl)
        balLbl.setBounds(44f, 1367f, 498f, 56f)
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
            setOnClickListener { NavigationManager.navigate(KoshelokSinenkiScreen(), VashBalancTrinadcatScreen()) }
        }
        a2.apply {
            setBounds(168f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
        a3.apply {
            setBounds(323f, 27f, 75f, 75f)
            setOnClickListener { sendSEND() }
        }
        a4.apply {
            setBounds(452f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(RinokZahlobuetsaOtBaksovScreen(), VashBalancTrinadcatScreen()) }
        }
        a5.apply {
            setBounds(590f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(KupitBtcScreen(), VashBalancTrinadcatScreen()) }
        }
    }

}