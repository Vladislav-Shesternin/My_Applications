package com.nicoledeonnit.cryptosignals.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.nicoledeonnit.cryptosignals.MiniActivity
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBox
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBoxStyle
import com.nicoledeonnit.cryptosignals.game.game
import com.nicoledeonnit.cryptosignals.game.manager.GameDataStoreManager
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.actor.animHide
import com.nicoledeonnit.cryptosignals.game.utils.actor.animpokazat
import com.nicoledeonnit.cryptosignals.game.utils.actor.disable
import com.nicoledeonnit.cryptosignals.game.utils.actor.enable
import com.nicoledeonnit.cryptosignals.game.utils.actor.setOnClickListener
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedStage
import com.nicoledeonnit.cryptosignals.game.utils.runGDX
import kotlinx.coroutines.launch


val panamera get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/pdfKKASdasd"
val tetum    get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/tfgfgKKKASDd"

class TelebonScreen: AdvancedScreen() {

    private val mImg = Image(SpriteManager.Palas.telekan.region)
    private val a     = ACheckBox(ACheckBoxStyle.piktogramma)
    private val b     = Actor()

    private var c = false


    override fun AdvancedStage.addActorsOnStageUI() {
        pipsja()
        root.animpokazat(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.pipsja() {
        addActor(mImg)
        mImg.setBounds(68f, 68f, 755f, 1488f)
        // Box
        addActor(a)
        a.apply {
            setBounds(68f, 256f,64f, 64f)
            setOnCheckListener {
                c = it
                if (c) b.enable() else b.disable()
            }
        }
        // Button
        addActor(b)
        b.apply {
            setBounds(68f, 68f,741f, 111f)
            disable()
            setOnClickListener {
                if (c) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Fantom.update { true }
                        stageUI.root.animHide(0.7f) { runGDX { NavigationManager.navigate(VoznagradaScreen()) } }
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
            setBounds(574f, 290f, 118f, 37f)
            setOnClickListener { guga() }
        }
        para2.apply {
            setBounds(133f, 247f, 257f, 37f)
            setOnClickListener { guga() }
        }
        tara.apply {
            setBounds(439f, 247f, 257f, 37f)
            setOnClickListener { malatko() }
        }
        tara2.apply {
            setBounds(133f, 203f, 257f, 37f)
            setOnClickListener { malatko() }
        }
    }

    private fun guga() {
        MiniActivity.webURL = panamera
        game.activity.webViewFragment.showAndOpenUrl()
    }

    private fun malatko() {
        MiniActivity.webURL = tetum
        game.activity.webViewFragment.showAndOpenUrl()
    }

}