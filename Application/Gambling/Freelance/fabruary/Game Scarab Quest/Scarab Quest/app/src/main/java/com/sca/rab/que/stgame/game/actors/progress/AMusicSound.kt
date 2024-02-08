//package com.sca.rab.que.stgame.game.actors.progress
//
//import com.badlogic.gdx.scenes.scene2d.InputEvent
//import com.badlogic.gdx.scenes.scene2d.InputListener
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.sca.rab.que.stgame.game.actors.masks.Mask
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//import com.sca.rab.que.stgame.game.utils.advanced.AdvancedGroup
//import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
//import com.sca.rab.que.stgame.game.utils.runGDX
//
//class AMusicSound(override val screen: AdvancedScreen): AdvancedGroup() {
//
//    private val LENGTH = 626f
//
//    private val mask          = Mask(screen, screen.game.alllAssets.VOLUME_MASK, alphaWidth = 1000)
//    private val progressImage = Image(screen.game.alllAssets.volume_progress)
//    private val cursorImage   = Image(screen.game.alllAssets.lapa)
//
//    private val onePercentX = LENGTH / 100f
//
//    // 0 .. 100 %
//    val progressPercentFlow = MutableStateFlow(0f)
//
//
//    override fun addActorsOnGroup() {
//        addMask()
//        addLapa()
//
//        coroutine?.launch {
//            progressPercentFlow.collect { percent ->
//                runGDX {
//                    progressImage.x = (percent * onePercentX) - LENGTH
//                    cursorImage.x = progressImage.x + LENGTH - 35f
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
//    private fun AdvancedGroup.addMask() {
//        addAndFillActor(mask)
//        mask.addProgress()
//    }
//
//    private fun AdvancedGroup.addProgress() {
//        addActor(progressImage)
//        progressImage.setBounds(-LENGTH, 0f, LENGTH, 49f)
//    }
//
//    private fun AdvancedGroup.addLapa() {
//        addActor(cursorImage)
//        cursorImage.setBounds(0f, -9f, 70f, 70f)
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
//                x >= LENGTH -> 100f
//                else -> x / onePercentX
//            }
//        }
//    }
//
//    fun setProgressPercent(percent: Float) {
//        progressPercentFlow.value = percent
//    }
//
//
//}