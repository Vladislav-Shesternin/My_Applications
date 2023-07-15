package com.bezdrodovam.cryptoinsightspro.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.bezdrodovam.cryptoinsightspro.game.manager.FontTTFManager
import com.bezdrodovam.cryptoinsightspro.game.manager.NavigationManager
import com.bezdrodovam.cryptoinsightspro.game.manager.SpriteManager
import com.bezdrodovam.cryptoinsightspro.game.utils.GameColor
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animAlpha0
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animSUSAlpha1
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.setOnClickListener
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedGroup
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedScreen
import com.bezdrodovam.cryptoinsightspro.game.utils.numStr
import com.bezdrodovam.cryptoinsightspro.game.utils.runGDX
import kotlinx.coroutines.launch


class ButBitcoinEstimatedScreen: AdvancedScreen() {

    private val bitikImg     = Image(SpriteManager.IgraRegion.BACK_BUY_BITCOIN_PRICE.region)
    private val balLbl       = Label("$${numStr(10,99, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))
    private val backAS       = Actor()
    private val howUSDTImg   = Image(SpriteManager.IgraRegion.HOW_USD_BTC.region)
    private val textField    = TextField("100", TextField.TextFieldStyle(
        FontTTFManager.GilReg.font_26.font, GameColor.gfggfg, TextureRegionDrawable(SpriteManager.IgraRegion.CURSOR.region), null, null
    ))
    private val otheLbl      = Label("0.000${numStr(10,999, 1)}", Label.LabelStyle(FontTTFManager.GilReg.font_26.font, GameColor.gfggfg))
    private val youPayImg    = Image(SpriteManager.IgraRegion.YOU_PAY_INCLUDES.region)
    private val circleImg    = Image(SpriteManager.IgraRegion.CIRCLE_ARROW.region)



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addImages()
            }
            bitikImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            balLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            howUSDTImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            textField.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            otheLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            youPayImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            circleImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImages() {
        addActors(bitikImg, balLbl, backAS, howUSDTImg, textField, otheLbl, youPayImg, circleImg)
        bitikImg.apply {
            setBounds(0f, 1211f, 679f, 298f)
            animAlpha0()
        }
        balLbl.apply {
            setBounds(184f, 1137f, 313f, 54f)
            animAlpha0()
            setAlignment(Align.center)
        }
        backAS.apply {
            setBounds(14f, 1353f, 94f, 94f)
            setOnClickListener { NavigationManager.back() }
        }
        howUSDTImg.apply {
            setBounds(0f, 797f, 679f, 264f)
            animAlpha0()
        }
        textField.apply {
            setBounds(43f, 858f, 238f, 77f)
            animAlpha0()
            alignment       = Align.center
            textFieldFilter = TextFieldFilter.DigitsOnlyFilter()

            setTextFieldListener { _, _ -> otheLbl.setText("$0.${numStr(0,9, 5)}") }
        }
        otheLbl.apply {
            setBounds(404f, 880f, 228f, 32f)
            animAlpha0()
            setAlignment(Align.center)
        }
        youPayImg.apply {
            setBounds(37f, 17f, 604f, 175f)
            animAlpha0()
        }
        circleImg.apply {
            setBounds(52f, 28f, 79f, 79f)
            animAlpha0()
        }

        val panelaDebka = Actor()
        addActor(panelaDebka)
        panelaDebka.apply {
            setBounds(37f, 17f, 604f, 103f)
            addListener(object : InputListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int
                ): Boolean {
                    touchDragged(event, x, y, pointer)
                    return true
                }

                override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                    circleImg.x = when {
                        x < 52f -> 52f
                        x > 502 -> {
                            NavigationManager.navigate(TotalBalanceProfileScreen())
                            502f
                        }
                        else -> x
                    }
                }
            })
        }

    }

}