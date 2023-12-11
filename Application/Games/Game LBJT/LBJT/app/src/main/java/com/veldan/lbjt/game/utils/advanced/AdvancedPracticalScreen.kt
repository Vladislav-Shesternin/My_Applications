package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.Input
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.bodiesGroup.practical.AbstractBGPractical
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide

abstract class AdvancedPracticalScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    abstract val bgPractical: AbstractBGPractical

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Practical()
    }

    override fun keyDown(keycode: Int): Boolean {
        return if (keycode == Input.Keys.BACK && bgPractical.aPracticalSettings.isOpen) {
            bgPractical.bPracticalSettings.getActor()?.runDoneBlock()
            true
        } else super.keyDown(keycode)
    }

    // ---------------------------------------------------
    // Create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Practical() {
        bgPractical.create(0f, 145f, 700f, 1255f)
    }

}