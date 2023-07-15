package com.danila.cryptotracker.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.danila.cryptotracker.game.actors.VerticalCryptoList
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen

class SpisokBtcScreen: AdvancedScreen() {

    //private val balLbl     = Label("$${numStr(10,99, 1)}.00", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))

    private val viverikImg = Image(SpriteManager.GameRegion.VIBERI_AKTIV_DLA_POKUPKI.region)
    private val menImg     = Image(SpriteManager.GameRegion.GOLOVNA.region)
    private val backA      = Actor()
    private val vCryoList  = VerticalCryptoList()


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
        addActor(viverikImg)
        viverikImg.setBounds(0f, 1337f, 694f, 103f)

        addActor(vCryoList)
        vCryoList.setBounds(41f, 39f, 612f, 1298f)
        vCryoList.block = { NavigationManager.navigate(PredprosmotrPokupkiScreen(), SpisokBtcScreen()) }

        addActor(menImg)
        menImg.setBounds(0f, 0f, 695f, 120f)

        addActor(backA)
        backA.apply {
            setBounds(8f, 1357f, 116f, 116f)
            setOnClickListener { NavigationManager.back() }
        }
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
            setOnClickListener {  }
        }
        a3.apply {
            setBounds(323f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
        a4.apply {
            setBounds(452f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
        a5.apply {
            setBounds(590f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
    }

}