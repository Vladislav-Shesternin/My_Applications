package aviator.original.fly.game.actors.puzzle

import aviator.original.fly.game.utils.advanced.AdvancedGroup
import aviator.original.fly.game.utils.advanced.AdvancedScreen
import aviator.original.fly.game.utils.puzzle.NumberPuzzles
import aviator.original.fly.game.utils.puzzle.Puzzles
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align

class APuzzlePanel(
    override val screen: AdvancedScreen,
    private val region: TextureRegion,
): AdvancedGroup() {

    private val numberPuzzles = NumberPuzzles.values().random()
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

}