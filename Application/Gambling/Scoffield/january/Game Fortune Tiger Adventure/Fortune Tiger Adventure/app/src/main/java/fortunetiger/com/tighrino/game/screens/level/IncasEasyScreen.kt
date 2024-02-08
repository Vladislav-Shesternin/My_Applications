package fortunetiger.com.tighrino.game.screens.level

import fortunetiger.com.tighrino.game.LibGDXGame
import fortunetiger.com.tighrino.game.actors.ATile
import fortunetiger.com.tighrino.game.screens.IncasResultScreen
import fortunetiger.com.tighrino.game.utils.actor.setOnClickListener
import fortunetiger.com.tighrino.game.utils.advanced.AdvancedGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IncasEasyScreen(_game: LibGDXGame): ILevelScreen(_game, Static.TypeScreen.EASY) {

    private val COUNT_PAIR = 6
    private val COLUMN = 3

    private var tile1: ATile? = null
    private var tile2: ATile? = null

    private var clickCounter = 0
    private var winCounter   = 0

    override fun AdvancedGroup.addActorsOnTmpGroup() {
        addTiles()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedGroup.addTiles() {
        val regions = game.allAssets.golds.shuffled().take(COUNT_PAIR)

        var n = 0
        val tiles = List<ATile>(COUNT_PAIR * 2) {
            if (it == COUNT_PAIR) n = COUNT_PAIR
            ATile(this@IncasEasyScreen,it-n, regions[it-n])
        }

        var nx = 306f
        var ny = 1116f

        tiles.onEachIndexed { index, tile ->
            addActor(tile)
            tile.setBounds(nx, ny, 100f, 100f)

            nx += 100f + 100f
            if (index.inc() % COLUMN == 0) {
                nx = 306f
                ny -= 108f + 100f
            }

            tile.setOnClickListener(game.soundUtil) {
                clickCounter++

                if (clickCounter <= 2) {
                    tile.animOpen()

                    when (clickCounter) {
                        1 -> tile1 = tile
                        2 -> {
                            coroutine?.launch {
                                delay(700)
                                tile2 = tile

                                if (tile1?.id == tile2?.id) {
                                    winCounter++
                                    if (winCounter == COUNT_PAIR) {
                                        IncasResultScreen.isWin = true
                                        game.navigationManager.navigate(IncasResultScreen::class.java.name)
                                    }
                                } else {
                                    tile1?.animClose()
                                    tile2?.animClose()
                                }

                                clickCounter = 0
                            }
                        }
                    }
                }

            }
        }
    }

}