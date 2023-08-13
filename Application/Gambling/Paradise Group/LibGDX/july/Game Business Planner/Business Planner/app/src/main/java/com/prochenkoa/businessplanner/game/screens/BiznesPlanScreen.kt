package com.prochenkoa.businessplanner.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.prochenkoa.businessplanner.MercedesActivity
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBox
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBoxStyle
import com.prochenkoa.businessplanner.game.game
import com.prochenkoa.businessplanner.game.manager.GameDataStoreManager
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.manager.SpriteManager
import com.prochenkoa.businessplanner.game.utils.actor.animHide
import com.prochenkoa.businessplanner.game.utils.actor.animShow
import com.prochenkoa.businessplanner.game.utils.actor.disable
import com.prochenkoa.businessplanner.game.utils.actor.enable
import com.prochenkoa.businessplanner.game.utils.actor.setOnClickListener
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedStage
import com.prochenkoa.businessplanner.game.utils.runGDX
import kotlinx.coroutines.launch


val pochemu_on_a_ne_j get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/pdfKKASdasd"
val ta_potomucho_on_a_ne_ty get() = "https://proshnastiesnasty1314.github.io/BusinessPlanner/tfgfgKKKASDd"

class BiznesPlanScreen: AdvancedScreen() {

    private val prodoImg = Image(SpriteManager.GameRegion.PRODO.region)
    private val bix     = ACheckBox(ACheckBoxStyle.cici)
    private val bit     = Actor()

    private var zemala = false


    override fun AdvancedStage.addActorsOnStageUI() {
        addLoaders()
        addBetTrip()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addLoaders() {
        val img = Image(SpriteManager.Sepe.POCO.region)
        addActor(img)
        img.setBounds(158f, 764f, 425f, 145f)
    }

    private fun AdvancedStage.addBetTrip() {
        addActor(prodoImg)
        prodoImg.setBounds(35f, 33f, 671f, 240f)
        // Box
        addActor(bix)
        bix.apply {
            setBounds(35f, 211f,61f, 61f)
            setOnCheckListener {
                zemala = it
                if (zemala) bit.enable() else bit.disable()
            }
        }
        // Button
        addActor(bit)
        bit.apply {
            setBounds(35f, 33f,671f, 119f)
            disable()
            setOnClickListener {
                if (zemala) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Matis.update { true }
                        stageUI.root.animHide(0.5f) { runGDX { NavigationManager.navigate(KategirijScreen()) } }
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
            setBounds(524f, 241f, 134f, 55f)
            setOnClickListener { openPrivacy() }
        }
        para2.apply {
            setBounds(97f, 206f, 252f, 55f)
            setOnClickListener { openPrivacy() }
        }
        tara.apply {
            setBounds(371f, 176f, 277f, 55f)
            setOnClickListener { openTerms() }
        }
        tara2.apply {
            setBounds(72f, 148f, 277f, 55f)
            setOnClickListener { openTerms() }
        }
    }

    private fun openPrivacy() {
        MercedesActivity.webViewURL = pochemu_on_a_ne_j
        game.activity.webViewFragment.showAndOpenUrl()
    }

    private fun openTerms() {
        MercedesActivity.webViewURL = ta_potomucho_on_a_ne_ty
        game.activity.webViewFragment.showAndOpenUrl()
    }

}