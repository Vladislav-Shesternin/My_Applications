package rainbowriches.lucky.start.game.screens

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.LibGDXGame
import rainbowriches.lucky.start.game.actors.button.AButton
import rainbowriches.lucky.start.game.actors.checkbox.ACheckBox
import rainbowriches.lucky.start.game.actors.panel.APanelMusicSound
import rainbowriches.lucky.start.game.actors.panel.APanelRules
import rainbowriches.lucky.start.game.utils.TIME_ANIM_SCREEN_ALPHA
import rainbowriches.lucky.start.game.utils.actor.animShow_Suspend
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.advanced.AdvancedStage
import rainbowriches.lucky.start.game.utils.region
import rainbowriches.lucky.start.game.utils.runGDX

class MusicSoundScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }
    private val menuBtn = AButton(this, AButton.Static.Type.MENU).apply { color.a = 0f }
    private val panel   = APanelMusicSound(this).apply { color.a = 0f }

    override fun show() {
        setBackgrounds(game.splashAssets.ASK_MY_NAME.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMusicCB()
                addMenuBtn()
                addPanel()
            }
            delay(500)
            val time2 = 0.3f
            musicCB.animShow_Suspend(time2)
            menuBtn.animShow_Suspend(time2)
            panel.animShow_Suspend(time2)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(70f, 1684f, 182f, 193f)
            setOnClickListener { animHideScreen { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(828f, 1684f, 182f, 193f)
            if (isMusic) uncheck(false) else check(false)

            setOnCheckListener {
                if (it) {
                    isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    isMusic = true
                    game.musicUtil.music?.play()
                }
            }
            
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panel)
        panel.apply { setBounds(57f, 415f, 876f, 1044f) }
    }

}