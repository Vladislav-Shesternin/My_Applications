package com.jumping.cubuletus.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.jumping.cubuletus.game.manager.SpriteManager
import com.jumping.cubuletus.game.util.advanced.AdvancedGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.jumping.cubuletus.game.util.Layout.Coub as LC

class ACoub: AdvancedGroup() {

    private val colorsImage = List(4) { Image(SpriteManager.ListRegion.COLORS.regionList[it]) }



    override fun sizeChanged() {
        if (width >= 0 && height >= 0) addActorsOnGroup()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.COUB.region))
        addColors()
    }

    private fun addColors() {
        var nx = LC.colors.x
        var ny = LC.colors.y

        colorsImage.onEachIndexed { index, image ->
            addActor(image)
            with(LC.colors) {
                image.setBounds(nx, ny, w, h)
                nx += w + hs
                if (index == 1) {
                    nx = x
                    ny -= h + vs
                }
            }

            coroutine.launch {
                while (isActive) {
                    delay(200)
                    image.drawable = TextureRegionDrawable(SpriteManager.ListRegion.COLORS.regionList.shuffled().first())
                }
            }

        }
    }



}