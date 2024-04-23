package com.tdapps.test.game.actors.slots.slot4x3

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import com.tdapps.test.game.utils.actor.animHide
import com.tdapps.test.game.utils.actor.animShow
import com.tdapps.test.game.utils.advanced.AdvancedGroup
import com.tdapps.test.game.utils.advanced.AdvancedScreen
import com.tdapps.test.game.utils.toMS
import com.tdapps.test.game.utils.Layout.Glow as LG

class Glow(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val GLOW_ITEM_COUNT = 3
    }

    private val glowItemImageList = List(GLOW_ITEM_COUNT) { Image(screen.game.spriteUtil.WIN) }

    var listIndexWin: List<Int>? = null

    override fun addActorsOnGroup() {
        addGlowItemList()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addGlowItemList() {
        var newY = LG.glow.y
        glowItemImageList.reversed().onEach { image ->
            addActor(image)
            image.apply {
                animHide()
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

    suspend fun show(time: Float = 0f, timeBetween: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        listIndexWin?.onEach { winIndex ->
            glowItemImageList[winIndex].animShow(time)
            delay(timeBetween.toMS)
        }
        continuation.complete(true)
    }.await()

    suspend fun hide(time: Float = 0f, timeBetween: Float = 0f) = CompletableDeferred<Boolean>().also { continuation ->
        listIndexWin?.onEach { winIndex ->
            glowItemImageList[winIndex].animHide(time)
            delay(timeBetween.toMS)
        }
        listIndexWin = null
        continuation.complete(true)
    }.await()

}