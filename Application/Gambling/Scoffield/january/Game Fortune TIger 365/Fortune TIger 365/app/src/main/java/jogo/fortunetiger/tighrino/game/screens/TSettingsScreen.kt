package jogo.fortunetiger.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogo.fortunetiger.tighrino.game.LibGDXGame
import jogo.fortunetiger.tighrino.game.actors.button.AButton
import jogo.fortunetiger.tighrino.game.actors.checkbox.ACheckBox
import jogo.fortunetiger.tighrino.game.actors.checkbox.ACheckBoxGroup
import jogo.fortunetiger.tighrino.game.actors.progress.AProgressMusicSound
import jogo.fortunetiger.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogo.fortunetiger.tighrino.game.utils.actor.animHide
import jogo.fortunetiger.tighrino.game.utils.actor.animShow
import jogo.fortunetiger.tighrino.game.utils.actor.setOnClickListener
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedStage
import jogo.fortunetiger.tighrino.game.utils.region
import kotlinx.coroutines.launch

class TSettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private companion object {
        var isYes = true
    }

    private val progressMusic = AProgressMusicSound(this)
    private val progressSound = AProgressMusicSound(this)
    private val cbYes = ACheckBox(this, ACheckBox.Static.Type.CHECK_BOX)
    private val cbNot = ACheckBox(this, ACheckBox.Static.Type.CHECK_BOX)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.B_MAIN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addSettings()
        addCheckBoxes()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TSettingsScreen, AButton.Static.Type.EXIT)
        addActor(menu)
        menu.setBounds(44f, 1754f, 114f, 119f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TSettingsScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(925f, 1754f, 114f, 119f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addSettings() {
        val settings = Image(game.gameAssets.F_SETTINGS)
        addActor(settings)
        settings.setBounds(116f, 309f, 859f, 1603f)

        addActors(progressMusic, progressSound)
        // Music
        progressMusic.apply {
            setBounds(330f, 1021f, 419f, 72f)
            setProgressPercent(screen.game.musicUtil.volumeLevelFlow.value)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.musicUtil.volumeLevelFlow.value = it
                }
            }
        }
        // Sound
        progressSound.apply {
            setBounds(330f, 763f, 419f, 72f)
            setProgressPercent(screen.game.soundUtil.volumeLevel)

            coroutine?.launch {
                progressPercentFlow.collect {
                    screen.game.soundUtil.volumeLevel = it
                }
            }
        }
    }

    private fun AdvancedStage.addCheckBoxes() {
        addActors(cbYes, cbNot)

        val cbg = ACheckBoxGroup()
        cbYes.apply {
            checkBoxGroup = cbg
            setBounds(354f, 524f, 49f, 36f)
            if (isYes) check()
            setOnCheckListener { if (it) isYes = true }
        }
        cbNot.apply {
            checkBoxGroup = cbg
            setBounds(775f, 524f, 49f, 36f)
            if (isYes.not()) check()
            setOnCheckListener { if (it) isYes = false }
        }
    }

}