package com.veldan.pharaohslots.actors.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.pharaohslots.advanced.AdvancedGroup
import com.veldan.pharaohslots.manager.slot.FillSlot3Manager
import com.veldan.pharaohslots.utils.getFigmaY
import com.veldan.pharaohslots.utils.setPositionFigmaY
import com.veldan.pharaohslots.utils.toDelay
import kotlinx.coroutines.delay
import com.veldan.pharaohslots.utils.Slot3 as S3
import com.veldan.pharaohslots.utils.Slot3Animated as S3A

class Slot3Animated: AdvancedGroup() {

    companion object {
        // seconds
        const val TIME_GO = 1f

        val SLOT_TOP_Y     = getFigmaY(S3A.SLOT_TOP_Y    , S3.H, S3A.H)
        val SLOT_BOTTOM_Y  = getFigmaY(S3A.SLOT_BOTTOM_Y , S3.H, S3A.H)
        val SLOT_OUTSIDE_Y = getFigmaY(S3A.SLOT_OUTSIDE_Y, S3.H, S3A.H)
    }

    private var topSlot3    = Slot3()
    private var bottomSlot3 = Slot3().apply { setSlotItemList(FillSlot3Manager.slotItemList.shuffled().take(3)) }

    var slotItemList = listOf<SlotItem>()
        set(value) {
            field = value
            topSlot3.setSlotItemList(value)
        }



    init {
        setSize(S3A.W, S3A.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addTopSlot3()
        addBottomSlot3()
    }


    private fun addTopSlot3() {
        topSlot3.apply { setPositionFigmaY(S3A.SLOT_X, S3A.SLOT_TOP_Y, S3.H, S3A.H) }
        addActor(topSlot3)
    }

    private fun addBottomSlot3() {
        bottomSlot3.apply { setPositionFigmaY(S3A.SLOT_X, S3A.SLOT_BOTTOM_Y, S3.H, S3A.H) }
        addActor(bottomSlot3)
    }



    private fun reset() {
        with(topSlot3) {
            topSlot3 = bottomSlot3
            bottomSlot3 = this
        }
        topSlot3.y = SLOT_TOP_Y
    }



    suspend fun go() {
        Gdx.app.postRunnable {
            topSlot3.addAction(Actions.moveTo(S3A.SLOT_X, SLOT_BOTTOM_Y, TIME_GO))
            bottomSlot3.addAction(Actions.moveTo(S3A.SLOT_X, SLOT_OUTSIDE_Y, TIME_GO))
        }
        // 50мс - дополнительное время, что-бы анимация успела закончиться
        delay(TIME_GO.toDelay + 50)
        reset()
    }

}