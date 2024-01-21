package fortunetiger.you.luck.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.you.luck.game.LibGDXGame
import fortunetiger.you.luck.game.actors.checkbox.ACheckBox
import fortunetiger.you.luck.game.actors.progress.AMusicSound
import fortunetiger.you.luck.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortunetiger.you.luck.game.utils.actor.animShow_Suspend
import fortunetiger.you.luck.game.utils.actor.setOnClickListener
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedStage
import fortunetiger.you.luck.game.utils.region
import fortunetiger.you.luck.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoaMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var isMusic = true
    }

    private val musicCB   = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }
    private val menuImg   = Image(game.gameAssets.LOA_MENU).apply { color.a = 0f }
    private val suslikImg = Image(game.gameAssets.suslik).apply { color.a = 0f }


    override fun show() {
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMusicCB()
                addMenu()
                addSuslikImg()
            }
            delay(470)
            musicCB.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            suslikImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(100)
            menuImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(891f, 1725f, 156f, 162f)
            if (isMusic.not()) check(false)

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

    private fun AdvancedStage.addSuslikImg() {
        addActor(suslikImg)
        suslikImg.setBounds(0f, -6f, 458f, 618f)
    }

    private fun AdvancedStage.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(93f, 482f, 943f, 1158f)

        val selections = listOf(
            LoaLevelScreen::class.java.name,
            LoaRulesScreen::class.java.name,
            LoaSettingsScreen::class.java.name,
        )

        var ny = 1120f
        selections.onEach { screenName ->
            addActor(Actor().apply {
                setBounds(189f, ny, 727f, 136f)
                ny -= 71f+136f
                setOnClickListener(game.soundUtil) { navigateGo(screenName) }
            })
        }

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(865f, 1317f, 171f, 175f)
            setOnClickListener(game.soundUtil) { game.navigationManager.back() }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        animHideScreen { game.navigationManager.navigate(id, this::class.java.name) }
    }


}