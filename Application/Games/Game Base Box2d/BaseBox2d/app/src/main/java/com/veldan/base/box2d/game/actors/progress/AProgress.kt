//package com.veldan.gamebox2d.game.actors.progress
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.scenes.scene2d.InputEvent
//import com.badlogic.gdx.scenes.scene2d.InputListener
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.veldan.gamebox2d.game.actors.masks.normal.Mask
//import com.veldan.gamebox2d.game.manager.SpriteManager
//import com.veldan.gamebox2d.game.utils.actor.setBounds
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
//import com.veldan.gamebox2d.game.utils.runGDX
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//import com.veldan.gamebox2d.game.utils.Layout.Progress as LP
//
//class AProgress: AdvancedGroup() {
//
//    private val backImage  = Image(SpriteManager.GameRegion.BACK.region)
//    private val frontImage = Image(SpriteManager.GameRegion.FRONT.region)
//    private val leverImage = Image(SpriteManager.GameRegion.LEVER.region)
//    private val mask       = Mask(SpriteManager.GameRegion.MASK.region)
//
//    private val onePercentX = LP.progressLength / 100f
//
//    // 0 .. 100 %
//    val progressPercentFlow = MutableStateFlow(0f)
//
//
//    override fun sizeChanged() {
//        if (width > 0 && height > 0) addActorsOnGroup()
//    }
//
//    private fun addActorsOnGroup() {
//        addBackImage()
//        addMask()
//
//        mask.addFrontImage()
//        addLeverImage()
//
//        coroutine.launch {
//            progressPercentFlow.collect { percent ->
//                runGDX {
//                    leverImage.x = percent * onePercentX
//                    frontImage.x = leverImage.x - LP.size.width
//                }
//            }
//        }
//
//        addListener(inputListener())
//    }
//
//    // ---------------------------------------------------
//    // Add Actors
//    // ---------------------------------------------------
//
//    private fun AdvancedGroup.addBackImage() {
//        addActor(backImage)
//        backImage.apply {
//            setBounds(LP.back, LP.size)
//        }
//    }
//
//    private fun AdvancedGroup.addMask() {
//        addActor(mask)
//        mask.apply {
//            setBounds(LP.back, LP.size)
//        }
//    }
//
//    private fun AdvancedGroup.addFrontImage() {
//        addActor(frontImage)
//        frontImage.apply {
//            setBounds(Vector2(0f, 0f), LP.size)
//        }
//    }
//    private fun AdvancedGroup.addLeverImage() {
//        addActor(leverImage)
//
//        leverImage.apply {
//            setBounds(Vector2(0f, 0f), LP.leverSize)
//        }
//    }
//
//    // ---------------------------------------------------
//    // Logic
//    // ---------------------------------------------------
//
//    private fun inputListener() = object : InputListener() {
//        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
//            touchDragged(event, x, y, pointer)
//            return true
//        }
//
//        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
//            progressPercentFlow.value = when {
//                x <= 0 -> 0f
//                x >= LP.progressLength -> 100f
//                else -> x / onePercentX
//            }
//        }
//    }
//
//
//}