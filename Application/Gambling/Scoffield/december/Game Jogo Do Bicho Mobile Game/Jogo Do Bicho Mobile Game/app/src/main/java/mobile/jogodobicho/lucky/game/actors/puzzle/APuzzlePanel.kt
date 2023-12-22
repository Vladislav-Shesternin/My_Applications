package mobile.jogodobicho.lucky.game.actors.puzzle

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import mobile.jogodobicho.lucky.game.screens.BullChooseLevelScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.puzzle.NumberPuzzles
import mobile.jogodobicho.lucky.game.utils.puzzle.Puzzles
import mobile.jogodobicho.lucky.util.log

class APuzzlePanel(
    override val screen: AdvancedScreen,
    private val region: TextureRegion,
): AdvancedGroup() {

    private val numberPuzzles = getNumberPuzzlesByLevel()
    private val puzzles       = Puzzles.generatePuzzles(screen, region, numberPuzzles)

    var finishBlock: () -> Unit = { }


    override fun addActorsOnGroup() {
        val puzzleSize = width / numberPuzzles.horizontalNumberPuzzles

        var nx = 0f
        var ny = height-puzzleSize

        puzzles.onEachIndexed { index, puzzle ->
            puzzle.apply {
                setBounds(nx, ny, puzzleSize, puzzleSize)
                nx += puzzleSize
                if (index.inc() % numberPuzzles.horizontalNumberPuzzles == 0) {
                    nx = 0f
                    ny -= puzzleSize
                }
                setOrigin(Align.center)
                rotation = listOf(90f, 180f, 270f, 360f).random()
            }

            addActor(puzzle)
            Puzzles.add(puzzle)
        }

        Puzzles.doAfterAssembledPuzzles = {
            finishBlock()
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getNumberPuzzlesByLevel() = when (BullChooseLevelScreen.LEVEL_STATIC) {
        BullChooseLevelScreen.LEVEL.EASY   -> NumberPuzzles._3x3
        BullChooseLevelScreen.LEVEL.MEDIUM -> NumberPuzzles._4x4
        BullChooseLevelScreen.LEVEL.HARD   -> NumberPuzzles._5x5
    }

}