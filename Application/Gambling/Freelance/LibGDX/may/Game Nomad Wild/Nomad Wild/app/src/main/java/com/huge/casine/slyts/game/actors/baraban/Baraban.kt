package com.huge.casine.slyts.game.actors.baraban

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.huge.casine.slyts.game.manager.SpriteManager
import com.huge.casine.slyts.game.utils.actor.setBounds
import com.huge.casine.slyts.game.utils.advanced.AdvancedGroup
import com.huge.casine.slyts.util.Completer
import com.huge.casine.slyts.util.log
import java.util.Random
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.huge.casine.slyts.game.utils.Layout.Baraban as LB

class Baraban : AdvancedGroup() {

    companion object {
        val ITEM_COUNT = 9
    }

    private val firstSymbolsShuffled = SpriteManager.ListRegion.ITEMS.regionList.shuffled() + SpriteManager.ListRegion.ITEMS.regionList.shuffled().take(3)

    private val symbolList = List(ITEM_COUNT) { Symbol(firstSymbolsShuffled[it]) }
    private val topGroup   = AdvancedGroup()
    private val bottomGroup= AdvancedGroup()


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActors(topGroup, bottomGroup)
        addItems()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun addItems() {
        listOf(0, 1, 2, 5).onEach { topGroup.addActor(symbolList[it]) }
        listOf(3, 6, 7, 8).onEach { bottomGroup.addActor(symbolList[it]) }
        addActor(symbolList[4])

        var nx = LB.items.x
        var ny = LB.items.y

        symbolList.onEachIndexed { index, image ->
            image.setBounds(Vector2(nx, ny), LB.items.size())
            nx += LB.items.w + LB.items.hs

            if (index.inc() % 3 == 0) {
                nx = LB.items.x
                ny -= LB.items.vs + LB.items.h
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun hideAnim() = suspendCoroutine<Unit> { continuation ->
        val completer = Completer(coroutine, 2) { continuation.resume(Unit) }
        symbolList[4].addAction(Actions.fadeOut(0.4f))
        topGroup.addAction(Actions.sequence(
            Actions.parallel(
                Actions.moveTo(494f, 0f, 0.4f),
                Actions.fadeOut(0.4f)
            ),
            Actions.run { completer.complete() }
        ))
        bottomGroup.addAction(Actions.sequence(
            Actions.parallel(
                Actions.moveTo(-494f, 0f, 0.4f),
                Actions.fadeOut(0.4f)
            ),
            Actions.run { completer.complete() }
        ))
    }

    private suspend fun showAnim() = suspendCoroutine<Unit> { continuation ->
        val completer = Completer(coroutine, 2) { continuation.resume(Unit) }
        symbolList[4].addAction(Actions.fadeIn(0.4f))
        topGroup.addAction(Actions.sequence(
            Actions.parallel(
                Actions.moveTo(0f, 0f, 0.4f),
                Actions.fadeIn(0.4f)
            ),
            Actions.run { completer.complete() }
        ))
        bottomGroup.addAction(Actions.sequence(
            Actions.parallel(
                Actions.moveTo(0f, 0f, 0.4f),
                Actions.fadeIn(0.4f)
            ),
            Actions.run { completer.complete() }
        ))
    }

    private suspend fun startWin(): Boolean {
        hideAnim()

        val shuffledRegionList = SpriteManager.ListRegion.ITEMS.regionList.shuffled()
        BarabanUtil.Win.values().toMutableList().shuffled().first().data.listRegionIndex.also { data ->
            symbolList.onEachIndexed { index, symbol ->
                symbol.setSymbol(shuffledRegionList[data[index]])
            }
        }

        showAnim()
        return true
    }

    private suspend fun startFail(): Boolean {
        hideAnim()

        val shuffledRegionList = SpriteManager.ListRegion.ITEMS.regionList.shuffled()
        BarabanUtil.Fail.values().toMutableList().shuffled().first().data.listRegionIndex.also { data ->
            symbolList.onEachIndexed { index, symbol ->
                symbol.setSymbol(shuffledRegionList[data[index]])
            }
        }

        showAnim()
        return false
    }

    suspend fun start(): Boolean = if (Random().nextBoolean()) startWin() else startFail()


}