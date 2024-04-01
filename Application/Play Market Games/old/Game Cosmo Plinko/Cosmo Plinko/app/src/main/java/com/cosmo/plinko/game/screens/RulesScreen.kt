package com.cosmo.plinko.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.actors.AKomete
import com.cosmo.plinko.game.actors.AMusor
import com.cosmo.plinko.game.actors.button.AButton
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.animShow
import com.cosmo.plinko.game.utils.actor.setBounds
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.region
import com.cosmo.plinko.game.utils.Layout.Splash as LS

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPalanet()
        addKomete()
        addMusor()
        addMenu()
        addBack()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPalanet() {
        val palanet = Image(game.splashAssets.PALANET)
        addActor(palanet)
        palanet.setBounds(LS.palanet)
    }

    private fun AdvancedStage.addKomete() {
        val palanet = AKomete(this@RulesScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMusor() {
        val palanet = AMusor(this@RulesScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMenu() {
        val menu = Image(game.gameAssets.RULES)
        addActor(menu)
        menu.setBounds(126f, 310f, 828f, 1303f)
    }

    private fun AdvancedStage.addBack() {
        val palanet = AButton(this@RulesScreen, AButton.Static.Type.MENU)
        addActor(palanet)
        palanet.setBounds(57f, 1780f, 124f, 106f)
        palanet.setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
    }


}