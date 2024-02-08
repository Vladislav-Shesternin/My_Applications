package com.tmesfo.frtunes.game.actors.slot.glow

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.tmesfo.frtunes.game.actors.slot.slot.SlotController
import com.tmesfo.frtunes.game.utils.controller.GroupController
import kotlinx.coroutines.CompletableDeferred
import com.tmesfo.frtunes.game.manager.assets.util.SoundUtil
import com.tmesfo.frtunes.game.manager.assets.util.playAdvanced

class GlowController(override val group: Glow) : GroupController {

    companion object {
        const val GLOW_ITEM_COUNT = SlotController.SLOT_ITEM_VISIBLE_COUNT
    }



    suspend fun glowIn(glowItemIndex: Int, time: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            SoundUtil.WIN.playAdvanced()
            group.glowImageList[glowItemIndex].addAction(Actions.sequence(
                    Actions.fadeIn(time),
                    Actions.run { continuation.complete(true) },
            ))
        }
    }.await()

    suspend fun glowOut(glowItemIndex: Int, time: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            group.glowImageList[glowItemIndex].addAction(Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { continuation.complete(true) },
            ))
        }
    }.await()

    suspend fun glowInAll(time: Float = 0f) {
        val completableList = List(group.glowImageList.size) { CompletableDeferred<Boolean>() }

        Gdx.app.postRunnable {
            group.glowImageList.onEachIndexed { index, glow ->
                glow.addAction(Actions.sequence(
                    Actions.fadeIn(time),
                    Actions.run { completableList[index].complete(true) }
                ))
            }
        }

        completableList.onEach { it.await() }
    }

    suspend fun glowOutAll(time: Float = 0f) {
        val completableList = List(group.glowImageList.size) { CompletableDeferred<Boolean>() }

        Gdx.app.postRunnable {
            group.glowImageList.onEachIndexed { index, glow ->
                glow.addAction(Actions.sequence(
                    Actions.fadeOut(time),
                    Actions.run { completableList[index].complete(true) }
                ))
            }
        }

        completableList.onEach { it.await() }
    }

}