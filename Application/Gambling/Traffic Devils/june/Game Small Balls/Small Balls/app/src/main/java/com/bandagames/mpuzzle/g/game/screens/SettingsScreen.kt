package com.bandagames.mpuzzle.g.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.actors.AButton
import com.bandagames.mpuzzle.g.game.actors.AProgressMusic
import com.bandagames.mpuzzle.g.game.actors.AProgressSound
import com.bandagames.mpuzzle.g.game.utils.TIME_ANIM
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.actor.animShow
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.region
import com.bandagames.mpuzzle.g.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.Aoll.saturetion)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Aoll.begi.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(23f, 89f, 1034f, 1756f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(429f, 1474f, 224f, 129f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgressMusic(this@SettingsScreen)
        val progressSound = AProgressSound(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(239f, 547f, 85f, 825f)

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
            setBounds(756f, 547f, 85f, 825f)

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