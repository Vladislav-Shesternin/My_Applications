package nsl.radead.egyptlegacy.game.actors.slots.slot5x3

import com.badlogic.gdx.graphics.g2d.TextureRegion
import nsl.radead.egyptlegacy.game.actors.slots.SlotItem
import nsl.radead.egyptlegacy.game.manager.SpriteManager

class SlotItemContainer(private val itemRegions: List<TextureRegion>) {
    companion object {
        const val SLOT_ITEM_WILD_ID = 14
    }

    val wild = SlotItem(SLOT_ITEM_WILD_ID, itemRegions[13])
    val list = List(13) { SlotItem(it.inc(), itemRegions[it]) }

}