package com.veldan.spinia.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.spinia.advanced.AdvancedGroup
import com.veldan.spinia.assets.SpriteManager
import com.veldan.spinia.utils.BASKET_X
import com.veldan.spinia.utils.HERO_FRAME_X
import com.veldan.spinia.utils.log

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