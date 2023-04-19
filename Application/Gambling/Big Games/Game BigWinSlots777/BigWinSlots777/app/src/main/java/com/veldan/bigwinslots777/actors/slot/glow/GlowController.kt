package com.veldan.bigwinslots777.actors.slot.glow

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.bigwinslots777.actors.slot.slot.SlotController
import com.veldan.bigwinslots777.manager.assets.util.SoundUtil
import com.veldan.bigwinslots777.manager.assets.util.playAdvanced
import com.veldan.bigwinslots777.utils.controller.GroupController
import com.veldan.bigwinslots777.utils.log
import kotlinx.coroutines.CompletableDeferred

class GlowController(override val group: Glow) : GroupController {

    companion object {
        const val GLOW_ITEM_COUNT = SlotController.SLOT_ITEM_VISIBLE_COUNT
    }



    suspend fun glowIn(glowItemIndex: Int, time: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
           // SoundUtil.WIN_SLOT_ITEM.playAdvanced()
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