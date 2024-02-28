package com.fortunetiger.bigwin.game.actors.slots.slot6x5

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import com.fortunetiger.bigwin.game.utils.actor.animHide
import com.fortunetiger.bigwin.game.utils.actor.animShow
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedGroup
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.toMS
import com.fortunetiger.bigwin.game.utils.Layout.Glow as LG

class Glow(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        const val GLOW_ITEM_COUNT = 5
    }

    private val glowItemImageList = List(GLOW_ITEM_COUNT) { Image(screen.game.allAssets.golowa) }

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
            screen.game.soundUtil.apply { play(coin_donatio) }
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