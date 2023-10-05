package com.lohina.titantreasuretrove.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.lohina.titantreasuretrove.game.LibGDXGame
import com.lohina.titantreasuretrove.game.utils.TIME_ANIM_ALPHA
import com.lohina.titantreasuretrove.game.utils.actor.animHide
import com.lohina.titantreasuretrove.game.utils.actor.animShow
import com.lohina.titantreasuretrove.game.utils.actor.setOnClickListener
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedScreen
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedStage

class TutorialScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val aZefsImg       = Image(game.spriteUtil.SPLASH.ZEFS)
    private val aTutorialImg   = Image(game.spriteUtil.GAME.TUTORIAL)
    private val aUnderstoodImg = Image(game.spriteUtil.GAME.UNDERSTOOD)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBtns() {
        addActors(aZefsImg, aTutorialImg, aUnderstoodImg)
        aZefsImg.apply { setBounds(385f, 0f, 254f, 181f) }
        aTutorialImg.apply { setBounds(198f, 160f, 320f, 226f) }
        aUnderstoodImg.apply { setBounds(657f, 114f, 293f, 96f) }

        aUnderstoodImg.setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(GameScreen(game), MenuScreen(game)) } }
    }

}