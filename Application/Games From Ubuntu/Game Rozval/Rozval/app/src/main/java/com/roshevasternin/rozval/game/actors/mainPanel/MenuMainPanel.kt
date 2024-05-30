package com.roshevasternin.rozval.game.actors.mainPanel

import com.roshevasternin.rozval.game.actors.button.AButton
import com.roshevasternin.rozval.game.utils.advanced.AdvancedGroup
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen

class MenuMainPanel(_screen: AdvancedScreen): AbstractMainPanel(_screen) {

    private val exitBtn = AButton(screen, AButton.Static.Type.Exit)

    var exitBtnBlock = {}

    override fun AdvancedGroup.addActorsOnParentGroup() {
        addExit()
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedGroup.addExit() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(18f, 35f, 124f, 51f)
            setOnClickListener(exitBtnBlock)
        }
    }

}