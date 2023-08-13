package com.tropical.treasure.catcher.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tropical.treasure.catcher.advanced.AdvancedGroup
import com.tropical.treasure.catcher.assets.SpriteManager
import com.tropical.treasure.catcher.utils.BASKET_X
import com.tropical.treasure.catcher.utils.HERO_FRAME_X

class Hero: AdvancedGroup() {

    private val hero = Image(SpriteManager.hero)

    var basketX = BASKET_X
         private set
    var frameX = HERO_FRAME_X
        private set


    override fun positionChanged() {
        basketX = x + BASKET_X
        frameX = x + HERO_FRAME_X
    }

    override fun sizeChanged() {
        super.sizeChanged()
        if (width > 0 && height > 0) addAndFillActor(hero)
    }

}