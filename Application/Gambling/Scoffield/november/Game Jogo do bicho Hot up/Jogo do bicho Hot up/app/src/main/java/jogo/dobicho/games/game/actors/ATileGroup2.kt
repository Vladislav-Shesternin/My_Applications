package jogo.dobicho.games.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.onesignal.OneSignal
import jogo.dobicho.games.game.actors.checkbox.ACheckBox
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import jogo.dobicho.games.game.actors.masks.Mask
import jogo.dobicho.games.game.actors.progress.ADerevoProgress
import jogo.dobicho.games.game.utils.actor.setOnClickListener
import jogo.dobicho.games.game.utils.advanced.AdvancedGroup
import jogo.dobicho.games.game.utils.advanced.AdvancedScreen
import jogo.dobicho.games.game.utils.runGDX
import jogo.dobicho.games.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class ATileGroup2(override val screen: AdvancedScreen): AdvancedGroup(), AbsTile {

    private val COUNT  = 10
    private val COLUMN = 5

    private var counter = 0
    private var tile1: ATileImage? = null
    private var tile2: ATileImage? = null

    private var winCounter = 0

    override var winBlock: () -> Unit = {}
    override var pairBlock: () -> Unit = {}

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.GAME_PANEL))
        addTiles()
    }

    private fun addTiles() {
        val regions = screen.game.gameAssets.ANIMALS.shuffled().take(COUNT)

        var i = 0
        val tiles = List(COUNT+COUNT) {
            if (it==COUNT) i = COUNT
            ATileGroup.Obj.Tile(it-i, regions[it-i])
        }

        var nx = 99f
        var ny = 582f

        tiles.onEachIndexed { index, tile ->
            ATileImage(screen, tile).also { tileImg ->
                addActor(tileImg)
                tileImg.setBounds(nx, ny, 122f, 124f)
                nx += (122+43f)
                if (index.inc()%COLUMN==0) {
                    nx = 99f
                    ny -= (23f+124f)
                }

                tileImg.setOnClickListener(screen.game.soundUtil) {
                    if (counter < 2) {
                        counter++
                        tileImg.animShowTile()

                        when(counter) {
                            1 -> tile1 = tileImg
                            2 -> {
                                coroutine?.launch {
                                    delay(1000)
                                    tile2 = tileImg

                                    if (tile1?.tile?.id == tile2?.tile?.id) {
                                        winCounter++
                                        pairBlock()
                                        screen.game.soundUtil.apply { play(PARA) }
                                        if (winCounter == COUNT) winBlock()
                                    } else {
                                        tile1?.animDefault()
                                        tile2?.animDefault()
                                        screen.game.soundUtil.apply { play(NEPARA) }
                                    }

                                    counter = 0
                                }
                            }
                        }

                    }
                }
            }
        }

    }

}