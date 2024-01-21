package com.hlperki.pesgllra.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.hlperki.pesgllra.game.utils.TextureEmpty
import com.hlperki.pesgllra.game.utils.advanced.AdvancedGroup
import com.hlperki.pesgllra.game.utils.advanced.AdvancedScreen
import com.hlperki.pesgllra.game.utils.region

class AHeppyImg(override val screen: AdvancedScreen): AdvancedGroup() {

    private val img = Image(TextureEmpty.region)

    override fun addActorsOnGroup() {
        addAndFillActor(img)
    }

    fun happy() {
        img.drawable = TextureRegionDrawable(screen.game.assets.ggg)
    }

    fun dehappy() {
        img.drawable = TextureRegionDrawable(screen.game.assets.rrr)
    }

}