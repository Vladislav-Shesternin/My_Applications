package com.veldan.boost.and.clean.simpleapp.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.boost.and.clean.simpleapp.MainActivity
import com.veldan.boost.and.clean.simpleapp.game.actors.Back
import com.veldan.boost.and.clean.simpleapp.game.actors.ControlPanel
import com.veldan.boost.and.clean.simpleapp.game.actors.PanelBoost
import com.veldan.boost.and.clean.simpleapp.game.actors.button.AButton
import com.veldan.boost.and.clean.simpleapp.game.actors.button.AButtonStyle
import com.veldan.boost.and.clean.simpleapp.game.actors.label.ALabelStyle
import com.veldan.boost.and.clean.simpleapp.game.game
import com.veldan.boost.and.clean.simpleapp.game.manager.NavigationManager
import com.veldan.boost.and.clean.simpleapp.game.utils.GameColor
import com.veldan.boost.and.clean.simpleapp.game.utils.actor.disable
import com.veldan.boost.and.clean.simpleapp.game.utils.actor.enable
import com.veldan.boost.and.clean.simpleapp.game.utils.actor.setBounds
import com.veldan.boost.and.clean.simpleapp.game.utils.advanced.AdvancedGroup
import com.veldan.boost.and.clean.simpleapp.game.utils.advanced.AdvancedScreen
import com.veldan.boost.and.clean.simpleapp.game.utils.advanced.AdvancedStage
import com.veldan.boost.and.clean.simpleapp.game.utils.hideTime
import com.veldan.boost.and.clean.simpleapp.game.utils.runGDX
import com.veldan.boost.and.clean.simpleapp.game.utils.showTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.veldan.boost.and.clean.simpleapp.game.actors.label.ALabelStyle.Mulish.Medium as SMMedium
import com.veldan.boost.and.clean.simpleapp.game.actors.label.ALabelStyle.Mulish.SemiBold as SMSemiBold
import com.veldan.boost.and.clean.simpleapp.game.utils.Layout.Common as LC

class BoostScreen: AdvancedScreen() {

    companion object {
        var isFinish = false
            private set
    }

    private var isStartClean = false

    private val descriptionText = "Speed up your device by analyzing memory, clearing notifications, closing background tasks. You can speed up your device with just one click."

    private val controlPanel     = ControlPanel(ControlPanel.Type.BOOST)
    private val panelBoost       = PanelBoost(if (isFinish) PanelBoost.Type.FINISH else PanelBoost.Type.START)
    private val descriptionLabel = Label(descriptionText, ALabelStyle.style(SMMedium._26, GameColor.black_40))
    private val button           = AButton(AButtonStyle.btn)
    private val label            = Label("Boost", ALabelStyle.style(SMSemiBold._33))
    private val back             = Back("Boost the system")



    override fun AdvancedStage.addActorsOnStageUI() {
        addControlPanel()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBack()

        coroutine.launch {
            withContext(Dispatchers.Default) {
                launch { addPanelBoost() }

                if (isFinish.not()) {
                    launch { addDescription() }
                    launch { addButton() }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addControlPanel() {
        runGDX {
            addActor(controlPanel)
            controlPanel.apply {
                setBounds(LC.controlPanel)
                onCheckClean   = {
                    if (isStartClean) isFinish = true
                    NavigationManager.navigate(CleanScreen(), BoostScreen())
                }
                onCheckBoost   = { }
                onCheckBattery = {
                    if (isStartClean) isFinish = true
                    NavigationManager.navigate(BatteryScreen(), BoostScreen())
                }
                onCheckCooling = {
                    if (isStartClean) isFinish = true
                    NavigationManager.navigate(CoolingScreen(), BoostScreen())
                }
            }
        }
    }

    private suspend fun AdvancedGroup.addPanelBoost() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            addActor(panelBoost)
            panelBoost.apply {
                setBounds(LC.screen)
                addAction(Actions.sequence(
                    Actions.alpha(0f),
                    Actions.fadeIn(showTime),
                    Actions.run { continuation.resume(Unit) }
                ))

                finishBlock = { finishCleaning() }
            }
        }
    }

    private suspend fun AdvancedGroup.addDescription() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            addActor(descriptionLabel)
            descriptionLabel.apply {
                setBounds(LC.description)
                setAlignment(Align.center)
                wrap = true
                addAction(Actions.sequence(
                    Actions.alpha(0f),
                    Actions.fadeIn(showTime),
                    Actions.run { continuation.resume(Unit) }
                ))
            }
        }
    }

    private suspend fun AdvancedGroup.addButton() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            addActor(button)

            button.apply {
                setBounds(LC.button)
                addAndFillActor(label)
                addAction(Actions.sequence(
                    Actions.alpha(0f),
                    Actions.fadeIn(showTime),
                    Actions.run { continuation.resume(Unit) }
                ))

                label.apply {
                    disable()
                    setAlignment(Align.center)
                }

                setOnClickListener { startCleaning() }
            }
        }
    }

    private fun AdvancedGroup.addBack() {
        addActor(back)
        back.apply {
            disable()
            setBounds(LC.back)
            addAction(Actions.alpha(0f))
            setOnClickListener { great() }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun startCleaning() {
        isStartClean = true
        button.disable()
        descriptionLabel.addAction(Actions.sequence(
            Actions.fadeOut(hideTime),
            Actions.run { panelBoost.cleaning() }
        ))
    }

    private fun finishCleaning() {
        isStartClean = false
        isFinish = true
        controlPanel.enable()
        label.setText("Great")
        back.apply {
            enable()
            addAction(Actions.sequence(
                Actions.fadeIn(showTime),
                Actions.run { MainActivity.interstitial.show(game.activity) }
            ))
        }
        button.apply {
            enable()
            setOnClickListener {
                disable()
                great()
            }
        }
    }

    private fun great() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(hideTime),
            Actions.run { NavigationManager.navigate(BoostScreen()) }
        ))
    }

}