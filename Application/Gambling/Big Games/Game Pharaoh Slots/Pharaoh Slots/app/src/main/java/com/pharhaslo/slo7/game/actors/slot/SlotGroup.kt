package com.pharhaslo.slo7.game.actors.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
import com.pharhaslo.slo7.game.actors.ScissorGroup
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.slot.SlotManager
import com.pharhaslo.slo7.game.utils.Game
import com.pharhaslo.slo7.game.utils.setBoundsFigmaY
import com.pharhaslo.slo7.game.utils.setPositionFigmaY
import com.pharhaslo.slo7.game.utils.toDelay
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import com.pharhaslo.slo7.game.utils.SlotGroup as SG
import com.pharhaslo.slo7.game.utils.Slot3Animated as S3A
import com.pharhaslo.slo7.game.utils.GlowSlot3 as GS3


class SlotGroup(viewport: Viewport) : AdvancedGroup() {

    companion object {
        const val SLOT_COUNT = 5

        // seconds
        const val TIME_BETWEEN_GLOW = 0.7f
        const val TIME_WAIT_AFTER_BLOW_IN = 1.5f
    }

    private val slotList     = List(SLOT_COUNT) { Slot3Animated() }
    private val glowList     = List(SLOT_COUNT) { GlowSlot3()     }
    private val scissorGroup = ScissorGroup(viewport)

    private val slotManager = SlotManager(slotList)





    init {
        setSize(SG.W, SG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addPanel()
        addGlowSlot3List()
        addScissorGroup()
        addSlot3AnimatedList()
        addSeparator()
    }

    private fun addPanel() {
        val image = Image(SpriteManager.GameSprite.SLOT_GROUP_PANEL.data.texture)
        addAndFillActor(image)
    }

    private fun addGlowSlot3List() {
        glowList.onEachIndexed { index, glow ->
            val newX = SG.GLOW_FIRST_X + (GS3.W + SG.GLOW_SPACE_HORIZONTAL) * index
            glow.setPositionFigmaY(newX, SG.GLOW_Y, GS3.H, SG.H)
            addActor(glow)
        }
    }

    private fun addScissorGroup() {
        scissorGroup.apply {
            setBoundsFigmaY(SG.SCISSOR_X, SG.SCISSOR_Y, SG.SCISSOR_W, SG.SCISSOR_H, SG.H)
            setBoundsScissor(Game.SCISSOR_X, Game.SCISSOR_Y, Game.SCISSOR_W, Game.SCISSOR_H)
        }
        addActor(scissorGroup)
    }

    private fun addSlot3AnimatedList() {
        slotList.onEachIndexed { index, slot ->
            val newX = SG.SLOT_FIRST_X + (S3A.W + SG.SLOT_SPACE_HORIZONTAL) * index
            slot.setPositionFigmaY(newX, SG.SLOT_Y, S3A.H, SG.SCISSOR_H)
            scissorGroup.addActor(slot)
        }
    }

    private fun addSeparator() {
        val image = Image(SpriteManager.GameSprite.SEPARATOR.data.texture).apply {
            setBoundsFigmaY(SG.SEPARATOR_X, SG.SEPARATOR_Y, SG.SEPARATOR_W, SG.SEPARATOR_H, SG.SCISSOR_H)
        }
        scissorGroup.addActor(image)
    }



    private suspend fun List<FillResult>.showWin() {
        // Включаем свечение Glow слотов по комбинациям
        onEach { fillResult ->
            with(fillResult) {
                // Если 1 слот (100% множество рядов) | VERTICAL
                if (combination.slotIndexList.size == 1) {
                    val glowIndex = combination.slotIndexList.first()
                    // Последовательное свечение всех Glow (свечений) в одном Glow слоте
                    repeat(3) {
                        glowList[glowIndex].blowIn(it)
                        delay(TIME_BETWEEN_GLOW.toDelay)
                    }
                }
                // Если множество слотов (50% 1 ряд | 50% множество рядов)
                else {
                    // Если 1 ряд | HORIZONTAL
                    if (combination.rowIndexList.size == 1) {
                        val rowIndex = combination.rowIndexList.first()
                        // Последовательное свечение Glow (свечений) в разных Glow слотах и одном ряде
                        combination.slotIndexList.onEach { slotIndex ->
                            glowList[slotIndex].blowIn(rowIndex)
                            delay(TIME_BETWEEN_GLOW.toDelay)
                        }
                    }
                    // Если множество рядов | DIAGONAL_UP, DIAGONAL_DOWN
                    else {
                        combination.slotIndexList.onEachIndexed { index, slotIndex ->
                            glowList[slotIndex].blowIn(combination.rowIndexList[index])
                            delay(TIME_BETWEEN_GLOW.toDelay)
                        }
                    }
                }
            }
        }

        delay(TIME_WAIT_AFTER_BLOW_IN.toDelay)
        glowList.onEach { it.blowOutAll() }
    }



    suspend fun spin() = coroutineScope<GoResult> {
        slotManager.spin().apply {
            fillResultList?.showWin()
        }
    }



}