package nsl.radead.egyptlegacy.game.actors.slots

import com.badlogic.gdx.graphics.g2d.TextureRegion
import nsl.radead.egyptlegacy.game.actors.slots.slot5x3.SlotItemContainer

//data class SpinResult(
//    val winSlotItemSet: Set<SlotItem>?,
//)

data class SlotItem(
    val id       : Int,
    val region   : TextureRegion,
)

enum class FillStrategy {
    MIX, WIN
}