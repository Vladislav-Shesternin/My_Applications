package com.roshevasternin.rozval.game.actors.mainPanel

import com.roshevasternin.rozval.game.actors.button.AButton
import com.roshevasternin.rozval.game.utils.advanced.AdvancedGroup
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen

class GameMainPanel(_screen: AdvancedScreen): AbstractMainPanel(_screen) {

    override val isBackground = true

    private val menuBtn = AButton(screen, AButton.Static.Type.Menu)

    var menuBtnBlock = {}

    override fun AdvancedGroup.addActorsOnParentGroup() {
        addMenu()
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedGroup.addMenu() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(18f, 35f, 124f, 51f)
            setOnClickListener(menuBtnBlock)
        }
    }

}