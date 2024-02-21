package com.fortunetiger.bigwin.game.actors.slots.slot6x5

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fortunetiger.bigwin.game.actors.slots.SlotItem

class SlotItemContainer(private val itemRegions: List<TextureRegion>) {

    //val wild = SlotItem(itemRegions[26])
    val list = List(10) { SlotItem(itemRegions[it]) }

}