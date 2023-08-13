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

class PredprosmotrPokupkiScreen: AdvancedScreen() {

    //private val balLbl     = Label("$${numStr(10,99, 1)}.00", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))

    private val kupitImg = Image(SpriteManager.GameRegion.KUPIT_SECGAS.region)
    private val backA    = Actor()
    private val kupitA   = Actor()


    override fun show() {
        mainGroup.animAlpha0()
        super.show()
        mainGroup.animAlpha1(0.7f)
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addVse()
    }

    private fun AdvancedGroup.addVse() {
        addActor(kupitImg)
        kupitImg.setBounds(0f, 0f, 694f, 1439f)

        addActor(backA)
        backA.apply {
            setBounds(8f, 1357f, 116f, 116f)
            setOnClickListener { NavigationManager.back() }
        }

        addActor(kupitA)
        kupitA.apply {
            setBounds(44f, 63f, 605f, 107f)
            setOnClickListener { NavigationManager.navigate(KoshelokSinenkiScreen()) }
        }
    }

}