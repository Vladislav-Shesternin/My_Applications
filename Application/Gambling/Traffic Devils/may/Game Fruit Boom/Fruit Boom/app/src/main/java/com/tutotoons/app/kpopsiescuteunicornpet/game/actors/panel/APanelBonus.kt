package com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ALabel
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.Block
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.GameColor
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.animHide
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.animShow
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.disable
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.font.FontParameter
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.toFruitRecordFormat

class APanelBonus(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "+")
    private val font50    = screen.fontGenerator_MiltonianTattoo.generateFont(parameter.setSize(50))
    private val font165   = screen.fontGenerator_MiltonianTattoo.generateFont(parameter.setSize(165))

    val backBtn        = AButton(screen, AButton.Static.Type.Back)
    val fruitPanel     = Image(screen.game.assetsAll.fruit_panel)
    val fruitRecordLbl = Label(screen.game.recordUtil.record.toFruitRecordFormat(), Label.LabelStyle(font50, GameColor.golden))
    val bonusImg       = Image(screen.game.assetsAll.records)
    val goBtn          = AButton(screen, AButton.Static.Type.Go)
    val backCircleImg  = Image(screen.game.assetsAll.background_circle)
    val frontCircleImg = Image(screen.game.assetsAll.main_circle)
    val winBonusLbl    = ALabel(screen, "", Label.LabelStyle(font165, GameColor.golden)).apply { color.a = 0f }

    override fun addActorsOnGroup() {
        addBack()
        addFruitPanel()
        addRecords()
        addGo()
        addCircles()
        addWinBonus()
    }

    // Actors ------------------------------------------------------------------------

    private fun addBack() {
        addActor(backBtn)
        backBtn.setBounds(1392f, 11f, 166f, 166f)
    }

    private fun addFruitPanel() {
        addActor(fruitPanel)
        fruitPanel.setBounds(950f, 740f, 651f, 206f)

        addActor(fruitRecordLbl)
        fruitRecordLbl.apply {
            setBounds(1141f, 799f, 445f, 67f)
            setAlignment(Align.center)
        }
    }

    private fun addRecords() {
        addActor(bonusImg)
        bonusImg.setBounds(26f, 107f, 371f, 732f)
    }

    private fun addGo() {
        addActor(goBtn)
        goBtn.setBounds(1208f, 375f, 254f, 122f)
    }

    private fun addCircles() {
        addActors(backCircleImg, frontCircleImg)
        backCircleImg.setBounds(484f, 111f, 637f, 650f)
        frontCircleImg.apply {
            setBounds(527f, 153f, 551f, 543f)
            setOrigin(Align.center)
        }
    }

    private fun addWinBonus() {
        addActor(winBonusLbl)
        winBonusLbl.apply {
            disable()
            setBounds(483f, 374f, 639f, 197f)
            label.setAlignment(Align.center)
            setOrigin(Align.center)
        }
    }

    // Logic ------------------------------------------------------------------------

    fun goCircles(block: Block) {
        frontCircleImg.addAction(Actions.sequence(
            Actions.rotateBy(-(360f * 5), (10..30).random() / 10f, Interpolation.sine),
            Actions.run { block.invoke() }
        ))
    }

    fun animWin(block: Block) {
        winBonusLbl.animShow(TIME_ANIM_SCREEN_ALPHA) {
            winBonusLbl.addAction(Actions.forever(Actions.sequence(
                Actions.scaleBy(-0.4f, -0.4f, 0.25f, Interpolation.slowFast),
                Actions.scaleBy(0.4f, 0.4f, 0.25f, Interpolation.fastSlow),
            )))
        }
        winBonusLbl.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.run { block.invoke() }
        ))
    }

}