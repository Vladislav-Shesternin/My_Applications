package com.polovinka.gurieva.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.polovinka.gurieva.game.manager.SpriteManager
import com.polovinka.gurieva.game.utils.advanced.AdvancedGroup

class ACard : AdvancedGroup() {

    private val cardImage    = Image(SpriteManager.ListRegion.COLORS.regionList.first())
    private val idImage      = Image(SpriteManager.ListRegion.IDS.regionList.first())
    private val wifiImage    = Image(SpriteManager.ListRegion.WIFI.regionList.first())
    private val surnameImage = Image(SpriteManager.ListRegion.SURNAME.regionList.first())
    private val nameImage    = Image(SpriteManager.ListRegion.NAMES.regionList.first())
    private val numeImage    = Image(SpriteManager.ListRegion.NUMES.regionList.first())


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(cardImage)
            addActirs()
        }
    }

    private fun addActirs() {
        addActors(idImage, wifiImage, surnameImage, nameImage, numeImage)

        idImage.apply {
            setBounds(48f, 179f, 71f, 71f)
        }
        wifiImage.apply {
            setBounds(57f, 46f, 55f, 55f)
        }
        surnameImage.apply {
            setBounds(535f, 24f, 101f, 44f)
        }
        nameImage.apply {
            setBounds(49f, 334f, 343f, 52f)
        }
        numeImage.apply {
            setBounds(166f, 189f, 517f, 75f)
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setColor(color: Drawable) {
        cardImage.drawable = color
    }

    fun setId(id: Drawable) {
        idImage.drawable = id
    }

    fun setWifi(wifi: Drawable) {
        wifiImage.drawable = wifi
    }

    fun setSurname(surname: Drawable) {
        surnameImage.drawable = surname
    }

    fun setName(name: Drawable) {
        nameImage.drawable = name
    }

    fun setNume(nume: Drawable) {
        numeImage.drawable = nume
    }


}