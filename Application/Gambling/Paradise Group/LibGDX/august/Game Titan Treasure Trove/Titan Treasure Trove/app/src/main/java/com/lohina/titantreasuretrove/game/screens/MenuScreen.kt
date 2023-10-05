package com.lohina.titantreasuretrove.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.lohina.titantreasuretrove.game.LibGDXGame
import com.lohina.titantreasuretrove.game.utils.TIME_ANIM_ALPHA
import com.lohina.titantreasuretrove.game.utils.actor.animHide
import com.lohina.titantreasuretrove.game.utils.actor.animShow
import com.lohina.titantreasuretrove.game.utils.actor.setOnClickListener
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedScreen
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedStage

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        private var isMusicPlay = false
    }

    // Actor
    private val aPlayImg = Image(game.spriteUtil.GAME.PLAY)
    private val aExitImg = Image(game.spriteUtil.GAME.EXIT)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)

        if (isMusicPlay.not()) game.musicUtil.apply { music = DEFAULT.apply { isLooping = true } }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBtns() {
        addActors(aPlayImg, aExitImg)
        aPlayImg.apply {
            setBounds(365f, 261f, 293f, 96f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(TutorialScreen(game), MenuScreen(game)) } }
        }
        aExitImg.apply {
            setBounds(365f, 136f, 293f, 96f)
            setOnClickListener { game.navigationManager.exit() }
        }
    }

}