package com.plugoya.rosgpb.game.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.plugoya.rosgpb.game.actors.scroll.HorizontalGroup
import com.plugoya.rosgpb.game.actors.scroll.VerticalGroup
import com.plugoya.rosgpb.game.manager.SpriteManager
import com.plugoya.rosgpb.game.soundUtil
import com.plugoya.rosgpb.game.utils.advanced.AdvancedGroup

class ACardSettingsScroll : AdvancedGroup() {

    private val linesGroup    = AdvancedGroup()
    private val linesImage    = Image(SpriteManager.GameRegion.LINES.region)
    private val verticalGroup = VerticalGroup()
    private val scrollPane    = ScrollPane(verticalGroup)

    private val colorHorizontalGroup    = HorizontalGroup(30f)
    private val colorScrollPane         = ScrollPane(colorHorizontalGroup)
    private val idHorizontalGroup       = HorizontalGroup(92f)
    private val idScrollPane            = ScrollPane(idHorizontalGroup)
    private val wifiHorizontalGroup     = HorizontalGroup(86f)
    private val wifiScrollPane          = ScrollPane(wifiHorizontalGroup)
    private val surnameHorizontalGroup  = HorizontalGroup(57f)
    private val surnameScrollPane       = ScrollPane(surnameHorizontalGroup)
    private val nameHorizontalGroup     = HorizontalGroup(28f)
    private val nameScrollPane          = ScrollPane(nameHorizontalGroup)
    private val numeHorizontalGroup     = HorizontalGroup(28f)
    private val numeScrollPane          = ScrollPane(numeHorizontalGroup)


    var colorBlock   : (Drawable) -> Unit = {}
    var idBlock      : (Drawable) -> Unit = {}
    var wifiBlock    : (Drawable) -> Unit = {}
    var surnameBlock : (Drawable) -> Unit = {}
    var nameBlock    : (Drawable) -> Unit = {}
    var numeBlock    : (Drawable) -> Unit = {}
    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActirs()
        }
    }

    private fun addActirs() {
        addScroll()

        linesGroup.apply {
            addColor()
            addId()
            addWifi()
            addSurname()
            addName()
            addNume()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun addScroll() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 0f, 683f, 661f)

        verticalGroup.addActor(linesGroup.apply { setSize(683f, 989f) })
        linesGroup.addAndFillActor(linesImage)
    }

    private fun AdvancedGroup.addColor() {
        addActor(colorScrollPane)
        colorScrollPane.setBounds(0f, 858f, 683f, 93f)

        SpriteManager.ListRegion.COLORS.regionList.onEach { region ->
            colorHorizontalGroup.addActor(Image(region).apply {
                setSize(133f, 93f)
                addListener(clickListener { colorBlock(drawable) })
            })
        }
    }

    private fun AdvancedGroup.addId() {
        addActor(idScrollPane)
        idScrollPane.setBounds(0f, 699f, 683f, 71f)

        SpriteManager.ListRegion.IDS.regionList.onEach { region ->
            idHorizontalGroup.addActor(Image(region).apply {
                setSize(71f, 71f)
                addListener(clickListener { idBlock(drawable) })
            })
        }
    }

    private fun AdvancedGroup.addWifi() {
        addActor(wifiScrollPane)
        wifiScrollPane.setBounds(0f, 523f, 683f, 77f)

        SpriteManager.ListRegion.WIFI.regionList.onEach { region ->
            wifiHorizontalGroup.addActor(Image(region).apply {
                setSize(77f, 77f)
                addListener(clickListener { wifiBlock(drawable) })
            })
        }
    }

    private fun AdvancedGroup.addSurname() {
        addActor(surnameScrollPane)
        surnameScrollPane.setBounds(0f, 381f, 683f, 46f)

        SpriteManager.ListRegion.SURNAME.regionList.onEach { region ->
            surnameHorizontalGroup.addActor(Image(region).apply {
                setSize(106f, 46f)
                addListener(clickListener { surnameBlock(drawable) })
            })
        }
    }

    private fun AdvancedGroup.addName() {
        addActor(nameScrollPane)
        nameScrollPane.setBounds(0f, 215f, 683f, 52f)

        SpriteManager.ListRegion.NAMES.regionList.onEach { region ->
            nameHorizontalGroup.addActor(Image(region).apply {
                setSize(343f, 52f)
                addListener(clickListener { nameBlock(drawable) })
            })
        }
    }

    private fun AdvancedGroup.addNume() {
        addActor(numeScrollPane)
        numeScrollPane.setBounds(0f, 47f, 683f, 75f)

        SpriteManager.ListRegion.NUMES.regionList.onEach { region ->
            numeHorizontalGroup.addActor(Image(region).apply {
                setSize(517f, 75f)
                addListener(clickListener { numeBlock(drawable) })
            })
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun clickListener(block: () -> Unit) = object : ClickListener() {
        override fun clicked(event: InputEvent?, x: Float, y: Float) {
            soundUtil.apply { play(CLICK) }
            block()
        }
    }

}