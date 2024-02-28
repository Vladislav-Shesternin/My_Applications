package com.minimal.tennis.fortwo.game.actors

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedGroup
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedScreen

class AShar(override val screen: AdvancedScreen): AdvancedGroup() {

    private val sharImg = Image().apply { touchable = Touchable.disabled }

    var boomBlock: () -> Unit = {}

    override fun addActorsOnGroup() {

    }

}