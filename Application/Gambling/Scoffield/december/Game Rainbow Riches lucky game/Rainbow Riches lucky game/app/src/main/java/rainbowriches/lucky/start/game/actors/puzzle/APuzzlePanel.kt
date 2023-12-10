package rainbowriches.lucky.start.game.actors.puzzle

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import rainbowriches.lucky.start.game.actors.panel.LEVEL
import rainbowriches.lucky.start.game.actors.panel.Level
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.puzzle.NumberPuzzles
import rainbowriches.lucky.start.game.utils.puzzle.Puzzles
import rainbowriches.lucky.start.game.utils.region
import rainbowriches.lucky.start.util.log

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

    private fun getNumberPuzzlesByLevel() = when(Level) {
        LEVEL.EASY -> NumberPuzzles._3x3
        LEVEL.NORMAL -> NumberPuzzles._4x4
        LEVEL.HARD -> NumberPuzzles._5x5
    }

}