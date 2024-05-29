package com.orange.magic.board.doodle.color.game.manager

import com.badlogic.gdx.Gdx
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.screens.*
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedScreen
import com.orange.magic.board.doodle.color.game.utils.runGDX

class NavigationManager(val game: LidaGame) {

    private val backStack = mutableListOf<String>()
    var key: Int? = null
        private set

    fun navigate(toScreenName: String, fromScreenName: String? = null, key: Int? = null) = runGDX {
        this.key = key

        game.updateScreen(getScreenByName(toScreenName))
        backStack.filter { name -> name == toScreenName }.onEach { name -> backStack.remove(name) }
        fromScreenName?.let { fromName ->
            backStack.filter { name -> name == fromName }.onEach { name -> backStack.remove(name) }
            backStack.add(fromName)
        }
    }

    fun back(key: Int? = null) = runGDX {
        this.key = key

        if (isBackStackEmpty()) exit() else game.updateScreen(getScreenByName(backStack.removeLast()))
    }


    fun exit() = runGDX { Gdx.app.exit() }


    fun isBackStackEmpty() = backStack.isEmpty()

    private fun getScreenByName(name: String): AdvancedScreen = when(name) {
        ALoderScreen  ::class.java.name -> ALoderScreen(game)
        EManuelScreen ::class.java.name -> EManuelScreen(game)
        LoRusScreen   ::class.java.name -> LoRusScreen(game)
        MedsikScreen  ::class.java.name -> MedsikScreen(game)
        WeRcordeScreen::class.java.name -> WeRcordeScreen(game)
        GalinkaScreen ::class.java.name -> GalinkaScreen(game)

        else -> EManuelScreen(game)
    }

}