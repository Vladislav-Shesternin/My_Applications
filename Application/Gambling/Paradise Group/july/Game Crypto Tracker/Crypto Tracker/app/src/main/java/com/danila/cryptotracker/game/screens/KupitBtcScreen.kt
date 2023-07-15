package com.danila.cryptotracker.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen

class KupitBtcScreen: AdvancedScreen() {

    //private val balLbl     = Label("$${numStr(10,99, 1)}.00", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))

    private val yVasNetImg = Image(SpriteManager.GameRegion.Y_VAS_NET_TRANZA.region)
    private val menImg     = Image(SpriteManager.GameRegion.GOLOVNA.region)
    private val backA      = Actor()
    private val kupiA      = Actor()


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
        addActor(yVasNetImg)
        yVasNetImg.setBounds(0f, 756f, 694f, 711f)

        addActor(menImg)
        menImg.setBounds(0f, 0f, 695f, 120f)

        addActor(backA)
        backA.apply {
            setBounds(8f, 1357f, 116f, 116f)
            setOnClickListener { NavigationManager.back() }
        }
        addActor(kupiA)
        kupiA.apply {
            setBounds(74f, 766f, 546f, 107f)
            setOnClickListener { NavigationManager.navigate(SpisokBtcScreen(), KupitBtcScreen()) }
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
            setOnClickListener { NavigationManager.navigate(VashBalancTrinadcatScreen(), KupitBtcScreen()) }
        }
        a3.apply {
            setBounds(323f, 27f, 75f, 75f)
            setOnClickListener { sendSEND() }
        }
        a4.apply {
            setBounds(452f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(RinokZahlobuetsaOtBaksovScreen(), KupitBtcScreen()) }
        }
        a5.apply {
            setBounds(590f, 27f, 75f, 75f)
            setOnClickListener { NavigationManager.navigate(ValutaBtcScreen(), KupitBtcScreen()) }
        }
    }

}