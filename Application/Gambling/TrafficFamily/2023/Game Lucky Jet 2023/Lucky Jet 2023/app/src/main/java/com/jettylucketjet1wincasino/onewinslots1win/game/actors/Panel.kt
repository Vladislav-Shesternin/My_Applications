package com.jettylucketjet1wincasino.onewinslots1win.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.label.ALabelStyle
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.runGDX
import com.jettylucketjet1wincasino.onewinslots1win.util.toMS
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Panel as LP

class Panel : AdvancedGroup() {

    val tapFlow          = MutableStateFlow(0)
    val timerTimeFlow    = MutableStateFlow(60)
    val isStartTimerFlow = MutableStateFlow(false)

    var finishBlock: () -> Unit = {}

    private val countLabel = Label("0", ALabelStyle.style(ALabelStyle.Bold._35))
    private val timeLabel  = Label("0:0", ALabelStyle.style(ALabelStyle.Regular._35))


    override fun sizeChanged() {
        super.sizeChanged()
        if (width > 0 && height > 0) addActorsOnGroup()
    }


    private fun addActorsOnGroup() {
        addCount()
        addTimer()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addCount() {
        addActor(countLabel)
        countLabel.apply {
            setBounds(LP.count)
            //setAlignment(Align.center)
        }
        coroutine.launch { collectTapFlow() }
    }

    private fun addTimer() {
        addActor(timeLabel)
        timeLabel.apply {
            setBounds(LP.time)
            setAlignment(Align.right)
        }
        coroutine.launch {
            isStartTimerFlow.collect {
                while (it) {
                    delay(1f.toMS)
                    if (timerTimeFlow.value > 0) timerTimeFlow.value -= 1
                    else {
                        isStartTimerFlow.value = false
                        finishBlock()
                        coroutine.cancel()
                    }
                }
            }
        }

        coroutine.launch { collectTimerFlow() }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun collectTapFlow() {
        tapFlow.collect { count -> runGDX { countLabel.setText(count) } }
    }

    private suspend fun collectTimerFlow() {
        timerTimeFlow.collect { time ->
            if (time >= 0) {
                val minutes = (time / 60f).toInt()
                val seconds = (time % 60f).toInt()

                runGDX { timeLabel.setText("${minutes}:${if (seconds < 10) "0$seconds" else seconds}") }
            }
        }
    }



}