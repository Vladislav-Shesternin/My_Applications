package com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.GameColor
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.font.FontParameter
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.toFruitRecordFormat

class APanelMenu(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        var isBonus     = true
        var isSuperGame = true
        var isBigGame   = true
    }

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(83)
    private val font      = screen.fontGenerator_MiltonianTattoo.generateFont(parameter)

    val btnPanel       = Image(screen.game.assetsAll.knopi)
    val btnList        = List(3) { Actor() }
    val quitBtn        = AButton(screen, AButton.Static.Type.Quit)
    val fruitPanel     = Image(screen.game.assetsAll.fruit_panel)
    val bonusImg       = Image(screen.game.assetsAll.bonusic)
    val fruitRecordLbl = Label(screen.game.recordUtil.record.toFruitRecordFormat(), Label.LabelStyle(font, GameColor.golden))
    val superGameBtn   = AButton(screen, AButton.Static.Type.SUPER_GAME)
    val bigGameBtn     = AButton(screen, AButton.Static.Type.BIG_GAME)


    override fun addActorsOnGroup() {
        addButtonPanel()
        addQuit()
        addFruitPanel()
        if (isBonus) addBonus()
        if (isSuperGame) addSuperGame()
        if (isBigGame) addBigGame()
    }

    // Actors ------------------------------------------------------------------------
    private fun addButtonPanel() {
        addActor(btnPanel)
        btnPanel.setBounds(388f, 369f, 830f, 454f)

        var ny = 679f
        btnList.onEach {
            addActor(it.apply {
                setBounds(456f, ny, 693f, 121f)
                ny -= 32+121
            })
        }
    }

    private fun addQuit() {
        addActor(quitBtn)
        quitBtn.setBounds(1392f, 11f, 166f, 166f)
    }

    private fun addFruitPanel() {
        addActor(fruitPanel)
        fruitPanel.setBounds(264f, 9f, 965f, 305f)

        addActor(fruitRecordLbl)
        fruitRecordLbl.apply {
            setBounds(547f, 97f, 659f, 99f)
            setAlignment(Align.center)
        }
    }

    private fun addBonus() {
        addActor(bonusImg)
        bonusImg.setBounds(85f, 477f, 181f, 181f)
    }

    private fun addSuperGame() {
        addActor(superGameBtn)
        superGameBtn.setBounds(1288f, 515f, 257f, 140f)
    }

    private fun addBigGame() {
        addActor(bigGameBtn)
        bigGameBtn.setBounds(1288f, 682f, 257f, 140f)
    }


}