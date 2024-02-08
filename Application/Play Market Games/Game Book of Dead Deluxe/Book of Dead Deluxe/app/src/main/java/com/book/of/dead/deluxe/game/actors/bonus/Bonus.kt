package com.book.of.dead.deluxe.game.actors.bonus

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.book.of.dead.deluxe.game.manager.SpriteManager
import com.book.of.dead.deluxe.game.util.advanced.AdvancedGroup
import com.book.of.dead.deluxe.toMS
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.book.of.dead.deluxe.game.util.Layout.Bonus as LB

class Bonus: AdvancedGroup() {

    private val bonusList = List(6) { Image(SpriteManager.GameRegion.MINI_BONUS_WILD.region) }

    private var showedCount = 0
    val countFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    var isWinFlow = MutableStateFlow(false)



    init {
        coroutineMain.launch {
            countFlow.collect {
                if (it > 0) show(it)
            }
        }
    }

    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addBonusList()
    }

    private fun addBonusList() {
        var newY = LB.bonus.y

        bonusList.reversed().onEach { bonus ->
            addActor(bonus)
            bonus.apply {
                addAction(Actions.alpha(0f))
                with(LB.bonus) {
                    setBounds(x, newY, w, h)
                    newY += h + vs
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private suspend fun show(count: Int) {
        val _count = if (showedCount + count <= bonusList.size) count else bonusList.size - showedCount

        repeat(_count) {
            bonusList[showedCount].addAction(Actions.fadeIn(0.5f))
            delay(1f.toMS)
            showedCount++
        }

        if (showedCount == bonusList.size) isWinFlow.value = true

    }

    fun hideAll() {
        showedCount     = 0
        isWinFlow.value = false
        bonusList.onEach { it.addAction(Actions.fadeOut(1f)) }
    }

}