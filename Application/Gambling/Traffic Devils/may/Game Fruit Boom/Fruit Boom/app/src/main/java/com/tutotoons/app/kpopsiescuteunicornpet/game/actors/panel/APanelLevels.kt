package com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButtonWithText
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.GameColor
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.font.FontParameter
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.toFruitRecordFormat

class APanelLevels(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "Level")
    private val font49    = screen.fontGenerator_MiltonianTattoo.generateFont(parameter.setSize(49))
    private val font50    = screen.fontGenerator_MiltonianTattoo.generateFont(parameter.setSize(50))

    private val labelStyle49 = LabelStyle(font49, Color.WHITE)

    // Actor
    val panelImg       = Image(screen.game.assetsAll.gamedofit)
    val backBtn        = AButton(screen, AButton.Static.Type.Back)
    val fruitPanel     = Image(screen.game.assetsAll.fruit_panel)
    val fruitRecordLbl = Label(screen.game.recordUtil.record.toFruitRecordFormat(), LabelStyle(font50, GameColor.golden))
    val btnList        = List(4) { AButtonWithText(screen, "Level ${it.inc()}", labelStyle49) }

    override fun addActorsOnGroup() {
        addPanel()
        addFruitPanel()
        addBack()
        addBtnList()
    }

    // Actors ------------------------------------------------------------------------

    private fun addPanel() {
        addActor(panelImg)
        panelImg.setBounds(222f, 0f, 1162f, 760f)
    }

    private fun addFruitPanel() {
        addActor(fruitPanel)
        fruitPanel.setBounds(477f, 740f, 651f, 206f)

        addActor(fruitRecordLbl)
        fruitRecordLbl.apply {
            setBounds(668f, 799f, 445f, 67f)
            setAlignment(Align.center)
        }
    }

    private fun addBack() {
        addActor(backBtn)
        backBtn.setBounds(1406f, 298f, 166f, 166f)
    }

    private fun addBtnList() {
        var ny = 606f
        btnList.onEach { btn ->
            addActor(btn)
            btn.setBounds(300f, ny, 1007f, 103f)
            ny -= 82 + 103
        }
    }

}