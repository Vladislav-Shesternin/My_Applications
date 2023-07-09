//package com.veldan.gamebox2d.game.actors
//
//import com.badlogic.gdx.graphics.g2d.TextureRegion
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.veldan.gamebox2d.game.actors.masks.normal.Mask
//import com.veldan.gamebox2d.game.manager.SpriteManager
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
//import com.veldan.gamebox2d.util.log
//import kotlinx.coroutines.cancel
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.isActive
//import kotlinx.coroutines.launch
//
//class LoaderGroup(val region: TextureRegion): AdvancedGroup() {
//
//    private val mask   = Mask(SpriteManager.ListRegion.LOADER.regionList[100])
//    private val loader = Image(region)
//
//
//    override fun sizeChanged() {
//        if (width > 0 && height > 0) {
//            addAndFillActor(mask)
//            mask.addAndFillActor(loader)
//        }
//    }
//
//
//    fun setProgress(percent: Int) {
//        if (percent in 0..100) mask.maskRegion = SpriteManager.ListRegion.LOADER.regionList[percent]
//    }
//
//}