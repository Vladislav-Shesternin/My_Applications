package com.mvgamesteam.mineta.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.actors.AButton
import com.mvgamesteam.mineta.game.actors.AProgressMS
import com.mvgamesteam.mineta.game.utils.TIME_ANIM
import com.mvgamesteam.mineta.game.utils.actor.animHide
import com.mvgamesteam.mineta.game.utils.actor.animShow
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedStage
import com.mvgamesteam.mineta.game.utils.region
import com.mvgamesteam.mineta.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.Jer.MSP)
    private val ms       = Image(game.Jer.MS)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Sap.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(99f, 237f, 1324f, 574f) })
        addPanel()
        addActor(ms.apply { setBounds(1066f, 359f, 197f, 344f) })

        addActor(back)
        back.apply {
            setBounds(1255f, 19f, 234f, 125f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgressMS(this@SettingsScreen)
        val progressSound = AProgressMS(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(199f, 613f, 1124f, 99f)

            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.musicUtil.volumeLevelFlow.value = it
                    }
                }
            }
        }
        progressSound.apply {
            setBounds(199f, 351f, 1124f, 99f)

            progressPercentFlow.value = screen.game.soundUtil.volumeLevel

            coroutine?.launch {
                progressPercentFlow.collect {
                    runGDX {
                        screen.game.soundUtil.volumeLevel = it
                    }
                }
            }
        }
    }

}