package fortunetiger.jogos.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.jogos.tighrino.game.LibGDXGame
import fortunetiger.jogos.tighrino.game.actors.TmpGroup
import fortunetiger.jogos.tighrino.game.actors.checkbox.ACheckBox
import fortunetiger.jogos.tighrino.game.utils.TIME_ANIM
import fortunetiger.jogos.tighrino.game.utils.actor.animHide
import fortunetiger.jogos.tighrino.game.utils.actor.animShow
import fortunetiger.jogos.tighrino.game.utils.actor.setOnClickListener
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedGroup
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedStage

class ValSettingsScreen(_game: LibGDXGame) : IPanelScreen(_game) {

    private val tmpGroup    = TmpGroup(this).apply { color.a = 0f }
    private val settingsImg = Image(game.allAssets.VAL_SETTINGS)
    private val musicBox    = ACheckBox(this, ACheckBox.Static.Type.OFF_ON)
    private val soundBox    = ACheckBox(this, ACheckBox.Static.Type.OFF_ON)


    override fun AdvancedStage.addActorsOnStage() {
        addTmpGroup()

        tmpGroup.apply {
            addSettings()
            addMusicSound()
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addTmpGroup() {
        addActor(tmpGroup)
        tmpGroup.setBounds(287f, 640f, 459f, 864f)
        tmpGroup.animShow(TIME_ANIM)

        val xBtn = Actor()
        addActor(xBtn)
        xBtn.apply {
            setBounds(812f, 350f, 171f, 175f)
            setOnClickListener(game.soundUtil) {
                tmpGroup.animHide(TIME_ANIM) { game.navigationManager.back() }
            }
        }
    }

    private fun AdvancedGroup.addSettings() {
        addActor(settingsImg)
        settingsImg.setBounds(0f, 186f, 459f, 678f)
    }

    private fun AdvancedGroup.addMusicSound() {
        addActors(musicBox, soundBox)
        musicBox.apply {
            setBounds(24f, 362f, 402f, 163f)
            if (game.musicUtil.music?.isPlaying == true) check(false)

            setOnCheckListener { isCheck ->
                game.musicUtil.music?.let { music ->
                    if (isCheck) music.play() else music.pause()
                }
            }
        }
        soundBox.apply {
            setBounds(24f, 0f, 402f, 163f)
            if (game.soundUtil.isPause.not()) check(false)

            setOnCheckListener { isCheck ->
                game.soundUtil.isPause = !isCheck
            }
        }
    }

}