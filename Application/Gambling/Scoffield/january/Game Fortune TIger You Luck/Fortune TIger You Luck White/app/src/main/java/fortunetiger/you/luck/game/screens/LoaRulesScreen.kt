package fortunetiger.you.luck.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.you.luck.game.LibGDXGame
import fortunetiger.you.luck.game.actors.checkbox.ACheckBox
import fortunetiger.you.luck.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortunetiger.you.luck.game.utils.actor.animShow_Suspend
import fortunetiger.you.luck.game.utils.actor.setOnClickListener
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedStage
import fortunetiger.you.luck.game.utils.region
import fortunetiger.you.luck.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoaRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB   = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }
    private val rulesImg  = Image(game.gameAssets.LOA_RILUSE).apply { color.a = 0f }
    private val suslikImg = Image(game.gameAssets.suslik).apply { color.a = 0f }

    override fun show() {
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMusicCB()
                addRules()
                addSuslikImg()
            }
            delay(470)
            musicCB.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            suslikImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            rulesImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(891f, 1725f, 156f, 162f)
            if (LoaMenuScreen.isMusic.not()) check(false)

            setOnCheckListener {
                if (it) {
                    LoaMenuScreen.isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    LoaMenuScreen.isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addSuslikImg() {
        addActor(suslikImg)
        suslikImg.setBounds(0f, -6f, 458f, 618f)
    }

    private fun AdvancedStage.addRules() {
        addActor(rulesImg)
        rulesImg.setBounds(93f, 482f, 943f, 1158f)

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(865f, 1343f, 171f, 175f)
            setOnClickListener(game.soundUtil) { animHideScreen { game.navigationManager.back() } }
        }
    }

}