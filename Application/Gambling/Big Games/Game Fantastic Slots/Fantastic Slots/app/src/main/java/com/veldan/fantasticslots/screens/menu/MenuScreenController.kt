package com.veldan.fantasticslots.screens.menu

import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.screens.game.GameScreen
import com.veldan.fantasticslots.screens.options.OptionsScreen
import com.veldan.fantasticslots.utils.controller.ScreenController

class MenuScreenController(
    override val screen: MenuScreen
) : ScreenController {

    fun playHandler() {
        NavigationManager.navigate(GameScreen(), MenuScreen())
    }

    fun optionsHandler() {
        NavigationManager.navigate(OptionsScreen(), MenuScreen())
    }

    fun exitHandler() {
        NavigationManager.exit()
    }

}