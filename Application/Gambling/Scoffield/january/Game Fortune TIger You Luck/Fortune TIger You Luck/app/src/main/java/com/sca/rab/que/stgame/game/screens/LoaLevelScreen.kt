package com.sca.rab.que.stgame.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.sca.rab.que.stgame.game.LibGDXGame
import com.sca.rab.que.stgame.game.actors.button.AButton
import com.sca.rab.que.stgame.game.actors.checkbox.ACheckBox
import com.sca.rab.que.stgame.game.utils.actor.animShow_Suspend
import com.sca.rab.que.stgame.game.utils.actor.setOnClickListener
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedStage
import com.sca.rab.que.stgame.game.utils.region
import com.sca.rab.que.stgame.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoaLevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var Level = LEVEL.EASY
    }

    private val menuBtn   = AButton(this, AButton.Static.Type.MENU).apply { color.a = 0f }
    private val musicCB   = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }
    private val levelImg  = Image(game.gameAssets.LOA_LEVEL).apply { color.a = 0f }
    private val suslikImg = Image(game.gameAssets.suslik).apply { color.a = 0f }

    override fun show() {
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMenuBtn()
                addMusicCB()
                addLevel()
                addSuslikImg()
                addBtns()
            }
            delay(470)
            menuBtn.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            musicCB.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            suslikImg.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            levelImg.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.setBounds(39f, 1717f, 156f, 162f)
        menuBtn.setOnClickListener { animHideScreen { game.navigationManager.back() } }
    }

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

    private fun AdvancedStage.addLevel() {
        addActor(levelImg)
        levelImg.setBounds(93f, 482f, 943f, 1158f)

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(865f, 1343f, 171f, 175f)
            setOnClickListener(game.soundUtil) { animHideScreen { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addBtns() {
        var ny = 1128f
        LEVEL.values().onEach { level ->
            val actor = Actor()
            addActor(actor)
            actor.setBounds(195f, ny, 714f, 120f)
            ny -= 87f + 120f
            actor.setOnClickListener(game.soundUtil) {
                Level = level
                animHideScreen { game.navigationManager.navigate(LoaPuzzleScreen::class.java.name, this@LoaLevelScreen::class.java.name) }
            }
        }
    }

    // ---------------------------------------------------
    // enum
    // ---------------------------------------------------

    enum class LEVEL(val text: String) {
        EASY("Easy"), NORMAL("Normal"), HARD("Hard")
    }

}