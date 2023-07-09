package com.vitaliyi.financeanalizator.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.vitaliyi.financeanalizator.game.actors.masks.normal.Mask
import com.vitaliyi.financeanalizator.game.manager.SpriteManager
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGroup

class LoaderGroup(val region: TextureRegion): AdvancedGroup() {

    private val mask   = Mask(SpriteManager.ListRegion.LOADER.regionList.first())
    private val loader = Image(region)


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(mask)
            mask.addAndFillActor(loader)
        }
    }


    fun setProgress(percent: Int) {
        if (percent in 0..100) mask.maskRegion = SpriteManager.ListRegion.LOADER.regionList[percent]
    }

}