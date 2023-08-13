package com.guess.pair.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.guess.pair.game.actors.ACard
import com.guess.pair.game.actors.label.ALabelStyle
import com.guess.pair.game.manager.NavigationManager
import com.guess.pair.game.manager.SpriteManager
import com.guess.pair.game.utils.*
import com.guess.pair.game.utils.actor.disable
import com.guess.pair.game.utils.actor.enable
import com.guess.pair.game.utils.actor.setBounds
import com.guess.pair.game.utils.actor.toClickable
import com.guess.pair.game.utils.advanced.AdvancedGroup
import com.guess.pair.game.utils.advanced.AdvancedScreen
import com.guess.pair.util.toMS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.guess.pair.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    // 24 cards
    private val cardList  = Deck().cards.shuffled()
    private val aCardList = List(cardList.size) { ACard(cardList[it]) }
    private val winLabel  = Label("WIN", ALabelStyle.style(ALabelStyle.Regular._300, GameColor.green))

    private var countWin = 0
    private var count    = 0
    private var card1: ACard? = null
    private var card2: ACard? = null


    override fun show() {
        setUIBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addCards()
                addActor(winLabel)
                winLabel.apply {
                    disable()
                    setBounds(LG.win)
                    setAlignment(Align.center)
                    addAction(Actions.alpha(0f))
                }
            }
            startGame()
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addCards() {
        aCardList.onEach { card ->
            addActor(card)
            card.apply {
                disable()
                addAction(Actions.alpha(0f))
                setBounds(LG.start)

                toClickable().setOnClickListener {
                    count++
                    when {
                        count == 1 -> {
                            card1 = this.apply {
                                disable()
                                show()
                            }
                        }
                        count == 2 -> {
                            coroutine.launch {
                                runGDX {
                                    mainGroup.disable()
                                    card2 = this@apply.apply { show() }
                                }
                                delay(1f.toMS)
                                runGDX {
                                    if (card1?.card?.id == card2?.card?.id) {
                                        // WIN
                                        listOfNotNull(card1, card2).onEach {
                                            it.disable()
                                            it.addAction(Actions.fadeOut(MAIN_ANIM_SPEED))
                                        }
                                        if (++countWin == 12) {
                                            winLabel.addAction(Actions.sequence(
                                                Actions.fadeIn(MAIN_ANIM_SPEED),
                                                Actions.delay(1f),
                                                Actions.run { NavigationManager.back() }
                                            ))
                                        }
                                    } else {
                                        // FAIL
                                        listOfNotNull(card1, card2).onEach { it.hide() }
                                    }
                                    count = 0
                                    mainGroup.enable()
                                    card1?.enable()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun startGame() {
        aCardList.onEachIndexed { index, card ->
            runGDX {
                card.addAction(Actions.parallel(
                    Actions.moveTo(LG.listPos[index].x, LG.listPos[index].y, MAIN_ANIM_SPEED),
                    Actions.fadeIn(MAIN_ANIM_SPEED)
                ))
            }
            delay(50)
        }
        aCardList.onEach { card ->
            delay(100)
            runGDX { card.hide() }
        }
        aCardList.onEach { it.enable() }
    }

}