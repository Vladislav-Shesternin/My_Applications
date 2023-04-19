//package com.veldan.bigwinslots777.actors.tutorial
//
//import com.badlogic.gdx.scenes.scene2d.actions.Actions
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.badlogic.gdx.utils.Align
//import com.veldan.bigwinslots777.actors.button.ButtonClickable
//import com.veldan.bigwinslots777.actors.button.ButtonClickableStyle
//import com.veldan.bigwinslots777.actors.label.LabelStyle
//import com.veldan.bigwinslots777.actors.label.scrolling.ScrollingLabel
//import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup
//import com.veldan.bigwinslots777.advanced.group.AdvancedGroup
//import com.veldan.bigwinslots777.utils.disable
//import com.veldan.bigwinslots777.utils.listeners.toClickable
//import com.veldan.bigwinslots777.layout.Layout.TutorialGroup as LT
//
//class TutorialGroup : AbstractAdvancedGroup() {
//    override val controller = TutorialGroupController(this)
//
//    val dialogImage = Image()
//    val frameImage  = Image()
//    val textLabel   = ScrollingLabel("", LabelStyle.font_50, Align.center, Align.center)
//    val skipButton  = ButtonClickable(ButtonClickableStyle.skip)
//
//
//
//    init {
//        disable()
//        addActorsOnGroup()
//        toClickable().setOnClickListener { controller.clickHandler() }
//        children.onEach { it.addAction(Actions.alpha(0f)) }
//    }
//
//
//
//    private fun AdvancedGroup.addActorsOnGroup() {
//        addDialogImage()
//        addFrameImage()
//        addTextLabel()
//        addSkipImage()
//    }
//
//    private fun AdvancedGroup.addDialogImage() {
//        addActor(dialogImage)
//    }
//
//    private fun AdvancedGroup.addFrameImage() {
//        addActor(frameImage)
//    }
//
//    private fun AdvancedGroup.addTextLabel() {
//        addActor(textLabel)
//    }
//
//    private fun AdvancedGroup.addSkipImage() {
//        addActor(skipButton)
//        skipButton.apply {
//            setBounds(LT.SKIP_X, LT.SKIP_Y, LT.SKIP_W, LT.SKIP_H)
//            toClickable().setOnClickListener { this@TutorialGroup.controller.isFinishFlow.tryEmit(true) }
//        }
//    }
//
//}