package com.rochevasternin.physical.joints.game.screens.practicalScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.practical.AbstractBGPractical
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.practical.BGPractical_JWeld
import com.rochevasternin.physical.joints.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.rochevasternin.physical.joints.game.utils.actor.animHide
import com.rochevasternin.physical.joints.game.utils.actor.animShow
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedPracticalScreen
import com.rochevasternin.physical.joints.game.utils.region

class Practical_JWeldScreen(override val game: LibGDXGame): AdvancedPracticalScreen(game) {

    // BodyGroup
    override val bgPractical: AbstractBGPractical = BGPractical_JWeld(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        super.show()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

}