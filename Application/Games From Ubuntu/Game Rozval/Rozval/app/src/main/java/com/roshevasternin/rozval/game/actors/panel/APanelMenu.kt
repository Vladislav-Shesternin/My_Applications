package com.roshevasternin.rozval.game.actors.panel

import com.roshevasternin.rozval.game.actors.mainPanel.MenuMainPanel
import com.roshevasternin.rozval.game.utils.advanced.AdvancedGroup
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen
import com.roshevasternin.rozval.game.utils.font.FontParameter

class APanelMenu(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font40    = screen.fontGenerator_LondrinaSolid_Regular.generateFont(parameter.setSize(40))

    val mainPanel = MenuMainPanel(screen)

    override fun addActorsOnGroup() {
        addMainPanel()
    }

    // Actors ------------------------------------------------------------------------

    private fun addMainPanel() {
        addActor(mainPanel)
        mainPanel.setBounds(1765f, 0f, 155f, 1080f)
    }

}