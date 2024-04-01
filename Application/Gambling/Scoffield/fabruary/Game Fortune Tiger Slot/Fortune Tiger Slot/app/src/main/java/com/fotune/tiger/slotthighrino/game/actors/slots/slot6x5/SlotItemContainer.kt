package com.fotune.tiger.slotthighrino.game.actors.slots.slot6x5

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fotune.tiger.slotthighrino.game.actors.slots.SlotItem

class SlotItemContainer(private val itemRegions: List<TextureRegion>) {

    //val wild = SlotItem(itemRegions[26])
    val list = List(9) { SlotItem(itemRegions[it]) }

}