package fortunetiger.you.luck.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import fortunetiger.you.luck.game.LibGDXGame
import fortunetiger.you.luck.game.actors.AResultGroup
import fortunetiger.you.luck.game.actors.button.AButton
import fortunetiger.you.luck.game.actors.checkbox.ACheckBox
import fortunetiger.you.luck.game.actors.masks.Mask
import fortunetiger.you.luck.game.actors.progress.ATimer
import fortunetiger.you.luck.game.actors.puzzle.APuzzlePanel
import fortunetiger.you.luck.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortunetiger.you.luck.game.utils.actor.animShow
import fortunetiger.you.luck.game.utils.actor.animShow_Suspend
import fortunetiger.you.luck.game.utils.actor.enable
import fortunetiger.you.luck.game.utils.actor.setOnClickListener
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedStage
import fortunetiger.you.luck.game.utils.font.FontParameter
import fortunetiger.you.luck.game.utils.puzzle.Puzzles
import fortunetiger.you.luck.game.utils.region
import fortunetiger.you.luck.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoaPuzzleScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameter = FontParameter()
    private val font      = fontGeneratorLionKing.generateFont(parameter.setCharacters(LoaLevelScreen.Level.text).setSize(128))

    private val puzzleRegion = game.gameAssets.puzzleList.random().region

    private val menuBtn   = AButton(this, AButton.Static.Type.MENU).apply { color.a = 0f }
    private val pauseCB   = ACheckBox(this, ACheckBox.Static.Type.PAUSE).apply { color.a = 0f }
    private val panelImg  = Image(game.gameAssets.LOA_GAME).apply { color.a = 0f }
    private val timer     = ATimer(this).apply { color.a = 0f }
    private val puzzlesPanel = APuzzlePanel(this, puzzleRegion).apply { color.a = 0f }
    private val puzzleImg    = Image(puzzleRegion).apply { color.a = 0f }
    private val puzzleImgB   = Image(game.gameAssets.pikalka).apply { color.a = 0f }
    private val levelLabel   = Label(LoaLevelScreen.Level.text, Label.LabelStyle(font, Color.BLACK)).apply { color.a = 0f }
    private val resultGroup  = AResultGroup(this)

    override fun show() {
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMenuBtn()
                addPauseCB()
                addGame()
                addPuzzlePanel()
                addPuzzleImg()
                addLevelLbl()
                addTimer()
                addAndFillActor(resultGroup)
            }
            delay(470)
            menuBtn.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            pauseCB.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            panelImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            puzzlesPanel.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            puzzleImgB.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            puzzleImg.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            delay(50)
            levelLabel.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            timer.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)

            runGDX {
                timer.startTimer {
                    resultGroup.apply {
                        timer.isPause = true
                        enable()
                        game.soundUtil.apply { play(NEGATIVE) }
                        showResult(false)
                        animShow(TIME_ANIM_SCREEN_ALPHA)
                    }
                }
            }
        }
    }

    override fun dispose() {
        Puzzles.dispose()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.setBounds(39f, 1717f, 156f, 162f)
        menuBtn.setOnClickListener { animHideScreen { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addPauseCB() {
        addActor(pauseCB)
        pauseCB.apply {
            setBounds(891f, 1725f, 156f, 162f)

            setOnCheckListener { timer.isPause = it }
        }
    }

    private fun AdvancedStage.addGame() {
        addActor(panelImg)
        panelImg.setBounds(93f, 512f, 908f, 953f)
    }

    private fun AdvancedStage.addPuzzlePanel() {
        addActor(puzzlesPanel)
        puzzlesPanel.setBounds(198f, 644f, 684f, 684f)

        puzzlesPanel.finishBlock = {
            resultGroup.apply {
                enable()
                game.soundUtil.apply { play(POSITIVE) }
                showResult(true)
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }
    }

    private fun AdvancedStage.addPuzzleImg() {
        addActor(puzzleImgB)
        puzzleImgB.setBounds(381f, 57f, 383f, 386f)

        val maska = Mask(this@LoaPuzzleScreen, game.gameAssets.LILIK)
        addActor(maska)
        maska.setBounds(407f, 108f, 315f, 315f)

        maska.addAndFillActor(puzzleImg)
    }

    private fun AdvancedStage.addLevelLbl() {
        addActor(levelLabel)
        levelLabel.setBounds(246f, 1726f, 632f, 147f)
        levelLabel.setAlignment(Align.center)
    }

    private fun AdvancedStage.addTimer() {
        addActor(timer)
        timer.setBounds(212f, 1506f, 666f, 90f)
    }

}