package com.bydeluxe.d3.android.program.sta.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bydeluxe.d3.android.program.sta.game.LibGDXGame
import com.bydeluxe.d3.android.program.sta.game.actors.ASettingsGroup
import com.bydeluxe.d3.android.program.sta.game.actors.button.AButton
import com.bydeluxe.d3.android.program.sta.game.utils.actor.setOnClickListener
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedScreen
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedStage
import com.bydeluxe.d3.android.program.sta.game.utils.region

class SettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val settImg  = Image(game.allAssets.settings)

    override fun show() {
        setBackgrounds(game.loadAssets.background.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addSett()

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val back = AButton(this@SettingsScreen, AButton.Static.Type.MENU)
        addActor(back)
        back.apply {
            setBounds(249f, 81f, 150f, 150f)
            setOnClickListener(game.soundUtil) { animHideScreen { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addSett() {
        addActor(settImg)
        settImg.setBounds(88f, 403f, 472f, 643f)

        val settGroup = ASettingsGroup(this@SettingsScreen)
        addActor(settGroup)
        settGroup.setBounds(88f, 403f, 472f, 643f)
    }




}