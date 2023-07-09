package com.mariam.cleverfinancier.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mariam.cleverfinancier.game.actors.masks.normal.Mask
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.Size
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress: AdvancedGroup() {

    companion object {
        const val PROGRESS_LENGTH = 586f
        val SIZE = Size(588f, 24f)
    }

    //private val backImage  = Image(SpriteManager.GameRegion.BACK.region)
    private val frontImage = Image(SpriteManager.GameRegion.BLUCK.region)
    //private val leverImage = Image(SpriteManager.GameRegion.LEVER.region)
    private val mask       = Mask(SpriteManager.GameRegion.BLACK.region)

    private val onePercentX = PROGRESS_LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        //addBackImage()
        addMask()

        mask.addFrontImage()
        //addLeverImage()

        coroutine.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    //leverImage.x = percent * onePercentX
                    frontImage.x = (percent * onePercentX) - SIZE.width
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackImage() {
//        addActor(backImage)
//        backImage.apply {
//            setBounds(LP.back, LP.size)
//        }
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.apply {
            setSize(SIZE.width, SIZE.height)
        }
    }

    private fun AdvancedGroup.addFrontImage() {
        addActor(frontImage)
        frontImage.apply {
            setSize(SIZE.width, SIZE.height)
        }
    }
    private fun AdvancedGroup.addLeverImage() {
//        addActor(leverImage)
//
//        leverImage.apply {
//            setBounds(Vector2(0f, 0f), LP.leverSize)
//        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun inputListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            progressPercentFlow.value = when {
                x <= 0 -> 0f
                x >= PROGRESS_LENGTH -> 100f
                else -> x / onePercentX
            }
        }
    }


}