package aviator.original.fly.game.screens

import aviator.original.fly.game.LibGDXGame
import aviator.original.fly.game.actors.AResultGroup
import aviator.original.fly.game.actors.ATimer
import aviator.original.fly.game.actors.button.AButton
import aviator.original.fly.game.actors.checkbox.ACheckBox
import aviator.original.fly.game.actors.puzzle.APuzzlePanel
import aviator.original.fly.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.fly.game.utils.actor.animHide
import aviator.original.fly.game.utils.actor.animShow
import aviator.original.fly.game.utils.actor.disable
import aviator.original.fly.game.utils.actor.enable
import aviator.original.fly.game.utils.actor.setOnClickListener
import aviator.original.fly.game.utils.advanced.AdvancedScreen
import aviator.original.fly.game.utils.advanced.AdvancedStage
import aviator.original.fly.game.utils.puzzle.Puzzles
import aviator.original.fly.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class AviatorPlaygraundScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val puzzleRegion = game.gameAssets.puzzleList[AVIA_INDEX].region

    private val pauseCB   = ACheckBox(this, ACheckBox.Static.Type.PAUSE)
    private val menuBtn   = AButton(this, AButton.Static.Type.MENU)
    private val panella   = Image(game.gameAssets.panella)
    private val exitBtn   = Image(game.gameAssets.exit)
    private val puzzleImg = Image(puzzleRegion)
    private val timer     = ATimer(this)
    private val puzzleA   = APuzzlePanel(this, puzzleRegion)
    private val result    = AResultGroup(this).apply { color.a = 0f }

    override fun show() {
        setUIBackground(game.splashAssets.AviatorLoading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPauseCB()
        addMenuBtn()
        addExitBtn()
        addPanella()
        addPuzzleImg()
        addTimer()
        addPuzzlesPanel()
        addResult()
    }

    override fun dispose() {
        Puzzles.dispose()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPauseCB() {
        addActor(pauseCB)
        pauseCB.apply {
            setBounds(505f, 1211f, 76f, 75f)

            setOnCheckListener { isCheck -> timer.isPause = isCheck }
        }
    }

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(19f, 1211f, 76f, 75f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addExitBtn() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(182f, 78f, 236f, 90f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addPanella() {
        addActor(panella)
        panella.setBounds(17f, 435f, 568f, 601f)
    }

    private fun AdvancedStage.addPuzzleImg() {
        addActor(puzzleImg)
        puzzleImg.setBounds(201f, 209f, 214f, 198f)
    }

    private fun AdvancedStage.addTimer() {
        addActor(timer)
        timer.setBounds(171f, 1096f, 239f, 92f)

        timer.startTimer {
            result.apply {
                game.soundUtil.apply { play(NEGATIVE) }
                update(false)
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }
    }

    private fun AdvancedStage.addPuzzlesPanel() {
        addActor(puzzleA)
        puzzleA.apply { setBounds(41f, 477f, 519f, 519f) }

        puzzleA.finishBlock = {
            result.apply {
                game.soundUtil.apply { play(POSITIVE) }
                update(true)
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }
    }

    private fun AdvancedStage.addResult() {
        addAndFillActor(result)
        result.disable()
    }


}