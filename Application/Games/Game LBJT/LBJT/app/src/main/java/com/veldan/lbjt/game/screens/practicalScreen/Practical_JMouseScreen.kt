package com.veldan.lbjt.game.screens.practicalScreen

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.bodiesGroup.practical.AbstractBGPractical
import com.veldan.lbjt.game.box2d.bodiesGroup.practical.BGPractical_JMouse
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedMouseScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedPracticalScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.region

class Practical_JMouseScreen(override val game: LibGDXGame): AdvancedPracticalScreen(game) {

    // BodyGroup
    override val bgPractical: AbstractBGPractical = BGPractical_JMouse(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        super.show()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

}