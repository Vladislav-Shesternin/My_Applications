package com.veldan.pinup.actors.slot.glow

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.layout.Layout.Glow as LG

class Glow : AbstractAdvancedGroup() {
    override val controller = GlowController(this)

    val glowImageList = List(GlowController.GLOW_ITEM_COUNT) { Image(SpriteManager.GameSprite.GLOW.data.texture) }



    init {
        setSize(LG.W, LG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addGlowGroup()
    }



    private fun addGlowGroup() {
        var newY = LG.GLOW_ITEM_FIRST_Y
        glowImageList.onEach { image ->
            addActor(image)
            image.apply {
                setBounds(0f, newY, LG.GLOW_ITEM_W, LG.GLOW_ITEM_H)
                addAction(Actions.alpha(0f))
            }
            newY -= (LG.GLOW_ITEM_SPACE_VERTICAL + LG.GLOW_ITEM_H)

        }
    }

}