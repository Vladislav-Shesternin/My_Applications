package com.veldan.fantasticslots.actors.trainingDialog

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.disable
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.TrainingDialog as TD

class TrainingDialog: AbstractAdvancedGroup() {
    override val controller = TrainingDialogController(this)

    private val backgroundImage = Image(SpriteManager.GameSprite.TRAINING_BACKGROUND.data.texture)
    private val trainingLabel   = RollingLabel(activityResources.getString(R.string.training_upper), LabelStyleUtil.robotoMono60)
    private val skipButton      = ButtonClickable(ButtonClickable.Style.button_1)
    private val startButton     = ButtonClickable(ButtonClickable.Style.button_1)
    private val skipLabel       = RollingLabel(activityResources.getString(R.string.skip), LabelStyleUtil.robotoMono60)
    private val startLabel      = RollingLabel(activityResources.getString(R.string.start), LabelStyleUtil.robotoMono60)

    var doAfterSkip : () -> Unit = {}
    var doAfterStart: () -> Unit = {}


    init {
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addBackground()
        addTrainingLabel()
        addSkip()
        addStart()
    }

    private fun addBackground() {
        addAndFillActor(backgroundImage)
    }

    private fun addTrainingLabel() {
        addActor(trainingLabel)
        trainingLabel.setBoundsFigmaY(TD.TRAINING_X, TD.TRAINING_Y,TD.TRAINING_W,TD.TRAINING_H)
    }

    private fun addSkip() {
        addActor(skipButton)
        skipButton.apply {
            setBoundsFigmaY(TD.SKIP_X, TD.SKIP_Y, TD.SKIP_W, TD.SKIP_H)
            addAndFillActor(skipLabel)
            skipLabel.disable()
            setOnClickListener { doAfterSkip() }
        }
    }

    private fun addStart() {
        addActor(startButton)
        startButton.apply {
            setBoundsFigmaY(TD.START_X, TD.START_Y,TD.START_W,TD.START_H)
            addAndFillActor(startLabel)
            startLabel.disable()
            setOnClickListener { doAfterStart() }
        }
    }


}