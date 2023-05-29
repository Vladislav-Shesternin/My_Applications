package com.playin.paganis.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.playin.paganis.game.manager.FontTTFManager
import com.playin.paganis.game.manager.NavigationManager
import com.playin.paganis.game.manager.SpriteManager
import com.playin.paganis.game.utils.actor.setOnClickListener
import com.playin.paganis.game.utils.advanced.AdvancedGroup
import com.playin.paganis.game.utils.advanced.AdvancedScreen

class SlotScreen: AdvancedScreen() {

    private val slot = ListScreen.globalSlot

    private val backImage = Image(SpriteManager.GameRegion.BACK.region)
    private val iconImage = Image(slot.iconRegion)
    private val slotImage = Image(slot.slotRegion)
    private val text      = Label(slot.text, Label.LabelStyle(FontTTFManager.InterFont.font_45.font, Color.WHITE))
    private val scroll    = ScrollPane(text)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BAKKKICH.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addIcon()
        addSlot()
        addScroll()
        addBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addIcon() {
        addActor(iconImage)
        iconImage.setBounds(277f, 1284f, 246f, 276f)
    }

    private fun AdvancedGroup.addSlot() {
        addActor(slotImage)
        slotImage.setBounds(20f, 832f, 760f, 410f)
    }

    private fun AdvancedGroup.addScroll() {
        addActor(scroll)
        scroll.setBounds(20f, 45f, 760f, 745f)

        text.wrap = true
    }

    private fun AdvancedGroup.addBack() {
        addActor(backImage)
        backImage.setBounds(70f, 1366f, 113f, 113f)
        backImage.setOnClickListener { NavigationManager.back() }
    }

}