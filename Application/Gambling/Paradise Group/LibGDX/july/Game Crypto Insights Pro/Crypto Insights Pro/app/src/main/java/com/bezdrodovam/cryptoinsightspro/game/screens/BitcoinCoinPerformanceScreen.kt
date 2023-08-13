package com.bezdrodovam.cryptoinsightspro.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.bezdrodovam.cryptoinsightspro.game.actors.checkbox.ACheckBox
import com.bezdrodovam.cryptoinsightspro.game.actors.checkbox.ACheckBoxGroup
import com.bezdrodovam.cryptoinsightspro.game.actors.checkbox.ACheckBoxStyle
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

class BitcoinCoinPerformanceScreen: AdvancedScreen() {

    private val xuetaKoniacha = listOf(
        SpriteManager.IgraRegion._A1.region,
        SpriteManager.IgraRegion._A2.region,
        SpriteManager.IgraRegion._A3.region,
        SpriteManager.IgraRegion._A4.region,
    )

    private val bitocStarImg = Image(SpriteManager.IgraRegion.BITCOIN_STAR.region)
    private val rafikEdraImg = Image(xuetaKoniacha.random())
    private val balLbl       = Label("$${numStr(10,99, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilReg.font_45.font, Color.WHITE))
    private val backAS       = Actor()
    private val timerikImg   = Image(SpriteManager.IgraRegion.TIMERIK.region)
    private val listCBBBBB   = listOf(
        ACheckBox(ACheckBoxStyle.AH),
        ACheckBox(ACheckBoxStyle.AD),
        ACheckBox(ACheckBoxStyle.AW),
        ACheckBox(ACheckBoxStyle.AM),
        ACheckBox(ACheckBoxStyle.AY),
    )
    private val coinPerfoImg = Image(SpriteManager.IgraRegion.COIN_PERFORMANCE.region)
    private val highLbl      = Label("$${numStr(10,99, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilMed.font_30.font, GameColor.grankid))
    private val lowwLbl      = Label("$${numStr(10,99, 1)},${numStr(100,999, 1)}.${numStr(0,9, 2)}", Label.LabelStyle(FontTTFManager.GilMed.font_30.font, GameColor.grankid))



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addImages()
            }
            bitocStarImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            balLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            rafikEdraImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            timerikImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            listCBBBBB.first().check()
            coinPerfoImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            highLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            lowwLbl.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImages() {
        addActors(bitocStarImg, balLbl, backAS, rafikEdraImg, timerikImg, coinPerfoImg, highLbl, lowwLbl)
        bitocStarImg.apply {
            setBounds(37f, 1256f, 605f, 164f)
            animAlpha0()
        }
        balLbl.apply {
            setBounds(43f, 1282f, 391f, 54f)
            animAlpha0()
        }
        rafikEdraImg.apply {
            setBounds(-5f, 615f, 696f, 619f)
            animAlpha0()
        }
        backAS.apply {
            setBounds(14f, 1353f, 94f, 94f)
            setOnClickListener { NavigationManager.back() }
        }
        timerikImg.apply {
            setBounds(37f, 545f, 601f, 41f)
            animAlpha0()
        }
        var nnxx = 37f
        val gggggggg = ACheckBoxGroup()
        listCBBBBB.onEach {
            addActor(it)
            it.checkBoxGroup = gggggggg
            it.setBounds(nnxx, 545f, 71f, 41f)
            nnxx += 131f
            it.setOnCheckListener { rafikEdraImg.drawable = TextureRegionDrawable(xuetaKoniacha.random()) }
        }
        coinPerfoImg.apply {
            setBounds(0f, 0f, 679f, 515f)
            animAlpha0()
        }

        val aka47 = Actor()
        val m16   = Actor()
        addActors(aka47, m16)
        aka47.apply {
            setBounds(94f, 26f, 213f, 85f)
            setOnClickListener { NavigationManager.navigate(ButBitcoinEstimatedScreen(), BitcoinCoinPerformanceScreen()) }
        }
        m16.apply {
            setBounds(371f, 26f, 213f, 85f)
            setOnClickListener { NavigationManager.navigate(MarketsBeginnersGuideScreen()) }
        }
        highLbl.apply {
            setBounds(383f, 277f, 232f, 37f)
            animAlpha0()
            setAlignment(Align.right)
        }
        lowwLbl.apply {
            setBounds(383f, 157f, 232f, 37f)
            animAlpha0()
            setAlignment(Align.right)
        }
    }

}