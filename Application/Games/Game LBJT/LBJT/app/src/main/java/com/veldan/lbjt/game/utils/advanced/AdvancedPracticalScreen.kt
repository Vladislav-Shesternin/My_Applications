package com.veldan.lbjt.game.utils.advanced

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.bodiesGroup.practical.AbstractBGPractical

abstract class AdvancedPracticalScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    abstract val bgPractical: AbstractBGPractical

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Practical()
    }

    // ---------------------------------------------------
    // Create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Practical() {
        bgPractical.create(0f, 145f, 700f, 1255f)
    }

}