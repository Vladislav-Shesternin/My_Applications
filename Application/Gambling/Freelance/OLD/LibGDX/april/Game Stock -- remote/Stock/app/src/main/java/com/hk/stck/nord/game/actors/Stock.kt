package com.hk.stck.nord.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hk.stck.nord.game.actors.button.AButton
import com.hk.stck.nord.game.actors.button.AButtonStyle
import com.hk.stck.nord.game.actors.label.spinning.SpinningLabel
import com.hk.stck.nord.game.manager.FontTTFManager
import com.hk.stck.nord.game.utils.actor.disable
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import com.hk.stck.nord.game.utils.runGDX
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class Stock: AdvancedGroup() {

    private val btn  = AButton(AButtonStyle.stock)
    private val text = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Regular.font_65.font, Color.WHITE))

    private var isUpdated = false

    var block: () -> Unit = {}


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(btn)
        addText()
        btn.setOnClickListener { block() }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private var scopee: Job? = null

    private fun addText() {
        addActor(text)
        text.setBounds(26f, 50f, 488f, 78f)
        text.disable()

        scopee = coroutine.launch {
            while (isActive && isUpdated.not()) {
                runGDX { if (isUpdated.not()) text.setText("Loading.") }
                delay(100)
                runGDX { if (isUpdated.not()) text.setText("Loading..") }
                delay(100)
                runGDX { if (isUpdated.not()) text.setText("Loading...") }
                delay(100)
                runGDX { if (isUpdated.not()) text.setText("Loading..") }
                delay(100)
                runGDX { if (isUpdated.not()) text.setText("Loading.") }
                delay(100)
            }
        }
    }

    fun update(str: String) {
        if (isUpdated.not()) {
            isUpdated = true
            scopee?.cancel()
        }

        text.addAction(Actions.sequence(
            Actions.fadeOut(0.3f),
            Actions.run { text.setText(str) },
            Actions.fadeIn(0.3f)
        ))
    }

}