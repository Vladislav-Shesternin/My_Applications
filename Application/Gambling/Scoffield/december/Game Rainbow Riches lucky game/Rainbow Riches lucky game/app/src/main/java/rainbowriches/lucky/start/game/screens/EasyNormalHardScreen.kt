package rainbowriches.lucky.start.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.LibGDXGame
import rainbowriches.lucky.start.game.actors.AResultGroup
import rainbowriches.lucky.start.game.actors.button.AButton
import rainbowriches.lucky.start.game.actors.checkbox.ACheckBox
import rainbowriches.lucky.start.game.actors.isWINNER
import rainbowriches.lucky.start.game.actors.panel.LEVEL
import rainbowriches.lucky.start.game.actors.panel.Level
import rainbowriches.lucky.start.game.actors.progress.ATimer
import rainbowriches.lucky.start.game.actors.puzzle.APuzzlePanel
import rainbowriches.lucky.start.game.utils.TIME_ANIM_SCREEN_ALPHA
import rainbowriches.lucky.start.game.utils.actor.animShow
import rainbowriches.lucky.start.game.utils.actor.animShow_Suspend
import rainbowriches.lucky.start.game.utils.actor.disable
import rainbowriches.lucky.start.game.utils.actor.enable
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.advanced.AdvancedStage
import rainbowriches.lucky.start.game.utils.region
import rainbowriches.lucky.start.game.utils.runGDX
import rainbowriches.lucky.start.util.log

class EasyNormalHardScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val titleLis = listOf(
        game.gameAssets.L_EASY,
        game.gameAssets.L_NORMAL,
        game.gameAssets.L_HARD,
    )
    private val puzzleRegion = game.gameAssets.puzzleList.random().region

    private val menuBtn      = AButton(this, AButton.Static.Type.MENU).apply { color.a = 0f }
    private val pauseCB      = ACheckBox(this, ACheckBox.Static.Type.PAUSE).apply { color.a = 0f }
    private val titleImg     = Image(titleLis[getTitleByLevel()]).apply { color.a = 0f }
    private val timer        = ATimer(this).apply { color.a = 0f }
    private val panelImg     = Image(game.gameAssets.PANEL_PLAY_GAME).apply { color.a = 0f }
    private val puzzlesPanel = APuzzlePanel(this, puzzleRegion)
    private val puzzleImg    = Image(puzzleRegion).apply { color.a = 0f }
    private val result       = AResultGroup(this).apply { color.a = 0f }

    override fun show() {
        setBackgrounds(game.gameAssets.PLAYGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMenuBtn()
                addPauseCB()
                addTitleImg()
                addTimer()
                addPanelImg()
                addPuzzlesPanel()
                addPuzzleImg()
                addResult()
            }
            delay(500)
            val time2 = 0.3f
            menuBtn.animShow_Suspend(time2)
            pauseCB.animShow_Suspend(time2)
            titleImg.animShow_Suspend(time2)
            timer.animShow_Suspend(time2)
            panelImg.animShow_Suspend(time2)
            puzzlesPanel.animShow_Suspend(time2)
            puzzleImg.animShow_Suspend(time2)
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

    private fun AdvancedStage.addPauseCB() {
        addActor(pauseCB)
        pauseCB.apply {
            setBounds(828f, 1684f, 182f, 193f)
            setOnCheckListener { timer.isPause = it }
        }
    }

    private fun AdvancedStage.addTitleImg() {
        addActor(titleImg)
        titleImg.apply { setBounds(368f, 1696f, 344f, 140f) }
    }

    private fun AdvancedStage.addTimer() {
        addActor(timer)
        timer.apply {
            setBounds(277f, 1489f, 568f, 77f)
            startTimer {
                result.apply {
                    isWINNER = false
                    game.soundUtil.apply { play(NEGATIVE) }
                    lila()
                    enable()
                    animShow(TIME_ANIM_SCREEN_ALPHA)
                }
            }
        }
    }

    private fun AdvancedStage.addPanelImg() {
        addActor(panelImg)
        panelImg.apply { setBounds(72f, 486f, 939f, 946f) }
    }

    private fun AdvancedStage.addPuzzlesPanel() {
        addActor(puzzlesPanel)
        puzzlesPanel.apply { setBounds(156f, 574f, 770f, 770f) }

        puzzlesPanel.finishBlock = {
            result.apply {
                isWINNER = true
                game.soundUtil.apply { play(POSITIVE) }
                lila()
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }
    }

    private fun AdvancedStage.addPuzzleImg() {
        addActor(puzzleImg)
        puzzleImg.apply { setBounds(355f, 72f, 370f, 370f) }
    }

    private fun AdvancedStage.addResult() {
        addAndFillActor(result)
        result.apply {
            disable()
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getTitleByLevel() = when(Level) {
        LEVEL.EASY -> 0
        LEVEL.NORMAL -> 1
        LEVEL.HARD -> 2
    }

//    private fun navigateGo(id: String) {
//        animHideScreen { game.navigationManager.navigate(id, this::class.java.name) }
//    }

}