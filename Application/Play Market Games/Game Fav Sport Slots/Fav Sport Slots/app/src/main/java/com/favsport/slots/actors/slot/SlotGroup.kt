package com.favsport.slots.actors.slot

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
import com.favsport.slots.MUSIC_VOLUME
import com.favsport.slots.actors.BonusGroup
import com.favsport.slots.actors.MoneyBonus
import com.favsport.slots.actors.ScissorGroup
import com.favsport.slots.advanced.AdvancedGroup
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.utils.*
import com.favsport.slots.utils.slot.SLOT_ITEM_ALL_ID
import com.favsport.slots.utils.slot.SlotItem
import com.favsport.slots.utils.slot.SlotManager
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SlotGroup(viewport: Viewport) : AdvancedGroup() {

    companion object {
        private var WIN_NUMBER = Random.nextInt(1, 5)
        private var MINI_GAME_NUMBER = Random.nextInt(4, 8)
        private var SUPER_GAME_NUMBER = Random.nextInt(5, 10)

        const val DELAY_BETWEEN_SPIN = 1000L
    }

    private var winSpinCounter = 0
    private var bonusMiniGameSpinCounter = 0
    private var bonusSuperGameSpinCounter = 0

    private val bonusGroup = BonusGroup()
    private val bonusMoney = MoneyBonus()
    private val slotSeparator = Image(SpriteManager.GameSprite.SLOT_SEPARATOR.textureData.texture)
    private val slotList = List(4) { Slot() }
    private val glowList = List(3) { Image(SpriteManager.GameSprite.GLOW.textureData.texture) }
    private val winLineList = List(3) { Image(SpriteManager.GameSprite.LINE_WIN.textureData.texture) }
    private val scissorGroup = ScissorGroup(
        viewport,
        Rectangle(SCISSOR_X, getFigmaY(SCISSOR_Y, SCISSOR_H), SCISSOR_W, SCISSOR_H)
    )

    private val slotManager = SlotManager(*slotList.toTypedArray())
    private var bonus: Bonus? = null

    private val musicBonusSpin = MusicUtil.BONUS_SPIN.apply {
        volume = MUSIC_VOLUME
        isLooping = true
    }



    init {
        touchable = Touchable.disabled
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addSlotSeparator()
        addGlows()
        addSlots()
        addWinLines()
        addActor(bonusGroup)
        addActor(bonusMoney)
    }



    private fun addGlows() {
        var newY = GLOW_FIRST_Y
        glowList.onEach {
            it.addAction(Actions.alpha(0f))
            it.setBoundsFigmaY(GLOW_X, newY, GLOW_W, GLOW_H)
            newY += it.height + GLOW_SPACE_VERTICAL
            addActor(it)
        }
    }

    private fun addSlots() {
        var newX = SLOT_FIRST_X
        slotList.onEach {
            it.setBoundsFigmaY(newX, SLOT_START_Y, SLOT_W, SLOT_H)
            newX += it.width + SLOT_SPACE_HORIZONTAL
            scissorGroup.addActor(it)
        }
        addActor(scissorGroup.group)
    }

    private fun addSlotSeparator() {
        val image = slotSeparator.apply {
            setBoundsFigmaY(SLOT_SEPARATOR_X, SLOT_SEPARATOR_Y, SLOT_SEPARATOR_W, SLOT_SEPARATOR_H)
        }
        addActor(image)
    }

    private fun addWinLines() {
        var newY = WIN_LINE_FIRST_Y
        winLineList.onEach {
            it.setBoundsFigmaY(WIN_LINE_START_X, newY, WIN_LINE_W, WIN_LINE_H)
            newY += it.height + WIN_LINE_SPACE_VERTICAL
            scissorGroup.addActor(it)
        }
    }



    private suspend fun checkWin(): List<SlotItem> {
        val winList = mutableListOf<SlotItem>()

        slotList.first().listItemWin.onEachIndexed { indexSlotItem, slotItem ->
            val resultList = mutableListOf<Boolean>()
            (1..slotList.lastIndex).onEach { indexSlot ->
                val result = if (slotItem.id == SLOT_ITEM_ALL_ID) {
                    slotList.last().listItemWin[indexSlotItem].id == slotList[indexSlot].listItemWin[indexSlotItem].id
                } else {
                    slotItem.id == slotList[indexSlot].listItemWin[indexSlotItem].id ||
                            slotList[indexSlot].listItemWin[indexSlotItem].id == SLOT_ITEM_ALL_ID
                }
                resultList.add(result)
            }
            if (resultList.all { it }) {
                showWin(indexSlotItem)
                winList.add(slotItem)
            }
        }
        return winList
    }

    private suspend fun checkBonus() = CompletableDeferred<Bonus?>().also { continuation ->
        var price = 0
        when (bonus) {
            Bonus.MINI_GAME  -> {
                price = bonusGroup.showBonusMiniGame()
                continuation.complete(Bonus.MINI_GAME.apply { this.price = price })
            }
            Bonus.SUPER_GAME -> {
                price = bonusMoney.show()
                continuation.complete(Bonus.SUPER_GAME.apply { this.price = price })
            }
            else             -> {
                continuation.complete(null)
            }
        }
    }.await()



    private suspend fun showWin(row: Int) {
        slotSeparator.addAction(Actions.fadeOut(1f))
        glowList[row].addAction(Actions.fadeIn(1f))
        winLineList[row].addAction(Actions.moveTo(WIN_LINE_END_X, winLineList[row].y, 1f))
        delay(2000)
    }



    private fun resetWin() {
        winSpinCounter = 0
        WIN_NUMBER = Random.nextInt(1, 5)

        glowList.onEach { it.addAction(Actions.fadeOut(1f)) }
        winLineList.onEach { it.addAction(Actions.moveTo(WIN_LINE_START_X, it.y, 1f)) }
        slotSeparator.addAction(Actions.fadeIn(1f))
    }

    private fun resetBonus() {
        when (bonus) {
            Bonus.MINI_GAME -> {
                bonusMiniGameSpinCounter = 0
                MINI_GAME_NUMBER = Random.nextInt(10, 15)
            }
            Bonus.SUPER_GAME -> {
                bonusSuperGameSpinCounter = 0
                SUPER_GAME_NUMBER = Random.nextInt(20, 30)
            }

            else -> {}
        }
        bonus = null
    }



    suspend fun spin() = coroutineScope {
        winSpinCounter++
        bonusMiniGameSpinCounter++
        bonusSuperGameSpinCounter++

        log("""
            
            winSpinCounter = $winSpinCounter WIN_NUM = $WIN_NUMBER
            bonusMiniGameSpinCounter = $bonusMiniGameSpinCounter MINI_NUM = $MINI_GAME_NUMBER
            bonusSuperGameSpinCounter = $bonusSuperGameSpinCounter SUPER_NUM = $SUPER_GAME_NUMBER
            """)

        var isMatchNumber = false

        if (winSpinCounter == WIN_NUMBER) {
            slotManager.fill(SlotManager.Strategy.WIN)
            isMatchNumber = true
        }
        if (bonusSuperGameSpinCounter == SUPER_GAME_NUMBER) {
            slotManager.fill(SlotManager.Strategy.SUPER_GAME)
            bonus = Bonus.SUPER_GAME
            isMatchNumber = true
        }
        if (bonusMiniGameSpinCounter == MINI_GAME_NUMBER) {
            slotManager.fill(SlotManager.Strategy.MINI_GAME)
            bonus = Bonus.MINI_GAME
            isMatchNumber = true
        }
        if (!isMatchNumber) {
            slotManager.fill(SlotManager.Strategy.RANDOM)
        }

        if (bonus != null) bonusSpin() else someSpin()

        SpinResult(
            listSlotItem = checkWin().apply { if (isNotEmpty()) resetWin() },
            bonus = checkBonus()?.apply { resetBonus() }
        )
    }



    private suspend fun someSpin() = coroutineScope {
        slotList.onEach { slot ->
            launch { slot.spin() }
            delay(DELAY_BETWEEN_SPIN)
        }
    }

    private suspend fun bonusSpin() = coroutineScope {
        val secondSlotWithBonusIndex = slotManager.getSecondSlotIndexWithBonus()

        log("BONUS SLOT INDEX = $secondSlotWithBonusIndex")

        slotList.onEachIndexed { index, slot ->
            if (index < secondSlotWithBonusIndex) {
                launch { slot.spin() }
                delay(DELAY_BETWEEN_SPIN)
            }
            if (index == secondSlotWithBonusIndex) {
                launch {
                    slot.spin()
                    MusicUtil.currentMusic.pause()
                    musicBonusSpin.play()
                }
                delay(DELAY_BETWEEN_SPIN)
            }
            if (index > secondSlotWithBonusIndex) {
                launch { slot.slowSpin() }
                delay(DELAY_BETWEEN_SPIN)
            }
        }
        delay(Slot.DELAY_SLOW_SPIN - DELAY_BETWEEN_SPIN)
        musicBonusSpin.stop()
        MusicUtil.currentMusic.play()
    }



    data class SpinResult(
        val listSlotItem: List<SlotItem>,
        val bonus: Bonus?
    )



    enum class Bonus(var price: Int) {
        MINI_GAME(0), SUPER_GAME(0)
    }

}