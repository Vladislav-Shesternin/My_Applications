package newyearpuz.lessons.forditky.game.actors.puzzle

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import newyearpuz.lessons.forditky.game.screens.PLevel
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedGroup
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.puzzle.NumberPuzzles
import newyearpuz.lessons.forditky.game.utils.puzzle.Puzzles

class Puzzlette(
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

    private fun getNumberPuzzlesByLevel() = when (PLevel) {
        3 -> NumberPuzzles._3x3
        4 -> NumberPuzzles._4x4
        5 -> NumberPuzzles._5x5
        else -> NumberPuzzles._3x3
    }

}