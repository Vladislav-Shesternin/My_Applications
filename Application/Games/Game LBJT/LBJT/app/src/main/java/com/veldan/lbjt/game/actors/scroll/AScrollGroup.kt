package com.veldan.lbjt.game.actors.scroll

import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen

class AScrollGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    override fun getPrefWidth(): Float {
        return width
    }

    override fun getPrefHeight(): Float {
        return height
    }

    override fun addActorsOnGroup() {}

}