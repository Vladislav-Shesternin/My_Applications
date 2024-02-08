package com.book.of.dead.deluxe.game.actors.slot

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.book.of.dead.deluxe.game.manager.SpriteManager
import com.book.of.dead.deluxe.game.util.advanced.AdvancedGroup
import com.book.of.dead.deluxe.toMS
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import com.book.of.dead.deluxe.game.util.Layout.Glow as LG

class Glow: AdvancedGroup() {

    companion object {
        const val GLOW_ITEM_COUNT = 3
    }

    private val glowItemImageList = List(GLOW_ITEM_COUNT) { Image(SpriteManager.GameRegion.GLOW.region) }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addGlowItemList()
    }

    private fun addGlowItemList() {
        var newY = LG.glow.y
        glowItemImageList.reversed().onEach { image ->
            addActor(image)
            image.apply {
                addAction(Actions.alpha(0f))
                with(LG.glow) {
                    image.setBounds(x, newY, w, h)
                    newY += h + vs
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    suspend fun glowIn(glowItemIndex: Int, time: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        glowItemImageList[glowItemIndex].addAction(Actions.sequence(
            Actions.fadeIn(time),
            Actions.run { continuation.complete(true) }
        ))
    }.await()

    suspend fun glowOut(glowItemIndex: Int, time: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        glowItemImageList[glowItemIndex].addAction(Actions.sequence(
            Actions.fadeOut(time),
            Actions.run { continuation.complete(true) }
        ))
    }.await()

    suspend fun glowInAll(time: Float = 0f, timeBetween: Float = 0f) {
        val completableList = List(glowItemImageList.size) { CompletableDeferred<Boolean>() }

        glowItemImageList.onEachIndexed { index, glow ->
            glow.addAction(Actions.sequence(
                Actions.fadeIn(time),
                Actions.run { completableList[index].complete(true) }
            ))
            delay(timeBetween.toMS)
        }
        completableList.onEach { it.await() }
    }

    suspend fun glowOutAll(time: Float = 0f, timeBetween: Float = 0f) {
        val completableList = List(glowItemImageList.size) { CompletableDeferred<Boolean>() }

        glowItemImageList.onEachIndexed { index, glow ->
            glow.addAction(Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { completableList[index].complete(true) }
            ))
            delay(timeBetween.toMS)
        }

        completableList.onEach { it.await() }
    }
}