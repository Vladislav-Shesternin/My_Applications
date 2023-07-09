package com.vitaliyi.financeanalizator.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.vitaliyi.financeanalizator.game.manager.FontTTFManager
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGroup
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class LOA(
    loader: TextureRegion,
    center: TextureRegion,
) : AdvancedGroup() {

    private val loaderG       = LoaderGroup(loader)
    private val centerImage   = Image(center)
    private val babloLabel    = Label("₽${number(1, 9, 4)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RMedium.font_34.font, Color.WHITE))


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(loaderG, centerImage, babloLabel)
            loaderG.setBounds(0f, 65f, 177f, 177f)
            centerImage.setBounds(24f, 108f, 129f, 91f)
            babloLabel.setBounds(0f, 0f, 177f, 40f)

            var progress = 0
            val maxL     = (10..100).shuffled().first()

            coroutine.launch {
                while (isActive) {
                    if (progress <= maxL) {
                        delay(30)
                        loaderG.setProgress(progress)
                        progress++
                    } else cancel()
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}