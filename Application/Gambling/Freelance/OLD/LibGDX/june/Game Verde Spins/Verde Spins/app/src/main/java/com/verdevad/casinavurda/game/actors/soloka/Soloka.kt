package com.verdevad.casinavurda.game.actors.soloka

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.verdevad.casinavurda.game.actors.maska.Mask
import com.verdevad.casinavurda.game.manager.SpriteManager
import com.verdevad.casinavurda.game.utils.Completer
import com.verdevad.casinavurda.game.utils.advanced.AdvancedGroup
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Soloka : AdvancedGroup() {

    private val leftV  = AdvancedGroup()
    private val rightV = AdvancedGroup()
    private val leftSymbolList  = List(60) { Image(SpriteManager.ListRegion.ITEMS.regionList[(0..5).shuffled().first()]) }
    private val rightSymbolList = List(60) { Image(SpriteManager.ListRegion.ITEMS.regionList[(0..5).shuffled().first()]) }
    private val mask = Mask()


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(mask)
        addVerticales()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun addVerticales() {
        mask.addActors(leftV, rightV)
        leftV.setBounds(0f, 0f, 216f, 14905f)
        rightV.setBounds(257f, -14193f, 216f, 14905f)

        var leftY  = 0f
        var rightY = 0f

        leftSymbolList.onEach {
            leftV.addActor(it)
            it.setBounds(0f, leftY, 216f, 214f)
            leftY += 214f + 35f
        }
        rightSymbolList.onEach {
            rightV.addActor(it)
            it.setBounds(0f, rightY, 216f, 214f)
            rightY += 214f + 35f
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private suspend fun hideAnim() = suspendCoroutine<Unit> { continuation ->
        val completer = Completer(coroutine, 2) { continuation.resume(Unit) }
        leftV.addAction(Actions.sequence(
            Actions.moveTo(0f, 0f),
            Actions.run { completer.complete() }
        ))
        rightV.addAction(Actions.sequence(
            Actions.moveTo(257f, -14193f),
            Actions.run { completer.complete() }
        ))
    }

    private suspend fun showAnim() = suspendCoroutine<Unit> { continuation ->
        val completer = Completer(coroutine, 2) { continuation.resume(Unit) }
        leftV.addAction(Actions.sequence(
            Actions.moveTo(257f, -14193f, 3.5f),
            Actions.run { completer.complete() }
        ))
        rightV.addAction(Actions.sequence(
            Actions.moveTo(0f, 0f, 3.5f),
            Actions.run { completer.complete() }
        ))
    }

    private suspend fun startWin(): Boolean {
        hideAnim()

        val left  = listOf(0, 2, 4)
        val right = listOf(1, 3, 5)

        val shuffledRegionList = SpriteManager.ListRegion.ITEMS.regionList.shuffled()

        BarabanUtil.Win.values().toMutableList().shuffled().first().data.listRegionIndex.also { list ->
            leftSymbolList.takeLast(3).reversed().onEachIndexed { index, image ->
                image.drawable = TextureRegionDrawable(shuffledRegionList[list[left[index]]])
            }
            rightSymbolList.take(3).onEachIndexed { index, image ->
                image.drawable = TextureRegionDrawable(shuffledRegionList[list[right[index]]])
            }
        }

        showAnim()
        return true
    }

    private suspend fun startFail(): Boolean {
        hideAnim()

        val left  = listOf(0, 2, 4)
        val right = listOf(1, 3, 5)

        val shuffledRegionList = SpriteManager.ListRegion.ITEMS.regionList.shuffled()

        BarabanUtil.Fail.values().toMutableList().shuffled().first().data.listRegionIndex.also { list ->
            leftSymbolList.takeLast(3).reversed().onEachIndexed { index, image ->
                image.drawable = TextureRegionDrawable(shuffledRegionList[list[left[index]]])
            }
            rightSymbolList.take(3).onEachIndexed { index, image ->
                image.drawable = TextureRegionDrawable(shuffledRegionList[list[right[index]]])
            }
        }

        showAnim()
        return false
    }

    suspend fun start(): Boolean = if (Random().nextBoolean()) startWin() else startFail()


}