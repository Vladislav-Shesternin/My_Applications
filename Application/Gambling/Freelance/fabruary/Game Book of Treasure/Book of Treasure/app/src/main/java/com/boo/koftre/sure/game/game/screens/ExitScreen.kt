package com.boo.koftre.sure.game.game.screens

import com.boo.koftre.sure.game.game.LibGDXGame
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedScreen

class ExitScreen(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        game.navigationManager.exit()
        super.show()
    }

}