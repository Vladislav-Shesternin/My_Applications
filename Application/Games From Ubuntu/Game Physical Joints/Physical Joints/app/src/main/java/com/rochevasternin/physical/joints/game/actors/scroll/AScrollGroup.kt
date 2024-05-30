package com.rochevasternin.physical.joints.game.actors.scroll

import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedGroup
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedScreen

class AScrollGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    override fun getPrefWidth(): Float {
        return width
    }

    override fun getPrefHeight(): Float {
        return height
    }

    override fun addActorsOnGroup() {}

}