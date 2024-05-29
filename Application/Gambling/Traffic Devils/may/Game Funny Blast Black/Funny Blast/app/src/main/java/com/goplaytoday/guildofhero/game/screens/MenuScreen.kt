package com.goplaytoday.guildofhero.game.screens

import com.goplaytoday.guildofhero.game.LGDXGame
import com.goplaytoday.guildofhero.game.actors.button.AButton
import com.goplaytoday.guildofhero.game.utils.TIME_ANIM_ALPHA
import com.goplaytoday.guildofhero.game.utils.actor.animHide
import com.goplaytoday.guildofhero.game.utils.actor.animShow
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedStage
import com.goplaytoday.guildofhero.game.utils.region

class MenuScreen(override val game: LGDXGame): AdvancedScreen() {

    private val btnPlay = AButton(this, AButton.Static.Type.Play)
    private val btnSett = AButton(this, AButton.Static.Type.Sett)
    private val btnExit = AButton(this, AButton.Static.Type.Exit)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.loaderAssets.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPlay()
        addSett()
        addExit()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPlay() {
        addActor(btnPlay)
        btnPlay.apply {
            setBounds(569f, 631f, 561f, 151f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(GameScreen::class.java.name, this::class.java.name) } }
        }
    }

    private fun AdvancedStage.addSett() {
        addActor(btnSett)
        btnSett.apply {
            setBounds(569f, 425f, 561f, 151f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(SettScreen::class.java.name, this::class.java.name) } }
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(btnExit)
        btnExit.apply {
            setBounds(569f, 219f, 561f, 151f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.exit() } }
        }
    }

}