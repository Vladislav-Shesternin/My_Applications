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

class RinokZahlobuetsaOtBaksovScreen: AdvancedScreen() {

    private val rinKalaImg = Image(SpriteManager.GameRegion.RINOK_KALA_PODOROJAL.region)
    private val menImg     = Image(SpriteManager.GameRegion.CINI.region)
    private val vCryoList  = VerticalCryptoList()
    private val balLbl     = Label("+${numStr(1,80, 1)}.${numStr(0,9, 2)}%", Label.LabelStyle(FontTTFManager.GilBold.font_46.font, GameColor.lobodaHUINA))


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
        addActor(rinKalaImg)
        rinKalaImg.setBounds(44f, 1271f, 620f, 185f)

        addActor(vCryoList)
        vCryoList.setBounds(44f, 93f, 612f, 1157f)
        vCryoList.block = { NavigationManager.navigate(ValutaBtcScreen(), RinokZahlobuetsaOtBaksovScreen()) }

        addActor(menImg)
        menImg.setBounds(0f, 0f, 695f, 120f)

        addActor(balLbl)
        balLbl.setBounds(396f, 1378f, 168f, 46f)
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
            setOnClickListener { NavigationManager.navigate(KoshelokSinenkiScreen(), RinokZahlobuetsaOtBaksovScreen()) }
        }
        a2.apply {
            setBounds(168f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(VashBalancTrinadcatScreen(), RinokZahlobuetsaOtBaksovScreen()) }
        }
        a3.apply {
            setBounds(323f, 27f, 75f, 75f)
            setOnClickListener { sendSEND() }
        }
        a4.apply {
            setBounds(452f, 27f, 75f, 75f)
            setOnClickListener {  }
        }
        a5.apply {
            setBounds(590f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(KupitBtcScreen(), RinokZahlobuetsaOtBaksovScreen()) }
        }
    }

}