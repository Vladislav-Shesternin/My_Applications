package com.danila.cryptotracker.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.danila.cryptotracker.game.actors.VerticalCryptoList
import com.danila.cryptotracker.game.manager.FontTTFManager
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.GameColor
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen
import com.danila.cryptotracker.game.utils.numStr

class ValutaBtcScreen: AdvancedScreen() {

    private val zvezdakImg = Image(SpriteManager.GameRegion.ZVEZDA_I_BACK.region)
    private val balLbl     = Label("$${numStr(10,95, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilBold.font_55.font, Color.BLACK))
    private val pluLbl     = Label("+$${numStr(1,9, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}(${numStr(1,9, 1)}.${numStr(0,9, 2)}%)", Label.LabelStyle(FontTTFManager.GilReg.font_26.font, GameColor.lobodaHUINA))
    private val backA      = Actor()
    private val gametafImg = Image(SpriteManager.GameRegion.GOMEOFAG.region)
    private val datTorgImg = Image(SpriteManager.GameRegion.DATA_I_TORGOVLA.region)
    private val torgA      = Actor()
    private val cryptaImg  = Image(SpriteManager.ListRegion.PAMOGUGON.regionList.random())





    override fun show() {
        mainGroup.animAlpha0()
        super.show()
        mainGroup.animAlpha1(0.7f)
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addVse()
    }

    private fun AdvancedGroup.addVse() {
        addActor(zvezdakImg)
        zvezdakImg.setBounds(44f, 1223f, 586f, 186f)
        addActor(balLbl)
        balLbl.setBounds(44f, 1222f, 482f, 81f)
        addActor(pluLbl)
        pluLbl.setBounds(44f, 1184f, 444f, 38f)
        addActor(backA)
        backA.setBounds(23f, 1344f, 87f, 87f)
        backA.setOnClickListener { NavigationManager.back() }
        addActor(gametafImg)
        gametafImg.setBounds(44f, 467f, 605f, 675f)
        addActor(datTorgImg)
        datTorgImg.setBounds(44f, 72f, 605f, 355f)
        addActor(torgA)
        torgA.setBounds(44f, 72f, 605f, 107f)
        torgA.setOnClickListener { NavigationManager.navigate(KoshelokSinenkiScreen()) }
        addActor(cryptaImg)
        cryptaImg.setBounds(44f, 235f, 605f, 100f)
    }

}