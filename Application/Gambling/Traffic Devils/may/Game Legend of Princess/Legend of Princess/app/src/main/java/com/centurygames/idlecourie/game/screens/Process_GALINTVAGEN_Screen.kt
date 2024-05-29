package com.centurygames.idlecourie.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecourie.game.HelloMotoGame
import com.centurygames.idlecourie.game.actors.ATile
import com.centurygames.idlecourie.game.actors.button.AButton
import com.centurygames.idlecourie.game.actors.checkbox.ACheckBox
import com.centurygames.idlecourie.game.actors.disable
import com.centurygames.idlecourie.game.utils.Timek
import com.centurygames.idlecourie.game.utils.actor.animHide
import com.centurygames.idlecourie.game.utils.actor.animShow
import com.centurygames.idlecourie.game.utils.actor.setBounds
import com.centurygames.idlecourie.game.utils.actor.setOnClickListener
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecourie.game.utils.advanced.AdvancedStage
import com.centurygames.idlecourie.game.utils.region
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Process_GALINTVAGEN_Screen(override val game: HelloMotoGame): AdvancedScreen() {

    private val winImg = Image(game.valhalla.win).apply { color.a = 0f }

    private val COUNT_PAIR = 8
    private val COLUMN = 4

    private var tile1: ATile? = null
    private var tile2: ATile? = null

    private var clickCounter = 0
    private var winCounter   = 0

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.gudomidron.farmer.region)
        super.show()
        stageUI.root.animShow(Timek)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        demoAddActrs()
        addPairs()

        addActor(winImg.apply {
            disable()
            setBounds(265f, 1278f, 551f, 199f)
        })

    }

    private fun AdvancedStage.demoAddActrs() {
        val back = AButton(this@Process_GALINTVAGEN_Screen, AButton.Static.Type.back)
        addActor(back)
        back.apply {
            setBounds(798f, 277f, 128f, 128f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(Image(game.valhalla.piricessssa).apply { setBounds(0f, 0f, 743f, 811f) })
        addActor(Image(game.valhalla.popap).apply { setBounds(14f, 870f, 1052f, 1017f) })

        val restart = AButton(this@Process_GALINTVAGEN_Screen, AButton.Static.Type.restat)
        addActor(restart)
        restart.apply {
            setBounds(700f, 486f, 325f, 325f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.navigate(Process_GALINTVAGEN_Screen::class.java.name)
                }
            }
        }
    }

    private fun AdvancedStage.addPairs() {
        val regions = game.valhalla.zamuhrushki.shuffled().take(COUNT_PAIR)

        var n = 0
        val tiles = List<ATile>(COUNT_PAIR * 2) {
            if (it == COUNT_PAIR) n = COUNT_PAIR
            ATile(this@Process_GALINTVAGEN_Screen,it-n, regions[it-n])
        }

        var nx = 81f
        var ny = 1630f

        tiles.onEachIndexed { index, tile ->
            addActor(tile)
            tile.setBounds(nx, ny, 207f, 207f)

            nx += 30f + 207f
            if (index.inc() % COLUMN == 0) {
                nx = 81f
                ny -= 30f + 207f
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
                                        winImg.animShow(Timek)
                                        game.soundUtil.apply { play(violin_win) }
                                        delay(1000)
                                        game.navigationManager.navigate(Process_GALINTVAGEN_Screen::class.java.name)
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