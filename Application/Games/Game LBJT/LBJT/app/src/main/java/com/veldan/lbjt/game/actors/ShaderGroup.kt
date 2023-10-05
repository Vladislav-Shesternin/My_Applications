package com.veldan.lbjt.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen

class ShaderGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val image = Image(screen.game.themeUtil.assets.LANGUAGE_UK)

    override fun addActorsOnGroup() {
        addAndFillActor(image)

    }



}