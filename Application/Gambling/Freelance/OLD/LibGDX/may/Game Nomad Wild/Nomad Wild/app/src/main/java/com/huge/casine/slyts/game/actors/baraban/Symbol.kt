package com.huge.casine.slyts.game.actors.baraban

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.huge.casine.slyts.game.manager.SpriteManager
import com.huge.casine.slyts.game.utils.advanced.AdvancedGroup

class Symbol(frontRegion: TextureRegion) : AdvancedGroup() {

    private val back  = Image(SpriteManager.GameRegion.JOLTIY.region)
    private val front = Image(frontRegion)

    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(back)
        addActor(front)
        front.setBounds(2f, 2f, 110f, 110f)
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setSymbol(frontRegion: TextureRegion) {
        front.drawable = TextureRegionDrawable(frontRegion)
    }


}