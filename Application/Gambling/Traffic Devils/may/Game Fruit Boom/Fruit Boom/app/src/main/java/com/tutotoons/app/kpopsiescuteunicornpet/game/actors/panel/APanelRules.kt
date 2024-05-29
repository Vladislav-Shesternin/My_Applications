package com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen

class APanelRules(override val screen: AdvancedScreen): AdvancedGroup() {

    val rulesImg = Image(screen.game.assetsAll.raeleus)
    val backBtn  = AButton(screen, AButton.Static.Type.Back)

    override fun addActorsOnGroup() {
        addRules()
        addBack()
    }

    // Add Actors
    private fun addRules() {
        addActor(rulesImg)
        rulesImg.setBounds(45f, 56f, 1235f, 834f)
    }

    private fun addBack() {
        addActor(backBtn)
        backBtn.setBounds(1392f, 11f, 166f, 166f)
    }

}