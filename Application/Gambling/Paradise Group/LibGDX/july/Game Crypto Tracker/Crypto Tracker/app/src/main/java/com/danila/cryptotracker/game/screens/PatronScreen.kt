package com.danila.cryptotracker.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.danila.cryptotracker.game.actors.checkbox.ACheckBox
import com.danila.cryptotracker.game.actors.checkbox.ACheckBoxStyle
import com.danila.cryptotracker.game.game
import com.danila.cryptotracker.game.manager.GameDataStoreManager
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.actor.animAlpha0
import com.danila.cryptotracker.game.utils.actor.animAlpha1
import com.danila.cryptotracker.game.utils.actor.disable
import com.danila.cryptotracker.game.utils.actor.enable
import com.danila.cryptotracker.game.utils.actor.setOnClickListener
import com.danila.cryptotracker.game.utils.advanced.AdvancedGroup
import com.danila.cryptotracker.game.utils.advanced.AdvancedScreen
import com.danila.cryptotracker.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch


var pisun_chik_ibum    = "https://bezpevhid20314sleq.github.io/CryptoInsightsPro/pisightssss"
var tiamimsu_si_sa_ske = "https://bezpevhid20314sleq.github.io/CryptoInsightsPro/tkjisightisghtt"

class PatronScreen: AdvancedScreen() {

    private val patronImg = Image(SpriteManager.GameRegion.PATRON.region)
    private val patronBox = ACheckBox(ACheckBoxStyle.patron)
    private val patronBtn = Actor()

    private var isPatron = false


    override fun show() {
        mainGroup.animAlpha0()
        super.show()
        mainGroup.animAlpha1(0.7f)
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(patronImg)
        addVse()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addVse() {
        addActors(patronBox, patronBtn)
        patronBox.apply {
            setBounds(44f, 250f,55f, 55f)
            setOnCheckListener {
                isPatron = it
                if (isPatron) patronBtn.enable() else patronBtn.disable()
            }
        }
        patronBtn.apply {
            setBounds(44f, 62f,605f, 107f)
            disable()
            setOnClickListener {
                if (isPatron) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Patron.update { true }
                        mainGroup.animAlpha0(0.3f) { runGDX { NavigationManager.navigate(KoshelokSinenkiScreen()) } }
                    }
                }
            }
        }

        val para  = Actor()
        val para2 = Actor()
        val tara  = Actor()
        val tara2 = Actor()
        addActors(para, para2, tara, tara2)
        para.apply {
            setBounds(482f, 276f, 124f, 46f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pisun_chik_ibum))) }
        }
        para2.apply {
            setBounds(100f, 245f, 226f, 46f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pisun_chik_ibum))) }
        }
        tara.apply {
            setBounds(347f, 227f, 254f, 46f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tiamimsu_si_sa_ske))) }
        }
        tara2.apply {
            setBounds(100f, 193f, 247f, 46f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tiamimsu_si_sa_ske))) }
        }
    }

}