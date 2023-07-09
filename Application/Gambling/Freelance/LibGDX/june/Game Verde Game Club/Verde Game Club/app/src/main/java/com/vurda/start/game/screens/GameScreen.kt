package com.vurda.start.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vurda.start.game.actors.ACard
import com.vurda.start.game.manager.NavigationManager
import com.vurda.start.game.manager.SpriteManager
import com.vurda.start.game.utils.*
import com.vurda.start.game.utils.actor.disable
import com.vurda.start.game.utils.actor.enable
import com.vurda.start.game.utils.actor.setBounds
import com.vurda.start.game.utils.actor.toClickable
import com.vurda.start.game.utils.advanced.AdvancedGroup
import com.vurda.start.game.utils.advanced.AdvancedScreen
import com.vurda.start.util.toMS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.vurda.start.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    // 24 cards
    private val cardList  = Deck().cards.shuffled()
    private val aCardList = List(cardList.size) { ACard(cardList[it]) }
    private val winLabel  = Image(SpriteManager.GameRegion.WIN.region)

    private var countWin = 0
    private var count    = 0
    private var card1: ACard? = null
    private var card2: ACard? = null


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
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
                    when (count) {
                        1 -> {
                            card1 = this.apply {
                                disable()
                                show()
                            }
                        }
                        2 -> {
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