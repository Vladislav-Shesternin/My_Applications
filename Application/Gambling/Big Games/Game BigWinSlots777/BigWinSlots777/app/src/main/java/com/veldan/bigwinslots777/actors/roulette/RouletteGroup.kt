package com.veldan.bigwinslots777.actors.roulette

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup


abstract class RouletteGroup<T : RouletteGroupController.RouletteItem>(
    val rouletteRegion: TextureRegion,
    val items         : List<T>,
) : AbstractAdvancedGroup() {

    override val controller by lazy { RouletteGroupController(this) }

    private val roulette = Image(rouletteRegion)



    override fun sizeChanged() {
        if (width > 0 && height > 0) addAndFillActor(roulette)
    }

}