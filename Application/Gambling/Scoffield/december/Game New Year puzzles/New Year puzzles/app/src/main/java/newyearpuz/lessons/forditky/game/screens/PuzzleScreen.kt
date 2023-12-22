package newyearpuz.lessons.forditky.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import newyearpuz.lessons.forditky.game.LibGDXGame
import newyearpuz.lessons.forditky.game.actors.Resultik
import newyearpuz.lessons.forditky.game.actors.button.AButton
import newyearpuz.lessons.forditky.game.actors.puzzle.Puzzlette
import newyearpuz.lessons.forditky.game.utils.TIME_ANIM_SCREEN_ALPHA
import newyearpuz.lessons.forditky.game.utils.actor.animHide
import newyearpuz.lessons.forditky.game.utils.actor.animShow
import newyearpuz.lessons.forditky.game.utils.actor.disable
import newyearpuz.lessons.forditky.game.utils.actor.enable
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedStage
import newyearpuz.lessons.forditky.game.utils.puzzle.Puzzles
import newyearpuz.lessons.forditky.game.utils.region

class PuzzleScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val puzzleRegion = game.gameAssets.puziles.random().region

    private val nextBtn    = AButton(this, AButton.Static.Type.next)
    private val panel      = Image(game.gameAssets.panel)

    private val puzzlesPanel = Puzzlette(this, puzzleRegion)
    private val puzzleImg    = Image(puzzleRegion)
    private val result       = Resultik(this).apply { color.a = 0f }


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.back.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        Puzzles.dispose()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addPuzzle()
        addNext()
        addResult()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addNext() {
        addActor(nextBtn)
        nextBtn.setBounds(390f, 75f, 299f, 168f)
        nextBtn.setOnClickListener { game.navigationManager.navigate(PuzzleScreen::class.java.name) }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panel)
        panel.setBounds(0f, 294f, 1080f, 1160f)
    }

    private fun AdvancedStage.addPuzzle() {
        addActor(puzzlesPanel)
        puzzlesPanel.setBounds(28f, 362f, 1024f, 1024f)

        puzzlesPanel.finishBlock = {
            result.apply {
                game.soundUtil.apply { play(win)  }
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)

                addAction(Actions.sequence(
                    Actions.delay(0.5f),
                    Actions.run { game.navigationManager.navigate(PuzzleScreen::class.java.name) }
                ))
            }
        }

        addActor(puzzleImg)
        puzzleImg.setBounds(333f, 1482f, 415f, 415f)
    }

    private fun AdvancedStage.addResult() {
        addAndFillActor(result)
        result.disable()
    }

}