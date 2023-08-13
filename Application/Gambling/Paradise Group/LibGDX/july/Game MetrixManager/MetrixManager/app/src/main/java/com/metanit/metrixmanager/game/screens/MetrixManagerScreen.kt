package com.metanit.metrixmanager.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.metanit.metrixmanager.RenoActivity
import com.metanit.metrixmanager.game.actors.checkbox.ACheckBox
import com.metanit.metrixmanager.game.actors.checkbox.ACheckBoxStyle
import com.metanit.metrixmanager.game.game
import com.metanit.metrixmanager.game.manager.GameDataStoreManager
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.utils.actor.animHide
import com.metanit.metrixmanager.game.utils.actor.animShow
import com.metanit.metrixmanager.game.utils.actor.disable
import com.metanit.metrixmanager.game.utils.actor.enable
import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
import com.metanit.metrixmanager.game.utils.advanced.AdvancedScreen
import com.metanit.metrixmanager.game.utils.advanced.AdvancedStage
import com.metanit.metrixmanager.game.utils.runGDX
import kotlinx.coroutines.launch


val panamera get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/pdfKKASdasd"
val tetum    get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/tfgfgKKKASDd"

class MetrixManagerScreen: AdvancedScreen() {

    private val mImg = Image(SpriteManager.GamePlay.mnogonojka.region)
    private val tImg = Image(SpriteManager.GamePlay.pravo_na_jizn.region)
    private val a     = ACheckBox(ACheckBoxStyle.lord_G)
    private val b     = Actor()

    private var c = false


    override fun AdvancedStage.addActorsOnStageUI() {
        addAbcdifg()
        root.animShow(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAbcdifg() {
        addActor(mImg)
        mImg.setBounds(0f, 763f, 705f, 763f)
        addActor(tImg)
        tImg.setBounds(0f, 0f, 704f, 813f)
        // Box
        addActor(a)
        a.apply {
            setBounds(52f, 293f,56f, 56f)
            setOnCheckListener {
                c = it
                if (c) b.enable() else b.disable()
            }
        }
        // Button
        addActor(b)
        b.apply {
            setBounds(75f, 105f,554f, 112f)
            disable()
            setOnClickListener {
                if (c) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Megan.update { true }
                        stageUI.root.animHide(0.7f) { runGDX { NavigationManager.navigate(PrivetSergioScreen()) } }
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
            setBounds(498f, 321f, 122f, 42f)
            setOnClickListener { openP() }
        }
        para2.apply {
            setBounds(109f, 287f, 227f, 42f)
            setOnClickListener { openP() }
        }
        tara.apply {
            setBounds(364f, 273f, 256f, 42f)
            setOnClickListener { openT() }
        }
        tara2.apply {
            setBounds(97f, 240f, 256f, 42f)
            setOnClickListener { openT() }
        }
    }

    private fun openP() {
        RenoActivity.webViewURL = panamera
        game.activity.webViewFragment.showAndOpenUrl()
    }

    private fun openT() {
        RenoActivity.webViewURL = tetum
        game.activity.webViewFragment.showAndOpenUrl()
    }

}