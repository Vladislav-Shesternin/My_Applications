package com.doradogames.confli.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.doradogames.confli.game.LibGDXGame
import com.doradogames.confli.game.actors.AButton
import com.doradogames.confli.game.actors.AProgress
import com.doradogames.confli.game.utils.TIME_ANIM
import com.doradogames.confli.game.utils.actor.animHide
import com.doradogames.confli.game.utils.actor.animShow
import com.doradogames.confli.game.utils.advanced.AdvancedScreen
import com.doradogames.confli.game.utils.advanced.AdvancedStage
import com.doradogames.confli.game.utils.region
import com.doradogames.confli.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.assets.ms)
    private val back     = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.load.Loading.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(107f, 465f, 850f, 1274f) })

        addPanel()

        addActor(back)
        back.apply {
            setBounds(204f, 190f, 656f, 187f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@SettingsScreen)
        val progressSound = AProgress(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(272f, 567f, 122f, 907f)

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
            setBounds(671f, 567f, 122f, 907f)

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