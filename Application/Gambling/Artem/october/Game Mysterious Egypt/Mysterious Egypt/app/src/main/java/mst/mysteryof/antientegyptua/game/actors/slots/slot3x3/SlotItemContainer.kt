package mst.mysteryof.antientegyptua.game.actors.slots.slot3x3

import com.badlogic.gdx.graphics.g2d.TextureRegion
import mst.mysteryof.antientegyptua.game.actors.slots.SlotItem

class SlotItemContainer(private val itemRegions: List<TextureRegion>) {
//    companion object {
//        const val SLOT_ITEM_WILD_ID = 14
//    }

    //val wild = SlotItem(SLOT_ITEM_WILD_ID, itemRegions[13])
    val list = List(14) { SlotItem(it.inc(), itemRegions[it]) }

}