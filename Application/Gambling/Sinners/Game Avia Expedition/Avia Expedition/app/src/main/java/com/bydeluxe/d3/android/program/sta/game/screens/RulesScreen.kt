package com.bydeluxe.d3.android.program.sta.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bydeluxe.d3.android.program.sta.game.LibGDXGame
import com.bydeluxe.d3.android.program.sta.game.actors.button.AButton
import com.bydeluxe.d3.android.program.sta.game.utils.actor.setOnClickListener
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedScreen
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedStage
import com.bydeluxe.d3.android.program.sta.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rulesImg  = Image(game.allAssets.rules)


    override fun show() {
        setBackgrounds(game.loadAssets.background.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addRules()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val back = AButton(this@RulesScreen, AButton.Static.Type.MENU)
        addActor(back)
        back.apply {
            setBounds(249f, 81f, 150f, 150f)
            setOnClickListener(game.soundUtil) { animHideScreen { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addRules() {
        addActor(rulesImg)
        rulesImg.setBounds(88f, 403f, 472f, 643f)
    }

}